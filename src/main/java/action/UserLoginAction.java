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
    private UserDaoImpl userDao;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //用户注册
    public String regist(){
         userDao=new UserDaoImpl();
        int result=userDao.addUser(user.getAccount(),user.getPassword());
        System.out.println(result);
        if(result!=-1){
            JOptionPane.showMessageDialog(null, "注册成功");
            return SUCCESS;
        }
        else {
            JOptionPane.showMessageDialog(null, "注册失败");
            return ERROR;
        }

    }

    //用户登录
    public  String login(){
        userDao=new UserDaoImpl();
        User checkUser=userDao.checkUser(user.getAccount(),user.getPassword());
        if(null==checkUser) {
            return SUCCESS;
        }
        else{
            ActionContext.getContext().put("tip",getText("success"));
            ActionContext.getContext().put("account",user.getAccount());
            return ERROR;
        }
    }
    public String execute() throws Exception{
       return INPUT;
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
