package com.course.businesss.controller.admin;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";
    @Autowired
    private CourseService courseService;

    /**
     * 查询章节列表
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto<PageDto> course(@RequestBody PageDto pageDto) {
        //查询章节列表
        courseService.list(pageDto);
        //设置返回值
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存章节 有ID为修改 无ID为新增
     *
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        //判断名称是否为空
        ValidatorUtil.require(courseDto.getName(),"名称");
        //判断名称长度是否合法
        ValidatorUtil.length(courseDto.getName(),"名称",1,50);
        //判断概述长度是否合法
        ValidatorUtil.length(courseDto.getSummary(),"概述",1,2000);
        //判断级别长度是否合法
        ValidatorUtil.length(courseDto.getLevel(),"级别",1,100);
        //新增章节
        courseService.save(courseDto);
        //设置返回值
        ResponseDto<CourseDto> responseDto = new ResponseDto<>();
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * 删除章节
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<CourseDto> deleteCourse(@PathVariable("id") String id) {
        return courseService.delete(id);
    }

}
