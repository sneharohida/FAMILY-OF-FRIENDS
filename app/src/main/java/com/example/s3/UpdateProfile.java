package com.example.s3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s3.extra.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UpdateProfile extends DashBoard {
    String m_Text="";
    EditText hos;
    Button go,ret;
    String FDCNs,mnos,lgnos,fnos,ecs,pins,nums,bdas,majors,years;
    String names, lnames,mnames,FDNas,allergys,chrs,suds,sks,phos,diss,casts,unames,citys,landls,emaddrs,cnos,aadhars,ems, pas, rels, bgs, nas, fns, mns, lgs, addrs;
    EditText name, lname,mname,bda,major,year,FDNa,FDCN,allergy,chr,sud,sk,pho,dis,num,cast,pin,city,landl,emaddr,cno,ec, aadhar,rel, bg, na, fn, fno, mn, mno, lg, lgno, addr;
    AsyncHttpClient client;
    RequestParams params;
    SessionManager sm;
    String id="";
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

    private ArrayList<JSONArray> mul=new ArrayList<JSONArray>();

    String url="http://192.168.64.174:8080/Hostel/AdminProfile",url2="http://192.168.64.174:8080/Hostel/UpdateProfile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View views=inflater.inflate(R.layout.activity_update_profile,null,false);
        drawerLayout.addView(views,1);

        name =findViewById(R.id.firstname);
        num = findViewById(R.id.no);
        mname = findViewById(R.id.midname);
        lname = findViewById(R.id.lastname);
        aadhar = findViewById(R.id.aadhar);
        rel = findViewById(R.id.rel);
        cast =findViewById(R.id.cast);
        bg = findViewById(R.id.Blood);
        na = findViewById(R.id.nationality);
        fn = findViewById(R.id.Fname);
        fno = findViewById(R.id.Fno);
        mn =findViewById(R.id.Mname);
        mno = findViewById(R.id.MNum);
        lg = findViewById(R.id.guardian);
        lgno = findViewById(R.id.gnum);
        addr = findViewById(R.id.Paddress);
        pin = findViewById(R.id.pin);
        city = findViewById(R.id.city);
        landl = findViewById(R.id.Landl);
        emaddr = findViewById(R.id.em);
        ec = findViewById(R.id.ec);
        cno = findViewById(R.id.cno);
        FDNa = findViewById(R.id.FDNa);
        allergy = findViewById(R.id.allergy);
        FDCN = findViewById(R.id.FDCN);
        chr = findViewById(R.id.chr);
        sud = findViewById(R.id.sud);
        sk = findViewById(R.id.sk);
        pho = findViewById(R.id.pho);
        dis = findViewById(R.id.dis);
        bda = findViewById(R.id.bd);
        major=findViewById(R.id.major);
        year=findViewById(R.id.year);
        go = findViewById(R.id.signup);


        s1.clear();
        s2.clear();
        s3.clear();
        s4.clear();
        s5.clear();
        s6.clear();
        s7.clear();
        s8.clear();
        s9.clear();
        s10.clear();
        s11.clear();
        mul.clear();
        sm=new SessionManager(this);
        new Async().execute();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nums = num.getText().toString();
                names = name.getText().toString();
                mnames = mname.getText().toString();
                lnames = lname.getText().toString();
                aadhars =aadhar.getText().toString();
                rels = rel.getText().toString();
                casts = cast.getText().toString();
                nas = na.getText().toString();
                bdas=bda.getText().toString();

                fns = fn.getText().toString();
                fnos = fno.getText().toString();
                mns = mn.getText().toString();
                mnos = mno.getText().toString();
                lgs = lg.getText().toString();
                lgnos = lgno.getText().toString();

                addrs = addr.getText().toString();
                pins = pin.getText().toString();
                citys = city.getText().toString();
                landls = landl.getText().toString();
                emaddrs = emaddr.getText().toString();
                ecs = ec.getText().toString();

                cnos = cno.getText().toString();
                majors=major.getText().toString();
                years=year.getText().toString();

                FDNas = FDNa.getText().toString();
                bgs = bg.getText().toString();
                allergys = allergy.getText().toString();
                FDCNs = FDCN.getText().toString();
                chrs = chr.getText().toString();
                suds = sud.getText().toString();
                sks = sk.getText().toString();
                phos = pho.getText().toString();
                diss = dis.getText().toString();

                Log.d("ttt",addrs+" "+ecs);
                new Async2().execute();
            }
        });


    }
    class Async2 extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                client=new AsyncHttpClient();
                params=new RequestParams();

                params.put("k37",sm.getUserDetail().get(SessionManager.ID));

                params.put("k1",nums);
                params.put("k2",names);params.put("k3",mnames);params.put("k4",lnames);
                params.put("k5",bdas);params.put("k6",aadhars); params.put("k7",rels);
                params.put("k8",casts); params.put("k14",nas);

                params.put("k12",cnos); params.put("k13",years);params.put("k35",majors);

                params.put("k16",fns); params.put("k17",fnos);
                params.put("k18",mns); params.put("k19",mnos);
                params.put("k20",lgs); params.put("k21",lgnos);

                params.put("k22",addrs); params.put("k23",pins);
                params.put("k24",citys); params.put("k25",landls);
                params.put("k26",ecs); params.put("k27",emaddrs);

                params.put("k28",FDCNs); params.put("k29",FDNas);
                params.put("k30",chrs); params.put("k31",suds);
                params.put("k32",phos); params.put("k33",sks);
                params.put("k34",diss);params.put("k15",bgs);params.put("k36",allergys);

                client.post (url2,params,new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Toast.makeText(UpdateProfile.this,"Profile Updated ",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(UpdateProfile.this,"Profile Updated ",Toast.LENGTH_SHORT).show();
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


    class Async extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                client=new AsyncHttpClient();
                params=new RequestParams();
                params.put("k1",sm.getUserDetail().get(SessionManager.ID));
                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                     //   Toast.makeText(UpdateProfile.this,"Success 1 ",Toast.LENGTH_SHORT).show();
                        for(int i=0;i<11;i++) {
                            try {
                                mul.add(response.getJSONArray(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for(int i=0;i<11;i++)
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
                            }
                                if(i==8) {
                                    try {
                                        s9.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }if(i==9) {
                                try {
                                    s10.add(mul.get(i).getString(j));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                                if(i==10) {
                                    try {
                                        s11.add(mul.get(i).getString(j));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }

                        Log.d("ttt",s2.toString());
                        Log.d("ttt",s4.toString());

                        name.setText(s1.get(0));
                        num.setText(s1.get(4));
                        mname.setText(s1.get(1));
                        lname.setText(s1.get(2));
                        bda.setText(s1.get(3));
                        aadhar.setText(s1.get(5));
                        rel.setText(s1.get(6));
                        cast.setText(s1.get(7));
                        na.setText(s1.get(8));

                        fn.setText(s2.get(0));
                        fno.setText(s2.get(1));
                        mn.setText(s2.get(2));
                        mno.setText(s2.get(3));
                        lg.setText(s2.get(4));
                        lgno.setText(s2.get(5));

                        addr.setText(s3.get(0));
                        pin.setText(s3.get(1));
                        city.setText(s3.get(2));
                        landl.setText(s3.get(3));
                        emaddr.setText(s3.get(4));
                        ec.setText(s3.get(5));

                        cno.setText(s4.get(0));
                        major.setText(s4.get(1));
                        year.setText(s4.get(2));

                        bg.setText(s5.get(0));
                        FDNa.setText(s5.get(1));
                        FDCN.setText(s5.get(2));

                        allergy.setText(s11.toString().replaceAll("\\[", "").replaceAll("\\]",""));
                        chr.setText(s6.toString().replaceAll("\\[", "").replaceAll("\\]",""));
                        sud.setText(s7.toString().replaceAll("\\[", "").replaceAll("\\]",""));
                        sk.setText(s8.toString().replaceAll("\\[", "").replaceAll("\\]",""));
                        pho.setText(s9.toString().replaceAll("\\[", "").replaceAll("\\]",""));
                        dis.setText(s10.toString().replaceAll("\\[", "").replaceAll("\\]",""));



                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        Log.d("ttt",""+statusCode);
                     //   Toast.makeText(UpdateProfile.this,"Failll "+statusCode,Toast.LENGTH_SHORT).show();

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
}