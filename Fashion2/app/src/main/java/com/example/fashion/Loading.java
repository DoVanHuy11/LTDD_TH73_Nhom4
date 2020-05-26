package com.example.fashion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Loading extends AppCompatActivity {

    Button btnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSub = (Button)findViewById(R.id.btnSubscribe);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loading.this,SubscribeActivity.class);
                startActivity(i);
                Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                startActivity(n);
            }
        });
    }
}
