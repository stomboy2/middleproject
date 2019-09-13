package com.example.middleproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.MyInfoModel;

/**
 * Created by stomb on 2018-05-01.
 * 내정보 Activity.
 * 로그인한 client의 프로필사진, 이름, 아이디, 핸드폰 번호를 확인할수 있다.
 * 프로필 사진은 갤러리 & 카메라를 통해서 변경 가능
 */

public class  ActivityMyInfo extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;
    final int REQ_CODE_SELECT_CAMERA=101;
    final int REQUEST_IMAGE_CAPTURE = 672;

    private String imageFilePath;   //카메라 촬영시 실제로 이미지가 저장되는 위치
    private Uri photoUri;

    TextView myInfoName;
    TextView myInfoId;
    TextView myInfoPhoneNum;
    ImageView myInfoProfile;
    ImageView myInfoBack;

    String userId;      // sharedPreferences 에서 갖고온 userid, server
                        // Server에서 현재 접속한 client의 핸드폰번호, 이름, 프로필 사진을 갖고오거나 다시 저장할때 사용.

    RetrofitInterface retrofitInterface;

    public static ActivityMyInfo activityMyInfo ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);

        //로그인한 client의 ID 갖고 오기.
        SharedPreferences sf = getSharedPreferences("loginValue", 0);
        userId = sf.getString("loginId","");

        activityMyInfo = ActivityMyInfo.this;

        myInfoName = (TextView) findViewById(R.id.my_info_name);
        myInfoId = (TextView) findViewById(R.id.my_info_id);
        myInfoPhoneNum = (TextView) findViewById(R.id.my_info_phoneNum);

        myInfoProfile = (ImageView) findViewById(R.id.my_info_profile);
        myInfoBack = (ImageView) findViewById(R.id.my_info_back);

        //프로필 이미지 layout을  동그랗게 만들기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myInfoProfile.setBackground(new ShapeDrawable(new OvalShape()));
        }
        if(Build.VERSION.SDK_INT >= 21){
            myInfoProfile.setClipToOutline(true);
        }

        //프로필 이미지를 카메라 및 갤러리에서 선택하여 변경하기.
        myInfoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그 setting 하는 부분 시작
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ActivityMyInfo.this);

                // 다이얼로그의 제목셋팅
                alertDialogBuilder.setTitle("프로필 변경");

                // 다이얼로그 셋팅
                alertDialogBuilder
                        .setMessage("프로필을 변경 방법")
                        .setCancelable(false)
                        .setPositiveButton("갤러리",
                                new DialogInterface.OnClickListener() {
                                    public void onClick( DialogInterface dialog, int id) {  //갤러리를 선택하여 프로필 이미지를 변경할때
                                        Intent imgIntent = new Intent(Intent.ACTION_PICK);
                                        imgIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                                        imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(imgIntent, REQ_CODE_SELECT_IMAGE);
                                    }
                                })
                        .setNegativeButton("사진찍기",
                                new DialogInterface.OnClickListener() { //카메라 촬영을 통해 프로필 이미지를 변경할때
                                    public void onClick( DialogInterface dialog, int id) {
//                                        Intent cameraIntent = new Intent(getApplicationContext(), ActivityOpenCv.class);
//                                        startActivity(cameraIntent);
//                                        sendTakePhotoIntent();
                                    }
                                });
                //다이얼로그 setting 하는 부분 끝

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        //화면 상단에 뒤로가기 버튼 클릭시 activity 종료 하기.
        myInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //내정보 Activity에 접근했을때 server를 통해 로그인한 client의 핸드폰번호, 이름, 프로필사진 을 갖고와서
        //위젯에 setting 해주기
        Call<MyInfoModel> myInfoCall = retrofitInterface.getMyInfo(userId);
        myInfoCall.enqueue(new Callback<MyInfoModel>() {
            @Override
            public void onResponse(Call<MyInfoModel> call, Response<MyInfoModel> response) {
                MyInfoModel myInfoModel = response.body();
                myInfoName.setText(myInfoModel.getMyinfo().get(0).getName());

                myInfoId.setText(userId);
                myInfoId.setClickable(false);
                myInfoId.setFocusable(false);

                //회원가입시 핸드폰번호가 국가번호인 +082로 저장되기 때문에 client에게 보여줄땐 010으로 보여주기 위해 처리
                String phoneNumTmp = myInfoModel.getMyinfo().get(0).getPhoneNum();
                String phoneNumTmp2 = phoneNumTmp.replace("+82","0");
                myInfoPhoneNum.setText(phoneNumTmp2);

                //user(DB)에 해당 client의 profile이 저장되어 있다면 위젯에 값을 초기화 시키고 저장되어 있지 않다면 기본값을 보여준다.
                if(myInfoModel.getMyinfo().get(0).getProfile() != null){
                    Glide.with(getApplicationContext()).load(myInfoModel.getMyinfo().get(0).getProfile()).into(myInfoProfile);
                }
            }

            @Override
            public void onFailure(Call<MyInfoModel> call, Throwable t) {

            }
        });
    }

    //startActivityForResult 사용시 결과값을 받기 위해 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //앨범에서 이미지를 갖고오기에 대한 결과
        if(requestCode == REQ_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri imgPath = data.getData();

                //갖고온 이미지를 위젯에 setting
                Glide.with(getApplicationContext()).load(imgPath).into(myInfoProfile);

                //변경된 이미지를 server로 업로드 후 client의 user(DB)에 업로드 시킨 사진의 위치를 저장
                //getRealPathFromURI은 갤러리에서 갖고온 사진의 경로와 실제 사진이 저장되어 있는 경로가 달라서 실제 사진이 저장되어 있는 경로를 추출하는 함수
                File file = new File(getRealPathFromURI(imgPath));

                //이미지를 서버로 upload 하기 위해 multipart를 이용하는데 그때 필요한 값들 setting
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody multiUserId = createPartFromString(userId);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

                //변경한 프로필 사진을 서버로 전송
                Call<Void> uploadImg = retrofitInterface.uploadImage(body, multiUserId);
                uploadImg.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        }

        //카메라로 사진을 찍었을때의 결과
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            ExifInterface exif = null;

            //사진을 앨범에 저장
            galleryAddPic();

