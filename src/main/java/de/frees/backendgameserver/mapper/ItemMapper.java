package de.frees.backendgameserver.mapper;

import com.example.itemapi.model.ItemOv1DTO;
import de.frees.backendgameserver.model.ItemEntity;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  public ItemOv1DTO itemEntityToItemDTO(ItemEntity itemEntity) {
    ItemOv1DTO itemDTO = new ItemOv1DTO();
    itemDTO.setItemId(itemEntity.getItemId() != null ? itemEntity.getItemId() : null);
    itemDTO.setItemName(itemEntity.getItemName() != null ? itemEntity.getItemName() : null);
    itemDTO.setPrice(itemEntity.getPrice() != null ? itemEntity.getPrice() : null);
    itemDTO.setDescription(
        itemEntity.getDescription() != null ? itemEntity.getDescription() : null);
    itemDTO.setCategory(
        itemEntity.getCategory() != null
            ? ItemOv1DTO.CategoryEnum.valueOf(itemEntity.getCategory().toUpperCase())
            : null);
    itemDTO.setCreatedAt(
        itemEntity.getCreatedAt() != null
            ? itemEntity.getCreatedAt().atOffset(ZoneOffset.UTC)
            : null);
    itemDTO.setUpdatedAt(
        itemEntity.getUpdatedAt() != null
            ? itemEntity.getUpdatedAt().atOffset(ZoneOffset.UTC)
            : null);
    return itemDTO;
  }

  public ItemEntity itemDTOToItemEntity(ItemOv1DTO itemDTO) {
    ItemEntity itemEntity = new ItemEntity();
    itemEntity.setItemId(itemDTO.getItemId() != null ? itemDTO.getItemId() : null);
    itemEntity.setItemName(itemDTO.getItemName() != null ? itemDTO.getItemName() : null);
    itemEntity.setPrice(itemDTO.getPrice() != null ? itemDTO.getPrice() : null);
    itemEntity.setDescription(itemDTO.getDescription() != null ? itemDTO.getDescription() : null);
    itemEntity.setCategory(
        itemDTO.getCategory().toString() != null ? itemDTO.getCategory().toString() : null);
    itemEntity.setCreatedAt(
        itemDTO.getCreatedAt() != null ? itemDTO.getCreatedAt().toInstant() : null);
    itemEntity.setUpdatedAt(
        itemDTO.getUpdatedAt() != null ? itemDTO.getUpdatedAt().toInstant() : null);
    return itemEntity;
  }
}
