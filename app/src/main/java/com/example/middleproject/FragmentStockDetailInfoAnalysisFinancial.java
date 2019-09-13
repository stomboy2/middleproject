package com.example.middleproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofitPackage.pojo.CompanyInfoModel;

/**
 * Created by stomb on 2018-05-25.
 */

public class FragmentStockDetailInfoAnalysisFinancial extends Fragment {
    TextView detail_info_textview1;
    TextView detail_info_textview2;
    TextView detail_info_textview3;
    TextView detail_info_textview4;
    TextView detail_info_textview5;
    TextView detail_info_textview6;
    TextView detail_info_textview7;
    TextView detail_info_textview8;
    TextView detail_info_textview9;
    TextView detail_info_textview10;
    TextView detail_info_textview11;
    TextView detail_info_textview12;
    TextView detail_info_textview13;
    TextView detail_info_textview14;
    TextView detail_info_textview15;
    TextView detail_info_textview16;
    TextView detail_info_textview17;
    TextView detail_info_textview18;
    TextView detail_info_textview19;
    TextView detail_info_textview20;
    TextView detail_info_textview21;
    TextView detail_info_textview22;
    TextView detail_info_textview23;
    TextView detail_info_textview24;
    TextView detail_info_textview25;
    TextView detail_info_textview26;
    TextView detail_info_textview27;
    TextView detail_info_textview28;
    TextView detail_info_textview29;
    TextView detail_info_textview30;
    TextView detail_info_textview31;
    TextView detail_info_textview32;
    TextView detail_info_textview33;
    TextView detail_info_textview34;
    TextView detail_info_textview35;
    TextView detail_info_textview36;
    TextView detail_info_textview37;
    TextView detail_info_textview38;
    TextView detail_info_textview39;
    TextView detail_info_textview40;
    TextView detail_info_textview41;
    TextView detail_info_textview42;
    TextView detail_info_textview43;
    TextView detail_info_textview44;
    TextView detail_info_textview45;

