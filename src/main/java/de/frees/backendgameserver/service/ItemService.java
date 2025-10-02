package de.frees.backendgameserver.service;

import com.example.itemapi.model.ItemOv1DTO;
import com.example.itemapi.model.ItemPageOv1DTO;
import de.frees.backendgameserver.Repository.ItemRepository;
import de.frees.backendgameserver.mapper.ItemMapper;
import de.frees.backendgameserver.model.ItemEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

  public ItemPageOv1DTO findAllItems(int limit, int offset) {
    PageRequest pageRequest = PageRequest.of(offset, limit);
    List<ItemEntity> itemEntityList = itemRepository.findAll(pageRequest).getContent();
    ItemPageOv1DTO itemPageDTO = new ItemPageOv1DTO();
    itemPageDTO.setContent(
        itemEntityList.stream().map(itemMapper::itemEntityToItemDTO).collect(Collectors.toList()));
    itemPageDTO.setLimit(limit);
    itemPageDTO.setOffset(offset);
    itemPageDTO.setTotal(itemEntityList.size());
    return itemPageDTO;
  }

  public String createItem(ItemOv1DTO itemDTO) {
    log.info("Creating Item");
    UUID uuid = UUID.randomUUID();

    ItemEntity itemEntity = itemMapper.itemDTOToItemEntity(itemDTO);
    itemEntity.setItemId(uuid.toString());

    itemRepository.save(itemEntity);
    log.info("Item created with id: '{}'", itemEntity.getItemId());
    return itemEntity.getItemId();
  }
}
