package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    public Response sendRegisterMail(String mailId,String userName){
        Response res = new Response();
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("karthickb@ivsourcing.com");
            message.setTo(mailId);
            message.setText("Hii "+ userName+ " , welcome to Iron valley Agronomy ");
            message.setSubject("Welcome to IRON VALLEY");
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


}
