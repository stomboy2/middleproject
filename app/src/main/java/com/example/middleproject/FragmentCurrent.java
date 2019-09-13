package com.example.middleproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.CategoredStockListModel;
import retrofitPackage.pojo.CurrentStockItemTmp;
import retrofitPackage.pojo.CurrentStockModel;
import retrofitPackage.pojo.StockCategoryListModel;

/**
 * Created by stomb on 2018-03-16.
 * 오늘의 종목 Activity
 */

public class FragmentCurrent extends Fragment {

    //뒤로가기 버튼을 눌렀을시 앱 종료를 위한 상수
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private int postCurrentStock = 104;

    RecyclerView current_recyclerView;
    CurrentStockRecyclerAdapter currentStockRecyclerAdapter;

    //SharedPreferences에 저장되어 있는 로그인한 client의 id랑 name을 저장할 변수선언
    String id="";
    String name="";

    int view = 0;

    FloatingActionButton current_write;
    RetrofitInterface retrofitInterface;
    List<CurrentStockItemTmp> currentStockItemList ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();

        SharedPreferences sf = context.getSharedPreferences("loginValue", 0);
        id = sf.getString("loginId","");
        name = sf.getString("loginName","");

        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);
        if(currentStockItemList != null ){
            currentStockItemList.clear();
        }else{
            getCurrentStockList();
        }

    }//onCreate END

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_current, container, false);


        current_write = (FloatingActionButton) rootView.findViewById(R.id.current_write);
        current_recyclerView = (RecyclerView) rootView.findViewById(R.id.current_recyclerView);

        //FloatingActionButton은 리딩자일때만 화면에 보임.
        //리딩자만 오늘의 종목을 작성할 수 있음.
        if(id.equals("admin")){
            current_write.setVisibility(View.VISIBLE);
        }else{
            current_write.setVisibility(View.GONE);
        }


        current_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  redingWriteIntent = new Intent(getContext(), ActivityredingWrite.class);
                startActivityForResult(redingWriteIntent,postCurrentStock);
            }
        });

        //오늘의 종목탭의 글 목록list를 갖고오는 함수
//        if(currentStockRecyclerAdapter == null){
//            getCurrentStockList();
//        }

        return rootView;
    }//onCreateView END

    //recycler 작성

    //recyclerView ViewHolder
    class CurrentStockViewHolder extends RecyclerView.ViewHolder{

        TextView current_item_title;
        TextView current_item_name;
        TextView current_item_date;
        TextView current_item_view;
        LinearLayout current_item_linear;

        public CurrentStockViewHolder(View itemView) {
            super(itemView);
            current_item_title = (TextView) itemView.findViewById(R.id.current_item_title);
            current_item_name = (TextView) itemView.findViewById(R.id.current_item_name);
            current_item_date = (TextView) itemView.findViewById(R.id.current_item_date);
            current_item_view = (TextView) itemView.findViewById(R.id.current_item_view);

            current_item_linear = (LinearLayout) itemView.findViewById(R.id.current_item_linear);

        }
    }

    //recyclerView.Adapter
    public class CurrentStockRecyclerAdapter extends RecyclerView.Adapter<CurrentStockViewHolder>{

        @Override
        public CurrentStockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CurrentStockViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.fragment_current_recyclerview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(CurrentStockViewHolder holder, final int position) {


            holder.current_item_title.setText(currentStockItemList.get(position).getTitle());
            holder.current_item_date.setText(currentStockItemList.get(position).getDate());
            holder.current_item_name.setText(currentStockItemList.get(position).getName());

            view = currentStockItemList.get(position).getView();
            holder.current_item_view.setText(String.valueOf(view));

            //오늘의 종목 클릭시 해당 요일에 해당하는 상세정보를 보기위한 Activity로 이동
            holder.current_item_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view = currentStockItemList.get(position).getView();
                    view = view +1;
                    Call<Void> postCurrentStockViewPlus = retrofitInterface.postCurrentStockViewPlus(currentStockItemList.get(position).getNo(), view);
                    postCurrentStockViewPlus.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent currentStockDetail = new Intent(getContext(), ActivityCurrentStockDetail.class);
                            currentStockDetail.putExtra("no", currentStockItemList.get(position).getNo());
                            startActivity(currentStockDetail);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return currentStockItemList.size();
        }
    }

    /** currentFragment 뒤로가기 함수 */
    public void onHostBack() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            getActivity().finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getContext(), "한번 더 뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }//currentFragment onHostBack END


    //오늘의 종목을 작성 후 FragmentCurrent 화면으로 되돌아 왔을때 서버를 통해서 방금 작성한 글을 갱신해와야함.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == postCurrentStock) {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }

    //Server를 통해서 오늘의 종목 내용을 갖고오는 함수
    public void getCurrentStockList(){
        Log.e("getcurrentStocklist","호출");
        currentStockItemList = new ArrayList<>();
        Call<CurrentStockModel> currentStockModelCall = retrofitInterface.getCurrentStock();
        currentStockModelCall.enqueue(new Callback<CurrentStockModel>() {
            @Override
            public void onResponse(Call<CurrentStockModel> call, Response<CurrentStockModel> response) {
                CurrentStockModel currentStockModel = response.body();
                CurrentStockItemTmp currentStockItem ;
                for( int i = 0 ; i < currentStockModel.getCurrentStockList().size() ; i++){
                    currentStockItem = new CurrentStockItemTmp();
                    currentStockItem.setNo(currentStockModel.getCurrentStockList().get(i).getNo());
                    currentStockItem.setTitle(currentStockModel.getCurrentStockList().get(i).getTitle());
                    currentStockItem.setName(currentStockModel.getCurrentStockList().get(i).getName());
                    currentStockItem.setDate(currentStockModel.getCurrentStockList().get(i).getDate());
                    currentStockItem.setView(currentStockModel.getCurrentStockList().get(i).getView());
                    currentStockItemList.add(currentStockItem);

                    currentStockRecyclerAdapter = new CurrentStockRecyclerAdapter();
                    current_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    current_recyclerView.setHasFixedSize(true);
                    current_recyclerView.setAdapter(currentStockRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<CurrentStockModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(currentStockRecyclerAdapter != null){
            currentStockItemList.clear();
            getCurrentStockList();
        }
    }
}// FragmentCurrent END
