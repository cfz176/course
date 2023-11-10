package com.course.${module}.controller.admin;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";
    @Autowired
    private ${Domain}Service ${domain}Service;

    /**
     * 查询章节列表
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto<PageDto> ${domain}(@RequestBody PageDto pageDto) {
        //查询章节列表
        ${domain}Service.list(pageDto);
        //设置返回值
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存章节
     *
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<${Domain}Dto> save${Domain}(@RequestBody ${Domain}Dto ${domain}Dto) {
        //判断课程名是否为空
        ValidatorUtil.require(${domain}Dto.getName(),"课程名");
        //判断课程号是否为空
        ValidatorUtil.require(${domain}Dto.getCourseId(),"课程编号");
        //判断课程号长度是否合法
        ValidatorUtil.length(${domain}Dto.getCourseId(),"课程编号",4,8);
        //新增章节
        ${domain}Service.save(${domain}Dto);
        //设置返回值
        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * 删除章节
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<${Domain}Dto> delete${Domain}(@PathVariable("id") String id) {
        return ${domain}Service.delete(id);
    }

}
