package com.course.server.service;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.Chapter;
import com.course.server.entity.ChapterExample;
import com.course.server.mapper.ChapterMapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
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
        List<ChapterDto> chapterDtoList = BeanCopyUtils.copyBeanList(chapterList, ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }

    /**
     * @param chapterDto
     */
    public void save(ChapterDto chapterDto) {
        //dto转换实体
        Chapter chapter = BeanCopyUtils.copyBean(chapterDto, Chapter.class);
        //判断id字段是否为空
        if (ObjectUtils.isEmpty(chapterDto.getId())) {
            //为空 则新增
            insert(chapter);
        } else if (!ObjectUtils.isEmpty(chapterDto.getId())) {
            //不为空 修改
            update(chapter);
        }
    }

    /**
     * 新增章节
     *
     * @param chapter
     */
    private void insert(Chapter chapter) {
        //生成章节id
        chapter.setId(UuidUtil.getShortUuid());
        //新增章节
        chapterMapper.insert(chapter);
    }

    /**
     * 修改章节
     *
     * @param chapter
     */
    private void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }

    /**
     * 删除章节
     * @param id
     */
    public ResponseDto delete(String id) {
        int result = chapterMapper.deleteByPrimaryKey(id);
        if (result == 1) {
            return ResponseDto.successResult();
        }
        return ResponseDto.errorResult();
    }
}
