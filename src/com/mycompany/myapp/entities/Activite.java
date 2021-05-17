/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.TextField;
import java.util.Date;


/**
 *
 * @author MSI PC
 */
public class Activite {
    private int id,nbmax,dureeparminute,categorie_id,user_id;
    private double prixparpersonne;
    private String nom,description,pointfort,activitedeconseille,infoimporatant,pointderencontre,
            ville,gouvernerat,pays,image;
    private Date datedebut, datefin;
    private boolean guidee, periode;

   


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNbmax() {
        return nbmax;
    }

    public void setNbmax(int nbmax) {
        this.nbmax = nbmax;
    }

    public int getDureeparminute() {
        return dureeparminute;
    }

    public void setDureeparminute(int dureeparminute) {
        this.dureeparminute = dureeparminute;
    }

    public double getPrixparpersonne() {
        return prixparpersonne;
    }

    public void setPrixparpersonne(double prixparpersonne) {
        this.prixparpersonne = prixparpersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPointfort() {
        return pointfort;
    }

    public void setPointfort(String pointfort) {
        this.pointfort = pointfort;
    }

    public String getActivitedeconseille() {
        return activitedeconseille;
    }

    public void setActivitedeconseille(String activitedeconseille) {
        this.activitedeconseille = activitedeconseille;
    }

    public String getInfoimporatant() {
        return infoimporatant;
    }

    public void setInfoimporatant(String infoimporatant) {
        this.infoimporatant = infoimporatant;
    }

    public String getPointderencontre() {
        return pointderencontre;
    }

    public void setPointderencontre(String pointderencontre) {
        this.pointderencontre = pointderencontre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGouvernerat() {
        return gouvernerat;
    }

    public void setGouvernerat(String gouvernerat) {
        this.gouvernerat = gouvernerat;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }


    public boolean isGuidee() {
        return guidee;
    }

    public void setGuidee(boolean guidee) {
        this.guidee = guidee;
    }

    public boolean isPeriode() {
        return periode;
    }

    public void setPeriode(boolean periode) {
        this.periode = periode;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Activite{" + "nbmax=" + nbmax + ", dureeparminute=" + dureeparminute + ", prixparpersonne=" + prixparpersonne + ", nom=" + nom + ", description=" + description + ", pointfort=" + pointfort + ", activitedeconseille=" + activitedeconseille + ", infoimporatant=" + infoimporatant + ", pointderencontre=" + pointderencontre + ", ville=" + ville + ", gouvernerat=" + gouvernerat + ", pays=" + pays + ", image=" + image + ", datedebut=" + datedebut + ", datefin=" + datefin + ", guidee=" + guidee + ", periode=" + periode + "}\n";
    }

   

    public Activite() {
    }

    
    

    

    }

   
   

   

  
    
    
    

