package de.frees.backendgameserver.model;

import java.time.OffsetDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity {

  @Id private String id;

  private String itemId;

  private String itemName;

  private String description;

  private String category;

  private Double price;

  @CreatedDate private OffsetDateTime createdAt;

  @LastModifiedDate private OffsetDateTime updatedAt;
}
