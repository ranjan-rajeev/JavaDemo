package com.horizonlabs.demoprojectjava.data.local;


import android.content.Context;

import com.horizonlabs.demoprojectjava.data.local.dao.UserDao;
import com.horizonlabs.demoprojectjava.model.UserEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase localDatabase;

    public abstract UserDao userDao();

    public static synchronized LocalDatabase getInstance(Context context) {
        if (localDatabase == null) {
            localDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, context.getApplicationInfo().name + "_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return localDatabase;
    }
}
