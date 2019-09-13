package com.example.middleproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import SectionRecycler.SectionParameters;
import SectionRecycler.SectionedRecyclerViewAdapter;
import SectionRecycler.StatelessSection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CategoredStockItemTmp;
import retrofitPackage.pojo.CategoredStockListModel;
import retrofitPackage.pojo.ChatRoomModel;
import retrofitPackage.pojo.StockCategoryListModel;
import retrofitPackage.pojo.kosValueModel;
import retrofitPackage.pojo.stockListModel;

/**
 * Created by stomb on 2018-03-16.
 * 주식 종목을 보고 해당 항목을 클릭할수 있는 Fragment
 */

public class FragmentInformation extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;

    //뒤로가기 버튼을 눌렀을시 앱 종료를 위한 상수 선언
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    TextView kospiPrice;                //코스피값
    TextView kospiRatio;                //코스피 변동값
    TextView kospiRatioPercent;         //코스피 변동률
    TextView kospiForeignerChange;      //코스피 외인
    TextView kospiOrganChange;          //코스피 기관
    TextView kospiIndividualChange;     //코스피 개인

    TextView kosdaqPrice;               //코스닥값
    TextView kosdaqRatio;               //코스닥 변동값
    TextView kosdaqRatioPercent;        //코스닥 변동률
    TextView kosdaqForeignerChange;     //코스닥 외인
    TextView kosdaqOrganChange;         //코스닥 기관
    TextView kosdaqIndividualChange;    //코스닥 개인

    ImageView kospiImg;                 //코스피 이미지
    ImageView kosdaqImg;                //코스닥 이미지

    String kospiPriceStr;               //DB에서 갖고온 코스피 가격
    String kospiRatioStr;               //DB에서 갖고온 코스피 변동값
    String kospiRatioPercentStr;        //DB에서 갖고온 코스피 변동률
    String kospiForeignerChangeStr;     //DB에서 갖고온 코스피 외인값
    String kospiOrganChangeStr;         //DB에서 갖고온 코스피 기관값
    String kospiIndividualChangeStr;    //DB에서 갖고온 코스피 개인값

    String kosdaqPriceStr;              //DB에서 갖고온 코스닥 가격
    String kosdaqRatioStr;              //DB에서 갖고온 코스닥 변동값
    String kosdaqRatioPercentStr;       //DB에서 갖고온 코스닥 변동률
    String kosdaqForeignerChangeStr;    //DB에서 갖고온 코스닥 외인값
    String kosdaqOrganChangeStr;        //DB에서 갖고온 코스닥 기관값
    String kosdaqIndividualChangeStr;   //DB에서 갖고온 코스닥 개인값

    String kospiForeignerColorStr ;     //DB에서 갖고온 코스피 외인 상승 하락 구분값
    String kospiOrganColorStr;          //DB에서 갖고온 코스피 기관 상승 하락 구분값
    String kospiIndividualColorStr;     //DB에서 갖고온 코스피 개인 상승 하락 구분값

    String kosdaqForeignerColorStr;     //DB에서 갖고온 코스닥 외인 상승 하락 구분값
    String kosdaqOrganColorStr;         //DB에서 갖고온 코스닥 기관 상승 하락 구분값
    String kosdaqIndividualColorStr;    //DB에서 갖고온 코스닥 개인 상승 하락 구분값

    String userId;          //sharedpreparence에서 갖고오는 id. 해당 기기의 로그인한 사용자의 id
    String userName;        //sharedpreparence에서 갖고오는 name. 해당 기기의 로그인한 사용자의 name
    CategoredStockListModel categoredStockListModel;
    StockCategoryListModel stockCategoryListModel;

    boolean kospiColorFlag = true;
    boolean kosdaqColorFlag = true;

    String minus = "-";

    RetrofitInterface retrofitInterface; //HTTP 통신을 하기 위해 retrofitinterface 선언
    stockListModel item;                //MainActivity에서 통신을 통해 갖고온 주식목록을 담기위한 변수 선언
    List<kosValueModel> kosValueItem2;  //MainActivity에서 통신을 통해 갖고온 코스피&닥 값을 담기위한 리스트 선언

    // AutoCompleteTextView에서 목록으로 사용하기 위한 주식 목록 배열
    String[] itemsName ;
    String[] itemsCode ;
    String[] itemsNumberOfStock;

    List<CategoredStockListModel> categoredStockList = new ArrayList<>();
    List<StockCategoryListModel> categoryList = new ArrayList<>();

    ChatRoomModel chatRoomModel;

    Handler kosValueHandler;
    //recyclerView 선언 및 recyclerAdapter 선언
