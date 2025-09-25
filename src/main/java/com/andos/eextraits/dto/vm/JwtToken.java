package com.andos.eextraits.dto.vm;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-09-25
 */
@Getter
@Setter
public class JwtToken {

    private UUID id;
    private String nomUtilisateur;
    private String nomComplet;
    private String token;
    private boolean expirer;
    private boolean revoquer;
    private Long userId;

    public JwtToken() {
        this(null, null, null);
    }

    public JwtToken(String token, String nomUtilisateur, String nomComplet) {
        this.id = UUID.randomUUID();
        this.token = token;
        this.nomUtilisateur = nomUtilisateur;
        this.nomComplet = nomComplet;
        this.expirer = false;
        this.revoquer = false;
    }
}
