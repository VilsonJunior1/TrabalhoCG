/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author vilson
 */
public class Coloracao {
    private ArrayList<Pontos> Poligon;
    private ArrayList<Arestas> arestasXY;
    private ArrayList<Arestas> arestasXZ;//alterado aqui
    private ArrayList<Arestas> arestasYZ;

    public Coloracao() {
        this.Poligon = new ArrayList<>();
        this.arestasXY = new ArrayList<>();
        this.arestasXZ = new ArrayList<>();
        this.arestasYZ = new ArrayList<>();
    }

    public ArrayList<Pontos> getPoligon() {
        return Poligon;
    }

    public void setPoligon(ArrayList<Pontos> Poligon) {
        this.Poligon = Poligon;
    }

    public ArrayList<Arestas> getArestasXY() {
        return arestasXY;
    }
    
    public ArrayList<Arestas> getArestasXZ() {
        return arestasXZ;
    }
    
    public ArrayList<Arestas> getArestasYZ() {
        return arestasYZ;
    }

    public void setArestasXY(ArrayList<Arestas> arestasXY) {
        this.arestasXY = arestasXY;
    }
    
    public void setArestasXZ(ArrayList<Arestas> arestasXZ) {
        this.arestasXZ = arestasXZ;
    }
    
    public void setArestasYZ(ArrayList<Arestas> arestasYZ) {
        this.arestasYZ = arestasYZ;
    }
    
