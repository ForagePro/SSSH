package com.jie.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Ordertime {
    private int id;
    private String code;
    private String oCode;
    private int status;
    private String name;
    private Timestamp subTime;
    private Timestamp payTime;
    private Timestamp sendTime;
    private Timestamp recTime;
    private Timestamp retTime;
    private Timestamp sretTime;
    private Timestamp closeTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sub_time")
    public Timestamp getSubTime() {
        return subTime;
    }

    public void setSubTime(Timestamp subTime) {
        this.subTime = subTime;
    }

    @Basic
    @Column(name = "pay_time")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "send_time")
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "rec_time")
    public Timestamp getRecTime() {
        return recTime;
    }

    public void setRecTime(Timestamp recTime) {
        this.recTime = recTime;
    }

    @Basic
    @Column(name = "ret_time")
    public Timestamp getRetTime() {
        return retTime;
    }

    public void setRetTime(Timestamp retTime) {
        this.retTime = retTime;
    }

    @Basic
    @Column(name = "sret_time")
    public Timestamp getSretTime() {
        return sretTime;
    }

    public void setSretTime(Timestamp sretTime) {
        this.sretTime = sretTime;
    }

    @Basic
    @Column(name = "close_time")
    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordertime ordertime = (Ordertime) o;

        if (id != ordertime.id) return false;
        if (status != ordertime.status) return false;
        if (code != null ? !code.equals(ordertime.code) : ordertime.code != null) return false;
        if (oCode != null ? !oCode.equals(ordertime.oCode) : ordertime.oCode != null) return false;
        if (name != null ? !name.equals(ordertime.name) : ordertime.name != null) return false;
        if (subTime != null ? !subTime.equals(ordertime.subTime) : ordertime.subTime != null) return false;
        if (payTime != null ? !payTime.equals(ordertime.payTime) : ordertime.payTime != null) return false;
        if (sendTime != null ? !sendTime.equals(ordertime.sendTime) : ordertime.sendTime != null) return false;
        if (recTime != null ? !recTime.equals(ordertime.recTime) : ordertime.recTime != null) return false;
        if (retTime != null ? !retTime.equals(ordertime.retTime) : ordertime.retTime != null) return false;
        if (sretTime != null ? !sretTime.equals(ordertime.sretTime) : ordertime.sretTime != null) return false;
        if (closeTime != null ? !closeTime.equals(ordertime.closeTime) : ordertime.closeTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (oCode != null ? oCode.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subTime != null ? subTime.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (recTime != null ? recTime.hashCode() : 0);
        result = 31 * result + (retTime != null ? retTime.hashCode() : 0);
        result = 31 * result + (sretTime != null ? sretTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        return result;
    }
}
