package in.ironvalleyagro.Agronomy.Controller;

import com.razorpay.Utils;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class PaymentVerificationController {

    @PostMapping("/verify-payment")
    public String verifyPayment(@RequestBody Map<String, String> data) throws Exception {

        String secret = "xNHTSwpWL6pLzNHVujb0iBX6";
        JSONObject jsObject = new JSONObject();
        jsObject.append("razorpay_order_id",data.get("razorpay_order_id"));
        jsObject.append("razorpay_payment_id",data.get("razorpay_payment_id"));
        jsObject.append("razorpay_signature",data.get("razorpay_signature"));
        boolean isSignatureValid = Utils.verifyPaymentSignature(jsObject, secret);

        if (isSignatureValid) {
            // Success
            return "Payment is successful and verified";
        } else {
            // Failure
            return "Payment signature verification failed";
        }
    }
}

