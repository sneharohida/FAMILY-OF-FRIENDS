package com.example.s3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.s3.Adapter.CategoriesAdapter;
import com.example.s3.Adapter.CategoriesHelperClass;
import com.example.s3.Adapter.FeaturedAdapter;
import com.example.s3.Adapter.FeaturedHelperClass;
import com.example.s3.Attendancee.Attendance;
import com.example.s3.Attendancee.AttendanceOptional;
import com.example.s3.Complaint.CF2;
import com.example.s3.Initial.Login;
import com.example.s3.NightOut.NightOut;
import com.example.s3.NightOut.NightOutReturn;
import com.example.s3.Notice.NoticeBoard;
import com.example.s3.Notice.NoticeDetail;
import com.example.s3.Poll.FestMenu;
import com.example.s3.Poll.Result;
import com.example.s3.extra.SessionManager;
import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FeaturedAdapter.OnNoteListener, CategoriesAdapter.OnNoteListener2 {
    RecyclerView featuredRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon, c1, c2, c3, c4;
    ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
    Menu menu;
    CheckBox c;
    private EditText hos, cat;
    String hostelnum, id, cats;
    Button button;
    LinearLayout contentView;
    static final float END_SCALE = 0.7f;
    RequestParams params;
    AsyncHttpClient client;
    Integer i = 0;
    private ArrayList<String> s1 = new ArrayList<>();
    private ArrayList<String> s2 = new ArrayList<>();
    private ArrayList<String> s3 = new ArrayList<>();
    private ArrayList<String> s4 = new ArrayList<>();
    private ArrayList<JSONArray> mul = new ArrayList<JSONArray>();
    SessionManager sm;
    HashMap<String, String> hm;
    String url = "http://192.168.64.174:8080/Hostel/Notice", url2 = "http://192.168.64.174:8080/Hostel/Feedback", u = "", p = "", name = ""/*,u="Final Year",p="All Students*/;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);

        sm = new SessionManager(DashBoard.this);
        hm = sm.getUserDetail();
        Log.d("ttt", hm.get(SessionManager.MAJOR));

        u = hm.get(SessionManager.MAJOR);
        p = hm.get(SessionManager.YEAR);
        name = hm.get(SessionManager.USER_NAME);
        id = hm.get(SessionManager.ID);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);
        featuredRecycler = findViewById(R.id.featured_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        menu = navigationView.getMenu();

        // Log.d("ttt",dateFormatted);

        cardSelection();
        naviagtionDrawer();
        featuredRecycler();
        categoriesRecycler();
    }

    private void cardSelection() {
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                Date date = new Date();

                String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                Log.d("ttt", SessionManager.IS_BACK);
               /* if (time.compareTo("21:00") < 0) {
                    Toast.makeText(DashBoard.this, "Only applicable from 9:00pm to 9:30pm", Toast.LENGTH_SHORT).show();
                } else if (time.compareTo("21:30") > 0) {
                    Toast.makeText(DashBoard.this, "Time done for filling Attendance", Toast.LENGTH_SHORT).show();
                } else */if (hm.get(SessionManager.IS_BACK).equals("no")) {
                    Intent intent5 = new Intent(DashBoard.this, AttendanceOptional.class);
                    startActivity(intent5);
                } else {

                    if (hm.get(SessionManager.DONE_ATT).equals("no")) {
                        Intent intent = new Intent(DashBoard.this, Attendance.class);
                        startActivity(intent);
                    } else if (hm.get(SessionManager.DONE_ATT).equals("yes")&& formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY)))
                        Toast.makeText(DashBoard.this, "Already made attendance", Toast.LENGTH_SHORT).show();
                    else if(hm.get(SessionManager.DONE_ATT).equals("yes")&& !formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY))){
                        Intent intent = new Intent(DashBoard.this, Attendance.class);
                        startActivity(intent);
                    }
                }

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hm.get(SessionManager.IS_BACK).equals("no")) {
                    Intent intent = new Intent(DashBoard.this, NightOutReturn.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DashBoard.this, NightOut.class);
                    startActivity(intent);
                }


            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormatted = formattedDate.format(nextDayOfWeek(Calendar.WEDNESDAY).getTime());

                Calendar calendar = Calendar.getInstance();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

             //   if (dayOfWeek >= Calendar.FRIDAY || dayOfWeek == Calendar.SUNDAY) {
                    if (hm.get(SessionManager.IS_POLL).equals("yes") && dateFormatted.equals(sm.getUserDetail().get(SessionManager.POLL_WED))) {
                        Toast.makeText(DashBoard.this, "you have made a poll", Toast.LENGTH_SHORT).show();
                    } else if (hm.get(SessionManager.IS_POLL).equals("yes") && !dateFormatted.equals(sm.getUserDetail().get(SessionManager.POLL_WED))) {
                        sm.setIsPoll("no");
                        Intent intent = new Intent(DashBoard.this, FestMenu.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(DashBoard.this, FestMenu.class);
                        startActivity(intent);
                    }
            /*    } else {
                    Log.d("ttt", "" + dayOfWeek);
                    Log.d("ttt", "" + Calendar.SATURDAY);
                    Toast.makeText(DashBoard.this, "Wait for friday", Toast.LENGTH_SHORT).show();

                }*/
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, CF2.class);
                startActivity(intent);

            }
        });


    }

    private void categoriesRecycler() {
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.timer, "Attendance Time", "gdjhghd"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, R.drawable.res2, "Poll Result", ""));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.ic_baseline_note_add_24, "Notices", ""));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.f22, "Suggestion", ""));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses, this);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {
        new Async2().execute();
    }

    protected void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
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
            case R.id.nav_home:
                Intent intent0 = new Intent(this, DashBoard.class);
                startActivity(intent0);
                break;
            case R.id.nav_about:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
            case R.id.nav_suggestion:
                opendialog();
                break;
            case R.id.nav_login:
                Intent intent2 = new Intent(this, Login.class);
                startActivity(intent2);
                break;
            case R.id.nav_profile:
                Intent intent4 = new Intent(this, UpdateProfile.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                sm.logout();
                Intent intent20 = new Intent(this, Login.class);
                startActivity(intent20);
                break;
            case R.id.nav_Attendance:
                SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                Date date = new Date();

                String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                Log.d("ttt", SessionManager.IS_BACK);
                if (time.compareTo("21:00") < 0) {
                    Toast.makeText(DashBoard.this, "Only applicable from 9:00pm to 9:30pm", Toast.LENGTH_SHORT).show();
                } else if (time.compareTo("21:30") > 0) {
                    Toast.makeText(DashBoard.this, "Time done for filling Attendance", Toast.LENGTH_SHORT).show();
                } else if (hm.get(SessionManager.IS_BACK).equals("no")) {
                    Intent intent5 = new Intent(DashBoard.this, AttendanceOptional.class);
                    startActivity(intent5);
                } else {

                    if (hm.get(SessionManager.DONE_ATT).equals("no")) {
                        Intent intent5 = new Intent(DashBoard.this, Attendance.class);
                        startActivity(intent5);
                    } else if (hm.get(SessionManager.DONE_ATT).equals("yes")&& formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY)))
                        Toast.makeText(DashBoard.this, "Already made attendance", Toast.LENGTH_SHORT).show();
                    else if(hm.get(SessionManager.DONE_ATT).equals("yes")&& !formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY))){
                        Intent intent5 = new Intent(DashBoard.this, Attendance.class);
                        startActivity(intent5);
                    }
                }

                break;
            case R.id.nav_NightOut:
                if (hm.get(SessionManager.IS_BACK).equals("no")) {
                    Intent intent6 = new Intent(this, NightOutReturn.class);
                    startActivity(intent6);
                } else {
                    Intent intent6 = new Intent(this, NightOut.class);
                    startActivity(intent6);
                }
                break;
            case R.id.nav_Poll:
                String dateFormatted = new SimpleDateFormat("dd-MMM-yyyy").format(nextDayOfWeek(Calendar.WEDNESDAY).getTime());

                Calendar calendar = Calendar.getInstance();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek >= Calendar.FRIDAY || dayOfWeek == Calendar.SUNDAY) {
                    if (hm.get(SessionManager.IS_POLL).equals("yes") && dateFormatted.equals(sm.getUserDetail().get(SessionManager.POLL_WED))) {
                        Toast.makeText(this, "you have made a poll", Toast.LENGTH_SHORT).show();
                    } else if (hm.get(SessionManager.IS_POLL).equals("yes") && !dateFormatted.equals(sm.getUserDetail().get(SessionManager.POLL_WED))) {
                        sm.setIsPoll("no");
                        Intent intent7 = new Intent(this, FestMenu.class);
                        startActivity(intent7);
                    } else {
                        Intent intent7 = new Intent(this, FestMenu.class);
                        startActivity(intent7);
                    }
                } else {
                    Toast.makeText(this, "Wait for friday", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.nav_Complaint:
                Intent intent8 = new Intent(this, CF2.class);
                startActivity(intent8);
                break;
            case R.id.nav_PollResult:
                Calendar calendar2 = Calendar.getInstance();
                int dayOfWeek2 = calendar2.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek2 >= Calendar.MONDAY && dayOfWeek2 <= Calendar.TUESDAY) {
                    Intent intent9 = new Intent(this, com.example.s3.Poll.Result.class);
                    startActivity(intent9);
                } else {
                    Toast.makeText(this, "Wait for Monday", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_NoticeBoard:
                Intent intent10 = new Intent(this, NoticeBoard.class);
                startActivity(intent10);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent0 = new Intent(this, DashBoard.class);
            startActivity(intent0);
        }
    }

    @Override
    public void onNoteClick2(int position) {
        if (position == 0) {
            SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
            Date date = new Date();

            String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            Log.d("ttt", SessionManager.IS_BACK);
            if (time.compareTo("21:00") < 0) {
                Toast.makeText(DashBoard.this, "Only applicable from 9:00pm to 9:30pm", Toast.LENGTH_SHORT).show();
            } else if (time.compareTo("21:30") > 0) {
                Toast.makeText(DashBoard.this, "Time done for filling Attendance", Toast.LENGTH_SHORT).show();
            } else if (hm.get(SessionManager.IS_BACK).equals("no")) {
                Intent intent5 = new Intent(DashBoard.this, AttendanceOptional.class);
                startActivity(intent5);
            } else {

                if (hm.get(SessionManager.DONE_ATT).equals("no")) {
                    Intent intent = new Intent(DashBoard.this, Attendance.class);
                    startActivity(intent);
                } else if (hm.get(SessionManager.DONE_ATT).equals("yes")&& formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY)))
                    Toast.makeText(DashBoard.this, "Already made attendance", Toast.LENGTH_SHORT).show();
            else if(hm.get(SessionManager.DONE_ATT).equals("yes")&& !formattedDate.format(date).equals(hm.get(SessionManager.ATT_DAY))){
                    Intent intent = new Intent(DashBoard.this, Attendance.class);
                    startActivity(intent);
                }
            }

        }

        if (position == 1) {
            Calendar calendar2 = Calendar.getInstance();
            int dayOfWeek2 = calendar2.get(Calendar.DAY_OF_WEEK);

           // if (dayOfWeek2 >= calendar2.MONDAY && dayOfWeek2 <= calendar2.TUESDAY) {
                Intent intent9 = new Intent(DashBoard.this, Result.class);
                startActivity(intent9);
          /*  } else {
                Toast.makeText(DashBoard.this, "Wait for Monday", Toast.LENGTH_SHORT).show();
            }*/
        }
        if (position == 2) {
            Intent i = new Intent(this, NoticeBoard.class);
            startActivity(i);
        }
        if (position == 3) {
            opendialog();
        }
    }

    private void opendialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogsuggestion, null);
        hos = view.findViewById(R.id.hosnum);
        cat = view.findViewById(R.id.cat);
        c = view.findViewById(R.id.chk);
        button = view.findViewById(R.id.butto);

        builder.setView(view).setTitle("Provide feedback");

        final AlertDialog dialog = builder.create();

        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostelnum = hos.getText().toString();
                cats = cat.getText().toString();
                if (c.isChecked() || c.isSelected()) {
                    name = "Anonymous";
                    id = "0";
                } else if (!c.isChecked() || !c.isSelected()) {
                    name = sm.getUserDetail().get(SessionManager.USER_NAME);
                    id = sm.getUserDetail().get(SessionManager.ID);
                }
                //    listener.applyTexts(hostelnum, roomnum, bednum);
                if (!hostelnum.equals("")) {
                    dialog.dismiss();
                    new Async().execute();

                }
            }
        });

    }


    class Async extends AsyncTask<Void, Void, Void> {
        String err = "";

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params = new RequestParams();
                params.put("k1", name);
                params.put("k3", id);
                params.put("k2", hostelnum);
                params.put("k4", cats);
                client = new AsyncHttpClient();
                client.post(url2, params, new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(DashBoard.this, "Feedback Added!! ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(DashBoard.this, "Feedback Added!! ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        i = 1;
                        Toast.makeText(DashBoard.this, "Feedback Added!! ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                          Toast.makeText(DashBoard.this, "Feedback Added!! ", Toast.LENGTH_SHORT).show();

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            } catch (Exception e) {
                err = e.toString();
                // t.setText(err);
            }


            super.onPostExecute(aVoid);
        }
    }

    class Async2 extends AsyncTask<Void, Void, Void> {
        String err = "";

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params = new RequestParams();
                params.put("k1", u);
                params.put("k2", p);
                client = new AsyncHttpClient();
                client.post(url, params, new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

                        for (int i = 0; i < 4; i++) {
                            try {
                                mul.add(response.getJSONArray(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < mul.get(i).length(); j++) {
                                if (i == 0) {
                                    try {
                                        s1.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (i == 1) {
                                    try {
                                        s2.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (i == 2) {
                                    try {
                                        s3.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (i == 3) {
                                    try {
                                        s4.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
/*
                        for(int i=0;i<s2.size();i++) {
                            if (s3.get(i).equals("Social Event"))
                                exampleList.add(new NoticeItem(R.drawable.tree, s1.get(i), s3.get(i)));
                            else
                                exampleList.add(new NoticeItem(R.drawable.warn, s1.get(i), s3.get(i)));
                        }
*/

                        featuredRecycler.setHasFixedSize(true);
                        featuredRecycler.setLayoutManager(new LinearLayoutManager(DashBoard.this, LinearLayoutManager.HORIZONTAL, false));
                        for (int i = 0; i < s2.size(); i++) {

                            if (s3.get(i).equals("Social Event"))
                                featuredLocations.add(new FeaturedHelperClass(R.drawable.tree, s1.get(i), s4.get(i)));
                            else if (s3.get(i).equals("Fest"))
                                featuredLocations.add(new FeaturedHelperClass(R.drawable.party, s1.get(i), s4.get(i)));

                            else if (s3.get(i).equals("Warning"))
                                featuredLocations.add(new FeaturedHelperClass(R.drawable.high, s1.get(i), s4.get(i)));

                            else if (s3.get(i).equals("General"))
                                featuredLocations.add(new FeaturedHelperClass(R.drawable.warn, s1.get(i), s4.get(i)));


                        }
                        adapter = new FeaturedAdapter(featuredLocations, DashBoard.this);
                        featuredRecycler.setAdapter(adapter);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        //   Toast.makeText(DashBoard.this, "Success 2 ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        i = 1;
                        //  Toast.makeText(DashBoard.this, "Success 3", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        //   Toast.makeText(DashBoard.this, "Failllllllllllllll " + statusCode, Toast.LENGTH_SHORT).show();

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            } catch (Exception e) {
                err = e.toString();
                // t.setText(err);
            }


            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onNoteClick(int position) {
        //  exampleList.get(position);
        Intent i = new Intent(this, NoticeDetail.class);
        i.putExtra("Position", position);
        i.putExtra("s1", s1);
        i.putExtra("s2", s2);
        i.putExtra("s3", s3);
        startActivity(i);


    }

    public void onClick(View view) {
        Intent i = new Intent(this, NoticeDetail.class);
        startActivity(i);

    }

    public static Calendar nextDayOfWeek(int dow) {
        Calendar date = Calendar.getInstance();
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);
        if (!(diff > 0)) {
            diff += 7;
        }
        date.add(Calendar.DAY_OF_MONTH, diff);
        return date;
    }


}