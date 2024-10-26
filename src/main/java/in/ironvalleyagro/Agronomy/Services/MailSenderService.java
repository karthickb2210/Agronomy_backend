package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Model.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("karthickb@ivsourcing.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public Response sendRegisterMail(String mailId,String userName) throws MessagingException {
        Response res = new Response();
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setText("Welcome to Iron Valley");
            helper.setTo(mailId);
            helper.setSubject("Welcome");
            helper.setFrom("karthickb@ivsourcing.com");
            System.out.println(helper.toString());
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


}
