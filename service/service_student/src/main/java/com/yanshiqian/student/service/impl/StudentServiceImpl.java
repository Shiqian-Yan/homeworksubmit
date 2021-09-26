package com.yanshiqian.student.service.impl;

import com.yanshiqian.student.entity.Student;
import com.yanshiqian.student.mapper.StudentMapper;
import com.yanshiqian.student.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-23
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
