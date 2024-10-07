package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthUserRepository extends MongoRepository<AuthUser,Long> {

    AuthUser findByUsername(String s);
}
