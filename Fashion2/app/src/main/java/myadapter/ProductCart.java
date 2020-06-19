package myadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fashion.GioHang;
import com.example.fashion.MainActivity;
import com.example.fashion.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Cart;

//public class ProductCart extends ArrayAdapter<String> {
//    private final Activity context;
//    private final String[] maintitle;
//    private final String[] subtitle;
//    private final Integer[] imgid;
//    private Button btvalues;
//    private Button btminus,btplus;
//
//    public ProductCart(Activity context,String[] maintitle,String[] subtitle,Integer[] imgid,Button btvalues){
//        super(context,R.layout.product_cart,maintitle);
//        this.context = context;
//        this.maintitle = maintitle;
//        this.subtitle = subtitle;
//        this.imgid = imgid;
//        this.btvalues = btvalues;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View row = inflater.inflate(R.layout.product_cart, null, true);
//        TextView title = (TextView) row.findViewById(R.id.tensp);
//        TextView sub_title = (TextView) row.findViewById(R.id.giasp);
//        ImageView img = (ImageView) row.findViewById(R.id.imgcart);
//        Button btnval = (Button) row.findViewById(R.id.btvalues);
//
//        title.setText(maintitle[position]);
//        sub_title.setText(subtitle[position]);
//        img.setImageResource(imgid[position]);
//
//
//        return row;
//    }
//}

    public class ProductCart extends BaseAdapter{
        Context context;
        ArrayList<Cart> arrayGiohang;

        public ProductCart(Context context, ArrayList<Cart> arrayGiohang) {
            this.context = context;
            this.arrayGiohang = arrayGiohang;
        }

        @Override
        public int getCount() {     //lấy hết các gtri trong mảng
            return arrayGiohang.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayGiohang.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder{            //gán ánh xạ nếu run lần 2
            public TextView txtnameCart,txtpriceCart;
            public ImageView imgCart;
            public Button btnminus,btnvalues,btnplus;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(view == null){               //run 1 nếu view = null
                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.product_cart,null);
                viewHolder.txtnameCart = view.findViewById(R.id.tensp);
                viewHolder.txtpriceCart = view.findViewById(R.id.giasp);
                viewHolder.imgCart = view.findViewById(R.id.imgcart);
                viewHolder.btnminus = view.findViewById(R.id.btminus);
                viewHolder.btnplus = view.findViewById(R.id.btplus);
                viewHolder.btnvalues = view.findViewById(R.id.btvalues);
                view.setTag(viewHolder);
            }else {                         //run 2
                viewHolder = (ViewHolder) view.getTag();
            }

            //lấy dl ra gán cho layout
            Cart cart = (Cart) getItem(position);
            viewHolder.txtnameCart.setText(cart.getTensp());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            viewHolder.txtpriceCart.setText(decimalFormat.format(cart.getGiasp()) + " đ");
            Picasso.get().load(arrayGiohang.get(position).getHinhsp()).into(viewHolder.imgCart);
            viewHolder.btnvalues.setText(cart.getSoluong() + "");           //cast về dạng chuỗi

            int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
            if(sl >= 10){
                viewHolder.btnplus.setVisibility(View.INVISIBLE);
                viewHolder.btnminus.setVisibility(View.VISIBLE);
            }else if(sl <= 1){
                viewHolder.btnplus.setVisibility(View.VISIBLE);
                viewHolder.btnminus.setVisibility(View.INVISIBLE);
            }else if(sl > 1){
                viewHolder.btnplus.setVisibility(View.VISIBLE);
                viewHolder.btnminus.setVisibility(View.VISIBLE);
            }

            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.btnvalues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int slmoi = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                    int slht = MainActivity.arrayCart.get(position).getSoluong();
                    long giaht = MainActivity.arrayCart.get(position).getGiasp();
                    MainActivity.arrayCart.get(position).setSoluong(slmoi);
                    long giamoi = (giaht * slmoi) / slht;
                    MainActivity.arrayCart.get(position).setGiasp(giamoi);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    finalViewHolder.txtpriceCart.setText(decimalFormat.format(giamoi) + " đ");
                    GioHang.LoadListProduct();
                    if(slmoi > 9){
                        finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                        finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                    }else {
                        finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                    }
                }
            });
            viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int slmoi = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                    int slht = MainActivity.arrayCart.get(position).getSoluong();
                    long giaht = MainActivity.arrayCart.get(position).getGiasp();
                    MainActivity.arrayCart.get(position).setSoluong(slmoi);
                    long giamoi = (giaht * slmoi) / slht;
                    MainActivity.arrayCart.get(position).setGiasp(giamoi);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    finalViewHolder.txtpriceCart.setText(decimalFormat.format(giamoi) + " đ");
                    GioHang.LoadListProduct();
                    if(slmoi < 2){
                        finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                        finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                    }else {
                        finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                    }
                }
            });
            return view;
        }
    }


