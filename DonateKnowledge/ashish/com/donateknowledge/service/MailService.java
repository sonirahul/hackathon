package com.donateknowledge.service;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailService {
    @Value("${sendMail}")
    String sendMail;

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
            this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg, true);

            UrlResource file;
            try {
                file = new UrlResource("http://localhost:8080/BookShop/images/logo.png");
                helper.addAttachment(file.getFilename(), file);
            } catch (MalformedURLException ex) {
                Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (MessagingException e) {
            throw new MailParseException(e);
        }

        if(sendMail.equals("true")) {
            mailSender.send(message);
        }
    }             
}
