package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class DangNhapActivity extends AppCompatActivity {

    ImageButton btnBack_DangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //Ẩn Thanh Trạng Thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init_Data();
        btnBack_DangNhap_Click();
    }

    protected void  Init_Data(){
        btnBack_DangNhap = (ImageButton) findViewById(R.id.btnBack_DangNhap);
    }

    protected void btnBack_DangNhap_Click(){
        btnBack_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, WelcomeActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}