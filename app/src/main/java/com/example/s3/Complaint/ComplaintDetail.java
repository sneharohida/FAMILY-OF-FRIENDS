package com.example.s3.Complaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s3.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class ComplaintDetail extends AppCompatActivity {

    ImageView im;
    CollapsingToolbarLayout co;
    TextView cat,sub,hos,flo,room,mess,wash,sn,sr,des;

    private ArrayList<String> s3=new ArrayList<>();
    private ArrayList<String> s4=new ArrayList<>();
    private ArrayList<String> s5=new ArrayList<>();
    private ArrayList<String> s6=new ArrayList<>();
    private ArrayList<String> s7=new ArrayList<>();
    private ArrayList<String> s8=new ArrayList<>();
    private ArrayList<String> s9=new ArrayList<>();
    private ArrayList<String> s10=new ArrayList<>();
    private ArrayList<String> s11=new ArrayList<>();
    private ArrayList<String> s12=new ArrayList<>();
    private ArrayList<ComplaintItem> el=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_complaint_detail);

        im=findViewById(R.id.im);
        co=findViewById(R.id.coltoolbar);
        cat=findViewById(R.id.category);
        sub=findViewById(R.id.subcategory);
        hos=findViewById(R.id.hos_num);
        flo=findViewById(R.id.floor_num);
        room=findViewById(R.id.room_num);
        mess=findViewById(R.id.mess_num);
        wash=findViewById(R.id.washroom);
        sn=findViewById(R.id.staffname);
        sr=findViewById(R.id.staffrole);
        des=findViewById(R.id.desc);




        s3=(ArrayList<String>)getIntent().getSerializableExtra("s3");
        s4=(ArrayList<String>)getIntent().getSerializableExtra("s4");
        s5=(ArrayList<String>)getIntent().getSerializableExtra("s5");
        s6=(ArrayList<String>)getIntent().getSerializableExtra("s6");
        s7=(ArrayList<String>)getIntent().getSerializableExtra("s7");
        s8=(ArrayList<String>)getIntent().getSerializableExtra("s8");
        s9=(ArrayList<String>)getIntent().getSerializableExtra("s9");
        s10=(ArrayList<String>)getIntent().getSerializableExtra("s10");
        s11=(ArrayList<String>)getIntent().getSerializableExtra("s11");
        s12=(ArrayList<String>)getIntent().getSerializableExtra("s12");
       // el=(ArrayList<ComplaintItem>)getIntent().getSerializableExtra("el");

        Integer pos=getIntent().getIntExtra("id",0);

      //  String pos1=getIntent().getStringExtra("id");
//Integer pos=Integer.valueOf(pos1)-1;
        Log.d("ttt",""+pos);


        // Drawable tr=R.drawable.
        // im.setImageDrawable(R.drawable.theft);
        if(s3.get(pos).equals("Hygiene"))
            im.setImageResource(R.drawable.clean);
        else if(s3.get(pos).equals("Repairing"))
            im.setImageResource(R.drawable.repair);
        else if(s3.get(pos).equals("WaterIssues"))
            im.setImageResource(R.drawable.dirt);
        else if(s3.get(pos).equals("Mess"))
            im.setImageResource(R.drawable.mess);
        else if(s3.get(pos).equals("Security"))
            im.setImageResource(R.drawable.guard);
        else if(s3.get(pos).equals("PestControl"))
            im.setImageResource(R.drawable.pest);
        else if(s3.get(pos).equals("StaffBehaviour"))
            im.setImageResource(R.drawable.staff);
        else
            im.setImageResource(R.drawable.miss);

        // co.setTitle(s1.get(pos));
        //txt.setText(s2.get(pos));
//im.setImageResource(el.get(pos).getImageResource());
        cat.setText(s3.get(pos));
        sub.setText(s4.get(pos));
        hos.setText(s5.get(pos));
        flo.setText(s6.get(pos));
        wash.setText(s9.get(pos));
        sn.setText(s10.get(pos));
        des.setText(s12.get(pos));
        room.setText(s7.get(pos));
        mess.setText(s8.get(pos));
        sr.setText(s11.get(pos));


    }
}