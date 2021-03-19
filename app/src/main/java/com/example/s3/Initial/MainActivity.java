package com.example.s3.Initial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3.R;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Fragment {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    String ssp="",ssp2="",di;
    TextInputLayout name, lname,mname,FDNa,FDCN,allergy,chr,sud,sk,pho,dis,num,cast,uname,pin,city,landl,emaddr,cno,ec, aadhar,em, pa, rel, bg, na, fn, fno, mn, mno, lg, lgno, addr;
    Button go;
    EditText bda;
    Spinner sp1,sp2;
    String currentPhotoPath;
    String FDCNs,mnos,lgnos,fnos,ecs,pins,nums,bdas;
    String names, lnames,mnames,FDNas,allergys,chrs,suds,sks,phos,diss,casts,unames,citys,landls,emaddrs,cnos,aadhars,ems, pas, rels, bgs, nas, fns, mns, lgs, addrs;
  //  ImageButton selectedImage,test;
    private OnDateSetListener mDateSetListener;
    public static final int REQUEST_CODE = 11;
    byte[] bytes;
    final String warden = "9834950004";
    ByteArrayOutputStream stream=new ByteArrayOutputStream();
    RequestParams params;
    AsyncHttpClient client;
    Bitmap bm=null;
    Bitmap b = null;
    RadioButton aa,ab,da,db,pha,phb,sa,sb,sda,sdb,cha,chb;
    RadioGroup rga,rgc,rgsd,rgp,rgd,rgs;

    String url="http://192.168.64.174:8080/Hostel/Sign";

    final Calendar myCalendar = Calendar.getInstance();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);
        num = root.findViewById(R.id.no);
        name = root.findViewById(R.id.firstname);
        mname = root.findViewById(R.id.midname);
        lname = root.findViewById(R.id.lastname);
        aadhar = root.findViewById(R.id.aadhar);
        rel = root.findViewById(R.id.rel);
        cast = root.findViewById(R.id.cast);
        uname = root.findViewById(R.id.username);
        em = root.findViewById(R.id.Email);
        pa = root.findViewById(R.id.password);
        bg = root.findViewById(R.id.Blood);
        na = root.findViewById(R.id.nationality);
        fn = root.findViewById(R.id.Fname);
        fno = root.findViewById(R.id.Fno);
        mn = root.findViewById(R.id.Mname);
        mno = root.findViewById(R.id.MNum);
        lg = root.findViewById(R.id.guardian);
        lgno = root.findViewById(R.id.gnum);
        addr = root.findViewById(R.id.Paddress);
        pin = root.findViewById(R.id.pin);
        city = root.findViewById(R.id.city);
        landl = root.findViewById(R.id.Landl);
        emaddr = root.findViewById(R.id.em);
        ec = root.findViewById(R.id.ec);
        cno = root.findViewById(R.id.cno);
        FDNa = root.findViewById(R.id.FDNa);
        allergy = root.findViewById(R.id.allergy);
        FDCN = root.findViewById(R.id.FDCN);
        chr = root.findViewById(R.id.chr);
        sud = root.findViewById(R.id.sud);
        sk = root.findViewById(R.id.sk);
        pho = root.findViewById(R.id.pho);
        dis = root.findViewById(R.id.dis);
        bda = root.findViewById(R.id.bd);
        sp1=root.findViewById(R.id.spin);
        sp2=root.findViewById(R.id.spin2);
        go = root.findViewById(R.id.signup);
        aa = root.findViewById(R.id.aa);
        ab = root.findViewById(R.id.ab);
        da = root.findViewById(R.id.da);
        db=root.findViewById(R.id.db);
        sa=root.findViewById(R.id.sa);
        sb = root.findViewById(R.id.sb);
        pha = root.findViewById(R.id.pha);
        phb = root.findViewById(R.id.phb);
        sda = root.findViewById(R.id.sda);
        sdb=root.findViewById(R.id.sdb);
        cha=root.findViewById(R.id.cha);
        chb = root.findViewById(R.id.chb);
        rga = root.findViewById(R.id.rga);
        rgd = root.findViewById(R.id.rgd);
        rgp = root.findViewById(R.id.rgp);
        rgc = root.findViewById(R.id.rgc);
        rgs = root.findViewById(R.id.rgs);
        rgsd = root.findViewById(R.id.rgsd);


        allergy.setEnabled(false);
        sk.setEnabled(false);
        chr.setEnabled(false);
        sud.setEnabled(false);
        pho.setEnabled(false);
        dis.setEnabled(false);

        rga.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.aa)
                {
                    allergy.setEnabled(true);
                }

                if(checkedId==R.id.ab)
                {
                    allergy.setEnabled(false);
                }


            }
        });
        rgs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.sa)
                {
                    sk.setEnabled(true);
                }

                if(checkedId==R.id.sb)
                {
                    sk.setEnabled(false);
                }


            }
        });rgsd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.sda)
                {
                    sud.setEnabled(true);
                }

                if(checkedId==R.id.sdb)
                {
                    sud.setEnabled(false);
                }


            }
        });rgc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.cha)
                {
                    chr.setEnabled(true);
                }

                if(checkedId==R.id.chb)
                {
                    chr.setEnabled(false);
                }


            }
        });rgd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.da)
                {
                    dis.setEnabled(true);
                }

                if(checkedId==R.id.db)
                {
                    dis.setEnabled(false);
                }


            }
        });rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.pha)
                {
                    pho.setEnabled(true);
                }

                if(checkedId==R.id.phb)
                {
                    pho.setEnabled(false);
                }


            }
        });

        di= Settings.Secure.getString(getActivity().getContentResolver(),Settings.Secure.ANDROID_ID);

        final List<String> category=new ArrayList<>();
        category.add("First Year");
        category.add("Second Year");
        category.add("Third Year");
        category.add("Fourth Year");
        category.add("Fifth Year");
        category.add("Current Academic Year");
        final int size=category.size()-1;
        ArrayAdapter<String> ma=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,category){
            @Override
            public int getCount() {
                return(size);
            }
        };
        ma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(ma);
        sp2.setSelection(size);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  ((TextView)view).setTextColor(Color.GRAY);
                ((TextView)view).setTextSize(16);
                ssp=sp2.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final List<String> category2=new ArrayList<>();
        category2.add("Engineering");
        category2.add("Architecture");
        category2.add("Select Major");
        final int size2=category2.size()-1;
        ArrayAdapter<String> ma2=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,category2){
            @Override
            public int getCount() {
                return(size2);
            }
        };
        ma2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ma2);
        sp1.setSelection(size2);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  ((TextView)view).setTextColor(Color.GRAY);
                ((TextView)view).setTextSize(16);
                ssp2=sp1.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                bda.setText(sdf.format(myCalendar.getTime()));
            }

        };
        bda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nums = num.getEditText().getText().toString();
                 names = name.getEditText().getText().toString();
                mnames = mname.getEditText().getText().toString();
                lnames = lname.getEditText().getText().toString();
                aadhars =aadhar.getEditText().getText().toString();
                rels = rel.getEditText().getText().toString();
                casts = cast.getEditText().getText().toString();
                unames = uname.getEditText().getText().toString();
                ems = em.getEditText().getText().toString();
                pas = pa.getEditText().getText().toString();
                bgs = bg.getEditText().getText().toString();
                nas = na.getEditText().getText().toString();
                fns = fn.getEditText().getText().toString();
                fnos = fno.getEditText().getText().toString();
                mns = mn.getEditText().getText().toString();
                mnos = mno.getEditText().getText().toString();
                lgs = lg.getEditText().getText().toString();
                lgnos = lgno.getEditText().getText().toString();
                addrs = addr.getEditText().getText().toString();
                pins = pin.getEditText().getText().toString();
                citys = city.getEditText().getText().toString();
                landls = landl.getEditText().getText().toString();
                emaddrs = emaddr.getEditText().getText().toString();
                ecs = ec.getEditText().getText().toString();
                cnos = cno.getEditText().getText().toString();
                FDNas = FDNa.getEditText().getText().toString();
                allergys = allergy.getEditText().getText().toString();
                FDCNs = FDCN.getEditText().getText().toString();
                chrs = chr.getEditText().getText().toString();
                suds = sud.getEditText().getText().toString();
                sks = sk.getEditText().getText().toString();
                phos = pho.getEditText().getText().toString();
                diss = dis.getEditText().getText().toString();
                //ssp=sp1.getSelectedItem().toString();
                //ssp2=sp2.getSelectedItem().toString();
                bdas=bda.getText().toString();

                SmsManager mySmsManager = SmsManager.getDefault();
                String Selfmessage = "Welcome to the Hostel " + names + " .Thanks for registering.";
                String Wardenmessage = "" + names + " has registered for the hostel sucessfully.";

                mySmsManager.sendTextMessage(nums, null, Selfmessage, null, null);
                mySmsManager.sendTextMessage(warden, null, Wardenmessage, null, null);

             //   Bitmap b=((BitmapDrawable)selectedImage.getDrawable()).getBitmap();

