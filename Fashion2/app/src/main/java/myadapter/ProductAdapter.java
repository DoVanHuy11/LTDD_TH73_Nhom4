package myadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fashion.R;

public class ProductAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle1;
    private final Integer[] imgId;

    public ProductAdapter(Activity context, String[] maintitle, String[] subtitle, Integer[] imgId) {
        super(context, R.layout.product, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.subtitle1 = subtitle;
        this.imgId = imgId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.product, null , true);
        TextView title = (TextView) row.findViewById(R.id.product_title);
        TextView subtitle = (TextView) row.findViewById(R.id.product_subtitle);
        ImageView img = (ImageView) row.findViewById(R.id.product_img);

        title.setText(maintitle[position]);
        subtitle.setText(subtitle1[position]);
        img.setImageResource(imgId[position]);

        return row;
    }
}
