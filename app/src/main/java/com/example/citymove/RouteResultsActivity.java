package com.example.citymove;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RouteResultsActivity extends AppCompatActivity {

    TextView journeySummary;
    TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_results);

        journeySummary = findViewById(R.id.journeySummary);
        backButton = findViewById(R.id.backButton);

        String startLocation = getIntent().getStringExtra("START_LOCATION");
        String endLocation = getIntent().getStringExtra("END_LOCATION");

        journeySummary.setText(startLocation + "  →  " + endLocation);

        backButton.setOnClickListener(v -> finish());

        Button viewRouteButton = findViewById(R.id.viewRouteButton);

        viewRouteButton.setOnClickListener(v -> {

            Intent intent = new Intent(RouteResultsActivity.this,
                    RouteDetailActivity.class);

            intent.putExtra("START_LOCATION",
                    getIntent().getStringExtra("START_LOCATION"));

            intent.putExtra("END_LOCATION",
                    getIntent().getStringExtra("END_LOCATION"));

            startActivity(intent);
        });

    }
}