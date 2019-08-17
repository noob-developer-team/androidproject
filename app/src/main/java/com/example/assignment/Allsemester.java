package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

public class Allsemester extends AppCompatActivity {
    Toolbar tb;
    RelativeLayout r1,r2,rMain;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allsemester_layout);
        tb = (Toolbar) findViewById(R.id.toolbar_allsemester);
        r1 = (RelativeLayout) findViewById(R.id.relative_layout1);
        r2 = (RelativeLayout) findViewById(R.id.relative_layout2);
        rMain =(RelativeLayout) findViewById(R.id.main_layout_semesters);

        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.INVISIBLE);
        rMain.setVisibility(View.VISIBLE);

        btn = (Button) findViewById(R.id.btn_foundation);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tb.setNavigationIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back_black_24dp,null));
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.VISIBLE);
                tb.setTitle("Foundation Year");
                tb.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        r2.setVisibility(View.GONE);
                        r1.setVisibility(View.VISIBLE);
                        tb.setTitle("All Semesters");
                        tb.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                            }
                        });
                    }
                });
            }
        });

    }


}
