package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView textViewShowName, textViewShowLanguage, textViewShowPremiered,textViewShowSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        textViewShowName = findViewById(R.id.tv_show_name);
        textViewShowLanguage = findViewById(R.id.tv_language);
        textViewShowPremiered = findViewById(R.id.tv_premiered);
        textViewShowSummary = findViewById(R.id.tv_summary);

        Intent intent=getIntent();
        String showName = intent.getStringExtra("show_name");
        String showLanguage = intent.getStringExtra("show_language");
        String showPremiered = intent.getStringExtra("show_premiered");
        String showSummary = intent.getStringExtra("show_summary");

//        textViewShowName.setText(showName);
        textViewShowLanguage.setText(showLanguage);
        textViewShowPremiered.setText(showPremiered);
        textViewShowSummary.setText(showSummary);

    }
}