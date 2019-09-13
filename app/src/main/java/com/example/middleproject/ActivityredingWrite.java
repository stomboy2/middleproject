package com.example.middleproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;

/**
 * Created by stomb on 2018-05-12.
 */

public class ActivityredingWrite extends AppCompatActivity {

    TextView reding_write_title ;
    TextView reding_write_stockname;
    TextView reding_write_buyprice;
    TextView reding_write_targetprice;
    TextView reding_write_cutprice;
    TextView reding_write_save;

    EditText reding_write_content;

    String title;
    String stockName;
    String buyPrice;
    String targetPrice;
    String cutPrice;
    String content;

    ImageView reding_write_back;

    RetrofitInterface retrofitInterface;

    SharedPreferences sharedPreferences;

    String userId;
    String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reding_write);

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        sharedPreferences = getSharedPreferences("loginValue", 0);
        userId = sharedPreferences.getString("loginId","");
        userName = sharedPreferences.getString("loginName", "");

        reding_write_title = (TextView) findViewById(R.id.reding_write_title);
        reding_write_stockname = (TextView) findViewById(R.id.reding_write_stockname);
        reding_write_buyprice = (TextView) findViewById(R.id.reding_write_buyprice);
        reding_write_targetprice = (TextView) findViewById(R.id.reding_write_targetprice);
        reding_write_cutprice = (TextView) findViewById(R.id.reding_write_cutprice);
        reding_write_content = (EditText) findViewById(R.id.reding_write_content);

        reding_write_save = (TextView) findViewById(R.id.reading_write_save);

        reding_write_back = (ImageView) findViewById(R.id.reding_write_back);
        reding_write_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reding_write_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //현재 시간 구하기
                String nowdate = CustomUtils.currnetTimeYYMMDDhhmm();

                if(reding_write_title.getText().length() == 0){
                    ToastMsg("제목을 입력해 주세요");
                }else if(reding_write_stockname.getText().length() == 0){
                    ToastMsg("종목명을 입력해 주세요");
                }else if(reding_write_buyprice.getText().length() == 0){
                    ToastMsg("매수가를 입력해 주세요");
                }else if(reding_write_targetprice.getText().length() == 0){
                    ToastMsg("목표가를 입력해 주세요");
                }else if(reding_write_cutprice.getText().length() == 0){
                    ToastMsg("손절가격을 입력해 주세요");
                }else if(reding_write_content.getText().length() == 0){
                    ToastMsg("내용을 입력해 주세요");
                }else{
                    title = reding_write_title.getText().toString();
                    stockName = reding_write_stockname.getText().toString();
                    buyPrice = reding_write_buyprice.getText().toString();
                    targetPrice = reding_write_targetprice.getText().toString();
                    cutPrice = reding_write_cutprice.getText().toString();
                    content = reding_write_content.getText().toString();

                    //글 저장 후 Activity 종료하고 FragmentCurrent로 돌아가기.
                    Call<String> postCurrentStock = retrofitInterface.postCurrentStock(title, "GoodStock", stockName, buyPrice, targetPrice, cutPrice, content, nowdate);
                    postCurrentStock.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String flag = response.body();
                            if(flag.equals("success")){
                                Intent gotoFragmentCurrent = new Intent();
                                setResult(RESULT_OK,gotoFragmentCurrent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public void ToastMsg(String msg){
        Toast.makeText(ActivityredingWrite.this, msg, Toast.LENGTH_SHORT).show();
    }
}
