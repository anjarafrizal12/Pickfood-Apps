package id.co.sweetmusroom.pickfood;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailTransaksiActivity extends AppCompatActivity {

    public static ArrayList<ModelList> arrayList = new ArrayList<>();
    public static double totalHargaDetail;
    private String noMeja, nmPemesan;
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    TextView noMejaText;
    TextView nmPemesanText;
    public static TextView tv_totalHarga;
    EditText et_jumlahUang;

    private Button batalBtn, pesanBtn;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    private final String server = "http://192.168.43.160/pickfood/insert_data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        totalHargaDetail = 0;

        mRecyclerview = findViewById(R.id.recycleViewDetail);
        noMejaText = findViewById(R.id.noMejaDetail);
        nmPemesanText = findViewById(R.id.namaPemesanDetail);
        tv_totalHarga = findViewById(R.id.totalHarga);
        et_jumlahUang = findViewById(R.id.jumlahUang);

        for (int i = 0; i < arrayList.size(); i++) {
            totalHargaDetail = totalHargaDetail + (arrayList.get(i).getQty() * arrayList.get(i).getHarga());
        }


        batalBtn = findViewById(R.id.btnBatalDetail);
        pesanBtn = findViewById(R.id.btnPesanDetail);

        noMeja = this.getIntent().getStringExtra("nomeja");
        nmPemesan = this.getIntent().getStringExtra("nama");


        noMejaText.setText(noMeja);
        nmPemesanText.setText(nmPemesan);
        tv_totalHarga.setText(String.valueOf(totalHargaDetail));

        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new DetailList(DetailTransaksiActivity.this, arrayList);
        mRecyclerview.setAdapter(mAdapter);

        pesanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Konfirmasi Pesanan");

                // Ask the final question
                builder.setMessage("Apakah anda yakin untuk melakukan pemesanan?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        try {
                            cekHarga();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });

        batalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Batalkan Pesanan");

                // Ask the final question
                builder.setMessage("Apakah anda yakin untuk membatalkan pesanan?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        finish();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });

        setTotalHarga();


    }

    private void setTotalHarga() {

        tv_totalHarga.setText(String.valueOf("Rp " + totalHargaDetail));
    }

    public void pesan() throws JSONException {
        // Showing progress dialog at user registration time.
        final JSONArray data = new JSONArray();

        for (int i = 0; i < arrayList.size(); i++) {
            JSONObject object = new JSONObject();

            try{
                object.put("namamakanan", arrayList.get(i).getTitle());
                object.put("qtymakanan", arrayList.get(i).getQty());
                object.put("hargamakanan", arrayList.get(i).getHarga());
                data.put(object);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.

                        // Showing response message coming from server.
                        Toast.makeText(DetailTransaksiActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(DetailTransaksiActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                // Adding All values to Params.
                params.put("nama_pemesan", nmPemesan);
                params.put("nomeja", noMeja);
                params.put("totalHarga", String.valueOf(totalHargaDetail));
                params.put("jumlahBayar", et_jumlahUang.getText().toString());
                params.put("list", String.valueOf(data));

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(DetailTransaksiActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

    private void cekHarga() throws JSONException {
        double bayar;

        if (et_jumlahUang.getText().toString().equals("")) {
            bayar = 0;
        } else {
            bayar = Double.parseDouble(et_jumlahUang.getText().toString());
        }

        if (bayar < totalHargaDetail) {
            Toast.makeText(DetailTransaksiActivity.this, "Maaf uang yang anda masukan tidak mencukup", Toast.LENGTH_SHORT).show();
        } else {
            pesan();
        }

    }

    public void onBackPressed(){
        // Build an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);

        // Set a title for alert dialog
        builder.setTitle("Batalkan Pesanan");

        // Ask the final question
        builder.setMessage("Apakah anda yakin untuk membatalkan pesanan?");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
}
