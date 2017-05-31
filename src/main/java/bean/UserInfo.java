package bean;

import java.io.Serializable;

/**
 * Created by Dao on 2017/5/31.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 107717350835356583L;

    private int uid;
    private String nickName;

    public UserInfo(int uid, String nickName) {
        this.uid = uid;
        this.nickName = nickName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
