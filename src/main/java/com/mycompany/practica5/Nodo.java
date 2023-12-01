/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica5;

/**
 *
 * @author felix
 */
public class Nodo<T> {
    Nodo<T> sig;
    Nodo<T> ant;
    T dato;
    public Nodo (T dato)
    {
        this.dato = dato;
    }

    public void setSig(Nodo<T> sig) {
        this.sig = sig;
    }

    public void setAnt(Nodo<T> ant) {
        this.ant = ant;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSig() {
        return sig;
    }

    public Nodo<T> getAnt() {
        return ant;
    }

    public T getDato() {
        return dato;
    }
    
}
