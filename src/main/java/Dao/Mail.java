package Dao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Mail {
    public static void main(String[] args) {
        System.out.println("prepararing send to message");
        String message="Hello,thanks for Register ";
        String subject="Well com Profile ";
        String to="priyesh.jn786@gmail.com";
        String from="priyesh.bhawsar@hotwaxsystems.com";


        sentEmail(message,subject,to,from);
    }
        //this is responsible  to send email
        private static void sentEmail(String message, String subject, String to, String from)  {
        //variable for gmail
        String host = "smtp.gmail.com";
        //get the syatem.properties
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);
        //setting importatent information  to properties object
        //host set
        properties.put("mail.smtp.host", host); //host key value pair
        properties.put("mail.smtp.port", "465"); //port key value pair
        properties.put("mail.smtp.ssl.enable", "true"); //ssl enable key value pair
        properties.put("mail.smtp.auth", "true"); // auth enable key value pair
            FileInputStream fis= null;
            try {
                fis = new FileInputStream("resources/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Properties p= new Properties();
            try {
                p.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String username=p.getProperty("username");
            String password=p.getProperty("password");
            System.out.println(username);

        //step1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password");
            }
        });
        session.setDebug(true);
        //step2:compose the message [text,multi media]
        MimeMessage mimeMessage = new MimeMessage(session);
        //from emailId
        try {
            mimeMessage.setFrom(from);
            //Adding  recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //adding subject  to message
            mimeMessage.setSubject(subject);
            //addng text to message
            mimeMessage.setText(message);
            //send
            //step3: sending the message using transport class
            Transport.send(mimeMessage);
            System.out.println("Sent succefully");
        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
