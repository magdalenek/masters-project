package com.charitable.user.email;

import com.charitable.user.model.User;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    final String localhost = "http://localhost:9052";
    final String username = "email";
    final String host = "smtp.gmail.com";
    final String password = "pass";
    final String signature = "Charitable, Imperial College London<br>";

    public EmailSender() {
    }


    public void sendVerificationEmail(User user) {  
        Properties properties = System.getProperties();
        setMail(properties);
        
        String to = user.getCharityEmail();

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Please verify your account with Charitable :)");
            String link = localhost + "/authenticate/" + user.getToken();

            String text = "Thank you for making a decision to join Charitable! Please verify your email by clicking at the following link: \n";
            String html = text + "\n<a href='" + link + "'>Link to accept</a>";
            message.setText(html, "UTF-8", "text/html");


            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.send(message);
            transport.close();

            System.out.println(link);

            System.out.println("Sent email message successfully to " + to);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


    public void sendWelcomeMessage(User charity) {

        String to = charity.getEmail();
        Properties properties = System.getProperties();
        setMail(properties);

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Welcome to Charitable!");

            String text = "This is it - you are now part of Charitable family! You can now access your account by logging " +
                    "in with your username and password at \n";
            String html = text + "\n" + signature;
            message.setText(html, "UTF-8", "html");


            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.send(message);
            transport.close();


            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    public void sendRejectionEmail(User user) {    //will take String to user email, will take string to token.

        String to = user.getEmail();
        Properties properties = System.getProperties();
        setMail(properties);

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Oops.. Something went wrong :(");

            String text = "We are very sorry, but we were unable to create an account for you." +
                    "But don't you worry yet - our automated system can sometimes be a little silly. Please keep on checking your email - we will be in touch with you shortly!";

            String html = text + signature;
            message.setText(html, "UTF-8", "html");


            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.send(message);
            transport.close();


            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    private void setMail(Properties properties) {
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }
}
