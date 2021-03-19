package com.example.s3.Notice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s3.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class NoticeDetail extends AppCompatActivity {

    ImageView im;
    CollapsingToolbarLayout co;
    TextView txt;
    private ArrayList<String> s1=new ArrayList<>();
    private ArrayList<String> s2=new ArrayList<>();
    private ArrayList<String> s3=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notice_detail);

        im=findViewById(R.id.im);
        co=findViewById(R.id.coltoolbar);
        txt=findViewById(R.id.txt);


        s1=(ArrayList<String>)getIntent().getSerializableExtra("s1");
       s2=(ArrayList<String>)getIntent().getSerializableExtra("s2");
       s3=(ArrayList<String>)getIntent().getSerializableExtra("s3");

        //Integer pos=Integer.parseInt(getIntent().getStringExtra("Position"));

        Integer pos=getIntent().getIntExtra("Position",0);



        // Drawable tr=R.drawable.
       // im.setImageDrawable(R.drawable.theft);
        if(s3.get(pos).equals("Warning"))
        im.setImageResource(R.drawable.high);
        else if(s3.get(pos).equals("Fest"))
            im.setImageResource(R.drawable.party);
        else if(s3.get(pos).equals("Social Event"))
            im.setImageResource(R.drawable.tree);
        else if(s3.get(pos).equals("General"))
            im.setImageResource(R.drawable.warn);


        co.setTitle(s1.get(pos));
        txt.setText(s2.get(pos));

    }

}