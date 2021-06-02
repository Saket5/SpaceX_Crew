package com.saket.spacex.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.saket.spacex.Dao.CrewDao;
import com.saket.spacex.model.Crew;

import org.jetbrains.annotations.NotNull;

@Database(entities = Crew.class,exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CrewDao crewDao();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "CrewDatabase")
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
    static  class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private CrewDao crewDao;

        PopulateAsyncTask(AppDatabase appDatabase)
        {
            crewDao=appDatabase.crewDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            crewDao.deleteTable();
            return null;
        }
    }
}
