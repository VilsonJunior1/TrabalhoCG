/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vilson
 */
public class Arquivo implements Serializable {
    private ArrayList<Poligono> AllPoligonos;

    public Arquivo() {
        this.AllPoligonos = new ArrayList<>();
    }

    public ArrayList<Poligono> getAllPoligonos() {
        return AllPoligonos;
    }

    public void setAllPoligonos(ArrayList<Poligono> AllPoligonos) {
        this.AllPoligonos = AllPoligonos;
    }
    
    public void Abrir(String endereco) {
        AllPoligonos = new ArrayList<>();
        try {
            FileInputStream abrir = new FileInputStream(endereco);
            ObjectInputStream LerArq = new ObjectInputStream(abrir);
            AllPoligonos = (ArrayList<Poligono>) (List<Poligono>) (LerArq.readObject()); 
            //System.out.println("adsd");
            abrir.close();
            LerArq.close();
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        
    }

    public void salvar(String endereco) {
        FileOutputStream end = null;

        try {
            end = new FileOutputStream(endereco);
            ObjectOutputStream Arq = new ObjectOutputStream(end);
            Arq.writeObject(AllPoligonos);
            Arq.flush();
            Arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
}