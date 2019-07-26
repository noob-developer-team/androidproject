package com.example.assignment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Controller.RetrofitController;
import com.example.assignment.Controller.RetrofitInstance;
import com.example.assignment.Model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends Fragment {
    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private Calendar calendar;
    private int dayOfMonth;
    private List<DMY> isDMY;
    private List<Schedule> schedules;
    private TextView textDateScheduleHeader;
    private TextView textViewDateScheduleHeader;
    private String dayToChoose;
    private String defaultTextScheduleHeader;
    private int userId;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("E");
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy/MMM/dd");
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat fullFormatMM = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_schedule, container, false);
        RecyclerView myRecyclerView = view.findViewById(R.id.recycler_id);
        RecyclerView myRecyclerView2 = view.findViewById(R.id.recycler_id2);
        textDateScheduleHeader = view.findViewById(R.id.test_calendartxt);
        textViewDateScheduleHeader = view.findViewById(R.id.txtDateHeader);
        defaultTextScheduleHeader = textDateScheduleHeader.getText().toString();
        view.findViewById(R.id.btn_calendar);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dayToChoose = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Date d = null;
                        try {
                            d = fullFormatMM.parse(dayToChoose);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            String dateSchedule;
                            dateSchedule = fullFormatMM.format(d);
                            getSchedule(userId, dateSchedule);
                        } catch (Exception ex) {
                            Log.v("Exception", ex.getLocalizedMessage());
                        }
                        String dateScheduleHeader = monthFormat.format(d);
                        textViewDateScheduleHeader.setText(dateScheduleHeader);
                        if (!(dayFormat.format(date).equals(Integer.toString(dayOfMonth)))) {
                            textDateScheduleHeader.setText(dayToChoose);
                        } else {
                            textDateScheduleHeader.setText(defaultTextScheduleHeader);
                        }
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });


        RecyclerVadapater recyclerVadapater = new RecyclerVadapater(getContext(), isDMY);
        RecyclerVadapater2 recyclerVadapater2 = new RecyclerVadapater2(getContext(), schedules);

        myRecyclerView.setAdapter(recyclerVadapater);
        myRecyclerView2.setAdapter(recyclerVadapater2);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        myRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Date today = new Date();
        String subjectDate = fullFormatMM.format(today);
        userId = MainActivity.userId;
        isDMY = new ArrayList<>();
        LocalDate thisMonth = LocalDate.now();
        LocalDate nextMonth;
        nextMonth = thisMonth.plusMonths(1);
        long day = ChronoUnit.DAYS.between(thisMonth, nextMonth);
        String date;
        String Day;

        for(int i=1; i<=day; i++){
            date = thisMonth.getYear() + "/" + thisMonth.getMonth() + "/"+ i;
            try {
                Date d = fullFormat.parse(date);
                Day = dayFormat.format(d);
                date = dayOfWeekFormat.format(d);
                isDMY.add(new DMY(date, Day));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
        }
        getSchedule(userId, subjectDate);
    }

    private void getSchedule(int userId, String date) {
        RetrofitController retrofit = RetrofitInstance.getService();
        Call<List<Schedule>> call = retrofit.getSchedule(userId, date);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(@Nullable Call<List<Schedule>> call, @NonNull Response<List<Schedule>> response) {
                if (response.isSuccessful()) {
                    schedules = response.body();
                } else {
                    Log.d("Error", response.message());
                }
            }

            @Override
            public void onFailure(@Nullable Call<List<Schedule>> call,@NonNull Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
        try {
            schedules = call.execute().body();
        }
        catch (Exception e)
        {
            Log.d("Can't get list schedule!", e.getMessage());
        }

    }
}
