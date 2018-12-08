package com.jie.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Evaluate {
    private int id;
    //private int uId;
    private String context;
//    private int cId;
    private Timestamp createTime;
    private User user;
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
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //    @Basic
//    @Column(name = "u_id")
//    public int getuId() {
//        return uId;
//    }
//
//    public void setuId(int uId) {
//        this.uId = uId;
//    }

    @ManyToOne
    @JoinColumn(name = "c_id")
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }




    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "context")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evaluate evaluate = (Evaluate) o;

        if (id != evaluate.id) return false;
        //if (uId != evaluate.uId) return false;
        //if (cId != evaluate.cId) return false;
        if (context != null ? !context.equals(evaluate.context) : evaluate.context != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + uId;
        result = 31 * result + (context != null ? context.hashCode() : 0);
        //result = 31 * result + cId;
        return result;
    }
}
