package retrofitPackage;

/**
 * Created by stomb on 2018-03-13.
 */

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import retrofitPackage.pojo.CategoredStockListModel;
import retrofitPackage.pojo.ChatRoomModel;
import retrofitPackage.pojo.ChatroomUserInfoModel;
import retrofitPackage.pojo.ChattingListModel;
import retrofitPackage.pojo.CompanyInfoItem;
import retrofitPackage.pojo.CompanyInfoModel;
import retrofitPackage.pojo.CurrentStockModel;
import retrofitPackage.pojo.JoinChatRoomModel;
import retrofitPackage.pojo.MyInfoModel;
import retrofitPackage.pojo.StockCategoryListModel;
import retrofitPackage.pojo.StockDetailListModel;
import retrofitPackage.pojo.Test;
import retrofitPackage.pojo.CandleDataModel;
import retrofitPackage.pojo.kosValueModel;
import retrofitPackage.pojo.loginResult;
import retrofitPackage.pojo.stockListModel;


public interface RetrofitInterface {


    @GET("dbSelect.php")
    Call<Test> doGetListResources();

    @FormUrlEncoded
    @POST("dbInsert.php")
    Call<Test> createUser(@Field("name") String name, @Field("id") String id);

    //로그인시 id, password가 있는지 check
    @FormUrlEncoded
    @POST("loginCheck.php")
    Call<List<loginResult>> loginCheck(@Field("id") String id, @Field("password") String password);

    //회원가입시 아이디가 중복되는지 check
    @FormUrlEncoded
    @POST("idCheck.php")
    Call<String> idCheck(@Field("id") String id);

    //회원가입시 입력된 data들을 DB에 저장하기 위해
    @FormUrlEncoded
    @POST("signUp.php")
    Call<List<loginResult>> signUp(@Field("name") String name, @Field("id") String id, @Field("password") String password, @Field("phoneNum") String phoneNum, @Field("token") String token);

    //FCM에서 사용. 토큰이 갱신되었을때 해당 ID를 이용하여 token을 갱신.
    @FormUrlEncoded
    @POST("registerToken.php")
    Call<String> sendToken(@Field("id") String id, @Field("token") String token);

    //회원가입시 핸드폰 중복 check.
    @FormUrlEncoded
    @POST("phoneCheck.php")
    Call<String> phoneCheck(@Field("phone") String phone);

    //주식목록 DB에서 갖고오기
    @GET("stockListGet.php")
    Call<stockListModel> stockListGet();

    //코스피 & 코스닥 Value get
    @GET("kosValueGet.php")
    Call<List<kosValueModel>> kosValueGet();

    //종목탭 카테고리 리스트 get
    @GET("stockCategoryListGet.php")
    Call<StockCategoryListModel> stockCategoryListGet();

    //종목탭 카테고리가 되어있는 종목 갖고오기
    @GET("categoredStockGet.php")
    Call<CategoredStockListModel> categoredStockListGet();

    //rss을 통해 저장되어 있는 뉴스 및 공시 갖고 오기
    @FormUrlEncoded
    @POST("rssGet.php")
    Call<StockDetailListModel> getStockdetailList(@Field("find") String find);

    //rss을 통해 저장되어 있는 뉴스 및 공시 갖고 오기
    @FormUrlEncoded
    @POST("rssGetNews.php")
    Call<StockDetailListModel> getStockdetailNewsList(@Field("find") String find);

    //rss을 통해 저장되어 있는 뉴스 및 공시 갖고 오기
    @FormUrlEncoded
    @POST("rssGetDisclosure.php")
    Call<StockDetailListModel> getStockdetailDisclosureList(@Field("find") String find);

    //종목 상세보기 화면 chart 그리기 위한 데이터 get
    @FormUrlEncoded
    @POST("candleDataSelect.php")
    Call<CandleDataModel> getcandleData(@Field("stockName") String stockName);

