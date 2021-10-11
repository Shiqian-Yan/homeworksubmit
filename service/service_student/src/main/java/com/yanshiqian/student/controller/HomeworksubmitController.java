package com.yanshiqian.student.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.feign.client.AclClient;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.student.entity.Homeworksubmit;

import com.yanshiqian.student.entity.vo.HomeworksubmitQuery;
import com.yanshiqian.student.service.HomeworksubmitService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
@RestController
@RequestMapping("/student/homeworksubmit")
public class HomeworksubmitController {
    @Autowired
    private HomeworksubmitService homeworksubmitService;
    @Autowired
    private AclClient client;
    @PreAuthorize("hasAuthority('homeworksub.add')")
    @PostMapping("upload/{courseId}/{times}/{year}")
    public R uploadWordFile(MultipartFile file,@PathVariable String courseId,@PathVariable String times,@PathVariable String year){
        Map<String, Object> userInfo = client.getUserInfo();
        Homeworksubmit homeworksubmit = homeworksubmitService.upload(file,userInfo,courseId,times,year);
        return R.ok().data("data",homeworksubmit);
    }
    @PreAuthorize("hasAuthority('homeworksub.list')")
    @PostMapping("list/{current}/{limit}")
    public R listBlog(@PathVariable long current,
                      @PathVariable long limit){
        Map<String, Object> userInfo = client.getUserInfo();
        Map<String,Object> map = homeworksubmitService.listPage(current,limit,userInfo);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('homeworksub.list')")
    @PostMapping("listAll/{current}/{limit}")
    public R listAllBlog(@PathVariable long current,
                         @PathVariable long limit, @RequestBody(required = false) HomeworksubmitQuery homeworksubmitQuery){

        Map<String,Object> map = homeworksubmitService.listPage(current,limit, homeworksubmitQuery);
        return R.ok().data(map);
    }
    @PreAuthorize("hasAuthority('homeworksub.delete')")
    @DeleteMapping("delete/{id}")
    public R deleteBlog(@PathVariable String id){
        homeworksubmitService.deleteSubmitHomework(id);
        return R.ok();
    }
    @GetMapping("/download/{filename}/{filename2}")
    public void downLoadWordFile(HttpServletResponse response,@PathVariable String filename,@PathVariable String filename2) throws IOException, InvalidFormatException {
          homeworksubmitService.downLoad(response,filename,filename2);
    }
    @GetMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        QueryWrapper<Homeworksubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        homeworksubmitService.remove(queryWrapper);
        return R.ok();
    }

}

