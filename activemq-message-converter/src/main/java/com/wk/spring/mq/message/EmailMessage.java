package com.wk.spring.mq.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tyue
 * @Email blackdancer.xm@gmail.com
 * @Date 2016/8/10 14:30
 * @Desc Email对象
 */
public class EmailMessage implements Serializable {

    private static final long serialVersionUID = 7048638482863320806L;
    /**
     * 发件人
     */
    private String from;
    /**
     * 要发送到的email地址
     */
    private List<String> mailTo = new ArrayList<String>();
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getMailTo() {
        return mailTo;
    }

    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "from='" + from + '\'' +
                ", mailTo=" + mailTo +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
