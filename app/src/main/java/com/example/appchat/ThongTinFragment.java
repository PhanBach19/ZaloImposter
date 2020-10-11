package com.example.appchat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import static android.content.Context.MODE_PRIVATE;

public class ThongTinFragment extends Fragment {
    View view;
    Button btnDangXuat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        Init_Data();
        btnDangXuat_Click();

        return view;
    }

    protected void  Init_Data(){
        btnDangXuat = (Button) view.findViewById(R.id.btnDangXuat_TaiKhoan);
    }

    protected void btnDangXuat_Click(){
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Đăng Xuất");
                builder.setMessage("Bạn Có Chắc Muốn Đăng Xuất ?");
                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Xoá Thông Tin Tài Khoản Trên Điện Thoại
                        SharedPreferences preferences = getActivity().getSharedPreferences("data_dang_nhap", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("SoDienThoai");
                        editor.remove("MatKhau");
                        editor.remove("Token_DangNhap");
                        editor.apply();

                        Intent intent = new Intent(getActivity(), SplashScreen.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Huỷ Bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
