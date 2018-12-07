package com.jie.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shopcart {
    private int id;
    //private int cId;
    private int num;
    private int uId;
    private int status;
    private Commodity commodity;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "u_id")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @OneToOne
    @JoinColumn(name = "c_id")
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
