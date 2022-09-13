package BatchParallelProcessing.Project2.Mail.MailSendingService;


// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class MailsendingService {
    
    // @Bean
    public void sendEmail(String toEmail, String Subject, String body) {

                
                final String sender = "affi01adarsh@gmail.com"; // The sender email
                final String urpass = "bxhhmeyvzvlfuoxr"; //keep it secure
                Properties set = new Properties();
                
                set.put("mail.smtp.starttls.enable", "true");
                set.put("mail.smtp.auth", "true");
                set.put("mail.smtp.host", "smtp.gmail.com");
                set.put("mail.smtp.port", "587");
                
                Session session = Session.getInstance(set,new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, urpass);
                }});
                
                try {
                    //email extends Java's Message Class, check out javax.mail.Message class to read more
                    Message email = new MimeMessage(session);
                    email.setFrom(new InternetAddress("affi01adarsh@gmail.com")); //sender email address here
                    email.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)); //Receiver email address here
                    email.setSubject(Subject); //Email Subject and message
                    email.setText("Hi, "+ body +"! It has been more than a month since we have seen you on melp app. Hope to see you soon :) ");
                    Transport.send(email);
                    System.out.println("Your email has successfully been sent!");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                
                System.out.println(" [ Mail sent to USER : " + body + " , EMAIL id : " + toEmail + " , SUBJECT : " + Subject + " ] ");
            }
}
