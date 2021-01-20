package id.co.sweetmusroom.pickfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PemesananActivity extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    RequestQueue mRequest;
    ArrayList<ModelList> mList;
    TextView noMeja;
    String nomj;
    EditText nmPemesan;
    Button btnPesan;
    String namaPemesan;
    public static boolean statPemesanan = false;

    private final String server = "http://192.168.43.160/pickfood/load_data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        nomj = this.getIntent().getStringExtra("nomeja");

        mRecyclerview = findViewById(R.id.recycleView1);
        mRequest = Volley.newRequestQueue(getApplicationContext());
        mList = new ArrayList<>();

        noMeja      = findViewById(R.id.noMeja);
        nmPemesan   = findViewById(R.id.nmPemesan);

        btnPesan = findViewById(R.id.btnPesan);

        noMeja.setText(nomj);

        getData();

        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterList(PemesananActivity.this, mList);
        mRecyclerview.setAdapter(mAdapter);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namaPemesan = nmPemesan.getText().toString();

                AdapterList.nomorMeja = noMeja.getText().toString();
                AdapterList.nmPemesan = namaPemesan;
                AdapterList.cekQty();

                if (statPemesanan){
                    finish();
                }

            }
        });
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ModelList model = new ModelList();
                        model.setId(jsonObject.getString("id_makanan"));
                        model.setTitle(jsonObject.getString("judul_makanan"));
                        model.setDesc(jsonObject.getString("detail_makanan"));
                        model.setHarga(Double.parseDouble(jsonObject.getString("harga_makanan")));
                        model.setImg(jsonObject.getString("gambar_makanan"));
                        model.setQty(0);

                        mList.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void onBackPressed(){
        finish();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}

