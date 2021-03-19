package com.example.s3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

import com.example.s3.Notice.NoticeBoard;
import com.example.s3.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminPollSelection extends AdminDashboard {
    CheckBox m1,m2,m3,m4,m5,m6,m7,m8;
    ArrayList<String> menu=new ArrayList<String>();
    Button sub;
    Integer a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
    String err="";
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/AdminMenu",u="",p=""/*,u="Final Year",p="All Students*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View views=inflater.inflate(R.layout.activity_admin_poll_selection,null,false);
        drawerLayout.addView(views,1);

        m1=findViewById(R.id.one);
        m2=findViewById(R.id.two);
        m3=findViewById(R.id.th);
        m4=findViewById(R.id.fo);
        m5=findViewById(R.id.fi);
        m6=findViewById(R.id.si);
        m7=findViewById(R.id.sev);
        m8=findViewById(R.id.ei);
        sub=findViewById(R.id.Submit);
        //menu.clear();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(menu.size()!=0)
                   menu.clear();
                if(m1.isChecked())
                    menu.add("PavBhaji");
                if(m2.isChecked())
                    menu.add("MisalPav");
                if(m3.isChecked())
                    menu.add("MasalaDosa");
                if(m4.isChecked())
                    menu.add("Chinese");
                if(m5.isChecked())
                    menu.add("CholeBhature");
                if(m6.isChecked())
                    menu.add("PaniPuri");
                if(m7.isChecked())
                    menu.add("MixSabji");
                if(m8.isChecked())
                    menu.add("RagadaPattice");

                Log.d("ttt",menu.toString());
                 final AlertDialog dialog = new AlertDialog.Builder(AdminPollSelection.this).setTitle("Confirm the menu to proceed")
                        .setMessage(menu.toString())
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("No", null)
                        .show();

                Button no = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        menu.clear();

                    }
                });

                Button yes = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ttt","0");
a=b=c=d=e=f=g=h=0;
                        if(m1.isChecked())
                            a++;
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

                        new Async().execute();
                    }
                });




            }
        });

    }
    class Async extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            /*try {
                Class.forName("com.mysql.jdbc.Driver");
                //  Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android","andro","andro");
                Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.90.174:3306/android2","andro","andro");
                Statement statement=connection.createStatement();

                statement.executeUpdate("delete from enablemenu;");
                String query="insert into enablemenu values (?,?,?,?,?,?,?,?,'0')";
                Log.d("ttt","2");

                PreparedStatement pat=connection.prepareStatement(query);
                pat.setInt(1,a);
                pat.setInt(2,b);
                pat.setInt(3,c);
                pat.setInt(4,d);
                pat.setInt(5,e);
                pat.setInt(6,f);
                pat.setInt(7,g);
                pat.setInt(8,h);
             //   pat.setInt(9,a);
                pat.execute();
                pat.close();

            }
            catch (Exception e)
            {
                err=e.toString();
            }*/
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
                client.post (url,params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(AdminPollSelection.this," Menu Selected Successfully!!",Toast.LENGTH_SHORT).show();
                        Intent intent10 = new Intent(AdminPollSelection.this, AdminDashboard.class);
                        startActivity(intent10);

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(AdminPollSelection.this," Menu Selected Successfully!!",Toast.LENGTH_SHORT).show();
                        Intent intent10 = new Intent(AdminPollSelection.this, AdminDashboard.class);
                        startActivity(intent10);
                        //   Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        Toast.makeText(AdminPollSelection.this," Menu Selected Successfully!!",Toast.LENGTH_SHORT).show();
                        Intent intent10 = new Intent(AdminPollSelection.this, AdminDashboard.class);
                        startActivity(intent10);

                        // Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(AdminPollSelection.this," Menu Selected Successfully!!",Toast.LENGTH_SHORT).show();
                        Intent intent10 = new Intent(AdminPollSelection.this, AdminDashboard.class);
                        startActivity(intent10);

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
    public void onBackPressed() {
        super.onBackPressed();

    }
}