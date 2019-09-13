package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-05-24.
 */

public class CompanyInfoModel {

    @SerializedName("companyInfoList")
    private CompanyInfoItem companyInfoList = null;

    public CompanyInfoItem getCompanyInfoList(){
        return  companyInfoList;
    }

    public class CompanyInfoItem {

        @SerializedName("takePrice1")
        private String takePrice1;

        @SerializedName("takePrice2")
        private String takePrice2;

        @SerializedName("salesPrice1")
        private String salesPrice1;

        @SerializedName("salesPrice2")
        private String salesPrice2;

        @SerializedName("sellAndadminPrice1")
        private String sellAndadminPrice1;

        @SerializedName("sellAndadminPrice2")
        private String sellAndadminPrice2;

        @SerializedName("operatingProfit1")
        private String operatingProfit1;

        @SerializedName("operatingProfit2")
        private String operatingProfit2;

        @SerializedName("netProfit1")
        private String netProfit1;

        @SerializedName("netProfit2")
        private String netProfit2;

        @SerializedName("netProfitZibea1")
        private String netProfitZibea1;

        @SerializedName("netProfitZibea2")
        private String netProfitZibea2;

        @SerializedName("totalAssets1")
        private String totalAssets1;

        @SerializedName("totalAssets2")
        private String totalAssets2;

        @SerializedName("tangibleAssets1")
        private String tangibleAssets1;

        @SerializedName("tangibleAssets2")
        private String tangibleAssets2;

        @SerializedName("intangibleAssets1")
        private String intangibleAssets1;

        @SerializedName("intangibleAssets2")
        private String intangibleAssets2;

        @SerializedName("cashAndcashingAssets1")
        private String cashAndcashingAssets1;

        @SerializedName("cashAndcashingAssets2")
        private String cashAndcashingAssets2;

        @SerializedName("totalOwnership1")
        private String totalOwnership1;

        @SerializedName("totalOwnership2")
        private String totalOwnership2;

        @SerializedName("totalDeDette1")
        private String totalDeDette1;

        @SerializedName("totalDeDette2")
        private String totalDeDette2;

        @SerializedName("businessActCashFlow1")
        private String businessActCashFlow1;

        @SerializedName("businessActCashFlow2")
        private String businessActCashFlow2;

        @SerializedName("investmentActCashFlow1")
        private String investmentActCashFlow1;

        @SerializedName("investmentActCashFlow2")
        private String investmentActCashFlow2;

        @SerializedName("financingActCashFlow1")
        private String financingActCashFlow1;

        @SerializedName("financingActCashFlow2")
        private String financingActCashFlow2;

        @SerializedName("grossMarginRatio1")
        private String grossMarginRatio1;

        @SerializedName("grossMarginRatio2")
        private String grossMarginRatio2;

        @SerializedName("businessMarginRatio1")
        private String businessMarginRatio1;

        @SerializedName("businessMarginRatio2")
        private String businessMarginRatio2;

        @SerializedName("EBITDAMarginRatio1")
        private String eBITDAMarginRatio1;

        @SerializedName("EBITDAMarginRatio2")
        private String eBITDAMarginRatio2;

        @SerializedName("roe1")
        private String roe1;

        @SerializedName("roe2")
        private String roe2;

        @SerializedName("roa1")
        private String roa1;

        @SerializedName("roa2")
        private String roa2;

        @SerializedName("roic1")
        private String roic1;

        @SerializedName("roic2")
        private String roic2;

        @SerializedName("salesGrowthRate1")
        private String salesGrowthRate1;

        @SerializedName("salesGrowthRate2")
        private String salesGrowthRate2;

        @SerializedName("businessGrowthRate1")
        private String businessGrowthRate1;

        @SerializedName("businessGrowthRate2")
        private String businessGrowthRate2;

        @SerializedName("deDetteRatio1")
        private String deDetteRatio1;

        @SerializedName("deDetteRatio2")
        private String deDetteRatio2;

