package com.course.server.service;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.${Domain};
import com.course.server.entity.${Domain}Example;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ${Domain}Service {

@Autowired
private ${Domain}Mapper ${domain}Mapper;

//查询大章列表
public void list(PageDto pageDto) {
//开启分页
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
//查询${domain}列表
${Domain}Example ${Domain}Example = new ${Domain}Example();
List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${Domain}Example);
    //获取分页数据
    PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        //添加总条数到pageDto
        pageDto.setTotal(pageInfo.getTotal());
        //        ArrayList<${Domain}Dto> ${domain}DtoList = new ArrayList<>();
            //        for (int i = 0, l = ${domain}List.size(); i < l; i++){
            //            ${Domain} ${domain} = ${domain}List.get(i);
            //            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            //            BeanUtils.copyProperties(${domain}, ${domain}Dto);
            //            ${domain}DtoList.add(${domain}Dto);
            //        }
            //添加数据到pageDto
            List<${Domain}Dto> ${domain}DtoList = BeanCopyUtils.copyBeanList(${domain}List, ${Domain}Dto.class);
                pageDto.setList(${domain}DtoList);
                }

                /**
                * @param ${domain}Dto
                */
                public void save(${Domain}Dto ${domain}Dto) {
                //dto转换实体
                ${Domain} ${domain} = BeanCopyUtils.copyBean(${domain}Dto, ${Domain}.class);
                //判断id字段是否为空
                if (ObjectUtils.isEmpty(${domain}Dto.getId())) {
                //为空 则新增
                insert(${domain});
                } else if (!ObjectUtils.isEmpty(${domain}Dto.getId())) {
                //不为空 修改
                update(${domain});
                }
                }

                /**
                * 新增章节
                *
                * @param ${domain}
                */
                private void insert(${Domain} ${domain}) {
                //生成章节id
                ${domain}.setId(UuidUtil.getShortUuid());
                //新增章节
                ${domain}Mapper.insert(${domain});
                }

                /**
                * 修改章节
                *
                * @param ${domain}
                */
                private void update(${Domain} ${domain}) {
                ${domain}Mapper.updateByPrimaryKey(${domain});
                }

                /**
                * 删除章节
                * @param id
                */
                public ResponseDto delete(String id) {
                int result = ${domain}Mapper.deleteByPrimaryKey(id);
                if (result == 1) {
                return ResponseDto.successResult();
                }
                return ResponseDto.errorResult();
                }
                }
