/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author vilson
 */
public class Faces implements Serializable{
    private ArrayList<Pontos> listaPFaces;
    private boolean Visivel;

    public Faces() {
        this.listaPFaces = new ArrayList<>();
        this.Visivel = true;
    }

    public ArrayList<Pontos> getListaPFaces() {
        return listaPFaces;
    }

    public void setListaPFaces(ArrayList<Pontos> listaP) {
        this.listaPFaces = listaP;
    }

    public boolean isVisivel() {
        return Visivel;
    }

    public void setVisivel(boolean Visivel) {
        this.Visivel = Visivel;
    }
    
    
}
