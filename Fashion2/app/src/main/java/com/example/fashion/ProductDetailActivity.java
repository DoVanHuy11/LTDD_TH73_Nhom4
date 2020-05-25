package com.example.fashion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.imgadapter.ImageAdapter;

public class ProductDetailActivity extends AppCompatActivity {
    private Button btnMinus, btnAdd;
    private TextView txtDigit;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

//        Image slider
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        mViewPager.setAdapter(adapter);

        AnhXa();
        CatchEventSpinner();
    }

    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8 ,9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soLuong);
        spinner.setAdapter(arrayAdapter);
    }

    private void AnhXa(){
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}
