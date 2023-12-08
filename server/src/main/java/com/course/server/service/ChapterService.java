package com.course.server.service;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
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
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询章节列表
     * @param chapterPageDto
     */
    public void list(ChapterPageDto chapterPageDto) {
        //开启分页
        PageHelper.startPage(chapterPageDto.getPage(), chapterPageDto.getSize());
        //查询章节列表
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        //判断是否携带courseId
        if (!StringUtils.isEmpty(chapterPageDto.getCourseId())) {
            //使用courseId作为条件查询
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        //获取分页数据
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        //添加总条数到pageDto
        chapterPageDto.setTotal(pageInfo.getTotal());
        //添加数据到pageDto
        List<ChapterDto> chapterDtoList = BeanCopyUtils.copyBeanList(chapterList, ChapterDto.class);
        chapterPageDto.setList(chapterDtoList);
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
