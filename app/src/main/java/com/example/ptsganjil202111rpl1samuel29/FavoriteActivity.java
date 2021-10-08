package com.example.ptsganjil202111rpl1samuel29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter adapter;
    List<MovieModel> movielist;
    Bundle bundle;
    ActionBar actionBar;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Favorite Movies");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.movies);

        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));

        movielist = new ArrayList<>();

        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        movielist = new ArrayList<>();

        movielist = realmHelper.getAllMovies();

        adapter = new MovieAdapter(movielist, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}