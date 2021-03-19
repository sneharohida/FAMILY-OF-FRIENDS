package com.example.s3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminNightouts extends AdminDashboard {
    TableLayout t1;
    private ArrayList<String> s2=new ArrayList<>();
    private ArrayList<String> s3=new ArrayList<>();
    private ArrayList<String> s1=new ArrayList<>();
    public ArrayList<String> same=new ArrayList<>();
    public ArrayList<String> same2=new ArrayList<>();
    public ArrayList<String> combined=new ArrayList<>();
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/AdminNightout";

    private ArrayList<JSONArray> mul=new ArrayList<JSONArray>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_admin_nightouts,null,false);
        drawerLayout.addView(views,1);

        t1=findViewById(R.id.t1);
        s1.clear();
        s2.clear();
        s3.clear();
        mul.clear();

        new Async().execute();
    }
    class Async extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                client=new AsyncHttpClient();
                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        Log.d("ttt","gvh");
                        for(int i=0;i<3;i++) {
                            try {
                                mul.add(response.getJSONArray(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for(int i=0;i<3;i++)
                        {
                            for(int j=0;j<mul.get(i).length();j++)
                            {
                                if(i==0) {
                                    try {
                                        s1.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(i==1) {
                                    try {
                                        s2.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }if(i==2) {
                                try {
                                    s3.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            }

                        }

                            for(int i=0;i<s1.size();i++)
                            {
                                TableRow r1=new TableRow(AdminNightouts.this);
                                TextView v1=new TextView(AdminNightouts.this);
                                v1.setText(s1.get(i));
                                v1.setTextColor(Color.BLUE);
                                v1.setTextSize(15);
                                v1.setBackgroundResource(R.drawable.table_row2);
                                v1.setGravity(Gravity.CENTER);
                                v1.setPadding(1,10,1,10);
                                r1.addView(v1);

                                TextView v2=new TextView(AdminNightouts.this);
                                v2.setText(s2.get(i));
                                v2.setTextSize(15);
                                v2.setTextColor(Color.BLUE);
                                v2.setBackgroundResource(R.drawable.table_row);
                                v2.setGravity(Gravity.CENTER);
                                v2.setPadding(1,10,1,10);
                                android.widget.TableRow.LayoutParams p = new android.widget.TableRow.LayoutParams();
                                p.leftMargin = dpToPixel(10,AdminNightouts.this); // right-margin = 10dp
                                v2.setLayoutParams(p);
                                r1.addView(v2);

                                TextView v3=new TextView(AdminNightouts.this);
                                v3.setText(s3.get(i));
                                v3.setTextSize(15);
                                v3.setTextColor(Color.BLUE);
                                v3.setBackgroundResource(R.drawable.table_row);
                                v3.setGravity(Gravity.CENTER);
                                v3.setPadding(1,10,1,10);
                                android.widget.TableRow.LayoutParams p2 = new android.widget.TableRow.LayoutParams();
                                p2.leftMargin = dpToPixel(10,AdminNightouts.this); // right-margin = 10dp
                                v3.setLayoutParams(p2);
                                r1.addView(v3);
                                t1.addView(r1);
                            }


                        Log.d("ttt",s1.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Log.d("ttt",statusCode+"");
                        Toast.makeText(AdminNightouts.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        Log.d("ttt",statusCode+"");
                        Toast.makeText(AdminNightouts.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("ttt",statusCode+"");
                        Toast.makeText(AdminNightouts.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();
                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                Log.d("ttt",e.toString());

                //err=e.toString();
                // t.setText(err);
            }
            super.onPostExecute(aVoid);

        }
    }
    private static Float scale;
    public static int dpToPixel(int dp, Context context) {
        if (scale == null)
            scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent0 = new Intent(this, AdminDashboard.class);
        startActivity(intent0);
    }}