package logic;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Stateless
public class EmailSender {
    final String username = "alhimoviciarsenii@gmail.com";
    final String password = "mdhr qafx fmmc lkpz";
    Properties properties = new Properties();
    Session session;
    @Inject
    LoggerService loggerService;

    public EmailSender(){
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
    }


    public void sendMessage(String title ,String messageText, String to){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title);
            message.setText(messageText);

            Transport.send(message);

            loggerService.logInfo("Успешно отправили сообщение юзеру: " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
