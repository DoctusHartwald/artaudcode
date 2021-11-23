package com.example.android.voicerecognitionservice;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

class MyAdapter extends ResourceCursorAdapter {
	public MyAdapter(Context context, int layout, Cursor c) {
		 
		super(context, layout, c);
	}
	
	/*
	@Override
    public View newView(Context context, Cursor cur, ViewGroup parent) {
    	LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return vi.inflate(R.layout.list_entry, parent, false);
    }*/

	@Override
	public void bindView(View view, Context context, Cursor c) {
        TextView nameContact = (TextView)view.findViewById(R.id.name_entry);
        TextView numberContact = (TextView)view.findViewById(R.id.number_entry);

        nameContact.setText(c.getString(c.getColumnIndex(Phone.DISPLAY_NAME)));
        numberContact.setText(c.getString(c.getColumnIndex(Phone.NUMBER)));

	}


}
