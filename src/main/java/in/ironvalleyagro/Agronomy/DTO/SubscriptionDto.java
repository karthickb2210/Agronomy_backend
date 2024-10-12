package in.ironvalleyagro.Agronomy.DTO;

import lombok.Data;

@Data
public class SubscriptionDto {
    private String mail;
    private Object subscriptionDetails;
    private String orderId;
    private String signature;
    private String paymentId;
    private String subscriptionType;
    private int boxSize;
}