        @SerializedName("flowingRatio1")
        private String flowingRatio1;

        @SerializedName("flowingRatio2")
        private String flowingRatio2;

        @SerializedName("personalNetRatio1")
        private String personalNetRatio1;

        @SerializedName("personalNetRatio2")
        private String personalNetRatio2;

        @SerializedName("interestRewardRatio1")
        private String interestRewardRatio1;

        @SerializedName("interestRewardRatio2")
        private String interestRewardRatio2;

        @SerializedName("debtRatio1")
        private String debtRatio1;

        @SerializedName("debtRatio2")
        private String debtRatio2;

        @SerializedName("totalAssetsTurnOverRatio1")
        private String totalAssetsTurnOverRatio1;

        @SerializedName("totalAssetsTurnOverRatio2")
        private String totalAssetsTurnOverRatio2;

        @SerializedName("totalDeptTurnOverRatio1")
        private String totalDeptTurnOverRatio1;

        @SerializedName("totalDeptTurnOverRatio2")
        private String totalDeptTurnOverRatio2;

        @SerializedName("totalNetTurnOverRatio1")
        private String totalNetTurnOverRatio1;

        @SerializedName("totalNetTurnOverRatio2")
        private String totalNetTurnOverRatio2;

        @SerializedName("sununjunjabonTurnOverRatio1")
        private String sununjunjabonTurnOverRatio1;

        @SerializedName("sununjunjabonTurnOverRatio2")
        private String sununjunjabonTurnOverRatio2;

        @SerializedName("eps1")
        private String eps1;

        @SerializedName("eps2")
        private String eps2;

        @SerializedName("bps1")
        private String bps1;

        @SerializedName("bps2")
        private String bps2;

        @SerializedName("sps1")
        private String sps1;

        @SerializedName("sps2")
        private String sps2;

        @SerializedName("adjustDps1")
        private String adjustDps1;

        @SerializedName("adjustDps2")
        private String adjustDps2;

        @SerializedName("cashDistributionTendency1")
        private String cashDistributionTendency1;

        @SerializedName("cashDistributionTendency2")
        private String cashDistributionTendency2;

        @SerializedName("per1")
        private String per1;

        @SerializedName("per2")
        private String per2;

        @SerializedName("pbr1")
        private String pbr1;

        @SerializedName("pbr2")
        private String pbr2;

        @SerializedName("pcr1")
        private String pcr1;

        @SerializedName("pcr2")
        private String pcr2;

        @SerializedName("psr1")
        private String psr1;

        @SerializedName("psr2")
        private String psr2;

        @SerializedName("evOrEbitda1")
        private String evOrEbitda1;


        public String getTakePrice1() {
            return takePrice1;
        }

        public void setTakePrice1(String takePrice1) {
            this.takePrice1 = takePrice1;
        }

        public String getTakePrice2() {
            return takePrice2;
        }

        public void setTakePrice2(String takePrice2) {
            this.takePrice2 = takePrice2;
        }

        public String getSalesPrice1() {
            return salesPrice1;
        }

        public void setSalesPrice1(String salesPrice1) {
            this.salesPrice1 = salesPrice1;
        }

        public String getSalesPrice2() {
            return salesPrice2;
        }

        public void setSalesPrice2(String salesPrice2) {
            this.salesPrice2 = salesPrice2;
        }

        public String getSellAndadminPrice1() {
            return sellAndadminPrice1;
        }

        public void setSellAndadminPrice1(String sellAndadminPrice1) {
            this.sellAndadminPrice1 = sellAndadminPrice1;
        }

        public String getSellAndadminPrice2() {
            return sellAndadminPrice2;
        }

        public void setSellAndadminPrice2(String sellAndadminPrice2) {
            this.sellAndadminPrice2 = sellAndadminPrice2;
        }

        public String getOperatingProfit1() {
            return operatingProfit1;
        }

