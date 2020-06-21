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


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.model.ItemNavigation;
import com.example.fashion.myadapter.MenuAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.fashion.model.Cart;
import com.example.fashion.myadapter.ImageAdapter;
import com.example.fashion.myadapter.ProductAdapter;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Cart> arrayCart;
    private ListView listView;
    private ListView listView_nav;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager mViewPager;
    private ListView listProduct,listViewMenu;
    private ImageView imgLogo;
    String[] navtitle ={
            "SẢN PHẨM", "GIỚI THIỆU", "HƯỚNG DẪN",
    };

    Integer[] navic = {R.drawable.ic_ao, R.drawable.ic_about, R.drawable.ic_how,};
    ///
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;  // time in milliseconds between successive task executions.


    Button btnAdd;

    Integer[] imgId={
            R.drawable.tshirt41, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        SetActionBar();
        SetListProduct();
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

        listView = (ListView) findViewById(R.id.listProduct);
        CatchOnItemListView();


    }
    private void setNavigation(){
        //Navigate chứa 2 thành phần logo vs listview menu
        imgLogo.setImageResource(R.drawable.logo);
        imgLogo.setScaleType(ImageView.ScaleType.FIT_XY);
        //
        ArrayList<ItemNavigation> arrayList = new ArrayList<ItemNavigation>();
        arrayList.add(new ItemNavigation(R.drawable.ic_ao,"Sản phẩm"));
        arrayList.add(new ItemNavigation(R.drawable.ic_about,"Giới thiệu"));
        arrayList.add(new ItemNavigation(R.drawable.ic_how,"Hướng dẫn"));
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
                        intent = new Intent(MainActivity.this, Product_Layout.class);
                        startActivity(intent);
                        break;
                    case 1:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(MainActivity.this, About.class);
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
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter adapter1 = new ImageAdapter(this );
        mViewPager.setAdapter(adapter1);
    }

    private void SetListProduct() {
        ProductAdapter adapter = new ProductAdapter(this ,
                getResources().getStringArray(R.array.mainTitle),
                getResources().getStringArray(R.array.subtitle),
                imgId);
        listProduct = (ListView) findViewById(R.id.listProduct);
        listProduct.setAdapter(adapter);
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
        listProduct = (ListView) findViewById(R.id.listProduct);
        toolbar= findViewById(R.id.toolBar_Main);
        navigationView = findViewById(R.id.navigation_view_main);
        drawerLayout = findViewById(R.id.drawerLayout_Main);
        mViewPager  = (ViewPager) findViewById(R.id.viewPager);
        listViewMenu = findViewById(R.id.listView_Menu);
        imgLogo = findViewById(R.id.imgLogo);
    }

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(position = 0; position <= listView.getCount(); position++){
                    Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        });
    }

    private void CatchOnItemListView2() {
        listView_nav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent[] intent = new Intent[3];
                intent[0] = new Intent(MainActivity.this, Product_Layout.class);
                intent[1] = new Intent(MainActivity.this, About.class);
                intent[2] = new Intent(MainActivity.this, howToBuy.class);

                if (position==0)
                    startActivity(intent[0]);

                else if (position==1)
                        startActivity(intent[1]);

                else if (position==2)
                      startActivity(intent[2]);
            }
        });
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
        return super.onOptionsItemSelected(item);
    }

}
