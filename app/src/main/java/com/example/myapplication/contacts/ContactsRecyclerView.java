package com.example.myapplication.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class ContactsRecyclerView extends RecyclerView.Adapter<ContactsRecyclerView.ViewHolder> {
    private List<Contact> list;
    public ContactsRecyclerView(List<Contact> list) {
        super();
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_simpleadapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = list.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvTell.setText(contact.getTell());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvTell;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_simple_text1);
            tvTell = itemView.findViewById(R.id.list_simple_text2);
        }
    }
}
