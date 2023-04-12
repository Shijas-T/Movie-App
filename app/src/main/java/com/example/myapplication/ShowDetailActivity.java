package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView textViewShowName, textViewShowLanguage, textViewShowPremiered,textViewShowSummary;
    private ImageView imageViewShow;

    private RecyclerView recyclerViewCast;
    private ArrayList<CastModel> arrayListAllCast;
    private CastAdapter castAdapter;

    ShowModel showModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        textViewShowName = findViewById(R.id.tv_show_name);
        textViewShowLanguage = findViewById(R.id.tv_language);
        textViewShowPremiered = findViewById(R.id.tv_premiered);
        textViewShowSummary = findViewById(R.id.tv_summary);
        imageViewShow = findViewById(R.id.ic_show_image);

        showModel = (ShowModel) getIntent().getSerializableExtra("showModel");

        Intent intent=getIntent();
        String showId = intent.getStringExtra("show_id");
        String showLanguage = intent.getStringExtra("show_language");
        String showPremiered = intent.getStringExtra("show_premiered");
        String showSummary = intent.getStringExtra("show_summary");
        String showImgUrl = intent.getStringExtra("show_img_url");

//        textViewShowName.setText(showName);
        textViewShowLanguage.setText(showLanguage);
        textViewShowPremiered.setText(showPremiered);
        textViewShowSummary.setText(showSummary);

        Picasso.with(this).load(showImgUrl).into(imageViewShow);

        recyclerViewCast = findViewById(R.id.recyclerview_cast);
        arrayListAllCast = new ArrayList<>();

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,"https://api.tvmaze.com/shows/"+showId+"/cast", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("--->",response+"");
                        try {
                            for (int i = 0; i < response.length(); i++){

                                JSONObject info = response.getJSONObject(i);

                                arrayListAllCast.add(new CastModel(
                                        info.getJSONObject("person").getString("name"),
                                        info.getJSONObject("character").getJSONObject("image").getString("medium")
                                ));
                            }
                            if(response.length()>0){
                                castAdapter = new CastAdapter(arrayListAllCast, ShowDetailActivity.this);
                                recyclerViewCast.setHasFixedSize(true);
                                recyclerViewCast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                                recyclerViewCast.setAdapter(castAdapter);
                                castAdapter.notifyDataSetChanged();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "NO DATA FOUND", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);


    }
}