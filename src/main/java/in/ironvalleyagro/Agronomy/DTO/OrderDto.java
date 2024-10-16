package in.ironvalleyagro.Agronomy.DTO;

import in.ironvalleyagro.Agronomy.Entity.Address;
import lombok.Data;

@Data
public class OrderDto {
    private String email;
    private Object orderDetails;
    private Object addressDetails;
    private String paymentId;
    private String amountPaid;
}
