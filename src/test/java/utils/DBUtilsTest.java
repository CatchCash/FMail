package utils;

import org.junit.Test;

import java.sql.*;
import java.sql.ResultSet;

/**
 * Created by Dao on 2017/5/31.
 */
public class DBUtilsTest {
   @Test
   public void DBUtilsTest() throws SQLException {
      String sql = "select * from userInfo";
      DBUtilsPool pool = null;
      //test ready
         int cycCount=300;
         long start = System.currentTimeMillis();
      //consume DBUtilsPool init
         pool= DBUtilsPool.getInstance();
         long initEnd = System.currentTimeMillis();
      //consume DBUtilsPool conns
      for (int i = 0; i < cycCount; i++) {
         pool = DBUtilsPool.getInstance();
         Connection conn = pool.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
         }
         rs.close();
         stmt.close();
         pool.release(conn);
      }
      pool.closePool();
      System.out.println("初始化连接池时间：" + (initEnd - start) + "ms\n");
      System.out.println("经过"+cycCount+"次的循环调用，使用连接池花费的时间:" + (System.currentTimeMillis() - initEnd) + "ms\n");

   }

}
