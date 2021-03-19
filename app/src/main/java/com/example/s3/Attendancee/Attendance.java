package com.example.s3.Attendancee;

import androidx.annotation.NonNull;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Attendance extends DashBoard {
    private static final int PERMISSION_READ_STATE = 1;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int ACTIVITY_NOT_INITIALIZED = 1;
    private EditText hos, room, bed;
    String hostelnum, roomnum, bednum;
    Button button;
    RequestParams params;
    RelativeLayout contentView;

    AsyncHttpClient client;
    CodeScanner mCodeScanner;
    CodeScannerView cdv;
    private TextView t1, t2;
    public String di, id, r;
    public String d, qr = "";
    private Button but;
    String url = "http://192.168.64.174:8080/Hostel/Attendance", url2 = "http://192.168.64.174:8080/Hostel/Attendance2", url3 = "http://192.168.64.174:8080/Hostel/Attendance3";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views = inflater.inflate(R.layout.activity_attendance, null, false);
        drawerLayout.addView(views, 1);


        //   createNoticationChannel();


        di = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        //t1=(TextView) findViewById(R.id.txt);
        // t1.setText(di.toString());

        //   StringBuilder sb =new StringBuilder();
//sb.append("IMEI: "+telephonyManager.getDeviceId() +"\n");
        //


        cdv = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, cdv);
        t1 = findViewById(R.id.res);

        t2 = findViewById(R.id.res2);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t1.setText("Attendee -> " + result.getText());
                        t2.setText("Device id -> " + di.toString());
                        r = result.getText();
                        qr = result.getText();
                    }
                });
            }
        });

        cdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        Log.d("ttt", "1");
        SessionManager sm = new SessionManager(this);

        Log.d("ttt", sm.getUserDetail().get(SessionManager.IS_FIRST));

        if (sm.getUserDetail().get(SessionManager.IS_FIRST).equals("yes")) {

            opendialog();
        }


        but = findViewById(R.id.but);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

     /*   Intent intent=new Intent(Attendance.this,ReminderBroadcast.class);
        //intent.setAction("MY_NOTIFICATION_MESSAGE");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(Attendance.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Calendar calendar=Calendar.getInstance();
       // calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.HOUR,21);
        calendar.add(Calendar.MINUTE,31);
        calendar.add(Calendar.SECOND,0);*/
                //     alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent );
                // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()-System.currentTimeMillis(),ten,pendingIntent );

                String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                if (time.compareTo("21:30") > 0) {
                    Toast.makeText(Attendance.this, "Time done for filling Attendance", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Attendance.this, DashBoard.class);
                    startActivity(intent);


                }
                SessionManager sm = new SessionManager(Attendance.this);

             /*   if (!sm.getUserDetail().get(SessionManager.IS_BACK).equals("yes")) {
                    Intent i = new Intent(Attendance.this, AttendanceOptional.class);
                    startActivity(i);

                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    //calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 21);
                    calendar.set(Calendar.MINUTE, 30);
                    calendar.set(Calendar.SECOND, 0);

                    Log.d("ttt", String.valueOf(calendar.getTimeInMillis()));
                    Log.d("ttt", String.valueOf(System.currentTimeMillis()));

                    if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                        finish();
                    } else {
               /* ArrayList<Integer> days = new ArrayList<Integer>();
                days.add(Calendar.MONDAY);
                days.add(Calendar.TUESDAY);
                days.add(Calendar.WEDNESDAY);
                days.add(Calendar.THURSDAY);
                days.add(Calendar.FRIDAY);
                days.add(Calendar.SATURDAY);
                days.add(Calendar.SUNDAY);


                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to Fill Attendance");
                i.putExtra(AlarmClock.EXTRA_DAYS, days);
                i.putExtra(AlarmClock.EXTRA_HOUR, 14);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 25);
                startActivity(i);
                Toast.makeText(Attendance.this, "hey", Toast.LENGTH_SHORT).show();


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        Intent intent = new Intent(Attendance.this, Attendance.class);
                        startActivity(intent);
                    }
                }, 1000);*/



    /*    Calendar cal = Calendar.getInstance();
        Uri EVENTS_URI = Uri.parse(getCalendarUriBase(Attendance.this) + "events");
        ContentResolver cr = getContentResolver();

// event insert
        ContentValues values = new ContentValues();
        values.put("calendar_id", 1);
        values.put("title", "Reminder Title");
        values.put("allDay", 0);
        values.put("dtstart", cal.getTimeInMillis() + 11*60*1000); // event starts at 11 minutes from now
        values.put("dtend", cal.getTimeInMillis()+60*60*1000); // ends 60 minutes from now
        values.put("description", "Reminder description");
        values.put("visibility", 0);
        values.put("hasAlarm", 1);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        Uri event = cr.insert(EVENTS_URI, values);

// reminder insert
        Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(Attendance.this) + "reminders");
        values = new ContentValues();
        values.put( "event_id", Long.parseLong(event.getLastPathSegment()));
        values.put( "method", 1 );
        values.put( "minutes", 10 );
        cr.insert( REMINDERS_URI, values );

*/

