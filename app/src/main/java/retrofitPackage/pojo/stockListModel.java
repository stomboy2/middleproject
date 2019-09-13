package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-03-27.
 */

public class stockListModel {

    @SerializedName("stockList")
    private List<stockListItem> stockList = null;

    public List<stockListItem> getStockList() {
        return stockList;
    }


    public class stockListItem {

        @SerializedName("stockCode")
        public String stockCode;

        @SerializedName("companyName")
        public String companyName;

        @SerializedName("no")
        public String no;

        @SerializedName("numberOfStock")
        public String numberOfStock;


        public String getStockCode(){
            return stockCode;
        }

        public String getCompanyName(){
            return companyName;
        }

        public String getNumberOfStock(){return numberOfStock;}

        public String getNo(){
            return no;
        }
    }
}
