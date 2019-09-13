package com.example.middleproject;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.ChatroomUserImgListModel;
import retrofitPackage.pojo.ChatroomUserInfoModel;
import retrofitPackage.pojo.JoinChatRoomModel;

/**
 * Created by stomb on 2018-03-16.
 * 채팅방 목록을 확인할 수 있는 Fragment
 */

public class FragmentChat extends Fragment {

    //뒤로가기 버튼을 눌렀을시 앱 종료를 위한 상수
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private RecyclerView chatRecyclerView;
    private ChatRecyclerAdapter chatRecyclerAdapter;
    final int REQUEST_ACT = 1001;

    String id = "";
    String name = "";

    RetrofitInterface retrofitInterface;

    JoinChatRoomModel joinChatRoomModel;
    ChatroomUserInfoModel chatroomUserInfoItem;

    HashMap<String, String> UserImgMap = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("LOG", "onCreate() - FragmentChat");
        Context context = getActivity();
        SharedPreferences sf = context.getSharedPreferences("loginValue", 0);
        id = sf.getString("loginId", "");
        name = sf.getString("loginName", "");

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);
        //채팅방 유저들의 img uri 갖고 오기
        getChatroomImg();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);
        Log.e("LOG", "onCreateView() - FragmentChat");

        chatRecyclerView = (RecyclerView) rootView.findViewById(R.id.chat_recyclerView);
        getChatroomList();

        return rootView;
    }//oncreateView END


    /**
     * chatFragment 뒤로가기 함수
     */
    public void onHostBack() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            getActivity().finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getContext(), "한번 더 뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }//chatFragment onHostBack END

    /**
     * 회원별 chatRoom을 보여주기 위한 RecyclerView 설정
     */

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView chat_title;
        TextView chat_date;
        TextView chat_content;
        TextView chat_count;
        ImageView chat_img;
        LinearLayout chat_linear;

        public ViewHolder(View itemView) {
            super(itemView);
            chat_title = (TextView) itemView.findViewById(R.id.chat_title);
            chat_date = (TextView) itemView.findViewById(R.id.chat_date);
            chat_count = (TextView) itemView.findViewById(R.id.chat_count);
            chat_content = (TextView) itemView.findViewById(R.id.chat_content);
            chat_linear = (LinearLayout) itemView.findViewById(R.id.chat_linear);
            chat_img = (ImageView) itemView.findViewById(R.id.chat_img);

        }
    }//ViewHolder END

    public class ChatRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat_recyclerview_item, parent, false));
        }//onCreateViewHolder END


        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int positionTmp = position;
            int count = 0;
            ArrayList<String> choiceUserId = new ArrayList<>();

            holder.chat_title.setText(joinChatRoomModel.getJoinChatRoomItemList().get(position).getTitle());
            holder.chat_count.setText(joinChatRoomModel.getJoinChatRoomItemList().get(position).getTotal());
            holder.chat_img.setBackground(new ShapeDrawable(new OvalShape()));
            holder.chat_img.setClipToOutline(true);

            //이미지 넣어주기
            for (int i = 0; i < chatroomUserInfoItem.getChatroomUserImgList().size(); i++) {
                if (joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getRoomNo().equals(chatroomUserInfoItem.getChatroomUserImgList().get(i).getRoomNo()) && !id.equals(chatroomUserInfoItem.getChatroomUserImgList().get(i).getId())) {
                    count++;
                    choiceUserId.add(chatroomUserInfoItem.getChatroomUserImgList().get(i).getId());
                    Log.e("확인하기", "방번호 = "  + joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getRoomNo() + " 해당 유저 = " + chatroomUserInfoItem.getChatroomUserImgList().get(i).getId());
                }
            }
            if(count == 1){
                Glide.with(getActivity()).load(UserImgMap.get(choiceUserId.get(0))).into(holder.chat_img);
            }

            //방을 선택했을때 해당 채팅방으로 접속하기
