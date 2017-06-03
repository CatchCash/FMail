package bean;

import javax.mail.Address;

/**
 * Created by Dao on 2017/6/1.
 */
public class Mail {
    /*发件人基本信息*/
    private String from;
    /*收件人*/
    private String to;
    /*标题*/
    private String title;
    /*主体内容*/
    private String bodyContent;

    public Mail(){}
/*
*  send
* */
    public Mail(String to, String title, String bodyContent) {
        this.to = to;
        this.title = title;
        this.bodyContent = bodyContent;
    }
    /*
    * receive
    * */
    public Mail(String from, String to, String title, String bodyContent) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.bodyContent = bodyContent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setFrom(Address[] from) {
        StringBuilder tmp=new StringBuilder();
        for(Address address:from){
            tmp.append(address.toString()+";");
        }
        this.from=tmp.toString();
    }
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTo(Address[] to) {
        StringBuilder tmp=new StringBuilder();
        for(Address address:to){
            tmp.append(address.toString()+";");
        }
        this.to=tmp.toString();
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
    @Override
    public String toString(){
        return "From:\n"+this.getFrom()+"\n"+
                "To:\n"+this.getTo()+"\n"+
                "Title:\n"+this.getTitle()+"\n"+
                "Content:\n"+this.getBodyContent()+"\n";
    }
}
