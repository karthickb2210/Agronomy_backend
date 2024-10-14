package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import in.ironvalleyagro.Agronomy.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/existsByMail/{email}")
    public Response existByMail(@PathVariable String email){
        Response res = new Response();
        boolean isPresent = userRepository.existsByMail(email);
        res.setFlag(isPresent);
        if(isPresent)
        res.setData(userRepository.findByMail(email));
        return res;
    }

    @PostMapping("/register")
    public Response newUser(@RequestBody User user){
        return registerService.newUser(user);
    }
}
