package me.zhengjie.modules.restaurant.service;

import me.zhengjie.modules.restaurant.domain.Food;
import me.zhengjie.modules.restaurant.service.dto.FoodDto;
import me.zhengjie.modules.restaurant.service.dto.FoodQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface FoodService {
    PageResult<FoodDto> queryAll(FoodQueryCriteria criteria, Pageable pageable);
    List<FoodDto> queryAll(FoodQueryCriteria criteria);
    FoodDto findById(Long id);
    FoodDto create(Food resources);
    FoodDto update(Food resources);
    void delete(Set<Long> ids);
    void download(List<FoodDto> all, HttpServletResponse response) throws IOException;
}
