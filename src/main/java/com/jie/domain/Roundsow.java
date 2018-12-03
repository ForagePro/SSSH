package com.jie.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "roundsow")
public class Roundsow {
    private int id;
    private String imgPath;
    private String type;
    private int cId;
    private Timestamp createTime;
    private int sort;
    private int status;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img_path")
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Roundsow() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roundsow roundsow = (Roundsow) o;
        return id == roundsow.id &&
                cId == roundsow.cId &&
                sort == roundsow.sort &&
                status == roundsow.status &&
                Objects.equals(imgPath, roundsow.imgPath) &&
                Objects.equals(type, roundsow.type) &&
                Objects.equals(createTime, roundsow.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgPath, type, cId, createTime, sort, status);
    }
}
