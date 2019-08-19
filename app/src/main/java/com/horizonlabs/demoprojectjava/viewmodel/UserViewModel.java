package com.horizonlabs.demoprojectjava.viewmodel;

import android.app.Application;


import com.horizonlabs.demoprojectjava.data.remote.ApiResponse;
import com.horizonlabs.demoprojectjava.data.repository.UserRepository;
import com.horizonlabs.demoprojectjava.model.UserEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }


    public void insert(UserEntity... userEntities) {
        userRepository.insert(userEntities);
    }

    public LiveData<ApiResponse<List<UserEntity>>> getAllUser() {
        return userRepository.getAllUser();
    }


}
