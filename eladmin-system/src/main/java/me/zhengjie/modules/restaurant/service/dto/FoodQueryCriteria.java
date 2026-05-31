package me.zhengjie.modules.restaurant.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

@Data
public class FoodQueryCriteria {
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private String category;

    @Query
    private Integer status;
}
