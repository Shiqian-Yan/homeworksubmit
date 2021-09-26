package com.yanshiqian.teacher.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.teacher.entity.Homework;
import com.yanshiqian.teacher.entity.vo.HomeworkQuery;
import com.yanshiqian.teacher.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public R addBlog(@RequestBody Homework homework){
        homeworkService.addHomework(homework);
        return R.ok();
    }
    //更新博客
    @PreAuthorize("hasAuthority('homework.update')")
    @PostMapping("update/{id}")
    public R updateBlog(@RequestBody Homework homework,@PathVariable String id){

        homeworkService.updateHomework(homework,id);
        return R.ok();
    }
    //列表博客

    @PostMapping("list/{current}/{limit}")
    public R listBlog(@PathVariable long current,
                      @PathVariable long limit,
                      @RequestBody(required = false) HomeworkQuery homeworkQuery){

        Map<String,Object> map = homeworkService.listPage(current,limit,homeworkQuery);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('homework.delete')")
    @DeleteMapping("delete/{id}")
    public R deleteBlog(@PathVariable String id){
        homeworkService.deleteHomework(id);
        return R.ok();
    }

    @GetMapping("listHomeworkById/{id}")
    public R listBlogById(@PathVariable String id) {
        Homework homework = homeworkService.getById(id);
        return R.ok().data("homework", homework);
    }
}

