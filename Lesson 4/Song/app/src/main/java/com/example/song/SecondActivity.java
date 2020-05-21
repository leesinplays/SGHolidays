package com.example.song;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button showAll;
    ListView lvSongs;
    ArrayAdapter songAdapter;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent receiveIntent = new Intent();

        lvSongs = (ListView) this.findViewById(R.id.lvSongs);
        showAll = (Button) this.findViewById(R.id.btnShowAll);

        DBHelper dbh = new DBHelper(this);
        songs = dbh.getAllSongs();

        songAdapter = new SongsArrayAdapter(this, R.layout.row, songs);
        lvSongs.setAdapter(songAdapter);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                songs = dbh.getAllSongsByStars(5);
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = songs.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("song", song);
                startActivityForResult(i, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 9 && resultCode == RESULT_OK) {
            DBHelper dbh = new DBHelper(SecondActivity.this);
            songs.clear();
            songs.addAll(dbh.getAllSongs());
            songAdapter.notifyDataSetChanged();
            dbh.close();
        }
    }
}
