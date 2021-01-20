package id.co.sweetmusroom.pickfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DetailViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_no;
    public TextView tv_namaMakanan;
    public TextView tv_qtyDetail;
    public TextView tv_hargaDetail;
    public TextView et_totalDetail;

    public DetailViewHolder(View itemView) {
        super(itemView);
        tv_no = itemView.findViewById(R.id.tv_no);
        tv_namaMakanan = itemView.findViewById(R.id.tv_namaMakanan);
        tv_qtyDetail = itemView.findViewById(R.id.tv_qtyDetail);
        tv_hargaDetail = itemView.findViewById(R.id.tv_hargaDetail);
        et_totalDetail = itemView.findViewById(R.id.tv_totalDetail);
    }
}
