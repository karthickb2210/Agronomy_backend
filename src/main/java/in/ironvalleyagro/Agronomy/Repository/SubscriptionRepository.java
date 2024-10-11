package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription,Long> {
}
