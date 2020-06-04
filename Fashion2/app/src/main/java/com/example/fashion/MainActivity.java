package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

import myadapter.ImageAdapter;
import myadapter.ProductAdapter;

public class MainActivity extends AppCompatActivity {


    ///
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;  // time in milliseconds between successive task executions.

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

        //////////////

        final ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter adapter1 = new ImageAdapter(this );
        mViewPager.setAdapter(adapter1);

        ////
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 5) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
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
