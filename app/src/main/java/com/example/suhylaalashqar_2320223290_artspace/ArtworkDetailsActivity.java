package com.example.suhylaalashqar_2320223290_artspace;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        Intent artwork = getIntent();
        String title = artwork.getStringExtra("title");
        String artist = artwork.getStringExtra("artist");
        int year = artwork.getIntExtra("year", 0);

        TextView detailsText = findViewById(R.id.details_text);
        detailsText.setText(title + "\n" + artist + " (" + year + ")");

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        findViewById(R.id.visit_website).setOnClickListener(view -> {
            Uri url = Uri.parse("https://esawebb.org/images/");
            Intent website = new Intent(Intent.ACTION_VIEW, url);

            try {
                startActivity(website);
            } catch (ActivityNotFoundException error) {
                Toast.makeText(this, "No browser available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}