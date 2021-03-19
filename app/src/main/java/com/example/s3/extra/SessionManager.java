package com.example.s3.extra;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    
    public SharedPreferences userSessions;
    SharedPreferences.Editor editor;
    Context context;
    
    public static final String IS_LOGGEDIN="log";
    public static final String USER_NAME="uname";
    public static final String PASSWORD="password";
    public static final String MAJOR="major";
    public static final String YEAR="year";
    public static final String FNUM="9834950004";
    public static final String ID="id";
    public static final String RD="RD";
    public static final String RT="RT";
    public static final String IS_BACK="back";
    public static final String IS_FIRST="first";
    public static final String IS_POLL="poll";
    public static final String POLL_WED="wed";
    public static final String DONE_ATT="att";
    public static final String ATT_DAY="day";
    public static final String ADMIN="admin";
    public static final String REM="rem";



    public SessionManager(Context _context){
        context=_context;
        userSessions=context.getSharedPreferences("userLoginSession",Context.MODE_PRIVATE);
        editor=userSessions.edit();
       // u2=context.getSharedPreferences("userSession",Context.MODE_PRIVATE);
        //e2=u2.edit();
       // editor.apply();
    }

    public void createLoginSession(String Username,String Password,String Major,String Year,String Fnum,String id){
        editor.putString(IS_LOGGEDIN,"yes");
        editor.putString(USER_NAME,Username);
        editor.putString(PASSWORD,Password);
        editor.putString(MAJOR,Major);
        editor.putString(YEAR,Year);
        editor.putString(FNUM,Fnum);
        editor.putString(ID,id);
        editor.commit();
    }

    public void createLoginSession2(String first,String poll,String wed,String back,String att,String attday,String admin){
        editor.putString(IS_BACK,back);
        editor.putString(IS_FIRST,first);
        editor.putString(IS_POLL,poll);
        editor.putString(POLL_WED,wed);
        editor.putString(DONE_ATT,att);
        editor.putString(ATT_DAY,attday);
        editor.putString(ADMIN,admin);

        editor.commit();
    }

    public void createLoginSession(String rd,String rt){

        editor.putString(RD,rd);
        editor.putString(RT,rt);
        editor.commit();
    }

    
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> userData=new HashMap<String, String>();
        userData.put(USER_NAME,userSessions.getString(USER_NAME,null));
        userData.put(PASSWORD,userSessions.getString(PASSWORD,null));
        userData.put(MAJOR,userSessions.getString(MAJOR,null));
        userData.put(YEAR,userSessions.getString(YEAR,null));
        userData.put(FNUM,userSessions.getString(FNUM,null));
        userData.put(ID,userSessions.getString(ID,null));
        userData.put(RD,userSessions.getString(RD,null));
        userData.put(RT,userSessions.getString(RT,null));
        userData.put(IS_LOGGEDIN,userSessions.getString(IS_LOGGEDIN,null));
        userData.put(ADMIN,userSessions.getString(ADMIN,null));
        userData.put(REM,userSessions.getString(REM,null));


        userData.put(IS_BACK,userSessions.getString(IS_BACK,null));
        userData.put(IS_FIRST,userSessions.getString(IS_FIRST,null));
       userData.put(IS_POLL,userSessions.getString(IS_POLL,null));
      userData.put(POLL_WED,userSessions.getString(POLL_WED,null));
        userData.put(DONE_ATT,userSessions.getString(DONE_ATT,null));
        userData.put(ATT_DAY,userSessions.getString(ATT_DAY,null));

        return  userData;
    }




    public void setIsLog(String back){
        editor.putBoolean(IS_LOGGEDIN,true);
        editor.commit();
    }

    public void setIsBack(String back){
        editor.putString(IS_BACK,back);
        editor.commit();
    }
    public void setIsFirst(String back){
        editor.putString(IS_FIRST,back);
        editor.commit();
    }
    public void setIsPoll(String back){
        editor.putString(IS_POLL,back);
        editor.commit();
    }
    public void setPollWed(String back){
        editor.putString(POLL_WED,back);
        editor.commit();
    }
    public void setAttDay(String back){
        editor.putString(ATT_DAY,back);
        editor.commit();
    }
    public void setatt(String back){
        editor.putString(DONE_ATT,back);
        editor.commit();
    }
    public void setrem(String back){
        editor.putString(REM,back);
        editor.commit();
    }
    public void logout(){
        editor.clear();
        editor.commit();
    }
}
