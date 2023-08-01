package com.example.datn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendMailService {

     @Autowired
    private JavaMailSender mailSender;


     public void sendMail(String to, String content, String text) throws MessagingException {
         MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         try {
             helper.setTo(to);
             helper.setSubject(content);
             helper.setText(text,true);
             mailSender.send(message);
         } catch (MessagingException e) {
             e.printStackTrace();
         }
     }

    public void sendMailAttachment(String to, String content, String text, String attachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setTo(to);
            helper.setSubject(content);
            helper.setText(text,true);
            FileSystemResource fileSystemResource= new FileSystemResource(new File(attachment));
            helper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
