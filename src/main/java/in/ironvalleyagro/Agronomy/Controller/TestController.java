package in.ironvalleyagro.Agronomy.Controller;

import com.mongodb.MongoException;
import in.ironvalleyagro.Agronomy.Model.AuthUser;
import in.ironvalleyagro.Agronomy.Models.MailDetails;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {



    @Autowired
    private AuthUserRepository authUserRepository;



    @GetMapping("/test")
    public String test(){
    try {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("test200");
        System.out.println(hashedPassword);
        AuthUser flag = authUserRepository.save(new AuthUser(2, "test200", hashedPassword, true));
    }catch (MongoException e){
        System.out.println(e.getMessage());
    }
    return "Working";
}

@GetMapping("/testMessage")
    public String getAcces(){
        return "accessable";
}
}
