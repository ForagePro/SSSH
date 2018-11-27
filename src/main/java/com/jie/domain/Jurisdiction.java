package com.jie.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jurisdiction {
    private int id;
    private int jRoundsow;
    private int jUser;
    private int jBreed;
    private int jOrder;
    private int jCommodity;
    private int jSystemmessage;
    private int jDispatch;
    private int jRole;
    private int roleId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "j_roundsow")
    public int getjRoundsow() {
        return jRoundsow;
    }

    public void setjRoundsow(int jRoundsow) {
        this.jRoundsow = jRoundsow;
    }

    @Basic
    @Column(name = "j_user")
    public int getjUser() {
        return jUser;
    }

    public void setjUser(int jUser) {
        this.jUser = jUser;
    }

    @Basic
    @Column(name = "j_breed")
    public int getjBreed() {
        return jBreed;
    }

    public void setjBreed(int jBreed) {
        this.jBreed = jBreed;
    }

    @Basic
    @Column(name = "j_order")
    public int getjOrder() {
        return jOrder;
    }

    public void setjOrder(int jOrder) {
        this.jOrder = jOrder;
    }

    @Basic
    @Column(name = "j_commodity")
    public int getjCommodity() {
        return jCommodity;
    }

    public void setjCommodity(int jCommodity) {
        this.jCommodity = jCommodity;
    }

    @Basic
    @Column(name = "j_systemmessage")
    public int getjSystemmessage() {
        return jSystemmessage;
    }

    public void setjSystemmessage(int jSystemmessage) {
        this.jSystemmessage = jSystemmessage;
    }

    @Basic
    @Column(name = "j_dispatch")
    public int getjDispatch() {
        return jDispatch;
    }

    public void setjDispatch(int jDispatch) {
        this.jDispatch = jDispatch;
    }

    @Basic
    @Column(name = "j_role")
    public int getjRole() {
        return jRole;
    }

    public void setjRole(int jRole) {
        this.jRole = jRole;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jurisdiction that = (Jurisdiction) o;

        if (id != that.id) return false;
        if (jRoundsow != that.jRoundsow) return false;
        if (jUser != that.jUser) return false;
        if (jBreed != that.jBreed) return false;
        if (jOrder != that.jOrder) return false;
        if (jCommodity != that.jCommodity) return false;
        if (jSystemmessage != that.jSystemmessage) return false;
        if (jDispatch != that.jDispatch) return false;
        if (jRole != that.jRole) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + jRoundsow;
        result = 31 * result + jUser;
        result = 31 * result + jBreed;
        result = 31 * result + jOrder;
        result = 31 * result + jCommodity;
        result = 31 * result + jSystemmessage;
        result = 31 * result + jDispatch;
        result = 31 * result + jRole;
        result = 31 * result + roleId;
        return result;
    }
}
