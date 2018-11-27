package com.jie.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Orderdetails {
    private int id;
    private String oCode;
    private int uId;
    private int rId;
    private Timestamp createTime;
    private String buymessage;
    private String invoicetitle;
    private BigDecimal sum;

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

    @Basic
    @Column(name = "u_id")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "r_id")
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderdetails that = (Orderdetails) o;

        if (id != that.id) return false;
        if (uId != that.uId) return false;
        if (rId != that.rId) return false;
        if (oCode != null ? !oCode.equals(that.oCode) : that.oCode != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (buymessage != null ? !buymessage.equals(that.buymessage) : that.buymessage != null) return false;
        if (invoicetitle != null ? !invoicetitle.equals(that.invoicetitle) : that.invoicetitle != null) return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (oCode != null ? oCode.hashCode() : 0);
        result = 31 * result + uId;
        result = 31 * result + rId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (buymessage != null ? buymessage.hashCode() : 0);
        result = 31 * result + (invoicetitle != null ? invoicetitle.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }
}
