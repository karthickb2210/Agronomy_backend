package in.ironvalleyagro.Agronomy.DTO;

import lombok.Data;

@Data
public class OrderDto {
    private String email;
    private Object orderDetails;
    private String paymentId;
    private String amountPaid;
}
