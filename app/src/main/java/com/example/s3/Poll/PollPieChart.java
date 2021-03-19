package com.example.s3.Poll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.s3.DashBoard;
import com.example.s3.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static java.util.Collections.max;

public class PollPieChart extends DashBoard {

    EditText te;
    LottieAnimationView gi;
    TextView win;
    AnyChartView any;
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/PollResult",u="",p=""/*,u="Final Year",p="All Students*/;
    String[] category={"PavBhaji","MisalPav","MasalaDosa","Chinese","CholeBhature","PaniPuri","Mix Sabji","RagadaPattice"};
 //   ArrayList<Integer> count;
    int[] count2={2,4,3,1,5,8,1,2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View views=inflater.inflate(R.layout.activity_poll_pie_chart,null,false);
        drawerLayout.addView(views,1);
        any=findViewById(R.id.pie);
        win=findViewById(R.id.winner);
        //te=findViewById(R.id.te);
        gi=findViewById(R.id.conf);

      // gi.setRepeatCount(1);
        new Async().execute();

        //setupPieChart();
    }



    class Async extends AsyncTask<Void,Void,Void> {
        String query;
        int[] count={0,0,0,0,0,0,0,0};


        @Override
        protected Void doInBackground(Void... voids) {
           /* try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.90.174:3306/android2","andro","andro");
                Statement statement=connection.createStatement();

               ResultSet resultSet=statement.executeQuery("select * from poll");

                // count.add(Integer.valueOf(resultSet.getString(1)));

              //  count[1]=resultSet.getInt(1);
                while(resultSet.next()){
                    count[0]=resultSet.getInt(1);
                    count[1]=resultSet.getInt(2);
                    count[2]=resultSet.getInt(3);
                    count[3]=resultSet.getInt(4);
                    count[4]=resultSet.getInt(5);
                    count[5]=resultSet.getInt(6);
                    count[6]=resultSet.getInt(7);
                    count[7]=resultSet.getInt(8);
                }
            }
            catch (Exception e)
            {
                String err=e.toString();
                Log.d("tag", err);

                Toast.makeText(PollPieChart.this,err,Toast.LENGTH_SHORT).show();

                //  te.setText(e.toString());
            }*/
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
                        super.onSuccess(statusCode, headers, resultSet);
                        try {

                            count[0]=Integer.valueOf(resultSet.getString(0));
                            count[1]=resultSet.getInt(1);
                            count[2]=resultSet.getInt(2);
                            count[3]=resultSet.getInt(3);
                            count[4]=resultSet.getInt(4);
                            count[5]=resultSet.getInt(5);
                            count[6]=resultSet.getInt(6);
                            count[7]=resultSet.getInt(7);

                            Integer max=0,in=0;
                            Pie pie= AnyChart.pie();
                            pie.credits().text("Fest Poll");
                            List<DataEntry> dataEntries=new ArrayList<>();
                            for(int i=0;i<category.length;i++)
                                dataEntries.add(new ValueDataEntry(category[i],count[i]));
                            pie.data(dataEntries);
                            pie.palette(new String[]{"#0039e6", "#00ffff", "#00ff00", "#ffff00", "#ffa500", "#e60000","#ff33ff", "#6600cc"});
                            any.setChart(pie);

                            for(int i=0;i<count.length;i++)
                            {
                                if(max<count[i])
                                {
                                    in=i;
                                    max=count[i];

                                }
                            }
                            win.setText(category[in]);
                        } catch (Exception e) {
                            Log.d("ttt",e.toString());
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        //   Toast.makeText(NoticeBoard.this,"Success 2 ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);

                        // Toast.makeText(NoticeBoard.this,"Success 3",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                       // Toast.makeText(PollPieChart.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    //    Toast.makeText(PollPieChart.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                      //  Toast.makeText(PollPieChart.this,"Failllllllllllllll "+statusCode,Toast.LENGTH_SHORT).show();

                    }
                });


                //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";

            }
            catch (Exception e)
            {
                Log.d("ttt",e.toString());

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