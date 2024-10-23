package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.ResourceReaderRepositoryPopulator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @DeleteMapping("/deleteUser/{mail}")
    public Response deleteAccount(@PathVariable String mail){
        Response res = new Response();
        try{
            if(!userRepository.existsByMail(mail)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            User user = userRepository.findByMail(mail);
            userRepository.deleteById(user.getId());
            authUserRepository.deleteById(user.getId());
            res.setFlag(true);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
