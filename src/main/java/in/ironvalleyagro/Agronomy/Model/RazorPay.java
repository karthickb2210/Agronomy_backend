package in.ironvalleyagro.Agronomy.Model;

import lombok.Data;

@Data
public class RazorPay {
    private String razor_pay_order_id;
    private String razorpay_payment_id;
    private String razorpay_signature;
}
