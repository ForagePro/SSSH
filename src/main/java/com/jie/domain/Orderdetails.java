package com.jie.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orderdetails {
    private int id;
    private String oCode;
    //private int uId;
    //private int rId;
    private Timestamp createTime;
    private String buymessage;
    private String invoicetitle;
    private BigDecimal sum;
    private Receiveaddress receiveaddress; //收货地址
    private Ordertime ordertime;           //物流单
    private User user;                     //用户
    private Set<Shoplist> set=new HashSet<>();
    private double dId;

    @Basic
    @Column(name = "d_id")
    public double getdId() {
        return dId;
    }

    public void setdId(double dId) {
        this.dId = dId;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "od_id")
    public Set<Shoplist> getSet() {
        return set;
    }

    public void setSet(Set<Shoplist> set) {
        this.set = set;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "od_id")
    public Ordertime getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Ordertime ordertime) {
        this.ordertime = ordertime;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "r_id")
    public Receiveaddress getReceiveaddress() {
        return receiveaddress;
    }

    public void setReceiveaddress(Receiveaddress receiveaddress) {
        this.receiveaddress = receiveaddress;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "o_code")
    public String getoCode() {
        return oCode;
    }

    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    /*@Basic
    @Column(name = "u_id")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }*/

    /*@Basic
    @Column(name = "r_id")
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }*/

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "buymessage")
    public String getBuymessage() {
        return buymessage;
    }

    public void setBuymessage(String buymessage) {
        this.buymessage = buymessage;
    }

    @Basic
    @Column(name = "invoicetitle")
    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    @Basic
    @Column(name = "sum")
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }


}
