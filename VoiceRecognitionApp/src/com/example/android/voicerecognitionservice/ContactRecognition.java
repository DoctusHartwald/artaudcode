package com.example.android.voicerecognitionservice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts.People;
import android.view.View;
import android.view.View.OnClickListener;

public class ContactRecognition  extends Activity implements OnClickListener  {

	private static final int PICK_CONTACT = 0;

	@Override
	public void onClick(View v) {		
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  switch (reqCode) {
	    case (PICK_CONTACT) :
	      if (resultCode == Activity.RESULT_OK) {
	        Uri contactData = data.getData();
	        Cursor c =  managedQuery(contactData, null, null, null, null);
	        if (c.moveToFirst()) {
	          String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
	          // TODO Whatever you want to do with the selected contact name.
	        }
	      }
	      break;
	  }
	}


}
