package com.cn.testagent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import bean.MessageBean;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by Lin on 16/4/15.
 */
public class SendLogManager {

    public final  static String FILETAG=SendLogManager.class.getName();
    public static SendLogManager instance;

    private final Logger log = LoggerFactory.getLogger(MainActivity.class);

    private SendLogManager(){};
    public static SendLogManager getInstance(){
        return instance=(instance==null?new SendLogManager().init():instance);

    }
    public SendLogManager init(){

        String path= Environment.getExternalStorageDirectory() + File.separator + "testlog" + File.separator + "testlog.txt";
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(path);
        logConfigurator.setRootLevel(Level.ALL);
        logConfigurator.setLevel("org.apache", Level.ALL);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();

        return this;
    }

    public static void send(Context context, MessageBean bean) {

        getInstance().log.debug(bean.content);

        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "com.afollestad.aidlexamplereceiver",
                        "com.afollestad.aidlexamplereceiver.MainService"));
        serviceIntent.putExtra("data", bean);
        context.startService(serviceIntent);
    }
    public static void start(Context context) {
        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "com.afollestad.aidlexamplereceiver",
                        "com.afollestad.aidlexamplereceiver.FloatViewService"));
        context.startService(serviceIntent);
    }
}
