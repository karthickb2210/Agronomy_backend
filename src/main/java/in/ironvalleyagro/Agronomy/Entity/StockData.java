package in.ironvalleyagro.Agronomy.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("StockData")
@AllArgsConstructor
@NoArgsConstructor
public class StockData {
    @Id
    private String itemId;
    private long babySpinachQuantity;
    private long pakChoiQuantity;
    private long basilQuantity;
    private long kaleQuantity;
    private long lettuceQuantity;
    private long argulaQuantity;
    private long beetRootQuantity;
    private long radishPurpleQuantity;
    private long radishWhiteQuantity;
    private long radishPinkQuantity;
    private long mustardQuantity;
    private long sunflowerQuantity;
    private long peaShootQuantity;
    private long broccoliQuantity;
    private long redCabbageQuantity;
}
