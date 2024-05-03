package com.example.itubeapp3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PlaylistDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PLAYLIST = "playlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VIDEO_URL = "video_url";

    public PlaylistDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_PLAYLIST + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VIDEO_URL + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST);
        onCreate(db);
    }

    public void addToPlaylist(String videoUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_URL, videoUrl);
        db.insert(TABLE_PLAYLIST, null, values);
        db.close();
    }

    public List<String> getAllVideos() {
        List<String> videoUrls = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PLAYLIST, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String videoUrl = cursor.getString(cursor.getColumnIndex(COLUMN_VIDEO_URL));
                videoUrls.add(videoUrl);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return videoUrls;
    }
}
