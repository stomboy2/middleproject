package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-04-15.
 */

public class ChattingListModel {

    @SerializedName("chattingList")
    private List<ChatReceiveMsg2> chattingList = null;

    public List<ChatReceiveMsg2> getChattingList(){
        return  chattingList;
    }

    public class ChatReceiveMsg2 {

        private String id;
        private String msg;
        private String kind;
        private String name;
        private String profile;
        private String currentTime;

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }
    }
}
