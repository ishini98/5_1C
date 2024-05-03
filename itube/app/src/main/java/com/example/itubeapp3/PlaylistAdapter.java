package com.example.itubeapp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private List<String> playlist;
    private Context context;

    public PlaylistAdapter(Context context, List<String> playlist) {
        this.context = context;
        this.playlist = playlist;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_playlist, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        String videoUrl = playlist.get(position);
        holder.videoUrlTextView.setText(videoUrl);
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView videoUrlTextView;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            videoUrlTextView = itemView.findViewById(R.id.videoUrlTextView);
        }
    }
}
