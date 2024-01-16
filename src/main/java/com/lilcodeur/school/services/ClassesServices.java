package com.lilcodeur.school.services;

import com.lilcodeur.school.modeles.Classes;
import com.lilcodeur.school.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClassesServices {
    private ClasseRepository classeRepository;
    public void addClasses(Classes classe){
        classeRepository.save(classe);
    }
}
