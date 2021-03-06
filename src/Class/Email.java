package Class;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Utility class that responsible of email function
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Email {

    private static String USER_NAME = "javatest132";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "javatest"; // GMail password
    private static String RECIPIENT = "eugeneaad@yahoo.com.sg";

    public static void send(String subtext,String bodytext , String recipient) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { recipient }; // list of recipient email addresses
        String subject = subtext;
        String body = bodytext;

        sendFromGMail(from, pass, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}