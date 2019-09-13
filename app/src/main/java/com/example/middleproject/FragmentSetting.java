package com.example.middleproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;

/**
 * Created by stomb on 2018-03-16.
 */

public class FragmentSetting extends Fragment {

    //뒤로가기 버튼을 눌렀을시 앱 종료를 위한 상수
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    TextView setting_myinfo;
    TextView setting_chat;
    TextView setting_logout;
    TextView setting_withdrawal;

    LinearLayout setting_chat_linear;
    RetrofitInterface retrofitInterface;

    String userId = "";
    String userName = "";
    String title ="";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity();
        SharedPreferences sf = context.getSharedPreferences("loginValue", 0);

        userId = sf.getString("loginId","");
        userName = sf.getString("loginName","");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        setting_myinfo = (TextView) rootView.findViewById(R.id.setting_myinfo);
        setting_chat = (TextView) rootView.findViewById(R.id.setting_chat);
        setting_logout = (TextView) rootView.findViewById(R.id.setting_logout);
        setting_withdrawal = (TextView) rootView.findViewById(R.id.setting_withdrawal);
        setting_chat_linear = (LinearLayout) rootView.findViewById(R.id.setting_chat_linear);

        if(userId.equals("admin")){
            setting_chat.setVisibility(View.GONE);
            setting_chat_linear.setVisibility(View.GONE);
        }
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        // 내정보보기를 클릭했을때
        setting_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myInfoIntent = new Intent(getContext(), ActivityMyInfo.class);
                startActivity(myInfoIntent);
            }
        });

        //제목 설정
        title = userName + "님의 문의";

        //1:1문의를 클릭했을때
//        setting_chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//
//                alertDialogBuilder.setTitle("1:1문의하기");
//
//                alertDialogBuilder
//                        .setMessage("문의 방법 선택")
//                        .setCancelable(true)
//                        .setPositiveButton("채팅",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Call<String> chatroomInsertCall = retrofitInterface.postchatroomInsert(title, userId);
//                                        chatroomInsertCall.enqueue(new Callback<String>() {
//                                            @Override
//                                            public void onResponse(Call<String> call, Response<String> response) {
//                                                String roomNo = response.body();
//                                                Intent chatRoomIntent = new Intent(getContext(), ActivityChatRoom.class);
//                                                chatRoomIntent.putExtra("roomNo", roomNo);
//                                                chatRoomIntent.putExtra("id", userId);
//                                                chatRoomIntent.putExtra("title", title);
//                                                chatRoomIntent.putExtra("name", userName);
//                                                chatRoomIntent.putExtra("newRoomFlag", "true");
//                                                startActivity(chatRoomIntent);
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<String> call, Throwable t) {
//
//                                            }
//                                        });
//                                    }
//                                })
//                        .setNegativeButton("영상통화",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        //영상통화 시작하기
//                                        Intent videotelephonyActivity = new Intent(getContext(), ConnectActivity.class);
//                                        startActivity(videotelephonyActivity);
//                                    }
//                                });
//
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            }
//        });

        //로그아웃을 클릭했을때
        setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goActivityLogin = new Intent(getContext(), ActivityLogin.class);
                goActivityLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                goActivityLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goActivityLogin);
            }
        });

        setting_withdrawal.setVisibility(View.GONE);
        //회원탈퇴를 클릭햇을때
        setting_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }//onCreateView END


    /** SettingFragment 뒤로가기 함수 */
    public void onHostBack() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            getActivity().finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getContext(), "한번 더 뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }//SettingFragment onHostBack END


}//FragementSetting END
