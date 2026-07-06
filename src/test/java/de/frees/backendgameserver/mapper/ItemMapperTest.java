package de.frees.backendgameserver.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.itemapi.model.ItemOv1DTO;
import de.frees.backendgameserver.model.ItemEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ItemMapperTest {

  private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

  @Test
  void mapsCategoryEnumValue_toEnum_andBack() {
    ItemEntity entity = new ItemEntity();
    entity.setCategory("weapon");

    ItemOv1DTO dto = mapper.mapFromEntityToDto(entity);
    assertThat(dto.getCategory()).isEqualTo(ItemOv1DTO.CategoryEnum.WEAPON);

    ItemEntity mappedBack = mapper.mapFromDtoToEntity(dto);
    assertThat(mappedBack.getCategory()).isEqualTo("weapon");
  }
}
