package com.cn.testagent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import bean.MessageBean;


/**
 * Created by Lin on 16/4/12.
 */
public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface mService;
    private TextView mLog;

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName + "   " + versionName + "  " + versionCode;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        MessageBean.applicationIdVar=getAppInfo();

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendLogManager.start(v.getContext());
                SendLogManager.send(v.getContext(), new MessageBean()
                        .setSrc("https://www.baidu.com/s?wd=Gson%E6%A0%BC%E5%BC%8F%E5%8C%96%E8%BE%93%E5%87%BA&rsv_spt=1&rsv_iqid=0x8817f2b80003c0f9&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=0&oq=json%E6%A0%BC%E5%BC%8F%E5%8C%96%E5%AD%97%E7%AC%A6%E4%B8%B2&rsv_t=4429OKyjE5SC602U8LncD823n3QOZflRHidSYnMDXgpMG6sEfb2FCP0MHWtaodnM%2FhFD&inputT=1293&rsv_pq=b2248c7b001b85ca&rsv_sug3=171&rsv_sug1=144&rsv_sug7=100&rsv_sug2=0&rsv_sug4=1846")
                        .setContent("{\n" +
                        "    \"people\":[\n" +
                        "        {\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},\n" +
                        "        {\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},\n" +
                        "        {\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}\n" +
                        "    ]\n" +
                        "}"));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendLogManager.send(v.getContext(), new MessageBean().setContent("{\n" +
                        "    \"name\": \"中国\",\n" +
                        "    \"province\": [{\n" +
                        "        \"name\": \"黑龙江\",\n" +
                        "        \"cities\": {\n" +
                        "            \"city\": [\"哈尔滨\", \"大庆\"]\n" +
                        "        }\n" +
                        "    }, {\n" +
                        "        \"name\": \"广东\",\n" +
                        "        \"cities\": {\n" +
                        "            \"city\": [\"广州\", \"深圳\", \"珠海\"]\n" +
                        "        }\n" +
                        "    }, {\n" +
                        "        \"name\": \"台湾\",\n" +
                        "        \"cities\": {\n" +
                        "            \"city\": [\"台北\", \"高雄\"]\n" +
                        "        }\n" +
                        "    }, {\n" +
                        "        \"name\": \"新疆\",\n" +
                        "        \"cities\": {\n" +
                        "            \"city\": [\"乌鲁木齐\"]\n" +
                        "        }\n" +
                        "    }]\n" +
                        "}"));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}