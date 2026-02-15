package de.frees.backendgameserver.controller;

import com.example.itemapi.api.ItemApi;
import com.example.itemapi.api.ItemsApi;
import com.example.itemapi.model.ItemOv1DTO;
import com.example.itemapi.model.ItemPageOv1DTO;
import de.frees.backendgameserver.service.ItemService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ItemController implements ItemApi, ItemsApi {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @Override
  public ResponseEntity<String> createItem(ItemOv1DTO itemOv1DTO) {
    log.info("Starting Create Item");
    log.debug("Starting Create Item with following information : '{}'", itemOv1DTO);
    String id = itemService.createItem(itemOv1DTO);
    return ResponseEntity.ok().body(id);
  }

  @Override
  public ResponseEntity<Void> deleteItem(UUID itemId) {
    itemService.deleteById(itemId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<ItemOv1DTO> getItemById(UUID itemId) {
    return ResponseEntity.ok(itemService.findById(itemId));
  }

  @Override
  public ResponseEntity<ItemPageOv1DTO> getItems(
      Integer limit, Integer offset, String category, String search) {
    ItemPageOv1DTO itemPageOv1DTO = itemService.findAllItems(limit, offset);
    return ResponseEntity.ok(itemPageOv1DTO);
  }
}
