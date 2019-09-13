package com.example.middleproject;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.loginResult;

/**
 * Created by stomb on 2018-03-14.
 * 회원가입 Activity
 */

public class ActivitySignUp extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    RetrofitInterface retrofitInterface;
    private GoogleApiClient mGoogleApiClient = null;
    final int RESOLVE_HINT = 101;

    EditText signUpName ;
    EditText signUpId ;
    EditText signUpPassword ;
    EditText signUpPasswordCheck;
    EditText signUpPhoneNum ;

    /**
     *  정규식 check를 위한 Textview
     */
    TextView signUpNameChk ;
    TextView signUpIdChk ;
    TextView signUpPasswordChk ;
    TextView signUpPasswordCheckChk;
    TextView signUpPhoneNumChk ;

    /**
     *  정규식 check를 위한 LinearLayout
     */
    LinearLayout nameChkLinear ;
    LinearLayout idChkLinear ;
    LinearLayout passwordChkLinear ;
    LinearLayout passwordCheckChkLinear;
    LinearLayout phoneNumChkLinear ;

    Button singupBtn;

    Button phoneSingupBtn;

    ImageView signupBackImg;

    boolean nameFlag = false;
    boolean idFlag = false;
    boolean passwordFlag = false;
    boolean passwordCheckFlag = false;
    boolean phoneNumFlag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //FirebaseInstancd

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);    //Retrofit 통신을 하기위한 연결을 생성.

        signUpName = (EditText) findViewById(R.id.signUpName);
        signUpId = (EditText) findViewById(R.id.signUpId);
        signUpPassword = (EditText) findViewById(R.id.signUpPassword);
        signUpPasswordCheck = (EditText) findViewById(R.id.signUpPasswordCheck);
        signUpPhoneNum = (EditText) findViewById(R.id.signUpPhoneNum);

        signUpNameChk = (TextView) findViewById(R.id.signUpNameChk);
        signUpIdChk = (TextView) findViewById(R.id.signUpIdChk);
        signUpPasswordChk = (TextView) findViewById(R.id.signUpPasswordChk);
        signUpPasswordCheckChk = (TextView) findViewById(R.id.signUpPasswordCheckChk);
        signUpPhoneNumChk = (TextView) findViewById(R.id.signUpPhoneNumChk);

        nameChkLinear = (LinearLayout) findViewById(R.id.nameChkLinear);
        idChkLinear = (LinearLayout) findViewById(R.id.idChkLinear);
        passwordChkLinear = (LinearLayout) findViewById(R.id.passwordChkLinear);
        passwordCheckChkLinear = (LinearLayout) findViewById(R.id.passwordCheckChkLinear);
        phoneNumChkLinear = (LinearLayout) findViewById(R.id.phoneNumChkLinear);

        singupBtn = (Button) findViewById(R.id.signupButton);
        singupBtn.setFocusableInTouchMode(true);

        phoneSingupBtn = (Button) findViewById(R.id.signUpPhoneNumBtn);

        signupBackImg = (ImageView) findViewById(R.id.signupBackImg);

        /** 이름 정규식 check 시작 */
        signUpName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(signUpName.getText().length()==0){
                        nameChkLinear.setVisibility(View.VISIBLE);
                        signUpNameChk.setText("이름을 입력해 주세요\n이름은 한글 및 영문자만 가능합니다.");
                        signUpNameChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpName.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        nameFlag = false;
                    }else if(!Pattern.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣a-z]{2,10}$", signUpName.getText())){
                        nameChkLinear.setVisibility(View.VISIBLE);
                        signUpNameChk.setText("이름은 한글 및 영문자만 가능합니다.");
                        signUpNameChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpName.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        nameFlag = false;
                    }else{
                        nameChkLinear.setVisibility(View.GONE);
                        signUpNameChk.setText("");
                        signUpNameChk.setTextColor(Color.parseColor("#000000"));
                        signUpName.setBackgroundResource(R.drawable.edit_rectangle);
                        nameFlag = true;
                    }
                }
            }
        });
        /** 이름 정규식 check 끝 */

        /** 아이디 정규식 check 시작 */
        signUpId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(signUpId.getText().length() == 0){
                        idChkLinear.setVisibility(View.VISIBLE);
                        signUpIdChk.setText("아이디를 입력해 주세요 \n영문소문자 시작, 영문자+숫자로 5~11자만 가능합니다.");
                        signUpIdChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpId.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        idFlag = false;
                    }else if(!Pattern.matches("^[a-z]+[a-z0-9]{4,10}$", signUpId.getText())){
                        idChkLinear.setVisibility(View.VISIBLE);
                        signUpIdChk.setText("영문소문자 시작, 영문자+숫자로 5~11자만 가능합니다.");
                        signUpIdChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpId.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        idFlag = false;
                    }else{
                        Call<String> idCheck = retrofitInterface.idCheck(signUpId.getText().toString());
                        idCheck.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body().equals("being")){
                                    idChkLinear.setVisibility(View.VISIBLE);
                                    signUpIdChk.setText("이미 존재하는 아이디 입니다.");
                                    signUpIdChk.setTextColor(Color.parseColor("#ffa448"));
                                    signUpId.setBackgroundResource(R.drawable.edit_rectangle_orange);
                                    idFlag = false;
                                }else{
                                    idChkLinear.setVisibility(View.GONE);
                                    signUpIdChk.setText("");
                                    signUpIdChk.setTextColor(Color.parseColor("#000000"));
                                    signUpId.setBackgroundResource(R.drawable.edit_rectangle);
                                    idFlag = true;
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });
        /** 아이디 정규식 check 끝 */

        /** 비밀번호 정규식 check 시작 */
        signUpPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(signUpPassword.getText().length() ==0){
                        passwordChkLinear.setVisibility(View.VISIBLE);
                        signUpPasswordChk.setText("비밀번호를 입력해 주세요\n영문자 + 숫자로 5~10자만 가능합니다.");
                        signUpPasswordChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpPassword.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        passwordFlag=false;
                    }else if(!Pattern.matches("^[a-zA-Z0-9]{5,10}$", signUpPassword.getText())){
                        passwordChkLinear.setVisibility(View.VISIBLE);
                        signUpPasswordChk.setText("영문자 + 숫자로 5~10자만 가능합니다.");
                        signUpPasswordChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpPassword.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        passwordFlag=false;
                    }else{
                        passwordChkLinear.setVisibility(View.GONE);
                        signUpPasswordChk.setText("");
                        signUpPasswordChk.setTextColor(Color.parseColor("#000000"));
                        signUpPassword.setBackgroundResource(R.drawable.edit_rectangle);
                        passwordFlag=true;
                    }
                }
            }
        });
        /** 비밀번호 정규식 check 끝 */

        /**비밀번호 확인 시작 */
        signUpPasswordCheck.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(signUpPasswordCheck.getText().length()==0){
                        passwordCheckChkLinear.setVisibility(View.VISIBLE);
                        signUpPasswordCheckChk.setText("입력한 비밀번호를 다시 입력해 주세요");
                        signUpPasswordCheckChk.setTextColor(Color.parseColor("#ffa448"));
                        signUpPasswordCheck.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        passwordCheckFlag = false;
                    }else if(!signUpPassword.getText().toString().equals(signUpPasswordCheck.getText().toString())){
                        passwordCheckChkLinear.setVisibility(View.VISIBLE);
                        signUpPasswordCheckChk.setText("비밀번호가 일치하지 않습니다. 다시 확인해주세요");
                        signUpPasswordCheck.setBackgroundResource(R.drawable.edit_rectangle_orange);
                        signUpPasswordCheckChk.setTextColor(Color.parseColor("#ffa448"));
                        passwordCheckFlag = false;
                    }else{
                        passwordCheckChkLinear.setVisibility(View.GONE);
                        signUpPasswordCheckChk.setText("");
                        signUpPasswordCheck.setBackgroundResource(R.drawable.edit_rectangle);
                        signUpPasswordCheckChk.setTextColor(Color.parseColor("#000000"));
                        passwordCheckFlag = true;
                    }
                }
            }
        });
        /** 비밀번호 확인 끝 */

        /** 핸드폰 정규식 check 시작 */
