package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by stomb on 2018-04-12.
 */

public class JoinChatRoomModel {

    @SerializedName("joinChatRoomList")
    private List<ChatRoomItem> joinchatRoomItemList = null;

    public List<ChatRoomItem> getJoinChatRoomItemList(){
        return joinchatRoomItemList;
    }

    public class ChatRoomItem{

        @SerializedName("roomNo")
        private String roomNo;

        @SerializedName("title")
        private String title;

        @SerializedName("total")
        private String total;

        @SerializedName("id")
        private String id;

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


        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }



        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
