package com.example.middleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


//import com.commexpert.CommExpertMng;
import com.truefriend.corelib.commexpert.intrf.IExpertInitListener;
import com.truefriend.corelib.commexpert.intrf.IExpertLoginListener;


import java.util.List;

import commExpert.CommExpertMng;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CategoredStockListModel;
import retrofitPackage.pojo.kosValueModel;
import retrofitPackage.pojo.stockListModel;

/**
 * Created by stomb on 2018-03-16.
 */

public class ActivityMain extends AppCompatActivity implements IExpertInitListener, IExpertLoginListener {

    private final String BROADCAST_MESSAGE = "com.android.application.broadcastreceiver";
    private final String BROADCAST_ClientToMaster = "com.android.application.clientToMasterIgnore";

    FragmentCurrent currentFragment;
    FragmentChat chatFragment;
    FragmentInformation informationFragment;
    FragmentSetting settingFragment;
    Fragment nowFragment; //뒤로가기 버튼을 처리하기 위한 Fragment

    LinearLayout currentLinear;
    LinearLayout chatLinear;
    LinearLayout informationLinear;
    LinearLayout settingLinear;

    ImageView currentImg;
    ImageView chatImg;
    ImageView informationImg;
    ImageView settingImg;

    TextView currentText;
    TextView chatText;
    TextView informationText;
    TextView settingText;

    private BroadcastReceiver mReceiver =null;

    boolean currentFlag = true;
    boolean chatFlag = false;
    boolean informationFlag = false;
    boolean settingFlag = false;

    RetrofitInterface retrofitInterface;

    static CategoredStockListModel categoredStockListModel;

    // 서버에서 갖고온 주식 목록
    static stockListModel stockList;

    static List<kosValueModel> kosValueItem;

    //service
    String userId;
    String userName;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("오늘의종목");

        SharedPreferences sf = getSharedPreferences("loginValue", 0);
        userId = sf.getString("loginId","");
        userName = sf.getString("loginName","");

        /** Retrofit으로 통신을 하기위한 연결을 생성 */
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

//        //로그인 후 Service 시작(18.04.23)

        //fragment를 framLayout에 넣기 위하여 객체 생성하기
        currentFragment = new FragmentCurrent();
        chatFragment = new FragmentChat();
        informationFragment = new FragmentInformation();
        settingFragment = new FragmentSetting();

        currentLinear =  findViewById(R.id.currentLinear);

        chatLinear = (LinearLayout) findViewById(R.id.chatLinear);
        informationLinear = (LinearLayout) findViewById(R.id.informationLinear);
        settingLinear = (LinearLayout) findViewById(R.id.settingLinear);

        currentImg = (ImageView) findViewById(R.id.currentImg);
        chatImg = (ImageView) findViewById(R.id.chatImg);
        informationImg = (ImageView) findViewById(R.id.informationImg);
        settingImg = (ImageView) findViewById(R.id.settingImg);

        currentText = (TextView) findViewById(R.id.currentText);
        chatText = (TextView) findViewById(R.id.chatText);
        informationText = (TextView) findViewById(R.id.informationText);
        settingText = (TextView) findViewById(R.id.settingText);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
        nowFragment = currentFragment;

        /** 화면 하단 이미지(오늘의 종목) onClick */
        currentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("오늘의종목");
                nowFragment = currentFragment;

                //하반 탭 이미지를 바꿔주기 위한 Flag
                currentFlag = true;
                chatFlag = false;
                informationFlag = false;
                settingFlag = false;
                setImg(currentFlag, chatFlag, informationFlag, settingFlag);

                //하단 탭 글씨 색을 바꿔주기
                currentText.setTextColor(Color.parseColor("#e74c3c"));
                chatText.setTextColor(Color.parseColor("#000000"));
                informationText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#000000"));

                getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
            }
        });//currentLinear.setOnclick END

        /** 화면 하단 이미지(채팅) onClick */
        chatLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("채팅");
                nowFragment = chatFragment;

                //하반 탭 이미지를 바꿔주기 위한 Flag
                currentFlag = false;
                chatFlag = true;
                informationFlag = false;
                settingFlag = false;
                setImg(currentFlag, chatFlag, informationFlag, settingFlag);

                //하단 탭 글씨 색을 바꿔주기
                currentText.setTextColor(Color.parseColor("#000000"));
                chatText.setTextColor(Color.parseColor("#e74c3c"));
                informationText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#000000"));

                getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
            }
        });//chatLinear.setOnclick END

        /** 화면 하단 이미지(종목) onClick */
        informationLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("종목");
                nowFragment = informationFragment;

                //하반 탭 이미지를 바꿔주기 위한 Flag
                currentFlag = false;
                chatFlag = false;
                informationFlag = true;
                settingFlag = false;
                setImg(currentFlag, chatFlag, informationFlag, settingFlag);

                //하단 탭 글씨 색을 바꿔주기
                currentText.setTextColor(Color.parseColor("#000000"));
                chatText.setTextColor(Color.parseColor("#000000"));
                informationText.setTextColor(Color.parseColor("#e74c3c"));
                settingText.setTextColor(Color.parseColor("#000000"));

                getSupportFragmentManager().beginTransaction().replace(R.id.container, informationFragment).commit();
            }
        });//informationLinear.setOnclick END

        /** 화면 하단 이미지(설정) onClick */
        settingLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("더보기");
                nowFragment = settingFragment;

                //하반 탭 이미지를 바꿔주기 위한 Flag
                currentFlag = false;
                chatFlag = false;
                informationFlag = false;
                settingFlag = true;
                setImg(currentFlag, chatFlag, informationFlag, settingFlag);

                //하단 탭 글씨 색을 바꿔주기
                currentText.setTextColor(Color.parseColor("#000000"));
                chatText.setTextColor(Color.parseColor("#000000"));
                informationText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#e74c3c"));
                getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
            }
        });//settingLinear.setOnclick END

        //DB에서 갖고온 주식 목록 값을 AutoCompleteTextView에서 사용하기 위해 item에 값을 setting;
        //실제로 사용하는 부분은 FragmentInformation 에서 사용.
        Call<stockListModel> stockListCall = retrofitInterface.stockListGet();
        stockListCall.enqueue(new Callback<stockListModel>() {
            @Override
            public void onResponse(Call<stockListModel> call, Response<stockListModel> response) {
                stockList =response.body();
            }

            @Override
            public void onFailure(Call<stockListModel> call, Throwable t) {
            }
        });

        //코스피 및 코스닥 값을 갖고오는 통신
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);
        Call<List<kosValueModel>> kosValueCall = retrofitInterface.kosValueGet();
        kosValueCall.enqueue(new Callback<List<kosValueModel>>() {
            @Override
            public void onResponse(Call<List<kosValueModel>> call, Response<List<kosValueModel>> response) {
                kosValueItem = response.body();
            }

            @Override
            public void onFailure(Call<List<kosValueModel>> call, Throwable t) {
            }
        });

//        // 주식(한국투자증권) API를 이용하기 위해 설정해줘야 되는 부분
        CommExpertMng.InitActivity(this);               //api를 사용할 activity를 this로 지정해주기
        CommExpertMng.InitCommExpert(this);     //기본적인 CommExpert를 사용하기 위한 context를 지정해주기
        CommExpertMng.getInstance().SetInitListener(this);    //initListener에 activity를 지정해줘야 api랑 init 부분 통신시 넘어오는 값을 인지할수 있음
        CommExpertMng.getInstance().SetLoginListener(this);   //LoginListener에 activity를 지정해줘야 api에 로그인시 넘어오는 결과값들을 인지할수 있음.
////
        CommExpertMng.getInstance().SetDevSetting("0"); //strDev : 0은 실전, 1은 모의
