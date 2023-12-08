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
     * 查询${tableNameCn}列表
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto<PageDto> ${domain}(@RequestBody PageDto pageDto) {
        //查询${tableNameCn}列表
        ${domain}Service.list(pageDto);
        //设置返回值
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存${tableNameCn} 有ID为修改 无ID为新增
     *
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<${Domain}Dto> save${Domain}(@RequestBody ${Domain}Dto ${domain}Dto) {
        <#list fieldList as field>
        <#if field.nameHump != "id" && field.nameHump != "createAt" && field.nameHump != "updateAt">
            <#if !field.nullAble>
        //判断${field.nameCn}是否为空
        ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        //判断${field.nameCn}长度是否合法
        ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}",1,${field.length?c});
            </#if>
        </#if>
        </#list>
        //新增${tableNameCn}
        ${domain}Service.save(${domain}Dto);
        //设置返回值
        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * 删除${tableNameCn}
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<${Domain}Dto> delete${Domain}(@PathVariable("id") String id) {
        return ${domain}Service.delete(id);
    }

}
