package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.DTO.OrderDto;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getLastOrderDetails/{id}")
    public Response getLastOrderDetails(@PathVariable long id){
           return orderService.getLastOrder(id);
    }

    @GetMapping("/admin/getAllOrders")
    public Response getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }

    @GetMapping("/updatePorterId/{id}/{porterId}")
    public Response updatePorterId(@PathVariable long id,@PathVariable  String porterId){
    return orderService.updatePorterId(id,porterId);
    }

    @GetMapping("/updateOrderStatus/{id}/{status}")
    public Response updateOrderStatus(@PathVariable long id,@PathVariable int status){
        return orderService.updateOrderStatus(id,status);
    }

}
