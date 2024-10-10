package in.ironvalleyagro.Agronomy.Repository;

import in.ironvalleyagro.Agronomy.Entity.StockData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockDataRepository extends MongoRepository<StockData,String> {

}
