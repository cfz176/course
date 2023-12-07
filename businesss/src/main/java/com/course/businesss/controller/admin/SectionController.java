package com.course.businesss.controller.admin;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.service.SectionService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";
    @Autowired
    private SectionService sectionService;

    /**
     * 查询章节列表
     *
     * @param sectionPageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto<PageDto> section(@RequestBody SectionPageDto sectionPageDto) {
        //chapterId不能为空
        ValidatorUtil.require(sectionPageDto.getChapterId(),"章节名");
        //查询章节列表
        sectionService.list(sectionPageDto);
        //设置返回值
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(sectionPageDto);
        return responseDto;
    }

    /**
     * 保存章节 有ID为修改 无ID为新增
     *
     * @param sectionDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<SectionDto> saveSection(@RequestBody SectionDto sectionDto) {
        //判断标题是否为空
        ValidatorUtil.require(sectionDto.getTitle(),"标题");
        //判断标题长度是否合法
        ValidatorUtil.length(sectionDto.getTitle(),"标题",1,50);
        //判断视频长度是否合法
        ValidatorUtil.length(sectionDto.getVideo(),"视频",1,200);
        //新增章节
        sectionService.save(sectionDto);
        //设置返回值
        ResponseDto<SectionDto> responseDto = new ResponseDto<>();
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /**
     * 删除章节
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<SectionDto> deleteSection(@PathVariable("id") String id) {
        return sectionService.delete(id);
    }

}
