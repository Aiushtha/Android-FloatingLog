package com.cn.testagent;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by Lin on 16/4/11.
 */
public class ConnService extends Service {

    private void log(String message) {
        Log.d("test", message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Received0000 start command.");
//        Intent serviceIntent = new Intent()
//                .setComponent(new ComponentName(
//                        "com.cn.testdemo",
//                        "service.MainService"));
//        startService(serviceIntent);
//        Log.d("test", "start");
//        Log.d("test", "service.MainService");
//
//        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
//        Log.d("test", "bind");
//        unbindService(serviceConnection);
        return START_STICKY;
    }

    //    private IMessageAidl2 mService;
    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d("test", "~~~~~>0");
            IMyAidlInterface myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                myAidlInterface.basicTypes(1, 2, false, 1.0f, 1.0d, "");
                Log.d("test", "~~~~~>1");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            IMessageAidl mService = IMessageAidl.Stub.asInterface(service);
//               IMyAidlInterface myAidlInterface=IMyAidlInterface.stu
//            try {
//                String result = mService.sendMessage(0, "1");
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            Log.d("test","~~~~~>");
//            Toast.makeText(MainActivity.this, "222", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d("test", "~~~~~>dis");
//            mService = null;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return mBinder;
    }

//    private final IMessageAidl.Stub mBinder = new IMessageAidl.Stub() {
//
//        @Override
//        public String sendMessage(int type, String cmd) throws RemoteException {
//            return "111";
//        }
//    };

    private final IMyAidlInterface.Stub mBinder=new IMyAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d("test","~~~~~>4");
        }
    };
}

