package com.yanshiqian.teacher.service;

import com.yanshiqian.teacher.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanshiqian.teacher.entity.vo.HomeworkQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
public interface HomeworkService extends IService<Homework> {

    void addHomework(Homework homework);

    void updateHomework(Homework homework, String id);

    void deleteHomework(String id);

    Map<String, Object> listPage(long current, long limit, HomeworkQuery blogQuery);
}
