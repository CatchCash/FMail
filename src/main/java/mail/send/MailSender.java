package mail.send;

import bean.Mail;
import bean.User;
import mail.base.MailConstant;
import org.apache.commons.lang3.ObjectUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Dao on 2017/6/1.
 */
public class MailSender {
    private User user;
    private Mail mail;
    public void setUser(User user){
        this.user=user;
    }
    public void setMail(Mail mail){
        this.mail=mail;
    }
    public boolean sender(String to,String title,String bodyContent){
        
    }
    /*
    * @Param default config
    * @Return if no error about your message,return true else false;
    * */
    public boolean sender(){
        if(mail==null){
            return false;
        }
        else{
            return sender(mail);
        }
    }
    /*
    * @Param Mail mail 首先传入mail对象
    * @Return if no error about your message,return true else false;
    * */
    public static boolean sender(Mail mail){
        boolean result = true;
        String from = mail.getUser().getAccount();
        String password = mail.getUser().getPassword();
        String to = mail.getTo();
        String title = mail.getTitle();
        String bodyContent = mail.getBodyContent();

        //properties set
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol","smtp");// 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", MailConstant.smtpServer);// 发件人的邮箱的 SMTP服务器地址
        props.setProperty("mail.smtp.auth","true");// 请求认证，参数名称与具体实现有关

        //session part
        // 创建Session实例对象
        try {
        Session session = Session.getDefaultInstance(props);
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置收件人
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        // 设置发送日期
        message.setSentDate(new Date());
        // 设置邮件主题
        message.setSubject(title);
        // 设置纯文本内容的邮件正文
        message.setText(bodyContent);
        // 保存并生成最终的邮件内容
        message.saveChanges();
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        //Transport
        Transport transport = session.getTransport("smtp");
        // 第2个参数需要填写的是邮箱的SMTP的授权码/密码
        transport.connect(from, password);
        // 发送，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        } catch(NullPointerException e){
           System.out.println("You input some NULL content");
           result =false;
           e.printStackTrace();
        } catch(MessagingException e) {
            //发送失败
            result=false;
            e.printStackTrace();
        }finally {
            return result;
        }
    }
    public static void main(String[] args) throws Exception {
        Mail mail =new Mail();
        mail.setUser(new User(1,"wangsixiong@hnu.edu.cn","f1sd23a4","AES"));
        mail.setTo("chinaqqyx@qq.com");
        mail.setTitle("new test");
        mail.setBodyContent("test content");
        if(sender(mail))
            System.out.println("Send Success");
        else
            System.out.println("Send Error");
    }
}
