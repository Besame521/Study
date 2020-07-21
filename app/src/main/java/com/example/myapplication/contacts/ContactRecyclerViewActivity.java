package com.example.myapplication.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ContactRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rvContract;
    private LinearLayoutManager linearLayoutManager;
    private ContactsRecyclerView contactsRecyclerView;
    private final int CONTRACT_PERMISSION_REQUEST_CODE = 1;
    private List<Contact> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_recycler_view);
        rvContract = findViewById(R.id.address_recycle_view);
        linearLayoutManager = new LinearLayoutManager(this);
        rvContract.setLayoutManager(linearLayoutManager);
        contactsRecyclerView = new ContactsRecyclerView(list);
        //判断用户是否已经授权给我们了 如果没有，调用下面方法向用户申请授权，之后系统就会弹出一个权限申请的对话框
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    ContactRecyclerViewActivity.this,new String[]{Manifest.permission.READ_CONTACTS},CONTRACT_PERMISSION_REQUEST_CODE);
        } else {
            readContacts();
        }
    }
    public void readContacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contact contact = new Contact(displayName,number);
                    list.add(contact);
                }
            }
            contactsRecyclerView = new ContactsRecyclerView(list);
            rvContract.setAdapter(contactsRecyclerView);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTRACT_PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts();
            } else {
                Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}