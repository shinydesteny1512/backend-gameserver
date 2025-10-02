package de.frees.backendgameserver.Repository;

import de.frees.backendgameserver.model.ItemEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<ItemEntity, UUID> {}
