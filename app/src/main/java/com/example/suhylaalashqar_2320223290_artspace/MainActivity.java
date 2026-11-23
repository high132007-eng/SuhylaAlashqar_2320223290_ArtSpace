package com.example.suhylaalashqar_2320223290_artspace;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final int[] images = {
            R.drawable.art_1, R.drawable.art_2, R.drawable.art_3,
            R.drawable.art_4, R.drawable.art_5, R.drawable.art_6
    };

    private final String[] titles = {
            "Webb's First Deep Field (NIRCam)",
            "Pillars of Creation",
            "Horsehead Nebula",
            "Cosmic Cliffs",
            "Sombrero Galaxy",
            "Hubble NGC 4449"
    };

    private final String[] artists = {
            "NASA, ESA, CSA, and STScI",
            "NASA, ESA/Hubble and the Hubble Heritage Team",
            "NASA, ESA & Hubble Heritage Team (AURA/STScI)",
            "NASA, ESA, CSA, and STScI",
            "NASA, ESA, CSA, STScI",
            "NASA, ESA, A. Aloisi (STScI/ESA), and The Hubble Heritage "
                    + "(STScI/AURA)-ESA/Hubble Collaboration"
    };

    private final int[] years = {2022, 2011, 2013, 2022, 2024, 2007};

    private int currentIndex = 0;
    private ImageView artworkImage;
    private TextView counterView;
    private TextView titleView;
    private TextView artistView;
    private Button previousButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        artworkImage = findViewById(R.id.artwork_image);
        counterView = findViewById(R.id.art_counter);
        titleView = findViewById(R.id.art_title);
        artistView = findViewById(R.id.art_artist);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        artworkImage.setOnClickListener(view -> {
            Intent intent = new Intent(this, ArtworkDetailsActivity.class);
            intent.putExtra("title", titles[currentIndex]);
            intent.putExtra("artist", artists[currentIndex]);
            intent.putExtra("year", years[currentIndex]);
            startActivity(intent);
        });

        previousButton.setOnClickListener(view -> {
            if (currentIndex > 0) {
                currentIndex--;
                showArtwork();
            }
        });

        nextButton.setOnClickListener(view -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                showArtwork();
            }
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("current_index", 0);
        }

        showArtwork();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current_index", currentIndex);
    }

    private void showArtwork() {
        artworkImage.setImageResource(images[currentIndex]);
        artworkImage.setContentDescription(titles[currentIndex]);

        counterView.setText((currentIndex + 1) + " / " + images.length);
        titleView.setText(titles[currentIndex]);
        artistView.setText(artists[currentIndex] + " (" + years[currentIndex] + ")");

        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < images.length - 1);
    }
}