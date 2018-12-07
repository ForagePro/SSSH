package com.jie.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Shoplist {
    private int id;
    //private int cId;
    private int num;
    private String oCode;
    private double price;
    //private int dId;
    private int odId;
    private Commodity commodity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "c_id")
//    public int getcId() {
//        return cId;
//    }
//
//    public void setcId(int cId) {
//        this.cId = cId;
//    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "o_code")
    public String getoCode() {
        return oCode;
    }

    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    @Basic
//    @Column(name = "d_id")
//    public int getdId() {
//        return dId;
//    }
//
//    public void setdId(int dId) {
//        this.dId = dId;
//    }

//    @ManyToOne
//    @JoinColumn(name = "od_id")
//    @Basic(fetch = FetchType.LAZY)
//    public Orderdetails getOrderdetails() {
//        return orderdetails;
//    }
//
//    public void setOrderdetails(Orderdetails orderdetails) {
//        this.orderdetails = orderdetails;
//    }


    @Basic
    @Column(name = "od_id")
    public int getOdId() {
        return odId;
    }

    public void setOdId(int odId) {
        this.odId = odId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoplist shoplist = (Shoplist) o;

        if (id != shoplist.id) return false;
        //if (cId != shoplist.cId) return false;
        if (num != shoplist.num) return false;
        //if (dId != shoplist.dId) return false;
        if (oCode != null ? !oCode.equals(shoplist.oCode) : shoplist.oCode != null) return false;
        //if (price != null ? !price.equals(shoplist.price) : shoplist.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + cId;
        result = 31 * result + num;
        result = 31 * result + (oCode != null ? oCode.hashCode() : 0);
       // result = 31 * result + (price != null ? price.hashCode() : 0);
        //result = 31 * result + dId;
        return result;
    }
}
