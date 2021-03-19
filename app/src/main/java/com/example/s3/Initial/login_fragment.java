package com.example.s3.Initial;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3.Admin.AdminDashboard;
import com.example.s3.Complaint.Generic_complaint;
import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class login_fragment extends Fragment {

    TextInputLayout pass, name;
    Button login;
    TextView ans;
    CheckBox forget;
    float v = 0;
    Boolean t = false;
    String records = "", err = "", u, p, id;
    RequestParams params;
    AsyncHttpClient client;
    SessionManager sm;
    Integer i = 0, ec = 0;
    String url = "http://192.168.64.174:8080/Hostel/Login", major = "", yr = "", fnum = "";

    // String url="http://192.168.43.65:8080/Hostel/Login",major="",yr="";
    //String url="http://10.0.2.2:8080/Hostel/Login";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        name = root.findViewById(R.id.uname);
        pass = root.findViewById(R.id.pass);
        login = root.findViewById(R.id.login);
        forget = root.findViewById(R.id.c);
    //    ans = root.findViewById(R.id.ans);
        sm = new SessionManager(getContext());

        name.setTranslationX(800);
        pass.setTranslationX(800);
        forget.setTranslationX(800);
        login.setTranslationX(800);

        name.setAlpha(v);
        pass.setAlpha(v);
        forget.setAlpha(v);
        login.setAlpha(v);

        name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
       // Log.d("ttt", "gfdghjhbjhkjhkj  " );

        if (sm.getUserDetail().get(SessionManager.REM) == null) {
            Log.d("ttt", "heeeeeeee");
            sm.setrem("no");
        } else if (sm.getUserDetail().get(SessionManager.REM).equals("yes")) {
           name.getEditText().setText(sm.getUserDetail().get(SessionManager.USER_NAME));
            pass.getEditText().setText(sm.getUserDetail().get(SessionManager.PASSWORD));
            Log.d("ttt", "in");

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u = name.getEditText().getText().toString();
                p = pass.getEditText().getText().toString();
                new Async2().execute();


            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    class Async2 extends AsyncTask<Void, Void, Void> {
        String err = "";

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            params = new RequestParams();
            params.put("k1", u);
            params.put("k2", p);
            client = new AsyncHttpClient();
            client.post(url, params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {


                    try {


                        ec = Integer.valueOf(response.getString(0));
                        if (ec != 12) {
                            major = response.getString(1);
                            yr = response.getString(2);
                            fnum = response.getString(3);
                            id = response.getString(4);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (forget.isSelected()||forget.isChecked()) {
                        sm.setrem("yes");
                        Log.d("ttt", "c checked  " + ec + "");

                    }
                  /*  ArrayList<Integer> days = new ArrayList<Integer>();
                    days.add(Calendar.MONDAY);
                    days.add(Calendar.TUESDAY);
                    days.add(Calendar.WEDNESDAY);
                    days.add(Calendar.THURSDAY);
                    days.add(Calendar.FRIDAY);
                    days.add(Calendar.SATURDAY);
                    days.add(Calendar.SUNDAY);
                    Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                    i.putExtra(AlarmClock.EXTRA_MESSAGE, "Hey Time to Fill Attendance");
                    i.putExtra(AlarmClock.EXTRA_DAYS, days);
                    i.putExtra(AlarmClock.EXTRA_HOUR, 21);
                    i.putExtra(AlarmClock.EXTRA_MINUTES, 25);
                    startActivity(i);*/


                    if (ec == 12) {
                        sm.createLoginSession("1", "2");

                        sm.setIsLog("yes");
                        sm.createLoginSession2("yes", "no", "no", "yes", "no", "", "admin" );

                        sm.createLoginSession("mahima", "mahima", "Engineering", "Fourth Year", "9834950004", "22");
                        Toast.makeText(getContext(),"Logged in Admin ",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), AdminDashboard.class);
                        startActivity(intent);
                    } else {

                        sm.setIsLog("yes");
                        sm.createLoginSession("1", "2");
                        sm.createLoginSession2("yes", "no", "no", "yes", "no", "", "student");

                        sm.createLoginSession(u, p, major, yr, fnum, id);
                        Log.d("ttt", u + " " + p + " " + yr + " " + id);
                        Toast.makeText(getActivity(),"Logged in "+u,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), DashBoard.class);
                        startActivity(intent);


                    }

                 /*   Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                    i.putExtra(AlarmClock.EXTRA_MESSAGE, "Hey Time to Fill Attendance");
                    i.putExtra(AlarmClock.EXTRA_DAYS, days);
                    i.putExtra(AlarmClock.EXTRA_HOUR, 21);
                    i.putExtra(AlarmClock.EXTRA_MINUTES, 25);
                    startActivity(i);*/

                  /*  final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            sm.createLoginSession(u,p,major,yr,fnum,id);
                            Log.d("ttt",u+" "+p);
                            Intent intent = new Intent(getContext(), tp.class);
                            startActivity(intent);
                        }
                    }, 1000);*/

                    // Toast.makeText(Attendance.this, "hey", Toast.LENGTH_SHORT).show();


                    // Toast.makeText(getActivity(),"Success  1"+response,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    i = 1;
                    //String a= (String) response.get("Ma");
                    //  String a=response.getString("Ma");

                  //  Toast.makeText(getActivity(), "Success  4", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    super.onSuccess(statusCode, headers, responseString);
                    i = 1;
                    // String a= (String) responseString.get("yr");
                    //  String a=response.getString("Ma");

                   // Toast.makeText(getActivity(), "Success  5", Toast.LENGTH_SHORT).show();

                    //  Toast.makeText(getActivity(),"Success 3",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("ttt", String.valueOf(statusCode) + "  " + responseString);
                    Toast.makeText(getActivity(), "Fail to login, Check your credentials", Toast.LENGTH_SHORT).show();
                    //    Intent intent = new Intent(getContext(), AdminDashboard.class);
                    //  startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    Log.d("tag", String.valueOf(statusCode));
                    Toast.makeText(getActivity(), "Fail to login, Check your credentials", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    Log.d("tag", String.valueOf(statusCode) + " " + throwable);
                    Toast.makeText(getActivity(), "Fail to login, Check your credentials", Toast.LENGTH_SHORT).show();

                }
            });


            // new Async().execute();


            super.onPostExecute(aVoid);
        }
    }

}
