package com.example.middleproject;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.RequestBody;

/**
 * Created by stomb on 2018-05-10.
 */

public class CustomUtils {


    //이미지를 핸드폰에 저장시키기 위한 파일을 만들기
    public static File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + ".jpg";

        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "gyeom");

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);

        Log.e("imageFileName",   imageFileName + " storageDir = " + String.valueOf(storageDir));
        return imageFile;
    }


    //Bitmap을 file로 저장하기
    public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath) {
        Log.e("bitmap",   String.valueOf(bitmap) + " strFilePath = " + strFilePath);
        File fileCacheItem = new File(strFilePath);
        OutputStream out = null;

        try
        {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
        }
        catch (Exception e){
            e.printStackTrace();
        } finally{
            try {
                out.close();
            }  catch (IOException e)  {
                e.printStackTrace();
            }
        }
    }

    //multipart 이용시 string 값을 전송하기 위해 사용
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    //메세지를 보낼때 현재 시간 만들기
    public static String currnetTimeYYMMDDhhmm(){

        SimpleDateFormat sdfnow = new SimpleDateFormat("yy.MM.dd HH:mm");

        Long now = System.currentTimeMillis();
        Date date = new Date(now);

        String nowDate = sdfnow.format(date);

        return nowDate;
    }

    public static String currentTimeYYYYMMDD(){
        //현재 날짜 구하기
        Long now ;
        Date date ;
        SimpleDateFormat sdfnow;

        now = System.currentTimeMillis();
        date = new Date(now);
        sdfnow = new SimpleDateFormat("yyyy-MM-dd");

        String finalTime= "";
        String nowDate = sdfnow.format(date);

        return nowDate;
    }

    public static String strTostr(String tempValue){
        String exchangeValue = NumberFormat.getInstance().format(Double.parseDouble(tempValue));
        return exchangeValue;
    }

    public static String minusStr(String tempValue){
        String temp = strCheck(tempValue);
        return temp;
    }

    public static String strMinusStr(String tempValue1, String tempValue2){
        tempValue1 = strCheck(tempValue1);
        tempValue2 = strCheck(tempValue2);

        float intTemp1 = Float.parseFloat(tempValue1);
        float intTemp2 = Float.parseFloat(tempValue2);
        float resultTemp = intTemp2-intTemp1;
        return String.format("%.1f", resultTemp)+"%";
    }


    public static String strRatio(String tempValue1, String tempValue2){
        String strTemp1 = "";
        String strTemp2 = "";

        if(tempValue1.length()>4){
            strTemp1 = tempValue1.replace(",","");
        }else{
            strTemp1 = tempValue1;
        }
        if(tempValue2.length()>4){
            strTemp2 = tempValue2.replace(",","");
        }else{
            strTemp2 = tempValue2;
        }

        strTemp1 = strCheck(strTemp1);
        strTemp2 = strCheck(strTemp2);

        float intTemp1 = Float.parseFloat(strTemp1);
        float intTemp2 = Float.parseFloat(strTemp2);
        Log.e("무슨값?", String.valueOf(intTemp1));
        Log.e("무슨값?2", String.valueOf(intTemp2));
//        Log.e("빼기", String.valueOf(intTemp2 - intTemp1));
//        Log.e("나누기", String.valueOf((intTemp2 - intTemp1)/intTemp1));
//        Log.e("곱하기", String.valueOf(((intTemp2 - intTemp1)/intTemp1)*100));
        if(intTemp1 == 0.0){
            return "0.0%";
        }else{
            float resultTemp = ((intTemp2 - intTemp1)/Math.abs(intTemp1))*100;
            return String.format("%.1f", resultTemp)+"%";
        }
    }

    public static String strCheck(String checkStr){

        if(checkStr.substring(0,1).equals("<")){
            int length = checkStr.length();
            checkStr = checkStr.substring(18, length-7);
        }else if(checkStr.substring(0,1).equals("N")){
            checkStr = "0";
        }else if(checkStr.substring(0,1).equals("&")){
            checkStr = "0";
        }else if(checkStr.substring(0,1).equals("적")){
            checkStr = "0";
        }else if(checkStr.substring(0,1).equals("흑")){
            checkStr = "0";
        }
        return checkStr;
    }

    public static void textViewColor(TextView viewTemp, String strTemp){
        if(strTemp.substring(0,1).equals("-")){
            viewTemp.setTextColor(Color.parseColor("#FFD41620"));
        }
    }
}
