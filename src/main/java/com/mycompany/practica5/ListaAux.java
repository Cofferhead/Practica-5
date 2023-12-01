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
public class ListaAux<T> {
    private Nodo<T> inicio;
    public ListaAux()
    {
        inicio = null;
    }
    public ListaAux (ListaAux copia)
    {
        int n = copia.posiciónFinal();
        for (int i = 0; i <= n; i++)
        {
            this.insertarFin((T) copia.buscarPosición(i).getDato());
        }
    }
    public void insertarInicio(T dato)
    {
        Nodo<T> n = new Nodo(dato);
        n.setSig(inicio);
        n.setAnt(null);
        if (inicio != null)
        {
            inicio.setAnt(n);
        }
        inicio = n;
    }
    public void insertarFin(T dato)
    {
        Nodo<T> n = new Nodo(dato);
        n.setSig(null);
        if (inicio == null)
        {
            n.setAnt(inicio);
            n.setSig(inicio);
            inicio = n;
        }
        else
        {
            Nodo<T> r = inicio;
            while (r.getSig() != null)
            {
                r = r.getSig();
            }
            r.setSig(n);
            n.setAnt(r);
        }
    }
    public T eliminarInicio()
    {
        if (inicio == null)
        {
            System.out.println("Lista vacia");
            return null;
        }
        else
        {
            Nodo<T> aux = inicio;
            if (inicio.getSig() == null)
            {
                inicio = null;
            }
            else
            {
                inicio = inicio.getSig();
                inicio.setAnt(null);
            }
            return aux.getDato();
        }
    }
    public T eliminarFinal()
    {
        if (inicio == null)
        {
            System.out.println("Lista vacia");
            return null;
        }
        else
        {
            Nodo<T> aux = inicio;
            if (inicio.getSig() == null)
            {
                inicio = null;
            }
            else
            {
                Nodo<T> r = inicio.getSig();
                Nodo<T> a = inicio;
                while (r.getSig() != null)
                {
                    a = r;
                    r = r.getSig();
                }
                aux = r;
                a.setSig(null);
                r.setAnt(null);
                r.setSig(null);
            }
            return aux.getDato();
        }
    }
    public void mostrarListaDesdeInicio ()
    {
        Nodo<T> aux = inicio;
        while (aux != null)
        {
            System.out.println(aux.getDato().toString());
            aux = aux.getSig();
        }
    }
    public void mostrarListaDesdeFinal ()
    {
        Nodo<T> aux = inicio;
        while (aux.getSig() != null)
        {
            aux = aux.getSig();
        }
        while (aux != null)
        {
            System.out.println(aux.getDato().toString());
            aux = aux.getAnt();
        }
    }
    
    //String mostrarRecursivo
    public String mostrarLista()
    {
        return mostrarListaRecursiva();
    }
    private String mostrarListaRecursiva ()
    {
        String salida = "";
        Nodo<T> aux = inicio;
        if (inicio != null)
        {
            salida += inicio.getDato().toString() + "\n";
            inicio = inicio.getSig();
            salida += mostrarListaRecursiva();
        }
        inicio = aux;
        return salida;
    }
    //T eliminaX
    public T eliminaX (T x)
    {
        Nodo<T> aux = inicio;
        Nodo<T> preAux = inicio.getAnt();
        if (inicio.getDato() == x)
        {
            inicio = inicio.getSig();
            inicio.setAnt(null);
            return aux.getDato();
        }
        else
        {
            preAux = aux;
            aux = inicio.getSig();
            while (aux != null)
            {
                if (aux.getDato() == x)
                {
                    if (aux.getSig() != null)
                    {
                        aux.getSig().setAnt(preAux);
                    }
                    preAux.setSig(aux.getSig());
                    return aux.getDato();
                }
                preAux = aux;
                aux = aux.getSig();
            }
            return null;
        }
    }
    //int buscarX
    public int buscarX (T x)
    {
        int pos = 0;
        Nodo<T> aux = inicio;
        while (aux != null)
        {
            if (aux.getDato() == x)
            {
                return pos;
            }
            pos += 1;
            aux = aux.getSig();
        }
        return -1;
    }
    //T eliminaPosición
    public T eliminaPosición (int posición)
    {
        int pos = 0;
        Nodo<T> aux = inicio;
        Nodo<T> preAux = null;
        if (posición == pos)
        {
            inicio = inicio.getSig();
            //inicio.setAnt(null);
            return aux.getDato();
        }
        else
        {
            pos += 1;
            aux = inicio.getSig();
            preAux = inicio;
            while (aux != null)
            {
                if (pos == posición)
                {
                    preAux.setSig(aux.getSig());
                    if (aux.getSig() != null)
                    {
                        aux.getSig().setAnt(preAux);
                    }
                    return aux.getDato();
                }
                pos += 1;
                preAux = aux;
                aux = aux.getSig();
            }
            return null;
        }
    }
    //ordenarLista ()
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
        int pos = -1;
        Nodo<T> r = inicio;
        while (r != null)
        {
            pos += 1;
            r = r.getSig();
        }
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
    
    //insertarEnPosición
    public void insertarEnPosición (T dato, int posición)
    {
        Nodo<T> n = new Nodo(dato);
        if (posición == 0)
        {
            inicio.setAnt(n);
            n.setSig(inicio);
            inicio = n;
            inicio.setAnt(null);
        }
        else
        {
            int pos = 1;
            Nodo<T> aux = inicio.getSig();
            Nodo<T> preAux = inicio;
            while (aux != null)
            {
                if (pos == posición)
                {
                    preAux.setSig(n);
                    n.setAnt(preAux);
                    aux.setAnt(n);
                    n.setSig(aux);
                    break;
                }
                pos += 1;
                preAux = aux;
                aux = aux.getSig();
            }
            if (aux == null)
            {
                preAux.setSig(n);
                n.setAnt(n);
                n.setSig(null);
            }
        }
    }
}
