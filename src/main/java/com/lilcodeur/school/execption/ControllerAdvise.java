package com.lilcodeur.school.execption;

import com.lilcodeur.school.Dto.AfficheErreur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvise {
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AfficheErreur afficheErreur(EntityNotFoundException message){
        return new AfficheErreur(message.getMessage());
    }
}