//
//        //주식 API를 사용하기 위해 API에 접근하는 부분
        CommExpertMng.getInstance().StartLogin("stomboy2","wl9tjs9rn9","!@wl9tjs9rn9");

        // 카테고리가 있는 stockList를 갖고오는 통신
        Call<CategoredStockListModel> categoredStockListModelCall =  retrofitInterface.categoredStockListGet();
        categoredStockListModelCall.enqueue(new Callback<CategoredStockListModel>() {
            @Override
            public void onResponse(Call<CategoredStockListModel> call, Response<CategoredStockListModel> response) {
                categoredStockListModel = response.body();
            }

            @Override
            public void onFailure(Call<CategoredStockListModel> call, Throwable t) {

            }
        });

    }//onCreate END

    /** 화면 하단 이미지 클릭시 fragment에 맞게 이미지 변경 */
    public void setImg(boolean currentFlag, boolean chatFlag, boolean informationFlag, boolean settingFlag){
        if(currentFlag){    //오늘의 종목
            currentImg.setImageResource(R.drawable.current2_red);
        }else{
            currentImg.setImageResource(R.drawable.current2_black);
        }

        if(chatFlag){       //채팅
            chatImg.setImageResource(R.drawable.chat_red);
        }else{
            chatImg.setImageResource(R.drawable.chat_black);
        }

        if(informationFlag){    // 시황정보
            informationImg.setImageResource(R.drawable.infofmation_red);
        }else{
            informationImg.setImageResource(R.drawable.information_black);
        }

        if(settingFlag){    //설정
            settingImg.setImageResource(R.drawable.setting_red);
        }else{
            settingImg.setImageResource(R.drawable.setting_black);
        }
    }//setImg END

    /** 프래그먼트들의 back 처리 */
    @Override
    public void onBackPressed() {
        if(nowFragment == currentFragment){
            currentFragment.onHostBack();
        }else if(nowFragment == chatFragment){
            chatFragment.onHostBack();
        }else if(nowFragment == informationFragment){
            informationFragment.onHostBack();
        }else if(nowFragment == settingFragment){
            settingFragment.onHostBack();
        }
    }

    /** 주식 API 사용을 위해 상속한 class 들의 method들 시작*/
    @Override
    public void onSessionConnecting() {
        Toast.makeText(this, "서버 접속 시작.", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onSessionConnected(boolean b, String s) {
        if(b == true)
        {
			Toast.makeText(this, s, Toast.LENGTH_SHORT ).show();
        }
        else//서버 실패
        {
			Toast.makeText(this, s, Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onAppVersionState(boolean b) {
        Toast.makeText(this, "라이브러리 버젼체크 완료.", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onMasterDownState(boolean b) {
        Toast.makeText(this, "Master 파일 DownLoad...", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onMasterLoadState(boolean b) {
        Toast.makeText(this, "Master 파일 Loading...", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onInitFinished() {

    }

    @Override
    public void onRequiredRefresh() {

    }

    @Override
    public void onLoginResult(boolean isSuccess, String strErrorMsg) {
        // TODO Auto-generated method stub
        if(isSuccess == true )
            Toast.makeText( getBaseContext(), "로그인 TR 성공", Toast.LENGTH_SHORT ).show();
        else
            Toast.makeText( getBaseContext(), strErrorMsg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onAccListResult(boolean isSuccess, String strErrorMsg) {
        // TODO Auto-generated method stub
        if(isSuccess == true )
            Toast.makeText( getBaseContext(), "계좌리스트 조회 TR 성공", Toast.LENGTH_SHORT ).show();
        else
            Toast.makeText( getBaseContext(), strErrorMsg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onPublicCertResult(boolean isSuccess) {
        // TODO Auto-generated method stub
        String strMsg = "";
        if(isSuccess == true )
            strMsg = "공인인증 검증 성공";
        else
            strMsg = "공인인증 검증 실패";

        Toast.makeText( getBaseContext(), strMsg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onLoginFinished() {
        // TODO Auto-generated method stub
        String strMsg = "로그인 성공";
        Toast.makeText( getBaseContext(), strMsg, Toast.LENGTH_SHORT ).show();
    }

    /** 주식 API 사용을 위해 상속한 class 들의 method들 끝*/

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    /** 동적으로(코드상으로) 브로드 캐스트를 등록한다. **/
    private void registerReceiver(){
        /** 1. intent filter를 만든다
         *  2. intent filter에 action을 추가한다.
         *  3. BroadCastReceiver를 익명클래스로 구현한다.
         *  4. intent filter와 BroadCastReceiver를 등록한다.
         * */
        if(mReceiver != null) return;

        final IntentFilter theFilter = new IntentFilter();
        theFilter.addAction(BROADCAST_MESSAGE);

        this.mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(BROADCAST_MESSAGE)){
                    chatFragment.getChatroomList();
                }
            }
        };

        this.registerReceiver(this.mReceiver, theFilter);

    }

    /** 동적으로(코드상으로) 브로드 캐스트를 종료한다. **/
    private void unregisterReceiver() {
        if(mReceiver != null){
            this.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }


//    @Override
//    public void onSessionConnecting() {
//
//    }
//
//    @Override
//    public void onSessionConnected(boolean b, String s) {
//
//    }
//
//    @Override
//    public void onAppVersionState(boolean b) {
//
//    }
//
//    @Override
//    public void onMasterDownState(boolean b) {
//
//    }
//
//    @Override
//    public void onMasterLoadState(boolean b) {
//
//    }
//
//    @Override
//    public void onInitFinished() {
//
//    }
//
//    @Override
//    public void onRequiredRefresh() {
//
//    }
//
//    @Override
//    public void onLoginResult(boolean b, String s) {
//
//    }
//
//    @Override
//    public void onAccListResult(boolean b, String s) {
//
//    }
//
//    @Override
//    public void onPublicCertResult(boolean b) {
//
//    }
//
//    @Override
//    public void onLoginFinished() {
//
//    }
}//ActivityMain END

