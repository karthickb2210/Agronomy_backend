package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Models.MailDetails;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/sendMail")
    public void SendMail(@RequestBody MailDetails mailDetails){
        String templates =
                "Dear " + mailDetails.getName() +",\n\n"+
                "We are delighted to confirm your appointment for a visit to our farm. Below are the details of your scheduled visit:\n\n" +
                "Date: "+mailDetails.getDate() +"\n" +
                "Time: "+mailDetails.getTime()+"\n" +
                "Location: 2ndAvenueStreet, SaptagiriColony, \n WestJaferkhanpet, K. K. Nagar, \n Chennai, Tamil Nadu 600083\n\n" +
                "If you have any special requests or need to reschedule, feel free to contact us.\n\n" +
                "We look forward to welcoming you and showing you around our farm!\n\n" +
                "Best regards,\n" +
                "Iron Valley Agronomy\n" +
                "ironvalleyagro.in";
        System.out.println(templates);
        mailSenderService.sendEmail(mailDetails.getEmail(), "Appointment Confirmation for Your Farm Visit on "+mailDetails.getDate()+" at "+ mailDetails.getTime() ,templates
                );

    }
}
