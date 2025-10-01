package de.frees.backendgameserver.service;

import com.example.itemapi.model.ItemCreateRequestDTO;
import com.example.itemapi.model.ItemDTO;
import de.frees.backendgameserver.Repository.ItemRepository;
import de.frees.backendgameserver.mapper.ItemMapper;
import de.frees.backendgameserver.model.ItemEntity;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemService {

  private final ItemMapper itemMapper = ItemMapper.INSTANCE;

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public UUID createItem(ItemCreateRequestDTO itemCreateRequestDTO) {
    log.info("Creating Item");
    ItemDTO itemDTO = itemMapper.itemToItemCreateRequestDTO(itemCreateRequestDTO);

    ItemEntity itemEntity = itemMapper.itemDTOToItem(itemDTO);

    itemRepository.save(itemEntity);
    log.info("Item created with id: '{}'", itemEntity.getUuid().toString());
    return itemEntity.getUuid();
  }
}
