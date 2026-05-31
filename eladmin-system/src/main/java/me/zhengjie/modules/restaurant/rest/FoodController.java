package me.zhengjie.modules.restaurant.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.restaurant.domain.Food;
import me.zhengjie.modules.restaurant.service.FoodService;
import me.zhengjie.modules.restaurant.service.dto.FoodDto;
import me.zhengjie.modules.restaurant.service.dto.FoodQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Api(tags = "点餐：菜品管理")
@RequestMapping("/api/restaurant/food")
public class FoodController {

    private final FoodService foodService;

    @ApiOperation("查询菜品")
    @GetMapping
    @PreAuthorize("@el.check('food:list')")
    public ResponseEntity<PageResult<FoodDto>> query(FoodQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(foodService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("查询全部")
    @GetMapping("/all")
    public ResponseEntity<List<FoodDto>> queryAll(FoodQueryCriteria criteria) {
        return new ResponseEntity<>(foodService.queryAll(criteria), HttpStatus.OK);
    }

    @ApiOperation("新增菜品")
    @PostMapping
    @PreAuthorize("@el.check('food:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Food resources) {
        return new ResponseEntity<>(foodService.create(resources), HttpStatus.CREATED);
    }

    @ApiOperation("修改菜品")
    @PutMapping
    @PreAuthorize("@el.check('food:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Food resources) {
        return new ResponseEntity<>(foodService.update(resources), HttpStatus.OK);
    }

    @ApiOperation("删除菜品")
    @DeleteMapping
    @PreAuthorize("@el.check('food:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        foodService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("导出菜品")
    @GetMapping("/download")
    @PreAuthorize("@el.check('food:list')")
    public void download(FoodQueryCriteria criteria, HttpServletResponse response) throws IOException {
        foodService.download(foodService.queryAll(criteria), response);
    }
}
