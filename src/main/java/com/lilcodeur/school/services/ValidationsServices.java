package com.lilcodeur.school.services;

import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.modeles.Validations;
import com.lilcodeur.school.repository.ValidationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Random;
import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@AllArgsConstructor
public class ValidationsServices {
    private ValidationRepository validationRepository;
    private EnvoieMailServices envoieMailServices;
    public void addValidation(Eleves eleves){
        Validations validations = new Validations();
        Instant creation = Instant.now();
        Instant expiration = creation.plus(3,MINUTES);
        validations.setEleves(eleves);
        Random random = new Random();
        int randonInt = random.nextInt(999999);
        String code = String.format("%06d",randonInt);
        validations.setCode(code);
        validations.setCreation(creation);
        validations.setExpiration(expiration);
        validationRepository.save(validations);
        envoieMailServices.envoieMail(validations);
    }
    public Validations getValidation(String code){
        Validations validationsBdd = validationRepository.findByCode(code);
        if(validationsBdd==null){
            throw new EntityNotFoundException("code de validation incorrecte");
        }else{
            return validationsBdd;
        }
    }
}
