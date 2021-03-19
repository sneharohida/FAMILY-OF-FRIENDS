package com.example.s3.Complaint;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.s3.DashBoard;
import com.example.s3.NightOut.NightOut;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class Generic_complaint extends DashBoard {

TextInputLayout ho_no,ro_no,fl_no,mess_no,was_no,st_na,st_role,desc;
String ho_nos,ro_nos,fl_nos,mess_nos,was_nos,st_nas,st_roles,descs,uname,bedno="",bmb,sub;
Integer priority;
CheckBox chk;
Button but;
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/Complaint";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_generic_complaint,null,false);
        drawerLayout.addView(views,1);

        Intent intent=getIntent();
        bmb=intent.getStringExtra("boom");
        sub=intent.getStringExtra("sub");
        priority=intent.getIntExtra("priority",3);

        SessionManager sm=new SessionManager(this);
        HashMap<String,String> hm=sm.getUserDetail();

        uname=hm.get(SessionManager.USER_NAME);


        ho_no=findViewById(R.id.hos_num);
        ro_no=findViewById(R.id.room_num);
        fl_no=findViewById(R.id.floor_num);
        mess_no=findViewById(R.id.mess_num);
        was_no=findViewById(R.id.washroom);
        st_na=findViewById(R.id.staffname);
        st_role=findViewById(R.id.staff_role);
        desc=findViewById(R.id.reason);
        chk=findViewById(R.id.chk);
        but=findViewById(R.id.button);

but.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ho_nos=ho_no.getEditText().getText().toString();
        ro_nos=ro_no.getEditText().getText().toString();
        fl_nos=fl_no.getEditText().getText().toString();
        mess_nos=mess_no.getEditText().getText().toString();
        was_nos=was_no.getEditText().getText().toString();
        st_nas=st_na.getEditText().getText().toString();
        st_roles=st_role.getEditText().getText().toString();
        descs=desc.getEditText().getText().toString();

       if(chk.isChecked()){
            uname="Anonymous";
            bedno="NA";
        }

 new Async().execute();

    }
});

    }


    class Async extends AsyncTask<Void,Void,Void> {


        //bytes=stream.toByteArray();
        //ByteArrayOutputStream stream;
        // byte[] bytes=stream.toByteArray();
        String string;

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params=new RequestParams();
                //string= Base64.encodeToString(bytes,Base64.DEFAULT);
                params.put("k1",ho_nos);
                params.put("k2",fl_nos);
                params.put("k3",ro_nos);
                params.put("k4",mess_nos);
                params.put("k5",was_nos);
                params.put("k6",st_nas);
                params.put("k7",st_roles);
                params.put("k8",descs);
                params.put("k9",uname);
                params.put("k10",priority);
                params.put("k11",bedno);
                params.put("k12",bmb);
                params.put("k13",sub);


                  Log.d("tag",bmb+" "+sub);

                client=new AsyncHttpClient();

                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        // super.onSuccess(statusCode, headers, response);
                        //  test.setImageBitmap(bm);

                        Toast.makeText(Generic_complaint.this,"Complaint Successfully Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Generic_complaint.this, CF2.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                        // test.setImageBitmap(bm);

                        Toast.makeText(Generic_complaint.this,"Complaint Successfully Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Generic_complaint.this, CF2.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //super.onSuccess(statusCode, headers, responseString);
                        //i=1;
                        //test.setImageBitmap(bm);
                     //   Toast.makeText(Generic_complaint.this,"Success 3 ",Toast.LENGTH_SHORT).show();
                        Toast.makeText(Generic_complaint.this,"Complaint Successfully Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Generic_complaint.this, CF2.class);
                        startActivity(intent);
                        Log.d("tag",responseString);
                        // bytes=Base64.decode(responseString,responseString.length());

                        //test.setImageBitmap(bm);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                 //       Log.d("tag",responseString);
                        // test.setImageBitmap(bm);
                        Toast.makeText(Generic_complaint.this,"Complaint Successfully Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Generic_complaint.this, CF2.class);
                        startActivity(intent);
                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                Log.d("tag",e.toString());

                //err=e.toString();
                // t.setText(err);
            }
            super.onPostExecute(aVoid);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Generic_complaint.this, CF2.class);
        startActivity(intent);
    }
}