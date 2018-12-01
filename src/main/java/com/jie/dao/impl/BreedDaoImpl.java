package com.jie.dao.impl;

import com.jie.dao.BreedDao;
import com.jie.domain.Breed;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BreedDaoImpl implements BreedDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    //验证品类
    @Override
    public boolean testBreedName(String name) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Breed> list=session.createQuery("from Breed where name=?").setString(0,name).list();
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
    //添加品类
    @Override
    public void addBreed(Breed breed) {
        hibernateTemplate.save(breed);
    }
    //获取品类名称
    @Override
    public List<Breed> getBreedName() {
        return hibernateTemplate.loadAll(Breed.class);
    }
    //多条件查询
    @Override
    public List<Breed> select(Breed breed, int num, int page, String keyword) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List list=new ArrayList();
        StringBuilder hql=new StringBuilder("from Breed where 1=1 ");
        if(!breed.getName().equals("品类名称")){
            list.add(breed.getName());
            hql=hql.append("and name=? ");
        }
        if(breed.getStatus()!=-1){
            list.add(breed.getStatus());
            hql=hql.append("and status=? ");
        }
        if(keyword.length()>0){
            hql=hql.append("and content like '%"+keyword+"%'");
        }
        System.out.println(hql);
        if(list.size()>0){
            Query query=session.createQuery(hql.toString()).setFirstResult((page-1)*num).setMaxResults(num);
            for(int i=0;i<list.size();i++){
                query.setParameter(i,list.get(i));
            }
            return query.list();
        }
        return session.createQuery(hql.toString()).list();
    }

    @Override
    public void updateStatus(int id, int status) {
        Breed breed=hibernateTemplate.get(Breed.class,id);
        breed.setStatus(status);
        hibernateTemplate.update(breed);
    }

    @Override
    public Breed getBreedById(int id) {
        Breed breed=hibernateTemplate.get(Breed.class,id);
        return breed;
    }

    @Override
    public void editBreed(Breed breed) {
        Breed breed1=hibernateTemplate.get(Breed.class,breed.getId());
        breed1.setSort(breed.getSort());
        breed1.setName(breed.getName());
        breed1.setContent(breed.getContent());
        hibernateTemplate.update(breed1);
    }

    @Override
    public void delete(int id) {
        Breed breed=hibernateTemplate.get(Breed.class,id);
        hibernateTemplate.delete(breed);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
