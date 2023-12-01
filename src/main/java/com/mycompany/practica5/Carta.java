/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica5;

/**
 *
 * @author felix
 */
public class Carta {
    String nombre;
    String atributo;
    String[] tipo;
    int lv;
    int atk;
    int def;
    String caminoImagen;
    String descripción;
    public Carta(String nombre, String atributo, String[] tipo, int lv, int atk, int def) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.tipo = tipo;
        this.lv = lv;
        this.atk = atk;
        this.def = def;
    }
    public Carta(String nombre, String atributo, String[] tipo, int lv, int atk, int def, String caminoImagen) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.tipo = tipo;
        this.lv = lv;
        this.atk = atk;
        this.def = def;
        this.caminoImagen = caminoImagen;
    }

    public Carta(String nombre, String atributo, String[] tipo) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.tipo = tipo;
        this.atk = -1;
        this.def = -1;
        this.lv = -1;
    }
    public Carta(String nombre, String atributo, String[] tipo, String caminoImagen) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.tipo = tipo;
        this.atk = -1;
        this.def = -1;
        this.lv = -1;
        this.caminoImagen = caminoImagen;
    }
    public String getNombre() {
        return nombre;
    }

    public String getAtributo() {
        return atributo;
    }

    public String[] getTipo() {
        return tipo;
    }

    public int getLv() {
        return lv;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public String getCaminoImagen() {
        return caminoImagen;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        String aux = "";
        if (descripción != null)
        {
        if (descripción.length() >= 100)
        {
            int corte = 100;
            int cortes = descripción.length()/corte;
            int i = 0;
            while (i < cortes)
            {
                aux += descripción.substring(i*corte, corte*(i+1));
                aux += "\n";
                i += 1;
            }
            aux += descripción.substring(corte*(i));
            this.descripción = aux;
        }
        else
        {
            this.descripción = descripción;
        }
        }
        else
        {
            this.descripción = descripción;
        }
    }
    
    @Override
    public String toString ()
    {
        String salida = "";
        salida += "-----------------------------\n";
        salida += "Carta: " + nombre + "\n";
        salida += "Atributo: " + atributo + "\n";
        salida += "Tipo: ";
        for (String i:tipo)
        {
            salida += i + ", ";
        }
        salida += "\n";
        if (this.atk != -1)
        {
            salida += "Atk: " + atk + " Def: " + def + " Lv: " + lv + "\n";
        }
        salida += "Descripción: " + descripción;
        salida += "--------------------------------\n";
        return salida;
    }
}
