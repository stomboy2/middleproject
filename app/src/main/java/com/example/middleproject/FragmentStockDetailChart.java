package com.example.middleproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CandleDataItem;
import retrofitPackage.pojo.CandleDataModel;

/**
 * Created by stomb on 2018-04-07.
 */

public class FragmentStockDetailChart extends Fragment {
    CandleDataModel candleDataModel; // 통신을 통해 갖고온 차트 데이터를 사용하기 위해 담을 별수


    private CandleStickChart candleChart;
    private CandleStickChart candleChartTrading;

    //일봉차트 color를 커스터마이징 하기위해 필요한 배열
    public int[] chart_color ;
    public float[] chart_float ;

    //일봉 차트생성시 x축에 날짜 값 setting
    final ArrayList<String> xLabel = new ArrayList<>();

    //바 그래프 데이터
    private BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labels;

    //차트 일봉 캔들 데이터 setting
    ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();

    //차트 거래량 캔들 데이터 setting
    ArrayList<CandleEntry> yTradingVals1 = new ArrayList<CandleEntry>();

    RetrofitInterface retrofitInterface;

    int startSize = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("attach","attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate","onCreate");
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);
    }

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stock_detail_chart, container, false);
        Log.e("onCreateView","onCreateView");
        String stockName = ActivityStockDetail.stockName;

        //차트 그리는부분
        //candleChart 차트 선언
        candleChart = (CandleStickChart) rootView.findViewById(R.id.candleStickChart);
        candleChart.setBackgroundColor(Color.WHITE);
        //설명을 없앤다?
        candleChart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        candleChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        candleChart.setPinchZoom(false);
        candleChart.setDrawGridBackground(false);

        //barchart 그리기 2
        barChart = (BarChart) rootView.findViewById(R.id.barChartTrading);
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(60);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setEnabled(false);                 //y축 없애기

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);               //x측 0번부터 시작하기


        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        barEntries = new ArrayList<>();
        labels = new ArrayList<>();

        barChart.getLegend().setEnabled(false); //legned 지우기
        //차트 그리기 끝


        //서버와의 통신을 통해 차트를 그릴때 필요한 정보를 갖고 오기.
        Call<CandleDataModel> candelDataModelCall = retrofitInterface.getcandleData(stockName);
        candelDataModelCall.enqueue(new Callback<CandleDataModel>() {
            @Override
            public void onResponse(Call<CandleDataModel> call, Response<CandleDataModel> response) {
                candleDataModel = response.body();
                startSize = candleDataModel.getCandleDataItemList().size();
                drawChart();
            }

            @Override
            public void onFailure(Call<CandleDataModel> call, Throwable t) {
            }

        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("onActivityCreated","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart","onStart");
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("onStop","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("onDestroyView","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("onDetach","onDetach");
    }

    public static FragmentStockDetailChart newInstance(){
        Bundle args = new Bundle();
        FragmentStockDetailChart fragment = new FragmentStockDetailChart();
        fragment.setArguments(args);
        return fragment;
    }

    //차트를 그리는 함수
    public void drawChart(){
        boolean even = false;
        chart_color = new int[candleDataModel.getCandleDataItemList().size()];
        chart_float = new float[candleDataModel.getCandleDataItemList().size()];

        //차트에서 x축 데이터 값을 setting
        for(int i = 0 ; i <candleDataModel.getCandleDataItemList().size() ; i++){
            xLabel.add(candleDataModel.getCandleDataItemList().get(i).getDate().substring(5));  //x축 바닥 값 setting
            labels.add(candleDataModel.getCandleDataItemList().get(i).getDate().substring(5));  //x축 바닥 값 setting

            float startFloat = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getStartValue());  //시가

            float highFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getHighValue()); //고가
            float highFloat = highFloatTmp-startFloat;

            float lowFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getLowValue());   //저가
            float lowFloat = startFloat-lowFloatTmp;

            float openFloat = 0f;//시작가격 = 시가

            float closeFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getEndValue());  //종가
            float closeFloat ;

            float tradingVolumeTmp ;
            if(candleDataModel.getCandleDataItemList().get(i).getTradingVolume() == null){
                tradingVolumeTmp = 20000f;
            }else{
                tradingVolumeTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getTradingVolume());//거래량
            }
            chart_float[i] = tradingVolumeTmp; // 거래량 저장하기.

            if(startFloat > closeFloatTmp){
                closeFloat = startFloat - closeFloatTmp;
                even = true;
            }else if(closeFloatTmp > startFloat){
                closeFloat = closeFloatTmp - startFloat;
                even = false;
            }else{
                closeFloat = 0f;
            }

            //param1 : X칸의 순서
            //param2 : 윗꼬리 그리기
            //param3 : 아래꼬리 그리기
            //param4 : 사각형 위아래중 한쪽
            //param5 : 사각형 위아래중 나머지한쪽

            //일봉 차트 데이터 값을 setting 하는 부분
            yVals1.add(new CandleEntry(
                    i, startFloat + highFloat,
                    startFloat - lowFloat,
                    even ? startFloat + openFloat : startFloat - openFloat,
                    even ? startFloat - closeFloat : startFloat + closeFloat
            ));

            //bar 그래프 2
            barEntries.add(new BarEntry(i, tradingVolumeTmp));  //첫번째 파라미터, x 위치, 두번째 파라미터 Y 값

        }// for END


        for(int i = 0 ; i < candleDataModel.getCandleDataItemList().size() ; i++){
            if(i ==0){
                chart_color[i] = Color.BLUE;
            }else{
                if(chart_float[i-1] > chart_float[i]){
                    chart_color[i] = Color.BLUE;
                }else{
                    chart_color[i] = Color.RED;
                }
            }

        }

        //bar 그래프2
        BarDataSet barDataSet1 = new BarDataSet(barEntries , "거래량");
        barDataSet1.setColors(chart_color);

        BarData bardata = new BarData(barDataSet1);
        barChart.setData(bardata);

        //일봉
        XAxis xAxis = candleChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });

        //일봉
        YAxis leftAxis = candleChart.getAxisLeft();
        leftAxis.setEnabled(false);

        //일봉
        YAxis rightAxis = candleChart.getAxisRight();
        rightAxis.setLabelCount(7, false);
        rightAxis.setDrawGridLines(true);
        rightAxis.setDrawAxisLine(false);

        candleChart.resetTracking();

        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.BLUE);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.RED);
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setDrawValues(false);

        CandleData data = new CandleData(set1);

        candleChart.setData(data);
        candleChart.invalidate();

        candleChart.getLegend().setEnabled(false);
    }
}
