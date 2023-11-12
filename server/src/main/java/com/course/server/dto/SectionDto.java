package com.course.server.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SectionDto{

     /**
     * ID
     */
     private String id;
     /**
     * 标题
     */
     private String title;
     /**
     * 课程|course_id
     */
     private String courseId;
     /**
     * 章节|course_id
     */
     private String chapterId;
     /**
     * 视频
     */
     private String video;
     /**
     * 时长|单位秒
     */
     private Integer time;
     /**
     * 收费|C 收费；F 免费
     */
     private String charge;
     /**
     * 顺序
     */
     private Integer sort;
     /**
     * 创建时间
     */
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
     private Date createdAt;
     /**
     * 修改时间
     */
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
     private Date updatedAt;

    public void setId(String id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setCourseId(String courseId){
        this.courseId = courseId;
    }
    public void setChapterId(String chapterId){
        this.chapterId = chapterId;
    }
    public void setVideo(String video){
        this.video = video;
    }
    public void setTime(Integer time){
        this.time = time;
    }
    public void setCharge(String charge){
        this.charge = charge;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getCourseId(){
        return this.courseId;
    }
    public String getChapterId(){
        return this.chapterId;
    }
    public String getVideo(){
        return this.video;
    }
    public Integer getTime(){
        return this.time;
    }
    public String getCharge(){
        return this.charge;
    }
    public Integer getSort(){
        return this.sort;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append(",id = ").append(id);
        sb.append(",title = ").append(title);
        sb.append(",courseId = ").append(courseId);
        sb.append(",chapterId = ").append(chapterId);
        sb.append(",video = ").append(video);
        sb.append(",time = ").append(time);
        sb.append(",charge = ").append(charge);
        sb.append(",sort = ").append(sort);
        sb.append(",createdAt = ").append(createdAt);
        sb.append(",updatedAt = ").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

}