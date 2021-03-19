package com.example.s3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminAttendance extends AdminDashboard {
TableLayout t1;
    RequestParams params;
    FloatingActionButton flo;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/AdminAttendance";
    private ArrayList<String> s1=new ArrayList<>();
    private ArrayList<String> s2=new ArrayList<>();
    private ArrayList<String> s3=new ArrayList<>();
    private ArrayList<String> s4=new ArrayList<>();
    public ArrayList<String> same=new ArrayList<>();
    public ArrayList<String> same2=new ArrayList<>();
    public ArrayList<String> combined=new ArrayList<>();

    private ArrayList<JSONArray> mul=new ArrayList<JSONArray>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LayoutInflater inflater=LayoutInflater.from(this);
        View views=inflater.inflate(R.layout.activity_admin_attendance,null,false);
        drawerLayout.addView(views,1);

        t1=findViewById(R.id.t1);
        flo=findViewById(R.id.flo);
        s1.clear();
        s2.clear();
        s3.clear();
        s4.clear();
        mul.clear();

        new Async().execute();


        flo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminAttendance.this);
                builder.setTitle("Students absent for 2 days");
                builder.setItems(combined.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
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
                client=new AsyncHttpClient();

                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                           for(int i=0;i<4;i++) {
                            try {
                                mul.add(response.getJSONArray(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                           }
                        for(int i=0;i<4;i++)
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
                            }if(i==3) {
                                try {
                                    s4.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            }

                        }

if(s2.size()!=0||s4.size()!=0)
for(int i=0;i<Math.min(s2.size(),s4.size());i++)
{
    TableRow r1=new TableRow(AdminAttendance.this);
    TextView v1=new TextView(AdminAttendance.this);
    v1.setText(s4.get(i));
    v1.setTextColor(Color.BLUE);
    v1.setTextSize(15);
    v1.setBackgroundResource(R.drawable.table_row);
    v1.setGravity(Gravity.CENTER);
    v1.setPadding(1,10,1,10);
    r1.addView(v1);

    TextView v2=new TextView(AdminAttendance.this);
    v2.setText(s2.get(i));
    v2.setTextSize(15);
    v2.setTextColor(Color.BLUE);
    v2.setBackgroundResource(R.drawable.table_row2);
    v2.setGravity(Gravity.CENTER);
    v2.setPadding(1,10,1,10);
    android.widget.TableRow.LayoutParams p = new android.widget.TableRow.LayoutParams();
    p.leftMargin = dpToPixel(10,AdminAttendance.this); // right-margin = 10dp
    v2.setLayoutParams(p);
    r1.addView(v2);
    t1.addView(r1);
}

               Integer max=Math.max(s2.size(),s4.size());
if(s2.size()!=s4.size()){
                        for(int i=0;i<max-Math.min(s2.size(),s4.size());i++)
                        {
                            TableRow r1=new TableRow(AdminAttendance.this);
if(max==s2.size())
{
    TextView v1=new TextView(AdminAttendance.this);
    v1.setText("");
    v1.setTextColor(Color.BLUE);
    v1.setGravity(Gravity.CENTER);
    v1.setTextSize(15);
    v1.setPadding(1,10,1,10);
    r1.addView(v1);

    TextView v2=new TextView(AdminAttendance.this);
    v2.setText(s2.get(Math.min(s2.size(),s4.size())+i));
    v2.setTextColor(Color.BLUE);
    v2.setGravity(Gravity.CENTER);
    v2.setBackgroundResource(R.drawable.table_row2);
    v2.setTextSize(15);
    v2.setPadding(1,10,1,10);
    android.widget.TableRow.LayoutParams p = new android.widget.TableRow.LayoutParams();
    p.leftMargin = dpToPixel(10,AdminAttendance.this); // right-margin = 10dp
    v2.setLayoutParams(p);
    r1.addView(v2);
}
                            else
                            {

                                TextView v2=new TextView(AdminAttendance.this);
                                v2.setText(s4.get(Math.min(s2.size(),s4.size())+i));
                                v2.setTextColor(Color.BLUE);
                                v2.setGravity(Gravity.CENTER);
                                v2.setPadding(1,10,1,10);
                                v2.setTextSize(15);
                                android.widget.TableRow.LayoutParams p = new android.widget.TableRow.LayoutParams();
                              //  p.leftMargin = dpToPixel(10,AdminAttendance.this); // right-margin = 10dp
                                v2.setLayoutParams(p);
                                v2.setBackgroundResource(R.drawable.table_row);
                                r1.addView(v2);

                                TextView v1=new TextView(AdminAttendance.this);
                                v1.setText("");
                                v1.setTextColor(Color.BLUE);
                                v1.setPadding(1,10,1,10);
                                v1.setGravity(Gravity.CENTER);
                                v1.setTextSize(15);
                                r1.addView(v1);
                            }


                            t1.addView(r1);}
                        }
same=s2;same2=s1;
                        same.retainAll(s4);
                        same2.retainAll(s3);
                        for(int i=0;i<same.size();i++)
                        {
                            combined.add(same.get(i)+"       UserID-> "+same2.get(i));
                        }


                        Log.d("ttt",same.toString());
                        Log.d("ttt",s2.toString());
                        Log.d("ttt",s4.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Log.d("ttt",statusCode+"");
                        Toast.makeText(AdminAttendance.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

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