//        signUpPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    if(signUpPhoneNum.getText().length()==0){
//                        phoneNumChkLinear.setVisibility(View.VISIBLE);
//                        signUpPhoneNumChk.setText("휴대폰 번호를 입력해 주세요");
//                        signUpPhoneNumChk.setTextColor(Color.parseColor("#ffa448"));
//                        signUpPhoneNum.setBackgroundResource(R.drawable.edit_rectangle_orange);
//                        phoneNumFlag = false;
//                    }else if(!Pattern.matches("^[0-9]{10,11}$", signUpPhoneNum.getText())){
//                        phoneNumChkLinear.setVisibility(View.VISIBLE);
//                        signUpPhoneNumChk.setText("휴대폰 번호를 정확하게 입력해 주세요");
//                        signUpPhoneNumChk.setTextColor(Color.parseColor("#ffa448"));
//                        signUpPhoneNum.setBackgroundResource(R.drawable.edit_rectangle_orange);
//                        phoneNumFlag = false;
//                    }else{
//                        phoneNumChkLinear.setVisibility(View.GONE);
//                        signUpPhoneNumChk.setText("");
//                        signUpPhoneNumChk.setTextColor(Color.parseColor("#000000"));
//                        signUpPhoneNum.setBackgroundResource(R.drawable.edit_rectangle);
//                        phoneNumFlag = true;
//                    }
//                }
//            }
//        });
        /** 핸드폰 정규식 check 끝 */

        /** 회원 가입 버튼을 클릭하여 서버로 값 전송 시작*/
        singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singupBtn.requestFocus();
                if(nameFlag && phoneNumFlag && passwordCheckFlag && passwordFlag &&idFlag ){

                    Call<List<loginResult>> signUp = retrofitInterface.signUp(signUpName.getText().toString(),signUpId.getText().toString(),signUpPassword.getText().toString(),signUpPhoneNum.getText().toString(), "1234");
                    signUp.enqueue(new Callback<List<loginResult>>() {
                        @Override
                        public void onResponse(Call<List<loginResult>> call, Response<List<loginResult>> response) {
                            List<loginResult> resource = response.body();
                            String resultTmp = "";
                            String resultId = "";
                            String resultName = "";
                            for(loginResult result : resource){
                                resultTmp = result.result;
                                resultId = result.id;
                                resultName = result.name;
                            }
                            if(!resultTmp.equals("success")){
                                Toast.makeText(getApplicationContext(),"다시 시도해 주세요",Toast.LENGTH_LONG).show();
                            }else{
                                //로그인 성공시

                                //SharedPreferences에 로그인한 id랑 name을 저장
                                SharedPreferences sf = getSharedPreferences("loginValue", 0);
                                SharedPreferences.Editor editor = sf.edit();
                                editor.putString("loginId",resultId );
                                editor.putString("loginName",resultName );
                                editor.commit();

                                //메인 화면 activity로 이동하기
                                Intent mainIntent = new Intent(getApplicationContext(),ActivityMain.class);
                                startActivity(mainIntent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<loginResult>> call, Throwable t) {

                        }
                    });
                }
            }
        });
        /** 회원 가입 버튼을 클릭하여 서버로 값 전송 끝*/

        /** 뒤로가기 이미지 처리*/
        signupBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();


        //휴대폰 인증
        phoneSingupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestHint();
            }
        });

    } //onCreate End

    //구글api 를 통해 핸드폰 번호를 갖고오게 함
    private void requestHint(){
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),RESOLVE_HINT,null,0,0,0);

        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT){
            if(resultCode == RESULT_OK){
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                signUpPhoneNum.setText(credential.getId());
                    Call<String> phoneCheck = retrofitInterface.phoneCheck(credential.getId());
                    phoneCheck.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body().equals("inUse")){
                                phoneNumChkLinear.setVisibility(View.VISIBLE);
//                                phoneNumFlag=false;
                                phoneNumFlag=true;
                                signUpPhoneNumChk.setText("이미 사용중인 휴대폰 번호입니다.");
                                signUpPhoneNumChk.setTextColor(Color.parseColor("#ffa448"));
                            }else if(response.body().equals("notInUse")){
                                phoneNumChkLinear.setVisibility(View.GONE);
                                phoneNumFlag=true;
                                signUpPhoneNumChk.setText("");
                                signUpPhoneNumChk.setTextColor(Color.parseColor("#000000"));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }//onStop END


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}//ActivitySinup END
