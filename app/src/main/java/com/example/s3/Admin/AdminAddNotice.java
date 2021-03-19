package com.example.s3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3.R;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class AdminAddNotice extends AdminDashboard {

    Spinner sp,sp2;
    Button but;
    TextInputLayout title,sho,brief;
    String stitle,ssho,sbrief,ssp,ssp2,err;
    TextView t;
    EditText d;
    String da;
    RequestParams params;
    AsyncHttpClient client;
    String url="http://192.168.64.174:8080/Hostel/AddNotice";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View views=inflater.inflate(R.layout.activity_admin_add_notice,null,false);
        drawerLayout.addView(views,1);

        title=findViewById(R.id.ti);
        sho=findViewById(R.id.st);
        brief=findViewById(R.id.de);
        but=findViewById(R.id.but);
        sp=(Spinner)findViewById(R.id.spin);
        sp2=(Spinner)findViewById(R.id.spin2);
        t=findViewById(R.id.k);
        d=findViewById(R.id.due);

Log.d("ttt","1");
        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // updateLabel();
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                d.setText(sdf.format(myCalendar.getTime()));
            }

        };

        d.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AdminAddNotice.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final List<String> category=new ArrayList<>();
        final List<String> category2=new ArrayList<>();

        category.add("Warning");
        category.add("Fest");
        category.add("Social Event");
        category.add("General");
        category.add("Category Of Notice");
        final int size=category.size()-1;

        category2.add("First Year");
        category2.add("Second Year");
        category2.add("Third Year");
        category2.add("Fourth Year");
        category2.add("Fifth Year");
        category2.add("All Engineering Students");
        category2.add("Architecture Students");
        category2.add("All Students");
        category2.add("Choose Recipient");

        final int size2=category2.size()-1;

        Log.d("ttt","2");

        ArrayAdapter<String> ma=new ArrayAdapter<String>(AdminAddNotice.this, android.R.layout.simple_list_item_1,category){
            @Override
            public int getCount() {
               return(size);
            }
        };
        ma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ma);
        sp.setSelection(size);


        ArrayAdapter<String> ma2=new ArrayAdapter<String>(AdminAddNotice.this, android.R.layout.simple_list_item_1,category2){
            @Override
            public int getCount() {
                return(size2);
            }
        };
        ma2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(ma2);
        sp2.setSelection(size2);

sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      //  ((TextView)view).setTextColor(Color.GRAY);
        ((TextView)view).setTextSize(16);
        ssp=sp.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});

//ssp=sp.getSelectedItem().toString();
        Log.d("ttt","3");

sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView)view).setTextSize(16);
        ssp2=sp2.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});
        Log.d("ttt","4");

//ssp2=sp2.getSelectedItem().toString();
but.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        stitle=title.getEditText().getText().toString();
        ssho=sho.getEditText().getText().toString();
        sbrief=brief.getEditText().getText().toString();
        da=d.getText().toString();
        Log.d("ttt","6");

        new Async().execute();

    }
});

        Log.d("ttt","5");

    }

    class Async extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                params=new RequestParams();
                //string= Base64.encodeToString(bytes,Base64.DEFAULT);
                params.put("k1",stitle);
                params.put("k2",ssho);
                params.put("k3",sbrief);
                params.put("k4",ssp);
                params.put("k5",ssp2);
                params.put("k6",da);

                client=new AsyncHttpClient();

                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        // super.onSuccess(statusCode, headers, response);
                        //  test.setImageBitmap(bm);

                        Toast.makeText(AdminAddNotice.this,"Notice Added Successfully ",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                        // test.setImageBitmap(bm);

                        Toast.makeText(AdminAddNotice.this,"Notice Added Successfully ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //super.onSuccess(statusCode, headers, responseString);
                        //i=1;
                        //test.setImageBitmap(bm);
                        Toast.makeText(AdminAddNotice.this,"Notice Added Successfully ",Toast.LENGTH_SHORT).show();

                        Log.d("tag",responseString);
                        // bytes=Base64.decode(responseString,responseString.length());

                        //test.setImageBitmap(bm);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("tag",responseString);
                        // test.setImageBitmap(bm);
                        Toast.makeText(AdminAddNotice.this,"Notice Added Successfully ",Toast.LENGTH_SHORT).show();

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
        super.onBackPressed();
        Intent intent0 = new Intent(this, AdminDashboard.class);
        startActivity(intent0);
    }
}