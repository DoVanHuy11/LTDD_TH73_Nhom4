package com.example.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.interf.StartActiFromRecycleView;
import com.example.fashion.model.ItemNavigation;
import com.example.fashion.model.ItemRecycleView;
import com.example.fashion.myadapter.MenuAdapter;
import com.example.fashion.myadapter.ProductRecycleViewAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.fashion.model.Cart;
import com.example.fashion.myadapter.ImageViewPagerAdapter;
import com.example.fashion.myadapter.ProductAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements StartActiFromRecycleView {
    public static ArrayList<Cart> arrayCart;
    private ListView listView;
    private ListView listView_nav;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager mViewPager;
    private ListView listViewMenu;
    private ImageView imgLogo;
    private RecyclerView recycleViewMain;

    //Khai báo firebase
    private DatabaseReference mDatabase;
    //Các node trong firebase
    String IMGVP = "ImageViewPager";
    ///
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;  // time in milliseconds between successive task executions.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        SetActionBar();
        setRecycleView();
        SetViewPager();
        setNavigation();


        //Set hình ảnh cho viewpager
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


    private void setRecycleView() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ArrayList<ItemRecycleView> arrayList = new ArrayList<ItemRecycleView>();
        final ArrayList<String> arrayKeys = new ArrayList<String>();
        final ProductRecycleViewAdapter recycleViewAdapter = new ProductRecycleViewAdapter(this
                ,arrayList,arrayKeys,this);

        mDatabase.child("ItemRecycleView").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot != null){
                    ItemRecycleView item = dataSnapshot.getValue(ItemRecycleView.class);
                    arrayList.add(item);
                    arrayKeys.add(dataSnapshot.getKey());
                    recycleViewAdapter.notifyDataSetChanged();

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
        recycleViewMain.setHasFixedSize(true);
        recycleViewMain.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recycleViewMain.setAdapter(recycleViewAdapter);
    }

    private void setNavigation(){
        //Navigate chứa 2 thành phần logo vs listview menu
        imgLogo.setImageResource(R.drawable.logo);
        imgLogo.setScaleType(ImageView.ScaleType.FIT_XY);
        //
        ArrayList<ItemNavigation> arrayList = new ArrayList<ItemNavigation>();
        arrayList.add(new ItemNavigation(R.drawable.ic_about,"GIỚI THIỆU"));
        arrayList.add(new ItemNavigation(R.drawable.ic_how,"HƯỚNG DẪN"));
        MenuAdapter menuAdapter = new MenuAdapter(arrayList,this);
        listViewMenu.setAdapter(menuAdapter);
        SetClickITemListViewMenu();
    }

    private void SetClickITemListViewMenu() {
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(MainActivity.this, About.class);
                        startActivity(intent);
                        break;
                    case 1:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(MainActivity.this, howToBuy.class);
                        startActivity(intent);
                        break;
                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(MainActivity.this, howToBuy.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    };


    private void SetViewPager() {
        final ArrayList<String> imgAddress = new ArrayList<>();
        final ImageViewPagerAdapter adapter1 = new ImageViewPagerAdapter(this,imgAddress );
        mViewPager.setAdapter(adapter1);
        //Lấy dữ liệu từ firebase
        mDatabase.child(IMGVP).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot != null){
                    imgAddress.add(dataSnapshot.getValue().toString());
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

    private void SetActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        recycleViewMain = (RecyclerView) findViewById(R.id.recycleViewMain);
        toolbar= findViewById(R.id.toolBar_Main);
        navigationView = findViewById(R.id.navigation_view_main);
        drawerLayout = findViewById(R.id.drawerLayout_Main);
        mViewPager  = (ViewPager) findViewById(R.id.viewPager);
        listViewMenu = findViewById(R.id.listView_Menu);
        imgLogo = findViewById(R.id.imgLogo);

        if(arrayCart != null){

        }else {
            arrayCart = new ArrayList<>();
        }

        //Khởi tạo firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
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

    @Override
    public void StartActivity(String key, ItemRecycleView item) {
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("item",item);
        intent.putExtra("key",key);
        startActivity(intent);
    }
}
