package com.example.yuzelli.horizontallistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.yuzelli.horizontallistviewdemo.fristDemo.HorizontalListView;
import com.example.yuzelli.horizontallistviewdemo.fristDemo.HorizontalListViewAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFristDemo();


    }

    private void initFristDemo() {
        HorizontalListView hv_demo = (HorizontalListView) this.findViewById(R.id.hv_demo);
        final String[] titles = new String[20];
        for (int i = 0; i < 20; i++) {
            titles[i] = "item" + i;
        }
        final   Context context =this;
        hv_demo.setAdapter(new HorizontalListViewAdapter(this,titles ));
        hv_demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,titles[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
