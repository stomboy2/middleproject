package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-04-14.
 */

public class ChatRoomModel {

    @SerializedName("chatRoomList")
    private List<ChatRoomItem2> chatRoomItemList = null;

    public List<ChatRoomItem2> getChatRoomItemList(){
        return chatRoomItemList;
    }

    public class ChatRoomItem2 {

        @SerializedName("no")
        private String no;

        @SerializedName("title")
        private String title;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
