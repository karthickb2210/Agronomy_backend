package in.ironvalleyagro.Agronomy.Model;

import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LastOrderWithCred {
    private Order order;
    private User user;
}
