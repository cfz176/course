package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 章节
 */
@RestController
@RequestMapping("/business/admin/chapter")
public class ChapterController {

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
