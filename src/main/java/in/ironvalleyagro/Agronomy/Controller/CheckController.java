package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Model.Cred;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @Autowired
    private CheckUserService checkUserService;

    @PostMapping("/checkUser")
    public Response checkUser(@RequestBody Cred cred){
        return checkUserService.checkUser(cred);
    }
}
