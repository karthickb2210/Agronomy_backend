package in.ironvalleyagro.Agronomy.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class PaymentController {

    private final RazorpayClient razorpayClient;

    public PaymentController() throws Exception {
        this.razorpayClient = new RazorpayClient("rzp_test_VIg1NaXkrCS4Lx", "WK4szpsjvFbz8lPgVzCe0a7d");
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestBody Map<String, Object> data) throws Exception {
        // Capture the amount from the request (in paise)

        int amount = (int) data.get("amount");

        // Create order in Razorpay
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount); // amount in paise (e.g., 10000 = ₹100)
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_rcptid_11");

        Order order = razorpayClient.Orders.create(orderRequest);
        System.out.println(order.toString());

        // Return order details as JSON
        return order.toString();
    }
}

