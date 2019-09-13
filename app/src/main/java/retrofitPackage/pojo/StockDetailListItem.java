package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-05-26.
 */

public class StockDetailListItem {
    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("date")
    public String date;

    @SerializedName("rssName")
    public String rssName;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @SerializedName("kind")
    public String kind;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRssName() {
        return rssName;
    }

    public void setRssName(String rssName) {
        this.rssName = rssName;
    }
}
