package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Models.MailDetails;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TestController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/sendMail")
    public void SendMail(@RequestBody MailDetails mailDetails){
        System.out.println(mailDetails.toString());
        mailSenderService.sendEmail(mailDetails.getEmail(), "Confirmation on booking",
                " Your booking has been confirmed successfully");

    }
}
