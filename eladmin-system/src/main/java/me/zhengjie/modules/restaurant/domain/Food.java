package me.zhengjie.modules.restaurant.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "menu")
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @ApiModelProperty("菜品名称") private String name;
    @NotBlank @ApiModelProperty("分类") private String category;
    @ApiModelProperty("图片URL") private String imageUrl;
    @NotNull @ApiModelProperty("价格(分)") private Integer price;
    @ApiModelProperty("库存(-1=无限)") private Integer stock = -1;
    @ApiModelProperty("规格") private String spec;
    @ApiModelProperty("描述") private String description;
    @NotNull @ApiModelProperty("状态(1上架0下架)") private Integer status = 1;
    @ApiModelProperty(value = "创建时间", hidden = true) private LocalDateTime createdAt;
    @ApiModelProperty(value = "更新时间", hidden = true) private LocalDateTime updatedAt;
}
