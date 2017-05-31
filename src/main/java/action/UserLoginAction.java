package action;

import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.annotation.Resource;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Dao on 2017/5/31.
 */
public class UserLoginAction extends ActionSupport implements ServletRequestAware ,
        ServletResponseAware,SessionAware{
    private static final long serialVersionUID = 5386429367683022172L;

    @Resource
    private UserDao userDao;
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


    //用户登录
    public String execute() throws Exception{
        String account=getAccount();
        String result=SUCCESS;
        if(account!=null && !"".equals(account) &&
                password!=null && !"".equals(password)){
            User user=userDao.checkUser(account,password);
            if(user!=null){
                System.out.println("login");
                result=SUCCESS;
            }
            else
                result=ERROR;
        }
        return result;
    }
    //用户退出
    public  String userLogout(){
        return null;
    }
    //完成输入校验需要重写的validate方法
    public void validate()
    {
        if(getAccount().trim().equals(""))
            addFieldError("account","error");
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {

    }

    public void setServletResponse(javax.servlet.http.HttpServletResponse httpServletResponse) {

    }

    public void setSession(Map<String, Object> map) {

    }
}
