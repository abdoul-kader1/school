package com.lilcodeur.school.controllers;

import com.lilcodeur.school.modeles.Classes;
import com.lilcodeur.school.services.ClassesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classe")
@AllArgsConstructor
public class ClassesControllers {
    private ClassesServices classesServices;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("add")
    public void addClasse(@RequestBody Classes classe){
        classesServices.addClasses(classe);
    }
}
