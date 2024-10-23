package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AddressRepository extends MongoRepository<Address,Long> {
    List<Address> findAllByUserId(long id);

    List<Address> findAllByEmail(String email);
}
