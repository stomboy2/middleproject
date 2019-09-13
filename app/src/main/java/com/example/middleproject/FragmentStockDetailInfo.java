package com.example.middleproject;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


//import com.commexpert.ExpertRealProc;
//import com.commexpert.ExpertTranProc;
import com.google.android.material.tabs.TabLayout;

import java.text.NumberFormat;

import commExpert.ExpertRealProc;
import commExpert.ExpertTranProc;
import retrofitPackage.pojo.CompanyInfoItem;
import retrofitPackage.pojo.CompanyInfoModel;

/**
 * Created by stomb on 2018-04-07.
 */

public class FragmentStockDetailInfo extends Fragment {

    ExpertTranProc m_PriceTranProc;
    ExpertRealProc m_PriceRealProc;
    CompanyInfoModel companyInfoModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stock_detail_info, container, false);

        SecionsPagerAdapter secionsPagerAdapter = new SecionsPagerAdapter(getFragmentManager());

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.stock_detail_info_view_pager);
        mViewPager.setAdapter(secionsPagerAdapter);

        TabLayout mtab = (TabLayout) rootView.findViewById(R.id.stock_detail_info_tab_layout);
        mtab.setupWithViewPager(mViewPager);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        m_PriceTranProc = ActivityStockDetail.m_PriceTranProc;
        m_PriceRealProc = ActivityStockDetail.m_PriceRealProc;
        companyInfoModel = ActivityStockDetail.companyInfoModel;
    }

    public static FragmentStockDetailInfo newInstance(){
        Bundle args = new Bundle();
        FragmentStockDetailInfo fragment = new FragmentStockDetailInfo();
        fragment.setArguments(args);
        return fragment;
    }

    //상단 탭 Adapter
    public class SecionsPagerAdapter extends FragmentPagerAdapter {

        public SecionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return FragmentStockDetailInfoSummary.newInstance();   //재무분석
                case 1 :
                    return FragmentStockDetailInfoAnalysisFinancial.newInstance();   //재무분석
                case 2 :
                    return FragmentStockDetailInfoInvestmentIndex.newInstance();  //투자지표
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0 :
                    return "개요";
                case 1 :
                    return "재무분석";
                case 2 :
                    return "투자지표";
                default:
                    return null;
            }
        }
    }
}
