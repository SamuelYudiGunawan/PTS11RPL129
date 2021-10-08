package com.example.ptsganjil202111rpl1samuel29;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmInit extends Application {

        @Override
        public void onCreate() {
            super.onCreate();

            Realm.init(this);
            RealmConfiguration configuration = new RealmConfiguration.Builder()
                    .allowWritesOnUiThread(true)
                    .allowQueriesOnUiThread(true)
                    .name("movie.db")
                    .schemaVersion(0)
                    .build();
            Realm.setDefaultConfiguration(configuration);
        }
}
