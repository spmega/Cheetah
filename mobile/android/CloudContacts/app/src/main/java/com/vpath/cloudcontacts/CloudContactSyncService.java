package com.vpath.cloudcontacts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ServiceConfigurationError;

/**
 * Created by spmega4567 on 8/1/16.
 */
public class CloudContactSyncService extends Service {

    private static CloudConatctSyncAdapter syncAdapter = null;

    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (syncAdapterLock) {
            if (syncAdapter == null) {
                syncAdapter = new CloudConatctSyncAdapter(getApplicationContext(), true);
            }
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
