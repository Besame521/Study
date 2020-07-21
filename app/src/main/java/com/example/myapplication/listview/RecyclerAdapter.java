package com.example.myapplication.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<MyBean> list;
    public RecyclerAdapter(List<MyBean> list) {
        super();
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //初始化item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_simpleadapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定item的view
        holder.tvName.setText(list.get(position).getName());
        holder.tvPass.setText(list.get(position).getPass());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_simple_text1);
            tvPass = itemView.findViewById(R.id.list_simple_text2);
        }
    }
}
