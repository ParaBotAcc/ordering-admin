package me.zhengjie.modules.restaurant.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class FoodDto implements Serializable {
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String spec;
    private String description;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