/*
        ContentResolver cr2 = getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put(CalendarContract.Events.TITLE,"attendance");Log.d("ttt","1");
        cv.put(CalendarContract.Events.DESCRIPTION,"attendance bharo");
        cv.put(CalendarContract.Events.EVENT_LOCATION,"tree");
        cv.put(CalendarContract.Events.DTSTART,Calendar.getInstance().getTimeInMillis() + 11*60*1000);Log.d("ttt","2");
        cv.put(CalendarContract.Events.DTEND,Calendar.getInstance().getTimeInMillis()+60*60*1000);
        cv.put(CalendarContract.Events.CALENDAR_ID,3);Log.d("ttt","3");
        cv.put(CalendarContract.Events.EVENT_TIMEZONE,Calendar.getInstance().getTimeZone().getID());Log.d("ttt","4");
        Uri uri = cr2.insert(CalendarContract.Events.CONTENT_URI, cv);Log.d("ttt","5");



*/


                id = di;
                t1.setText("Attendee -> " + r);
                t2.setText("Device id -> " + id);
                new Async2().execute();


                //   }
                //   }
            }
        });

    }

    public void opendialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Attendance.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogattendance, null);
        hos = view.findViewById(R.id.hosnum);
        room = view.findViewById(R.id.roomnum);
        bed = view.findViewById(R.id.bednum);
        button = view.findViewById(R.id.butto);

        builder.setView(view).setTitle("Provide details");

        final AlertDialog dialog = builder.create();

        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostelnum = hos.getText().toString();
                roomnum = room.getText().toString();
                bednum = bed.getText().toString();
                //    listener.applyTexts(hostelnum, roomnum, bednum);
                if (!hostelnum.equals("") && !roomnum.equals("") && !bednum.equals("")) {
                    dialog.dismiss();
                    new Async().execute();

                } else
                    Toast.makeText(Attendance.this, "please provide details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*  @Override
      public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);

              switch (requestCode) {
                  case PERMISSION_READ_STATE: {
                      if (grantResults.length > 0
                              && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                          // permission granted!
                          // you may now do the action that requires this permission
                          TelephonyManager telephonyManager;
                          telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                          String deviceId = telephonyManager.getDeviceId();
                          String subscriberId = telephonyManager.getSubscriberId();

                          t2=(TextView)findViewById(R.id.txt2);
                          t1=(TextView) findViewById(R.id.txt);
                          t1.setText(deviceId);
                           t2.setText(subscriberId);
                      } else {
                          // permission denied
                          ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);

                      }
                      return;
                  }

              }
      }
  */

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                mCodeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(Attendance.this, "Permission required", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }


    class Async4 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                final SessionManager sm = new SessionManager(Attendance.this);

                params = new RequestParams();
                //string= Base64.encodeToString(bytes,Base64.DEFAULT);
                params.put("k1", sm.getUserDetail().get(SessionManager.ID));
                client = new AsyncHttpClient();

                client.post(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        // super.onSuccess(statusCode, headers, response);
                        //  test.setImageBitmap(bm);
                        sm.setatt("yes");
                        SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                        Date date = new Date();
                        sm.setAttDay(formattedDate.format(date));
                        Toast.makeText(Attendance.this,"Attendance Marked ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(Attendance.this, DashBoard.class);
                        startActivity(intent0);


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                        // test.setImageBitmap(bm);
                        sm.setatt("yes");
                        SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                        Date date = new Date();
                        sm.setAttDay(formattedDate.format(date));
                        Toast.makeText(Attendance.this,"Attendance Marked ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(Attendance.this, DashBoard.class);
                        startActivity(intent0);
                        //   Toast.makeText(Attendance.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //super.onSuccess(statusCode, headers, responseString);
                        //i=1;
                        //test.setImageBitmap(bm);
                        sm.setatt("yes");
                        SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                        Date date = new Date();
                        sm.setAttDay(formattedDate.format(date));
                        Toast.makeText(Attendance.this,"Attendance Marked ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(Attendance.this, DashBoard.class);
                        startActivity(intent0);
                        //    Toast.makeText(Attendance.this,"Success 3 " +responseString,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("tag", responseString);
                        sm.setatt("yes");
                        SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                        Date date = new Date();
                        sm.setAttDay(formattedDate.format(date));
                        Toast.makeText(Attendance.this,"Attendance Marked ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(Attendance.this, DashBoard.class);
                        startActivity(intent0);
                        // test.setImageBitmap(bm);
                        //  Toast.makeText(Attendance.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

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

    class Async2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                SessionManager sm = new SessionManager(Attendance.this);

                params = new RequestParams();
                params.put("k1", sm.getUserDetail().get(SessionManager.ID));
                params.put("k2", qr);
                client = new AsyncHttpClient();

                client.post(url3, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(Attendance.this, "Success 3 " + responseString, Toast.LENGTH_SHORT).show();
                        if (responseString.equals(di))
                            new Async4().execute();
                        else
                            Toast.makeText(Attendance.this, "Please mark attendance by registered phone", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("tag", responseString);
                        // test.setImageBitmap(bm);
                        if (responseString.substring(1, responseString.length() - 1).equals(di))
                            new Async4().execute();
                        else
                            Toast.makeText(Attendance.this, "Please mark attendance by registered phone", Toast.LENGTH_SHORT).show();

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

    class Async extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                final SessionManager sm = new SessionManager(Attendance.this);

                params = new RequestParams();
                params.put("k3", sm.getUserDetail().get(SessionManager.ID));
                params.put("k2", di);
                params.put("k1", hostelnum + roomnum + bednum);
                client = new AsyncHttpClient();

                client.post(url2, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                      //  Toast.makeText(Attendance.this, "Success 1 ", Toast.LENGTH_SHORT).show();
                        sm.setIsFirst("no");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                      //  Toast.makeText(Attendance.this, "Success 2 ", Toast.LENGTH_SHORT).show();
                        sm.setIsFirst("no");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //super.onSuccess(statusCode, headers, responseString);
                        //i=1;
                        //test.setImageBitmap(bm);
                   //     Toast.makeText(Attendance.this, "Success 3 " + responseString, Toast.LENGTH_SHORT).show();
                        sm.setIsFirst("no");

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("tag", responseString);
                        sm.setIsFirst("no");

                        // test.setImageBitmap(bm);
                        //    Toast.makeText(Attendance.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

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


    private void createNoticationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Reminder";
            String description = "Channel for Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("ttt", "hey1");

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

