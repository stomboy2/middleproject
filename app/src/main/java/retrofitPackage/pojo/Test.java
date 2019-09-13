package retrofitPackage.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stomb on 2018-03-13.
 */

public class Test {

    @SerializedName("testing")
    private List<Testing> testing = null;

    public List<Testing> getTesting() {
        return testing;
    }


    public class Testing {

        @SerializedName("no")
        private String no;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
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
    }
}
