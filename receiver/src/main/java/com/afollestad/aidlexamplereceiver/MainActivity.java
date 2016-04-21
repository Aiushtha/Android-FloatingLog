package com.afollestad.aidlexamplereceiver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

    private ListView listView;
    private MyAdapter adapter;

    public void onEventMainThread(PostEvent event) {
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmessage);
        listView=(ListView)findViewById(R.id.listview);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        EventBus.getDefault().register(this);
    }

    public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

        @Override
        public int getCount() {
            return MessageCache.listMessages.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView textView= (TextView) convertView.findViewById(android.R.id.text1);
            textView.setSingleLine();
            textView.setTextColor(Color.WHITE);
            textView.setText(MessageCache.listMessages.get(position).getSrc());
            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(MainActivity.this,WebActivity.class);
            intent.putExtra("data",MessageCache.listMessages.get(position));
            startActivity(intent);
        }
    }
}
