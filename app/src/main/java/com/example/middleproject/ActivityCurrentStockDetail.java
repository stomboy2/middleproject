package com.example.middleproject;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CurrentStockModel;

/**
 * Created by stomb on 2018-05-12.
 * 오늘의종목 탭에서 1개의 종목을 선택했을때 그 종목의 상세정보를 보는 화면
 */

public class ActivityCurrentStockDetail extends AppCompatActivity {

    TextView current_detail_title;
    TextView current_detail_name;
    TextView current_detail_date;
    TextView current_detail_view;

    EditText current_detail_stockName;
    EditText current_detail_buyprice;
    EditText current_detail_targetprice;
    EditText current_detail_cutprice;
    EditText current_detail_content;

    ImageView current_stock_detail_back;

    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_stock_detail);

        Intent getIntent = getIntent();
        String no = getIntent.getExtras().getString("no");
        Log.e("no", no);
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        current_detail_title = (TextView)findViewById(R.id.current_detail_title);
        current_detail_name = (TextView)findViewById(R.id.current_detail_name);
        current_detail_date = (TextView)findViewById(R.id.current_detail_date);
        current_detail_view = (TextView)findViewById(R.id.current_detail_view);

        current_detail_stockName = (EditText)findViewById(R.id.current_detail_stockName);
        current_detail_buyprice = (EditText)findViewById(R.id.current_detail_buyprice);
        current_detail_targetprice = (EditText)findViewById(R.id.current_detail_targetprice);
        current_detail_cutprice = (EditText)findViewById(R.id.current_detail_cutprice);
        current_detail_content = (EditText)findViewById(R.id.current_detail_content);

        //현재 Activity 닫기.
        current_stock_detail_back = (ImageView)findViewById(R.id.current_stock_detail_back);
        current_stock_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //editText들 수정 못하게 막기
        current_detail_stockName.setClickable(false);
        current_detail_stockName.setFocusable(false);

        current_detail_buyprice.setClickable(false);
        current_detail_buyprice.setFocusable(false);

        current_detail_targetprice.setClickable(false);
        current_detail_targetprice.setFocusable(false);

        current_detail_cutprice.setClickable(false);
        current_detail_cutprice.setFocusable(false);

        current_detail_content.setClickable(false);
        current_detail_content.setFocusable(false);

        //Server와의 통신으로 오늘의종목에서 선택한 값의 상세정보를 갖고와서 위젯에 setting 해주기.
        Call<CurrentStockModel> currentStockModelCall = retrofitInterface.getCurrentStock2(no);
        currentStockModelCall.enqueue(new Callback<CurrentStockModel>() {
            @Override
            public void onResponse(Call<CurrentStockModel> call, Response<CurrentStockModel> response) {
                CurrentStockModel currentStockModel = response.body();
                current_detail_title.setText(currentStockModel.getCurrentStockList().get(0).getTitle());
                current_detail_name.setText(currentStockModel.getCurrentStockList().get(0).getName());
                current_detail_date.setText(currentStockModel.getCurrentStockList().get(0).getDate());
                int view = currentStockModel.getCurrentStockList().get(0).getView();
                current_detail_view.setText(String.valueOf(view));

                current_detail_stockName.setText(currentStockModel.getCurrentStockList().get(0).getStockName());
                current_detail_buyprice.setText(currentStockModel.getCurrentStockList().get(0).getBuyPrice());
                current_detail_targetprice.setText(currentStockModel.getCurrentStockList().get(0).getTargetPrice());
                current_detail_cutprice.setText(currentStockModel.getCurrentStockList().get(0).getCutPrice());
                current_detail_content.setText(currentStockModel.getCurrentStockList().get(0).getContext());
            }

            @Override
            public void onFailure(Call<CurrentStockModel> call, Throwable t) {

            }
        });
    }
}
