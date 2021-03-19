package com.example.s3.Admin;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.s3.Attendancee.Attendance;
import com.example.s3.Adapter.ComplaintAdapter;
import com.example.s3.Complaint.ComplaintDetail;
import com.example.s3.Complaint.ComplaintItem;
import com.example.s3.R;
import com.example.s3.extra.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class AdminComplaint extends AdminDashboard implements ComplaintAdapter.OnNoteListener,ComplaintAdapter.ComplaintInterface {
    private ArrayList<ComplaintItem> exampleList;
    //public ArrayList<ArrayList<ArrayList<String>>> send=new ArrayList<ArrayList<ArrayList<String>>>();

    private ArrayList<String> s1=new ArrayList<>();
    private ArrayList<String> s2=new ArrayList<>();
    private ArrayList<String> s3=new ArrayList<>();
    private ArrayList<String> s4=new ArrayList<>();
    private ArrayList<String> s5=new ArrayList<>();
    private ArrayList<String> s6=new ArrayList<>();
    private ArrayList<String> s7=new ArrayList<>();
    private ArrayList<String> s8=new ArrayList<>();
    private ArrayList<String> s9=new ArrayList<>();
    private ArrayList<String> s10=new ArrayList<>();
    private ArrayList<String> s11=new ArrayList<>();
    private ArrayList<String> s12=new ArrayList<>();
    private ArrayList<String> s13=new ArrayList<>();
    private ArrayList<String> s14=new ArrayList<>();
    private ArrayList<JSONArray> mul=new ArrayList<JSONArray>();

    private RecyclerView mRecyclerView;
    private ComplaintAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView nxt;
    RequestParams params;
    AsyncHttpClient client;
    Integer i=0;
    String url="http://192.168.64.174:8080/Hostel/AddComplaint",u="",p="";
    String id="";
    String url2="http://192.168.64.174:8080/Hostel/DeleteComplaint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_admin_complaint,null,false);
        drawerLayout.addView(views,1);

        exampleList = new ArrayList<>();
        SessionManager sm=new SessionManager(this);
        HashMap<String,String> hm=sm.getUserDetail();

        u=hm.get(SessionManager.MAJOR);
        p=hm.get(SessionManager.YEAR);
        // t=findViewById(R.id.t);

        nxt=findViewById(R.id.r);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminComplaint.this, Attendance.class);
                startActivity(i);
            }
        });


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

            try {
                params=new RequestParams();
                client=new AsyncHttpClient();
                client.post (url,params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

                        for(int i=0;i<14;i++) {
                            try {
                                mul.add(response.getJSONArray(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        for(int i=0;i<14;i++)
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
                                if(i==4) {
                                    try {
                                        s5.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(i==5) {
                                    try {
                                        s6.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }if(i==6) {
                                try {
                                    s7.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }if(i==7) {
                                try {
                                    s8.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }if(i==8) {
                                try {
                                    s9.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                                if(i==9) {
                                    try {
                                        s10.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }if(i==10) {
                                try {
                                    s11.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }if(i==11) {
                                try {
                                    s12.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                                if(i==12) {
                                    try {
                                        s13.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(i==13) {
                                    try {
                                        s14.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }

                        for(int i=0;i<s1.size();i++) {
                            if(s13.get(i).equals("1"))
                                exampleList.add(new ComplaintItem(R.drawable.high, s1.get(i),s2.get(i), s3.get(i),s4.get(i), s5.get(i),s6.get(i), s7.get(i),s8.get(i), s9.get(i),s10.get(i), s11.get(i),s12.get(i),s13.get(i),s14.get(i)));
                            else if(s13.get(i).equals("2"))
                                exampleList.add(new ComplaintItem(R.drawable.medium, s1.get(i),s2.get(i), s3.get(i),s4.get(i), s5.get(i),s6.get(i), s7.get(i),s8.get(i), s9.get(i),s10.get(i), s11.get(i),s12.get(i),s13.get(i),s14.get(i)));
                            else
                                exampleList.add(new ComplaintItem(R.drawable.lowp, s1.get(i),s2.get(i), s3.get(i),s4.get(i), s5.get(i),s6.get(i), s7.get(i),s8.get(i), s9.get(i),s10.get(i), s11.get(i),s12.get(i),s13.get(i),s14.get(i)));

                        }

                        buildRecyclerView();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                     //   Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        i=1;
                       // Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                      //  Toast.makeText(AdminComplaint.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

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

    @Override
    public void deleteItem(int position) {
        id =exampleList.get(position).getid();
        exampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        s1.remove(position);
        s2.remove(position);
        s3.remove(position);
        s4.remove(position);
        s5.remove(position);
        s6.remove(position);
        s7.remove(position);
        s8.remove(position);
        s9.remove(position);
        s10.remove(position);
        s11.remove(position);
        s12.remove(position);
        s13.remove(position);
       // Toast.makeText(AdminComplaint.this,position,Toast.LENGTH_SHORT).show();

        new Async().execute();

    }

    class Async extends AsyncTask<Void,Void,Void> {
        String err="";

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            params=new RequestParams();
            params.put("k1",id);
            client=new AsyncHttpClient();
            client.post (url2,params,new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    super.onSuccess(statusCode, headers, responseString);
                    i=1;
                 //   Toast.makeText(AdminComplaint.this,"Success 3",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
               //     Toast.makeText(AdminComplaint.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                }
            });

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
        mAdapter = new ComplaintAdapter(exampleList,this,this);
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
        id =exampleList.get(position).getid();
        Intent i=new Intent(this, ComplaintDetail.class);
//        i.putExtra("id",id);
        i.putExtra("id",position);
        i.putExtra("s3", s3);
        i.putExtra("s4", s4);
        i.putExtra("s5", s5);
        i.putExtra("s6", s6);
        i.putExtra("s7", s7);
        i.putExtra("s8", s8);
        i.putExtra("s9", s9);
        i.putExtra("s10", s10);
        i.putExtra("s12", s12);
        i.putExtra("s11", s11);
        i.putExtra("s13", s13);
        //i.putExtra("el",exampleList);


        startActivity(i);
    }

    public void onClick(View view){
        Intent i=new Intent(this,ComplaintDetail.class);
        // i.putExtra("Note", String.valueOf(exampleList.get(position)));
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent0 = new Intent(this, AdminDashboard.class);
        startActivity(intent0);
    }
}
