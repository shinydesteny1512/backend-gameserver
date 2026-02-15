package de.frees.backendgameserver.exception;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException(UUID itemId) {
    super("Item not found: " + itemId);
  }
}
