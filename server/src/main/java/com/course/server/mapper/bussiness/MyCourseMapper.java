package com.course.server.mapper.bussiness;

import org.apache.ibatis.annotations.Param;

public interface MyCourseMapper {

    /**
     * 根据小节修改视频时间
     * @param id
     * @return
     */
    long updateTime(@Param("id") String id);

}
