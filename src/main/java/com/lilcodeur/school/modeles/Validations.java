package com.lilcodeur.school.modeles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Table(name = "validation_mail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant creation,expiration;
    private String code;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_eleves")
    private Eleves eleves;

}
