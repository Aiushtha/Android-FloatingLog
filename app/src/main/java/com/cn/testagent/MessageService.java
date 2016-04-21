package com.cn.testagent;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Lin on 16/4/11.
 */
public class MessageService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test","onCreate");
        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "com.cn.testdemo",
                        "com.cn.testdemo.MainService"));
        Log.d("test", "com.cn.testdemo.MainService");
        startService(serviceIntent);
        Log.d("test", "start");
//        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
        Log.d("test", "bind");
    }

//    private final IMessageAidl.Stub mBinder = new IMessageAidl.Stub() {
//
//        @Override
//        public String sendMessage(int type, String cmd) throws RemoteException {
//            return "222";
//        }
//    };
//    private IMessageAidl mService;
//    ServiceConnection serviceConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            mService = IMessageAidl.Stub.asInterface(service);
//            try {
//                String result = mService.sendMessage(0, "1");
//                Toast.makeText(MessageService.this, result, Toast.LENGTH_LONG).show();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            Log.d("test","~~~~~>");
//            Toast.makeText(MessageService.this, "222", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName className) {
//            mService = null;
//        }
//    };
}
