package com.example.fashion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Account> accountArrayList;
    AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        lv = (ListView) findViewById(R.id.lvAccount);
        accountArrayList = new ArrayList<>();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String yourname = bundle.getString("ten");
        String email = bundle.getString("mail");
        String phone = bundle.getString("dt");

        accountArrayList.add(new Account("Họ và tên",yourname,R.drawable.ic_people_24dp));
        accountArrayList.add(new Account("Số điện thoại",phone,R.drawable.ic_phone_24dp));
        accountArrayList.add(new Account("Email",email,R.drawable.ic_mail_24dp));
        accountArrayList.add(new Account("Ngày sinh","11/11/1999",R.drawable.ic_cake_24dp));
        accountArrayList.add(new Account("Giới tính","Nam",R.drawable.ic_sex_24));
        lv.setAdapter(accountAdapter);
    }
}