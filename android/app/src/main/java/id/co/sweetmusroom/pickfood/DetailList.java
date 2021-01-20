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

public class DetailList extends RecyclerView.Adapter<DetailViewHolder> {

    private static Activity activity;
    private static ArrayList<ModelList> arrayList;
    private static ArrayList<ModelList> arrayListCopy;
    private static ArrayList<ModelList> items;
    private static Context mContext;
    private double qty;
    public static String nomorMeja,nmPemesan;
    private static double totalHarga = 0;

    public DetailList(Activity activity, ArrayList<ModelList> items){
        this.activity = activity;
        this.items = items;
        this.arrayList = items;
        this.arrayListCopy = new ArrayList<ModelList>();

        this.mContext = activity;
        this.totalHarga = 0;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows_detail,parent,false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder holder, final int position) {

        holder.tv_no.setText(String.valueOf(position+1));
        holder.tv_namaMakanan.setText(items.get(position).getTitle());
        holder.tv_qtyDetail.setText(String.valueOf(items.get(position).getQty()));
        holder.tv_hargaDetail.setText(String.valueOf(items.get(position).getHarga()));
        holder.et_totalDetail.setText(String.valueOf(items.get(position).getHarga() * items.get(position).getQty()));

        totalHarga = totalHarga + (items.get(position).getHarga() * items.get(position).getQty());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}