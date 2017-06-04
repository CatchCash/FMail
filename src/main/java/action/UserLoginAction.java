package action;

import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import dao.UserDaoImpl;
import javax.annotation.Resource;
import javax.swing.JOptionPane;

/**
 * Created by Dao on 2017/5/31.
 */
public class UserLoginAction extends ActionSupport {
    private static final long serialVersionUID = 5386429367683022172L;

    @Resource
    private UserDao userDao;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //用户注册
    public String regist(){
        UserDaoImpl dao=new UserDaoImpl();
        int result=dao.addUser(user.getAccount(),user.getPassword());
        if(result!=-1){
            JOptionPane.showMessageDialog(null, "注册成功");
            return INPUT;
        }
        else {
            JOptionPane.showMessageDialog(null, "注册失败");
            return INPUT;
        }

    }

    //用户登录
    public  String login(){
        User checkUser=userDao.checkUser(user.getAccount(),user.getPassword());
        if(null==checkUser) {
            return LOGIN;
        }
        else{
            ActionContext.getContext().put("tip",getText("success"));
            ActionContext.getContext().put("account",user.getAccount());
            return SUCCESS;
        }
    }
    public String execute() throws Exception{
        System.out.println("UserLoginAction start");
       return SUCCESS;
    }
    //用户退出
    public  String userLogout(){
        return null;
    }
    //完成输入校验需要重写的validate方法
    public void validate()
    {
        if(user.getAccount()==null||user.getAccount().trim().equals(""))
            addFieldError("account","error");
        if(user.getPassword()==null||user.getPassword().trim().equals("")){
            addFieldError("password","error");
        }
    }
}