    //아직 사용하지 않음, chart 데이터를 db에 넣기 위해 사용
    @FormUrlEncoded
    @POST("candleDataInsert.php")
    Call<Void> postCandleData(@Field("stockName") String stockName, @Field("startValue") String startValue, @Field("highValue") String highValue, @Field("lowValue") String lowValue, @Field("endValue") String endValue, @Field("tradingVolume") String tradingVolume);

    //로그인한 유저가 속한 chatroom 번호를 알기위해 get
    @FormUrlEncoded
    @POST("chatroomNo.php")
    Call<JoinChatRoomModel> getJoinChatRoomList(@Field("id") String id);

    //종목탭 카테고리가 되어있는 종목 갖고오기
    @GET("chatroomList.php")
    Call<ChatRoomModel> getChatRoomList();

    //1:1문의하기 클릭시 chatroom에 방 추가
    @FormUrlEncoded
    @POST("chatroomInsert.php")
    Call<String> postchatroomInsert(@Field("title") String title, @Field("userId") String userId);


    @FormUrlEncoded
    @POST("chattingGet.php")
    Call<ChattingListModel> getChattingMsg(@Field("roomNo") String roomNo, @Field("joinFlag") String joinFlag, @Field("userId") String userId );

    //단체톡방을 중간에 들어가게 되면 진입시점부터 chatting을 볼수 있어야 해서 db(chatting)에서 마지막 값을 갖고 와야함
    @GET("chattingLastNum.php")
    Call<String> getChattingLastNum();

    //내정보 Activity에 필요한 값들을 로그인한 id를 이용해서 갖고오기
    @FormUrlEncoded
    @POST("myInfoGet.php")
    Call<MyInfoModel> getMyInfo(@Field("id") String id);

    //내정보 Activity에서 수정된 이미지를 server에 저장.
    @Multipart
    @POST("uploadImg.php")
    Call<Void> uploadImage(@Part MultipartBody.Part file, @Part("multiUserId")RequestBody multiUserId);

    //내정보 Activity가 destroy 될때 화면에 있는 이름과 핸드폰 값을 user(DB)에 저장하기
    @FormUrlEncoded
    @POST("myInfoUpdate.php")
    Call<Void> postMyInfoUpdate(@Field("id") String id, @Field("name") String name, @Field("phoneNum") String phoneNum);

    //
    @GET("chatroomUserImg.php")
    Call<ChatroomUserInfoModel> getChatroomuserImg();

    //오늘의 종목 등록하기
    @FormUrlEncoded
    @POST("currentStockPost.php")
    Call<String> postCurrentStock(@Field("title") String title, @Field("name") String name, @Field("stockName") String stockName, @Field("buyPrice") String buyPrice, @Field("targetPrice") String targetPrice, @Field("cutPrice") String cutPrice, @Field("content") String content, @Field("date") String date);

    //오늘의 종목 목록 갖고오기
    @GET("currentStockGet.php")
    Call<CurrentStockModel> getCurrentStock();

    //오늘의 종목 상세정보 갖고오기
    @FormUrlEncoded
    @POST("currentStockGet2.php")
    Call<CurrentStockModel> getCurrentStock2(@Field("no") String no);

    //오늘의종목 클릭스 조회수 증가시키기
    @FormUrlEncoded
    @POST("currentStockViewPlus.php")
    Call<Void> postCurrentStockViewPlus(@Field("no") String no, @Field("view") int view);

    //영상통화 시도. 상대방에게 영상통화 화면 띄워주기
    @FormUrlEncoded
    @POST("videoTelephony.php")
    Call<String> postVideoTelephony(@Field("no") String no, @Field("userId") String userId);

    @GET("videoTelephonyIgnoreCToA.php")
    Call<Void> postVideoTelephonyIgnoreCToA();

    @FormUrlEncoded
    @POST("videoTelephonyIgnoreAToC.hp")
    Call<Void> postVideoTelephonyIgnoreAToC(@Field("userId") String userId);

    @GET("companyInfo.php")
    Call<CompanyInfoModel> getCompanyInfo();

    @FormUrlEncoded
    @POST("companyInfo.php")
    Call<CompanyInfoModel> postCompanyInfo(@Field("stockCode") String stockCode);
}
