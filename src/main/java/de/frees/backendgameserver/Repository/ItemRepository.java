package de.frees.backendgameserver.Repository;

import de.frees.backendgameserver.model.ItemEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<ItemEntity, String> {
  Optional<ItemEntity> findByItemId(String itemId);

  boolean existsByItemId(String itemId);

  void deleteByItemId(String itemId);
}
