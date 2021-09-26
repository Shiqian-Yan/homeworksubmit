package com.yanshiqian.blogclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.blogclass.entity.BlogChapter;
import com.yanshiqian.blogclass.entity.BlogClass;
import com.yanshiqian.blogclass.service.BlogClassService;
import com.yanshiqian.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-14
 */
@RestController
@RequestMapping("/blogclass/class")
public class BlogClassController {
    @Autowired
    private BlogClassService blogClassService;
    @PreAuthorize("hasAuthority('subject.list')")
    @GetMapping("getAllClass")
    public R getAllClass(){
        List<BlogClass> res = blogClassService.getAllChapterClass();
        return R.ok().data("children",res);
    }
    @PreAuthorize("hasAuthority('subject.list')")
    @GetMapping("getClass")
    public R getClassAll(){
        List<BlogClass> list = blogClassService.list(null);
        return R.ok().data("data",list);
    }
    @PreAuthorize("hasAuthority('subject.import')")
    @PostMapping("addClass")
    public R addClass(@RequestBody BlogClass blogClass){
        blogClass.setParentId("0");
        blogClassService.save(blogClass);
        String filename = blogClass.getTitle()+blogClass.getYear();
        File file = new File("D:/hometest/"+filename);
        if(!file.exists()){
            file.mkdir();
        }
        return R.ok();
    }
    @PreAuthorize("hasAuthority('subject.update')")
    @ApiOperation(value = "修改分类")
    @PostMapping("updateClass")
    public R updateById(@RequestBody BlogClass blogClass) {
        blogClassService.updateById(blogClass);
        String filename = blogClass.getTitle()+blogClass.getYear();
        File file = new File("D:/hometest/"+filename);
        if(!file.exists()){
            file.mkdir();
        }
        return R.ok();
    }
    @PreAuthorize("hasAuthority('subject.list')")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable String id){
        BlogClass service = blogClassService.getById(id);
        return R.ok().data("data",service);
    }
    @PreAuthorize("hasAuthority('subject.remove')")
    @DeleteMapping("{id}")
     public R deleteById(@PathVariable String id){
        blogClassService.deleteClassById(id);
         return R.ok();
    }
    @PreAuthorize("hasAuthority('subject.import')")
    @PostMapping("addsecClass/{parentId}")
    public R addSecClass(@PathVariable String parentId,@RequestBody BlogClass blogClass){
        blogClass.setParentId(parentId);
        blogClassService.save(blogClass);
        return R.ok();
    }
    @GetMapping("getCourseName/{id}")
    public String getCourseName(@PathVariable String id){
        BlogClass blogClass = blogClassService.getById(id);
        return blogClass.getTitle();
    }

}

