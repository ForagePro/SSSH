package com.jie.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Commodity {
    private int id;
    private String name;
    //private int bId;
    private double price;
    private int status;
    private Timestamp createTime;
    private String imgPath;
    private double downprice;
    private double minsaleweight;
    private double stockbalance;
    private String details;
    private int sort;
    private String unit;
    private Breed breed;



    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "b_id")
    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

//    @Basic
//    @Column(name = "b_id")
//    public int getbId() {
//        return bId;
//    }
//
//    public void setbId(int bId) {
//        this.bId = bId;
//    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "downprice")
    public double getDownprice() {
        return downprice;
    }

    public void setDownprice(double downprice) {
        this.downprice = downprice;
    }

    @Basic
    @Column(name = "minsaleweight")
    public double getMinsaleweight() {
        return minsaleweight;
    }

    public void setMinsaleweight(double minsaleweight) {
        this.minsaleweight = minsaleweight;
    }

    @Basic
    @Column(name = "stockbalance")
    public double getStockbalance() {
        return stockbalance;
    }

    public void setStockbalance(double stockbalance) {
        this.stockbalance = stockbalance;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Commodity() {
    }




    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        if (id != commodity.id) return false;
        //if (bId != commodity.bId) return false;
        if (status != commodity.status) return false;
        if (Double.compare(commodity.minsaleweight, minsaleweight) != 0) return false;
        if (Double.compare(commodity.stockbalance, stockbalance) != 0) return false;
        if (sort != commodity.sort) return false;
        if (name != null ? !name.equals(commodity.name) : commodity.name != null) return false;
        //if (price != null ? !price.equals(commodity.price) : commodity.price != null) return false;
        if (createTime != null ? !createTime.equals(commodity.createTime) : commodity.createTime != null) return false;
        if (imgPath != null ? !imgPath.equals(commodity.imgPath) : commodity.imgPath != null) return false;
        //if (downprice != null ? !downprice.equals(commodity.downprice) : commodity.downprice != null) return false;
        if (details != null ? !details.equals(commodity.details) : commodity.details != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        //result = 31 * result + bId;
        //result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        //result = 31 * result + (downprice != null ? downprice.hashCode() : 0);
        temp = Double.doubleToLongBits(minsaleweight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stockbalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + sort;
        return result;
    }
}
