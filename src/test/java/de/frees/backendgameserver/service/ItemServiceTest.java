package de.frees.backendgameserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.frees.backendgameserver.exception.objects.ItemNotFoundException;
import de.frees.backendgameserver.mapper.ItemMapper;
import de.frees.backendgameserver.repository.ItemRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

  @Mock private ItemMapper itemMapper;

  @Mock private ItemRepository itemRepository;

  @InjectMocks private ItemService itemService;

  @Test
  void deleteById_notFound_throws() {
    // given
    UUID id = UUID.randomUUID();

    // when
    when(itemRepository.existsByItemId(id.toString())).thenReturn(false);

    // then
    assertThatThrownBy(() -> itemService.deleteById(id)).isInstanceOf(ItemNotFoundException.class);
  }

  @Test
  void deleteById_existing_deletesAndReturnsDeletedId() {
    // given
    UUID id = UUID.randomUUID();
    when(itemRepository.existsByItemId(id.toString())).thenReturn(true);

    // when
    UUID deletedId = itemService.deleteById(id);

    // then
    assertThat(deletedId).isEqualTo(id);
    verify(itemRepository).deleteByItemId(id.toString());
  }
}
