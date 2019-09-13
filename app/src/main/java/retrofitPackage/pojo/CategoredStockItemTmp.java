package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-04-02.
 */

public class CategoredStockItemTmp {
    @SerializedName("stockCode")
public String stockCode;

    @SerializedName("companyName")
    public String companyName;

    @SerializedName("categoryName")
    public String categoryName;

    @SerializedName("category")
    public String category;

    @SerializedName("numberOfStock")
    public String numberOfStock;

    public String getNumberOfStock() {
        return numberOfStock;
    }

    public void setNumberOfStock(String numberOfStock) {
        this.numberOfStock = numberOfStock;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
