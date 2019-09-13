package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-05-04.
 */

public class ChatroomUserInfoModel {

    @SerializedName("chatroomUserImgList")
    private List<ChatroomUserInfoItem>  chatroomUserImgList = null;

    public List<ChatroomUserInfoItem> getChatroomUserImgList(){
        return  chatroomUserImgList;
    }
    public class ChatroomUserInfoItem {
        @SerializedName("id")
        public String id;

        @SerializedName("roomNo")
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


}
