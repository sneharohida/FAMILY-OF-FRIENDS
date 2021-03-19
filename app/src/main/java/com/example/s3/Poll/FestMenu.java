package com.example.s3.Poll;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class FestMenu extends DashBoard {

    RadioButton m1,m2,m3,m4,m5,m6,m7,m8;
    Button sub;
    Integer a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
    String err="",menu="";
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/PollSelection",u="",p=""/*,u="Final Year",p="All Students*/;
    String url2="http://192.168.64.174:8080/Hostel/PollSelection2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LayoutInflater inflater=LayoutInflater.from(this);
        View views=inflater.inflate(R.layout.activity_fest_menu,null,false);
        drawerLayout.addView(views,1);

        //setContentView(R.layout.activity_fest_menu);
        final SessionManager sm=new SessionManager(FestMenu.this);

        m1=findViewById(R.id.one);
        m2=findViewById(R.id.two);
        m3=findViewById(R.id.th);
        m4=findViewById(R.id.fo);
        m5=findViewById(R.id.fi);
        m6=findViewById(R.id.si);
        m7=findViewById(R.id.sev);
        m8=findViewById(R.id.ei);
        sub=findViewById(R.id.Submit);

        new Async2().execute();



sub.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        menu="";
        if(m1.isChecked())
            menu="PavBhaji";
        if(m2.isChecked())
            menu="MisalPav";
        if(m3.isChecked())
            menu="MasalaDosa";
        if(m4.isChecked())
            menu="Chinese";
        if(m5.isChecked())
            menu="CholeBhature";
        if(m6.isChecked())
            menu="PaniPuri";
        if(m7.isChecked())
            menu="MixSabji";
        if(m8.isChecked())
            menu="RagadaPattice";

        final AlertDialog dialog = new AlertDialog.Builder(FestMenu.this).setTitle("Confirm the menu to proceed")
                .setMessage(menu)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show();

        Button no = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                menu="";
                sm.setIsPoll("no");
                a=0;b=0;c=0;d=0;e=0;f=0;g=0;h=0;

            }
        });

        Button yes = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m1.isChecked()) {
                    // m1.getBackground().setAlpha(100);
                    a++;
                }
                if(m2.isChecked())
                    b++;
                if(m3.isChecked())
                    c++;
                if(m4.isChecked())
                    d++;
                if(m5.isChecked())
                    e++;
                if(m6.isChecked())
                    f++;
                if(m7.isChecked())
                    g++;
                if(m8.isChecked())
                    h++;
                sm.setIsPoll("yes");
                SimpleDateFormat formattedDate= new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormatted=formattedDate.format(nextDayOfWeek(Calendar.WEDNESDAY).getTime());
                sm.setPollWed(dateFormatted);

                dialog.dismiss();
                new Async().execute();
            }
        });




    }
});

    }

    class Async2 extends AsyncTask<Void,Void,Void> {
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
                       // super.onSuccess(statusCode, headers, response);
                        try {
                          //  Toast.makeText(FestMenu.this,"Success 1",Toast.LENGTH_SHORT).show();

                            if(resultSet.getInt(0)==0)
                                m1.setEnabled(false);
                            if(resultSet.getInt(1)==0)
                                m2.setEnabled(false);
                            if(resultSet.getInt(2)==0)
                                m3.setEnabled(false);
                            if(resultSet.getInt(3)==0)
                                m4.setEnabled(false);
                            if(resultSet.getInt(4)==0)
                                m5.setEnabled(false);
                            if(resultSet.getInt(5)==0)
                                m6.setEnabled(false);
                            if(resultSet.getInt(6)==0)
                                m7.setEnabled(false);
                            if(resultSet.getInt(7)==0)
                                m8.setEnabled(false);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(FestMenu.this,"Make Single Poll from available Menu ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);

                        Toast.makeText(FestMenu.this,"Make Single Poll from available Menu ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(FestMenu.this,"Make Single Poll from available Menu ",Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(FestMenu.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                err=e.toString();
                // t.setText(err);
            }


            super.onPostExecute(aVoid);
        }
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
                params.put("a",a);
                params.put("b",b);
                params.put("c",c);
                params.put("d",d);
                params.put("e",e);
                params.put("f",f);
                params.put("g",g);
                params.put("h",h);
                client.post (url2,params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray resultSet) {
                        // super.onSuccess(statusCode, headers, response);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        //   Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        Toast.makeText(FestMenu.this,"Poll made  ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(FestMenu.this, DashBoard.class);
                        startActivity(intent0);
                        // Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(FestMenu.this,"Poll made  ",Toast.LENGTH_SHORT).show();
                        Intent intent0 = new Intent(FestMenu.this, DashBoard.class);
                        startActivity(intent0);
                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                err=e.toString();
                // t.setText(err);
            }




            super.onPostExecute(aVoid);
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}