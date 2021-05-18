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
public class Calendar {
    
    private int id;
    private String titre,couleurfond;
    private Date debut,fin;

    public int getId() {
        return id;
    }

   

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCouleurfond() {
        return couleurfond;
    }

    public void setCouleurfond(String couleurfond) {
        this.couleurfond = couleurfond;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Calendar{" + "titre=" + titre + ", debut=" + debut + ", fin=" + fin + '}';
    }
    
}
