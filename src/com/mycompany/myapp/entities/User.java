/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;


public class User {
    private int id,isverified,coder,codep;
    private String email,password,firstname,lastname,suspension,raisonsuspension,image,nomuser;
    private Date dater;
    private String roles ;
    private String roleuser;
    private String confirmpassword;

    
    public int getCoder() {
        return coder;
    }

    public void setCoder(int coder) {
        this.coder = coder;
    }

    public int getCodep() {
        return codep;
    }

    public void setCodep(int codep) {
        this.codep = codep;
    }

   
 
   
    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(String roleuser) {
        this.roleuser = roleuser;
    }
   

    public int getId() {
        return id;
    }
    public void setId(int id) {
         this.id = id;
    }

    

    public int getIsverified() {
        return isverified;
    }

    public void setIsverified(int isverified) {
        this.isverified = isverified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public String getRaisonsuspension() {
        return raisonsuspension;
    }

    public void setRaisonsuspension(String raisonsuspension) {
        this.raisonsuspension = raisonsuspension;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public Date getDater() {
        return dater;
    }

    public void setDater(Date dater) {
        this.dater = dater;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles= roles;
        
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", coder=" + coder + ", codep=" + codep + ", email=" + email + ", password=" + password + ", suspension=" + suspension + ", raisonsuspension=" + raisonsuspension + ", image=" + image + ", nomuser=" + nomuser + ", roles=" + roles + '}';
    }

   
   
   
   
}
