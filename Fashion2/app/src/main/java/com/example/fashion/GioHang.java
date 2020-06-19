package com.example.fashion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import model.Cart;
import myadapter.ProductCart;

public class GioHang extends AppCompatActivity {
    ListView listCart;
    static TextView txtTotal;
    ImageView imgEmptycart;
    Button btnMuahang;
    ProductCart productCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        actionBar.setTitle("Giỏ hàng");

        ////
        listCart = (ListView) findViewById(R.id.listproduct);
        txtTotal = (TextView) findViewById(R.id.total);
        btnMuahang = (Button) findViewById(R.id.btnmuahang);
        imgEmptycart = (ImageView) findViewById(R.id.giohangrong);
        productCart = new ProductCart(GioHang.this,MainActivity.arrayCart);
        listCart.setAdapter(productCart);

        ///
        LoadListProduct();
        CheckData();
        CatchOnItemListView();
    }

    private void CatchOnItemListView() {
        listCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arrayCart.size() <= 0){
                            imgEmptycart.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.arrayCart.remove(position);
                            productCart.notifyDataSetChanged();
                            LoadListProduct();
                            if(MainActivity.arrayCart.size() <= 0){
                                imgEmptycart.setVisibility(View.VISIBLE);
                            }else {
                                imgEmptycart.setVisibility(View.INVISIBLE);
                                productCart.notifyDataSetChanged();
                                LoadListProduct();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productCart.notifyDataSetChanged();
                        LoadListProduct();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    private void CheckData() {
        if(MainActivity.arrayCart.size() <= 0){
            productCart.notifyDataSetChanged();         //Refresh ListView
            imgEmptycart.setVisibility(View.VISIBLE);
            listCart.setVisibility(View.INVISIBLE);
        }else {
            productCart.notifyDataSetChanged();
            imgEmptycart.setVisibility(View.INVISIBLE);
            listCart.setVisibility(View.VISIBLE);
        }
    }

    public static void LoadListProduct() {
        long tongtien = 0;
        for(int i = 0;i < MainActivity.arrayCart.size();i++){
            tongtien += MainActivity.arrayCart.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(tongtien) + " đ");
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
}
