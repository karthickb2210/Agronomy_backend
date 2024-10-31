package in.ironvalleyagro.Agronomy.Services;

import com.mongodb.client.result.UpdateResult;
import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.DTO.OrderDto;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.OrderRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private SmsService smsService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Response updatePorterId(long id,String porterId){
        Response res=  new Response();
        try{
            if(!orderRepository.existsById(id)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            Query query = new Query(Criteria.where("_id").is(id));
            Update update = new Update().set("porterTrackerId", porterId);
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Order.class);
            if(updateResult.getModifiedCount()==1){
                res.setFlag(true);
            }
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setData(updateResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

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
            System.out.println(orderDto.toString());
            long newId = generatorService.generateSequence(Order.SEQUENCE_NAME);
            User user = userRepository.findByMail(orderDto.getEmail());
            Order order = new Order();
            order.setEmail(orderDto.getEmail());
            order.setOrderId(newId);
            order.setUserid(user.getId());
            order.setAddress(orderDto.getAddressDetails());
            order.setOrderDetails(orderDto.getOrderDetails());
            order.setPaymentId(orderDto.getPaymentId());
            order.setCreatedAt(LocalDateTime.now());
            order.setPorterTrackerId("0");
            order.setAmountPaid(orderDto.getAmountPaid());
            Order newOrder = orderRepository.save(order);
            if(newOrder.getOrderId()!=0) {
                mailSenderService.sentOrderMail(newOrder);
            }
           res.setStatusCode(ResponseCode.CODE_SUCCESS);

            res.setData(newOrder);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Response updateOrderStatus(long id,int status){
        Response res = new Response();
        try{
            if(!orderRepository.existsById(id)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            boolean temp = status == 1;
            Query query = new Query(Criteria.where("_id").is(id));
            Update update = new Update().set("isDelivered", temp);
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Order.class);
            if(updateResult.getModifiedCount()==1){
                res.setFlag(true);
            }
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setData(updateResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
