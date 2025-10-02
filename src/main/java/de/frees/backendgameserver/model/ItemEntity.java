package de.frees.backendgameserver.model;

import java.time.Instant;
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

  @Id private String itemId;

  private String itemName;

  private String description;

  private Double price;

  private String category;

  @CreatedDate private Instant createdAt;

  @LastModifiedDate private Instant updatedAt;
}
