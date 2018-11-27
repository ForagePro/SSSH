package com.jie.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Dispatch {
    private int id;
    private String way;
    private String destination;
    private BigDecimal cost;
    private String content;
    private String logname;
    private int logcode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "way")
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "cost")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "logname")
    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    @Basic
    @Column(name = "logcode")
    public int getLogcode() {
        return logcode;
    }

    public void setLogcode(int logcode) {
        this.logcode = logcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispatch dispatch = (Dispatch) o;

        if (id != dispatch.id) return false;
        if (logcode != dispatch.logcode) return false;
        if (way != null ? !way.equals(dispatch.way) : dispatch.way != null) return false;
        if (destination != null ? !destination.equals(dispatch.destination) : dispatch.destination != null)
            return false;
        if (cost != null ? !cost.equals(dispatch.cost) : dispatch.cost != null) return false;
        if (content != null ? !content.equals(dispatch.content) : dispatch.content != null) return false;
        if (logname != null ? !logname.equals(dispatch.logname) : dispatch.logname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (way != null ? way.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (logname != null ? logname.hashCode() : 0);
        result = 31 * result + logcode;
        return result;
    }
}
