package com.jellali.serviceutilisateur.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private LocalDateTime dateCreation;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", email=" + email + "]";
    }

    // Getters and Setters
}
