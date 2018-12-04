package com.jie.service;

import com.jie.domain.Roundsow;

import java.util.List;

public interface RoundsowService {

    public void toAdd(Roundsow roundsow);

    //获取图片信息
    public List<Roundsow> toShow(int pageNo, int pageSize);

    //修改图片状态
    public String toUpdataStatus(int status,int id);

    //删除图片
    public void toDelete(int id);

    public List<Roundsow> toFind();

    public List<Roundsow> toQuery(int status);
}
