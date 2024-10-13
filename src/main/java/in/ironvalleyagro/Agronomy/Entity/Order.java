package in.ironvalleyagro.Agronomy.Entity;

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

@Data
@Builder
@Document("Orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "OrderId";
    @Id
    private long orderId;
    private String email;
    @Indexed
    private long userid;
    private Object orderDetails;
    private String paymentId;

    private String porterTrackerId;
    private boolean isDelivered;
    private String amountPaid;
    @CreatedDate
    private LocalDateTime createdAt;

}
