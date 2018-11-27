package com.jie.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Roundsow {
    private int id;
    private int imgId;
    private String type;
    private int cId;
    private Timestamp createTime;
    private int sort;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img_id")
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "c_id")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roundsow roundsow = (Roundsow) o;

        if (id != roundsow.id) return false;
        if (imgId != roundsow.imgId) return false;
        if (cId != roundsow.cId) return false;
        if (sort != roundsow.sort) return false;
        if (type != null ? !type.equals(roundsow.type) : roundsow.type != null) return false;
        if (createTime != null ? !createTime.equals(roundsow.createTime) : roundsow.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + imgId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + cId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + sort;
        return result;
    }
}
