package de.frees.backendgameserver.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemEntity {

    @MongoId private UUID itemId;

    private String itemName;

    private String description;

    private Double price;

    private String category;
}