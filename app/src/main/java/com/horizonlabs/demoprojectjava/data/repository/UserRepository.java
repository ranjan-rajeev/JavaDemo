package com.horizonlabs.demoprojectjava.data.repository;

import android.app.Application;
import android.os.AsyncTask;


import com.horizonlabs.demoprojectjava.data.local.LocalDatabase;
import com.horizonlabs.demoprojectjava.data.local.dao.UserDao;
import com.horizonlabs.demoprojectjava.data.remote.ApiResponse;
import com.horizonlabs.demoprojectjava.data.remote.RetrofitClent;
import com.horizonlabs.demoprojectjava.data.remote.api.UserApi;
import com.horizonlabs.demoprojectjava.model.UserEntity;
import com.horizonlabs.demoprojectjava.utility.Logger;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class UserRepository {

    private UserApi userApi;
    private UserDao userDao;

    private MutableLiveData<ApiResponse<List<UserEntity>>> allUser;

    public UserRepository(Application application) {
        userDao = LocalDatabase.getInstance(application).userDao();
        userApi = RetrofitClent.getInstance().create(UserApi.class);
        allUser = new MutableLiveData<>();
    }

    public void insert(UserEntity... userEntities) {
        new InsertUserAsyncTask(userDao).execute(userEntities);
    }

    public void update(UserEntity userEntity) {
        new UpdateUserAsyncTask(userDao).execute(userEntity);
    }

    public LiveData<ApiResponse<List<UserEntity>>> getAllUser() {
        if (allUser.getValue().data != null && allUser.getValue().data.size() > 0) {
            getUsersFromServer();
        }
        return allUser;
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.saveUser(userEntities);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.updateUser(userEntities[0]);
            return null;
        }
    }

    public LiveData<ApiResponse<List<UserEntity>>> getUsersFromServer() {
        allUser.postValue(new ApiResponse<>(ApiResponse.ApiStatus.LOADING, null, "Fetching data from server..."));

        userApi.getUsers().enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if (response.isSuccessful()) {
                    Logger.d("list fetch successful");
                    insert(response.body().toArray(new UserEntity[response.body().size()]));
                    allUser.postValue(new ApiResponse<>(ApiResponse.ApiStatus.SUCCESS, response.body(), "Data Fetch successF"));
                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                // TODO: 20-07-2019 HAndle failure cases
                Logger.d("" + t.getMessage());
            }
        });

        return allUser;
    }

    private static class FetchUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao userDao;

        private FetchUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.getUsersSync();
            return null;
        }
    }
}
