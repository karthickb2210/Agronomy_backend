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
}
