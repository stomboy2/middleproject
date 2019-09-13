package com.example.middleproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

//import com.commexpert.ExpertRealProc;
//import com.commexpert.ExpertTranProc;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.android.material.tabs.TabLayout;
import com.truefriend.corelib.commexpert.intrf.IExpertInitListener;
import com.truefriend.corelib.commexpert.intrf.IRealDataListener;
import com.truefriend.corelib.commexpert.intrf.ITranDataListener;

import java.text.NumberFormat;
import java.util.ArrayList;

import commExpert.ExpertRealProc;
import commExpert.ExpertTranProc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CandleDataItem;
import retrofitPackage.pojo.CompanyInfoModel;
import retrofitPackage.pojo.StockDetailListModel;
import retrofitPackage.pojo.CandleDataModel;

/**
 * Created by stomb on 2018-03-31.
 * 종목 프래그먼트에서 주식 종목 1개를 클릭시 해당 주식에 대한 상세 내용을 볼수 있는 Activity
 */

public class ActivityStockDetail extends AppCompatActivity implements ITranDataListener, IRealDataListener, IExpertInitListener {

    TextView stock_detail_stockName;            //주식이름
    TextView stock_detail_price;                //주식 가격
    TextView stock_detail_ratio;                //주식 변동성
    TextView stock_detail_volume;               //주식거래량
    TextView stock_detail_ratioPercent;         //주식 변동률
    TextView stock_detail_title;                //화면 제목

    ImageView stock_detail_backimg;             //화면 상단 뒤로가기 이미지
    ImageView stock_detail_arrow;               //가격 상승,하락에 대한 이미지

    static ExpertTranProc m_PriceTranProc = null;		// 주식 API 에서 현재가를 조회할 수 있도록 돕는 객체 선언
    static ExpertRealProc m_PriceRealProc = null;		// 주식 API 에서 현재가 체결을 실시간으로 조회할수 있도록 돕는 객체 선언
    static CompanyInfoModel companyInfoModel = null;

    RetrofitInterface retrofitInterface; //HTTP 통신을 하기 위해 retrofitinterface 선언

    StockDetailListModel stockDetailListitem;   // 통신을 통해 갖고온 해당 종목 기사를 담을 변수 선언

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

    static String stockName;
    static String numberOfStock;

    CandleDataItem candleDataItem;

    int startSize = 0;

    private static int PAGE_NUMBER = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        Intent getIntent = getIntent();     //주식 정보 프래그먼트에서 받아온 Intent 값을 getIntent에 setting
        String stockCode = getIntent.getExtras().getString("stockCode");
        String stockCodeTmp = "A"+stockCode;
        stockName = getIntent.getExtras().getString("stockName");
        numberOfStock = getIntent.getExtras().getString("numberOfStock");

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        //캔들을 만들기 위한 데이터를 server 통신을 통해서 갖고오기.
        Call<CandleDataModel> candelDataModelCall = retrofitInterface.getcandleData(stockName);
        candelDataModelCall.enqueue(new Callback<CandleDataModel>() {
            @Override
            public void onResponse(Call<CandleDataModel> call, Response<CandleDataModel> response) {
                candleDataModel = response.body();
                startSize = candleDataModel.getCandleDataItemList().size();
            }

            @Override
            public void onFailure(Call<CandleDataModel> call, Throwable t) {
            }

        });

        stock_detail_stockName = (TextView) findViewById(R.id.stock_detail_stockName);      //주식 이름
        stock_detail_price = (TextView) findViewById(R.id.stock_detail_price);              //주식 가격
        stock_detail_ratio = (TextView) findViewById(R.id.stock_detail_ratio);              //주식 변동
        stock_detail_volume = (TextView) findViewById(R.id.stock_detail_volume);            //주식 거래량
        stock_detail_ratioPercent = (TextView) findViewById(R.id.stock_detail_ratioPercent);//주식 변동률
        stock_detail_title = (TextView) findViewById(R.id.stock_detail_title);              //화면 제목
//
        stock_detail_backimg = (ImageView) findViewById(R.id.stock_detail_backimg);
        stock_detail_arrow = (ImageView) findViewById(R.id.stock_detail_arrow);

        stock_detail_title.setText(stockName);
        stock_detail_stockName.setText(stockName);

        //TR 초기화
        m_PriceTranProc = new ExpertTranProc(getApplicationContext());
        m_PriceTranProc.InitInstance(this);

        //실시간 초기화
        m_PriceRealProc = new ExpertRealProc(getApplicationContext());
        m_PriceRealProc.InitInstance(this);

