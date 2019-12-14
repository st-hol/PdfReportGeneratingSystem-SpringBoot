package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.training.util.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMailWithAttachment(Mail mail, byte[] pdfBytes) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getTo());

        InputStreamSource source = new ByteArrayResource(pdfBytes, "report.pdf");
        helper.addAttachment("report.pdf", source);
        emailSender.send(message);
        //It might be that gmail uses this delay to prevent spammers from using their SMTP server from the "outside": if the SMTP is called from the actual webmail client it would not use this delay.
    }

}