package com.example.appchat.Retrofit2;

import com.example.appchat.Models.Message;
import com.example.appchat.Models.NguoiDung;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataClient {

    @POST("api")
    Call<Message> ThemNguoiDung(@Body NguoiDung nguoiDung);

    @POST("api/login")
    Call<Message> DangNhap(@Body NguoiDung nguoiDung);

    @GET("api/sdt={sdt}")
    Call<Message> GetThongTinNguoiDung_bySDT(@Path("sdt") String sdt, @HeaderMap Map<String, String> token);

    @PUT("api")
    Call<Message> SuaThongTin(@Body NguoiDung nguoiDung, @HeaderMap Map<String, String> token);
}
