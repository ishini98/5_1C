package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener {

    private RecyclerView topStoriesRecyclerView;
    private RecyclerView newsRecyclerView;
    private TopStoriesAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topStoriesRecyclerView = findViewById(R.id.recycler_top_stories);
        newsRecyclerView = findViewById(R.id.recycler_news);

        // Check if the container for two-pane layout is present in the layout
        isTwoPane = findViewById(R.id.container) != null;

        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<NewsStory> topStories = createSampleTopStories();
        ArrayList<NewsStory> newsStories = createSampleNewsStories();

        topStoriesAdapter = new TopStoriesAdapter(topStories);
        newsAdapter = new NewsAdapter(newsStories, this);

        topStoriesRecyclerView.setAdapter(topStoriesAdapter);
        newsRecyclerView.setAdapter(newsAdapter);

        if (isTwoPane) {
            // For two-pane layout, show the first item's details initially
            onItemClick(newsStories.get(0));
        }
    }

    private ArrayList<NewsStory> createSampleTopStories() {
        ArrayList<NewsStory> topStories = new ArrayList<>();
        topStories.add(new NewsStory(1, "MI vs KKR Live Score", "IPL 2024: Out of form Mumbai Indians takes on Kolkata Knight Riders; Toss at 7PM IST, predicted lineups.\n", R.drawable.mi, new ArrayList<>()));
        topStories.add(new NewsStory(2, "MI vs KKR Toss Updates", "IPL 2024: Mumbai Indians or Kolkata Knight Riders - Who will win coin flip today?\n" +
                "\n", R.drawable.mii, new ArrayList<>()));
        topStories.add(new NewsStory(3, "SRH vs RR IPL 2024 Match Highlights in Pictures", "Nitish, Bhuvneshwar shine as Hyderabad win a last ball thriller\n" +
                "\n", R.drawable.rr, new ArrayList<>()));

        return topStories;
    }

    private ArrayList<NewsStory> createSampleNewsStories() {
        ArrayList<NewsStory> newsStories = new ArrayList<>();
        newsStories.add(new NewsStory(4, "MI vs KKR Dream11 Prediction", "IPL 2024: Mumbai Indians vs Kolkata Knight Riders predicted XI, fantasy team, squads.", R.drawable.ind, new ArrayList<>()));
        newsStories.add(new NewsStory(5, "IPL 2024:", "Glad people are taking my name for the right reasons,’ says Riyan Parag after SRH vs RR.\n" +
                "\n" +
                "IPL 2024: Riyan Parag reflected on Rajasthan Royals’ one-run loss to Sunrisers Hyderabad, his knock and overall form in the ongoing tournament.", R.drawable.r, new ArrayList<>()));
        newsStories.add(new NewsStory(6, "IPL 2024 Orange Cap standings updated", "SRH vs RR: Riyan Parag enters top-five; Gaikwad tops table. ", R.drawable.o, new ArrayList<>()));
        newsStories.add(new NewsStory(7, "Indian players performed since selection in T20 World Cup squad?", "The Indian team for T20 World Cup, set to be held in the West Indies and USA in June, was announced on Tuesday.\n" +
                "\n" +
                "he Indian team for T20 World Cup, set to be held in the West Indies and USA in June, was announced on Tuesday and since then, 10 out of 15 players have featured in the ongoing Indian Premier League (IPL) 2024 for their respective matches.\n" +
                "\n", R.drawable.in, new ArrayList<>()));

        return newsStories;
    }

    @Override
    public void onItemClick(NewsStory newsStory) {
        Log.d("MainActivity", "Item Clicked: " + newsStory.getTitle());

        if (isTwoPane) {
            // For two-pane layout, replace the detail fragment in the container
            NewsDetailFragment detailFragment = NewsDetailFragment.newInstance(newsStory.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .commit();
        } else {
            // For single-pane layout, start a new activity to show the details
            startActivity(NewsDetailActivity.createIntent(this, newsStory.getId()));
        }
    }
}
