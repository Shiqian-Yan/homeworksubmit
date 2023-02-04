package com.yanshiqian.teacher.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.teacher.entity.Homework;
import com.yanshiqian.teacher.entity.vo.HomeworkQuery;
import com.yanshiqian.teacher.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
@RestController
@RequestMapping("/teacher/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @PreAuthorize("hasAuthority('homework.add')")
    @PostMapping("add")
    public R addHomework(@RequestBody Homework homework){
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("times",homework.getTimes());
        queryWrapper.eq("class_id",homework.getClassId());
        List<Homework> list = homeworkService.list(queryWrapper);
        System.out.println(list);
        if(list.size()==0) {
            homeworkService.addHomework(homework);
            return R.ok();
        }else{
            return R.error().message("重复发布作业了");
        }

    }
    //更新博客
    @PreAuthorize("hasAuthority('homework.update')")
    @PostMapping("update/{id}")
    public R updateHomework(@RequestBody Homework homework,@PathVariable String id){

        homeworkService.updateHomework(homework,id);
        return R.ok();
    }
    //列表博客

    @PostMapping("list/{current}/{limit}")
    public R listPage(@PathVariable long current,
                      @PathVariable long limit,
                      @RequestBody(required = false) HomeworkQuery homeworkQuery){

        Map<String,Object> map = homeworkService.listPage(current,limit,homeworkQuery);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('homework.delete')")
    @DeleteMapping("delete/{id}")
    public R deleteHomework(@PathVariable String id){
        homeworkService.deleteHomework(id);
        return R.ok();
    }

    @GetMapping("listHomeworkById/{id}")
    public R getById(@PathVariable String id) {
        Homework homework = homeworkService.getById(id);
        return R.ok().data("homework", homework);
    }
    @GetMapping("deleteCourse/{id}")
    public R deleteCourse1(@PathVariable String id){
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id",id);
        homeworkService.remove(queryWrapper);
        return R.ok();
    }
    @GetMapping("getContent/{id}")
    public R content(@PathVariable String id){
        Homework homework = homeworkService.getById(id);
        return R.ok().data("homework",homework);
    }
    @GetMapping("getEnd/{times}/{id}")
    public Date getEnd(@PathVariable String times,@PathVariable String id){
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("times",times);
        queryWrapper.eq("class_id",id);
        Homework homework = homeworkService.getOne(queryWrapper);
        return homework.getEnd();
    }
}