    CompanyInfoModel companyInfoModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stock_detail_info_analysis_financial, container, false);

        detail_info_textview1 = (TextView) rootView.findViewById(R.id.detail_info_textview1);
        detail_info_textview2 = (TextView) rootView.findViewById(R.id.detail_info_textview2);
        detail_info_textview3 = (TextView) rootView.findViewById(R.id.detail_info_textview3);
        detail_info_textview4 = (TextView) rootView.findViewById(R.id.detail_info_textview4);
        detail_info_textview5 = (TextView) rootView.findViewById(R.id.detail_info_textview5);
        detail_info_textview6 = (TextView) rootView.findViewById(R.id.detail_info_textview6);
        detail_info_textview7 = (TextView) rootView.findViewById(R.id.detail_info_textview7);
        detail_info_textview8 = (TextView) rootView.findViewById(R.id.detail_info_textview8);
        detail_info_textview9 = (TextView) rootView.findViewById(R.id.detail_info_textview9);
        detail_info_textview10 = (TextView) rootView.findViewById(R.id.detail_info_textview10);
        detail_info_textview11 = (TextView) rootView.findViewById(R.id.detail_info_textview11);
        detail_info_textview12 = (TextView) rootView.findViewById(R.id.detail_info_textview12);
        detail_info_textview13 = (TextView) rootView.findViewById(R.id.detail_info_textview13);
        detail_info_textview14 = (TextView) rootView.findViewById(R.id.detail_info_textview14);
        detail_info_textview15 = (TextView) rootView.findViewById(R.id.detail_info_textview15);
        detail_info_textview16 = (TextView) rootView.findViewById(R.id.detail_info_textview16);
        detail_info_textview17 = (TextView) rootView.findViewById(R.id.detail_info_textview17);
        detail_info_textview18 = (TextView) rootView.findViewById(R.id.detail_info_textview18);
        detail_info_textview19 = (TextView) rootView.findViewById(R.id.detail_info_textview19);
        detail_info_textview20 = (TextView) rootView.findViewById(R.id.detail_info_textview20);
        detail_info_textview21 = (TextView) rootView.findViewById(R.id.detail_info_textview21);
        detail_info_textview22 = (TextView) rootView.findViewById(R.id.detail_info_textview22);
        detail_info_textview23 = (TextView) rootView.findViewById(R.id.detail_info_textview23);
        detail_info_textview24 = (TextView) rootView.findViewById(R.id.detail_info_textview24);
        detail_info_textview25 = (TextView) rootView.findViewById(R.id.detail_info_textview25);
        detail_info_textview26 = (TextView) rootView.findViewById(R.id.detail_info_textview26);
        detail_info_textview27 = (TextView) rootView.findViewById(R.id.detail_info_textview27);
        detail_info_textview28 = (TextView) rootView.findViewById(R.id.detail_info_textview28);
        detail_info_textview29 = (TextView) rootView.findViewById(R.id.detail_info_textview29);
        detail_info_textview30 = (TextView) rootView.findViewById(R.id.detail_info_textview30);
        detail_info_textview31 = (TextView) rootView.findViewById(R.id.detail_info_textview31);
        detail_info_textview32 = (TextView) rootView.findViewById(R.id.detail_info_textview32);
        detail_info_textview33 = (TextView) rootView.findViewById(R.id.detail_info_textview33);
        detail_info_textview34 = (TextView) rootView.findViewById(R.id.detail_info_textview34);
        detail_info_textview35 = (TextView) rootView.findViewById(R.id.detail_info_textview35);
        detail_info_textview36 = (TextView) rootView.findViewById(R.id.detail_info_textview36);
        detail_info_textview37 = (TextView) rootView.findViewById(R.id.detail_info_textview37);
        detail_info_textview38 = (TextView) rootView.findViewById(R.id.detail_info_textview38);
        detail_info_textview39 = (TextView) rootView.findViewById(R.id.detail_info_textview39);
        detail_info_textview40 = (TextView) rootView.findViewById(R.id.detail_info_textview40);
        detail_info_textview41 = (TextView) rootView.findViewById(R.id.detail_info_textview41);
        detail_info_textview42 = (TextView) rootView.findViewById(R.id.detail_info_textview42);
        detail_info_textview43 = (TextView) rootView.findViewById(R.id.detail_info_textview43);
        detail_info_textview44 = (TextView) rootView.findViewById(R.id.detail_info_textview44);
        detail_info_textview45 = (TextView) rootView.findViewById(R.id.detail_info_textview45);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        companyInfoModel = ActivityStockDetail.companyInfoModel;

        detail_info_textview1.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTakePrice1()));
        CustomUtils.textViewColor(detail_info_textview1,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTakePrice1()));
        detail_info_textview2.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTakePrice2()));
        CustomUtils.textViewColor(detail_info_textview2,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTakePrice2()));
        detail_info_textview3.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTakePrice1(),companyInfoModel.getCompanyInfoList().getTakePrice2()));
        CustomUtils.textViewColor(detail_info_textview3,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTakePrice1(),companyInfoModel.getCompanyInfoList().getTakePrice2()));

        detail_info_textview4.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSalesPrice1()));
        CustomUtils.textViewColor(detail_info_textview4,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSalesPrice1()));
        detail_info_textview5.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSalesPrice2()));
        CustomUtils.textViewColor(detail_info_textview5,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSalesPrice2()));
        detail_info_textview6.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getSalesPrice1(),companyInfoModel.getCompanyInfoList().getSalesPrice2()));
        CustomUtils.textViewColor(detail_info_textview6,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getSalesPrice1(),companyInfoModel.getCompanyInfoList().getSalesPrice2()));

        detail_info_textview7.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSellAndadminPrice1()));
        CustomUtils.textViewColor(detail_info_textview7,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSellAndadminPrice1()));
        detail_info_textview8.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSellAndadminPrice2()));
        CustomUtils.textViewColor(detail_info_textview8,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getSellAndadminPrice2()));
        detail_info_textview9.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getSellAndadminPrice1(),companyInfoModel.getCompanyInfoList().getSellAndadminPrice2()));
        CustomUtils.textViewColor(detail_info_textview9,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getSellAndadminPrice1(),companyInfoModel.getCompanyInfoList().getSellAndadminPrice2()));

        detail_info_textview10.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getOperatingProfit1()));
        CustomUtils.textViewColor(detail_info_textview10,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getOperatingProfit1()));
        detail_info_textview11.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getOperatingProfit2()));
        CustomUtils.textViewColor(detail_info_textview11,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getOperatingProfit2()));
        detail_info_textview12.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getOperatingProfit1(),companyInfoModel.getCompanyInfoList().getOperatingProfit2()));
        CustomUtils.textViewColor(detail_info_textview12,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getOperatingProfit1(),companyInfoModel.getCompanyInfoList().getOperatingProfit2()));

        detail_info_textview13.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfit1()));
        CustomUtils.textViewColor(detail_info_textview13,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfit1()));
        detail_info_textview14.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfit1()));
        CustomUtils.textViewColor(detail_info_textview14,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfit2()));
        detail_info_textview15.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getNetProfit1(),companyInfoModel.getCompanyInfoList().getNetProfit2()));
        CustomUtils.textViewColor(detail_info_textview15,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getNetProfit1(),companyInfoModel.getCompanyInfoList().getNetProfit2()));

        detail_info_textview16.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfitZibea1()));
        CustomUtils.textViewColor(detail_info_textview16,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfitZibea1()));
        detail_info_textview17.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfitZibea2()));
        CustomUtils.textViewColor(detail_info_textview17,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getNetProfitZibea2()));
        detail_info_textview18.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getNetProfitZibea1(),companyInfoModel.getCompanyInfoList().getNetProfitZibea2()));
        CustomUtils.textViewColor(detail_info_textview18,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getNetProfitZibea1(),companyInfoModel.getCompanyInfoList().getNetProfitZibea2()));

        detail_info_textview19.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalAssets1()));
        CustomUtils.textViewColor(detail_info_textview19,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalAssets1()));
        detail_info_textview20.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalAssets2()));
        CustomUtils.textViewColor(detail_info_textview20,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalAssets2()));
        detail_info_textview21.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalAssets1(),companyInfoModel.getCompanyInfoList().getTotalAssets2()));
        CustomUtils.textViewColor(detail_info_textview21,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalAssets1(),companyInfoModel.getCompanyInfoList().getTotalAssets2()));

        detail_info_textview22.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTangibleAssets1()));
        CustomUtils.textViewColor(detail_info_textview22,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTangibleAssets1()));
        detail_info_textview23.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTangibleAssets2()));
        CustomUtils.textViewColor(detail_info_textview23,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTangibleAssets2()));
        detail_info_textview24.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTangibleAssets1(),companyInfoModel.getCompanyInfoList().getTangibleAssets2()));
        CustomUtils.textViewColor(detail_info_textview24,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTangibleAssets1(),companyInfoModel.getCompanyInfoList().getTangibleAssets2()));

        detail_info_textview25.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getIntangibleAssets1()));
        CustomUtils.textViewColor(detail_info_textview25,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getIntangibleAssets1()));
        detail_info_textview26.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getIntangibleAssets2()));
        CustomUtils.textViewColor(detail_info_textview26,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getIntangibleAssets2()));
        detail_info_textview27.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getIntangibleAssets1(),companyInfoModel.getCompanyInfoList().getIntangibleAssets2()));
        CustomUtils.textViewColor(detail_info_textview27,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getIntangibleAssets1(),companyInfoModel.getCompanyInfoList().getIntangibleAssets2()));

        detail_info_textview28.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets1()));
        CustomUtils.textViewColor(detail_info_textview28,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets1()));
        detail_info_textview29.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets2()));
        CustomUtils.textViewColor(detail_info_textview29,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets2()));
        detail_info_textview30.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets1(),companyInfoModel.getCompanyInfoList().getCashAndcashingAssets2()));
        CustomUtils.textViewColor(detail_info_textview30,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getCashAndcashingAssets1(),companyInfoModel.getCompanyInfoList().getCashAndcashingAssets2()));

        detail_info_textview31.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalOwnership1()));
        CustomUtils.textViewColor(detail_info_textview31,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalOwnership1()));
        detail_info_textview32.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalOwnership2()));
        CustomUtils.textViewColor(detail_info_textview32,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalOwnership2()));
        detail_info_textview33.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalOwnership1(),companyInfoModel.getCompanyInfoList().getTotalOwnership2()));
        CustomUtils.textViewColor(detail_info_textview33,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalOwnership1(),companyInfoModel.getCompanyInfoList().getTotalOwnership2()));

        detail_info_textview34.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalDeDette1()));
        CustomUtils.textViewColor(detail_info_textview34,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalDeDette1()));
        detail_info_textview35.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalDeDette2()));
        CustomUtils.textViewColor(detail_info_textview35,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getTotalDeDette2()));
        detail_info_textview36.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalDeDette1(),companyInfoModel.getCompanyInfoList().getTotalDeDette2()));
        CustomUtils.textViewColor(detail_info_textview36,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getTotalDeDette1(),companyInfoModel.getCompanyInfoList().getTotalDeDette2()));

        detail_info_textview37.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow1()));
        CustomUtils.textViewColor(detail_info_textview37,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow1()));
        detail_info_textview38.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview38,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow2()));
        detail_info_textview39.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow1(),companyInfoModel.getCompanyInfoList().getBusinessActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview39,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getBusinessActCashFlow1(),companyInfoModel.getCompanyInfoList().getBusinessActCashFlow2()));

        detail_info_textview40.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow1()));
        CustomUtils.textViewColor(detail_info_textview40,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow1()));
        detail_info_textview41.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview41,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow2()));
        detail_info_textview42.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow1(),companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview42,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow1(),companyInfoModel.getCompanyInfoList().getInvestmentActCashFlow2()));

        detail_info_textview43.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow1()));
        CustomUtils.textViewColor(detail_info_textview43,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow1()));
        detail_info_textview44.setText(CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview44,CustomUtils.minusStr(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow2()));
        detail_info_textview45.setText(CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow1(),companyInfoModel.getCompanyInfoList().getFinancingActCashFlow2()));
        CustomUtils.textViewColor(detail_info_textview45,CustomUtils.strRatio(companyInfoModel.getCompanyInfoList().getFinancingActCashFlow1(),companyInfoModel.getCompanyInfoList().getFinancingActCashFlow2()));
    }

    public static FragmentStockDetailInfoAnalysisFinancial newInstance(){
        Bundle args = new Bundle();
        FragmentStockDetailInfoAnalysisFinancial fragment = new FragmentStockDetailInfoAnalysisFinancial();
        fragment.setArguments(args);
        return fragment;
    }
}
