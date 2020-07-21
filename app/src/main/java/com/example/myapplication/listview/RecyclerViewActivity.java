package com.example.myapplication.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    //以线性布局来展示列表项
    private LinearLayoutManager linearLayoutManager;
    //以表格方式展示列表
    private GridLayoutManager gridLayoutManager;
    //以瀑布流形式展示
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.list_recycle_view);
        List<MyBean> beanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyBean bean = new MyBean();
            bean.setName("name" + i);
            bean.setPass("pass" + i);
            beanList.add(bean);
        }
        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        gridLayoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerAdapter = new RecyclerAdapter(beanList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}