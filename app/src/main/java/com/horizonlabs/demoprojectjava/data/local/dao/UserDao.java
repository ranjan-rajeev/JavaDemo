package com.horizonlabs.demoprojectjava.data.local.dao;

import com.horizonlabs.demoprojectjava.model.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getUsersSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(UserEntity... userEntities);

    @Update
    void updateUser(UserEntity userEntity);

    @Delete
    void deleteUser(UserEntity userEntity);
}
