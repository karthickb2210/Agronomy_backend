package in.ironvalleyagro.Agronomy.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("User")
@AllArgsConstructor
public class User {
    @Id
    private long id;
    @Indexed
    private String username;
    private String mail;
    private String mobileNumber;
    private String password;
}
