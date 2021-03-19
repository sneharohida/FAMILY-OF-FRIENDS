package com.example.s3.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.s3.Adapter.FeaturedHelperClass;
import com.example.s3.Initial.Login;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
CardView c1,c2,c3,c4,c5,c6,c7,c8;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    TextView txt;
    static final float END_SCALE = 0.7f;
    ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
    Menu menu;
    RelativeLayout contentView;
    AnyChartView any;
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/PollResult",u="",p=""/*,u="Final Year",p="All Students*/;
    String[] category={"PavBhaji","MisalPav","MasalaDosa","Chinese","CholeBhature","PaniPuri","Mix Sabji","RagadaPattice"};
    //   ArrayList<Integer> count;
    int[] count={0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);
        menuIcon = findViewById(R.id.t);
        menu=navigationView.getMenu();
        naviagtionDrawer();

        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        c7=findViewById(R.id.c7);
        c8=findViewById(R.id.c8);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminAttendance.class);
                startActivity(intent2);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminAddNotice.class);
                startActivity(intent2);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminComplaint.class);
                startActivity(intent2);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminPollSelection.class);
                startActivity(intent2);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminNightouts.class);
                startActivity(intent2);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminViewProfile.class);
                startActivity(intent2);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AdminSugestion.class);
                startActivity(intent2);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Async().execute();
            }
        });
    }

    private void opendialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_admin_view_poll, null);

        Log.d("ttt",count[0]+""+count[7]);
        any=view.findViewById(R.id.pie);
        txt=view.findViewById(R.id.txt);
        Integer max=0,in=0;
        Pie pie= AnyChart.pie();
        pie.credits().text("Fest Poll");
        List<DataEntry> dataEntries=new ArrayList<>();
        for(int i=0;i<category.length;i++)
            dataEntries.add(new ValueDataEntry(category[i],count[i]));
        pie.data(dataEntries);
        pie.palette(new String[]{"#0039e6", "#00ffff", "#00ff00", "#ffff00", "#ffa500", "#e60000","#ff33ff", "#6600cc"});
        any.setChart(pie);
        for(int i=0;i<count.length;i++)
        {
            if(max<count[i])
            {
                in=i;
                max=count[i];

            }
        }
        txt.setText(category[in]);
        builder.setView(view).setTitle("Poll Result");
        final AlertDialog dialog = builder.create();
        dialog.show();

    }
    class Async extends AsyncTask<Void,Void,Void> {
        String query;


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            try {
                params=new RequestParams();
                client=new AsyncHttpClient();
                client.post (url,params,new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray resultSet) {
                        super.onSuccess(statusCode, headers, resultSet);
                        try {
                            Log.d("ttt","s");
                            count[0]=Integer.valueOf(resultSet.getString(0));
                            count[1]=resultSet.getInt(1);
                            count[2]=resultSet.getInt(2);
                            count[3]=resultSet.getInt(3);
                            count[4]=resultSet.getInt(4);
                            count[5]=resultSet.getInt(5);
                            count[6]=resultSet.getInt(6);
                            count[7]=resultSet.getInt(7);
opendialog();
                          /*  Integer max=0,in=0;
                            Pie pie= AnyChart.pie();
                            pie.credits().text("Fest Poll");
                            List<DataEntry> dataEntries=new ArrayList<>();
                            for(int i=0;i<category.length;i++)
                                dataEntries.add(new ValueDataEntry(category[i],count[i]));
                            pie.data(dataEntries);
                            pie.palette(new String[]{"#0039e6", "#00ffff", "#00ff00", "#ffff00", "#ffa500", "#e60000","#ff33ff", "#6600cc"});
                            any.setChart(pie);

                            for(int i=0;i<count.length;i++)
                            {
                                if(max<count[i])
                                {
                                    in=i;
                                    max=count[i];

                                }
                            }
                            win.setText(category[in]);*/
                        } catch (Exception e) {
                            Log.d("ttt",e.toString());
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        //   Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);

                        // Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(AdminDashboard.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(AdminDashboard.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(AdminDashboard.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                Log.d("ttt",e.toString());

                // t.setText(err);
            }






            super.onPostExecute(aVoid);
        }
    }


    protected void naviagtionDrawer(){

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }
    protected void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home: Intent intent0 = new Intent(this, AdminDashboard.class);
                startActivity(intent0);
                break;
            case R.id.nav_about:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
                case R.id.nav_login:

                Intent intent2 = new Intent(this, Login.class);
                startActivity(intent2);
                break;
             case R.id.nav_logout:

                 new SessionManager(this).logout();
                 Intent intent20 = new Intent(this, Login.class);
                 startActivity(intent20);
                break;
            case R.id.nav_Attendance:
                Intent intent5 = new Intent(this, AdminAttendance.class);
                    startActivity(intent5);
                break;
            case R.id.nav_NightOut:
                Intent intent6 = new Intent(this, AdminNightouts.class);
                    startActivity(intent6);
                break;
            case R.id.nav_Poll:
                Intent intent7 = new Intent(this,AdminPollSelection.class);
                        startActivity(intent7);
                break;
            case R.id.nav_Complaint:
                Intent intent8 = new Intent(this, AdminComplaint.class);
                startActivity(intent8);
                break;
            case R.id.nav_PollResult:
                new Async().execute();
                break;
            case R.id.nav_NoticeBoard:
                Intent intent10 = new Intent(this, AdminAddNotice.class);
                startActivity(intent10);
                break;
            case R.id.nav_Suggestion:
                Intent intent11 = new Intent(this, AdminSugestion.class);
                startActivity(intent11);
                break;
            case R.id.nav_Profile:
                Intent intent12 = new Intent(this, AdminViewProfile.class);
                startActivity(intent12);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent0 = new Intent(this, AdminDashboard.class);
            startActivity(intent0);
        }
    }
}