package com.example.s3.Initial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3.Admin.AdminDashboard;
import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;

public class Splash extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 5000;
 //   View f,s,t,fo,fi,si;
    TextView a,slogan;
  //  Animation top,bot,mid;
    static final int NUM_PAGES=3;
    ViewPager viewPager;
    ScreenSlidePagerAdapter pagerAdapter;
    LinearLayout lin;
    RelativeLayout up;
    ImageView img;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       // SessionManager sm=new SessionManager(getApplicationContext());
       // final HashMap<String,String> hm=sm.getUserDetail();

       // final String is= SessionManager.IS_LOGGEDIN;
final SessionManager sm=new SessionManager(this);
  /*      top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bot= AnimationUtils.loadAnimation(this,R.anim.bot);
        mid= AnimationUtils.loadAnimation(this,R.anim.mid);
*/
anim=AnimationUtils.loadAnimation(this,R.anim.onboard);
        img=findViewById(R.id.img);
        //up=findViewById(R.id.up);
        viewPager=findViewById(R.id.pager);
        pagerAdapter=new ScreenSlidePagerAdapter(getSupportFragmentManager());


        a=findViewById(R.id.logo);
        slogan=findViewById(R.id.tagLine);

      //  a.setAnimation(mid);
        //slogan.setAnimation(bot);

       /* f.setAnimation(top);
        s.setAnimation(top);
        t.setAnimation(top);
        fo.setAnimation(top);
        fi.setAnimation(top);
        si.setAnimation(top);*/
        Log.d("ttt",sm.userSessions.getAll()+"");

        if(sm.userSessions.getAll().size()==1){

            Animation animation=AnimationUtils.loadAnimation(Splash.this,R.anim.onboard);

            viewPager.setAdapter(pagerAdapter);
            viewPager.setAnimation(animation);

        }
        else if(sm.userSessions.getAll().isEmpty())
        {
            Animation animation=AnimationUtils.loadAnimation(Splash.this,R.anim.onboard);

            viewPager.setAdapter(pagerAdapter);
            viewPager.setAnimation(animation);
        }
else
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              Log.d("ttt",sm.getUserDetail().get(SessionManager.IS_LOGGEDIN));
                if (sm.getUserDetail().get(SessionManager.IS_LOGGEDIN).equals("yes")) {

                    if(sm.getUserDetail().get(SessionManager.ADMIN).equals("admin")){
                        Intent i = new Intent(Splash.this, AdminDashboard.class);
                        startActivity(i);
                    }
                    else if(sm.getUserDetail().get(SessionManager.ADMIN).equals("student")) {
                        Intent i = new Intent(Splash.this, DashBoard.class);
                        startActivity(i);
                    }

                 }

          /*   finish();*/
        }
        },SPLASH_TIME_OUT);
img.animate().translationY(-2600).setDuration(1000).setStartDelay(4000);
slogan.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        a.animate().translationY(-1700).setDuration(600).setStartDelay(4000);
     /*   f.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        s.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        t.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        fo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        fi.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        si.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
*/
        //Intent i = new Intent(Splash.this, tp.class);
       //startActivity(i);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 t1=new OnBoardingFragment1();
                    return t1;
                case 1:
                    OnBoardingFragment2 t2=new OnBoardingFragment2();
                    return t2;
                case 2:
                    OnBoardingFragment3 t3=new OnBoardingFragment3();
                    return t3;
            }
            return null;
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}