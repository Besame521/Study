package com.example.myapplication.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private ListView lvTest;

    private String[] stringArray = new String[]{"第一个","第二个","第三个","第四个","第五个","第六个","第七个"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTest = findViewById(R.id.list_list);
        //ArrayListAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_arrayadapter,R.id.array_list_item, stringArray);
        //
        List<Map<String,String>> mapList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String,String> map = new ArrayMap<>();
            map.put("title","title"+i);
            map.put("inside","inside"+i);
            mapList.add(map);
        }
        //SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.list_simpleadapter,
                new String[]{"title","inside"},new int[]{R.id.list_simple_text1,R.id.list_simple_text2});
        //BaseAdapter
        List<MyBean> beanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyBean bean = new MyBean();
            bean.setName("name" + i);
            bean.setPass("pass" + i);
            beanList.add(bean);
        }
        MyBaseAdapter baseAdapter = new MyBaseAdapter(beanList,this);

        lvTest.setAdapter(baseAdapter);

    }
}