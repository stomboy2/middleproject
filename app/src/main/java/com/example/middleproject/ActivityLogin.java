package com.example.middleproject;

/**
 * Created by stomb on 2018-03-14.
 * 로그인 화면. 앱의 첫 화면.
 */

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;

public class ActivityLogin extends AppCompatActivity {

    RetrofitInterface retrofitInterface;
    EditText loginIdEdit;
    EditText loginPasswordEdit;
    TextView signUpEdit;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Retrofit으로 통신을 하기위한 연결을 생성.
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        loginIdEdit = (EditText) findViewById(R.id.loginId);
        loginPasswordEdit = (EditText) findViewById(R.id.loginPassword);
        signUpEdit = (TextView) findViewById(R.id.signUp);

        Button loginButton = (Button) findViewById(R.id.loginButton);

        //로그인시 아이디랑 비밀번호 check를 위한 dialog 생성
        builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        /** 로그인 버튼이 클릭 되었을때 서버에서 아이디랑 비밀번호를 check 후 값이 있으면 로그인 */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginId = loginIdEdit.getText().toString();
                String password = loginPasswordEdit.getText().toString();


                String resultId = loginId;
                String resultName = "관리자";

                //SharedPreferences에 로그인한 id랑 name을 저장
                SharedPreferences sf = getSharedPreferences("loginValue", 0);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("loginId",resultId );
                editor.putString("loginName",resultName );
                editor.commit();

                //메인 화면 activity로 이동하기
                Intent mainIntent = new Intent(getApplicationContext(),ActivityMain.class);
                startActivity(mainIntent);

                //activity 종료
                finish();
            }
        });

        /** 회원가입 Activity로 이동 */
        signUpEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(getApplication(),ActivitySignUp.class);
                startActivity(signUpIntent);
                //activity 종료
            }
        });
    }
}
