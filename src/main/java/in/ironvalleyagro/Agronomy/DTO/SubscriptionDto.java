package in.ironvalleyagro.Agronomy.DTO;

import lombok.Data;

@Data
public class SubscriptionDto {
    private String mail;
    private Object subscriptionDetails;
    private String paymentId;
    private Object address;
    private String subscriptionType;
    private int boxSize;
}
