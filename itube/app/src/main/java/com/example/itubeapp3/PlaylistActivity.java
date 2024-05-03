package com.example.itubeapp3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaylistAdapter playlistAdapter;
    private PlaylistDBHelper playlistDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        recyclerView = findViewById(R.id.playlistRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playlistDBHelper = new PlaylistDBHelper(this);
        List<String> playlist = playlistDBHelper.getAllVideos();

        playlistAdapter = new PlaylistAdapter(this, playlist);
        recyclerView.setAdapter(playlistAdapter);
    }
}
