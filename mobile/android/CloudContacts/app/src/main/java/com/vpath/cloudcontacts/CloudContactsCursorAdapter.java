package com.vpath.cloudcontacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by spmega4567 on 7/30/16.
 */

public class CloudContactsCursorAdapter extends CursorAdapter {

    public CloudContactsCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.contacts_list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textViewName = (TextView) view.findViewById(R.id.contact_name);
        TextView textViewId = (TextView) view.findViewById(R.id.contact_id);
        TextView textViewPhone = (TextView) view.findViewById(R.id.contact_phone);
        TextView textViewEmail = (TextView) view.findViewById(R.id.contact_email);

        // Extract properties from cursor
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

        Cursor phoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[] { String.valueOf(id) }, null);

        Cursor emailCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[] { String.valueOf(id) }, null);

        String phoneNum = "";
        String phoneNumVersion = "";
        if(phoneCursor.getCount()>0){
            phoneCursor.moveToNext();
            phoneNum = ("PHONE: "+
                    phoneCursor.getString(
                            phoneCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER)));
            phoneNumVersion = ("PHONE_VERSION: "+
                    phoneCursor.getString(
                            phoneCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.Phone.DATA_VERSION)));
        }


        String email = "";
        String emailVersion = "";
        if(emailCursor.getCount()>0) {
            emailCursor.moveToNext();
            email = ("EMAIL: " +
                    emailCursor.getString(
                            emailCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.Email.ADDRESS)));

            emailVersion = ("EMAIL: " +
                    emailCursor.getString(
                            emailCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.Email.DATA_VERSION)));
        }

        // Populate fields with extracted properties
        textViewName.setText("Name: "+name);
        textViewId.setText("ID: "+String.valueOf(id));
        textViewEmail.setText(email + " " + emailVersion);
        textViewPhone.setText(phoneNum + " " + phoneNumVersion);
    }
}