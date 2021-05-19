/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author 2020
 */
public class Guide {
     private int id;
    private String nom,prenom,domaine,email;
    

    public Guide(int id, String nom, String prenom, String domaine, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
        this.email = email;
    }

    public Guide(String nom, String prenom, String domaine, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
        this.email = email;
    }

    public Guide() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Guide{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", domaine=" + domaine + ", email=" + email + '}';
    }
}
