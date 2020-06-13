package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import myadapter.ProductDetail_ImageAdapter;
import myadapter.ProductDetail_ListViewAdapter;

public class ProductDetailActivity extends AppCompatActivity {

    private Spinner spinner;
    Button btnAddCart0, btnAddCart1;
    ListView listProductDetail;

    Integer [] imgid = {
            R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7,R.drawable.img8
    };

    String [] maintitle ={
            "nebula1", "nebula2", "nebula3", "nebula4",
            "nebula5", "nebula6", "nebula7", "nebula8"
    };

    String [] subtitle ={
            "Simple description", "Simple description", "Simple description", "Simple description",
            "Simple description", "Simple description", "Simple description", "Simple description"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

//        Image slider
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ProductDetail_ImageAdapter adapter1 = new ProductDetail_ImageAdapter(this);
        mViewPager.setAdapter(adapter1);

        getValues();
        CatchEventSpinner();
        CatchEventButtonClick();
        CatchOnItemListView();
        SetFocusForListView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ProductDetail_ListViewAdapter adapter2 = new ProductDetail_ListViewAdapter(this, maintitle, subtitle, imgid);
        listProductDetail = (ListView)findViewById(R.id.listProductDetail);
        listProductDetail.setAdapter(adapter2);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8 ,9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soLuong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getValues(){
        spinner = (Spinner) findViewById(R.id.spinner);
        btnAddCart0 = (Button) findViewById(R.id.btnAddCart0);
        btnAddCart1 = (Button) findViewById(R.id.btnAddCart1);
        listProductDetail = (ListView) findViewById(R.id.listProductDetail);
    }

    private void CatchEventButtonClick(){
        btnAddCart0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }});

        btnAddCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SetFocusForListView(){
        listProductDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void CatchOnItemListView() {
        listProductDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(position = 0; position <= listProductDetail.getCount(); position++){
                    Intent intent = new Intent(getApplication(), ProductDetailActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        });
    }
}
