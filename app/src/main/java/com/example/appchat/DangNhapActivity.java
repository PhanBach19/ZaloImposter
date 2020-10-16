package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.appchat.Models.Message;
import com.example.appchat.Models.NguoiDung;
import com.example.appchat.Retrofit2.APIUtils;
import com.example.appchat.Retrofit2.DataClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    String SDT, MatKhau;
    Button btnTiepTuc_DangNhap, btnLayLaiMatKhau;
    ImageButton btnBack_DangNhap;

    EditText editTextMatKhau_DangNhap;
    EditText editTextSDT_DangNhap;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //Ẩn Thanh Trạng Thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init_Data();
        btnBack_DangNhap_Click();
        btnTiepTuc_DangNhap_Click();
        btnLayLaiMatKhau_Click();
    }

    protected void  Init_Data(){
        btnLayLaiMatKhau = (Button) findViewById(R.id.btnLayLaiMatKhau);
        btnBack_DangNhap = (ImageButton) findViewById(R.id.btnBack_DangNhap);
        btnTiepTuc_DangNhap = (Button) findViewById(R.id.btnTiepTuc_DangNhap);

        editTextSDT_DangNhap = (EditText) findViewById(R.id.etxtSoDienThoai_DangNhap);
        editTextMatKhau_DangNhap = (EditText) findViewById(R.id.etxtMatKhau_DangNhap);
    }

    protected void btnTiepTuc_DangNhap_Click(){
        btnTiepTuc_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SDT = editTextSDT_DangNhap.getText().toString();
                MatKhau = editTextMatKhau_DangNhap.getText().toString();

                NguoiDung nguoiDung = new NguoiDung();

                nguoiDung.setSoDienThoai(SDT);
                nguoiDung.setPassword(MatKhau);

                DataClient dataClient = APIUtils.getData();
                Call<Message> call = dataClient.DangNhap(nguoiDung);
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        Message message = (Message) response.body();

                        if(message != null){
                            if(message.getSuccess() == 1){
                                //Lưu Thông Tin Đăng Nhập Của Người Dùng
                                preferences = getSharedPreferences("data_dang_nhap", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("SoDienThoai", SDT);
                                editor.putString("MatKhau", MatKhau);
                                editor.putString("Token_DangNhap", message.getToken());
                                editor.apply();

                                Intent intent = new Intent(DangNhapActivity.this, TroChuyenActivity.class);
                                startActivity(intent);

                                finish();
                            }else {
                                Toast.makeText(DangNhapActivity.this,"Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(DangNhapActivity.this,"Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                        Log.e("Server Message: ", t.getMessage());
                    }
                });
            }
        });
    }

    protected void btnBack_DangNhap_Click(){
        btnBack_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void btnLayLaiMatKhau_Click(){
        btnLayLaiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, LayLaiMatKhauActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}