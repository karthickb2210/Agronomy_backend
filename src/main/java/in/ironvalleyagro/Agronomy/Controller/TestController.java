package in.ironvalleyagro.Agronomy.Controller;

import com.mongodb.MongoException;
import in.ironvalleyagro.Agronomy.Entity.StockData;
import in.ironvalleyagro.Agronomy.Model.AuthUser;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Repository.StockDataRepository;
import in.ironvalleyagro.Agronomy.Services.MailSenderService;
import in.ironvalleyagro.Agronomy.Services.SmsService;
import jakarta.mail.MessagingException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private StockDataRepository stockDataRepository;


    @Autowired
    private MailSenderService mailSenderService;




    @GetMapping("/stockTest")
    public Response testLink() throws MessagingException {

        StockData stockData = new StockData("0",9000,9000,9000,9000,9000,9000,9000,9000,9000 ,9000,9000,9000,9000,9000,9000);
        stockData.setItemId("0");
        stockDataRepository.save(stockData);
        return new Response();
    }


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
