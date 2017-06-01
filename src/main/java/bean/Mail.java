package bean;

/**
 * Created by Dao on 2017/6/1.
 */
public class Mail {
    /*发件人基本信息*/
    private User user;
    /*收件人*/
    private String to;
    /*标题*/
    private String title;
    /*主体内容*/
    private String bodyContent;

    public Mail(){}

    public Mail(User user, String to, String title, String bodyContent) {
        this.user = user;
        this.to = to;
        this.title = title;
        this.bodyContent = bodyContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
}
