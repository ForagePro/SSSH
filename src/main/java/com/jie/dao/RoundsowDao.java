package com.jie.dao;

import com.jie.domain.Roundsow;
import com.jie.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoundsowDao {

    //添加图片
    public void toAdd(Roundsow roundsow);

    //获取图片信息
    public List<Roundsow> toShow(int pageNo,int pageSize);

    //修改图片状态
    public String toUpdataStatus(int status,int id);

    //前台显示上架轮播图
    public List<Roundsow> toFind();

    //删除图片
    public void toDelete(int id);

    //查询
    public List<Roundsow>toQuery(int status);
}
