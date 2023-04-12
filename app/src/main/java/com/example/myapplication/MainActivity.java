package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaration
    private RecyclerView recyclerViewShows;
    private ArrayList<ShowModel> arrayListAllShows;
    private ShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewShows = findViewById(R.id.recyclerview_shows);
        arrayListAllShows = new ArrayList<>();

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,"https://api.tvmaze.com/shows", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++){

                                JSONObject info = response.getJSONObject(i);

                                arrayListAllShows.add(new ShowModel(
                                        info.getString("name"),
                                        info.getString("language"),
                                        info.getString("premiered"),
                                        info.getString("summary"),
                                        info.getJSONObject("image").getString("medium"),
                                        info.getString("id")
                                ));
                            }
                            if(response.length()>0){
                                showAdapter = new ShowAdapter(arrayListAllShows, MainActivity.this);
                                recyclerViewShows.setHasFixedSize(true);
                                recyclerViewShows.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                                recyclerViewShows.setAdapter(showAdapter);
                                showAdapter.notifyDataSetChanged();
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