package com.example.itubeapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PlaylistDBHelper playlistDBHelper = new PlaylistDBHelper(this);

        urlEditText = findViewById(R.id.urlEditText);
        urlEditText.setTextIsSelectable(true); // Enable text selection
        urlEditText.setLongClickable(false); // Disable long press (copy-paste) by default

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = urlEditText.getText().toString().trim();
                if (isValidYouTubeUrl(videoUrl)) {
                    Intent intent = new Intent(HomeActivity.this, VideoPlayerActivity.class);
                    intent.putExtra("videoUrl", videoUrl);
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = urlEditText.getText().toString().trim();
                if (isValidYouTubeUrl(videoUrl)) {
                    playlistDBHelper.addToPlaylist(videoUrl);
                    Toast.makeText(HomeActivity.this, "Added to Playlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button myPlaylistButton = findViewById(R.id.myPlaylistButton);
        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidYouTubeUrl(String url) {
        return url.startsWith("https://www.youtube.com/");
    }
}