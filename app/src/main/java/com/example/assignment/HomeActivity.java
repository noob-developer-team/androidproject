package com.example.assignment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView btn_nv = findViewById(R.id.btn_navigation);
        btn_nv.setOnNavigationItemSelectedListener(bnv);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ScheduleActivity()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener bnv = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectitem = null;

            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectitem = new ScheduleActivity();
                    break;
                case R.id.navigation_profile:
                    selectitem = new ProfileActivity();
                    break;
                case R.id.navigation_setting:
                    selectitem = new SettingActivity();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectitem).commit();
            return true;
        }
    };

}
