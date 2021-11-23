package com.example.todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Main extends Activity implements View.OnClickListener,DialogInterface.OnClickListener, OnKeyListener{


	//1§
	EditText txtItem;
	EditText txtDescription;
	Button btnAdd;
	private ListView listItem;

	List<String> toDoItems;
	ArrayAdapter<String> aa;

	Spinner mProcessus;
	//Partie SMS
	Button btnSendSMS;
	EditText txtPhoneNo;
	EditText txtMessage;


	//§9
	String[] processusMetier ={"EAM Pooler","I/F Guardian","I/F PGI","I/F Micado","I/F ECM","I/F Visu GC","I/F CAO","EAM Monitoring","WF01","WF02","WF03","WF04","WF05","RP01","RP02","R03","Autre","Sans Objet"};


	//Création de la ArrayList qui nous permettra de remplire la listView
	ArrayList<HashMap<String, String>> listItemData = new ArrayList<HashMap<String, String>>();
	ArrayAdapter<String> mProcessusSpinnerAdaptor;
	SimpleAdapter mSchedule;


	//On déclare la HashMap qui contiendra les informations pour un item
	HashMap<String, String> map;




	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//2§
		txtItem = (EditText) findViewById(R.id.txtItem);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		listItem  = (ListView) findViewById(R.id.listItems);
		txtDescription = (EditText)  findViewById(R.id.txtDescription);

		//3§
		btnAdd.setOnClickListener(this);
		txtItem.setOnClickListener(this);

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);

		mProcessus = (Spinner) findViewById(R.id.Spinner01);
		btnSendSMS.setOnClickListener(this);
		txtPhoneNo.setOnClickListener(this);
		txtMessage.setOnClickListener(this);

		//spinner
		//mProcessus.setOnClickListener(this);
		//mProcessus.setOnItemSelectedListener(this);

		//4§
		toDoItems = new ArrayList<String>();
		aa = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,toDoItems);
		listItem.setAdapter(aa);      

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, processusMetier);    
		mProcessus.setAdapter(adapter);




		//Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
		mSchedule = new SimpleAdapter (this.getBaseContext(), listItemData, R.layout.affichageitem,
				new String[] {"img", "titre", "description"}, new int[] {R.id.img, R.id.titre, R.id.description});

		map = new HashMap<String, String>();
		map.put("titre", "AN-EX TEST");
		map.put("description", "Default Logiciel");
		map.put("img", String.valueOf(R.drawable.vide));
		listItemData.add(map);

		listItem.setAdapter(mSchedule);
	}


	/**
	 * §8
	 * @param pMenu
	 * @return
	 */
	public boolean onCreateOptionsMenu(Menu pMenu){

		super.onCreateOptionsMenu(pMenu);
		MenuItem item ;
		item = pMenu.add("KO");
		item.setIcon(R.drawable.ko);

		item = pMenu.add("Attente Info");
		item.setIcon(R.drawable.lamp);

		item = pMenu.add("Done");
		item.setIcon(R.drawable.tick);

		item = pMenu.add("Todo");
		item.setIcon(R.drawable.todo);

		item = pMenu.add("trash");
		item.setIcon(R.drawable.trash);

		return true;
	}
	/**
	 * §9
	 * @param pTitle
	 * @param items
	 */
	private void displayPopUp(String pTitle,String items[]){
		AlertDialog.Builder  builder = new AlertDialog.Builder(this);
		builder.setTitle(pTitle);
		builder.setItems(items, this);
		builder.show();
	}

	public boolean onOptionsItemSelectedv1(MenuItem pItem){
		super.onOptionsItemSelected(pItem);
		if(pItem.hasSubMenu()== false){
			if(pItem.getTitle() == "KO" || pItem.getTitle() == "Attente Info" || pItem.getTitle() == "Done" || pItem.getTitle() =="Todo"){
				this.displayPopUp("Processus concernés", processusMetier);
				this.txtItem.setText(""); //Reset the text button input

			}
			if(pItem.getTitle() == "trash"){
				int index = listItem.getSelectedItemPosition();
				this.deleteItem(index);
			}
		}
		return true;
	}
	/**
	 * §5
	 * @param pItem
	 */
	private void addItem(String pItem){
		if(pItem!=null && pItem.length()>0){
			Toast.makeText(getApplicationContext(), pItem + "added", Toast.LENGTH_SHORT).show();
			this.toDoItems.add(pItem);
			this.aa.notifyDataSetChanged();
			this.txtItem.setText(""); //Reset the text button input
		}
	}

	private void deleteItem(int pItemId){
		if(pItemId >= 0 ){
			String itemName = (String)listItem.getItemAtPosition(pItemId);
			Toast.makeText(getApplicationContext(), itemName + "deleted", Toast.LENGTH_SHORT).show();
			this.toDoItems.remove(pItemId);
			aa.notifyDataSetChanged();
		}
	}

	/**
	 * §10 et 11
	 */
	public boolean onOptionsItemSelected(MenuItem pItem){
		super.onOptionsItemSelected(pItem);
		if(pItem.hasSubMenu()== false){
			if(pItem.getTitle() == "KO"){
				//this.displayPopUp("Processus concernés", processusMetier);
				this.txtItem.setText(""); //Reset the text button input
				this.txtDescription.setText("");
				int index = listItem.getSelectedItemPosition();
				updateKoItemToList(index, pItem.getTitleCondensed().toString());
			}
			if( pItem.getTitle() == "Attente Info"){
				//this.displayPopUp("Processus concernés", processusMetier);
				this.txtItem.setText(""); //Reset the text button input
				this.txtDescription.setText("");
				int index = listItem.getSelectedItemPosition();
				updateAttenteInfoItemToList(index, pItem.getTitleCondensed().toString());
			}
			if(pItem.getTitle() == "Done" ){
				//this.displayPopUp("Processus concernés", processusMetier);
				this.txtItem.setText(""); //Reset the text button input
				this.txtDescription.setText("");
				int index = listItem.getSelectedItemPosition();
				updateDoneItemToList(index, pItem.getTitleCondensed().toString());
			}
			if(pItem.getTitle() =="Todo"){
				//this.displayPopUp("Processus concernés", processusMetier);
				this.txtItem.setText(""); //Reset the text button input
				this.txtDescription.setText("");
				int index = listItem.getSelectedItemPosition();
				updateTodoItemToList(index, pItem.getTitleCondensed().toString());
			}
			if(pItem.getTitle() == "trash"){
				int index = listItem.getSelectedItemPosition();
				this.deleteItemToList(index);
			}
		}
		return true;
	}

	private void updateKoItemToList(int pItemId, String pProcessus){
		if(pItemId >= 0 ){
			HashMap<String, String> itemName = (HashMap<String, String>) listItem.getItemAtPosition(pItemId);
			map = new HashMap<String, String>();
			String vTitre = itemName.get("titre");
			String vDescription = itemName.get("description");

			int vOldItemData = listItemData.indexOf(itemName);
			map.put("titre", vTitre);
			map.put("description", pProcessus+" "+vDescription);
			map.put("img", String.valueOf(R.drawable.ko));
			listItemData.add(map);

			Toast.makeText(getApplicationContext(), itemName.get("titre") + " has been updated.", Toast.LENGTH_LONG).show();
			listItemData.remove(vOldItemData);

			this.mSchedule.notifyDataSetChanged();
		}
	}

	private void updateAttenteInfoItemToList(int pItemId, String pProcessus){
		if(pItemId >= 0 ){
			HashMap<String, String> itemName = (HashMap<String, String>) listItem.getItemAtPosition(pItemId);
			map = new HashMap<String, String>();
			String vTitre = itemName.get("titre");
			String vDescription = itemName.get("description");

			int vOldItemData = listItemData.indexOf(itemName);
			map.put("titre", vTitre);
			map.put("description", pProcessus+" "+vDescription);
			map.put("img", String.valueOf(R.drawable.lamp));
			listItemData.add(map);

			Toast.makeText(getApplicationContext(), itemName.get("titre") + " has been updated.", Toast.LENGTH_LONG).show();
			listItemData.remove(vOldItemData);

			this.mSchedule.notifyDataSetChanged();
		}
	}

	private void updateDoneItemToList(int pItemId, String pProcessus){
		if(pItemId >= 0 ){
			HashMap<String, String> itemName = (HashMap<String, String>) listItem.getItemAtPosition(pItemId);
			map = new HashMap<String, String>();
			String vTitre = itemName.get("titre");
			String vDescription = itemName.get("description");

			int vOldItemData = listItemData.indexOf(itemName);
			map.put("titre", vTitre);
			map.put("description", pProcessus+" "+vDescription);
			map.put("img", String.valueOf(R.drawable.tick));
			listItemData.add(map);

			Toast.makeText(getApplicationContext(), itemName.get("titre") + " has been updated.", Toast.LENGTH_LONG).show();
			listItemData.remove(vOldItemData);

			this.mSchedule.notifyDataSetChanged();
		}
	}

	private void updateTodoItemToList(int pItemId, String pProcessus){
		if(pItemId >= 0 ){
			HashMap<String, String> itemName = (HashMap<String, String>) listItem.getItemAtPosition(pItemId);
			map = new HashMap<String, String>();
			String vTitre = itemName.get("titre");
			String vDescription = itemName.get("description");

			int vOldItemData = listItemData.indexOf(itemName);
			map.put("titre", vTitre);
			map.put("description", pProcessus+" "+vDescription);
			map.put("img", String.valueOf(R.drawable.todo));
			listItemData.add(map);

			Toast.makeText(getApplicationContext(), itemName.get("titre") + " has been updated.", Toast.LENGTH_LONG).show();
			listItemData.remove(vOldItemData);

			this.mSchedule.notifyDataSetChanged();
		}
	}


	private void deleteItemToList(int pItemId){
		if(pItemId >= 0 ){
			HashMap<String, String> itemName = (HashMap<String, String>) listItem.getItemAtPosition(pItemId);
			Toast.makeText(getApplicationContext(), itemName.get("titre") + " has been deleted. (" + itemName.get("description")+").", Toast.LENGTH_LONG).show();
			listItemData.remove(pItemId);
			this.mSchedule.notifyDataSetChanged();
		}
	}


	private void addItemToListDisplay(){
		map = new HashMap<String, String>();
		
		String       vProcessusSelected = ""+(String) mProcessus.getSelectedItem(); 
		map.put("titre", "AN-EX "+this.txtItem.getText().toString());
		if(this.txtDescription!=null && txtDescription.getText().toString().length()>0){
			map.put("description",vProcessusSelected+" "+this.txtDescription.getText().toString()+"\n");
		}
		else{
			map.put("description","Processus: "+vProcessusSelected +" "+"Default logiciel.");
		}

		map.put("img", String.valueOf(R.drawable.vide));
		listItemData.add(map);

		Toast.makeText(getApplicationContext(), this.txtItem.getText().toString() + " added", Toast.LENGTH_SHORT).show();

		this.mSchedule.notifyDataSetChanged();
		this.txtItem.setText("");
		this.txtDescription.setText("");
	}

	public void sendSMSList() throws ParseException{
		Set<Entry<String, String>> vKeyListItems = map.entrySet();
		String vMessageToSent = null ;

		int i = 0;

		if (map!=null){
			i=map.size();
			if(map.size()==0){
				Toast.makeText(getBaseContext(), 
						"Attention la liste des elements envoyes est vide.", 
						Toast.LENGTH_LONG).show();	
			}
		}

		for (Entry<String, String> entry : vKeyListItems) {
			vMessageToSent = vMessageToSent + entry.getKey().toString()+": "+entry.getValue()+"\n";
		}


		Log.i("MESSAGESMS",vMessageToSent);
		String phoneNo = txtPhoneNo.getText().toString();
		String message = txtMessage.getText().toString();                 

		if (phoneNo.length()>0 && message.length()>0)                
			sendSMS(phoneNo, this.constructCurrentDate()+"\n Subject :"+message+"\n"+vMessageToSent,i);                
		else{
			Toast.makeText(getBaseContext(), 
					"Please enter both phone number and message.", 
					Toast.LENGTH_LONG).show();
		}
	}

	private String constructCurrentDate() throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	//---sends an SMS message to another device---
	private void sendSMS(String phoneNumber, String message,final int numberItemSend)
	{        
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
				new Intent(SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		//---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver(){

			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS sent to recipient . * Number of items :"+numberItemSend, 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic failure", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Radio off", 
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		//---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS delivered", 
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered", 
							Toast.LENGTH_SHORT).show();
					break;                        
				}
			}
		}, new IntentFilter(DELIVERED));        

		Log.i("SMS", "Enter SMS ");
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);      

		Log.i("SMS", "SMS sent out !");
	}
	/**
	 * §6
	 */
	public void onClick(View v) {
		Log.i("CLICK", " Boutton CLICKED OUT **********************");

		if(v == this.btnAdd){
			addItemToListDisplay();
			//this.addItem(this.txtItem.getText().toString());
		}

		if(v == this.btnSendSMS){
			try {
				sendSMSList();
			} catch (ParseException e) {
				this.txtMessage.setText(e.toString());
				e.printStackTrace();
			}

		}
	}

	/**
	 * §7
	 */
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			this.addItemToListDisplay();
		}
		return false;
	}


	@Override
	public void onClick(DialogInterface dialog, int item) {
		// TODO Auto-generated method stub

	}

}