package com.example.citymove;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SavedRoutesActivity extends AppCompatActivity {

    LinearLayout savedRoutesContainer;
    TextView emptyStateText;
    TextView savedBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_routes);

        savedRoutesContainer = findViewById(R.id.savedRoutesContainer);
        emptyStateText = findViewById(R.id.emptyStateText);
        savedBackButton = findViewById(R.id.savedBackButton);

        savedBackButton.setOnClickListener(v -> finish());

        loadSavedRoutes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSavedRoutes();
    }

    private void loadSavedRoutes() {

        savedRoutesContainer.removeAllViews();

        SharedPreferences preferences =
                getSharedPreferences("CityMoveRoutes", MODE_PRIVATE);

        Map<String, ?> savedRoutes = preferences.getAll();

        int count = 0;

        for (Map.Entry<String, ?> entry : savedRoutes.entrySet()) {

            if (Boolean.TRUE.equals(entry.getValue())) {

                String route = entry.getKey();

                String[] locations = route.split(" → ", 2);

                if (locations.length != 2) {
                    continue;
                }

                String start = locations[0];
                String destination = locations[1];

                addRouteCard(start, destination);
                count++;
            }
        }

        if (count == 0) {
            emptyStateText.setVisibility(TextView.VISIBLE);
        } else {
            emptyStateText.setVisibility(TextView.GONE);
        }
    }

    private void addRouteCard(String start, String destination) {

        LinearLayout card = new LinearLayout(this);

        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundResource(R.drawable.card_background);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        cardParams.setMargins(0, 0, 0, 16);

        card.setLayoutParams(cardParams);

        TextView routeTitle = new TextView(this);

        routeTitle.setText(start + "  →  " + destination);
        routeTitle.setTextSize(17);
        routeTitle.setTextColor(0xFF1D2922);
        routeTitle.setTypeface(null, android.graphics.Typeface.BOLD);

        card.addView(routeTitle);

        TextView routeSubtitle = new TextView(this);

        routeSubtitle.setText("Tap to view journey details");
        routeSubtitle.setTextSize(13);
        routeSubtitle.setTextColor(0xFF777777);
        routeSubtitle.setPadding(0, 12, 0, 0);

        card.addView(routeSubtitle);

        card.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SavedRoutesActivity.this,
                    RouteDetailActivity.class);

            intent.putExtra("START_LOCATION", start);
            intent.putExtra("END_LOCATION", destination);

            startActivity(intent);
        });

        savedRoutesContainer.addView(card);
    }
}