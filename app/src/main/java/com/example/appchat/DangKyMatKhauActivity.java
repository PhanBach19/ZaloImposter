package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DangKyMatKhauActivity extends AppCompatActivity {
    Button btnNext;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_mat_khau);

        Init_Data();
        btnBack_Click();
        btnNext_Click();
    }

    protected void Init_Data() {
        btnBack = (ImageButton) findViewById(R.id.btnBack_DangKy_MatKhau);
        btnNext = (Button) findViewById(R.id.btnTiepTuc_MatKhau);
    }

    protected void btnBack_Click() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void btnNext_Click() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyMatKhauActivity.this, TroChuyenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}