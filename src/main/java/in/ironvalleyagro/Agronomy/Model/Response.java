package in.ironvalleyagro.Agronomy.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int statusCode;
    private boolean flag;
    private Object data;
}
