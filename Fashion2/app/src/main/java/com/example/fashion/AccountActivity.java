package com.example.fashion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    EditText edtHoTen,edtSoDienThoai,edtMail,edtNgaySinh;
    RadioButton rbNam, rbNu;
    ImageView imgDong;
    Button btDangXuat, btCapNhat, btDoiMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        anhXa();

//        Intent i = getIntent();
//        Bundle bundle = i.getExtras();
//        String yourname = bundle.getString("ten");
//        String email = bundle.getString("mail");
//        String phone = bundle.getString("dt");
//
//        edtHoTen.setText(yourname);
//        edtMail.setText(email);
//        edtMail.setText(phone);

    }

    private void anhXa() {
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtSoDienThoai = (EditText) findViewById(R.id.edtSoDienThoai);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        rbNam =(RadioButton) findViewById(R.id.rbNam);
        rbNu =(RadioButton) findViewById(R.id.rbNu);
        imgDong = (ImageView) findViewById(R.id.imgDong);
        btCapNhat = (Button) findViewById(R.id.btCapNhat);
        btDangXuat = (Button) findViewById(R.id.btDangXuat);
        btDoiMatKhau =(Button) findViewById(R.id.btDoiMatKhau);
    }
}