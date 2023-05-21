package com.example.AutoCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailUtil
{

    @Autowired
    private static JavaMailSender mailSender;
    public static void sendMail(String recepient) throws MessagingException
    {
        System.out.println("Preparing to send email");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "20tanu1999@gmail.com";
        String password = "pxtbporqzpolimjo";

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient)
    {
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("The health status of demo application");
            AutoCheckController obj = new AutoCheckController();
            try {
                String answer = obj.getEndpoint();
                SimpleMailMessage m = new SimpleMailMessage();
                m.setTo(recepient);
                m.setSubject("The health status of demo application");
                m.setText(answer);
                mailSender.send(m);
                message.setText(answer);
            }
            catch (Exception e) {
                message.setText("ERROR 400");
                return message;
            }
            return message;
        }
        catch(Exception ex)
        {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}



