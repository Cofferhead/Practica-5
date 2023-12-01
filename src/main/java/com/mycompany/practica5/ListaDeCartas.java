/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author felix
 */
public class ListaDeCartas {
    private ListaCircular<Carta> cartas;
    private File csv;
    private FileReader file;
    private BufferedReader buffer;
    private String atributoFiltro;
    private String lvFiltro;
    public ListaDeCartas () throws IOException
    {
        cartas = new ListaCircular();
        atributoFiltro = "Any";
        lvFiltro = "Any";
        csv = new File ("C:\\Users\\felix\\OneDrive\\Documentos\\NetBeansProjects\\Practica4\\src\\main\\java\\com\\mycompany\\practica4\\listaCartas.csv");
        file = null;
        try {
            file = new FileReader(csv);
            }catch (FileNotFoundException ex) {
            Logger.getLogger(Practica5.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new BufferedReader(file);
        //Carta aux;
        String lineaCSV = buffer.readLine();
        String[] datosCarta;
        String[] tipos;
        int tamaño;
        ImageIcon iconoAux;
        String caminoImagen;
        String caminoAux = "C:\\Users\\felix\\OneDrive\\Documentos\\NetBeansProjects\\Practica4\\src\\main\\java\\com\\mycompany\\practica4\\Imagenes de cartas\\";
        while(lineaCSV != null)
        {
            datosCarta = lineaCSV.split(",");
            tamaño = datosCarta.length;
            caminoImagen = caminoAux + datosCarta[0] + ".jpg";
            if (datosCarta.length > 3)
            {
                tipos = new String[tamaño-5];
                for (int i = 2; i < tamaño-3; i++)
                {
                    tipos[i-2] = datosCarta[i];
                }
                //aux = new Carta(datosCarta[0], datosCarta[1],tipos, Integer.parseInt(datosCarta[tamaño-3]), Integer.parseInt(datosCarta[tamaño-2]), Integer.parseInt(datosCarta[tamaño-1]));
                cartas.insertarInicio( new Carta(datosCarta[0], datosCarta[1],tipos, Integer.parseInt(datosCarta[tamaño-3]), 
                        Integer.parseInt(datosCarta[tamaño-2]), Integer.parseInt(datosCarta[tamaño-1]), caminoImagen));
            }
            else
            {
                tipos = new String[tamaño-2];
                for (int i = 2; i < tamaño; i++)
                {
                    tipos[i-2] = datosCarta[i];
                }
                //aux = new Carta(datosCarta[0], datosCarta[1], tipos);
                cartas.insertarInicio(new Carta(datosCarta[0], datosCarta[1], tipos, caminoImagen));
            }
            //caminoImagen = caminoAux + datosCarta[0] + ".png";
            //iconoAux = new ImageIcon(caminoAux + datosCarta[0] + ".png");
            //aux.setIcono(iconoAux);        
           lineaCSV = buffer.readLine();
        }
        asignarDescripciones ();
    }
    private void asignarDescripciones () throws IOException
    {
        int length = this.cartas.posiciónFinal();
        int i = 0;
        csv = new File ("C:\\Users\\felix\\OneDrive\\Documentos\\NetBeansProjects\\Practica4\\src\\main\\java\\com\\mycompany\\practica4\\DescripcionesCartas.csv");
        file = null;
        try {
            file = new FileReader(csv);
            }catch (FileNotFoundException ex) {
            Logger.getLogger(Practica5.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new BufferedReader(file);
        //Carta aux;
        String lineaCSV = buffer.readLine();
        while (length > -1 || lineaCSV != null)
        {
            cartas.buscarPosición(length).getDato().setDescripción(lineaCSV);
            lineaCSV = buffer.readLine();
            length -= 1;
        }
    }
    public void mostrarLista()
    {
        System.out.println(cartas.mostrarLista());
    }

    public ListaCircular<Carta> getCartas() {
        return cartas;
    }
    
    public ListaCircular<Carta> ordenarListaPorNivelLtoH()
    {
        Comparator porNivelLtoH = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c1.getLv() - c2.getLv());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porNivelLtoH);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorNivelHtoL()
    {
        Comparator porNivelHtoL = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c2.getLv() - c1.getLv());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porNivelHtoL);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaAlfabeticamente()
    {
        Comparator alfabeticamente = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return c1.getNombre().compareTo(c2.getNombre());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(alfabeticamente);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaAlfabeticamenteInversa()
    {
        Comparator alfabeticamenteInversa = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return c2.getNombre().compareTo(c1.getNombre());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(alfabeticamenteInversa);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorAtributo ()
    {
        Comparator porAtributo = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c1.getAtributo().compareTo(c2.getAtributo()));
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porAtributo);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorAtributoReverse ()
    {
        Comparator porAtributoReverse = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c1.getAtributo().compareTo(c2.getAtributo()));
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porAtributoReverse);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorAtkLtoH ()
    {
        Comparator porAtkLtoH = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c1.getAtk()-c2.getAtk());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porAtkLtoH);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
     public ListaCircular<Carta> ordenarListaPorAtkHtoL ()
    {
        Comparator porAtkHtoL = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c2.getAtk()-c1.getAtk());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porAtkHtoL);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorDefHtoL()
    {
        Comparator porDefHtoL = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c2.getDef()-c1.getDef());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porDefHtoL);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
    public ListaCircular<Carta> ordenarListaPorDefLtoH()
    {
        Comparator porDefLtoH = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               Carta c1 = (Carta) o1;
               Carta c2 = (Carta) o2;
               return (c1.getDef()-c2.getDef());
            }
        };
        ListaCircular<Carta> salida = new ListaCircular(this.cartas);
        salida.ordenarLista(porDefLtoH);
        salida = this.filtrarLista(salida, this.atributoFiltro, this.lvFiltro);
        salida = quitarMagiasTrampas(salida);
        return salida;
    }
    public String getAtributoFiltro() {
        return atributoFiltro;
    }

    public String getLvFiltro() {
        return lvFiltro;
    }

    public void setAtributoFiltro(String atributoFiltro) {
        this.atributoFiltro = atributoFiltro;
    }

    public void setLvFiltro(String lvFiltro) {
        this.lvFiltro = lvFiltro;
    }
    
    public ListaCircular<Carta> filtrarLista (ListaCircular<Carta> lista, String atributo, String lv)
    {
        int tamaño = lista.posiciónFinal();
        ListaCircular<Carta> aux = new ListaCircular(lista);
        this.atributoFiltro = atributo;
        this.lvFiltro = lv;
        int i = 0;
        int a = 0;
        while (lista.buscarPosición(i) != null)
        {
            atributo = atributoFiltro;
            lv = lvFiltro;
            if (atributo.equals("Any"))
            {
                atributo = lista.buscarPosición(i).getDato().getAtributo();
            }
            if (lv.equals("Any"))
            {
                lv = String.valueOf(lista.buscarPosición(i).getDato().getLv());
            }
            if (lista.buscarPosición(i).getDato().getAtributo().equals(atributo) == false ||
                    lv.equals(String.valueOf(lista.buscarPosición(i).getDato().getLv())) == false)
            {
                lista.eliminaPosición(i);
                i -= 1;
            }
            a += 1;
            i += 1;
        }
        if (lista.buscarPosición(0)  != null)
        {
            return lista;
        }
        else
        {
            return aux;
        }
    }
    public ListaCircular<Carta> quitarMagiasTrampas (ListaCircular<Carta> lista)
    {
        ListaCircular<Carta> aux = new ListaCircular(lista);
        int i = 0;
        int a = 0;
        while (lista.buscarPosición(i) != null)
        {
            
            if (lista.buscarPosición(i).getDato().getAtributo().equals("Magia") == true ||
                    lista.buscarPosición(i).getDato().getAtributo().equals("Trampa") == true)
            {
                lista.eliminaPosición(i);
                i -= 1;
            }
            a += 1;
            i += 1;
        }
        if (lista.buscarPosición(0)  != null)
        {
            return lista;
        }
        else
        {
            return aux;
        }
    }
}
