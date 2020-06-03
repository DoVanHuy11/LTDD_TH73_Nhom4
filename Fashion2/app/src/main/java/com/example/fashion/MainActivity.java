package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import myadapter.ProductAdapter;

public class MainActivity extends AppCompatActivity {
    private GridView listProduct;

    Button btnAdd;

    String[] maintitle={
            "San pham 1", "San pham 2", "San pham 3", "San pham 4", "San pham 5", "San pham 6"
    };

    String[] subtitle={
            "Subtitle 1", "Subtitle 2", "Subtitle 3",  "Subtitle 4", "Subtitle 5", "Subtitle 6",
    };

    Integer[] imgId={
            R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductAdapter adapter = new ProductAdapter(this , maintitle, subtitle, imgId);
        listProduct = (GridView) findViewById(R.id.listview);
        listProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.idcart)
        {
            Intent cart = new Intent(this,GioHang.class);
            startActivity(cart);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
