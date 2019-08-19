package com.horizonlabs.demoprojectjava.data.remote;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
public class ApiResponse<T> {

    @NonNull
    public final ApiStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public ApiResponse(@NonNull ApiStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(@Nullable T data) {
        return new ApiResponse<>(ApiStatus.SUCCESS, data, null);
    }

    public static <T> ApiResponse<T> failure(@NonNull String msg, @Nullable T data) {
        return new ApiResponse<>(ApiStatus.FAILURE, data, msg);
    }

    public static <T> ApiResponse<T> loading(@Nullable T data) {
        return new ApiResponse<>(ApiStatus.LOADING, data, null);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(ApiStatus.ERROR, null, null);
    }

    public enum ApiStatus {SUCCESS, FAILURE, LOADING, ERROR}

}