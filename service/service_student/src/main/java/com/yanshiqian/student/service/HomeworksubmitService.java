package com.yanshiqian.student.service;

import com.yanshiqian.student.entity.Homeworksubmit;
import com.baomidou.mybatisplus.extension.service.IService;

import com.yanshiqian.student.entity.vo.HomeworksubmitQuery;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
public interface HomeworksubmitService extends IService<Homeworksubmit> {

    Homeworksubmit upload(MultipartFile file, Map<String, Object> id,  String courseId, String times,String year);

    void downLoad(HttpServletResponse response, String filename,String filename2) throws IOException, InvalidFormatException;

    Map<String, Object> listPage(long current, long limit, Map<String, Object> userInfo);

    void deleteSubmitHomework(String id);

    Map<String, Object> listPage(long current, long limit, HomeworksubmitQuery homeworksubmitQuery);
}
