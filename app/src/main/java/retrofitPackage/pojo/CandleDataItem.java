package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-05-21.
 */

public class CandleDataItem {
    @SerializedName("date")
    private String date;

    @SerializedName("endValue")
    private String endValue;

    @SerializedName("lowValue")
    private String lowValue;

    @SerializedName("highValue")
    private String highValue;

    @SerializedName("startValue")
    private String startValue;

    @SerializedName("tradingVolume")
    private String tradingVolume;

    public String getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(String tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public String getLowValue() {
        return lowValue;
    }

    public void setLowValue(String lowValue) {
        this.lowValue = lowValue;
    }

    public String getHighValue() {
        return highValue;
    }

    public void setHighValue(String highValue) {
        this.highValue = highValue;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }
}
