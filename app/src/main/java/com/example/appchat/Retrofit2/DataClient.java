package com.example.appchat.Retrofit2;

import com.example.appchat.Models.Message;
import com.example.appchat.Models.NguoiDung;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient {

    @POST("api")
    Call<Message> ThemNguoiDung(@Body NguoiDung nguoiDung);
}
