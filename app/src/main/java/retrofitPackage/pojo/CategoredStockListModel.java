package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-04-01.
 */

public class CategoredStockListModel {
    @SerializedName("categoredStockList")
    private List<CategoredStockItem> categoredStockList = null;

    public List<CategoredStockItem> getCategoredStockList(){
        return  categoredStockList;
    }
    public class CategoredStockItem {
        @SerializedName("stockCode")
        public String stockCode;

        @SerializedName("companyName")
        public String companyName;

        @SerializedName("categoryName")
        public String categoryName;

        @SerializedName("numberOfStock")
        public String numberOfStock;

        @SerializedName("category")
        public String category;

        public String getStockCode(){
            return stockCode;
        }

        public String getCompanyName(){
            return companyName;
        }

        public String getCategoryName(){
            return categoryName;
        }

        public String getCategory(){
            return category;
        }

        public String getNumberOfStock(){
            return numberOfStock;
        }

        public void setStockCode(String stockCode){
            this.stockCode= stockCode;
        }
        public void setCompanyName(String companyName){
            this.companyName= companyName;
        }
        public void setCategory(String category){
            this.categoryName= category;
        }
    }
}
