package com.example.citymove;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BusActivity extends AppCompatActivity {

    TextView busBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        busBackButton = findViewById(R.id.busBackButton);

        busBackButton.setOnClickListener(v -> {
            finish();
        });
    }
}