//                b.compress(Bitmap.CompressFormat.PNG,0,stream);
               // Log.d("tag",b.toString());

                //bytes=stream.toByteArray();

                //new Async().execute();
                  //            new Async2().execute();
               // Log.d("tag1",b.toString());
                new Async4().execute();
               // Log.d("tag1",b.toString());

             //   Intent i=new Intent(getActivity(),NoticeBoard.class);
               // startActivity(i);


            }
        });


       /* selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Choose Option")
                        .setPositiveButton("Gallery", null)
                        .setNegativeButton("Camera", null)
                        .show();

                Button camera = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        askCameraPermissions();

                    }
                });

                Button gallery = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
                        //Toast.makeText(getActivity(), "Gallery", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });*/

        return root;


    }

    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            dispatchTakePictureIntent();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getActivity(), "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                b = (Bitmap) data.getExtras().get("data");
               // selectedImage.setImageBitmap(b);
             //   File f = new File(currentPhotoPath);
               // b.compress(Bitmap.CompressFormat.PNG,0,stream);

              //  b=((BitmapDrawable)selectedImage.getDrawable()).getBitmap();

               // b.compress(Bitmap.CompressFormat.PNG,0,stream);
        //bytes=stream.toByteArray();
//String string= Base64.encodeToString(bytes,Base64.DEFAULT);


              // new Async().execute();
               //new Async2().execute();



                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
               // Uri contentUri = Uri.fromFile(f);
            //    mediaScanIntent.setData(contentUri);
                getActivity().sendBroadcast(mediaScanIntent);
            }
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                // selectedImage.setImageURI(contentUri);

                try {
                    b = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Bitmap b = (Bitmap) data.getExtras().get("data");
               // selectedImage.setImageBitmap(b);
                //b.compress(Bitmap.CompressFormat.PNG,0,stream);

                //  File f = new File(currentPhotoPath);

              //  b.compress(Bitmap.CompressFormat.PNG, 0, stream);
                //bytes =stream.toByteArray();
//String string= Base64.encodeToString(bytes,Base64.DEFAULT);

//new Async().execute();
                //              new Async2().execute();

            }

        }


    }


    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageFile = new File(pictureDirectory, "kkk");

        Uri pictureUri = FileProvider.getUriForFile(getActivity(),
                "com.example.android.fileprovider",
                imageFile);
        currentPhotoPath = imageFile.getAbsolutePath();
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);

    }




    class Async4 extends AsyncTask<Void,Void,Void>{


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
                params.put("k1",nums);
                params.put("k2",names);
                params.put("k3",mnames);
                params.put("k4",lnames);
                params.put("k5",bdas);
                params.put("k6",aadhars); params.put("k7",rels);
                params.put("k8",casts); params.put("k9",unames);
                params.put("k10",ems); params.put("k11",pas);
                params.put("k12",cnos); params.put("k13",ssp2);
                params.put("k14",nas); params.put("k15",bgs);
                params.put("k16",fns); params.put("k17",fnos);
                params.put("k18",mns); params.put("k19",mnos);
                params.put("k20",lgs); params.put("k21",lgnos);
                params.put("k22",addrs); params.put("k23",pins);
                params.put("k24",citys); params.put("k25",landls);
                params.put("k26",ecs); params.put("k27",emaddrs);
                params.put("k28",FDCNs); params.put("k29",FDNas);
                params.put("k30",chrs); params.put("k31",suds);
                params.put("k32",phos); params.put("k33",sks);
                params.put("k34",diss);params.put("k35",ssp);
                params.put("k36",allergys);params.put("k37",di.toString());


                //  Log.d("tag",string);

                client=new AsyncHttpClient();

                client.post (url,params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        // super.onSuccess(statusCode, headers, response);
                      //  test.setImageBitmap(bm);

                        Toast.makeText(getActivity(),"Registered Successfully ",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                       // test.setImageBitmap(bm);

                        Toast.makeText(getActivity(),"Registered Successfully ",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //super.onSuccess(statusCode, headers, responseString);
                        //i=1;
                        //test.setImageBitmap(bm);
                        Toast.makeText(getActivity(),"Registered Successfully ",Toast.LENGTH_SHORT).show();

                        Log.d("tag",responseString);
                       // bytes=Base64.decode(responseString,responseString.length());

                        //test.setImageBitmap(bm);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        // super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("tag",responseString);
                      // test.setImageBitmap(bm);
                        Toast.makeText(getActivity(),"Registered Successfully ",Toast.LENGTH_SHORT).show();

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
    String err="";
    byte[] bytes=stream.toByteArray();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //  Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android","andro","andro");
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android2","andro","andro");

            Statement statement=connection.createStatement();
            String query="insert into test values (?)";
            PreparedStatement pat=connection.prepareStatement(query);
            pat.setBinaryStream(1,new ByteArrayInputStream(bytes),bytes.length);


            pat.executeUpdate();
            pat.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tag", e.toString());

        }
        //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";
        return null;

        }



    @Override
    protected void onPostExecute(Void aVoid) {



        super.onPostExecute(aVoid);
    }
}


    class Async2 extends AsyncTask<Void,Void,Void> {
        String err="";
        Bitmap bitmap;
        byte[] bytes=stream.toByteArray();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //  Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android","andro","andro");
                Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.43.65:3306/android2","andro","andro");

                Statement statement=connection.createStatement();
                String query="select * from test";
                ResultSet rs=statement.executeQuery(query);
                while(rs.next())
                {
                    Blob bl=rs.getBlob("img");
                    byte[] tb=bl.getBytes(1L,(int)bl.length());
                    bitmap= BitmapFactory.decodeByteArray(tb,0,tb.length);

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("tag", e.toString());

            }
            //records+=resultSet.getString(1)+" "+resultSet.getString(2)+"\n";
            return null;

        }



        @Override
        protected void onPostExecute(Void aVoid) {

//test.setImageBitmap(bitmap);

            super.onPostExecute(aVoid);
        }
    }

}