package me.zhengjie.modules.restaurant.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.restaurant.domain.Food;
import me.zhengjie.modules.restaurant.service.dto.FoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper extends BaseMapper<FoodDto, Food> {
}