    private void incializaAresta()
    {
        ArrayList<Arestas> inicializaXY = new ArrayList<>();
        ArrayList<Arestas> inicializaXZ = new ArrayList<>();
        ArrayList<Arestas> inicializaYZ = new ArrayList<>();
        
        Pontos p1XY = new Pontos();
        Pontos p2XY = new Pontos();
        Pontos p1XZ = new Pontos();
        Pontos p2XZ = new Pontos();
        Pontos p1YZ = new Pontos();
        Pontos p2YZ = new Pontos();
        
        for (int i = 0; i < Poligon.size(); i++)
        {
                p1XY.setX(Poligon.get(i).getX());
                p1XY.setY(Poligon.get(i).getY());
                p1XZ.setX(Poligon.get(i).getX());
                p1XZ.setY(Poligon.get(i).getZ());
                p1YZ.setX(Poligon.get(i).getZ());
                p1YZ.setY(Poligon.get(i).getY());
            
                if(i == Poligon.size()-1)
                {
                    p2XY.setX(Poligon.get(0).getX());
                    p2XY.setY(Poligon.get(0).getY());
                    p2XZ.setX(Poligon.get(0).getX());
                    p2XZ.setY(Poligon.get(0).getZ());
                    p2YZ.setX(Poligon.get(0).getZ());
                    p2YZ.setY(Poligon.get(0).getY());
                }
                else
                {
                    p2XY.setX(Poligon.get(i+1).getX());
                    p2XY.setY(Poligon.get(i+1).getY());
                    p2XZ.setX(Poligon.get(i+1).getX());
                    p2XZ.setY(Poligon.get(i+1).getZ());
                    p2YZ.setX(Poligon.get(i+1).getZ());
                    p2YZ.setY(Poligon.get(i+1).getY());
                }
                Arestas arestaXY = new Arestas();
                Arestas arestaXZ = new Arestas();
                Arestas arestaYZ = new Arestas();

                arestaXY.setX1((int)p1XY.getX());
                arestaXY.setY1((int)p1XY.getY());
                arestaXZ.setX1((int)p1XZ.getX());
                arestaXZ.setY1((int)p1XZ.getY());
                arestaYZ.setX1((int)p1YZ.getX());
                arestaYZ.setY1((int)p1YZ.getY());

                arestaXY.setX2((int)p2XY.getX());
                arestaXY.setY2((int)p2XY.getY());
                arestaXZ.setX2((int)p2XZ.getX());
                arestaXZ.setY2((int)p2XZ.getY());
                arestaYZ.setX2((int)p2YZ.getX());
                arestaYZ.setY2((int)p2YZ.getY());

                inicializaXY.add(arestaXY);
                inicializaXZ.add(arestaXZ);
                inicializaYZ.add(arestaYZ);

                p1XY = new Pontos();
                p2XY = new Pontos();
                p1XZ = new Pontos();
                p2XZ = new Pontos();
                p1YZ = new Pontos();
                p2YZ = new Pontos();
            
        }
        
        for(int i = 0; i < inicializaXY.size(); i++)
        {
            
            double x1XY, x2XY, y1XY, y2XY, m;
            double x1XZ, x2XZ, y1XZ, y2XZ;
            double x1YZ, x2YZ, y1YZ, y2YZ;
            
            x1XY = inicializaXY.get(i).getX1();
            y1XY = inicializaXY.get(i).getY1();
            x2XY = inicializaXY.get(i).getX2();
            y2XY = inicializaXY.get(i).getY2();
            
            x1XZ = inicializaXZ.get(i).getX1();
            y1XZ = inicializaXZ.get(i).getY1();
            x2XZ = inicializaXZ.get(i).getX2();
            y2XZ = inicializaXZ.get(i).getY2();
            
            x1YZ = inicializaYZ.get(i).getX1();
            y1YZ = inicializaYZ.get(i).getY1();
            x2YZ = inicializaYZ.get(i).getX2();
            y2YZ = inicializaYZ.get(i).getY2();
            
            //alterado Para encontrar os pontos das arestas do plano XY
            if(y1XY > y2XY)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x2XY);
                aresta.setY1(y2XY);
                
                aresta.setX2(x1XY);
                aresta.setY2(y1XY);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasXY.add(aresta);
            }
            else if(y1XY < y2XY)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1XY);
                aresta.setY1(y1XY);
                
                aresta.setX2(x2XY);
                aresta.setY2(y2XY);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasXY.add(aresta);
            }
            else
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1XY);
                aresta.setY1(y1XY);
                
                aresta.setX2(x2XY);
                aresta.setY2(y2XY);
                
                m = -1000;
                
                aresta.setM(m);
                
                arestasXY.add(aresta);
            }
            
            //alterado Para encontrar os pontos das arestas do plano XZ
            if(y1XZ > y2XZ)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x2XZ);
                aresta.setY1(y2XZ);
                
                aresta.setX2(x1XZ);
                aresta.setY2(y1XZ);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasXZ.add(aresta);
            }
            else if(y1XZ < y2XZ)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1XZ);
                aresta.setY1(y1XZ);
                
                aresta.setX2(x2XZ);
                aresta.setY2(y2XZ);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasXZ.add(aresta);
            }
            else
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1XZ);
                aresta.setY1(y1XZ);
                
                aresta.setX2(x2XY);
                aresta.setY2(y2XY);
                
                m = -1000;
                
                aresta.setM(m);
                
                arestasXZ.add(aresta);
            }
            
            //alterado Para encontrar os pontos das arestas do plano XZ
            if(y1YZ > y2YZ)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x2YZ);
                aresta.setY1(y2YZ);
                
                aresta.setX2(x1YZ);
                aresta.setY2(y1YZ);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasYZ.add(aresta);
            }
            else if(y1YZ < y2YZ)
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1YZ);
                aresta.setY1(y1YZ);
                
                aresta.setX2(x2YZ);
                aresta.setY2(y2YZ);
                
                m = (aresta.getX2() - aresta.getX1())/(aresta.getY2() - aresta.getY1());
                
                aresta.setM(m);
                
                arestasYZ.add(aresta);
            }
            else
            {
                Arestas aresta = new Arestas();
                
                aresta.setX1(x1YZ);
                aresta.setY1(y1YZ);
                
                aresta.setX2(x2YZ);
                aresta.setY2(y2YZ);
                
                m = -1000;
                
                aresta.setM(m);
                
                arestasYZ.add(aresta);
            }
        }
    }
    public void colorir(GraphicsContext gc, String cor, int tela)//alterado para void
    {
        incializaAresta();
        pontosColorir(gc, cor, tela);

        //return corFill;
    }
    //Começou a alteração aqui
    private void pontosColorir(GraphicsContext gc, String cor, int tela)
    {
        double Xmax=0, Xmin=0, Ymax=0, Ymin=0;
        ArrayList<Arestas> arestas = new ArrayList<>();
            if(tela == 1)
            {
                arestas = arestasXY;
                Xmax = Poligon.get(0).getX();
                Xmin = Poligon.get(0).getX();
                Ymax = Poligon.get(0).getY();
                Ymin = Poligon.get(0).getY();
                for (int i = 0; i < Poligon.size(); i++)
                {
                    if (Xmax < Poligon.get(i).getX())
                    {
                        Xmax = Poligon.get(i).getX();
                    }
                    if (Xmin > Poligon.get(i).getX())
                    {
                        Xmin = Poligon.get(i).getX();
                    }
                    if (Ymax < Poligon.get(i).getY())
                    {
                        Ymax = Poligon.get(i).getY();
                    }
                    if (Ymin > Poligon.get(i).getY())
                    {
                        Ymin = Poligon.get(i).getY();
                    }
                }
            }
            else if(tela == 2)
            {
                arestas = arestasXZ;
                Xmax = Poligon.get(0).getX();
                Xmin = Poligon.get(0).getX();
                Ymax = Poligon.get(0).getZ();
                Ymin = Poligon.get(0).getZ();
                for (int i = 0; i < Poligon.size(); i++)
                {
                    if (Xmax < Poligon.get(i).getX())
                    {
                        Xmax = Poligon.get(i).getX();
                    }
                    if (Xmin > Poligon.get(i).getX())
                    {
                        Xmin = Poligon.get(i).getX();
                    }
                    if (Ymax < Poligon.get(i).getZ())
                    {
                        Ymax = Poligon.get(i).getZ();
                    }
                    if (Ymin > Poligon.get(i).getZ())
                    {
                        Ymin = Poligon.get(i).getZ();
                    }
                }
            }
            else if(tela == 3)
            {
                arestas = arestasYZ;
                Xmax = Poligon.get(0).getY();
                Xmin = Poligon.get(0).getY();
                Ymax = Poligon.get(0).getZ();
                Ymin = Poligon.get(0).getZ();
                for (int i = 0; i < Poligon.size(); i++)
                {
                    if (Xmax < Poligon.get(i).getZ())
                    {
                        Xmax = Poligon.get(i).getZ();
                    }
                    if (Xmin > Poligon.get(i).getZ())
                    {
                        Xmin = Poligon.get(i).getZ();
                    }
                    if (Ymax < Poligon.get(i).getY())
                    {
                        Ymax = Poligon.get(i).getY();
                    }
                    if (Ymin > Poligon.get(i).getY())
                    {
                        Ymin = Poligon.get(i).getY();
                    }
                }
            }


            ArrayList<Pontos> pontos = new ArrayList<>();

            Map<Integer,ArrayList<Double>> lista = new HashMap<>();

            for(int i = (int)Ymin; i < Ymax; i++)
            {
                for(int j = 0; j < arestas.size(); j++)
                {

                    if(i==arestas.get(j).getY1())//se for o  minimo
                    {
                        double variavel=0;
                        for(int k=(int) arestas.get(j).getY1();k<arestas.get(j).getY2();k++)//Percorre toda a aresta
                        {
                            //System.out.println("j="+j);
                            if((k == (int)arestas.get(j).getY1())&&(arestas.get(j).getM()!=-1000))//verifica se existe uma aresta naquele ponto y
                            {
                                if(!lista.containsKey(k))//se não conter na hashmap
                                {
                                    ArrayList<Double> nuevo = new ArrayList<>();
                                    nuevo.add(arestas.get(j).getX1());

                                    lista.put(k,nuevo);
                                    variavel = arestas.get(j).getX1();
                                }
                                else if(lista.containsKey(k)){
                                    ArrayList<Double> nuevo;

                                    nuevo = lista.get(k);
                                    nuevo.add(arestas.get(j).getX1());

                                    lista.remove(k);
                                    lista.put(k,nuevo);
                                    variavel = arestas.get(j).getX1();
                                }
                            }
                            else
                            {
                                if(!lista.containsKey(k)&&(arestas.get(j).getM()!=-1000))//se não conter na hashmap
                                {
                                    //System.out.println("aiaiaia");
                                    ArrayList<Double> nuevo = new ArrayList<>();
                                    variavel += (arestas.get(j).getM());
                                    nuevo.add(variavel);

                                    lista.put(k,nuevo);
                                }
                                else
                                if(lista.containsKey(k)&&(arestas.get(j).getM()!=-1000))
                                {
                                    ArrayList<Double> nuevo;


                                    nuevo = lista.get(k);
                                    variavel += (arestas.get(j).getM());
                                    nuevo.add(variavel);

                                    lista.remove(k);
                                    lista.put(k,nuevo);
                                }
                            }
                            //System.out.println("Variavel = "+variavel+"  k = "+k);
                        }
                    }

                }
            }


            for(int i = (int)Ymin; i < Ymax; i++)
            {
                if(lista.containsKey(i))
                {
                    ArrayList<Double> nuevo;

                    nuevo = lista.get(i);
                    nuevo = ordenaLista(nuevo);

                    lista.put(i,nuevo);
                }
            }

            for(int i = (int)Ymin+1; i < Ymax; i++)
            {
                //System.out.println(""+i);
                if(lista.containsKey(i))
                {
                    for(int j=0;j<lista.get(i).size();j++){

                        if(j%2==0)
                        {
                            gc.beginPath();
                            gc.setLineCap(StrokeLineCap.BUTT);
                            gc.setLineWidth(2);
                            gc.setStroke(Color.web(cor));
                            gc.moveTo(lista.get(i).get(j) + 181, i + 133.75);

                        }
                        else
                        {
                            
                            gc.lineTo(lista.get(i).get(j) + 181, i + 133.75);
                            gc.stroke();
                        }

                    }
                }
            }
        
        /*for(int i = (int)Ymin; i < Ymax; i++)
        {
            if(lista.containsKey(i))
            {
                double x1, x2;
                ArrayList<Double> nuevo;
                
                nuevo = lista.get(i);
                
                for(int j = 0; j < nuevo.size(); j++)
                {
                    x1 = nuevo.get(j);
                    
                    Pontos ponto = new Pontos();
                    ponto.setX(x1);
                    ponto.setY(i);
                    pontos.add(ponto);
                }
            }
        }*/
        //
        
        //return pontos;
    }
    //terminou a alteração aqui
    private ArrayList<Double> ordenaLista(ArrayList<Double> nuevo)
    { 
        Collections.sort (nuevo, new Comparator() {
            public int compare(Object o1, Object o2) {
                Double p1 = (Double) o1;
                Double p2 = (Double) o2;
                return p1 < p2 ? -1 : (p1 > p2 ? +1 : 0);
            }
        });
        
        return nuevo;
    }
    
   
}
