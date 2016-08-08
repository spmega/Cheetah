package com.vpath.cloudcontacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CloudContactsMainActivity extends AppCompatActivity {

    private final String SCOPE = "https://www.googleapis.com/auth/googletalk";
    private final String AUTH_TYPE = "oauth2:" + SCOPE;
    private final String SHARED_PREF_NAME = "CloudContactsSharedPref";
    private String TOKEN = "";
    private static final int ACCOUNT_CODE = 1601;
    private static final int AUTHORIZATION_CODE = 1601;
    private String accountName;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_contacts_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account selAccount = accounts[0];

        accountName = selAccount.name;
        Log.d("MainActivity", "AccountName is "+accountName);
        accountManager.getAuthToken(selAccount, "oauth2:https://www.googleapis.com/auth/googletalk", null, this, new CloudContactOnTokenAquired(), null);

        JSONObject json = null;
        try {
            json = new CloudContactsAsyncTask().execute(new URL("http://192.168.1.148:8080/greeting")).get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.list_contact);
        listView.setAdapter(new CloudContactsCursorAdapter(this, this.getAllContacts(), 0));

        TextView textView = (TextView) findViewById(R.id.hello_text);
        if(json != null)
            try {
                textView.setText(json.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        TextView accountInfo = (TextView) findViewById(R.id.account_info);
        accountInfo.setText("Name: " + selAccount.name);
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(selAccount, "com.vpath.cloudcontacts", settingsBundle);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cloud_contacts_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == AUTHORIZATION_CODE) {

            } else if (requestCode == ACCOUNT_CODE) {
                accountName = data
                        .getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                // invalidate old tokens which might be cached. we want a fresh
                // one, which is guaranteed to work
            }
        }
    }

    public String generateTokenKey() {
        return String.valueOf(this.hashCode());
    }

    @Override
    public int hashCode() {
        int result = SCOPE != null ? SCOPE.hashCode() : 0;
        result = 31 * result + (AUTH_TYPE != null ? AUTH_TYPE.hashCode() : 0);
        result = 31 * result + (SHARED_PREF_NAME != null ? SHARED_PREF_NAME.hashCode() : 0);
        return result;
    }

    public Cursor getAllContacts() {
        String[] projection = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + ">=0";
        String[] selectionArgs = null;
        String orderBy = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

        Cursor contactsCursor = getContentResolver()
                .query(ContactsContract.Contacts.CONTENT_URI, projection, selection, selectionArgs, orderBy);

        return contactsCursor;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CloudContactsMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.vpath.cloudcontacts/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CloudContactsMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.vpath.cloudcontacts/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class CloudContactOnTokenAquired implements AccountManagerCallback<Bundle>{
        @Override
        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            try {
                Bundle bundle = accountManagerFuture.getResult();
                Log.d("MainActivity:", "Auth toke is: "+bundle.getString(AccountManager.KEY_AUTHTOKEN));
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }
        }
    }
}