//            holder.chat_linear.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent chatRoomIntent = new Intent(getContext(), ActivityChatRoom.class);
//                    chatRoomIntent.putExtra("roomNo", joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getRoomNo());
//                    chatRoomIntent.putExtra("id", joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getId());
//                    chatRoomIntent.putExtra("title", joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getTitle());
//                    chatRoomIntent.putExtra("name", name);
//                    chatRoomIntent.putExtra("newRoomFlag", "false");
//                    chatRoomIntent.putExtra("profile", UserImgMap.get(joinChatRoomModel.getJoinChatRoomItemList().get(positionTmp).getId()));
//
//                    startActivityForResult(chatRoomIntent, REQUEST_ACT);
//                }
//            });
        }//onBindViewHolder END


        @Override
        public int getItemCount() {
            return joinChatRoomModel.getJoinChatRoomItemList().size();
        }//getItemCount END
    }//ChatRecyclerAdapter END

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACT) {
            getChatroomList();
        }
    }

    @Override
    public void onDetach() {
        Log.e("LOG", "onDetach() - FragmentChat");
        super.onDetach();
    }

    // 채팅방 리스트를 갖고 오기.
    // 채티방 리스트를 통해 화면에 채팅방 목록을 만듬
    public void getChatroomList() {
        Call<JoinChatRoomModel> chatRoomModelCall = retrofitInterface.getJoinChatRoomList(id);
        chatRoomModelCall.enqueue(new Callback<JoinChatRoomModel>() {
            @Override
            public void onResponse(Call<JoinChatRoomModel> call, Response<JoinChatRoomModel> response) {
                joinChatRoomModel = response.body();

                chatRecyclerAdapter = new ChatRecyclerAdapter();
                chatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                chatRecyclerView.setHasFixedSize(true);
                chatRecyclerView.setAdapter(chatRecyclerAdapter);

            }//onResponse END

            @Override
            public void onFailure(Call<JoinChatRoomModel> call, Throwable t) {

            }//onFailure END
        });
    }

    //client 들의 프로필 이미지를 갖고 오기
    //카카오톡 단체톡처럼 프로필을 나누기 위해서 client들의 프로필 이미지가 필요
    public void getChatroomImg() {
        Call<ChatroomUserInfoModel> chatroomUserInfoImgCall = retrofitInterface.getChatroomuserImg();
        chatroomUserInfoImgCall.enqueue(new Callback<ChatroomUserInfoModel>() {
            @Override
            public void onResponse(Call<ChatroomUserInfoModel> call, Response<ChatroomUserInfoModel> response) {
                chatroomUserInfoItem = response.body();
                for (int i = 0; i < chatroomUserInfoItem.getChatroomUserImgList().size(); i++) {
                    ChatroomUserImgListModel chatroomUserImgListModel = new ChatroomUserImgListModel();
                    chatroomUserImgListModel.setId(chatroomUserInfoItem.getChatroomUserImgList().get(i).getId());
                    chatroomUserImgListModel.setRoomNo(chatroomUserInfoItem.getChatroomUserImgList().get(i).getRoomNo());
                    chatroomUserImgListModel.setProfile(chatroomUserInfoItem.getChatroomUserImgList().get(i).getProfile());

                    UserImgMap.put(chatroomUserInfoItem.getChatroomUserImgList().get(i).getId(), chatroomUserInfoItem.getChatroomUserImgList().get(i).getProfile());
                }
            }

            @Override
            public void onFailure(Call<ChatroomUserInfoModel> call, Throwable t) {

            }
        });
    }

    public Bitmap resizeBitmapImage(Bitmap source, int maxResolution) {
        int width = source.getWidth();
        int height = source.getHeight();
        int newWidth = width;
        int newHeight = height;
        float rate = 0.5f;

        if (width > height) { //가로가 더 길떄
            if (maxResolution < width) {
                rate = maxResolution / (float) width;
                newHeight = (int) (height * rate);
                newWidth = maxResolution;
            }
        } else {    //세로가 더 길때
            if (maxResolution < height) {
                rate = maxResolution / (float) height;
                newWidth = (int) (width * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
    }

    private Bitmap combineImage2(Bitmap b1, Bitmap b2) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inDither = true;

        Bitmap bitmap = null;

        b1 = resizeBitmapImage(b1, 500);
        b2 = resizeBitmapImage(b2, 500);

        bitmap = Bitmap.createScaledBitmap(b1, b1.getWidth(), b1.getHeight(), true);

        Paint p = new Paint();
        p.setDither(true);
        p.setFlags(Paint.ANTI_ALIAS_FLAG);

        Canvas c = new Canvas(bitmap);
        c.drawBitmap(b1, -b1.getWidth() / 2, 0, p);

        c.drawBitmap(b2, b1.getWidth() / 2, 0, p);

        b1.recycle();
        b2.recycle();

        return bitmap;
    }
}//FragmentChat END