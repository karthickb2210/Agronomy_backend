package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.DTO.OrderDto;
import in.ironvalleyagro.Agronomy.DTO.SubscriptionDto;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.Subscription;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.OrderRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    @Autowired
    private OrderRepository orderRepository;

    public Response getAllOrders(){
        Response res = new Response();
        res.setFlag(true);
        res.setStatusCode(ResponseCode.CODE_SUCCESS);
        res.setData(orderRepository.findAll());
        return res;
    }

    public Response addOrder(OrderDto orderDto){
        Response res = new Response();
        try{
            long newId = generatorService.generateSequence(Order.SEQUENCE_NAME);
            User user = userRepository.findByMail(orderDto.getEmail());
            Order order = new Order();
            order.setEmail(orderDto.getEmail());
            order.setOrderId(newId);
            order.setUserid(user.getId());
            order.setOrderDetails(orderDto.getOrderDetails());
            order.setPaymentId(orderDto.getPaymentId());
            order.setCreatedAt(LocalDateTime.now());
            order.setAmountPaid(orderDto.getAmountPaid());
            Order newOrder = orderRepository.save(order);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setData(newOrder);

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
