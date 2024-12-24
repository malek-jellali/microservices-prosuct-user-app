package com.jellali.serviceutilisateur.Services;

import com.jellali.serviceutilisateur.Entities.Utilisateur;
import com.jellali.serviceutilisateur.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Créer un nouvel utilisateur
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Lire un utilisateur par son ID
    public Optional<Utilisateur> lireUtilisateur(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Lire tous les utilisateurs
    public List<Utilisateur> lireTousUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Mettre à jour un utilisateur
    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateurModifie) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurModifie.setId(id);
            return utilisateurRepository.save(utilisateurModifie);
        }
        return null;
    }

    // Supprimer un utilisateur
    public boolean supprimerUtilisateur(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
