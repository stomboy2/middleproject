package retrofitPackage.pojo;

/**
 * Created by stomb on 2018-05-10.
 */

public class FilterItemModel {

    private String name;
    private String ImgUri;

    public int getImgUriInt() {
        return ImgUriInt;
    }

    public void setImgUriInt(int imgUriInt) {
        ImgUriInt = imgUriInt;
    }

    private int ImgUriInt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return ImgUri;
    }

    public void setImgUri(String imgUri) {
        ImgUri = imgUri;
    }
}
