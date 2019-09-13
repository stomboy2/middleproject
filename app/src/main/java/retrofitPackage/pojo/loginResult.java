package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-03-17.
 */

public class loginResult {
    @SerializedName("result")
    public String result;

    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public String id;
}
