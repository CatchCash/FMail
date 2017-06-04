package dao;

import bean.User;
import utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by SSS on 2017/6/4.
 */
public class UserDaoImpl implements UserDao{
    public User checkUser(String account, String password) {
        User result=null;
        DBUtils pool = null;
        Connection con = null;
        try {
            pool = DBUtils.getInstance();
            con = pool.getConnection();
        } catch (Exception se) {
            System.out.println("数据库连接失败！");
            se.printStackTrace();
        }
        try {
		    /*select 之后的字段必须指定，不准使用*替代*/
            String sql = "select uid,account,password from user where account = ? and password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, account);//account=1 where account=1
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result= new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.release(con);
        }
        return result;
    }
    public int addUser(String account, String password){
        int result=-1;
        {
            User user=null;
            DBUtils pool = null;
            Connection con = null;
            try {
                pool = DBUtils.getInstance();
                con = pool.getConnection();
            } catch (Exception se) {
                System.out.println("数据库连接失败！");
                se.printStackTrace();
            }
            try {
		    /*select 之后的字段必须指定，不准使用*替代*/
                String sql = "insert into user (account,password) VALUES(?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, account);//account=1 where account=1
                preparedStatement.setString(2, password);
                /*ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user= new User(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
                }*/
                result=preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println("注册错误");
                e.printStackTrace();
            } finally {
                pool.release(con);
            }
            return result;
        }
    }
}
