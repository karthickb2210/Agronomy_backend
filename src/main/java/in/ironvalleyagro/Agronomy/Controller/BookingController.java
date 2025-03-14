package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Models.MailDetails;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BookingController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/sendMail")
    public void SendMail(@RequestBody MailDetails mailDetails){
        String templates =
                "Dear " + mailDetails.getName() +",\n\n"+
                        "We are delighted upon your appointment for a visit to our farm. You will be receiving an email on visit confirmation:\n\n" +
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
    mailSenderService.sendEmail("sales@ivsourcing.com","Received Appointment on "+mailDetails.getDate()+" at "+mailDetails.getTime()," " +
            " We have received a booking appointment from "+mailDetails.getName()+" .To visit Us on "+ mailDetails.getDate()+" at "+mailDetails.getTime()+" \n\n"+" The message from them is -- "+mailDetails.getMessage()
    );
    }
}
