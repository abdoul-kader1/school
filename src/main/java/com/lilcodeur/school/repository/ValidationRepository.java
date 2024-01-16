package com.lilcodeur.school.repository;

import com.lilcodeur.school.modeles.Validations;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends CrudRepository<Validations,Integer> {
    Validations findByCode(String code);
}
