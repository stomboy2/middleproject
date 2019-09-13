package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stomb on 2018-05-05.
 */

public class ChatroomUserImgListModel {
    @SerializedName("id")
    public String id;

    @SerializedName("companyName")
    public String roomNo;

    @SerializedName("profile")
    public String profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
