package com.example.ptsganjil202111rpl1samuel29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {

    int id;
    ImageView ivPoster;
    TextView tvOriginalTitle, tvOverview, tvReleaseDate;
    String poster_path, originalTitle, overview, releaseDate;
    Bundle bundle;
    Realm realm;
    ActionBar actionBar;
    RealmHelper realmHelper;
    Button favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvReleaseDate = findViewById(R.id.tv_realese_date);
        ivPoster = findViewById(R.id.iv_poster);
        favorite = findViewById(R.id.favoriteBtn);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();

        if(bundle != null){
            id = bundle.getInt("id");
            originalTitle = bundle.getString("original_title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            poster_path = bundle.getString("poster_path");
            getSupportActionBar().setTitle(originalTitle);
        }

        tvOriginalTitle.setText(originalTitle);
        tvReleaseDate.setText(releaseDate);
        tvOverview.setText(overview);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/".concat(poster_path))
                .into(ivPoster);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    MovieModel model = new MovieModel(id,originalTitle,overview,releaseDate,poster_path);
                    realmHelper.save(model);
                    Toast.makeText(DetailActivity.this, "Added To Favorite", Toast.LENGTH_SHORT).show();
            }
        });
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