        m_PriceTranProc.ClearInblockData();
        m_PriceTranProc.SetSingleData(0,0, "J");
        m_PriceTranProc.SetSingleData(0,1, stockCode);
        m_PriceTranProc.RequestData("scp");

        m_PriceRealProc.RequestReal("sc_r", stockCode);


        SecionsPagerAdapter secionsPagerAdapter = new SecionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.stock_detail_view_pager);
        mViewPager.setAdapter(secionsPagerAdapter);

        TabLayout mtab = (TabLayout) findViewById(R.id.stock_detail_tab_layout);
        mtab.setupWithViewPager(mViewPager);

        //선택한 종목의 상세 기업 정보를 확인
        Call<CompanyInfoModel> companyInfoItemCall = retrofitInterface.postCompanyInfo(stockCodeTmp);
        companyInfoItemCall.enqueue(new Callback<CompanyInfoModel>() {
            @Override
            public void onResponse(Call<CompanyInfoModel> call, Response<CompanyInfoModel> response) {
                companyInfoModel = response.body();
            }

            @Override
            public void onFailure(Call<CompanyInfoModel> call, Throwable t) {
                Log.e("ActivityStockDetail", t.getMessage());
            }
        });
    }   //onCreated


    //주식 API 를 통한 해당 종목의 현재가, 시가, 거래량등의 값 갖고 오기
    @Override
    public void onTranDataReceived(String s, int i) {
        String currentPriceStr = m_PriceTranProc.GetSingleData(0,11);				//현재가
        String startvalueStr = m_PriceTranProc.GetSingleData(0,18);					//시가
        String ratioStr = m_PriceTranProc.GetSingleData(0,14);						//전일대비율
        String yesterdayStr = m_PriceTranProc.GetSingleData(0,12);					//전일대비
        String exchangeVolumeStr = m_PriceTranProc.GetSingleData(0,16);		 		//거래량

        String stockItemName = m_PriceTranProc.GetSingleData(0,4);
//        Log.e("봐라 ","최고가 =" + highvalueStr + "최저가 = " + lowvalueStr + "거래대금 = " + exchangeDeagum + "52주 최고 =" + highestValue + "52주 최저 = " + lowestValue);
//        Log.e("봐라2 ","외인보유비중 =" + weainboyou + "시가총액 = " + sigaTotal + "액면가 = " + actmyunValue + "총주식수 =" + totalsu );
//        Log.e("봐라3 ","EPS =" + eps + "pef = " + pef + "bps = " + bps + "pbr =" + pbr);

        /**차트 데이터를 insert 해주는 부분*/
//        String currentPriceIntTmpTmp = String.valueOf(Integer.parseInt(currentPriceStr));
//        String startvalueStrTmp = String.valueOf(Integer.parseInt(startvalueStr));
//        String highvalueStrTmp = String.valueOf(Integer.parseInt(highvalueStr));
//        String lowvalueStrTmp = String.valueOf(Integer.parseInt(lowvalueStr));
//
//        차트 데이터 서버로 보낼때 거래량도 넣어줘야 한다. 현재는 거래량이 없음
//        Call<Void> postCandleDataCall = retrofitInterface.postCandleData(stockName, startvalueStrTmp, highvalueStrTmp, lowvalueStrTmp, currentPriceIntTmpTmp, exchangeVolumeStr);
//        postCandleDataCall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });

        ratioStr = ratioStr.substring(0,8);

        if (ratioStr.substring(3, 4).equals("0")) {
            ratioStr = ratioStr.substring(4,8);
        }else{
            ratioStr = ratioStr.substring(3,8);
        }

        int yesterdayInt = Integer.parseInt(yesterdayStr);              //전일대비 Int
        String yesterdayTmp = Integer.toString(yesterdayInt);

        if(yesterdayTmp.substring(0,1).equals("-")){    //전일대비 - 일때
            yesterdayTmp = yesterdayTmp.substring(1);
            stock_detail_price.setTextColor(Color.parseColor("#FF0022FA"));
            stock_detail_ratio.setTextColor(Color.parseColor("#FF0022FA"));
            stock_detail_ratioPercent.setTextColor(Color.parseColor("#FF0022FA"));
            stock_detail_arrow.setImageResource(R.drawable.bluearrow);
        }else{      //전일 대비 - 가 아닐때
            if(yesterdayInt > 0){   // 전일대비 상승중일때
                stock_detail_price.setTextColor(Color.parseColor("#FFD41620"));
                stock_detail_ratio.setTextColor(Color.parseColor("#FFD41620"));
                stock_detail_ratioPercent.setTextColor(Color.parseColor("#FFD41620"));
                stock_detail_arrow.setImageResource(R.drawable.redarrow);
            }else{  //전일대비 동일한 값일때
                stock_detail_price.setTextColor(Color.parseColor("#FF000000"));
                stock_detail_ratio.setTextColor(Color.parseColor("#FF000000"));
                stock_detail_ratioPercent.setTextColor(Color.parseColor("#FF000000"));
                stock_detail_arrow.setImageResource(R.drawable.nopoint);
            }
        }

        //천단위 표시하기
        String currentPriceIntTmp = NumberFormat.getInstance().format(Double.parseDouble(currentPriceStr));  //현재가 천단위 표시
        String exchangeVolumeTmp = NumberFormat.getInstance().format(Double.parseDouble(exchangeVolumeStr)); //거래량 천단위 표시
        String exchangeVolumeTmpTmp = NumberFormat.getInstance().format(Double.parseDouble(yesterdayTmp));   //전일대비 천단위 표시

        stock_detail_price.setText(currentPriceIntTmp);                 //현재가격   위젯에 값 setting
        stock_detail_ratio.setText(exchangeVolumeTmpTmp);               //전일대비   위젯에 값 setting
        stock_detail_volume.setText(exchangeVolumeTmp);                 //거래량     위젯에 값 setting
        stock_detail_ratioPercent.setText(ratioStr);                    //전일대비율 위젯에 값 setting

        stock_detail_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSessionConnecting() {

    }

    @Override
    public void onSessionConnected(boolean b, String s) {

    }

    @Override
    public void onAppVersionState(boolean b) {

    }

    @Override
    public void onMasterDownState(boolean b) {

    }

    @Override
    public void onMasterLoadState(boolean b) {

    }

    @Override
    public void onInitFinished() {

    }

    @Override
    public void onRequiredRefresh() {

    }

    @Override
    public void onTranMessageReceived(int i, String s, String s1, String s2) {

    }

    @Override
    public void onTranTimeout(int i) {

    }

    //실시간 데이터 갖고오기
    @Override
    public void onRealDataReceived(String s) {
        if(s =="sc_r"){
            String date = CustomUtils.currentTimeYYYYMMDD();                                                //날짜
            String currentPriceStr = m_PriceRealProc.GetRealData(0,2);				//현재가
            String startvalueStr = m_PriceRealProc.GetRealData(0,7);					//시가
            String highPriceStr = m_PriceRealProc.GetRealData(0,8);					//고가
            String lowPriceStr = m_PriceRealProc.GetRealData(0,9);					//저가
            String endPriceStr = m_PriceRealProc.GetRealData(0,2);				    //종가
            String ratioStr = m_PriceRealProc.GetRealData(0,5);						//전일대비율
            String yesterdayStr = m_PriceRealProc.GetRealData(0,4);					//전일대비
            String exchangeVolumeStr = m_PriceRealProc.GetRealData(0,13);				//거래량

            candleDataItem = new CandleDataItem();
            candleDataItem.setDate(date);                       //날짜
            candleDataItem.setStartValue(startvalueStr);        //시가
            candleDataItem.setEndValue(endPriceStr);            //종가
            candleDataItem.setHighValue(highPriceStr);          //고가
            candleDataItem.setLowValue(lowPriceStr);            //저가
            candleDataItem.setTradingVolume(exchangeVolumeStr); //거래량

            if(startSize == candleDataModel.getCandleDataItemList().size()){
                candleDataModel.setCandleDataItemList(candleDataItem);
            }else{
                candleDataModel.getCandleDataItemList().remove(candleDataModel.getCandleDataItemList().size()-1);
                candleDataModel.setCandleDataItemList(candleDataItem);
            }

            Log.e("실시간 ","현재가격 =" + currentPriceStr + "시가 = " + startvalueStr + "전일대비율 = " + ratioStr + "전일대비 =" + yesterdayStr + "거래량 = " + exchangeVolumeStr);

//            ratioStr = ratioStr.substring(1,5);
//            if (ratioStr.substring(3, 4).equals("0")) {
//                ratioStr = ratioStr.substring(4,8);
//            }else{
//                ratioStr = ratioStr.substring (3,8);
//            }

            int yesterdayInt = Integer.parseInt(yesterdayStr);              //전일대비 Int
            String yesterdayTmp = Integer.toString(yesterdayInt);

            if(yesterdayTmp.substring(0,1).equals("-")){    //전일대비 - 일때
                ratioStr = ratioStr.substring(1,5);
                yesterdayTmp = yesterdayTmp.substring(1);
                stock_detail_price.setTextColor(Color.parseColor("#FF0022FA"));
                stock_detail_ratio.setTextColor(Color.parseColor("#FF0022FA"));
                stock_detail_ratioPercent.setTextColor(Color.parseColor("#FF0022FA"));
                stock_detail_arrow.setImageResource(R.drawable.bluearrow);
            }else{      //전일 대비 - 가 아닐때
                if(yesterdayInt > 0){   // 전일대비 상승중일때
                    ratioStr = ratioStr.substring(0,4);
                    stock_detail_price.setTextColor(Color.parseColor("#FFD41620"));
                    stock_detail_ratio.setTextColor(Color.parseColor("#FFD41620"));
                    stock_detail_ratioPercent.setTextColor(Color.parseColor("#FFD41620"));
                    stock_detail_arrow.setImageResource(R.drawable.redarrow);
                }else{  //전일대비 동일한 값일때
                    stock_detail_price.setTextColor(Color.parseColor("#FF000000"));
                    stock_detail_ratio.setTextColor(Color.parseColor("#FF000000"));
                    stock_detail_ratioPercent.setTextColor(Color.parseColor("#FF000000"));
                    stock_detail_arrow.setImageResource(R.drawable.nopoint);
                }
            }

            //천단위 표시하기
            String currentPriceIntTmp = NumberFormat.getInstance().format(Double.parseDouble(currentPriceStr));  //현재가 천단위 표시
            String exchangeVolumeTmp = NumberFormat.getInstance().format(Double.parseDouble(exchangeVolumeStr)); //거래량 천단위 표시
            String exchangeVolumeTmpTmp = NumberFormat.getInstance().format(Double.parseDouble(yesterdayTmp));   //전일대비 천단위 표시

            stock_detail_price.setText(currentPriceIntTmp);                 //현재가격   위젯에 값 setting
            stock_detail_ratio.setText(exchangeVolumeTmpTmp);               //전일대비   위젯에 값 setting
            stock_detail_volume.setText(exchangeVolumeTmp);                 //거래량     위젯에 값 setting
            stock_detail_ratioPercent.setText(ratioStr);                    //전일대비율 위젯에 값 setting

            stock_detail_backimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /** recyclerView 1*/

    //커스텀 뷰 홀더
    //Item layout에 존재하는 위젯들을 바인딩. 바인딩?
//    class ViewHolder extends RecyclerView.ViewHolder{
//        TextView detail_title;
//        TextView detail_rssName;
//        TextView detail_date;
//
//        LinearLayout stock_detail_recyclerview_linear;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            detail_title = itemView.findViewById(R.id.stock_detail_recyclerview_title);
//            detail_rssName = itemView.findViewById(R.id.stock_detail_recyclerview_rssname);
//            detail_date = itemView.findViewById(R.id.stock_detail_recyclerview_date);
//            stock_detail_recyclerview_linear = itemView.findViewById(R.id.stock_detail_recyclerview_linear);
//        }
//    }//ViewHolder END

    /*//해당 종목의 뉴스를 확인할수 있는 recycler를 setting
    public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        //새로운 view 홀더를 생성
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_stock_detail_recyclerview_item, parent, false));
        }//ViewHolder END

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int positionTmp = position;
            holder.detail_title.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getTitle());
//             holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());
            holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());

            String rssNameTmp ="";
            if(stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("edaily")){
                rssNameTmp = "이데일리";
            }else if(stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("hankuk")){
                rssNameTmp = "한국경제";
            }else if(stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("herald")){
                rssNameTmp = "헤럴드경제";
            }else if(stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("meail")){
                rssNameTmp = "매일경제";
            }else if(stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("moneytoday")){
                rssNameTmp = "머니투데이";
            }

            holder.detail_rssName.setText(rssNameTmp);
            holder.stock_detail_recyclerview_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent stockDetaiWebViewlIntent = new Intent(getApplication(), ActivityStockDetailWebview.class);
                    stockDetaiWebViewlIntent.putExtra("url", stockDetailListitem.getStockDetailListItemLIst().get(positionTmp).getUrl());
                    startActivity(stockDetaiWebViewlIntent);
                }
            });
        }//onBindViewHolder END

        @Override
        public int getItemCount() {
            if(stockDetailListitem != null){
                return  stockDetailListitem.getStockDetailListItemLIst().size();
            }else{
                return 0;
            }
        }//getItemCount END

    }//RecyclerAdapter END*/

    //chart draw
    //차트를 그리는 함수
//    public void drawChart(){
//        boolean even = false;
//        chart_color = new int[candleDataModel.getCandleDataItemList().size()];
//        chart_float = new float[candleDataModel.getCandleDataItemList().size()];
//
//        //차트에서 x축 데이터 값을 setting
//        for(int i = 0 ; i <candleDataModel.getCandleDataItemList().size() ; i++){
//            xLabel.add(candleDataModel.getCandleDataItemList().get(i).getDate().substring(5));  //x축 바닥 값 setting
//            labels.add(candleDataModel.getCandleDataItemList().get(i).getDate().substring(5));  //x축 바닥 값 setting
//
//            float startFloat = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getStartValue());  //시가
//
//            float highFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getHighValue()); //고가
//            float highFloat = highFloatTmp-startFloat;
//
//            float lowFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getLowValue());   //저가
//            float lowFloat = startFloat-lowFloatTmp;
//
//            float openFloat = 0f;//시작가격 = 시가
//
//            float closeFloatTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getEndValue());  //종가
//            float closeFloat ;
//
//            float tradingVolumeTmp = Float.parseFloat(candleDataModel.getCandleDataItemList().get(i).getTradingVolume());//거래량
//            chart_float[i] = tradingVolumeTmp; // 거래량 저장하기.
//
//            if(startFloat > closeFloatTmp){
//                closeFloat = startFloat - closeFloatTmp;
//                even = true;
//            }else if(closeFloatTmp > startFloat){
//                closeFloat = closeFloatTmp - startFloat;
//                even = false;
//            }else{
//                closeFloat = 0f;
//            }
//
//            //param1 : X칸의 순서
//            //param2 : 윗꼬리 그리기
//            //param3 : 아래꼬리 그리기
//            //param4 : 사각형 위아래중 한쪽
//            //param5 : 사각형 위아래중 나머지한쪽
//
//            //일봉 차트 데이터 값을 setting 하는 부분
//            yVals1.add(new CandleEntry(
//                    i, startFloat + highFloat,
//                    startFloat - lowFloat,
//                    even ? startFloat + openFloat : startFloat - openFloat,
//                    even ? startFloat - closeFloat : startFloat + closeFloat
//            ));
//
//            //bar 그래프 2
//            barEntries.add(new BarEntry(i, tradingVolumeTmp));  //첫번째 파라미터, x 위치, 두번째 파라미터 Y 값
//
//        }// for END
//
//
//        for(int i = 0 ; i < candleDataModel.getCandleDataItemList().size() ; i++){
//            if(i ==0){
//                chart_color[i] = Color.BLUE;
//            }else{
//                if(chart_float[i-1] > chart_float[i]){
//                    chart_color[i] = Color.BLUE;
//                }else{
//                    chart_color[i] = Color.RED;
//                }
//            }
//
//        }
//
//        //bar 그래프2
//        BarDataSet barDataSet1 = new BarDataSet(barEntries , "거래량");
//        barDataSet1.setColors(chart_color);
//
//        BarData bardata = new BarData(barDataSet1);
//        barChart.setData(bardata);
//
//        //일봉
//        XAxis xAxis = candleChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xLabel.get((int)value);
//            }
//        });
//
//        //일봉
//        YAxis leftAxis = candleChart.getAxisLeft();
//        leftAxis.setEnabled(false);
//
//        //일봉
//        YAxis rightAxis = candleChart.getAxisRight();
//        rightAxis.setLabelCount(7, false);
//        rightAxis.setDrawGridLines(true);
//        rightAxis.setDrawAxisLine(false);
//
//        candleChart.resetTracking();
//
//        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
//
//        set1.setDrawIcons(false);
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setShadowColor(Color.DKGRAY);
//        set1.setShadowWidth(0.7f);
//        set1.setDecreasingColor(Color.BLUE);
//        set1.setDecreasingPaintStyle(Paint.Style.FILL);
//        set1.setIncreasingColor(Color.RED);
//        set1.setIncreasingPaintStyle(Paint.Style.FILL);
//        set1.setDrawValues(false);
//
//        CandleData data = new CandleData(set1);
//
//        candleChart.setData(data);
//        candleChart.invalidate();
//
//        candleChart.getLegend().setEnabled(false);
//    }

    //상단 탭 Adapter
    public class SecionsPagerAdapter extends FragmentPagerAdapter {

        public SecionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return FragmentStockDetailNews.newInstance();   //뉴스 프래그먼트
                case 1 :
                    return FragmentStockDetailChart.newInstance();  //차트 프래그먼트
                case 2 :
                    return FragmentStockDetailInfo.newInstance();   //정보 프래그먼트
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PAGE_NUMBER;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0 :
                    return "뉴스 / 공시";
                case 1 :
                    return "차트";
                case 2 :
                    return "종목정보";
                default:
                    return null;
            }
        }
    }

}//ActivityStockDetail END
