package in.ironvalleyagro.Agronomy.Controller;

import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import in.ironvalleyagro.Agronomy.Model.RazorPay;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PaymentVerificationController {

    @Value("${razor.secret}")
    private String razorSecret;

    @PostMapping("/verify-payment")
    public boolean verifyPayment(@RequestBody RazorPay razorPay) throws Exception {

        // RazorpayClient razorpayClient = new RazorpayClient("rzp_test_wbXDO68U56KR2k", "xNHTSwpWL6pLzNHVujb0iBX6");
        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", razorPay.getRazor_pay_order_id());
        options.put("razorpay_payment_id", razorPay.getRazorpay_payment_id());
        options.put("razorpay_signature", razorPay.getRazorpay_signature());

        boolean status =  Utils.verifyPaymentSignature(options, razorSecret);
        return status;
    }
}

