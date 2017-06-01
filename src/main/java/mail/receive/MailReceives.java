package mail.receive;

import bean.Mail;
import bean.User;
import mail.base.MailConstant;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
/**
 * Created by Dao on 2017/5/29.
 */
public class MailReceives {
    private User user;
    public void setUser(User user){
        this.user=user;
    }
    /*
    * Before :YOU NEED TO INITIALIZE USER OBJECT
    * @Param FOLDER : INBOX/SENDED/DRAFT/DELETE/SPAM
    * @Return NULL User return null,else return true when success or false when error
    * */
    public Mail[] Receives(String FOLDER){
        if(user==null)
            return null;
        else{
            return Receives(user,FOLDER);
        }
    }
    /*
    *
    * */
    public static Mail[] Receives(User user, String FOLDER){
        Mail[] mails = null;
        // 定义连接POP3服务器的属性信息
        String pop3Server = MailConstant.pop3Server;
        String protocol = "pop3";
        String username = user.getAccount();
        String password = user.getPassword(); // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址



        try {
        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = session.getStore(protocol);
        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        Folder folder = store.getFolder(FOLDER);// 获得用户的邮件帐户
        folder.open(Folder.); // 设置对邮件帐户的访问权限
        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件
        mails=new Mail[messages.length];
        for(int i=0;i<messages.length;i++){
            mails[i]=new Mail();
            mails[i].setFrom(messages[i].getFrom());
            mails[i].setTo(messages[i].getAllRecipients());
            mails[i].setTitle(messages[i].getSubject());
            mails[i].setBodyContent(messages[i].getContent().toString());
        }
        folder.close(false);// 关闭邮件夹对象
        store.close(); // 关闭连接对象
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            return mails;
        }
    }
    public static void main(String[] args) throws Exception {
        Mail[] mails = Receives(
                new User(0,"wangsixiong@hnu.edu.cn","f1sd23a4","AES"),
                MailConstant.FOLDER_SENDED
        );
        int count=1;
        for(Mail mail:mails){
            System.out.println("EmailNo:"+count);
            System.out.println(mail.toString());
            System.out.println("------------Email:"+(count++)+"------------");
        }

    }
}
