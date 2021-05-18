/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import javafx.scene.control.Button;

/**
 *
 * @author MSI PC
 */
public class CodeCadeauReservation {
    private int id;
    private String code;
    private double prix;
    public CodeCadeauReservation() {
    }

    public CodeCadeauReservation(String code, double prix) {
        this.code = code;
        this.prix = prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getId() {
        return id;
    }

    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    
    
    @Override
    public String toString() {
        return "CodeCadeauReservation{" +"id=" +id+ " ,code=" + code + ", prix=" + prix + '}';
    }
    
    
}
