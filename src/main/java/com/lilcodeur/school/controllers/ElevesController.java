package com.lilcodeur.school.controllers;

import com.lilcodeur.school.Dto.Connexion;
import com.lilcodeur.school.modeles.Eleves;
import com.lilcodeur.school.securiter.JwtSerices;
import com.lilcodeur.school.services.ElevesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("eleves")
@AllArgsConstructor
public class ElevesController {
    private ElevesServices elevesServices;
    private AuthenticationManager authenticationManager;
    private JwtSerices jwtSerices;
    @PostMapping("inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEleves(@RequestBody Eleves eleves){
        elevesServices.addEleves(eleves);
    }
    @PostMapping("connexion")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> connexion(@RequestBody Connexion useConnexion){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(useConnexion.email(), useConnexion.mdp()));
        if(authenticate.isAuthenticated()){
            return jwtSerices.generate(useConnexion.email());
        }
        return null;
    }
}
