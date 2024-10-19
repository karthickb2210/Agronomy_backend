package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.DTO.SubscriptionDto;
import in.ironvalleyagro.Agronomy.Entity.Subscription;
import in.ironvalleyagro.Agronomy.Model.AuthUser;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AuthUserRepository;
import in.ironvalleyagro.Agronomy.Repository.SubscriptionRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SequenceGeneratorService generatorService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    public Response getAllSubscriptions(){
        Response res = new Response();
        try{
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            List<Subscription> subscriptionList = subscriptionRepository.findAll();
            res.setFlag(true);
            res.setData(subscriptionList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Response addSubscription(SubscriptionDto subscriptionDto){
        Response res = new Response();
        long newId = generatorService.generateSequence(Subscription.SEQUENCE_NAME);
        try{
            AuthUser user = authUserRepository.findByUsername(subscriptionDto.getMail());
            Subscription subscription = getSubscription(subscriptionDto, user, newId);
            Subscription newSubscription =  subscriptionRepository.save(subscription);
            if(newSubscription.getSubscriptionId()!=0) {
                res.setData(newSubscription);
                res.setFlag(true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    private static @NotNull Subscription getSubscription(SubscriptionDto subscriptionDto, AuthUser user, long newId) {
        Subscription subscription = new Subscription();
        subscription.setUserid(user.getId());
        subscription.setEmail(user.getUsername());
        subscription.setSubscriptionDetails(subscriptionDto.getSubscriptionDetails());
        subscription.setSubscriptionId(newId);
        subscription.setSubscriptionType(subscriptionDto.getSubscriptionType());
        subscription.setBoxSize(subscriptionDto.getBoxSize());
        subscription.setAddress(subscriptionDto.getAddress());
        subscription.setPaymentId(subscriptionDto.getPaymentId());
        subscription.setCreatedAt(LocalDateTime.now());
        return subscription;
    }
}
