package com.example.s3.Notice;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s3.Adapter.NoticeAdapter;
import com.example.s3.Attendancee.Attendance;
import com.example.s3.DashBoard;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class NoticeBoard extends DashBoard implements NoticeAdapter.OnNoteListener {
    private ArrayList<NoticeItem> exampleList;
    //public ArrayList<ArrayList<ArrayList<String>>> send=new ArrayList<ArrayList<ArrayList<String>>>();

    private ArrayList<String> s1=new ArrayList<>();
    private ArrayList<String> s2=new ArrayList<>();
    private ArrayList<String> s3=new ArrayList<>();
    private ArrayList<String> s4=new ArrayList<>();

    private ArrayList<JSONArray> mul=new ArrayList<JSONArray>();


    private ImageView back;
    private RecyclerView mRecyclerView;
    private NoticeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;
    TextView t;
    RequestParams params;
    AsyncHttpClient client;
    Integer i=0;
    String url="http://192.168.64.174:8080/Hostel/Notice",u="",p=""/*,u="Final Year",p="All Students*/;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view=inflater.inflate(R.layout.activity_notice_board,null,false);
drawerLayout.addView(view,1);
     //   setContentView(R.layout.activity_notice_board);
        exampleList = new ArrayList<>();
        SessionManager sm=new SessionManager(this);
        HashMap<String,String> hm=sm.getUserDetail();

          u=hm.get(SessionManager.MAJOR);
         p=hm.get(SessionManager.YEAR);
       // t=findViewById(R.id.t);




        new Async2().execute();

    }



    class Async2 extends AsyncTask<Void,Void,Void> {
        String err="";

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            s1.clear();
            s2.clear();
            s3.clear();
            s4.clear();
            mul.clear();
            try {
                params=new RequestParams();
                params.put("k1",u);
                params.put("k2",p);
                    client=new AsyncHttpClient();
                client.post (url,params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

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

                        for(int i=0;i<s2.size();i++) {
                            if (s3.get(i).equals("Social Event"))
                                exampleList.add(new NoticeItem(R.drawable.tree, s1.get(i), s4.get(i)));
                            else if (s3.get(i).equals("Fest"))
                                exampleList.add(new NoticeItem(R.drawable.party, s1.get(i), s4.get(i)));
                            else if (s3.get(i).equals("Warning"))
                                exampleList.add(new NoticeItem(R.drawable.high, s1.get(i), s4.get(i)));
                            else if (s3.get(i).equals("General"))
                                exampleList.add(new NoticeItem(R.drawable.warn, s1.get(i), s4.get(i)));
                        }

                        buildRecyclerView();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                  //      Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        i=1;
                  //      Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    //    Toast.makeText(NoticeBoard.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

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
String err="";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //  Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android","andro","andro");
                Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android2","andro","andro");
                Statement statement=connection.createStatement();
               // Statement statement2=connection.createStatement();
                statement.executeUpdate("delete from notice where DueDate<curDate()");
                ResultSet resultSet=statement.executeQuery("select * from notice where Recipient='Final Year' or Recipient='All Students'");
                while(resultSet.next()){
                   // records+=resultSet.getString(1)+" "+resultSet.getString(2)+resultSet.getString(3)+" "+resultSet.getString(4)+resultSet.getString(5)+"\n";
                    if(resultSet.getString(4).equals("Social Event"))
                    exampleList.add(new NoticeItem(R.drawable.tree, resultSet.getString(1), resultSet.getString(2)));
                   else
                        exampleList.add(new NoticeItem(R.drawable.warn, resultSet.getString(1), resultSet.getString(2)));

                   s1.add(resultSet.getString(1));
                    s2.add(resultSet.getString(3));
                    s3.add(resultSet.getString(4));

                    //send.add(resultSet.getString(1), resultSet.getString(2), resultSet.getString(4));



                }
              //  Toast.makeText(NoticeBoard.this,"Success  1 "+exampleList.get(0).toString(),Toast.LENGTH_SHORT).show();
                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                err=e.toString();
               // t.setText(err);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            buildRecyclerView();


            super.onPostExecute(aVoid);
        }
    }


    public void changeItem(int position, String text) {
            exampleList.get(position).changeText1(text);
            mAdapter.notifyItemChanged(position);
        }

        public void buildRecyclerView() {
            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new NoticeAdapter(exampleList,this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);



          /*  mAdapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    changeItem(position, "Clicked");
                }
            });*/
        }




    @Override
    public void onNoteClick(int position) {
      //  exampleList.get(position);
        Intent i=new Intent(this, NoticeDetail.class);
        i.putExtra("Position",position);
        i.putExtra("s1", s1);
      i.putExtra("s2", s2);
      i.putExtra("s3", s3);


        startActivity(i);
    }

    public void onClick(View view){
        Intent i=new Intent(this,NoticeDetail.class);
       // i.putExtra("Note", String.valueOf(exampleList.get(position)));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
