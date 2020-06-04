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
    private ListView listProduct;

    Button btnAdd;

    String[] maintitle={
            "Daddy Shirt", "Barbed Wire Shirt", "Warning Sleeve", "Warning Tee Đen",  "Color Block Tee", "Rat TShirt - RTS"
    };

    String[] subtitle={
            "357.000", "439.000", "365.000",  "512.000", "355.000", "455.000",
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
        listProduct = (ListView) findViewById(R.id.listview);
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
