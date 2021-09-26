package com.yanshiqian.teacher.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class HomeworkQuery {
    @ApiModelProperty(value = "分类ID")
    private String classId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "次数")
    private String times;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;


}
