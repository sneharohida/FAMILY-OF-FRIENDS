package com.example.s3.NightOut;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class NightOutReturn extends DashBoard {
    Button button;
    Integer id;
    String url = "http://192.168.64.174:8080/Hostel/NightOutReturn", re, uname, wnum = "9834950004", rd, rt;
    final Calendar myCalendar = Calendar.getInstance();
    RequestParams params;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views = inflater.inflate(R.layout.activity_night_out_return, null, false);
        drawerLayout.addView(views, 1);
        final SessionManager sm = new SessionManager(this);
        final HashMap<String, String> hm = sm.getUserDetail();
        String id1 = hm.get(SessionManager.ID);
        uname = hm.get(SessionManager.USER_NAME);
        id = Integer.parseInt(id1);
        rd = hm.get(SessionManager.RD);
        rt = hm.get(SessionManager.RT);

        button = findViewById(R.id.sub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());


                if (date.compareTo(rd) > 0) {
                    sm.setIsBack("yes");
                    // Log.d("ttt",hm.get(SessionManager.IS_BACK));
                    Toast.makeText(NightOutReturn.this, "You have paased the return date. Contact warden", Toast.LENGTH_SHORT).show();
                } else if (date.compareTo(rd) < 0) {
                    sm.setIsBack("yes");
                    if (time.compareTo(rt) > 0)
                        Toast.makeText(NightOutReturn.this, "You have paased the return time. Contact warden", Toast.LENGTH_SHORT).show();
                    else {
                        sm.setIsBack("yes");
                        new Async().execute();
                    }

                } else {
                    sm.setIsBack("yes");
                    new Async().execute();
                }
            }
        });
    }


    class Async extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params = new RequestParams();
                params.put("k1", id);
                client = new AsyncHttpClient();

                client.post(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        Toast.makeText(NightOutReturn.this, "Success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Toast.makeText(NightOutReturn.this, "Success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                      //  Toast.makeText(NightOutReturn.this, "Success", Toast.LENGTH_SHORT).show();
                        SmsManager mySmsManager = SmsManager.getDefault();
                        //String sname = name.getEditText().getText().toString();
                        String Selfmessage = uname + "has returned from NightOut";
                        mySmsManager.sendTextMessage(wnum, null, Selfmessage, null, null);
                        Intent intent = new Intent(NightOutReturn.this, DashBoard.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        // test.setImageBitmap(bm);
                       // Toast.makeText(NightOutReturn.this, "Fail  " + statusCode, Toast.LENGTH_SHORT).show();
                        SmsManager mySmsManager = SmsManager.getDefault();
                        //String sname = name.getEditText().getText().toString();
                        String Selfmessage = uname + "has returned from NightOut";
                        mySmsManager.sendTextMessage(wnum, null, Selfmessage, null, null);
                        Intent intent = new Intent(NightOutReturn.this, DashBoard.class);
                        startActivity(intent);
                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            } catch (Exception e) {
                // Log.d("tag",e.toString());

                //err=e.toString();
                // t.setText(err);
            }
            super.onPostExecute(aVoid);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}