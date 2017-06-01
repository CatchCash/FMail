package mail.receive;

import bean.User;

import javax.mail.*;
import java.util.Properties;
/**
 * Created by Dao on 2017/5/29.
 */
public class MailReceives {
    private User user;
    public void setUser(User user){
        this.user=user;
    }
    public Message[] Receives(){
        if(user==null)
            return null;
        else{

        }
    }
    public static Message[] Receives(User user){

    }
    public static void main(String[] args) throws Exception {
        // 定义连接POP3服务器的属性信息
        String pop3Server = "pop3.hnu.edu.cn";
        String protocol = "pop3";
        String username = "wangsixiong@hnu.edu.cn";
        String password = "f1sd23a4"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址



        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = session.getStore(protocol);

        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件

        for (Message message : messages) {
            String subject = message.getSubject();// 获得邮件主题
            Address from = (Address) message.getFrom()[0];// 获得发送者地址
            System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from);
            System.out.println("邮件的内容为：");
            System.out.println(message.getContent().toString());
            //message.writeTo(System.out);// 输出邮件内容到控制台
        }

        folder.close(false);// 关闭邮件夹对象
        store.close(); // 关闭连接对象
    }
}
