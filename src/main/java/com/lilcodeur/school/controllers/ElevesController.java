package com.lilcodeur.school.controllers;

import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.services.ElevesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eleves")
@AllArgsConstructor
public class ElevesController {
    private ElevesServices elevesServices;
    @PostMapping("inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEleves(@RequestBody Eleves eleves){
        elevesServices.addEleves(eleves);
    }
}
