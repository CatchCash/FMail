package dao;

import bean.User;
/**
 * 类说明：用户基本操作类UsersDAO接口类
 *
 * @author 作者:
 * @version 创建时间:
 */
public interface UserDao {

    public interface UserDAO {
        /**
         * 检测用户是否存在，并返回该用户
         *
         * @param account
         *            用户名
         * @param password
         *            密码
         * @return User 根据用户名与密码查询的结果，没有该用户或者密码不正确则返回null
         */
        public User checkUser(String account, String password);
    }

}
