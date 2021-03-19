package com.example.s3.Complaint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.s3.DashBoard;
import com.example.s3.R;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListenerAdapter;


public class CF2 extends DashBoard {

    BoomMenuButton bmb,bmb2,bmb5,bmb7,bmb4,bmb6,bmb3,bmb8;
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    Integer priority=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_c_f2);
        bmb = findViewById(R.id.bmb);
        bmb2 = findViewById(R.id.bmb2);
        bmb4 = findViewById(R.id.bmb4);
        bmb5 = findViewById(R.id.bmb5);
        bmb6 = findViewById(R.id.bmb6);
        bmb7 = findViewById(R.id.bmb7);
        bmb3 = findViewById(R.id.bmb3);
        bmb8 = findViewById(R.id.bmb8);

        int[] hy = new int[5];
        hy[0] = R.drawable.clean;
        hy[1] = R.drawable.ic2;
        hy[2] = R.drawable.trash;
        hy[3] = R.drawable.pad;
        hy[4] = R.drawable.ot;

        final String[] hs = new String[]{
                "Cleaning", "Washroom", "Dustbin", "Sanitary Napkin", "Other"
        };

        Rect r = new Rect();
        r.set(34, 24, 34, 34);
        ObjectAnimator obj = ObjectAnimator.ofFloat(findViewById(R.id.oo), "Opacity", 0.9f);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(hy[i])
                    .normalText(hs[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {
                                Intent j = new Intent(CF2.this, Generic_complaint.class);
                                j.putExtra("boom","Hygiene");
                               j.putExtra("sub",hs[i]);
if(i==1||i==3)
    priority=1;
else if(i==2)
    priority=2;
else
    priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    }).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#80FF303D"));



            bmb.addBuilder(builder);

        }


        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.oo).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.oo).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
            }

        });


        int[] re = new int[]{
                R.drawable.reg,
                R.drawable.flush,
                R.drawable.furniture,
                R.drawable.net,
                R.drawable.cooler,
                R.drawable.tube,
                R.drawable.leak,
                R.drawable.ot
        };

        final String[] rs = new String[]{
                "Regulator", "Flusher", "Furniture", "Window Net", "cooler", "TubeLight", "Leakage", "Other"
        };

        for (int i = 0; i < bmb2.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(re[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","Repairing");
                            j.putExtra("sub",rs[i]);
                            if(i==1||i==6)
                                priority=1;
                            else if(i==5)
                                priority=2;
                            else
                                priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(rs[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#807C00FF"));
            bmb2.addBuilder(builder);
        }

        int[] wa = new int[]{
                R.drawable.no,
                R.drawable.time,
                R.drawable.dirt,
                R.drawable.low,
                R.drawable.ot
        };

        final String[] ws = new String[]{
                "No water", "Not on Time", "Dirty water", "Low Force", "other"
        };

        for (int i = 0; i < bmb4.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(wa[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","WaterIssues");
                            j.putExtra("sub",ws[i]);
                            if(i==0||i==2)
                                priority=1;
                            else
                                priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(ws[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#8000FFFB"));
            bmb4.addBuilder(builder);
        }

        int[] me = new int[]{
                R.drawable.dish,
                R.drawable.time,
                R.drawable.spoilt,
                R.drawable.sme,
                R.drawable.ot
        };

        final String[] ms = new String[]{
                "Unclean Dishes", "Food Not Ready on Time", "Contaminated Food", "Spoilt Food", "Other"
        };

        for (int i = 0; i < bmb5.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(me[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","Mess");
                            j.putExtra("sub",ms[i]);
                            if(i==2||i==3)
                                priority=1;
                            else if(i==0)
                                priority=2;
                            else
                                priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(ms[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#8007E840"));
            bmb5.addBuilder(builder);
        }


        int[] se = new int[]{
                R.drawable.guard,
                R.drawable.vis,
                R.drawable.cctv,
                R.drawable.bully,
                R.drawable.ot

        };

        final String[] ss = new String[]{
                "No Secrity Guard", "Outsiders", "CCTV", "Bullying", "other"
        };

        for (int i = 0; i < bmb6.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(se[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","Security");
                            j.putExtra("sub",ss[i]);
                            if(i==1||i==3)
                                priority=1;
                            else if(i==2||i==0)
                                priority=2;
                            else
                                priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(ss[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#80FB2C0C"));
            bmb6.addBuilder(builder);
        }

        int[] pe = new int[]{
                R.drawable.rat,
                R.drawable.cock,
                R.drawable.ant,
                R.drawable.termite,
                R.drawable.ot
        };

        final String[] ps = new String[]{
                "Rat", "Cockroach", "Ants", "Termite", "Other"
        };


        for (int i = 0; i < bmb7.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(pe[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","PestControl");
                            j.putExtra("sub",ps[i]);

                                priority=2;

                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(ps[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#8008899F"));
            bmb7.addBuilder(builder);
        }


        int[] sb = new int[]{
                R.drawable.rude,
                R.drawable.nop,
                R.drawable.late,
                R.drawable.theft,
                R.drawable.ot
        };

        final String[] bb = new String[]{
                "Impolite", "No Perfection", "Irregular", "Theft", "Other"
        };


        for (int i = 0; i < bmb3.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(sb[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","StaffBehaviour");
                            j.putExtra("sub",bb[i]);
                            if(i==3)
                                priority=1;
                            else if(i==2)
                                priority=2;
                            else
                                priority=3;
                            j.putExtra("priority",priority);

                            startActivity(j);

                        }
                    })
                    .normalText(bb[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#80FB0CE6"));
            bmb3.addBuilder(builder);
        }


        for (int i = 0; i < bmb8.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(pe[i]).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {

                            Intent j = new Intent(CF2.this, Generic_complaint.class);
                            j.putExtra("boom","MissingItems");
                            j.putExtra("sub",ps[i]);
                            startActivity(j);

                        }
                    })
                    .normalText(ps[i]).textSize(15).buttonRadius(115).normalColor(Color.parseColor("#800BC6C3"));
                    //.normalTextColor(R.color.black);
            bmb8.addBuilder(builder);
        }







        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.aa).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);
                findViewById(R.id.oo).getBackground().setAlpha(100);


            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.aa).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);

            }

        });

        bmb3.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.aa).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);


            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.aa).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);


            }

        });

        bmb2.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.aa).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);


            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.aa).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);


            }

        });

        bmb8.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.cc).getBackground().setAlpha(100);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);

            }

        });
        bmb4.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.cc).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);
                findViewById(R.id.oo).getBackground().setAlpha(100);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);

                findViewById(R.id.bb).getBackground().setAlpha(255);
            }

        });
        bmb5.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);

            }

        });
        bmb6.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.cc).getBackground().setAlpha(100);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
                findViewById(R.id.oo).getBackground().setAlpha(255);

            }

        });
        bmb7.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.op).getBackground().setAlpha(10);
                findViewById(R.id.bb).getBackground().setAlpha(100);
                findViewById(R.id.ff).getBackground().setAlpha(100);
                findViewById(R.id.ee).getBackground().setAlpha(100);
                findViewById(R.id.dd).getBackground().setAlpha(100);
                findViewById(R.id.oo).getBackground().setAlpha(100);

                findViewById(R.id.cc).getBackground().setAlpha(100);

            }

            @Override
            public void onBoomDidHide() {
                findViewById(R.id.oo).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.op).getBackground().setAlpha(255);
                findViewById(R.id.ff).getBackground().setAlpha(255);
                findViewById(R.id.ee).getBackground().setAlpha(255);
                findViewById(R.id.dd).getBackground().setAlpha(255);
                findViewById(R.id.cc).getBackground().setAlpha(255);
                findViewById(R.id.bb).getBackground().setAlpha(255);
            }

        });


    }


    public void createDialog(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View pop=getLayoutInflater().inflate(R.layout.activity_generic_complaint,null);
        dialogBuilder.setView(pop);
        dialog=dialogBuilder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        Intent j = new Intent(CF2.this, DashBoard.class);
        startActivity(j);
    }
}