package pro1041.team_3.util;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author sonhn
 */
public class EmailUtil {

    public static void send(String to, String subject, String body) {
        final String from = "waikikiphonesystem@gmail.com";
        final String pass = "loswmmaumazyjkyd";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.debug", "true");
        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to, false)
            );
            message.setSubject(subject);
            String content = "<h1>" + body + "</h1>";
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendFile(String to, String subject, String body, File file, String fileName) {
        final String from = "waikikiphonesystem@gmail.com";
        final String pass = "loswmmaumazyjkyd";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.debug", "true");
        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to, false)
            );
            message.setSubject(subject);
            DataSource source = new FileDataSource(file);
            message.setDataHandler(new DataHandler(source));
            message.setFileName(fileName + ".pdf");
//            String content = "<h1>" + body + "</h1>";
//            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
