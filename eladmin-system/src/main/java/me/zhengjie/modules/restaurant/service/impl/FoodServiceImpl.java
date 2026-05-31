package me.zhengjie.modules.restaurant.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.restaurant.domain.Food;
import me.zhengjie.modules.restaurant.repository.FoodRepository;
import me.zhengjie.modules.restaurant.service.FoodService;
import me.zhengjie.modules.restaurant.service.dto.FoodDto;
import me.zhengjie.modules.restaurant.service.dto.FoodQueryCriteria;
import me.zhengjie.modules.restaurant.service.mapstruct.FoodMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    @Override
    public PageResult<FoodDto> queryAll(FoodQueryCriteria criteria, Pageable pageable) {
        Page<Food> page = foodRepository.findAll((root, query, cb) ->
            QueryHelp.getPredicate(root, criteria, cb), pageable);
        return PageUtil.toPage(page.map(foodMapper::toDto));
    }

    @Override
    public List<FoodDto> queryAll(FoodQueryCriteria criteria) {
        return foodMapper.toDto(foodRepository.findAll((root, query, cb) ->
            QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public FoodDto findById(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() ->
            new BadRequestException("菜品不存在"));
        return foodMapper.toDto(food);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoodDto create(Food resources) {
        return foodMapper.toDto(foodRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoodDto update(Food resources) {
        Food food = foodRepository.findById(resources.getId()).orElseThrow(() ->
            new BadRequestException("菜品不存在"));
        BeanUtils.copyProperties(resources, food, "id", "createdAt");
        return foodMapper.toDto(foodRepository.save(food));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        foodRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void download(List<FoodDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FoodDto dto : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("菜品名称", dto.getName());
            map.put("分类", dto.getCategory());
            map.put("价格", dto.getPrice() / 100.0);
            map.put("库存", dto.getStock() == -1 ? "无限" : dto.getStock());
            map.put("状态", dto.getStatus() == 1 ? "上架" : "下架");
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
