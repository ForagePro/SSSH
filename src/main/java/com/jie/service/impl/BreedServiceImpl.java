package com.jie.service.impl;

import com.jie.dao.BreedDao;
import com.jie.domain.Breed;
import com.jie.service.BreedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {
    @Resource
    private BreedDao breedDao;

    @Override
    public boolean testBreedName(String name) {
        return breedDao.testBreedName(name);
    }

    @Override
    public void addBreed(Breed breed) {
        breedDao.addBreed(breed);
    }

    @Override
    public List<Breed> getBreedName() {
        return breedDao.getBreedName();
    }

    @Override
    public List<Breed> select(Breed breed, int num, int page, String keyword) {
        return breedDao.select(breed,num,page,keyword);
    }

    @Override
    public void updateStatus(int id, int status) {
        breedDao.updateStatus(id,status);
    }

    @Override
    public Breed getBreedById(int id) {
        return breedDao.getBreedById(id);
    }

    @Override
    public void editBreed(Breed breed) {
        breedDao.editBreed(breed);
    }

    @Override
    public void delete(int id) {
        breedDao.delete(id);
    }

    public void setBreedDao(BreedDao breedDao) {
        this.breedDao = breedDao;
    }
}
