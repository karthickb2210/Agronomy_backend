package in.ironvalleyagro.Agronomy.Entity;

import in.ironvalleyagro.Agronomy.Model.OrderDetails;
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
import java.util.List;

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
    private List<OrderDetails> orderDetails;
    private String paymentId;
    private Object address;
    private String porterTrackerId;
    private boolean isDelivered;
    private String amountPaid;
    @CreatedDate
    private LocalDateTime createdAt;

}
