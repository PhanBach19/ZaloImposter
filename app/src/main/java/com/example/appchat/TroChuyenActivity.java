package com.example.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TroChuyenActivity extends AppCompatActivity {
    Fragment selectedFragment;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_chuyen);

        //Ẩn Thanh Trạng Thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init_Data();


        //Set Fragment Mặc Định Sẽ Mở Khi Load Activity
        selectedFragment = new TroChuyenFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    private void Init_Data(){
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListerner);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_message:
                            selectedFragment = new TroChuyenFragment();
                            break;
                        case R.id.nav_friend:
                            selectedFragment = new DanhBaFragment();
                            break;
                        case R.id.nav_group:
                            selectedFragment = new NhomFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ThongTinFragment();
                            break;
                        default:
                            selectedFragment = new TroChuyenFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };
}