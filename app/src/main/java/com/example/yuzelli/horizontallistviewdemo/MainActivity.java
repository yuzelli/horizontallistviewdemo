package com.example.yuzelli.horizontallistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.horizontallistviewdemo.fristDemo.HorizontalListView;
import com.example.yuzelli.horizontallistviewdemo.fristDemo.HorizontalListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initFristDemo();

        initSecendDemo();


    }
    Context context ;
    private void initSecendDemo() {
        //已经定义好的全局变量

       int  listCount = 0;
        final String[] a = new String[20];
        for (int i = 0; i < 20; i++) {
            a[i] = "item" + i;
        }
        final ArrayList<String> titles= new ArrayList<>(Arrays.asList(a));
      final LinearLayout thumbnailLinearLayout = (LinearLayout) this.findViewById(R.id.thumbnailLinearLayout);
        for(String title : titles){
            listCount++;
            //加载布局
            View view = getLayoutInflater().inflate(R.layout.cell_item2,null);
            //找到ImageView
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
            //设定图片宽高（80*80）
            int w = getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
            int h = getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setText(title);

            //添加到布局中
            thumbnailLinearLayout.addView(view);
        }


        thumbnailLinearLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取到LinearLayout的宽度  注：在onCreat方法里面直接获取是0 所以要动态的获取
                int width = thumbnailLinearLayout.getWidth();
                //得到LinearLayout
                int itemCount = thumbnailLinearLayout.getChildCount();
                //计算出每一个child的平均宽度
                float itemWidth = width/itemCount*1.0f;
                //得到点击的X坐标
                float touchX = event.getX(0);

             //用这个式子算出点击坐标所在那个child上 （点击的坐标除以每个child的宽度 就可以得出所在child的索引） 需要 +1 吗？
                int coverCount = (int) (touchX/itemWidth);

               //有了索引就能干一些事情了
              doSomething(coverCount);


                for(int i=0;i<itemCount;i++){
                    thumbnailLinearLayout.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
           //添加背景颜色，方便区别 所点击的那个child
                thumbnailLinearLayout.getChildAt(coverCount).setBackgroundColor(Color.rgb(120, 120, 120));
                return false;
            }

            private void doSomething(int coverCount) {
                Toast.makeText(context,titles.get(coverCount),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFristDemo() {
        HorizontalListView hv_demo = (HorizontalListView) this.findViewById(R.id.hv_demo);
        final String[] titles = new String[20];
        for (int i = 0; i < 20; i++) {
            titles[i] = "item" + i;
        }
      final HorizontalListViewAdapter adapter = new HorizontalListViewAdapter(this,titles );
        hv_demo.setAdapter(adapter);
        hv_demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectIndex(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(context,titles[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
