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
public class Reservation {
    Activite act;
    CodeCadeauReservation codecadeau;
    User user;
    private String datedebut,datefin;
    private int id,nbpersonne;
    private double prix,total;
    private String typepaiement,status,codevalidationreservation,nom,prenom,addresse,
            ville,region,pays,codepostal,email,numtel;

    public Reservation() {
    }

    
    public Activite getAct() {
        return act;
    }

    public void setAct(Activite act) {
        this.act = act;
    }

    public CodeCadeauReservation getCodecadeau() {
        return codecadeau;
    }

    public void setCodecadeau(CodeCadeauReservation codecadeau) {
        this.codecadeau = codecadeau;
    }

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getId() {
        return id;
    }


    public int getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTypepaiement() {
        return typepaiement;
    }

    public void setTypepaiement(String typepaiement) {
        this.typepaiement = typepaiement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodevalidationreservation() {
        return codevalidationreservation;
    }

    public void setCodevalidationreservation(String codevalidationreservation) {
        this.codevalidationreservation = codevalidationreservation;
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Reservation{" + "act=" + act + ", codecadeau=" + codecadeau + ", user=" + user + ", datedebut=" + datedebut + ", datefin=" + datefin + ", id=" + id + ", nbpersonne=" + nbpersonne + ", prix=" + prix + ", total=" + total + ", typepaiement=" + typepaiement + ", status=" + status + ", codevalidationreservation=" + codevalidationreservation + ", nom=" + nom + ", prenom=" + prenom + ", addresse=" + addresse + ", ville=" + ville + ", region=" + region + ", pays=" + pays + ", codepostal=" + codepostal + ", email=" + email + ", numtel=" + numtel + '}';
    }

   
    
}
