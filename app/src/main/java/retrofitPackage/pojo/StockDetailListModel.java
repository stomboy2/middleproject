package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stomb on 2018-04-04.
 */

public class StockDetailListModel {
    @SerializedName("stockDetailList")
    private List<StockDetailListItem> stockDetailListItemList = null;

    public List<StockDetailListItem> getStockDetailListItemLIst() {
        return stockDetailListItemList;
    }
}
