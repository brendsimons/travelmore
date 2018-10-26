package be.thomasmore.travelmore.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class MailService {

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public void send(String email, String subject, String textMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(textMessage);

            Transport.send(message);
        } catch (MessagingException e) {
            Logger.getLogger(MailService.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}
