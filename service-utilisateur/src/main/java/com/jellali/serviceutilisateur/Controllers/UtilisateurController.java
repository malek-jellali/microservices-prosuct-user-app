package com.jellali.serviceutilisateur.Controllers;

import com.jellali.serviceutilisateur.Entities.Utilisateur;
import com.jellali.serviceutilisateur.Services.UtilisateurService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private Environment environment;

    // Créer un utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurCree = utilisateurService.creerUtilisateur(utilisateur);
        return new ResponseEntity<>(utilisateurCree, HttpStatus.CREATED);
    }

    // Lire un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> lireUtilisateur(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.lireUtilisateur(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Retry(name = "userRetry", fallbackMethod = "fallbackForLireTousUtilisateurs")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "fallbackForLireTousUtilisateurs")
    @CircuitBreaker(name = "userMicroservice", fallbackMethod = "fallbackForLireTousUtilisateurs")
    public ResponseEntity<List<Utilisateur>> lireTousUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.lireTousUtilisateurs();

        // Get the port from environment
        String port;
        port = environment.getProperty("local.server.port");

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Served-Port", port);

        return ResponseEntity.ok()
                .headers(headers)
                .body(utilisateurs);
    }


    // Fallback method for lireTousUtilisateurs
    public ResponseEntity<List<Utilisateur>> fallbackForLireTousUtilisateurs(Throwable throwable) {
        // Log the exception (optional)
        System.err.println("Erreur lors de l'appel de lireTousUtilisateurs: " + throwable.getMessage());
        // Return a generic response or empty list
        return ResponseEntity.ok(Collections.emptyList());
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurModifie = utilisateurService.mettreAJourUtilisateur(id, utilisateur);
        return utilisateurModifie != null ? ResponseEntity.ok(utilisateurModifie) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        return utilisateurService.supprimerUtilisateur(id) ? ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}