        public void setOperatingProfit1(String operatingProfit1) {
            this.operatingProfit1 = operatingProfit1;
        }

        public String getOperatingProfit2() {
            return operatingProfit2;
        }

        public void setOperatingProfit2(String operatingProfit2) {
            this.operatingProfit2 = operatingProfit2;
        }

        public String getNetProfit1() {
            return netProfit1;
        }

        public void setNetProfit1(String netProfit1) {
            this.netProfit1 = netProfit1;
        }

        public String getNetProfit2() {
            return netProfit2;
        }

        public void setNetProfit2(String netProfit2) {
            this.netProfit2 = netProfit2;
        }

        public String getNetProfitZibea1() {
            return netProfitZibea1;
        }

        public void setNetProfitZibea1(String netProfitZibea1) {
            this.netProfitZibea1 = netProfitZibea1;
        }

        public String getNetProfitZibea2() {
            return netProfitZibea2;
        }

        public void setNetProfitZibea2(String netProfitZibea2) {
            this.netProfitZibea2 = netProfitZibea2;
        }

        public String getTotalAssets1() {
            return totalAssets1;
        }

        public void setTotalAssets1(String totalAssets1) {
            this.totalAssets1 = totalAssets1;
        }

        public String getTotalAssets2() {
            return totalAssets2;
        }

        public void setTotalAssets2(String totalAssets2) {
            this.totalAssets2 = totalAssets2;
        }

        public String getTangibleAssets1() {
            return tangibleAssets1;
        }

        public void setTangibleAssets1(String tangibleAssets1) {
            this.tangibleAssets1 = tangibleAssets1;
        }

        public String getTangibleAssets2() {
            return tangibleAssets2;
        }

        public void setTangibleAssets2(String tangibleAssets2) {
            this.tangibleAssets2 = tangibleAssets2;
        }

        public String getIntangibleAssets1() {
            return intangibleAssets1;
        }

        public void setIntangibleAssets1(String intangibleAssets1) {
            this.intangibleAssets1 = intangibleAssets1;
        }

        public String getIntangibleAssets2() {
            return intangibleAssets2;
        }

        public void setIntangibleAssets2(String intangibleAssets2) {
            this.intangibleAssets2 = intangibleAssets2;
        }

        public String getCashAndcashingAssets1() {
            return cashAndcashingAssets1;
        }

        public void setCashAndcashingAssets1(String cashAndcashingAssets1) {
            this.cashAndcashingAssets1 = cashAndcashingAssets1;
        }

        public String getCashAndcashingAssets2() {
            return cashAndcashingAssets2;
        }

        public void setCashAndcashingAssets2(String cashAndcashingAssets2) {
            this.cashAndcashingAssets2 = cashAndcashingAssets2;
        }

        public String getTotalOwnership1() {
            return totalOwnership1;
        }

        public void setTotalOwnership1(String totalOwnership1) {
            this.totalOwnership1 = totalOwnership1;
        }

        public String getTotalOwnership2() {
            return totalOwnership2;
        }

        public void setTotalOwnership2(String totalOwnership2) {
            this.totalOwnership2 = totalOwnership2;
        }

        public String getTotalDeDette1() {
            return totalDeDette1;
        }

        public void setTotalDeDette1(String totalDeDette1) {
            this.totalDeDette1 = totalDeDette1;
        }

        public String getTotalDeDette2() {
            return totalDeDette2;
        }

        public void setTotalDeDette2(String totalDeDette2) {
            this.totalDeDette2 = totalDeDette2;
        }

        public String getBusinessActCashFlow1() {
            return businessActCashFlow1;
        }

        public void setBusinessActCashFlow1(String businessActCashFlow1) {
            this.businessActCashFlow1 = businessActCashFlow1;
        }

        public String getBusinessActCashFlow2() {
            return businessActCashFlow2;
        }

        public void setBusinessActCashFlow2(String businessActCashFlow2) {
            this.businessActCashFlow2 = businessActCashFlow2;
        }

