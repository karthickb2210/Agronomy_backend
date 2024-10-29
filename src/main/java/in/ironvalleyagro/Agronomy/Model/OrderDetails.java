package in.ironvalleyagro.Agronomy.Model;

import lombok.Data;

@Data
public class OrderDetails {
    private String itemName;
    private int itemQuantity;
    private int itemGrams;
}
