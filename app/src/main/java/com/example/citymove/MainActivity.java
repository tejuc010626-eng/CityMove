
package com.example.citymove;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText fromInput, toInput;
    Button searchButton, accessibilityButton;

    TextView viewAllButton;
    TextView navHome, navExplore, navSaved, navProfile;

    View metroCard, busCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect UI components
        fromInput = findViewById(R.id.fromInput);
        toInput = findViewById(R.id.toInput);

        searchButton = findViewById(R.id.searchButton);
        accessibilityButton = findViewById(R.id.accessibilityButton);

        viewAllButton = findViewById(R.id.viewAllButton);

        metroCard = findViewById(R.id.metroCard);
        busCard = findViewById(R.id.busCard);

        navHome = findViewById(R.id.navHome);
        navExplore = findViewById(R.id.navExplore);
        navSaved = findViewById(R.id.navSaved);
        navProfile = findViewById(R.id.navProfile);


        // Search journey
        searchButton.setOnClickListener(v -> {

            String startingPoint = fromInput.getText().toString().trim();
            String destination = toInput.getText().toString().trim();

            if (startingPoint.isEmpty()) {
                fromInput.setError("Enter your starting point");
                fromInput.requestFocus();
                return;
            }

            if (destination.isEmpty()) {
                toInput.setError("Enter your destination");
                toInput.requestFocus();
                return;
            }

            Intent intent = new Intent(MainActivity.this, RouteResultsActivity.class);

            intent.putExtra("START_LOCATION", startingPoint);
            intent.putExtra("END_LOCATION", destination);

            startActivity(intent);

        });

        Button savedRoutesButton = findViewById(R.id.savedRoutesButton);

        savedRoutesButton.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,
                    SavedRoutesActivity.class);

            startActivity(intent);
        });


        // Metro section
        metroCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MetroActivity.class);
            startActivity(intent);
        });

        // Bus section
        busCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BusActivity.class);
            startActivity(intent);
        });


        // View all transport
        viewAllButton.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    "Explore all transport options",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // Accessibility
        accessibilityButton.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    "Accessibility information coming soon",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // Bottom navigation
        navHome.setOnClickListener(v -> showMessage("Home"));

        navExplore.setOnClickListener(v -> showMessage("Explore"));

        navSaved.setOnClickListener(v -> showMessage("Saved routes"));

        navProfile.setOnClickListener(v -> showMessage("Profile"));

    }


    private void showMessage(String page) {

        Toast.makeText(
                this,
                page + " section coming soon",
                Toast.LENGTH_SHORT
        ).show();

    }

}
