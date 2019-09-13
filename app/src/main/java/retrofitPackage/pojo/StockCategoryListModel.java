package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-04-01.
 */

public class StockCategoryListModel {
        @SerializedName("stockCategoryList")
        private List<StockCategoryItem> stockCategoryList = null;

        public List<StockCategoryItem> getStockCategoryList(){
                return  stockCategoryList;
        }

        public class StockCategoryItem {
                @SerializedName("no")
                public String no;

                @SerializedName("categoryName")
                public String categoryName;

                public String getNo(){
                        return no;
                }

                public String getCategoryName(){
                        return categoryName;
                }

                public void setNo(String no){
                        this.no = no;
                }

                public void setCategoryName(String categoryName){
                        this.categoryName = categoryName;
                }
        }
}
