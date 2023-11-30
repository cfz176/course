package com.course.server.service;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.Course;
import com.course.server.entity.CourseExample;
import com.course.server.mapper.CourseMapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    //查询大章列表
    public void list(PageDto pageDto) {
        //开启分页
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        //查询课程列表
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        //获取分页数据
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        //添加总条数到pageDto
        pageDto.setTotal(pageInfo.getTotal());
        // ArrayList <CourseDto> courseDtoList = new ArrayList<>();
        // for (int i = 0, l = courseList.size(); i < l; i++){
        // Course course = courseList.get(i);
        // CourseDto courseDto = new CourseDto();
        // BeanUtils.copyProperties(course, courseDto);
        // courseDtoList.add(courseDto);
        // }
        //添加数据到pageDto
        List <CourseDto> courseDtoList = BeanCopyUtils.copyBeanList(courseList, CourseDto.class);
        pageDto.setList(courseDtoList);
    }

    /**
    * @param courseDto
    */
    public void save(CourseDto courseDto) {
        //dto转换实体
        Course course = BeanCopyUtils.copyBean(courseDto, Course.class);
        //判断id字段是否为空
        if (ObjectUtils.isEmpty(courseDto.getId())) {
        //为空 则新增
        insert(course);
        } else if (!ObjectUtils.isEmpty(courseDto.getId())) {
        //不为空 修改
        update(course);
        }
    }

    /**
    * 新增章节
    *
    * @param course
    */
    private void insert(Course course) {
        //生成章节id
        course.setId(UuidUtil.getShortUuid());
        //新增章节
        courseMapper.insert(course);
    }

    /**
    * 修改章节
    *
    * @param course
    */
    private void update(Course course) {
        courseMapper.updateByPrimaryKey(course);
    }

    /**
    * 删除章节
    * @param id
    */
    public ResponseDto delete(String id) {
        int result = courseMapper.deleteByPrimaryKey(id);
        if (result == 1) {
        return ResponseDto.successResult();
        }
        return ResponseDto.errorResult();
    }
}
