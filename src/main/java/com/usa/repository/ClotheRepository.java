package com.usa.repository;

import com.usa.interfaces.ClotheInterface;
import com.usa.model.Clothe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClotheRepository {

    @Autowired
    private ClotheInterface crudInterface;

    public List<Clothe> getAll() {
        return crudInterface.findAll();
    }

    public Optional<Clothe> getClothe(String reference) {
        return crudInterface.findById(reference);
    }

    public Clothe create(Clothe clothe) {
        return crudInterface.save(clothe);
    }

    public void update(Clothe clothe) {
        crudInterface.save(clothe);
    }

    public void delete(Clothe clothe) {
        crudInterface.delete(clothe);
    }
}
