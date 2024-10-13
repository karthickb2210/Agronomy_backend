package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.DTO.SubscriptionDto;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/admin/getAllSubscriptions")
    public Response getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping("/addSubscription")
    public Response addSubscription(@RequestBody SubscriptionDto subscriptionDto){
        return  subscriptionService.addSubscription(subscriptionDto);
    }
}
