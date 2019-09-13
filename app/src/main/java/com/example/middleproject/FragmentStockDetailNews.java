package com.example.middleproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitPackage.RetrofitConnect;
import retrofitPackage.RetrofitInterface;
import retrofitPackage.pojo.StockDetailListItem;
import retrofitPackage.pojo.StockDetailListModel;

/**
 * Created by stomb on 2018-04-07.
 */

public class FragmentStockDetailNews extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    StockDetailListModel stockDetailListitem;   // 통신을 통해 갖고온 해당 종목 기사를 담을 변수 선언
    StockDetailListModel stockDetailListitemNews;
    StockDetailListModel stockDetailListitemDisclosure;
    StockDetailListModel stockDetailListitemOriginal;

    RetrofitInterface retrofitInterface;

    RadioGroup radio_group;
    RadioButton radio_all;
    RadioButton radio_news;
    RadioButton radio_disclosure;

    String flag = "1";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitInterface = RetrofitConnect.getClient().create(RetrofitInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stock_detail_news, container, false);

        String stockName = ActivityStockDetail.stockName;

        Call<StockDetailListModel> stockdetailListGet = retrofitInterface.getStockdetailList(stockName);
        stockdetailListGet.enqueue(new Callback<StockDetailListModel>() {
            @Override
            public void onResponse(Call<StockDetailListModel> call, Response<StockDetailListModel> response) {
                stockDetailListitem = response.body();
                stockDetailListitemOriginal = stockDetailListitem;
                recyclerAdapter = new RecyclerAdapter();
                recyclerView = rootView.findViewById(R.id.stock_detail_recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<StockDetailListModel> call, Throwable t) {

            }
        });

        Call<StockDetailListModel> stockDetailListNewsList = retrofitInterface.getStockdetailNewsList(stockName);
        stockDetailListNewsList.enqueue(new Callback<StockDetailListModel>() {
            @Override
            public void onResponse(Call<StockDetailListModel> call, Response<StockDetailListModel> response) {
                stockDetailListitemNews = response.body();
            }

            @Override
            public void onFailure(Call<StockDetailListModel> call, Throwable t) {

            }
        });

        Call<StockDetailListModel> stockDetailListDisclosureList = retrofitInterface.getStockdetailDisclosureList(stockName);
        stockDetailListDisclosureList.enqueue(new Callback<StockDetailListModel>() {
            @Override
            public void onResponse(Call<StockDetailListModel> call, Response<StockDetailListModel> response) {
                stockDetailListitemDisclosure = response.body();
            }

            @Override
            public void onFailure(Call<StockDetailListModel> call, Throwable t) {

            }
        });

        RadioGroup  radio_group;
        RadioButton radio_all;
        RadioButton radio_news;
        RadioButton radio_disclosure;

        radio_group = (RadioGroup) rootView.findViewById(R.id.information_radio_group);
        radio_all = (RadioButton) rootView.findViewById(R.id.information_radio_all);
        radio_news = (RadioButton) rootView.findViewById(R.id.information_radio_news);
        radio_disclosure = (RadioButton) rootView.findViewById(R.id.information_radio_disclosure);

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.information_radio_all:
                        flag = "1";
                        stockDetailListitem = stockDetailListitemOriginal;
                        recyclerAdapter.notifyDataSetChanged();
                        break;
                    case R.id.information_radio_news:
                        flag = "2";
                        recyclerAdapter.notifyDataSetChanged();
                        break;
                    case R.id.information_radio_disclosure:
                        flag = "3";
                        recyclerAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        return rootView;
    }

    public static FragmentStockDetailNews newInstance() {
        Bundle args = new Bundle();
        FragmentStockDetailNews fragment = new FragmentStockDetailNews();
        fragment.setArguments(args);
        return fragment;
    }

    //커스텀 뷰 홀더
    //Item layout에 존재하는 위젯들을 바인딩. 바인딩?
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView detail_title;
        TextView detail_rssName;
        TextView detail_date;

        LinearLayout stock_detail_recyclerview_linear;

        public ViewHolder(View itemView) {
            super(itemView);
            detail_title = itemView.findViewById(R.id.stock_detail_recyclerview_title);
            detail_rssName = itemView.findViewById(R.id.stock_detail_recyclerview_rssname);
            detail_date = itemView.findViewById(R.id.stock_detail_recyclerview_date);
            stock_detail_recyclerview_linear = itemView.findViewById(R.id.stock_detail_recyclerview_linear);
        }
    }//ViewHolder END


    //해당 종목의 뉴스를 확인할수 있는 recycler를 setting
    public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        //새로운 view 홀더를 생성
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.activity_stock_detail_recyclerview_item, parent, false));
        }//ViewHolder END

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int positionTmp = position;
            String rssNameTmp = "";
            if(flag.equals("1")){
                holder.detail_title.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getTitle());
