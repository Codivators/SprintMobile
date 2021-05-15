/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author MSI PC
 */
public class Article {
    private int id,userid;
    private String title,text,texthtml,image, username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private Date datepub;
    

    public Date getDatepub() {
        return datepub;
    }

    public void setDatepub(Date datepub) {
        this.datepub = datepub;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTexthtml() {
        return texthtml;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTexthtml(String texthtml) {
        this.texthtml = texthtml;
    }

    public int getId() {
        return id;
    }



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return userid +"  "+ title +"  "+"  "+ text +"  "+ datepub + "\n";
    }
    
    
}
