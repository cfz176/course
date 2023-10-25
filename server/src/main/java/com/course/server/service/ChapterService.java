package com.course.server.service;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.entity.Chapter;
import com.course.server.entity.ChapterExample;
import com.course.server.mapper.ChapterMapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    //查询大章列表
    public void list(PageDto pageDto) {
        //开启分页
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        //查询chapter列表
        ChapterExample ChapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(ChapterExample);
        //获取分页数据
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        //添加总条数到pageDto
        pageDto.setTotal(pageInfo.getTotal());
//        ArrayList<ChapterDto> chapterDtoList = new ArrayList<>();
//        for (int i = 0, l = chapterList.size(); i < l; i++){
//            Chapter chapter = chapterList.get(i);
//            ChapterDto chapterDto = new ChapterDto();
//            BeanUtils.copyProperties(chapter, chapterDto);
//            chapterDtoList.add(chapterDto);
//        }
        //添加数据到pageDto
        List<ChapterDto> chapterDtoList  = BeanCopyUtils.copyBeanList(chapterList, ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }

    /**
     * 新增章节
     * @param chapterDto
     */
    public void save(ChapterDto chapterDto) {
        //生成章节id
        chapterDto.setId(UuidUtil.getShortUuid());
        //dto转换实体
        Chapter chapter = BeanCopyUtils.copyBean(chapterDto, Chapter.class);
        //新增章节
        chapterMapper.insert(chapter);
    }
}
