package de.frees.backendgameserver.mapper;

import com.example.itemapi.model.ItemOv1DTO;
import de.frees.backendgameserver.model.ItemEntity;
import com.example.itemapi.model.ItemOv1DTO.CategoryEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryEnum")
  ItemOv1DTO mapFromEntityToDto(ItemEntity itemEntity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "category", source = "category", qualifiedByName = "fromCategoryEnum")
  ItemEntity mapFromDtoToEntity(ItemOv1DTO itemOv1DTO);

  @Named("toCategoryEnum")
  default CategoryEnum toCategoryEnum(String category) {
    if (category == null) {
      return null;
    }
    return CategoryEnum.fromValue(category);
  }

  @Named("fromCategoryEnum")
  default String fromCategoryEnum(CategoryEnum category) {
    if (category == null) {
      return null;
    }
    return category.getValue();
  }
}
