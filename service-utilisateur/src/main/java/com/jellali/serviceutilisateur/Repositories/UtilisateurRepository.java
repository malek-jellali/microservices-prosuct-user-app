package com.jellali.serviceutilisateur.Repositories;

import com.jellali.serviceutilisateur.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
    Utilisateur findByEmail(String email);
}
