package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Cred;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckUserService {

    @Autowired
    private UserRepository userRepository;

    public Response checkUser(Cred cred){
        Response res = new Response();

        List<User> users = userRepository.findAll();
        List<User> nameMatch = users.stream().filter((user)->{
            return user.getMail().equalsIgnoreCase(cred.getName())
                    && user.getPassword().equals(cred.getPass());
        }).toList();
        System.out.println(nameMatch);
        res.setFlag(!nameMatch.isEmpty());
        if(res.isFlag()){
            res.setData(nameMatch.get(0));
        }
        return res;
    }
}
