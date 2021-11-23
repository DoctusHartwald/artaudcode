package com.example.android.voicerecognitionservice;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.SimpleCursorAdapter;


public class ContactList extends Activity {
	
	SimpleCursorAdapter adapter ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         //setContentView(R.layout.voicerecognition);



       // Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null,null);
        
        startManagingCursor(cursor);

        String[] from = new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        int[] to = new int[] { R.id.name_entry, R.id.number_entry};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to);
        
        
        this.setAdapter(adapter);
    }
	public SimpleCursorAdapter getAdapter() {
		return adapter;
	}
	public void setAdapter(SimpleCursorAdapter adapter) {
		this.adapter = adapter;
	}

	
}
