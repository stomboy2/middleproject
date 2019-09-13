package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-03-13.
 */

public class User {

    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public String id;

    public User(String name, String id){
        this.name = name;
        this.id = id;
    }
}
