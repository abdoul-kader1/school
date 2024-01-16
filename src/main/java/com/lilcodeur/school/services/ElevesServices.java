package com.lilcodeur.school.services;

import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.repository.ElevesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ElevesServices {
    private ElevesRepository elevesRepository;
    private BCryptPasswordEncoder mdpEncode;
    private ValidationsServices validationsServices;
    public void addEleves(Eleves eleves){
        Eleves elevesBdd = elevesRepository.findByEmail(eleves.getEmail());
        if(elevesBdd==null){
            String crypteMdp = mdpEncode.encode(eleves.getMdp());
            eleves.setMdp(crypteMdp);
            elevesRepository.save(eleves);
            validationsServices.addValidation(eleves);
        }else{
            throw new EntityNotFoundException("l'email existe deja");
        }
    }
    public Eleves getEleves(int id){
       Optional<Eleves> eleves = elevesRepository.findById(id);
       return eleves.orElseThrow(()->new EntityNotFoundException("Id eleves incorrecte"));
    }
    public void ajouter(Eleves eleves){
        elevesRepository.save(eleves);
    }
}
