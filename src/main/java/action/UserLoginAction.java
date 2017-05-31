package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Dao on 2017/5/31.
 */
public class UserLoginAction extends ActionSupport {
    private String account;
    private String password;

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

    public String execute(){
        return SUCCESS;
    }
    //完成输入校验需要重写的validate方法
    public void validate()
    {
        if(getAccount().trim().equals(""))
            addFieldError("account","error");
    }
}
