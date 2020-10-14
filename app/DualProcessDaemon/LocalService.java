package com.zt.base.AppBase.DualProcessDaemon;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.honghe.guardtest.IMyAidlInterface;
import com.zt.base.Sample.TestFragment.Activity.MainActivity;

import static com.zt.base.AppBase.DualProcessDaemon.DualProcessDaemon.getMainActivity;


public class LocalService extends Service {
    private static final String TAG = LocalService.class.getName();

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                Log.e("LocalService", "connected with " + iMyAidlInterface.getServiceName());
                if (getMainActivity() == null) {
                    Intent intent = new Intent(LocalService.this.getBaseContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: 链接断开，重新启动 RemoteService");
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class), connection, Context.BIND_IMPORTANT);
        }
    };

    public LocalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: LocalService 启动");
        startService(new Intent(LocalService.this, RemoteService.class));
        bindService(new Intent(LocalService.this, RemoteService.class), connection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getServiceName() throws RemoteException {
            return LocalService.class.getName();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
}
