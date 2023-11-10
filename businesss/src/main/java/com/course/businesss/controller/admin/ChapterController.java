package com.course.businesss.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";
    @Autowired
    private ChapterService chapterService;

    /**
     * 查询章节列表
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto<PageDto> chapter(@RequestBody PageDto pageDto) {
        //查询章节列表
        chapterService.list(pageDto);
        //设置返回值
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存章节
     *
     * @param chapterDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto<ChapterDto> saveChapter(@RequestBody ChapterDto chapterDto) {
        //判断课程名是否为空
        ValidatorUtil.require(chapterDto.getName(),"课程名");
        //判断课程号是否为空
        ValidatorUtil.require(chapterDto.getCourseId(),"课程编号");
        //判断课程号长度是否合法
        ValidatorUtil.length(chapterDto.getCourseId(),"课程编号",4,8);
        //新增章节
        chapterService.save(chapterDto);
        //设置返回值
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    /**
     * 删除章节
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto<ChapterDto> deleteChapter(@PathVariable("id") String id) {
        return chapterService.delete(id);
    }

}
