package de.frees.backendgameserver.service;

import com.example.itemapi.model.ItemOv1DTO;
import com.example.itemapi.model.ItemPageOv1DTO;
import de.frees.backendgameserver.exception.objects.ItemNotFoundException;
import de.frees.backendgameserver.mapper.ItemMapper;
import de.frees.backendgameserver.model.ItemEntity;
import de.frees.backendgameserver.repository.ItemRepository;
import de.frees.backendgameserver.repository.OffsetBasedPageRequest;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemService {

  private final ItemMapper itemMapper;

  private final ItemRepository itemRepository;

  public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
    this.itemMapper = itemMapper;
    this.itemRepository = itemRepository;
  }

  @NonNull
  public ItemPageOv1DTO findAllItems(int limit, int offset) {
    if (limit < 1 || limit > 100) {
      throw new IllegalArgumentException("Limit must be between 1 and 100");
    }
    if (offset < 0) {
      throw new IllegalArgumentException("Offset must be greater than or equal to 0");
    }

    Page<ItemEntity> itemPage = itemRepository.findAll(new OffsetBasedPageRequest(limit, offset));
    ItemPageOv1DTO itemPageDTO = new ItemPageOv1DTO();
    itemPageDTO.setContent(
        itemPage.getContent().stream().map(itemMapper::mapFromEntityToDto).toList());
    itemPageDTO.setLimit(limit);
    itemPageDTO.setOffset(offset);
    itemPageDTO.setTotal((int) itemPage.getTotalElements());
    itemPageDTO.setHasNext(itemPage.hasNext());
    itemPageDTO.setHasPrevious(offset > 0);
    return itemPageDTO;
  }

  @NonNull
  public ItemOv1DTO findById(UUID id) {
    log.info("Find Item by ID {}", id);
    ItemEntity itemEntity =
        itemRepository
            .findByItemId(id.toString())
            .orElseThrow(() -> new ItemNotFoundException(id));
    return itemMapper.mapFromEntityToDto(itemEntity);
  }

  @NonNull
  public UUID createItem(ItemOv1DTO itemDTO) {
    log.info("Creating Item");
    UUID itemId = UUID.randomUUID();
    ItemEntity itemEntity = itemMapper.mapFromDtoToEntity(itemDTO);
    itemEntity.setItemId(itemId.toString());

    itemRepository.save(itemEntity);
    log.info("Item created with id: '{}'", itemEntity.getItemId());

    return itemId;
  }

  @NonNull
  public ItemOv1DTO updateItem(UUID id, ItemOv1DTO itemDTO) {
    log.info("Update Item by ID {}", id);
    ItemEntity existingItem =
        itemRepository
            .findByItemId(id.toString())
            .orElseThrow(() -> new ItemNotFoundException(id));

    ItemEntity updatedItem = itemMapper.mapFromDtoToEntity(itemDTO);
    updatedItem.setId(existingItem.getId());
    updatedItem.setItemId(existingItem.getItemId());
    updatedItem.setCreatedAt(existingItem.getCreatedAt());

    ItemEntity savedItem = itemRepository.save(updatedItem);
    return itemMapper.mapFromEntityToDto(savedItem);
  }

  @NonNull
  public UUID deleteById(UUID id) {
    log.info("Delete Item by ID {}", id);
    String itemId = id.toString();
    boolean exists = itemRepository.existsByItemId(itemId);
    if (!exists) {
      throw new ItemNotFoundException(id);
    }
    itemRepository.deleteByItemId(itemId);

    return id;
  }
}
