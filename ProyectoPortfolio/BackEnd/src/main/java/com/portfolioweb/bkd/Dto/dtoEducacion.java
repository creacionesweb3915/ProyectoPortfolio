/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioweb.bkd.Dto;

import javax.validation.constraints.NotBlank;


public class dtoEducacion {
    @NotBlank
    private String nombreD;
    @NotBlank
    private String descripcionD;
    
    //Constructores

    public dtoEducacion() {
    }

    public dtoEducacion(String nombreD, String descripcionD) {
        this.nombreD = nombreD;
        this.descripcionD = descripcionD;
    }
    
    
    //getters adn setters

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getDescripcionD() {
        return descripcionD;
    }

    public void setDescripcionD(String descripcionD) {
        this.descripcionD = descripcionD;
    }
    
    
    
}
