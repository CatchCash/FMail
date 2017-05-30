package mail.receive;

import mail.MailConstant;

import javax.mail.*;
import java.util.Properties;
/**
 * Created by Dao on 2017/5/29.
 */
public class MailReceives {
    public static void main(String[] args) throws Exception {
        // 定义连接POP3服务器的属性信息
        String pop3Server = "pop.qq.com";
        String protocol = "pop3";
        String username = "386208935@qq.com";
        String pop3Port = String.valueOf(MailConstant.pop3Port_SSL);
        String password = MailConstant.pop3Passwd; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

        Properties props = new Properties();
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址
        props.setProperty("mail.smtp.port", "465"); // 发件人的邮箱的 SMTP服务器端口
        props.setProperty("mail.smtp.socketFactory.port", "465");


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
            message.writeTo(System.out);// 输出邮件内容到控制台
        }

        folder.close(false);// 关闭邮件夹对象
        store.close(); // 关闭连接对象
    }
}
