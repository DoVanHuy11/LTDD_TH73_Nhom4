package com.example.fashion;

import android.content.Intent;
import android.graphics.Color;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.model.Cart;
import com.example.fashion.model.ItemRecycleView;
import com.example.fashion.myadapter.ProductDetail_ImageAdapter;
import com.example.fashion.myadapter.ProductDetail_ListViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private Spinner spinner;
    Button btnAddCart0,  btnSmall, btnMiddle, btnBig;
    ListView listProductDetail;
    String Size = "";
    ViewPager mViewPager;
    ActionBar actionBar;
    TextView tvProductName,tvProductPrice,tvDecribe;

    ///
    private DatabaseReference mDatabase;;
    ////
    ItemRecycleView item;
    String key;
    ///
    TextView txtTen,txtGia,txtMota;
    String id = "";
    int idsp = 0;
    String nameProd = "";
    String priceDetal = "";
    String detailImg = "";
    String description = "";
    int gia = 0;


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


        //Biến chứa thông tin Product chính của activity
        item = (ItemRecycleView) getIntent().getSerializableExtra("item");
        key = getIntent().getStringExtra("key");

        AnhXa();
        SetViewPager();
        SetInfoProduct();
        CatchEventSpinner();
        CatchEventButtonClick();
       // CatchOnItemListView();
        SetFocusForListView();
        SetActionBar();
        //EventButton();
        SetListRelatedProducts();
//        ItemRecycleView itemRecycleView = (ItemRecycleView) getIntent().getSerializableExtra("ItemRecycleView");
//        //int idsp = 0;
//
//        nameProd = itemRecycleView.getName();
//        priceDetal = itemRecycleView.getPrice();
//
//        detailImg = itemRecycleView.getImage();
//        description = itemRecycleView.getDescription();
//        tvProductName.setText(nameProd);
//        tvProductPrice.setText(priceDetal.toString()+ ".000");
//        tvDecribe.setText(description);
//        gia = Integer.parseInt(tvProductPrice.toString());
//        id = itemRecycleView.getId();
//        idsp = Integer.parseInt(id.toString());
//        Picasso.get().load(detailImg).into((Target) mViewPager);



    }

    private void SetInfoProduct() {
        tvProductName.setText(item.getName());
        tvProductPrice.setText(item.getPrice().toString()+".000");
        tvDecribe.setText(item.getDescription());
    }

    private void SetViewPager() {
        final ArrayList<String> arrayList = new ArrayList<String>();
        final ProductDetail_ImageAdapter adapter1 = new ProductDetail_ImageAdapter(this,arrayList);
        mViewPager.setAdapter(adapter1);
        mDatabase.child("ImagesProduct").child(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot!= null){
                    arrayList.add(dataSnapshot.getValue().toString());
                    adapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SetListRelatedProducts() {

        final ArrayList<ItemRecycleView> arrayList = new ArrayList<ItemRecycleView>();
        final ProductDetail_ListViewAdapter adapter2 = new ProductDetail_ListViewAdapter(this, arrayList);
        listProductDetail.setAdapter(adapter2);
        mDatabase.child("ProductsRelated").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot != null){
                    ItemRecycleView item = dataSnapshot.getValue(ItemRecycleView.class);
                    arrayList.add(item);
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
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
//                        MainActivity.arrayCart.add(new Cart(id,nameProd,newprice,detailImg,soluong));
//                    }
//
//                }else {     //tạo dl ms
//                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//                    long newprice = soluong * priceDetal;
//                    MainActivity.arrayCart.add(new Cart(id,nameDetail,newprice,detailImg,soluong));
//                }
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
        else if(id == R.id.idAccount) {
            Intent account = new Intent(this, AccountActivity.class);
            startActivity(account);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AnhXa(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        spinner = (Spinner) findViewById(R.id.spinner);
        btnAddCart0 = (Button) findViewById(R.id.btnAddCart0);
        listProductDetail = (ListView) findViewById(R.id.listRelatedProduct);
        btnSmall= (Button) findViewById(R.id.btnProductSizeS);
        btnMiddle= (Button) findViewById(R.id.btnProductSizeM);
        btnBig= (Button) findViewById(R.id.btnProductSizeB);

        txtTen = (TextView) findViewById(R.id.txtViewProductName);
        txtGia = (TextView) findViewById(R.id.txtViewProductPrice);
        txtMota = (TextView)findViewById(R.id.decribe);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tvProductName = findViewById(R.id.txtViewProductName);
        tvProductPrice= findViewById(R.id.txtViewProductPrice);
        tvDecribe = findViewById(R.id.decribe);
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
                if(Size == ""){
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn size(Nhỏ/Trung/Lớn)!", Toast.LENGTH_SHORT).show();
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long newprice = soluong * gia;
                    MainActivity.arrayCart.add(new Cart(idsp,nameProd,newprice,detailImg,soluong,Size));
                    Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                    //Size = "";
                }
            }});

        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Size = btnSmall.getText().toString();
                if(btnSmall.isClickable()){
                    btnSmall.setBackgroundColor(Color.BLACK);
                    btnSmall.setTextColor(Color.WHITE);
                    btnMiddle.setBackgroundColor(Color.WHITE);
                    btnMiddle.setTextColor(Color.BLACK);
                    btnBig.setBackgroundColor(Color.WHITE);
                    btnBig.setTextColor(Color.BLACK);
                }
            }
        });

        btnMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Size = btnMiddle.getText().toString();
                if(btnMiddle.isClickable() == true){
                    btnMiddle.setBackgroundColor(Color.BLACK);
                    btnMiddle.setTextColor(Color.WHITE);
                    btnSmall.setBackgroundColor(Color.WHITE);
                    btnSmall.setTextColor(Color.BLACK);
                    btnBig.setBackgroundColor(Color.WHITE);
                    btnBig.setTextColor(Color.BLACK);
                }
            }
        });

        btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Size = btnBig.getText().toString();
                if(btnBig.isClickable() == true){
                    btnBig.setBackgroundColor(Color.BLACK);
                    btnBig.setTextColor(Color.WHITE);
                    btnSmall.setBackgroundColor(Color.WHITE);
                    btnSmall.setTextColor(Color.BLACK);
                    btnMiddle.setBackgroundColor(Color.WHITE);
                    btnMiddle.setTextColor(Color.BLACK);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }
}