//    private RecyclerView recyclerView = null;
//    private RecyclerAdapter recyclerAdapter = null;

    List<CategoredStockItemTmp> categoredStockItemTmps ;
    List<CategoredStockItemTmp> categoredStockItemTmps2 ;

    String roomNo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity();
        SharedPreferences sf = context.getSharedPreferences("loginValue", 0);
        userId = sf.getString("loginId","");
        userName = sf.getString("loginName","");

        Log.e("fragmentInformation", userId);

        // 통신을 위한 객체 생성
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        // 서버에서 갖고온 주식 목록
        // 서버를 통해 갖고오는 값이 많아 시간이 걸리기 때문에 main에서 통신을 통해 미리 값을 갖고온후 여기서 불러다 쓰기.
        item = ActivityMain.stockList;
        kosValueItem2 = ActivityMain.kosValueItem;

        //item의 사이즈 만큼 배열의 크기를 정함.
        itemsName = new String[item.getStockList().size()];
        itemsCode = new String[item.getStockList().size()];
        itemsNumberOfStock = new String[item.getStockList().size()];

        for(int i = 0; i< item.getStockList().size(); i++){
            itemsName[i] = item.getStockList().get(i).getCompanyName();
            itemsCode[i] = item.getStockList().get(i).getStockCode();
            itemsNumberOfStock[i] = item.getStockList().get(i).getNumberOfStock();
        }

        //Information 화면 로딩시 MainActivity에서 서버 통신을 통해 갖고온 kosValueItem2값을 이용해 코스피&닥 정보를 setting
        //MainActivity 에서 미리 로딩하지 않고 Information 화면을 불러올떄 통신을 하면 통신결과가 오기전에 화면이 만들어져서 값 setting이 되지 않음.
        kosValueSetting(kosValueItem2);

        //카테고리 목록을 갖고 오는 통신
