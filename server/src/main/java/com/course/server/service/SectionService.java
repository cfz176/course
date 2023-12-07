package com.course.server.service;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.entity.Section;
import com.course.server.entity.SectionExample;
import com.course.server.mapper.SectionMapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    //查询大章列表
    public void list(SectionPageDto sectionPageDto) {
        //开启分页
        PageHelper.startPage(sectionPageDto.getPage(), sectionPageDto.getSize());
        //查询section列表
        SectionExample sectionExample = new SectionExample();
        //判断chapterId是否为空
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if (!StringUtils.isEmpty(sectionPageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        //获取分页数据
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        //添加总条数到pageDto
        sectionPageDto.setTotal(pageInfo.getTotal());
        //添加数据到pageDto
        List <SectionDto> sectionDtoList = BeanCopyUtils.copyBeanList(sectionList, SectionDto.class);
        sectionPageDto.setList(sectionDtoList);
     }

    /**
    * @param sectionDto
    */
    public void save(SectionDto sectionDto) {
        //dto转换实体
        Section section = BeanCopyUtils.copyBean(sectionDto, Section.class);
        //判断id字段是否为空
        if (ObjectUtils.isEmpty(sectionDto.getId())) {
        //为空 则新增
        insert(section);
        } else if (!ObjectUtils.isEmpty(sectionDto.getId())) {
        //不为空 修改
        update(section);
        }
    }

    /**
    * 新增章节
    *
    * @param section
    */
    private void insert(Section section) {
        //生成章节id
        section.setId(UuidUtil.getShortUuid());

        //新增章节
        sectionMapper.insert(section);
    }

    /**
    * 修改章节
    *
    * @param section
    */
    private void update(Section section) {
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
    * 删除章节
    * @param id
    */
    public ResponseDto delete(String id) {
        int result = sectionMapper.deleteByPrimaryKey(id);
        if (result == 1) {
        return ResponseDto.successResult();
        }
        return ResponseDto.errorResult();
    }
}
