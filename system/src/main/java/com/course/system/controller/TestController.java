package com.course.system.controller;

import com.course.server.dto.ChapterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.course.server.entity.Test;
import com.course.server.service.TestService;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public List<Test> test() {
        ChapterDto chapterDto = new ChapterDto();
        chapterDto.setId("123");
        return testService.list();
    }

}
