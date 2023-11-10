package com.course.server.service;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
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

import java.util.List;

@Service
public class SectionService {

@Autowired
private SectionMapper sectionMapper;

//查询大章列表
public void list(PageDto pageDto) {
//开启分页
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
//查询section列表
SectionExample SectionExample = new SectionExample();
List<Section> sectionList = sectionMapper.selectByExample(SectionExample);
    //获取分页数据
    PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        //添加总条数到pageDto
        pageDto.setTotal(pageInfo.getTotal());
        //        ArrayList<SectionDto> sectionDtoList = new ArrayList<>();
            //        for (int i = 0, l = sectionList.size(); i < l; i++){
            //            Section section = sectionList.get(i);
            //            SectionDto sectionDto = new SectionDto();
            //            BeanUtils.copyProperties(section, sectionDto);
            //            sectionDtoList.add(sectionDto);
            //        }
            //添加数据到pageDto
            List<SectionDto> sectionDtoList = BeanCopyUtils.copyBeanList(sectionList, SectionDto.class);
                pageDto.setList(sectionDtoList);
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