        public String getInvestmentActCashFlow1() {
            return investmentActCashFlow1;
        }

        public void setInvestmentActCashFlow1(String investmentActCashFlow1) {
            this.investmentActCashFlow1 = investmentActCashFlow1;
        }

        public String getInvestmentActCashFlow2() {
            return investmentActCashFlow2;
        }

        public void setInvestmentActCashFlow2(String investmentActCashFlow2) {
            this.investmentActCashFlow2 = investmentActCashFlow2;
        }

        public String getFinancingActCashFlow1() {
            return financingActCashFlow1;
        }

        public void setFinancingActCashFlow1(String financingActCashFlow1) {
            this.financingActCashFlow1 = financingActCashFlow1;
        }

        public String getFinancingActCashFlow2() {
            return financingActCashFlow2;
        }

        public void setFinancingActCashFlow2(String financingActCashFlow2) {
            this.financingActCashFlow2 = financingActCashFlow2;
        }

        public String getGrossMarginRatio1() {
            return grossMarginRatio1;
        }

        public void setGrossMarginRatio1(String grossMarginRatio1) {
            this.grossMarginRatio1 = grossMarginRatio1;
        }

        public String getGrossMarginRatio2() {
            return grossMarginRatio2;
        }

        public void setGrossMarginRatio2(String grossMarginRatio2) {
            this.grossMarginRatio2 = grossMarginRatio2;
        }

        public String getBusinessMarginRatio1() {
            return businessMarginRatio1;
        }

        public void setBusinessMarginRatio1(String businessMarginRatio1) {
            this.businessMarginRatio1 = businessMarginRatio1;
        }

        public String getBusinessMarginRatio2() {
            return businessMarginRatio2;
        }

        public void setBusinessMarginRatio2(String businessMarginRatio2) {
            this.businessMarginRatio2 = businessMarginRatio2;
        }

        public String geteBITDAMarginRatio1() {
            return eBITDAMarginRatio1;
        }

        public void seteBITDAMarginRatio1(String eBITDAMarginRatio1) {
            this.eBITDAMarginRatio1 = eBITDAMarginRatio1;
        }

        public String geteBITDAMarginRatio2() {
            return eBITDAMarginRatio2;
        }

        public void seteBITDAMarginRatio2(String eBITDAMarginRatio2) {
            this.eBITDAMarginRatio2 = eBITDAMarginRatio2;
        }

        public String getRoe1() {
            return roe1;
        }

        public void setRoe1(String roe1) {
            this.roe1 = roe1;
        }

        public String getRoe2() {
            return roe2;
        }

        public void setRoe2(String roe2) {
            this.roe2 = roe2;
        }

        public String getRoa1() {
            return roa1;
        }

        public void setRoa1(String roa1) {
            this.roa1 = roa1;
        }

        public String getRoa2() {
            return roa2;
        }

        public void setRoa2(String roa2) {
            this.roa2 = roa2;
        }

        public String getRoic1() {
            return roic1;
        }

        public void setRoic1(String roic1) {
            this.roic1 = roic1;
        }

        public String getRoic2() {
            return roic2;
        }

        public void setRoic2(String roic2) {
            this.roic2 = roic2;
        }

        public String getSalesGrowthRate1() {
            return salesGrowthRate1;
        }

        public void setSalesGrowthRate1(String salesGrowthRate1) {
            this.salesGrowthRate1 = salesGrowthRate1;
        }

        public String getSalesGrowthRate2() {
            return salesGrowthRate2;
        }

        public void setSalesGrowthRate2(String salesGrowthRate2) {
            this.salesGrowthRate2 = salesGrowthRate2;
        }

        public String getBusinessGrowthRate1() {
            return businessGrowthRate1;
        }

        public void setBusinessGrowthRate1(String businessGrowthRate1) {
            this.businessGrowthRate1 = businessGrowthRate1;
        }

        public String getBusinessGrowthRate2() {
            return businessGrowthRate2;
        }

