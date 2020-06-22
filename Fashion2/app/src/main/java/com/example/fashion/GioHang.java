package com.example.fashion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.fashion.myadapter.ProductCart;

public class GioHang extends AppCompatActivity {
    ListView listCart,listViewMenu;
    static TextView txtTotal;
    ImageView imgEmptycart;
    Button btnmuahang;
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
        btnmuahang = (Button) findViewById(R.id.btnmuahang);
        imgEmptycart = (ImageView) findViewById(R.id.giohangrong);
        productCart = new ProductCart(GioHang.this,MainActivity.arrayCart);
        listCart.setAdapter(productCart);
//
//        ///
        LoadListProduct();
        CheckData();
        CatchOnItemListView();
        Payment();
    }

    private void Payment() {
        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayCart.size() > 0){
                    final Dialog dialog = new Dialog(GioHang.this);
                    dialog.setContentView(R.layout.thanh_toan);

                    final EditText NameCus = dialog.findViewById(R.id.NameCustomer);
                    final EditText AddressCus = dialog.findViewById(R.id.AddressCustomer);
                    final EditText PhoneCus = dialog.findViewById(R.id.phoneNumber);
                    final Button btSend = dialog.findViewById(R.id.btnSend);

                    btSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ten = NameCus.getText().toString();
                            String diachi = AddressCus.getText().toString();
                            String sdt = PhoneCus.getText().toString();

                            if(ten.isEmpty()){
                                NameCus.setError("Vui lòng nhập học tên!");
                            }
                            else if(diachi.isEmpty()){
                                AddressCus.setError("Vui lòng nhập địa chỉ!");
                            }
                            else if(sdt.isEmpty()){
                                PhoneCus.setError("Vui lòng nhập số điện thoại!");
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Bạn đã đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                imgEmptycart.setVisibility(View.VISIBLE);
                                listCart = null;
                                MainActivity.arrayCart = null;
                                productCart.notifyDataSetChanged();
                                long tongtien = 0;
                                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                txtTotal.setText(decimalFormat.format(tongtien)+" đ");
                            }
                        }
                    });
                    dialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Giỏ hàng chưa có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
