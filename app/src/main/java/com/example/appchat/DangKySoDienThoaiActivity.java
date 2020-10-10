package com.example.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class DangKySoDienThoaiActivity extends AppCompatActivity {
    FirebaseAuth auth;

    private String HoTen_DangKy;

    private EditText etxtSoDienThoai_DangKy;
    protected Button btnTiepTuc_SoDienThoai;
    protected Button btnBack_OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_so_dien_thoai);

        //Ẩn Thanh Trạng Thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();

        Init_Data();
        btnTiepTuc_SoDienThoai_Click();

        HoTen_DangKy = getIntent().getExtras().getString("HoTen_DangKy");
    }

    protected void Init_Data() {
        btnTiepTuc_SoDienThoai = (Button) findViewById(R.id.btnTiepTuc_SoDienThoai);
        etxtSoDienThoai_DangKy = (EditText) findViewById(R.id.etxtSoDienThoai_DangKy);
    }

    protected void btnTiepTuc_SoDienThoai_Click() {
        btnTiepTuc_SoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etxtSoDienThoai_DangKy.getText().toString().isEmpty()){
                    String SoDienThoai_GetCode = etxtSoDienThoai_DangKy.getText().toString();
                    SoDienThoai_GetCode = SoDienThoai_GetCode.substring(1);

                    Intent intent_02 = new Intent(DangKySoDienThoaiActivity.this, NhapMaOTPActivity.class);
                    intent_02.putExtra("SoDienThoai_DangKy", SoDienThoai_GetCode);
                    intent_02.putExtra("HoTen_DangKy", HoTen_DangKy);

                    startActivity(intent_02);
                    finish();
                }else {
                    Toast.makeText(DangKySoDienThoaiActivity.this,"Bạn Chưa Nhập Số Điện Thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}