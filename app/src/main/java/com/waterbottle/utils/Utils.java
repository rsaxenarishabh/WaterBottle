package com.waterbottle.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.waterbottle.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    public static boolean hideKeyBoard(Activity activity) {
        try {

            InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
            return inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private static ProgressDialog pDialog;

    public static void showProgressDialog(Context context) {
        if (pDialog != null) {
            pDialog.dismiss();
        }
        try {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dismissProgressDialog() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context Application Context
     * @return true if connected with active internet else false
     */
    public static boolean isInternetConnected(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) context
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void displayToast(Context applicationContext, String s) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show();

    }

//    public static String tripDateFormat(String testDate) {
//        String myDate = "2014/10/29 18:10:45";
//        LocalDateTime localDateTime = LocalDateTime.parse(myDate,
//                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss") );
///*
//  With this new Date/Time API, when using a date, you need to
//  specify the Zone where the date/time will be used. For your case,
//  seems that you want/need to use the default zone of your system.
//  Check which zone you need to use for specific behaviour e.g.
//  CET or America/Lima
//*/
//        long millis = localDateTime
//                .atZone(ZoneId.systemDefault())
//                .toInstant().toEpochMilli();
//
//
//        long testTime = getTime(testDate);
//        Log.d("Time:",""+testTime);
//        return dateFormat(testTime);
//    }


    private static long getTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date myDate = sdf.parse(date);
            return myDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strTime = mdformat.format(calendar.getTime());
       return strTime;
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());
        return strDate;

    }




    public static String dateFormat(long timeStamp) {


        if (timeStamp <= 0) {
            return null;
        }

        Log.d("date", ""+timeStamp);
        SimpleDateFormat tripDateFormat = new SimpleDateFormat("dd MMM YYYY");
        Date dNow = new Date(timeStamp);
        return tripDateFormat.format(dNow);
    }

    public static long getMillies(String testDate) {

        Calendar calendar = Calendar.getInstance();
        String dates[] = testDate.split("/");
        int dt= Integer.parseInt(dates[0]);
        int mm= Integer.parseInt(dates[1]);
        int yy= Integer.parseInt(dates[2]);

        calendar.set(yy,mm-1,dt);

        try {
            Date date = calendar.getTime();
            return date.getTime();
        } catch (Exception e) {
            // Log.e("Tag", "Wrong date Format");
        }
        return -1;
    }

    public static long getMilliesFromTime(String testDate) {

        Calendar calendar = Calendar.getInstance();
        String dates[] = testDate.split(":");
        int hh= Integer.parseInt(dates[0]);
        int mm= Integer.parseInt(dates[1]);
        int ss= Integer.parseInt(dates[2]);

        calendar.set(hh,mm,ss);

        try {
            Date date = calendar.getTime();
            return date.getTime();
        } catch (Exception e) {
            // Log.e("Tag", "Wrong date Format");
        }
        return -1;
    }

    public static long getMilliesOfTestTime(String time) {

        String source = "00:10:17";
        String[] tokens = source.split(":");
        int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        return secondsToMs + minutesToMs + hoursToMs;
    }

    public static boolean isValid(String s)
    {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    public static String getAddress(Context mActivity, double lat, double lang) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mActivity, Locale.getDefault());
        String address = "";

        try {
            addresses = geocoder.getFromLocation(
                    lat, lang, 3); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            if (addresses!=null && addresses.size()>0){

                address = addresses.get(0).getAddressLine(0);
            }
            // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            if (!TextUtils.isEmpty(address)){
                return address;
            }else{
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
