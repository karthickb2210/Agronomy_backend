package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.Address;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.Subscription;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.DashBoardData;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AddressRepository;
import in.ironvalleyagro.Agronomy.Repository.OrderRepository;
import in.ironvalleyagro.Agronomy.Repository.SubscriptionRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardDetails {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Response getDashBoardDetails(String email){
        Response res = new Response();
        try {
            if(!userRepository.existsByMail(email)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            User user = userRepository.findByMail(email);
            DashBoardData dashBoardData = new DashBoardData();
            dashBoardData.setProfileDetails(user);
            List<Subscription> subscriptionList = subscriptionRepository.findAllByEmail(email);
            dashBoardData.setSubscriptionDetails(subscriptionList);
            List<Order> orders = orderRepository.findAllByUserid(user.getId());
            dashBoardData.setOrderDetails(orders);
            List<Address> addresses = addressRepository.findAllByUserId(user.getId());
            dashBoardData.setAddressDetails(addresses);
            res.setData(dashBoardData);
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
