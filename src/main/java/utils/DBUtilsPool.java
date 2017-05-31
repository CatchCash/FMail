package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Dao on 2017/5/31.
 */
public class DBUtilsPool {

    private Vector<Connection> pool;

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    /**
     * 连接池的大小，也就是连接池中有多少个数据库连接。
     */
    private int poolSize = 1;

    private static DBUtilsPool instance = null;

    private static long connCount=0;
    /**
     * 私有的构造方法，禁止外部创建本类的对象，要想获得本类的对象，通过getIstance方法。
     * 使用了设计模式中的单子模式。
     */
    private DBUtilsPool() {
        init();
    }

    /**
     * 连接池初始化方法，读取属性文件的内容 建立连接池中的初始连接
     */
    private void init() {
        pool = new Vector<Connection>(poolSize);
        readConfig();
        addConnection();
    }

    /**
     * 返回连接到连接池中
     */
    public synchronized void release(Connection conn) {
        if(null==conn)
            System.out.println("--CurrentConn:"+(connCount)+" release a null point");
        else{
            pool.add(conn);
            System.out.println("--CurrentConn:"+(connCount)+" poolSize:"+pool.size()+" release one :"+conn);
        }

    }

    /**
     * 关闭连接池中的所有数据库连接
     */
    public synchronized void closePool() {
        for (int i = 0; i < pool.size(); i++) {
            try {
                ((Connection) pool.get(i)).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.remove(i);
        }
    }

    /**
     * 返回当前连接池的一个对象
     */
    public static DBUtilsPool getInstance() {
        if (instance == null) {
            instance = new DBUtilsPool();
        }
        return instance;
    }

    /**
     * 返回连接池中的一个数据库连接
     */
    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            System.out.println("--CurrentConn:"+(++connCount)+" get one :"+conn);
            return conn;

        } else {
            System.out.println("--CurrentConn:"+(++connCount)+" get null");
            return null;
        }
    }

    /**
     * 在连接池中创建初始设置的的数据库连接
     */
    private void addConnection() {
        Connection conn = null;
        for (int i = 0; i < poolSize; i++) {

            try {
                Class.forName(driverClassName);
                conn = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(conn);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 读取设置连接池的属性文件
     */
    private void readConfig() {
        try {
            String path = this.getClass().getResource("/").getPath();
            path=path.substring(0, path.lastIndexOf("/"))+"/dbpool.properties";
//            System.out.println(path);
            FileInputStream is = new FileInputStream(path);
            Properties props = new Properties();
            props.load(is);
            this.driverClassName = props.getProperty("driverClassName");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.url = props.getProperty("url");
            this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("读取属性文件出错. ");
        }
    }
}
