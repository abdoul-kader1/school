package com.lilcodeur.school.services;

import com.lilcodeur.school.Dto.ConfirmeCodeValidation;
import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.modeles.Validations;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ConfirmeCodeValidationServices {
    private ElevesServices elevesServices;
    private ValidationsServices validationsServices;
    public void confirmeCodeValidation(ConfirmeCodeValidation confirmeCodeValidation){
        Validations validationsBdd = validationsServices.getValidation(confirmeCodeValidation.codeValidation());
        Instant verifieCodeExpiration = Instant.now();
        if(verifieCodeExpiration.isAfter(validationsBdd.getExpiration())){
            throw new EntityNotFoundException("Code de confirmation expiré");
        }else{
            Eleves eleves = validationsBdd.getEleves();
            eleves.setActif(true);
            elevesServices.ajouter(eleves);
        }
    }
}
