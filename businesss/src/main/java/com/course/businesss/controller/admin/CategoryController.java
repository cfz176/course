package com.course.businesss.controller.admin;

import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.Category;
import com.course.server.service.CategoryService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseDto category() {
        //设置DtoList
        ResponseDto<Object> responseDto = new ResponseDto<>();
        //查询分类列表
        categoryService.all(responseDto);
        //返回数据
        return responseDto;
    }

    /**
     * 保存分类 有ID为修改 无ID为新增
     *
     * @param categoryDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        //判断上级id是否为空
        ValidatorUtil.require(categoryDto.getParent(),"上级id");
        //判断名称是否为空
        ValidatorUtil.require(categoryDto.getName(),"名称");
        //判断名称长度是否合法
        ValidatorUtil.length(categoryDto.getName(),"名称",1,50);
        //新增分类
        categoryService.save(categoryDto);
        //设置返回值
        ResponseDto<CategoryDto> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<CategoryDto> deleteCategory(@PathVariable("id") String id) {
        return categoryService.delete(id);
    }

}
