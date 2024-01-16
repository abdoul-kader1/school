package com.lilcodeur.school.controllers;

import com.lilcodeur.school.Dto.ConfirmeCodeValidation;
import com.lilcodeur.school.services.ConfirmeCodeValidationServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("code_validation")
@AllArgsConstructor
public class ConfirmationCodeControllers {
    private ConfirmeCodeValidationServices confirmeCodeValidationServices;
    @PostMapping("confirme")
    @ResponseStatus(HttpStatus.OK)
    public void confirmation(@RequestBody ConfirmeCodeValidation confirmeCodeValidation){
        confirmeCodeValidationServices.confirmeCodeValidation(confirmeCodeValidation);
    }
}
