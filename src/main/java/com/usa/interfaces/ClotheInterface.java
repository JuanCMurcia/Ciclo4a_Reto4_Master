package com.usa.interfaces;

import com.usa.model.Clothe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClotheInterface extends MongoRepository<Clothe, String> {
}
