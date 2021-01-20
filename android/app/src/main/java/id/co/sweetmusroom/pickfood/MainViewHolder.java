package id.co.sweetmusroom.pickfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_id;
    public TextView tv_title;
    public TextView tv_desc;
    public TextView tv_harga;
    public EditText et_qty;
    public ImageView iv_img;

    public MainViewHolder(View itemView) {
        super(itemView);
        tv_id       = itemView.findViewById(R.id.tv_id);
        tv_title    = itemView.findViewById(R.id.tv_title);
        tv_desc     = itemView.findViewById(R.id.tv_description);
        tv_harga    = itemView.findViewById(R.id.tv_harga);
        et_qty      = itemView.findViewById(R.id.qty);
        iv_img      = itemView.findViewById(R.id.imageCover);
    }
}