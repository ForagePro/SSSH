package com.jie.dao;

import com.jie.domain.Breed;

import java.util.List;

public interface BreedDao {
    //验证品类名
    public boolean testBreedName(String name);
    //添加品类
    public void addBreed(Breed breed);
    //获取品类名称
    public List<Breed> getBreedName();
    //多条件查询
    public List<Breed> select(Breed breed,int num,int page,String keyword);
    //修改状态
    public void updateStatus(int id,int status);
    //根据id获取breed
    public Breed getBreedById(int id);
    //修改breed
    public void editBreed(Breed breed);
    //删除
    public void delete(int id);
}
