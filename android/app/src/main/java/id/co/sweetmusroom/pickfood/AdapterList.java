package id.co.sweetmusroom.pickfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterList extends RecyclerView.Adapter<MainViewHolder> {

    private static Activity activity;
    private static ArrayList<ModelList> arrayList;
    private static ArrayList<ModelList> arrayListCopy;
    private static ArrayList<ModelList> items;
    private static Context mContext;
    private double qty;
    public static String nomorMeja, nmPemesan;

    public AdapterList(Activity activity, ArrayList<ModelList> items) {
        this.activity = activity;
        this.items = items;
        this.arrayList = items;
        this.arrayListCopy = new ArrayList<ModelList>();

        this.mContext = activity;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {

        holder.tv_id.setText(items.get(position).getId());
        holder.tv_title.setText(items.get(position).getTitle());
        holder.tv_desc.setText(items.get(position).getDesc());
        holder.tv_harga.setText("Rp " + (int) items.get(position).getHarga());

        holder.et_qty.setHint("0");

        holder.et_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (holder.et_qty.getText().toString().equals("")) {
                    arrayList.get(position).setQty(0.0);
                } else {
                    arrayList.get(position).setQty(Double.parseDouble(holder.et_qty.getText().toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Picasso.with(mContext)
                .load(items.get(position).getImg())
                .into(holder.iv_img);
    }

    public static void cekQty() {
        MainViewHolder holder;

        arrayListCopy = new ArrayList<ModelList>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getQty() != 0.0) {
                arrayListCopy.add(arrayList.get(i));
            }
        }

        if (nmPemesan.equals("")) {
            Toast.makeText(mContext, "Pastikan anda mengisikan nama pemesan", Toast.LENGTH_LONG).show();
            PemesananActivity.statPemesanan = false;
        } else {

            if (arrayListCopy.size() < 1) {
                Toast.makeText(mContext, "Pastikan anda memilih menu makanan", Toast.LENGTH_LONG).show();
                PemesananActivity.statPemesanan = false;
            } else {
                Intent i = new Intent(mContext, DetailTransaksiActivity.class);
                i.putExtra("nomeja", nomorMeja);
                i.putExtra("nama", nmPemesan);
                DetailTransaksiActivity.arrayList.clear();
                DetailTransaksiActivity.arrayList = arrayListCopy;
                mContext.startActivity(i);

                PemesananActivity.statPemesanan = true;

            }
        }


    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}