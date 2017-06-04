package mail.receive;

import bean.Mail;
import bean.User;
import mail.base.MailConstant;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
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
    * @Return NULL User return null,else return true when success or false when error
    * */
    public Mail[] Receives(){
        if(user==null)
            return null;
        else{
            return Receives(user,0,Integer.MAX_VALUE);
        }
    }
    /*
    * @Return NULL User return null,else return true when success or false when error
    * */
    public static Mail[] Receives(User user){
        System.out.println("Receives(User user)");
        return Receives(user,0,10);
    }
    /*
    *
    * */
    public static Mail[] Receives(User user, int start, int end){
        System.out.println("Receives(User user,int start,int end) start="+start+" end="+end);
        Mail[] mails = null;
        // 定义连接POP3服务器的属性信息
        String pop3Server = MailConstant.pop3Server;
        String pop3Port_SSL = String.valueOf(MailConstant.pop3Port_SSL);
        String protocol = "pop3";
        String username = user.getAccount();
        String password = user.getPassword(); // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

        Properties props = new Properties();
        props.put("mail.pop3.ssl.enable", true);
        props.put("mail.pop3.host", pop3Server);
        props.put("mail.pop3.port", pop3Port_SSL);


        System.out.println("Receives try connect");
        try {
        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = session.getStore(protocol);
        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        Folder folder = store.getFolder(MailConstant.FOLDER_INBOX);// 获得用户的邮件帐户
        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限
        Message[] messagesArray = folder.getMessages(1,10);// 得到邮箱帐户中的所有邮件
        List<Message> messages = new LinkedList<Message>() ;
        for(Message message:messagesArray){
            messages.add(message);
        }
            messages.sort(new Comparator<Message>() {
                public int compare(Message o1, Message o2) {
                    try {
                        return o1.getSentDate().compareTo(o2.getSentDate());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        mails=new Mail[messages.size()];
        int i=0;
        for(Message message:messages){
            mails[i]=new Mail();
            //mails[i].setFrom(messages[i].getFrom());
            mails[i].setFrom(getFrom((MimeMessage)message));
            mails[i].setTo(message.getAllRecipients());
            mails[i].setTitle(message.getSubject());
            mails[i].setBodyContent("");
            StringBuffer sb=new StringBuffer();
            getMailTextContent((MimeMessage) message,sb);
            mails[i++].setBodyContent(sb.toString());
            //mails[i].setBodyContent(messages[i].getContent().toString());
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
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }
    public static void main(String[] args) throws  Exception {
        long start=System.nanoTime();
        Mail[] mails = Receives(
                new User(
                        /*
                "wangsixiong@hnu.edu.cn",
                "f1sd23a4",*/
                        "386208935@qq.com",
                        "rojunwbrhmhybhgi",
                        null
                )
        );
        int count=1;
        for(Mail mail:mails){
            System.out.println("EmailNo:"+count);
            System.out.println(mail.toString());
            System.out.println("------------Email:"+(count++)+"------------");
        }
        System.out.println((System.nanoTime()-start)/1000000+"ms");
    }

    /**
     * 获得邮件发件人
     * @param msg 邮件内容
     * @return 姓名 <Email地址>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1)
            throw new MessagingException("没有发件人!");

        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";

        return from;
    }
    /**
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     * @throws UnsupportedEncodingException
     */
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }
}
