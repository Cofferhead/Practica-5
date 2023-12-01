/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica5;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author felix
 */
public class Practica5 {

   public static void main(String[] args) throws Exception {
    ListaAux<String> a = new ListaAux();
    ListaCircular<String> b = new ListaCircular();
    a.insertarFin("Leobardo");
    a.insertarFin("Félix");
    a.insertarFin("Ayala");
    a.insertarInicio("Before");
    a.insertarInicio("Even");
    a.insertarEnPosición("Wait for...", 3);
    a.insertarEnPosición("That's it", 9);
    a.insertarEnPosición("So...", 0);
       System.out.println("Lista a:");
       System.out.println(a.mostrarLista());
    b.insertarFin("Leobardo");
    b.insertarFin("Félix");
    b.insertarFin("Ayala");
    b.insertarInicio("Before");
    b.insertarInicio("Even");
    b.insertarEnPosición("Wait for...", 3);
    b.insertarEnPosición("That's it", 9);
    b.insertarEnPosición("So...", 0);
       System.out.println("Lista b:");
       System.out.println(b.mostrarLista());
  }
}
