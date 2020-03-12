package com.fgy.myblog.bean;

import java.io.Serializable;

/**
 * 栏目类
 */
public class CategoryInfo implements Serializable {
    private Integer categoryId;

    private String categoryName;

    private String categoryAlias;

    private String categoryDesc;

    private Integer totalNumber=0;//文章总数

    public Integer getTotalNumber() {

        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias == null ? null : categoryAlias.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAlias='" + categoryAlias + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                ", totalNumber=" + totalNumber +
                '}';
    }
}