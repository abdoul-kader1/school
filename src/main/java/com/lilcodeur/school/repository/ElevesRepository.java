package com.lilcodeur.school.repository;

import com.lilcodeur.school.modeles.Eleves;
import org.springframework.data.repository.CrudRepository;

public interface ElevesRepository extends CrudRepository<Eleves,Integer> {
    Eleves findByEmail(String email);
}
