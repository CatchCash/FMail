package bean;

import java.io.Serializable;

/**
 * Created by Dao on 2017/5/31.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 107717250835356582L;
    private int uid;
    private String account;
    private String password;
    private String encrypted;

    public User(int uid, String account, String password, String encrypted) {
        this.uid = uid;
        this.account = account;
        this.password = password;
        this.encrypted = encrypted;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }
}
