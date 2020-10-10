package com.example.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class NhapMaOTPActivity extends AppCompatActivity {
    FirebaseAuth auth;

    private String SoDienThoai_XacThuc;
    private String HoTen_DangKy;

    private String VerificationId;
    private PhoneAuthProvider.ForceResendingToken ResendToken;

    EditText etxtMaOTP_DangKy;
    Button btnTiepTuc_MaOTP_DangKy;
    ImageButton btnBack_OTP;

    TextView txt_notify_sending_otp;
    TextView txtSoDienThoai_XacThuc;

    ProgressBar prgbr_Waiting_OTP_DangKy;
    LinearLayout container_nhap_ma_xac_thuc;

    PhoneAuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_ma_o_t_p);

        //Ẩn Thanh Trạng Thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();

        Init_Data();
        btnTiepTuc_MaOTP_DangKy_Click();
        btnBack_OTP_Click();

        HoTen_DangKy = getIntent().getExtras().getString("HoTen_DangKy");
        SoDienThoai_XacThuc = "+84" + getIntent().getExtras().getString("SoDienThoai_DangKy");
        request_OTP(SoDienThoai_XacThuc);

        //Đang Xử Lý OTP Không Cho Back về Activity Trước
        btnBack_OTP.setEnabled(false);
    }

    protected void Init_Data(){
        etxtMaOTP_DangKy = (EditText) findViewById(R.id.etxtMaOTP_DangKy);
        btnTiepTuc_MaOTP_DangKy = (Button) findViewById(R.id.btnTiepTuc_MaOTP_DangKy);
        btnBack_OTP = (ImageButton) findViewById(R.id.btnBack_DangKy_OTP);

        txtSoDienThoai_XacThuc = (TextView) findViewById(R.id.txtSoDienThoai_XacThuc);
        txtSoDienThoai_XacThuc.setText("0" + getIntent().getExtras().getString("SoDienThoai_DangKy"));

        txt_notify_sending_otp = (TextView) findViewById(R.id.txt_notify_sending_otp);

        container_nhap_ma_xac_thuc = (LinearLayout) findViewById(R.id.container_nhap_ma_xac_thuc);
        prgbr_Waiting_OTP_DangKy = (ProgressBar) findViewById(R.id.prgbr_Waiting_OTP_DangKy);
    }

    protected void btnBack_OTP_Click(){
        btnBack_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void btnTiepTuc_MaOTP_DangKy_Click(){
        btnTiepTuc_MaOTP_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etxtMaOTP_DangKy.getText().toString();

                credential = PhoneAuthProvider.getCredential(VerificationId, code);
                SignInWithCredential(credential);

                prgbr_Waiting_OTP_DangKy.setVisibility(View.VISIBLE);
                txt_notify_sending_otp.setText("Đang Kiểm Tra");
                txt_notify_sending_otp.setVisibility(View.VISIBLE);

                container_nhap_ma_xac_thuc.setVisibility(View.GONE);
            }
        });
    }

    protected void request_OTP(String phone) {
        auth.setLanguageCode("vi");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 30, TimeUnit.SECONDS,
                NhapMaOTPActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        if(phoneAuthCredential.getSmsCode() != null){
                            etxtMaOTP_DangKy.setText(phoneAuthCredential.getSmsCode());
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        VerificationId = s;
                        ResendToken = forceResendingToken;

                        prgbr_Waiting_OTP_DangKy.setVisibility(View.GONE);
                        txt_notify_sending_otp.setVisibility(View.GONE);
                        container_nhap_ma_xac_thuc.setVisibility(View.VISIBLE);
                        btnBack_OTP.setEnabled(true);
                    }
                });
    }

    protected void SignInWithCredential(final PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent_03 = new Intent(NhapMaOTPActivity.this, DangKyMatKhauActivity.class);
                    intent_03.putExtra("SoDienThoai_DangKy", SoDienThoai_XacThuc);
                    intent_03.putExtra("HoTen_DangKy", HoTen_DangKy);

                    startActivity(intent_03);
                    finish();
                }else {
                    prgbr_Waiting_OTP_DangKy.setVisibility(View.GONE);
                    txt_notify_sending_otp.setVisibility(View.GONE);
                    container_nhap_ma_xac_thuc.setVisibility(View.VISIBLE);

                    Toast.makeText(NhapMaOTPActivity.this, "Xác Nhận Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}