package com.course.server.dto;


public class CategoryDto{

     /**
     * id
     */
     private String id;
     /**
     * 上级id
     */
     private String parent;
     /**
     * 名称
     */
     private String name;
     /**
     * 排序
     */
     private Integer sort;

    public void setId(String id){
        this.id = id;
    }
    public void setParent(String parent){
        this.parent = parent;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }

    public String getId(){
        return this.id;
    }
    public String getParent(){
        return this.parent;
    }
    public String getName(){
        return this.name;
    }
    public Integer getSort(){
        return this.sort;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append(",id = ").append(id);
        sb.append(",parent = ").append(parent);
        sb.append(",name = ").append(name);
        sb.append(",sort = ").append(sort);
        sb.append("]");
        return sb.toString();
    }

}