//            try {
//                exif = new ExifInterface(imageFilePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            int exifOrientation;
//            int exifDegree;
//
//            if (exif != null) {
//                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                exifDegree = exifOrientationToDegrees(exifOrientation);
//            } else {
//                exifDegree = 0;
//            }
            Glide.with(getApplicationContext()).load(photoUri).into(myInfoProfile);

            //변경된 이미지를 server로 업로드 후 client의 user(DB)에 업로드 시킨 사진의 위치를 저장시킴.
            //카메라로 찍은 이미지는 저장시 실제경로를 구했기 때문에 갤러리에서와는 다르게 실제경로를 구하는 함수를 사용 안함.
            String realPath = imageFilePath;
            File file = new File(realPath);

            //이미지를 서버로 upload 하기 위해 multipart를 이용하는데 그때 필요한 값들 setting
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            RequestBody multiUserId = createPartFromString(userId);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

            Call<Void> uploadImg = retrofitInterface.uploadImage(body, multiUserId);
            uploadImg.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });

        }
    }

    //Retrofit을 이용해 이미지를 server로 전송하기 위해서는 이미지 절대 경로를 갖고 와야 한다.
    //Intent.ACTION_PICK를 통한 onActivityResult에서
    private String getRealPathFromURI(Uri contentURI) {
        String filePath;
        Cursor cursor = getApplication().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            filePath = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            filePath = cursor.getString(idx);
            cursor.close();
        }
        return filePath;
    }

    //multipart 이용시 string 값을 전송하기 위해 사용
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    //촬영한 사진이 돌아갔는지 안돌아 갔는지 여부 check?
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    //돌아간 사진 정위치 시키기
    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    //사진 촬영 버튼을 눌렀을때 실행.
    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = CustomUtils.createImageFile();
                imageFilePath = photoFile.getAbsolutePath();        //파일의 실제 경로를 구하기.
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                photoUri = providerURI;
                // 인텐트에 전달할 때는 FileProvier의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    //실제로 이미지 파일을 만드는 것은 아니고 빈껍데기인 파일을 먼저 만들기? 더 알아보기
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "TEST_" + timeStamp + ".jpg";
//
//        File imageFile = null;
//        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "gyeom");
//
//        if (!storageDir.exists()) {
//            Log.i("mCurrentPhotoPath1", storageDir.toString());
//            storageDir.mkdirs();
//        }
//
//        imageFile = new File(storageDir, imageFileName);
//        imageFilePath = imageFile.getAbsolutePath();
//        Log.e("파일경로 = ", imageFilePath +" imageFileName = " + imageFileName + " storageDir = " + String.valueOf(storageDir));
//        return imageFile;
//    }

    //촬영한 사진을 실제 핸드폰 저장소에 저장하기
    private void galleryAddPic(){
        Log.i("galleryAddPic", "Call");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // 해당 경로에 있는 파일을 객체화(새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(imageFilePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }
    //카메라 사용을 위한 함수들 끝

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ActivityMyInfo Activity에서 나갈 시 화면에 있는 이름과 핸드폰 번호를 User(DB)에 Update
        String phoneNumTmp = myInfoPhoneNum.getText().toString();
        String phoneNumTmp2 = "+82" + phoneNumTmp.substring(1);

        Call<Void> myInfoUpdate = retrofitInterface.postMyInfoUpdate(userId, myInfoName.getText().toString(), phoneNumTmp2);
        myInfoUpdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
