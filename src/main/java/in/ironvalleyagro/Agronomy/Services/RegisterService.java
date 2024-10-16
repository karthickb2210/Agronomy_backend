package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.AuthUser;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    boolean flag;
    public Response newUser(User user){
        Response res = new Response();
        if(userRepository.existsByMail(user.getMail())) {
            res.setStatusCode(ResponseCode.DUPLICATE_MAIL_ENTRY);
            return res;
        }
        long newId = generatorService.generateSequence(AuthUser.SEQUENCE_NAME);
        user.setPassword(user.getPassword());
        user.setId(newId);
        AuthUser authUser = new AuthUser();
        authUser.setId(newId);
        authUser.setUsername(user.getMail());
        authUser.setPassword(user.getPassword());
        authUser.setActive(true);
        authUserRepository.save(authUser);
        User newUser = userRepository.save(user);
        res.setData(newUser);
        return res;
    }
}
