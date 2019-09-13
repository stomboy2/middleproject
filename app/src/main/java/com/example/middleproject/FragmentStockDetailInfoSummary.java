package com.example.middleproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import commExpert.ExpertTranProc;

//import com.commexpert.ExpertTranProc;


/**
 * Created by stomb on 2018-05-25.
 */

public class FragmentStockDetailInfoSummary extends Fragment {

    ExpertTranProc m_PriceTranProc;

    TextView stock_detail_info_start_price;
    TextView stock_detail_info_kind;
    TextView stock_detail_info_high_price;
    TextView stock_detail_info_low_price;
    TextView stock_detail_info_exchange_volume;
    TextView stock_detail_info_exchange_deagum;
    TextView stock_detail_info_highest_price;
    TextView stock_detail_info_lowest_price;
    TextView stock_detail_info_weainboyou;
    TextView stock_detail_info_siga_total;
    TextView stock_detail_info_actmyun_price;
    TextView stock_detail_info_totalsu;
    TextView stock_detail_info_eps;
    TextView stock_detail_info_per;
    TextView stock_detail_info_bps;
    TextView stock_detail_info_pbr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stock_detail_info_summary, container, false);

        stock_detail_info_start_price = (TextView) rootView.findViewById(R.id.stock_detail_info_start_price);
        stock_detail_info_kind = (TextView) rootView.findViewById(R.id.stock_detail_info_kind);
        stock_detail_info_high_price = (TextView) rootView.findViewById(R.id.stock_detail_info_high_price);
        stock_detail_info_low_price = (TextView) rootView.findViewById(R.id.stock_detail_info_low_price);
        stock_detail_info_exchange_volume = (TextView) rootView.findViewById(R.id.stock_detail_info_exchange_volume);
        stock_detail_info_exchange_deagum = (TextView) rootView.findViewById(R.id.stock_detail_info_exchange_deagum);
        stock_detail_info_highest_price = (TextView) rootView.findViewById(R.id.stock_detail_info_highest_price);
        stock_detail_info_lowest_price = (TextView) rootView.findViewById(R.id.stock_detail_info_lowest_price);
        stock_detail_info_weainboyou = (TextView) rootView.findViewById(R.id.stock_detail_info_weainboyou);
        stock_detail_info_siga_total = (TextView) rootView.findViewById(R.id.stock_detail_info_siga_total);
        stock_detail_info_actmyun_price = (TextView) rootView.findViewById(R.id.stock_detail_info_actmyun_price);
        stock_detail_info_totalsu = (TextView) rootView.findViewById(R.id.stock_detail_info_totalsu);
        stock_detail_info_eps = (TextView) rootView.findViewById(R.id.stock_detail_info_eps);
        stock_detail_info_per = (TextView) rootView.findViewById(R.id.stock_detail_info_per);
        stock_detail_info_bps = (TextView) rootView.findViewById(R.id.stock_detail_info_bps);
        stock_detail_info_pbr = (TextView) rootView.findViewById(R.id.stock_detail_info_pbr);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        m_PriceTranProc = ActivityStockDetail.m_PriceTranProc;

        String yesterdayStr = m_PriceTranProc.GetSingleData(0,12);
        int yesterdayInt = Integer.parseInt(yesterdayStr);              //전일대비 Int
        String yesterdayTmp = Integer.toString(yesterdayInt);

        String startPriceStr = m_PriceTranProc.GetSingleData(0,18);
        String highPriceStr = m_PriceTranProc.GetSingleData(0,19);				  //최고가
        String lowPricStr = m_PriceTranProc.GetSingleData(0,20);					  //최저가
        String exchangeVolume =m_PriceTranProc.GetSingleData(0,16);                 //거래량
        String exchangeDeagum =m_PriceTranProc.GetSingleData(0,15);                 //거래대금
        String highestPric = m_PriceTranProc.GetSingleData(0,61);                   //52주 최고
        String lowestPric = m_PriceTranProc.GetSingleData(0,64);                    //52주 최저
        String weainboyou = m_PriceTranProc.GetSingleData(0,25);                    //외인보유비중
        String sigaTotal = m_PriceTranProc.GetSingleData(0,42);                     //시가총액
        String actmyunPric = m_PriceTranProc.GetSingleData(0,37);                   //액면가
        String totalsu = ActivityStockDetail.numberOfStock;                                               //총 주식수
        String eps = m_PriceTranProc.GetSingleData(0,47);                           //EPS
        String per = m_PriceTranProc.GetSingleData(0,43);                           //PER
        String bps = m_PriceTranProc.GetSingleData(0,48);                           //BPS
        String pbr = m_PriceTranProc.GetSingleData(0,44);                           //PBR

        String startPriceStrTmp = CustomUtils.strTostr(startPriceStr);
        String highPricStrTmp = CustomUtils.strTostr(highPriceStr);
        String lowPricStrTmp = CustomUtils.strTostr(lowPricStr);
        String exchangeVolumeTmp = CustomUtils.strTostr(exchangeVolume);
        String exchangeDeagumTmp = CustomUtils.strTostr(exchangeDeagum);
        String highestPricTmp = CustomUtils.strTostr(highestPric);
        String lowestPricTmp = CustomUtils.strTostr(lowestPric);
        String weainboyouTmp = CustomUtils.strTostr(weainboyou);
        String sigaTotalTmp = CustomUtils.strTostr(sigaTotal);
        String actmyunPricTmp = CustomUtils.strTostr(actmyunPric);
        String totalsuTmp = CustomUtils.strTostr(totalsu);
        String epsTmp = CustomUtils.strTostr(eps);
        String perTmp = CustomUtils.strTostr(per);
        String bpsTmp = CustomUtils.strTostr(bps);
        String pbrTmp = CustomUtils.strTostr(pbr);

        exchangeDeagumTmp = exchangeDeagumTmp.substring(0,exchangeDeagumTmp.length()-8);

        stock_detail_info_start_price.setText(startPriceStrTmp);
        stock_detail_info_kind.setText(m_PriceTranProc.GetSingleData(0,4));
        stock_detail_info_high_price.setText(highPricStrTmp);
        stock_detail_info_low_price.setText(lowPricStrTmp);
        stock_detail_info_exchange_volume.setText(exchangeVolumeTmp+"주");
        stock_detail_info_exchange_deagum.setText(exchangeDeagumTmp+"백만");
        stock_detail_info_highest_price.setText(highestPricTmp);
        stock_detail_info_lowest_price.setText(lowestPricTmp);
        stock_detail_info_weainboyou.setText(weainboyouTmp+"%");
        stock_detail_info_siga_total.setText(sigaTotalTmp+"억");
        stock_detail_info_actmyun_price.setText(actmyunPricTmp+"원");
        stock_detail_info_totalsu.setText(totalsuTmp+"주");
        stock_detail_info_eps.setText(epsTmp+"원");
        stock_detail_info_per.setText(perTmp+"배");
        stock_detail_info_bps.setText(bpsTmp+"원");
        stock_detail_info_pbr.setText(pbrTmp+"배");

        if(yesterdayTmp.substring(0,1).equals("-")){    //전일대비 - 일때
            yesterdayTmp = yesterdayTmp.substring(1);
            stock_detail_info_high_price.setTextColor(Color.parseColor("#FF0022FA"));
            stock_detail_info_low_price.setTextColor(Color.parseColor("#FF0022FA"));
            stock_detail_info_start_price.setTextColor(Color.parseColor("#FF0022FA"));
        }else{      //전일 대비 - 가 아닐때
            if(yesterdayInt > 0){   // 전일대비 상승중일때
                stock_detail_info_high_price.setTextColor(Color.parseColor("#FFD41620"));
                stock_detail_info_low_price.setTextColor(Color.parseColor("#FFD41620"));
                stock_detail_info_start_price.setTextColor(Color.parseColor("#FFD41620"));
            }else{  //전일대비 동일한 값일때
                stock_detail_info_high_price.setTextColor(Color.parseColor("#FF000000"));
                stock_detail_info_low_price.setTextColor(Color.parseColor("#FF000000"));
                stock_detail_info_start_price.setTextColor(Color.parseColor("#FF000000"));
            }
        }

        stock_detail_info_highest_price.setTextColor(Color.parseColor("#FFD41620"));
        stock_detail_info_lowest_price.setTextColor(Color.parseColor("#FF0022FA"));
    }//onStart()

    public static FragmentStockDetailInfoSummary newInstance(){
        Bundle args = new Bundle();
        FragmentStockDetailInfoSummary fragment = new FragmentStockDetailInfoSummary();
        fragment.setArguments(args);
        return fragment;
    }
}
