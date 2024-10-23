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
@Document("Address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Transient
    public static final String SEQUENCE_NAME = "AddressId";

    @Id
    private long addressId;
    private long userId;
    private String name;
    @Indexed
    private String email;
    private String mobileNumber;
    private String house;
    private String street;
    private String address;
    private String city;
    private String state;
    private String zip;
    @CreatedDate
    private LocalDateTime createdAt;

}
