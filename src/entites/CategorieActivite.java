/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author MSI PC
 */
public class CategorieActivite {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "CategorieActivite{" + "nom=" + nom + '}';
    }
    
}
