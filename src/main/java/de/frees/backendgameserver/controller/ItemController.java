package de.frees.backendgameserver.controller;

import com.example.itemapi.api.ItemsApi;
import com.example.itemapi.model.ItemOv1DTO;
import com.example.itemapi.model.ItemPageOv1DTO;
import de.frees.backendgameserver.service.ItemService;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ItemController implements ItemsApi {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @Override
  @NonNull
  public ResponseEntity<UUID> createItem(ItemOv1DTO itemOv1DTO) {
    log.info("Starting Create Item");
    log.debug("Starting Create Item with following information : '{}'", itemOv1DTO);
    UUID id = itemService.createItem(itemOv1DTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(Objects.requireNonNull(id));
  }

  @Override
  @NonNull
  public ResponseEntity<UUID> deleteItem(UUID itemId) {
    UUID deletedItemId = itemService.deleteById(itemId);
    return ResponseEntity.ok(Objects.requireNonNull(deletedItemId));
  }

  @Override
  @NonNull
  public ResponseEntity<ItemOv1DTO> getItemById(UUID itemId) {
    return ResponseEntity.ok(Objects.requireNonNull(itemService.findById(itemId)));
  }

  @Override
  @NonNull
  public ResponseEntity<ItemPageOv1DTO> getItems(Integer limit, Integer offset) {
    ItemPageOv1DTO itemPageOv1DTO =
        itemService.findAllItems(limit == null ? 25 : limit, offset == null ? 0 : offset);
    return ResponseEntity.ok(Objects.requireNonNull(itemPageOv1DTO));
  }

  @Override
  @NonNull
  public ResponseEntity<ItemOv1DTO> updateItem(UUID itemId, ItemOv1DTO itemOv1DTO) {
    return ResponseEntity.ok(Objects.requireNonNull(itemService.updateItem(itemId, itemOv1DTO)));
  }
}
