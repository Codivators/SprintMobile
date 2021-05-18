/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author MSI PC
 */
public class Activite {
    private int id,nbmax,dureeparminute;
    private float prixparpersonne;
    private String nom,description,pointfort,activitedeconseille,infoimporatant,pointderencontre,
            ville,gouvernerat,pays,image;
    private String datedebut, datefin;
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

    public float getPrixparpersonne() {
        return prixparpersonne;
    }

    public void setPrixparpersonne(float prixparpersonne) {
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

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
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

    @Override
    public String toString() {
        return "Activite{" + "nbmax=" + nbmax + ", prixparpersonne=" + prixparpersonne + ", nom=" + nom + ", description=" + description + ", pays=" + pays + '}';
    }
    
    
    
}
