package pl.wat.logic.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by michal on 23.04.2017.
 */
public class MailContainer {

    private MailSender mailSender;

    public MailContainer(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("projekteventer@gmail.com");
        javaMailSender.setPassword("Eventer2017");

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth","true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable","true");
        javaMailSender.setJavaMailProperties(javaMailProperties);

        this.mailSender=javaMailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
