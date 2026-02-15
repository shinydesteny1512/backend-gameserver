package de.frees.backendgameserver.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.frees.backendgameserver.Repository.ItemRepository;
import de.frees.backendgameserver.exception.ItemNotFoundException;
import de.frees.backendgameserver.mapper.ItemMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

  @Mock private ItemMapper itemMapper;

  @Mock private ItemRepository itemRepository;

  @InjectMocks private ItemService itemService;

  @Test
  void deleteById_notFound_throws() {
    UUID id = UUID.randomUUID();
    when(itemRepository.existsByItemId(id.toString())).thenReturn(false);

    assertThatThrownBy(() -> itemService.deleteById(id)).isInstanceOf(ItemNotFoundException.class);
  }

  @Test
  void deleteById_existing_deletes() {
    UUID id = UUID.randomUUID();
    when(itemRepository.existsByItemId(id.toString())).thenReturn(true);

    itemService.deleteById(id);

    verify(itemRepository).deleteByItemId(id.toString());
  }
}
