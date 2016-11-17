package com.charitable.charity.crud.mail;

import com.charitable.charity.crud.model.Charity;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    final String localhost = "http://localhost:9051";
    final String username = "charitable2016@gmail.com";
    final String host = "smtp.gmail.com";
    final String password = "Imperi@l123";
    final String signature = "<br><br>Charitable, Imperial College London<br>" + "charitable2016@gmail.com";

    public EmailSender(){
    }


    public void sendVerificationEmail(Charity charityToVerify){    //will take String to user email, will take string to token.
        Properties properties = System.getProperties();
        setMail(properties);

        String to = "magdalena_s@hotmail.co.uk";  //remove me
        //String to = charityToVerify.getCharityEmail();
        //add null or empty string "" check

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication( username, password);
                    }
                });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Please verify your account with Charitable :)");
            String link = localhost + "/authenticate/" + charityToVerify.getToken();

            String text = "Thank you for making a decision to join Charitable! Please verify your email within 24 hours by clicking at the following link: <br>";
            String html = text + "<br><a href='" + link + "'>Link to accept</a>";
            message.setText(html, "UTF-8", "html");


            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.send(message);
            transport.close();

            System.out.println(link);

            System.out.println("Sent email message successfully to " + to);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


    public void sendWelcomeMessage(Charity charity) {    //will take String to user email, will take string to token.

        String to = charity.getCharityEmail();
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
                    "in with your username and password!";
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

    public void sendRejectionEmail(Charity charity) {    //will take String to user email, will take string to token.

        String to = charity.getCharityEmail();
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

            String text = "We are very sorry, but we were unable to verify " + charity.getCharityName() + "!" +
                            "<br>But don't you worry yet - our automated system can sometimes be a little silly. Please keep on checking your email - we will be in touch with you shortly!";

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

    private void setMail(Properties properties){
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
