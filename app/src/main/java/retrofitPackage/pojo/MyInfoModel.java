package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-05-02.
 */

public class MyInfoModel {

    @SerializedName("myInfo")
    private List<MyInfoModelItem> myinfo = null;

    public List<MyInfoModelItem> getMyinfo(){
        return myinfo;
    }

    public class MyInfoModelItem{

        private String name;
        private String phoneNum;
        private String profile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }
}
