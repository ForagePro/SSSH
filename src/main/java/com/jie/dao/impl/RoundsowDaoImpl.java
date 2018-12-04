package com.jie.dao.impl;

import com.jie.dao.RoundsowDao;
import com.jie.domain.Roundsow;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class RoundsowDaoImpl implements RoundsowDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void toAdd(Roundsow roundsow) {
        roundsow.setStatus(1);
        hibernateTemplate.save(roundsow);
    }

    @Override
    public List<Roundsow> toShow(int pageNo, int pageSize) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Roundsow>list=session.createQuery("select r from Roundsow r order by sort desc").setFirstResult((pageNo-1)*pageSize).setMaxResults(pageNo*pageSize).list();
        return list;
    }

    @Override
    public String toUpdataStatus(int status, int id) {

//        String hql="select count(*) from Roundsow as r group by r.status having r.status=0";
//        Integer num=(Integer) hibernateTemplate.find(hql).listIterator().next();
//        System.out.println(num.intValue());
        String sql="select count(id) from roundsow group by status having status=0";
        Integer num=jdbcTemplate.queryForObject(sql,Integer.class);
        if (num==null){
            num=0;
        }
        System.out.println(num);
        String msg="";
        if (num>=5&&status==0) {
            msg="上架图片不得超过5张，如要继续，请先下架！";
        }else if (num<=1&&status!=0){
            msg="不能继续下架！";
        } else {
            Roundsow roundsow=hibernateTemplate.load(Roundsow.class,id);
            roundsow.setStatus(status);
            hibernateTemplate.update(roundsow);
            msg="修改成功！";
        }
        return msg;
    }

    @Override
    public List<Roundsow> toFind() {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Roundsow>list=session.createQuery("select r from Roundsow r where status=0 order by sort desc").list();
//        String sql="select * from roundsow where status=0 order by sort desc";
//        List<Roundsow> list=jdbcTemplate.queryForList(sql,Roundsow.class);
//        List<Roundsow>list=hibernateTemplate.loadAll(Roundsow.class);
        return list;
    }

    @Override
    public void toDelete(int id) {
        Roundsow roundsow=hibernateTemplate.load(Roundsow.class,id);
        hibernateTemplate.delete(roundsow);
    }

    @Override
    public List<Roundsow> toQuery(int status) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Roundsow>list=session.createQuery("select r from Roundsow r where status=? order by sort desc").setInteger(0,status).list();
        return list;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
