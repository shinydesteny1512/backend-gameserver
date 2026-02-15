package de.frees.backendgameserver.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.frees.backendgameserver.error.GlobalExceptionHandler;
import de.frees.backendgameserver.exception.ItemNotFoundException;
import de.frees.backendgameserver.service.ItemService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ItemController.class)
@Import(GlobalExceptionHandler.class)
class ItemControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ItemService itemService;

  @Test
  void getItemById_returnsNotFound() throws Exception {
    UUID id = UUID.randomUUID();
    when(itemService.findById(id)).thenThrow(new ItemNotFoundException(id));

    mockMvc
        .perform(get("/item/{itemId}", id))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value("NOT_FOUND"))
        .andExpect(jsonPath("$.message").value("Item not found: " + id));
  }

  @Test
  void deleteItem_returnsNotFound() throws Exception {
    UUID id = UUID.randomUUID();
    doThrow(new ItemNotFoundException(id)).when(itemService).deleteById(id);

    mockMvc
        .perform(delete("/item/{itemId}", id))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value("NOT_FOUND"))
        .andExpect(jsonPath("$.message").value("Item not found: " + id));
  }

  @Test
  void getItemById_invalidUuid_returnsBadRequest() throws Exception {
    mockMvc
        .perform(get("/item/{itemId}", "not-a-uuid"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
  }

  @Test
  void deleteItem_returnsNoContent() throws Exception {
    UUID id = UUID.randomUUID();
    doNothing().when(itemService).deleteById(id);

    mockMvc.perform(delete("/item/{itemId}", id)).andExpect(status().isNoContent());
  }
}
