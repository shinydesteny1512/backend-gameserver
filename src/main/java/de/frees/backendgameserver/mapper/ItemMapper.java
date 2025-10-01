package de.frees.backendgameserver.mapper;

import com.example.itemapi.model.ItemCreateRequestDTO;
import com.example.itemapi.model.ItemDTO;
import de.frees.backendgameserver.model.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

  ItemDTO itemEntityToItemDTO(ItemEntity item);

  ItemEntity itemDTOToItem(ItemDTO itemDTO);

  ItemDTO itemToItemCreateRequestDTO(ItemCreateRequestDTO itemCreateRequestDTO);
}
