package com.yanshiqian.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanshiqian.teacher.entity.Homework;
import com.yanshiqian.teacher.entity.vo.HomeworkQuery;
import com.yanshiqian.teacher.mapper.HomeworkMapper;
import com.yanshiqian.teacher.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {

    @Override
    public void addHomework(Homework homework) {
         baseMapper.insert(homework);
    }

    @Override
    public void updateHomework(Homework homework, String id) {
        homework.setId(id);
        baseMapper.updateById(homework);
    }

    @Override
    public void deleteHomework(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> listPage(long current, long limit, HomeworkQuery homeworkQuery) {
        Page<Homework> page = new Page<>(current,limit);
        QueryWrapper<Homework> wrapper = new QueryWrapper<>();
        System.out.println(current);
        System.out.println(limit);
        String title = homeworkQuery.getTitle();
        String times = homeworkQuery.getTimes();
        String classId = homeworkQuery.getClassId();
        String content = homeworkQuery.getContent();
        String begin = homeworkQuery.getBegin();
        String end = homeworkQuery.getEnd();
        if(!StringUtils.isEmpty(classId)){
            wrapper.eq("class_id",classId);
        }
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(times)){
            wrapper.eq("times",times);
        }
        if(!StringUtils.isEmpty(content)){
            wrapper.eq("content",content);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);//大于等于
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);//小于等于
        }
        baseMapper.selectPage(page,wrapper);
        long total = page.getTotal();//总记录数
        System.out.println(total);
        List<Homework> records = page.getRecords();//集合

        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("rows",records);
        return res;
    }
}
