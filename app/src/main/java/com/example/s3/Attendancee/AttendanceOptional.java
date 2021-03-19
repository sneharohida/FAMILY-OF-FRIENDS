package com.example.s3.Attendancee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.s3.DashBoard;
import com.example.s3.R;

public class AttendanceOptional extends DashBoard {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_attendance_optional,null,false);
        drawerLayout.addView(views,1);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}