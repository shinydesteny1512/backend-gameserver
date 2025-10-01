package de.frees.backendgameserver.controller;

import com.example.itemapi.api.ItemsApi;
import com.example.itemapi.model.GetItems200ResponseDTO;
import com.example.itemapi.model.ItemCreateRequestDTO;
import com.example.itemapi.model.ItemDTO;
import de.frees.backendgameserver.service.ItemService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ItemController implements ItemsApi {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @Override
  public ResponseEntity<UUID> createItem(ItemCreateRequestDTO itemCreateRequestDTO) {
    log.info("Starting Create Item");
    log.debug("Starting Create Item with following information : '{}'", itemCreateRequestDTO);
    UUID id = itemService.createItem(itemCreateRequestDTO);
    return ResponseEntity.ok().body(id);
  }

  @Override
  public ResponseEntity<Void> deleteItem(UUID itemId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }

  @Override
  public ResponseEntity<ItemDTO> getItemById(UUID itemId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }

  @Override
  public ResponseEntity<GetItems200ResponseDTO> getItems(
      Optional<@Min(1) @Max(100) Integer> limit,
      Optional<@Min(0) Integer> offset,
      Optional<String> category,
      Optional<String> search) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
