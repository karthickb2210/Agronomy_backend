package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Models.MailDetails;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
@GetMapping("/test")
    public String test(){
    return "Working";
}
}