//             holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());
                holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());


                if (stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("edaily")) {
                    rssNameTmp = "이데일리";
                } else if (stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("hankuk")) {
                    rssNameTmp = "한국경제";
                } else if (stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("herald")) {
                    rssNameTmp = "헤럴드경제";
                } else if (stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("meail")) {
                    rssNameTmp = "매일경제";
                } else if (stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName().equals("moneytoday")) {
                    rssNameTmp = "머니투데이";
                }else{
                    rssNameTmp = stockDetailListitem.getStockDetailListItemLIst().get(position).getRssName();
                }

            }else if(flag.equals("2")){
                holder.detail_title.setText(stockDetailListitemNews.getStockDetailListItemLIst().get(position).getTitle());
//             holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());
                holder.detail_date.setText(stockDetailListitemNews.getStockDetailListItemLIst().get(position).getDate());

                if (stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName().equals("edaily")) {
                    rssNameTmp = "이데일리";
                } else if (stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName().equals("hankuk")) {
                    rssNameTmp = "한국경제";
                } else if (stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName().equals("herald")) {
                    rssNameTmp = "헤럴드경제";
                } else if (stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName().equals("meail")) {
                    rssNameTmp = "매일경제";
                } else if (stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName().equals("moneytoday")) {
                    rssNameTmp = "머니투데이";
                }else{
                    rssNameTmp = stockDetailListitemNews.getStockDetailListItemLIst().get(position).getRssName();
                }
            }else {
                holder.detail_title.setText(stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getTitle());
//             holder.detail_date.setText(stockDetailListitem.getStockDetailListItemLIst().get(position).getDate());
                holder.detail_date.setText(stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getDate());

                if (stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName().equals("edaily")) {
                    rssNameTmp = "이데일리";
                } else if (stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName().equals("hankuk")) {
                    rssNameTmp = "한국경제";
                } else if (stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName().equals("herald")) {
                    rssNameTmp = "헤럴드경제";
                } else if (stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName().equals("meail")) {
                    rssNameTmp = "매일경제";
                } else if (stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName().equals("moneytoday")) {
                    rssNameTmp = "머니투데이";
                }else{
                    rssNameTmp = stockDetailListitemDisclosure.getStockDetailListItemLIst().get(position).getRssName();
                }
            }

            holder.detail_rssName.setText(rssNameTmp);
            holder.stock_detail_recyclerview_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent stockDetaiWebViewlIntent = new Intent(getContext(), ActivityStockDetailWebview.class);
                    if(flag.equals("1")){
                        stockDetaiWebViewlIntent.putExtra("url", stockDetailListitem.getStockDetailListItemLIst().get(positionTmp).getUrl());
                    }else if(flag.equals("2")){
                        stockDetaiWebViewlIntent.putExtra("url", stockDetailListitemNews.getStockDetailListItemLIst().get(positionTmp).getUrl());
                    } else{
                        stockDetaiWebViewlIntent.putExtra("url", stockDetailListitemDisclosure.getStockDetailListItemLIst().get(positionTmp).getUrl());
                    }

                    startActivity(stockDetaiWebViewlIntent);
                }
            });
        }//onBindViewHolder END

        @Override
        public int getItemCount() {
            Log.e("flag 값은", flag);
            if(flag.equals("1")){
                if (stockDetailListitem != null) {
                    return stockDetailListitem.getStockDetailListItemLIst().size();
                }else{
                    return 0;
                }
            }else if(flag.equals("2")){
                if (stockDetailListitemNews != null) {
                    return stockDetailListitemNews.getStockDetailListItemLIst().size();
                }else {
                    return 0;
                }
            }else if(flag.equals("3")){
                if (stockDetailListitemDisclosure != null) {
                    return stockDetailListitemDisclosure.getStockDetailListItemLIst().size();
                }else{
                    return 0;
                }
            }else{
                return 0;
            }
        }//getItemCount END
    }//RecyclerAdapter END
}

