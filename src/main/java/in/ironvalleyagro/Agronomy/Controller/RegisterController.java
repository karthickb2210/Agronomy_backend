package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Response newUser(@RequestBody User user){
        Response res = new Response();
        try{
            User newUser = registerService.newUser(user);
            res.setData(newUser);
            res.setFlag(newUser.getId()!=0);
            System.out.println(newUser.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
