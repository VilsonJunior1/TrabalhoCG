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
public class Poligono implements Serializable{
    private ArrayList<Pontos> listaP;
    private ArrayList<Faces> listaFaces;
    private double KaR;
    private double KaG;
    private double KaB;
    private double KsR;
    private double KsG;
    private double KsB;
    private double KdR;
    private double KdG;
    private double KdB;
    private double n;

    public Poligono() {
        this.listaP = new ArrayList<>();
        this.listaFaces = new ArrayList<>();
        this.KaR = 1;//1;
        this.KaG = 1;
        this.KaB = 1;//0.5;
        this.KsR = 1;//0.7;
        this.KsG = 1;//0.7;
        this.KsB = 1;//0.7;
        this.KdR = 1;//0.5;
        this.KdG = 1;//0.5;
        this.KdB = 1;//0.5;
        this.n = 1;//2.15;
    }

    public ArrayList<Pontos> getListaP() {
        return listaP;
    }

    public void setListaP(ArrayList<Pontos> listaP) {
        this.listaP = listaP;
    }

    public ArrayList<Faces> getListaFaces() {
        return listaFaces;
    }

    public void setListaFaces(ArrayList<Faces> listaFaces) {
        this.listaFaces = listaFaces;
    }

    public double getKaR() {
        return KaR;
    }

    public void setKaR(double KaR) {
        this.KaR = KaR;
    }

    public double getKaG() {
        return KaG;
    }

    public void setKaG(double KaG) {
        this.KaG = KaG;
    }

    public double getKaB() {
        return KaB;
    }

    public void setKaB(double KaB) {
        this.KaB = KaB;
    }

    public double getKsR() {
        return KsR;
    }

    public void setKsR(double KsR) {
        this.KsR = KsR;
    }

    public double getKsG() {
        return KsG;
    }

    public void setKsG(double KsG) {
        this.KsG = KsG;
    }

    public double getKsB() {
        return KsB;
    }

    public void setKsB(double KsB) {
        this.KsB = KsB;
    }

    public double getKdR() {
        return KdR;
    }

    public void setKdR(double KdR) {
        this.KdR = KdR;
    }

    public double getKdG() {
        return KdG;
    }

    public void setKdG(double KdG) {
        this.KdG = KdG;
    }

    public double getKdB() {
        return KdB;
    }

    public void setKdB(double KdB) {
        this.KdB = KdB;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }
    
}
