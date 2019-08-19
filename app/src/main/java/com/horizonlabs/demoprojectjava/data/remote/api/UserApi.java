package com.horizonlabs.demoprojectjava.data.remote.api;

import com.horizonlabs.demoprojectjava.model.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
public interface UserApi {

    @GET("/users")
    Call<List<UserEntity>> getUsers();

}
