package com.example.citymove;

import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

public class RouteDetailActivity extends AppCompatActivity {

    TextView detailBackButton;
    TextView detailJourneySummary;
    TextView detailStartLocation;
    TextView detailEndLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        detailBackButton = findViewById(R.id.detailBackButton);
        detailJourneySummary = findViewById(R.id.detailJourneySummary);
        detailStartLocation = findViewById(R.id.detailStartLocation);
        detailEndLocation = findViewById(R.id.detailEndLocation);

        String startLocation = getIntent().getStringExtra("START_LOCATION");
        String endLocation = getIntent().getStringExtra("END_LOCATION");

        if (startLocation == null) {
            startLocation = "Starting point";
        }

        if (endLocation == null) {
            endLocation = "Destination";
        }

        detailJourneySummary.setText(startLocation + " → " + endLocation);
        detailStartLocation.setText(startLocation);
        detailEndLocation.setText(endLocation);

        detailBackButton.setOnClickListener(v -> finish());
        TextView saveRouteButton = findViewById(R.id.saveRouteButton);

        SharedPreferences preferences =
                getSharedPreferences("CityMoveRoutes", MODE_PRIVATE);

        String routeKey = startLocation + " → " + endLocation;

        boolean isSaved = preferences.getBoolean(routeKey, false);

        if (isSaved) {
            saveRouteButton.setText("✓ Route Saved");
        }

        saveRouteButton.setOnClickListener(v -> {

            boolean currentlySaved = preferences.getBoolean(routeKey, false);

            if (currentlySaved) {
                preferences.edit().putBoolean(routeKey, false).apply();
                saveRouteButton.setText("Save Route");
            } else {
                preferences.edit().putBoolean(routeKey, true).apply();
                saveRouteButton.setText("✓ Route Saved");
            }
        });
    }
}