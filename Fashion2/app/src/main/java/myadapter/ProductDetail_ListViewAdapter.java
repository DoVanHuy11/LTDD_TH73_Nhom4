package myadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fashion.R;

public class ProductDetail_ListViewAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;

    public ProductDetail_ListViewAdapter(Activity context, String[] maintitle, String[] subtitle,Integer[] imgid){
        super(context, R.layout.product2, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.product2, null, true);
        TextView title = (TextView) row.findViewById(R.id.productdetail_title);
        TextView sub_title = (TextView) row.findViewById(R.id.productdetail_subtitle);
        ImageView img = (ImageView) row.findViewById(R.id.productdetail_img);

        title.setText(maintitle[position]);
        sub_title.setText(subtitle[position]);
        img.setImageResource(imgid[position]);

        return row;
    }
}
