package com.afollestad.aidlexamplereceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import bean.MessageBean;
import de.greenrobot.event.EventBus;

/**
 * Created by Lin on 16/4/16.
 */
public class WebActivity extends Activity {
    private WebView webView;

    public static TextView textView;

    public static String content;
    private Intent intent;
    private MessageBean bean;

    public void onEventMainThread(PostEvent event) {
        content = event.msg.content;
        webView.loadUrl("file:///android_asset/index.html");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        intent = getIntent();
        bean = (MessageBean) intent.getSerializableExtra("data");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:writeHttp('" + bean.src + "'"
                        + "," + "'" + PrintFormat.url2json(bean.src) + "'"+
                        "," + "'" + PrintFormat.printJson2html(bean.content) + "'"+ ")");

//                webView.loadUrl("javascript:writeText('" + bean.src + "'" + "," + "'" + PrintFormat.printJson2html(bean.content) + "'" + ")");
            }
        });
        webView.loadUrl("file:///android_asset/index.html");
    }


    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
//
//            TextView textView=new TextView(this);
//            textView.setText("123");
//            textView.setBackgroundColor(Color.RED);
//            setContentView(textView);
//
//        }else{
//           Button btn=new Button(this);
//            btn.setText("45");
//            btn.setBackgroundColor(Color.YELLOW);
//            setContentView(btn);
//
//        }
//
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
