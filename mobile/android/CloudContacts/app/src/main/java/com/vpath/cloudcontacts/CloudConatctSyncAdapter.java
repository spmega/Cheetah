package com.vpath.cloudcontacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

/**
 * Created by spmega4567 on 7/31/16.
 */
public class CloudConatctSyncAdapter extends AbstractThreadedSyncAdapter {

    public CloudConatctSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    public CloudConatctSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d("SyncAdapter", "The sync adapter is running");
    }
}
