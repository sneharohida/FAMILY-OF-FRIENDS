package com.example.s3.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminViewProfile extends AdminDashboard {
String m_Text="";
EditText hos;
Button go,ret;
    TextView name, lname,mname,bda,major,year,FDNa,FDCN,allergy,chr,sud,sk,pho,dis,num,cast,pin,city,landl,emaddr,cno,ec, aadhar,rel, bg, na, fn, fno, mn, mno, lg, lgno, addr;
    AsyncHttpClient client;
    RequestParams params;
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

    String url="http://192.168.64.174:8080/Hostel/AdminProfile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_admin_view_profile,null,false);
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
        ret =findViewById(R.id.ret);

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
        opendialog();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminViewProfile.this, AdminDashboard.class);
                startActivity(intent2);
            }
        });
    }

    private void opendialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_profile, null);
        hos = view.findViewById(R.id.hosnum);
        Button b1 = view.findViewById(R.id.butto);
        builder.setView(view).setTitle("Enter UserID to view profile");

        final AlertDialog dialog = builder.create();

        dialog.show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_Text = hos.getText().toString();
if(!m_Text.equals("")) {
    dialog.cancel();
    new Async().execute();
}
else
    Toast.makeText(AdminViewProfile.this, "please provide details", Toast.LENGTH_SHORT).show();

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
                client=new AsyncHttpClient();
                params=new RequestParams();
                params.put("k1",m_Text);
                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                       //  Toast.makeText(AdminViewProfile.this,"Success 1 ",Toast.LENGTH_SHORT).show();
                        Log.d("ttt",m_Text);
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
                        Toast.makeText(AdminViewProfile.this,"Failll "+statusCode,Toast.LENGTH_SHORT).show();

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
        Intent intent0 = new Intent(this, AdminDashboard.class);
        startActivity(intent0);
    }
}