//        Call<StockCategoryListModel> stockCategoryListModelCall = retrofitInterface.stockCategoryListGet();
//        stockCategoryListModelCall.enqueue(new Callback<StockCategoryListModel>() {
//            @Override
//            public void onResponse(Call<StockCategoryListModel> call, Response<StockCategoryListModel> response) {
//                stockCategoryListModel = response.body();
////                for(int i = 0 ; i < stockCategoryListModel.getStockCategoryList().size(); i++){
////                    StockCategoryListModel stockCategoryListModelTmp = new StockCategoryListModel();
////                    StockCategoryItem stockCategoryItem = new StockCategoryItem();
////                    stockCategoryItem.setNo(stockCategoryListModel.getStockCategoryList().get(i).getNo());
////                    stockCategoryItem.setCategoryName(stockCategoryListModel.getStockCategoryList().get(i).getCategoryName());
////                    stockCategoryListModel.setStockCategoryItems(stockCategoryItem);
//
////                }
//            }
//
//            @Override
//            public void onFailure(Call<StockCategoryListModel> call, Throwable t) {
//
//            }
//        });
    }//onCreate

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_information, container, false);

        kospiPrice = (TextView) rootView.findViewById(R.id.kospiPrice);
        kospiRatio = (TextView) rootView.findViewById(R.id.kospiRatio);
        kospiRatioPercent = (TextView) rootView.findViewById(R.id.kospiRatioPercent);
        kospiForeignerChange = (TextView) rootView.findViewById(R.id.kospiForeignerChange);
        kospiOrganChange = (TextView) rootView.findViewById(R.id.kospiOrganChange);
        kospiIndividualChange = (TextView) rootView.findViewById(R.id.kospiIndividualChange);

        kosdaqPrice  = (TextView) rootView.findViewById(R.id.kosdaqPrice);
        kosdaqRatio   = (TextView) rootView.findViewById(R.id.kosdaqRatio);
        kosdaqRatioPercent   = (TextView) rootView.findViewById(R.id.kosdaqRatioPercent);
        kosdaqForeignerChange = (TextView) rootView.findViewById(R.id.kosdaqForeignerChange);
        kosdaqOrganChange = (TextView) rootView.findViewById(R.id.kosdaqOrganChange);
        kosdaqIndividualChange = (TextView) rootView.findViewById(R.id.kosdaqIndividualChange);

        categoredStockListModel = ActivityMain.categoredStockListModel;

        kospiImg = (ImageView) rootView.findViewById(R.id.kospiImg);
        kosdaqImg = (ImageView) rootView.findViewById(R.id.kosdaqImg);

        //title 을 이용하여 room 번호를 알아내기 위한 roomNo
        kosValueViewSetting();

        //텍스트뷰의 자동완성을 위한 AutoCompleteTextView 객체 edit 생성.
        AutoCompleteTextView edit = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView);

        //AutoCompleteTextView 객체 edit에 adapter를 붙여주기
        //items : 주식목록을 담고 있는 배열
        edit.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, itemsName));

        //자동완성시 생성되는 목록 클릭시 이벤트 처리
        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i< item.getStockList().size(); i++){
                    if(((TextView)view).getText().toString().equals(itemsName[i])){
                        stockDetailView(itemsCode[i], ((TextView)view).getText().toString(), itemsNumberOfStock[i]);
                    }
                }
            }
        });

    /** recyclerView 1*/
        List<String> tmp = new ArrayList<>();
        tmp.add("안희정주");
        tmp.add("대북주");
        categoredStockItemTmps = new ArrayList<>();
        categoredStockItemTmps2 = new ArrayList<>();
        for(int i = 0 ; i < categoredStockListModel.getCategoredStockList().size() ; i++){
            if(categoredStockListModel.getCategoredStockList().get(i).getCategory().equals("1")){
                CategoredStockItemTmp categoredStockItemTmp = new CategoredStockItemTmp();
                categoredStockItemTmp.setCategoryName( categoredStockListModel.getCategoredStockList().get(i).getCategoryName());
                categoredStockItemTmp.setCompanyName(categoredStockListModel.getCategoredStockList().get(i).getCompanyName());
                categoredStockItemTmp.setStockCode(categoredStockListModel.getCategoredStockList().get(i).getStockCode());
                categoredStockItemTmp.setNumberOfStock(categoredStockListModel.getCategoredStockList().get(i).getNumberOfStock());
                categoredStockItemTmps.add(categoredStockItemTmp);
            }else{
                CategoredStockItemTmp categoredStockItemTmp = new CategoredStockItemTmp();
                categoredStockItemTmp.setCategoryName( categoredStockListModel.getCategoredStockList().get(i).getCategoryName());
                categoredStockItemTmp.setCompanyName(categoredStockListModel.getCategoredStockList().get(i).getCompanyName());
                categoredStockItemTmp.setStockCode(categoredStockListModel.getCategoredStockList().get(i).getStockCode());
                categoredStockItemTmp.setNumberOfStock(categoredStockListModel.getCategoredStockList().get(i).getNumberOfStock());
                categoredStockItemTmps2.add(categoredStockItemTmp);
            }
        }


        sectionAdapter =  new SectionedRecyclerViewAdapter();

        Call<ChatRoomModel> chatRoomModelCall = retrofitInterface.getChatRoomList();
        chatRoomModelCall.enqueue(new Callback<ChatRoomModel>() {
            @Override
            public void onResponse(Call<ChatRoomModel> call, Response<ChatRoomModel> response) {
                chatRoomModel = response.body();
                //title과 db에서 갖고온 title을 매치시켜서 방번호 알아내기
                for(int i = 0 ; i < chatRoomModel.getChatRoomItemList().size() ; i++){
                    if(chatRoomModel.getChatRoomItemList().get(i).getTitle().equals("대북주")){
                        roomNo = chatRoomModel.getChatRoomItemList().get(i).getNo();
                        sectionAdapter.addSection(new MySection("대북주",categoredStockItemTmps, roomNo ));

                    }else if(chatRoomModel.getChatRoomItemList().get(i).getTitle().equals("안희정주")){
                        roomNo = chatRoomModel.getChatRoomItemList().get(i).getNo();
                        sectionAdapter.addSection(new MySection("안희정주",categoredStockItemTmps2, roomNo ));
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<ChatRoomModel> call, Throwable t) {

            }
        });


        //RecyclerView 선언 및 Adapter 연결
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.frg_info_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(sectionAdapter);

        //코스닥 코스피 정보를 스레드를 통해 20초 간격으로
        KosValueThread kosValueThread = new KosValueThread();
        kosValueThread.start();

        //스레드를 통해 디비에서 얻어온 코스닥 & 코스피 정보를 위젯들에게 setting 하는 부분
        kosValueHandler = new KosValueHandler();

        return rootView;
    }//onCreateView END

    @Override
    public void onResume() {
        super.onResume();
        if(ActivityStockDetail.m_PriceRealProc != null){
            ActivityStockDetail.m_PriceRealProc.ClearInstance();
            ActivityStockDetail.m_PriceRealProc = null;
        }
    }

    /** recyclerView 1*/
    /**
    //커스텀 뷰 홀더
    //Item layout에 존재하는 위젯들을 바인딩. 바인딩?
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView stockCode;
        TextView companyName;
        LinearLayout recyclerview_linear;

        public ViewHolder(View itemView) {
            super(itemView);
            stockCode = itemView.findViewById(R.id.recyclerview_stockCode);
            companyName = itemView.findViewById(R.id.recyclerview_stockName);
            recyclerview_linear = itemView.findViewById(R.id.recyclerview_linear);
        }
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{

        private Context context;

        public RecyclerAdapter(Context context){
            this.context = context;
        }

        //새로운 뷰 홀더 생성
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_information_recyclerview_item, parent,false));
        }

        // View의 내용을 해당 포지션의 데이터로 바꿈
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int positionTmp = position;
            holder.stockCode.setText(item.getStockList().get(position).getStockCode());
            holder.companyName.setText(item.getStockList().get(position).getCompanyName());

            //recyclerView에서 item을 클릭했을때 처리.
            holder.recyclerview_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        //데이터의 사이즈를 리턴
        @Override
        public int getItemCount() {
            return item.getStockList().size();
        }
    }
    */

    /** RecyclerView 중 Section을 포함한 RecyclerView의 구조를 만듬??  */
    private class MySection extends StatelessSection{

        String title;
        List<CategoredStockItemTmp> categoredStockItemTmps2;
        String roomNo;
        boolean expanded = true;
        /**
         * Create a stateless Section object based on {@link SectionParameters}.
         *
         *
         */
        public MySection(String title,List<CategoredStockItemTmp>  categoredStockItemTmps2 , String roomNo) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.fragment_information_recyclerview_item)
                    .headerResourceId(R.layout.fragment_infromation_recyclerview_header)
                    .build());

            this.categoredStockItemTmps2 = categoredStockItemTmps2;
            this.title = title;
            this.roomNo = roomNo;
        }