        public void setBusinessGrowthRate2(String businessGrowthRate2) {
            this.businessGrowthRate2 = businessGrowthRate2;
        }

        public String getDeDetteRatio1() {
            return deDetteRatio1;
        }

        public void setDeDetteRatio1(String deDetteRatio1) {
            this.deDetteRatio1 = deDetteRatio1;
        }

        public String getDeDetteRatio2() {
            return deDetteRatio2;
        }

        public void setDeDetteRatio2(String deDetteRatio2) {
            this.deDetteRatio2 = deDetteRatio2;
        }

        public String getFlowingRatio1() {
            return flowingRatio1;
        }

        public void setFlowingRatio1(String flowingRatio1) {
            this.flowingRatio1 = flowingRatio1;
        }

        public String getFlowingRatio2() {
            return flowingRatio2;
        }

        public void setFlowingRatio2(String flowingRatio2) {
            this.flowingRatio2 = flowingRatio2;
        }

        public String getPersonalNetRatio1() {
            return personalNetRatio1;
        }

        public void setPersonalNetRatio1(String personalNetRatio1) {
            this.personalNetRatio1 = personalNetRatio1;
        }

        public String getPersonalNetRatio2() {
            return personalNetRatio2;
        }

        public void setPersonalNetRatio2(String personalNetRatio2) {
            this.personalNetRatio2 = personalNetRatio2;
        }

        public String getInterestRewardRatio1() {
            return interestRewardRatio1;
        }

        public void setInterestRewardRatio1(String interestRewardRatio1) {
            this.interestRewardRatio1 = interestRewardRatio1;
        }

        public String getInterestRewardRatio2() {
            return interestRewardRatio2;
        }

        public void setInterestRewardRatio2(String interestRewardRatio2) {
            this.interestRewardRatio2 = interestRewardRatio2;
        }

        public String getDebtRatio1() {
            return debtRatio1;
        }

        public void setDebtRatio1(String debtRatio1) {
            this.debtRatio1 = debtRatio1;
        }

        public String getDebtRatio2() {
            return debtRatio2;
        }

        public void setDebtRatio2(String debtRatio2) {
            this.debtRatio2 = debtRatio2;
        }

        public String getTotalAssetsTurnOverRatio1() {
            return totalAssetsTurnOverRatio1;
        }

        public void setTotalAssetsTurnOverRatio1(String totalAssetsTurnOverRatio1) {
            this.totalAssetsTurnOverRatio1 = totalAssetsTurnOverRatio1;
        }

        public String getTotalAssetsTurnOverRatio2() {
            return totalAssetsTurnOverRatio2;
        }

        public void setTotalAssetsTurnOverRatio2(String totalAssetsTurnOverRatio2) {
            this.totalAssetsTurnOverRatio2 = totalAssetsTurnOverRatio2;
        }

        public String getTotalDeptTurnOverRatio1() {
            return totalDeptTurnOverRatio1;
        }

        public void setTotalDeptTurnOverRatio1(String totalDeptTurnOverRatio1) {
            this.totalDeptTurnOverRatio1 = totalDeptTurnOverRatio1;
        }

        public String getTotalDeptTurnOverRatio2() {
            return totalDeptTurnOverRatio2;
        }

        public void setTotalDeptTurnOverRatio2(String totalDeptTurnOverRatio2) {
            this.totalDeptTurnOverRatio2 = totalDeptTurnOverRatio2;
        }

        public String getTotalNetTurnOverRatio1() {
            return totalNetTurnOverRatio1;
        }

        public void setTotalNetTurnOverRatio1(String totalNetTurnOverRatio1) {
            this.totalNetTurnOverRatio1 = totalNetTurnOverRatio1;
        }

        public String getTotalNetTurnOverRatio2() {
            return totalNetTurnOverRatio2;
        }

        public void setTotalNetTurnOverRatio2(String totalNetTurnOverRatio2) {
            this.totalNetTurnOverRatio2 = totalNetTurnOverRatio2;
        }

