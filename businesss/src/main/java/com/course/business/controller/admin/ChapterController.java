package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.entity.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public PageDto chapter(@RequestBody PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }

    /**
     * 新增章节
     * @param chapterDto
     * @return
     */
    @RequestMapping("/save")
    public ChapterDto saveChapter(@RequestBody ChapterDto chapterDto) {
        chapterService.save(chapterDto);
        return chapterDto;
    }

}
