/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author vilson
 */
public class Pintura implements Serializable{
    private ArrayList<Pontos> listaPColorir;
    private ArrayList<Faces> listaFaces;
    private String cor;
    private boolean temCor;

    public Pintura() {
        this.listaPColorir = new ArrayList<>();
        this.listaFaces = new ArrayList<>();
        this.cor = "";
        this.temCor = false;
    }

    public ArrayList<Pontos> getListaPColorir() {
        return listaPColorir;
    }

    public void setListaPColorir(ArrayList<Pontos> listaPColorir) {
        this.listaPColorir = listaPColorir;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        //System.out.println("trocou a cor de "+ this.cor);
        this.cor = cor;
        //System.out.println("para "+ this.cor);
    }

    public boolean getTemCor() {
        return temCor;
    }

    public void setTemCor(boolean temCor) {
        this.temCor = temCor;
    }
    
    public ArrayList<Faces> getListaFaces() {
        return listaFaces;
    }

    public void setListaFaces(ArrayList<Faces> listaFaces) {
        this.listaFaces = listaFaces;
    }
    
}