        public String getSununjunjabonTurnOverRatio1() {
            return sununjunjabonTurnOverRatio1;
        }

        public void setSununjunjabonTurnOverRatio1(String sununjunjabonTurnOverRatio1) {
            this.sununjunjabonTurnOverRatio1 = sununjunjabonTurnOverRatio1;
        }

        public String getSununjunjabonTurnOverRatio2() {
            return sununjunjabonTurnOverRatio2;
        }

        public void setSununjunjabonTurnOverRatio2(String sununjunjabonTurnOverRatio2) {
            this.sununjunjabonTurnOverRatio2 = sununjunjabonTurnOverRatio2;
        }

        public String getEps1() {
            return eps1;
        }

        public void setEps1(String eps1) {
            this.eps1 = eps1;
        }

        public String getEps2() {
            return eps2;
        }

        public void setEps2(String eps2) {
            this.eps2 = eps2;
        }

        public String getBps1() {
            return bps1;
        }

        public void setBps1(String bps1) {
            this.bps1 = bps1;
        }

        public String getBps2() {
            return bps2;
        }

        public void setBps2(String bps2) {
            this.bps2 = bps2;
        }

        public String getSps1() {
            return sps1;
        }

        public void setSps1(String sps1) {
            this.sps1 = sps1;
        }

        public String getSps2() {
            return sps2;
        }

        public void setSps2(String sps2) {
            this.sps2 = sps2;
        }

        public String getAdjustDps1() {
            return adjustDps1;
        }

        public void setAdjustDps1(String adjustDps1) {
            this.adjustDps1 = adjustDps1;
        }

        public String getAdjustDps2() {
            return adjustDps2;
        }

        public void setAdjustDps2(String adjustDps2) {
            this.adjustDps2 = adjustDps2;
        }

        public String getCashDistributionTendency1() {
            return cashDistributionTendency1;
        }

        public void setCashDistributionTendency1(String cashDistributionTendency1) {
            this.cashDistributionTendency1 = cashDistributionTendency1;
        }

        public String getCashDistributionTendency2() {
            return cashDistributionTendency2;
        }

        public void setCashDistributionTendency2(String cashDistributionTendency2) {
            this.cashDistributionTendency2 = cashDistributionTendency2;
        }

        public String getPer1() {
            return per1;
        }

        public void setPer1(String per1) {
            this.per1 = per1;
        }

        public String getPer2() {
            return per2;
        }

        public void setPer2(String per2) {
            this.per2 = per2;
        }

        public String getPbr1() {
            return pbr1;
        }

        public void setPbr1(String pbr1) {
            this.pbr1 = pbr1;
        }

        public String getPbr2() {
            return pbr2;
        }

        public void setPbr2(String pbr2) {
            this.pbr2 = pbr2;
        }

        public String getPcr1() {
            return pcr1;
        }

        public void setPcr1(String pcr1) {
            this.pcr1 = pcr1;
        }

        public String getPcr2() {
            return pcr2;
        }

        public void setPcr2(String pcr2) {
            this.pcr2 = pcr2;
        }

        public String getPsr1() {
            return psr1;
        }

        public void setPsr1(String psr1) {
            this.psr1 = psr1;
        }

        public String getPsr2() {
            return psr2;
        }

        public void setPsr2(String psr2) {
            this.psr2 = psr2;
        }

        public String getEvOrEbitda1() {
            return evOrEbitda1;
        }

        public void setEvOrEbitda1(String evOrEbitda1) {
            this.evOrEbitda1 = evOrEbitda1;
        }

        public String getEvOrEbitda2() {
            return evOrEbitda2;
        }

        public void setEvOrEbitda2(String evOrEbitda2) {
            this.evOrEbitda2 = evOrEbitda2;
        }

        @SerializedName("evOrEbitda2")

        private String evOrEbitda2;
    }
}
