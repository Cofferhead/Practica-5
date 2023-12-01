/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica5;

import java.util.Comparator;

/**
 *
 * @author felix
 */
public class ListaCircular<T> {
    Nodo<T> inicio;
    Nodo<T> fin;
    public ListaCircular()
    {
        inicio = null;
        fin = null;
    }
    public ListaCircular (ListaCircular copia)
    {
        int n = copia.posiciónFinal();
        for (int i = 0; i <= n; i++)
        {
            this.insertarFin((T) copia.buscarPosición(i).getDato());
        }
    }
    public void insertarInicio (T dato)
    {
        Nodo<T> n = new Nodo(dato);
        if (inicio == null)
        {
            inicio = n;
            fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        }
        else
        {
            n.setSig(inicio);
            inicio.setAnt(n);
            inicio = n;
            fin.setSig(inicio);
            n.setAnt(fin);
        }
    }
    public void insertarFin(T dato)
    {
        Nodo<T> n = new Nodo(dato);
        if (inicio == null)
        {
            inicio = n;
            fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        }
        else
        {
            n.setSig(inicio);
            inicio.setAnt(n);
            fin.setSig(n);
            n.setAnt(fin);
            fin = n;
        }
    }
    public Nodo<T> eliminarInicio ()
    {
        Nodo<T> aux = null;
        if (inicio == null)
        {
            System.out.println("Lista vacia");
        }
        else if (inicio == fin)
        {
            aux = inicio;
            inicio = null;
            fin = null;
        }
        else
        {
            aux = inicio;
            fin.setSig(inicio.getSig());
            inicio = inicio.getSig();
            inicio.setAnt(fin);
        }
        return aux;
    }
    public Nodo<T> eliminarFinal ()
    {
        Nodo<T> aux = null;
        if (inicio == null)
        {
            System.out.println("Lista vacia");
        }
        else if (inicio == fin)
        {
            aux = fin;
            inicio = null;
            fin = null;
        }
        else
        {
            aux = fin;
            Nodo<T> r = fin.getAnt();
            r.setSig(inicio);
            inicio.setAnt(r);
            fin = r;
        }
        return aux;
    }
    //St mostrarRecursivo(Nodo x)
    public Nodo<T> peek ()
    {
        return inicio;
    }
    public String mostrarLista()
    {
        return mostrarListaRecursiva(inicio);
    }
    //String mostrarRecursivo(Nodo x)
    public String mostrarListaRecursiva (Nodo x)
    {
        String salida = "";
        if (x != fin)
        {
            salida += x.getDato().toString() + "\n";
            salida += mostrarListaRecursiva(x.getSig());
        }
        else
        {
            salida += fin.getDato().toString();
        }
        return salida;
        
    }
    //T eliminaX(T x)
    public T eliminaX (T x)
    {
        Nodo<T> aux = inicio;
        Nodo<T> preAux = fin;
        if (inicio.getDato() == x)
        {
            inicio = inicio.getSig();
        }
        do
        {
            if (aux.getDato() == x)
            {
                preAux.setSig(aux.getSig());
                aux.getSig().setAnt(preAux);
                if (aux == fin)
                {
                    fin = preAux;
                }
                return aux.getDato();
            }
            preAux = aux;
            aux = aux.getSig();
        }while (aux != inicio);
        return null;
    }
    //int buscar( T x)
    public int buscarX(T x)
    {
        Nodo<T> aux = inicio;
        int pos = 0;
        do
        {
            if (x == aux.getDato())
            {
                return pos;
            }
            pos += 1;
            aux = aux.getSig();
        }while (aux != inicio);
        return -1;
    }
    //T eliminaPos(int pos)
    public T eliminaPosición (int posición)
    {
        Nodo<T> aux = inicio;
        Nodo<T> preAux = fin;
        int pos = 0;
        if (inicio == fin)
        {
            inicio = null;
            fin = inicio;
            return null;
        }
        
        if (posición == 0)
        {
            inicio = inicio.getSig();
        }
        
        do
        {
            if (posición == pos)
            {
                preAux.setSig(aux.getSig());
                aux.getSig().setAnt(preAux);
                if (aux == fin)
                {
                    fin = preAux;
                }
                return aux.getDato();
            }
            pos += 1;
            preAux = aux;
            aux = aux.getSig();
        }while (aux != inicio);
        return null;
    }
    //ordenarLista()
    public void ordenarLista (Comparator comp)
    {
        int n = posiciónFinal();
        Nodo<T> aux1;
        Nodo<T> aux2;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n-i; j++)
            {
                aux1 = buscarPosición(j);
                aux2 = buscarPosición(j+1);
                if (comp.compare(aux1.getDato(), aux2.getDato()) > 0)
                {
                    intercambiarValores(j, j+1);
                }
            }
        }
    }
    public int posiciónFinal ()
    {
        int pos = 0;
        Nodo<T> r = inicio;
        if (inicio == fin)
        {
            return pos;
        }
        do
        {
            pos += 1;
            r = r.getSig();
        } while (r.getSig() != inicio);
        return pos;
    }
    public Nodo<T> buscarPosición(int posición)
    {
        Nodo<T> aux = inicio;
        int pos = 0;
        do
        {
            if (pos == posición)
            {
                return aux;
            }
            pos += 1;
            aux = aux.getSig();
        }while (aux != inicio);
        return null;
    }
    public void intercambiarValores (int pos1, int pos2)
    {
        int pos = 0;
        Nodo<T> aux1 = inicio;
        while (pos1 != pos)
        {
            if (pos == pos1)
            {
                break;
            }
            pos += 1;
            aux1 = aux1.getSig();
        }
        pos = 0;
        Nodo<T> aux2 = inicio;
        while (pos2 != pos)
        {
            if (pos == pos2)
            {
                break;
            }
            pos += 1;
            aux2 = aux2.getSig();
        }
        T valorAux = aux1.getDato();
        aux1.setDato(aux2.getDato());
        aux2.setDato(valorAux);
    }
    //insertaEnPosición(T dato, int pos)
    public void insertarEnPosición (T dato, int posición)
    {
        Nodo<T> n = new Nodo(dato);
        int pos = 0;
        Nodo<T> aux = inicio;
        Nodo<T> preAux = fin;
        do
        {
            if (pos == posición)
            {
                preAux.setSig(n);
                n.setAnt(preAux);
                n.setSig(aux);
                aux.setAnt(n);
                break;
            }
            pos += 1;
            preAux = aux;
            aux = aux.getSig();
        }while (aux != inicio);
        if (posición == 0)
        {
            inicio = n;
        }
        else if (aux == inicio)
        {
            preAux.setSig(n);
            n.setAnt(preAux);
            n.setSig(aux);
            aux.setAnt(n);
            fin = n;
        }
    }
}
