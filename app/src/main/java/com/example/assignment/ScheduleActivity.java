package com.example.assignment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends Fragment implements DatePickerDialog.OnDateSetListener {
    View v;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    Calendar calendar;
    int dayofMonth;
    private RecyclerView myrecyclerview, myrecyclerview2;
    private List<DMY> isDMY;
    private ImageButton btncalendar;
    private TextView testcal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_schedule, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recycler_id);
        myrecyclerview2 = (RecyclerView) v.findViewById(R.id.recycler_id2);
        testcal = (TextView) v.findViewById(R.id.test_calendartxt);

        v.findViewById(R.id.btn_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        testcal.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,dayofMonth);
                        datePickerDialog.show();
            }
        });


        RecyclerVadapater recyclerVadapater = new RecyclerVadapater(getContext(), isDMY);
        RecyclerVadapater2 recyclerVadapater2 = new RecyclerVadapater2(getContext(), isDMY);

        myrecyclerview.setAdapter(recyclerVadapater);
        myrecyclerview2.setAdapter(recyclerVadapater2);

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        myrecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDMY = new ArrayList<>();
        isDMY.add(new DMY("Monday", "21"));
        isDMY.add(new DMY("Tuesday", "21"));
        isDMY.add(new DMY("Wednesday", "21"));
        isDMY.add(new DMY("Thursday", "21"));
        isDMY.add(new DMY("Friday", "21"));
        isDMY.add(new DMY("Saturday", "21"));
        isDMY.add(new DMY("Monday", "21"));
        isDMY.add(new DMY("Tuesday", "21"));
        isDMY.add(new DMY("Wednesday", "21"));
        isDMY.add(new DMY("Thursday", "21"));
        isDMY.add(new DMY("Friday", "21"));
        isDMY.add(new DMY("Saturday", "21"));


    }
}
