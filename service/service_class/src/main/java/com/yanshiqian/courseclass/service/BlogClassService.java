package com.yanshiqian.courseclass.service;

import com.yanshiqian.courseclass.entity.BlogClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-14
 */
public interface BlogClassService extends IService<BlogClass> {

    List<BlogClass> getAllChapterClass();

    List<BlogClass> getClassById(String id);

    void deleteClassById(String id);
}
