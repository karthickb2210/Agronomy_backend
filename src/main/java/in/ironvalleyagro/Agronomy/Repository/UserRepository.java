package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {
    User findByUsername(String s);

    User findByMail(String email);

    boolean existsByMail(String email);

    boolean existsByMobileNumber(String mobileNumber);
}
