package com.afollestad.aidlexamplereceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.afollestad.aidlexample.IMainService;
import com.afollestad.aidlexample.MainObject;

import java.util.ArrayList;
import java.util.List;

import bean.MessageBean;
import de.greenrobot.event.EventBus;

/**
 * @author Aidan Follestad (afollestad)
 */
public class MainService extends Service {

    private void log(String message) {
        Log.v("test", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        register();
    }

    public void onEvent(PostEvent event) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void register() {
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if(intent!=null) {
                MessageBean bean = (MessageBean) intent.getSerializableExtra("data");
                if(bean!=null) {
                    MessageCache.addMessage(bean);
                    EventBus.getDefault().post(new PostEvent(bean));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return mBinder;
    }

    private final IMainService.Stub mBinder = new IMainService.Stub() {
        @Override
        public MainObject[] listFiles(String path) throws RemoteException {
            log("Received list command for: " + path);
            List<MainObject> toSend = new ArrayList<>();
            // Generates a list of 1000 objects that aren't sent back to the binding Activity
            for (int i = 0; i < 1000; i++)
                toSend.add(new MainObject("/example/item" + (i + 1)));
            return toSend.toArray(new MainObject[toSend.size()]);
        }


        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }

        @Override
        public String send(String path) throws RemoteException {
            return "qqqq";
        }
    };
}
