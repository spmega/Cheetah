package com.vpath.cloudcontacts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.security.Provider;
import java.util.ServiceConfigurationError;

/**
 * Created by spmega4567 on 8/4/16.
 */
public class CloudContactsSyncService extends Service {
    private CloudConatctSyncAdapter syncAdapter = new CloudConatctSyncAdapter(getApplicationContext(), true);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CloudSyncService", "Service is created");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
