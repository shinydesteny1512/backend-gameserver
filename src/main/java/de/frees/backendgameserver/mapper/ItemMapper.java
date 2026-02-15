package de.frees.backendgameserver.mapper;

import com.example.itemapi.model.ItemOv1DTO;
import de.frees.backendgameserver.model.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  ItemOv1DTO mapFromEntityToDto(ItemEntity itemEntity);

  @Mapping(target = "id", ignore = true)
  ItemEntity mapFromDtoToEntity(ItemOv1DTO itemOv1DTO);
}
