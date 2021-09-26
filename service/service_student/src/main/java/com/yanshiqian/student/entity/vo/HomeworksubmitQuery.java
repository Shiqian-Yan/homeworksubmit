package com.yanshiqian.student.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HomeworksubmitQuery {
    private String name;
    private String stuClass;
    private String stuId;
    private String courseId;
    private String times;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;


}