//        public MySection(String title) {
//            super(SectionParameters.builder()
//                    .itemResourceId(R.layout.fragment_information_recyclerview_item)
//                    .headerResourceId(R.layout.fragment_infromation_recyclerview_header)
//                    .build());
//
//            this.title = title;
//        }

        @Override
        public int getContentItemsTotal() {
            return expanded ? categoredStockItemTmps2.size() : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new MyItemViewHolder(view);
        }

        //recyclerView item부분 값setting 및 이벤트 처리하는곳
        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
            final int positionTmp = position;
//            itemHolder.stockCode.setText(item.getStockList().get(position).getStockCode());
//            itemHolder.companyName.setText(item.getStockList().get(position).getCompanyName());
            itemHolder.stockCode.setText(categoredStockItemTmps2.get(positionTmp).getStockCode());
            itemHolder.companyName.setText(categoredStockItemTmps2.get(positionTmp).getCompanyName());

            //recyclerView에서 item을 클릭했을때 처리.
            itemHolder.recyclerview_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stockDetailView(categoredStockItemTmps2.get(positionTmp).getStockCode(),categoredStockItemTmps2.get(positionTmp).getCompanyName(), categoredStockItemTmps2.get(positionTmp).getNumberOfStock());
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new myHeaderViewHolder(view);
        }

        //recyclerView header부분 값setting 및 이벤트 처리하는곳
        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final myHeaderViewHolder headerHolder = (myHeaderViewHolder) holder;

            headerHolder.session_header.setText(title);


            headerHolder.session_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    headerHolder.session_arrow.setImageResource(
                            expanded ? R.drawable.ic_keyboard_arrow_up_black_18dp : R.drawable.ic_keyboard_arrow_down_black_18dp
                    );
                    sectionAdapter.notifyDataSetChanged();
                }
            });

            //recyclerView header에서 chat 이미지를 클릭했을때
