package in.ironvalleyagro.Agronomy.DTO;

import in.ironvalleyagro.Agronomy.Entity.Address;
import in.ironvalleyagro.Agronomy.Model.OrderDetails;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String email;
    private List<OrderDetails> orderDetails;
    private Object addressDetails;
    private String paymentId;
    private String amountPaid;
}
