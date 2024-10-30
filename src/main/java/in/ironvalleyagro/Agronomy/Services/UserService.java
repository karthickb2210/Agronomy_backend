package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    public Response changeMobileNumber(String mail,String mobileNumber){
        Response res = new Response();
        try{
            if(!userRepository.existsByMail(mail)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            User user = userRepository.findByMail(mail);
            user.setMobileNumber(mobileNumber);
            userRepository.save(user);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setFlag(true);
            res.setData(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
