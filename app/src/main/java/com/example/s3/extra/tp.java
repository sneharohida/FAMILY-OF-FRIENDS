package com.example.s3.extra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.s3.R;

public class tp extends AppCompatActivity {
TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp);

        tt=findViewById(R.id.tt);

        SessionManager sm=new SessionManager(this);
   //     HashMap<String,String> hm=sm.getUserDetail();

    /*    String un=hm.get(SessionManager.USER_NAME);
        String pa=hm.get(SessionManager.PASSWORD);
        String mj=hm.get(SessionManager.MAJOR);
        String yr=hm.get(SessionManager.YEAR);
        String fnum=hm.get(SessionManager.FNUM);
        String id=hm.get(SessionManager.ID);
android:endColor="#00dbde"
        android:startColor="#fc00ff"

        tt.setText(un+" \n "+pa+" \n "+mj+" \n "+yr+" \n "+fnum+" \n "+id);
*/
        if(sm.userSessions.getAll().size()==1)
            tt.setText("ghfjhkj");
        else
            tt.setText(""+sm.userSessions.getAll());
     //   tt.setText(sm.getIsBack());

    }
}