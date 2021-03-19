package com.example.s3.NightOut;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class NightOut extends DashBoard {

    Button go;
    TextInputLayout reason;
    ImageView nxt;
    String uname="",fnum="",lds,rds,lts,rts,res;
    EditText ld,rd,lt,rt;
    String url="http://192.168.64.174:8080/Hostel/NightOut",re;
    final Calendar myCalendar = Calendar.getInstance();
    RequestParams params;
    AsyncHttpClient client;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View views=inflater.inflate(R.layout.activity_night_out,null,false);
        drawerLayout.addView(views,1);

        ld=findViewById(R.id.ld);
        rd=findViewById(R.id.rd);
        lt=findViewById(R.id.lt);
        rt=findViewById(R.id.rt);
        reason=findViewById(R.id.re);
      /*  drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);*/

        // nxt=findViewById(R.id.r);
        go = findViewById(R.id.Submit);

        final SessionManager sm=new SessionManager(this);
        HashMap<String,String> hm=sm.getUserDetail();
        uname = hm.get(SessionManager.USER_NAME);
        fnum = hm.get(SessionManager.FNUM);
        String id1=hm.get(SessionManager.ID);
        id = Integer.parseInt(id1);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // updateLabel();
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ld.setText(sdf.format(myCalendar.getTime()));
            }

        };

        ld.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NightOut.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // updateLabel();
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                rd.setText(sdf.format(myCalendar.getTime()));
            }

        };

        rd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NightOut.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        lt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(NightOut.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        lt.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        rt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(NightOut.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        rt.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lds=ld.getText().toString();
                rds=rd.getText().toString();
                rts=rt.getText().toString();
                lts=lt.getText().toString();
                res=reason.getEditText().getText().toString();

                SmsManager mySmsManager = SmsManager.getDefault();
                //String sname = name.getEditText().getText().toString();
                String Selfmessage = "Your child  " + uname + " is requesting for Night Out. Requesting for a call to grant permission";
                String Wardenmessage = "" + uname + " has registered for the hostel sucessfully.";

                if(fnum==""||fnum.equals("null"))
                    fnum="9834950004";

                mySmsManager.sendTextMessage(fnum, null, Selfmessage, null, null);
sm.setIsBack("no");
                new Async().execute();
            }
        });


    }


    class Async extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params=new RequestParams();
                params.put("k1",id);
                params.put("k2",lds);
                params.put("k3",lts);
                params.put("k4",rds);
                params.put("k5",rts);
                params.put("k6",res);
                params.put("k7",uname);
                client=new AsyncHttpClient();

                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                         }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        SessionManager sessionManager=new SessionManager(NightOut.this);
                        sessionManager.createLoginSession(rds,rts);
                        HashMap<String,String> hm=sessionManager.getUserDetail();
                        Toast.makeText(NightOut.this,"Form filled Successfully!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NightOut.this, DashBoard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        // test.setImageBitmap(bm);
                        SessionManager sessionManager=new SessionManager(NightOut.this);
                        sessionManager.createLoginSession(rds,rts);
                        HashMap<String,String> hm=sessionManager.getUserDetail();
                        Log.d("ttt",hm.get(SessionManager.RD)+" "+hm.get(SessionManager.RT));
                        Toast.makeText(NightOut.this,"Form filled Successfully!!",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(NightOut.this, DashBoard.class);
                        startActivity(intent);

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
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