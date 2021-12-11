package com.usa.service;

import com.usa.model.Clothe;
import com.usa.repository.ClotheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClotheService {

    @Autowired
    private ClotheRepository clotheRepository;

    public List<Clothe> getAll() {
        return clotheRepository.getAll();
    }

    public Optional<Clothe> getClothe(String reference) {
        return clotheRepository.getClothe(reference);
    }

    public Clothe create(Clothe accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return clotheRepository.create(accesory);
        }
    }

    public Clothe update(Clothe clothe){
        if (clothe.getReference()!= null){
            Optional<Clothe> clotheDb = clotheRepository.getClothe(clothe.getReference());
            if (!clotheDb.isEmpty()){
                if (clothe.getReference()!=null){
                    clotheDb.get().setReference(clothe.getReference());
                }
                if (clothe.getCategory()!=null){
                    clotheDb.get().setCategory(clothe.getCategory());
                }
                if (clothe.getSize()!=null){
                    clotheDb.get().setSize(clothe.getSize());
                }
                if (clothe.getDescription()!=null){
                    clotheDb.get().setDescription(clothe.getDescription());
                }
                if (clothe.getPrice()!= 0.0){
                    clotheDb.get().setPrice(clothe.getPrice());
                }
                if (clothe.getQuantity()!=0){
                    clotheDb.get().setQuantity(clothe.getQuantity());
                }
                if (clothe.getPhotography()!=null){
                    clotheDb.get().setPhotography(clothe.getPhotography());
                }
                clotheDb.get().setAvailability(clothe.isAvailability());
                clotheRepository.update(clotheDb.get());
                return clotheDb.get();
            }else{
                return clothe;
            }
        }else{
            return clothe;
        }
    }

    public boolean delete(String reference){
        boolean del = getClothe(reference).map(clothe -> {
            clotheRepository.delete(clothe);
            return true;
        }).orElse(false);
        return del;
    }
}