//            headerHolder.session_chat.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent chatRoomIntent = new Intent(getContext(), ActivityChatRoom.class);
//                    chatRoomIntent.putExtra("roomNo", roomNo);
//                    chatRoomIntent.putExtra("id", userId);
//                    chatRoomIntent.putExtra("title", title);
//                    chatRoomIntent.putExtra("name", userName);
//                    chatRoomIntent.putExtra("newRoomFlag", "join");
//                    startActivity(chatRoomIntent);
//                }
//            });
        }
    }

    /** RecyclerView header부분 값 setting */
    private class myHeaderViewHolder extends RecyclerView.ViewHolder{

        private final View rootView;
        private final TextView session_header;
        private final ImageView session_chat;
        private final ImageView session_arrow;

        myHeaderViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            session_header = (TextView) itemView.findViewById(R.id.session_header);
            session_chat = (ImageView) itemView.findViewById(R.id.session_chat);
            session_arrow = (ImageView) itemView.findViewById(R.id.session_arrow);
        }
    }

    /** RecyclerView Item부분 값 setting */
    private class MyItemViewHolder extends RecyclerView.ViewHolder{

        private final View rootView;
        private final TextView stockCode;
        private final TextView companyName;
        private final LinearLayout recyclerview_linear;

        MyItemViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            stockCode = itemView.findViewById(R.id.recyclerview_stockCode);
            companyName = itemView.findViewById(R.id.recyclerview_stockName);
            recyclerview_linear = itemView.findViewById(R.id.recyclerview_linear);
        }

    }

    /** 20초에 한번씩 Thread가 돌면서 DB에 저장되어 있는 코스피 및 코스닥의 정보를 갖고오는 부분 */
    class KosValueThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                try {
                    Call<List<kosValueModel>> kosValueCall = retrofitInterface.kosValueGet();
                    kosValueCall.enqueue(new Callback<List<kosValueModel>>() {
                        @Override
                        public void onResponse(Call<List<kosValueModel>> call, Response<List<kosValueModel>> response) {
                            List<kosValueModel> kosValueItem = response.body();
                            kosValueSetting(kosValueItem);                //통신이 성공하면 갖고온 값들을 변수에 Setting
                            kosValueHandler.sendEmptyMessage(0);    //통신이 성공하면 View 위젯들의 값을 바궈주는 Handler 호출
                        }
                        @Override
                        public void onFailure(Call<List<kosValueModel>> call, Throwable t) {

                        }
                    });
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }//run
    }//Thread

    /** FragmentInformation 에서 Thread가 돌면서 코스피코스닥 값을 갱신 */
    class KosValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            kosValueViewSetting();
        }
    }

    /** informationFragment 뒤로가기 함수 */
    public void onHostBack() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            getActivity().finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getContext(), "한번 더 뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }//informationFragment onHostBack END

    /** DB에서 갖고 온 값 변수에 저장하는 부분 */
    public void kosValueSetting(List<kosValueModel> kosValueModel){
        for(kosValueModel result : kosValueModel){
            if(!result.kospiPrice.equals("")){
                kospiPriceStr = result.kospiPrice;
                kospiRatioStr = result.kospiRatio;
                kospiRatioPercentStr =  result.kospiRatioPercent.substring(1);
                kospiForeignerChangeStr = result.kospiForeigner;
                kospiOrganChangeStr = result.kospiOrgan;
                kospiIndividualChangeStr = result.kospiIndividual;
                kospiForeignerColorStr = result.kospiForeignerChange;
                kospiOrganColorStr = result.kospiOrganChange;
                kospiIndividualColorStr = result.kospiIndividualChange;

                kosdaqPriceStr = result.kosdaqPrice;
                kosdaqRatioStr = result.kosdaqRatio;
                kosdaqRatioPercentStr = result.kosdaqRatioPercent.substring(1);
                kosdaqForeignerChangeStr = result.kosdaqForeigner;
                kosdaqOrganChangeStr = result.kosdaqOrgan;
                kosdaqIndividualChangeStr = result.kosdaqIndividual;
                kosdaqForeignerColorStr = result.kosdaqForeignerChange;
                kosdaqOrganColorStr = result.kosdaqOrganChange;
                kosdaqIndividualColorStr = result.kosdaqIndividualChange;

                //상승이면 코스피&닥 하위 내용들의 글씨색을 빨간색로 주기 위한 flag
                if(result.kospiRatioPercent.substring(0,1).equals("+")){
                    kospiColorFlag = true;
                }else{
                    kospiColorFlag = false;
                }

                //하락이면 코스피&닥 하위 내용들의 글씨색을 파란색을 주기 위한 flag
                if(result.kosdaqRatioPercent.substring(0,1).equals("+")){
                    kosdaqColorFlag = true;
                }else{
                    kosdaqColorFlag = false;
                }
            }//if
        }//for
    }//kosValueSetting Class End

    /** DB에서 갖고온 값들을 View에 Setting 하는 부분 */
    public void kosValueViewSetting(){
        kospiPrice.setText(kospiPriceStr);
        kospiRatio.setText(kospiRatioStr);
        kospiRatioPercent.setText(kospiRatioPercentStr);

        kosdaqPrice.setText(kosdaqPriceStr);
        kosdaqRatio.setText(kosdaqRatioStr);
        kosdaqRatioPercent.setText(kosdaqRatioPercentStr);

        if(kospiColorFlag){ //코스피가 상승중이면 빨간색
            kospiPrice.setTextColor(Color.parseColor("#FFD41620"));
            kospiRatio.setTextColor(Color.parseColor("#FFD41620"));
            kospiRatioPercent.setTextColor(Color.parseColor("#FFD41620"));
            kospiImg.setImageResource(R.drawable.redarrow);
        }else{//코스피가 하락중이면 파란색
            kospiPrice.setTextColor(Color.parseColor("#303F9F"));
            kospiRatio.setTextColor(Color.parseColor("#303F9F"));
            kospiRatioPercent.setTextColor(Color.parseColor("#303F9F"));
            kospiImg.setImageResource(R.drawable.bluearrow);
        }

        if(kosdaqColorFlag){ //코스닥이 상승중이면 빨간색
            kosdaqPrice.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqRatio.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqRatioPercent.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqImg.setImageResource(R.drawable.redarrow);
        }else{ //코스닥이 하락중이면 파란색
            kosdaqPrice.setTextColor(Color.parseColor("#303F9F"));
            kosdaqRatio.setTextColor(Color.parseColor("#303F9F"));
            kosdaqRatioPercent.setTextColor(Color.parseColor("#303F9F"));
            kosdaqImg.setImageResource(R.drawable.bluearrow);
        }

        //코스피 외인이 상승이면 빨간색 하락이면 파란색
        if(kospiForeignerColorStr.equals("up")){
            kospiForeignerChange.setTextColor(Color.parseColor("#FFD41620"));
            kospiForeignerChange.setText(kospiForeignerChangeStr);
        }else{
            kospiForeignerChange.setTextColor(Color.parseColor("#FF0022FA"));
            kospiForeignerChange.setText(kospiForeignerChangeStr);
        }

        //코스피 기관이 상승이면 빨간색 하락이면 파란색
        if(kospiOrganColorStr.equals("up")){
            kospiOrganChange.setTextColor(Color.parseColor("#FFD41620"));
            kospiOrganChange.setText(kospiOrganChangeStr);
        }else{
            kospiOrganChange.setTextColor(Color.parseColor("#FF0022FA"));
            kospiOrganChange.setText(kospiOrganChangeStr);
        }

        //코스피 개인이 상승이면 빨간색 하락이면 파란색
        if(kospiIndividualColorStr.equals("up")){
            kospiIndividualChange.setTextColor(Color.parseColor("#FFD41620"));
            kospiIndividualChange.setText(kospiIndividualChangeStr);
        }else{
            kospiIndividualChange.setTextColor(Color.parseColor("#FF0022FA"));
            kospiIndividualChange.setText(kospiIndividualChangeStr);
        }

        //코스닥 외인이 상승이면 빨간색 하락이면 파란색
        if(kosdaqForeignerColorStr.equals("up")){
            kosdaqForeignerChange.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqForeignerChange.setText(kosdaqForeignerChangeStr);
        }else{
            kosdaqForeignerChange.setTextColor(Color.parseColor("#FF0022FA"));
            kosdaqForeignerChange.setText(kosdaqForeignerChangeStr);
        }

        //코스닥 기관이 상승이면 빨간색 하락이면 파란색
        if(kosdaqOrganColorStr.equals("up")){
            kosdaqOrganChange.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqOrganChange.setText(kosdaqOrganChangeStr);
        }else{
            kosdaqOrganChange.setTextColor(Color.parseColor("#FF0022FA"));
            kosdaqOrganChange.setText(kosdaqOrganChangeStr);
        }

        //코스닥 개인이 상승이면 빨간색 하락이면 파란색
        if(kosdaqIndividualColorStr.equals("up")){
            kosdaqIndividualChange.setTextColor(Color.parseColor("#FFD41620"));
            kosdaqIndividualChange.setText(kosdaqIndividualChangeStr);
        }else{
            kosdaqIndividualChange.setTextColor(Color.parseColor("#FF0022FA"));
            kosdaqIndividualChange.setText(kosdaqIndividualChangeStr);
        }
    }

    /** 자동완성 및 recyclerView에서 item 클릭시 Activity 이동 */
    public void stockDetailView(String stockCode, String stockName, String numberOfStock){
        Intent stockDetailIntent = new Intent(getContext(),ActivityStockDetail.class);
        stockDetailIntent.putExtra("stockCode", stockCode);
        stockDetailIntent.putExtra("stockName", stockName);
        stockDetailIntent.putExtra("numberOfStock", numberOfStock);
        startActivity(stockDetailIntent);
    }

}//FragmentInformation END
