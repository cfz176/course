package com.course.server.service;

import com.course.server.dto.ChapterDto;
import com.course.server.entity.Chapter;
import com.course.server.entity.ChapterExample;
import com.course.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterMapper ChapterMapper;

    public List<ChapterDto> list() {
        ChapterExample ChapterExample = new ChapterExample();
        List<Chapter> chapterList = ChapterMapper.selectByExample(ChapterExample);
        ArrayList<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0, l = chapterList.size(); i < l; i++){
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
