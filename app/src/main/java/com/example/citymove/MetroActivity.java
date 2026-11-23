package com.example.citymove;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MetroActivity extends AppCompatActivity {

    TextView metroBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        metroBackButton = findViewById(R.id.metroBackButton);

        metroBackButton.setOnClickListener(v -> {
            finish();
        });
    }
}