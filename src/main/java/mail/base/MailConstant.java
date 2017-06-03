package mail.base;

import java.util.Map;

/**
 * Created by Dao on 2017/5/29.
 */
public class MailConstant {
    /** 收件夹 */
    public static final String FOLDER_INBOX = "INBOX";

    /** 已发送 */
    public static final String FOLDER_SENDED = "SENDED";

    /** 草稿箱 */
    public static final String FOLDER_DRAFT = "DRAFT";

    /** 已删除 */
    public static final String FOLDER_DELETE = "DELETE";

    /** 垃圾邮件夹 */
    public static final String FOLDER_SPAM = "SPAM";

    /** 邮件夹类型 */
    public static final int INBOX = 1;// 收件夹
    public static final int DRAFT = 2;// 草稿箱
    public static final int SENDED = 3;// 已发送
    public static final int DELETE = 4;// 已删除
    public static final int SPAM = 5;// 垃圾邮件

    /*
    *
    * */
    //public static final String host = "localhost";//主机
   // public static final String doname="hnu.edu.cn";
    public static final String doname="qq.com";
    public static final String smtpServer = "smtp."+doname;
    //public static final int smtpPort = 25;
    public static final int smtpPort = 465;
    //public static final String imapPasswd = "pjaaegrcpruxbidf";
    //public static final String pop3Passwd = "rojunwbrhmhybhgi";
    public static final String imapServer = "imap."+doname;
    public static final String pop3Server = "pop."+doname;
    //public static final int imapPort = 465;
    public static final int pop3Port_SSL = 995;
}
