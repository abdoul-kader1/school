package com.lilcodeur.school.services;

import com.lilcodeur.school.modeles.Validations;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EnvoieMailServices {
     JavaMailSender javaMailSender;
    public void envoieMail(Validations validations){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@SerifouKader.com");
        message.setTo(validations.getEleves().getEmail());
        message.setSubject("Code de validation");
        String texte = String.format("Bonjour %s, votre code de validation est : %s, il expireras dans 3 minutes Rdv sur l'app school", validations.getEleves().getNom(),validations.getCode());
        message.setText(texte);
        javaMailSender.send(message);
    }
}
