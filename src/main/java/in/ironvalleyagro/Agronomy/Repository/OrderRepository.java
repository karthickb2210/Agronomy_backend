package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,Long> {
    List<Order> findAllByUserid(long id);

    List<Order> findAllByEmail(String mail);

    Order findTopByUseridOrderByCreatedAtDesc(long userId);
}
