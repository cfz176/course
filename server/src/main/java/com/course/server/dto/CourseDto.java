package com.course.server.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CourseDto{

     /**
     * id
     */
     private String id;
     /**
     * 名称
     */
     private String name;
     /**
     * 概述
     */
     private String summary;
     /**
     * 时长|单位秒
     */
     private Integer time;
     /**
     * 价格
     */
     private BigDecimal price;
     /**
     * 封面
     */
     private String image;
     /**
     * 级别|枚举[CourseLevelEnum]ONE("1","初级"）,TOW("2","中级"）,THREE("","高级"）
     */
     private String level;
     /**
     * 收费|枚举[CourseChargeEnum]CHARGE("C","收费"),FREE("F","免费")
     */
     private String charge;
     /**
     * 状态|枚举[CourseStatusEnum]PUBLISH("P","发布"),DRAF("D","草稿")
     */
     private String status;
     /**
     * 报名数
     */
     private Integer enroll;
     /**
     * 顺序
     */
     private Integer sort;
     /**
     * 创建时间
     */
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
     private Date createAt;
     /**
     * 修改时间
     */
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
     private Date updateAt;

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSummary(String summary){
        this.summary = summary;
    }
    public void setTime(Integer time){
        this.time = time;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public void setImage(String image){
        this.image = image;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public void setCharge(String charge){
        this.charge = charge;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setEnroll(Integer enroll){
        this.enroll = enroll;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }
    public void setCreateAt(Date createAt){
        this.createAt = createAt;
    }
    public void setUpdateAt(Date updateAt){
        this.updateAt = updateAt;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSummary(){
        return this.summary;
    }
    public Integer getTime(){
        return this.time;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public String getImage(){
        return this.image;
    }
    public String getLevel(){
        return this.level;
    }
    public String getCharge(){
        return this.charge;
    }
    public String getStatus(){
        return this.status;
    }
    public Integer getEnroll(){
        return this.enroll;
    }
    public Integer getSort(){
        return this.sort;
    }
    public Date getCreateAt(){
        return this.createAt;
    }
    public Date getUpdateAt(){
        return this.updateAt;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append(",id = ").append(id);
        sb.append(",name = ").append(name);
        sb.append(",summary = ").append(summary);
        sb.append(",time = ").append(time);
        sb.append(",price = ").append(price);
        sb.append(",image = ").append(image);
        sb.append(",level = ").append(level);
        sb.append(",charge = ").append(charge);
        sb.append(",status = ").append(status);
        sb.append(",enroll = ").append(enroll);
        sb.append(",sort = ").append(sort);
        sb.append(",createAt = ").append(createAt);
        sb.append(",updateAt = ").append(updateAt);
        sb.append("]");
        return sb.toString();
    }

}