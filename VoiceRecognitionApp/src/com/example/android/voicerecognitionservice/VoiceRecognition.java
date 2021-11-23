package com.example.android.voicerecognitionservice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class VoiceRecognition extends Activity implements OnClickListener {
    MyAdapter mListAdapter;
    SimpleCursorAdapter adapter ;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

	private static final int PICK_CONTACT = 123;

    private ListView mList;

    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate our UI from its XML layout description.
        setContentView(R.layout.voicerecognition);

        // Get display items for later interaction
        Button speakButton = (Button) findViewById(R.id.btn_speak);
        Button telephoneButton = (Button) findViewById(R.id.tele);
        Button MapButton = (Button) findViewById(R.id.map);
        Button wayButton = (Button) findViewById(R.id.way);
        Button SmsButton = (Button) findViewById(R.id.sms);
        Button ContactButton = (Button) findViewById(R.id.contact);

        
        

      
        mList = (ListView) findViewById(R.id.listSpeak);

        // Check to see if a recognition activity is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        
		Toast.makeText(getApplicationContext(), "Adding Listener", Toast.LENGTH_SHORT).show();
		telephoneButton.setOnClickListener(this);
        MapButton.setOnClickListener(this);
        wayButton.setOnClickListener(this);
        SmsButton.setOnClickListener(this);
        ContactButton.setOnClickListener(this);
        
        if (activities.size() != 0) {

            speakButton.setOnClickListener(this);
            
            
        } else {
            speakButton.setEnabled(false);
            speakButton.setText("Recognizer not present in your device .");
        }
    }
    
    public void contactList(){


    }

    /**
     * Handle the click on the start recognition button.
     */
    public void onClick(View v) {
    	
    	//button speak 
        if (v.getId() == R.id.btn_speak) {
            startVoiceRecognitionActivity();
        }
        
        //button telephone . 
        if (v.getId() == R.id.tele) {
        	Toast.makeText(getApplicationContext(), "TELEPHONE !!!  ", Toast.LENGTH_SHORT).show();
        	startTelephone();
        }
        
        if (v.getId() == R.id.map) {
        	startMap();
        }
        if (v.getId() == R.id.way) {
        	startway();
        }
        if (v.getId() == R.id.sms) {
        	startSMS();
        }
        
        
        if (v.getId() == R.id.contact) {
        	startContacts();
        }
        
    
        
    }

    public void startTelephone(){
    	Log.i("TEL","start Telephone ");

    	Uri uri = Uri.parse("tel:0648343433");
    	Intent it  = new Intent(Intent.ACTION_VIEW,uri);
    	startActivity(it); 
    }
    
    public void startMap(){
    	Uri uri = Uri.parse("geo:q=Montparnasse");
    	Intent it = new Intent(Intent.ACTION_VIEW,uri);
    	startActivity(it); 
    }
    
    public void startway(){
    	//show ways
    	Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
    	Intent it = new Intent(Intent.ACTION_VIEW,uri);
    	startActivity(it);
    }
    
    public void startSMS(){
    	//send sms
    	Uri uri = Uri.parse("smsto:0800000123");   
    	Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
    	it.putExtra("sms_body", "The SMS text");   
    	startActivity(it);  
    }
    
    public void startContacts(){
    	Intent it = new Intent(Intent.ACTION_VIEW);
        startActivityForResult(it, PICK_CONTACT);
    }
    
    /**
     * Fire an intent to start the speech recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo power");

        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    /**
     * Handle the results from the recognition activity.
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it could have heard
        	 
			
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    matches));
        }
        
        if(requestCode == PICK_CONTACT && resultCode == RESULT_OK){
        	Intent myIntent = new Intent(VoiceRecognition.this, ContactList.class);
        	startActivity(myIntent);
        	
        	  /*ContentResolver cr = getContentResolver();
              Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                      null, null, null, null);
              if (cur.getCount() > 0) {
                  while (cur.moveToNext()) {
                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if (Integer.parseInt(cur.getString(
                              cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                           Cursor pCur = cr.query(
                                     ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                     null,
                                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                                     new String[]{id}, null);
                           while (pCur.moveToNext()) {
                               String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                               Toast.makeText(getApplicationContext(), "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                           }
                          pCur.close();
                      }
                  }
              }
        	Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null,ContactsContract.Contacts.DISPLAY_NAME + " ASC");
            
            startManagingCursor(cursor);

            String[] from = new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

            int[] to = new int[] { R.id.name_entry, R.id.number_entry};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to);
            
            setmListAdapter(adapter);*/
        	

        	
            //Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);
        	//Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,new String[] { Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);
        	/*Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        	while (cursor.moveToNext()) {
                String phoneNumber = cursor.getString(cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 

        		   
        		Toast.makeText(getApplicationContext(), "Adding Listener  "+phoneNumber, Toast.LENGTH_SHORT).show();
        		
        	}
        	
            startManagingCursor(cursor);
            
            //MyAdapter mListAdapter = new MyAdapter(getBaseContext(),  R.layout.list_entry, cursor);

            String[] from = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER};

            int[] to = new int[] { R.id.name_entry, R.id.number_entry};

             adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to);

            */
          
           
        }
        	
        
        super.onActivityResult(requestCode, resultCode, data);
    }


	public void setmListAdapter(MyAdapter mListAdapter) {
		this.mListAdapter = mListAdapter;
	}



	
}