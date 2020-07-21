package com.example.myapplication.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class MyBaseAdapter extends BaseAdapter {
    private List<MyBean> list;
    private LayoutInflater layoutInflater;
    public MyBaseAdapter(List list, Context context) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //手机在滚动屏幕时，超出屏幕之外的ViewItem要被移除
        //如果有10000个ViewItem，那么getView方法就要调用10000次，加载布局文件10000次，然后创建ViewItem，这是相当耗资源的
        //为了提高性能，就需要对移除的ViewItem进行复用，每次滚动屏幕移除的ViewItem就是convertView
        //因此每次调用方法时，都要对convertView判空，如果为空才去加载布局文件，创建新的View，否则进行复用。
        //这里的ViewHolder相当于一个容器，承载了一个ViewItem中所有的组件，避免了每次都要findViewById
        //一次findViewById之后，通过setTag方法将其设置到convertView中，下次就不用再查找了
        MyHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_simpleadapter,viewGroup,false);
            holder = new MyHolder();
            holder.name = view.findViewById(R.id.list_simple_text1);
            holder.pass = view.findViewById(R.id.list_simple_text2);
            view.setTag(holder);
        }else {
            holder = (MyHolder)view.getTag();
        }
        MyBean bean = (MyBean) getItem(i);
        holder.name.setText(bean.getName());
        holder.pass.setText(bean.getPass());
        return view;
    }

    private class MyHolder{
        TextView name,pass;
    }
}
