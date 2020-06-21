package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.myadapter.ProductDetail_ImageAdapter;
import com.example.fashion.myadapter.ProductDetail_ListViewAdapter;

public class ProductDetailActivity extends AppCompatActivity {

    private Spinner spinner;
    Button btnAddCart0, btnAddCart1;
    ListView listProductDetail;
    ////
    TextView txtTen,txtGia,txtMota;
    int id = 0;
    String nameDetail = "";
    int priceDetal = 0;
    String detailImg = "";
    String description = "";
    int idsp = 0;

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
        //EventButton();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ProductDetail_ListViewAdapter adapter2 = new ProductDetail_ListViewAdapter(this, maintitle, subtitle, imgid);
        listProductDetail.setAdapter(adapter2);
    }

//    private void EventButton() {
//        btnAddCart0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(MainActivity.arrayCart.size() > 0){      //cập nhật dl
//                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
//                    boolean exists = false;
//                    for(int i = 0; i< MainActivity.arrayCart.size();i++){         //thay đổi sl nhưng vẫn giữ idsp
//                        if(MainActivity.arrayCart.get(i).getIdsp() == id){
//                            MainActivity.arrayCart.get(i).setSoluong(MainActivity.arrayCart.get(i).getSoluong() + sl);
//                            if(MainActivity.arrayCart.get(i).getSoluong() >= 10){
//                                MainActivity.arrayCart.get(i).setSoluong(10);
//                            }
//                            MainActivity.arrayCart.get(i).setGiasp(priceDetal * MainActivity.arrayCart.get(i).getSoluong());
//                            exists = true;
//                        }
//                    }
//                    if(exists == false){       //đi vào nhưng ko kiếm đc hàng nào cùng id ==> ko cập nhật dl ==> tạo dl ms
//                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//                        long newprice = soluong * priceDetal;
//                        MainActivity.arrayCart.add(new Cart(id,nameDetail,newprice,detailImg,soluong));
//                    }
//
//                }else {     //tạo dl ms
//                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//                    long newprice = soluong * priceDetal;
//                    MainActivity.arrayCart.add(new Cart(id,nameDetail,newprice,detailImg,soluong));
//                }
//                Intent intent = new Intent(getApplicationContext(),GioHang.class);
//                startActivity(intent);
//                //Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnAddCart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(MainActivity.arrayCart.size() > 0){      //cập nhật dl
//                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
//                    boolean exists = false;
//                    for(int i = 0; i< MainActivity.arrayCart.size();i++){         //thay đổi sl nhưng vẫn giữ idsp
//                        if(MainActivity.arrayCart.get(i).getIdsp() == id){
//                            MainActivity.arrayCart.get(i).setSoluong(MainActivity.arrayCart.get(i).getSoluong() + sl);
//                            if(MainActivity.arrayCart.get(i).getSoluong() >= 10){
//                                MainActivity.arrayCart.get(i).setSoluong(10);
//                            }
//                            MainActivity.arrayCart.get(i).setGiasp(priceDetal * MainActivity.arrayCart.get(i).getSoluong());
//                            exists = true;
//                        }
//                    }
//                    if(exists == false){       //đi vào nhưng ko biết hàng nào cùng id ==> ko cập nhật dl ==> tạo dl ms
//                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//                        long newprice = soluong * priceDetal;
//                        MainActivity.arrayCart.add(new Cart(id,nameDetail,newprice,detailImg,soluong));
//                    }
//
//                }else {     //tạo dl ms
//                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//                    long newprice = soluong * priceDetal;
//                    MainActivity.arrayCart.add(new Cart(id,nameDetail,newprice,detailImg,soluong));
//                }
//                Intent intent = new Intent(getApplicationContext(),GioHang.class);
//                startActivity(intent);
//                //Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
//            }
//        });
//       }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        if(id == R.id.idcart) {
            Intent cart = new Intent(this, GioHang.class);
            startActivity(cart);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getValues(){
        spinner = (Spinner) findViewById(R.id.spinner);
        btnAddCart0 = (Button) findViewById(R.id.btnAddCart0);
        listProductDetail = (ListView) findViewById(R.id.listProductDetail);

        ////

        txtTen = (TextView) findViewById(R.id.txtViewProductName);
        txtGia = (TextView) findViewById(R.id.txtViewProductPrice);
        txtMota = (TextView)findViewById(R.id.decribe);
    }

    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8 ,9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soLuong);
        spinner.setAdapter(arrayAdapter);
    }

    private void CatchEventButtonClick(){
        btnAddCart0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }});
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }
}
