package in.ironvalleyagro.Agronomy.Entity;

import in.ironvalleyagro.Agronomy.Model.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Document("Subscription")
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Transient
    public static final String SEQUENCE_NAME = "Subscription_Id";
    @Id
    private long subscriptionId;

    private String email;
    @Indexed
    private long userid;
    private Object subscriptionDetails;
    private String subscriptionType;
    private int boxSize;
    private String orderId;
    private String signature;
    private String paymentId;
    @CreatedDate
    private LocalDateTime createdAt;
}
