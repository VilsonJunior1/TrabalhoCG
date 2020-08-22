/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author vilson
 */
public class Transformacoes3D {
    private Poligono Poligon;

    public Transformacoes3D() {
        this.Poligon = new Poligono();
    }

    public Poligono getPoligon() {
        return Poligon;
    }

    public void setPoligon(Poligono Poligon) {
        this.Poligon = Poligon;
    }
    
    private double calculaRaio(double X, double Y)
    {
        double raio, x, y, funcao, x1, y1;
        
        
        x1 = X;
        x = 0;
        y = 0;
        y1 = Y;
        
        funcao = ((x1)*(x1)) + ((y1)*(y1));
        raio = Math.sqrt(funcao);
        //System.out.println("raio = " + raio);
        
        return raio;
    }
    
    private ArrayList<Pontos> rotacionaPontos(double angulo, double X, double Y, int QtdPontos, double raio)
    {
        ArrayList<Pontos> pontos = new ArrayList<>();
        Pontos ponto = new Pontos();
        double grau = 0;
        
        ponto.setX((X));
        ponto.setY((Y));
        pontos.add(ponto);
        ponto = new Pontos();
        
        for (int i = 0; i < QtdPontos-1; i++)
        {
            grau += angulo;
            //System.out.println("grau = " + grau);
            double cos = 0, sin = 0;
            
            cos = Math.cos(Math.toRadians(grau));
            sin = Math.sin(Math.toRadians(grau));
            
            double x, y;
            
           
            x = (X*cos) + (Y*sin);
            y = (Y*cos) - (X *sin);
            
            ponto.setX(x);
            ponto.setY(y);
            pontos.add(ponto);
            ponto = new Pontos();
        }
        
        return pontos;
    }
    
    private Poligono ligaPontosRevolucaoIgual(Poligono poligono)
    {
        Poligono NovoPoligono = new Poligono();
        
        //NovoPoligono.setContorno(poligono.getContorno());
        //NovoPoligono.setCor(poligono.getCor());
        NovoPoligono.setListaP(poligono.getListaP());
        
        ArrayList<Faces> faces = new ArrayList<>();
        ArrayList<Faces> faceI = new ArrayList<>();
        ArrayList<Faces> faceF = new ArrayList<>();
        ArrayList<Faces> facesResultado = new ArrayList<>();

        int quantidade = poligono.getListaFaces().size();

        for (int i = 0; i < poligono.getListaP().size(); i++)
        {
            if(i < poligono.getListaP().size() - 1)
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i+1).getX());
                ponto.setY(poligono.getListaP().get(i+1).getY());
                ponto.setZ(poligono.getListaP().get(i+1).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceI.add(aux);
            }
            else
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(0).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(0).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(0).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(0).getX());
                ponto.setY(poligono.getListaP().get(0).getY());
                ponto.setZ(poligono.getListaP().get(0).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceI.add(aux);
            }
        }

        int tamanho = poligono.getListaFaces().size()-1;
        
        for (int i = 0; i < poligono.getListaP().size(); i++)
        {
            if(i < poligono.getListaP().size() - 1)
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i+1).getX());
                ponto.setY(poligono.getListaP().get(i+1).getY());
                ponto.setZ(poligono.getListaP().get(i+1).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i+1).getX());
                ponto.setY(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i+1).getY());
                ponto.setZ(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i+1).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceF.add(aux);
            }
            else
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(tamanho).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(0).getX());
                ponto.setY(poligono.getListaP().get(0).getY());
                ponto.setZ(poligono.getListaP().get(0).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(tamanho).getListaPFaces().get(0).getX());
                ponto.setY(poligono.getListaFaces().get(tamanho).getListaPFaces().get(0).getY());
                ponto.setZ(poligono.getListaFaces().get(tamanho).getListaPFaces().get(0).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceF.add(aux);
            }
        }

        for(int j = 0; j < poligono.getListaFaces().size(); j++)
        {
            if(j < poligono.getListaFaces().size() - 1)
            {
                for (int i = 0; i < poligono.getListaP().size(); i++)
                {
                    if(i < poligono.getListaP().size() - 1)
                    {
                        ArrayList<Pontos> lista = new ArrayList<>();

                        Pontos ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getZ());

                        lista.add(ponto);

                        Faces aux = new Faces();

                        aux.setListaPFaces(lista);

                        faces.add(aux);
                    }
                    else
                    {
                        ArrayList<Pontos> lista = new ArrayList<>();

                        Pontos ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(0).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(0).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(0).getZ());

                        lista.add(ponto);

                        Faces aux = new Faces();

                        aux.setListaPFaces(lista);

                        faces.add(aux);
                    }
                }
            }
        }

        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            facesResultado.add(poligono.getListaFaces().get(i));
        }
        for(int i = 0; i < faceI.size(); i++)
        {
            facesResultado.add(faceI.get(i));
        }
        for(int i = 0; i < faces.size(); i++)
        {
            facesResultado.add(faces.get(i));
        }
        for(int i = 0; i < faceF.size(); i++)
        {
            facesResultado.add(faceF.get(i));
        }
        
        NovoPoligono.setListaFaces(facesResultado);
        
        
        return NovoPoligono;
    }
    
    private Poligono ligaPontosRevolucaoMenos(Poligono poligono)
    {
        Poligono NovoPoligono = new Poligono();
        
        //NovoPoligono.setContorno(poligono.getContorno());
        //NovoPoligono.setCor(poligono.getCor());
        NovoPoligono.setListaP(poligono.getListaP());
        
        ArrayList<Faces> faces = new ArrayList<>();
        ArrayList<Faces> faceI = new ArrayList<>();
        ArrayList<Faces> facesResultado = new ArrayList<>();

        int quantidade = poligono.getListaFaces().size();

        for (int i = 0; i < poligono.getListaP().size(); i++)
        {
            if(i < poligono.getListaP().size() - 1)
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i+1).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i+1).getX());
                ponto.setY(poligono.getListaP().get(i+1).getY());
                ponto.setZ(poligono.getListaP().get(i+1).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceI.add(aux);
            }
            else
            {
                ArrayList<Pontos> lista = new ArrayList<>();

                Pontos ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(i).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(i).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(i).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaFaces().get(0).getListaPFaces().get(0).getX());
                ponto.setY(poligono.getListaFaces().get(0).getListaPFaces().get(0).getY());
                ponto.setZ(poligono.getListaFaces().get(0).getListaPFaces().get(0).getZ());

                lista.add(ponto);

                ponto = new Pontos();

                ponto.setX(poligono.getListaP().get(0).getX());
                ponto.setY(poligono.getListaP().get(0).getY());
                ponto.setZ(poligono.getListaP().get(0).getZ());

                lista.add(ponto);

                Faces aux = new Faces();

                aux.setListaPFaces(lista);

                faceI.add(aux);
            }
        }

        for(int j = 0; j < poligono.getListaFaces().size(); j++)
        {
            if(j < poligono.getListaFaces().size() - 1)
            {
                for (int i = 0; i < poligono.getListaP().size(); i++)
                {
                    if(i < poligono.getListaP().size() - 1)
                    {
                        ArrayList<Pontos> lista = new ArrayList<>();

                        Pontos ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i+1).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i+1).getZ());

                        lista.add(ponto);

                        Faces aux = new Faces();

                        aux.setListaPFaces(lista);

                        faces.add(aux);
                    }
                    else
                    {
                        ArrayList<Pontos> lista = new ArrayList<>();

                        Pontos ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(i).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getX());
                        ponto.setY(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getY());
                        ponto.setZ(poligono.getListaFaces().get(j+1).getListaPFaces().get(0).getZ());

                        lista.add(ponto);

                        ponto = new Pontos();

                        ponto.setX(poligono.getListaFaces().get(j).getListaPFaces().get(0).getX());
                        ponto.setY(poligono.getListaFaces().get(j).getListaPFaces().get(0).getY());
                        ponto.setZ(poligono.getListaFaces().get(j).getListaPFaces().get(0).getZ());

                        lista.add(ponto);

                        Faces aux = new Faces();

                        aux.setListaPFaces(lista);

                        faces.add(aux);
                    }
                }
            }
        }

        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            facesResultado.add(poligono.getListaFaces().get(i));
        }
        for(int i = 0; i < faceI.size(); i++)
        {
            facesResultado.add(faceI.get(i));
        }
        for(int i = 0; i < faces.size(); i++)
        {
            facesResultado.add(faces.get(i));
        }
        
        NovoPoligono.setListaFaces(facesResultado);
        
        
        return NovoPoligono;
    }
    
    public Poligono Revolucao(Poligono poligono, double graus, int quantidadePontos, String eixo)
    {
        double XO, YO;
        
        XO = 181;
        YO = 133.75;
        
        ArrayList<ArrayList<Pontos>> listaGeral = new ArrayList<>();
        ArrayList<Faces> faces = new ArrayList<>();
        
        for(int i = 0; i < quantidadePontos; i++)
        {
            ArrayList<Pontos> insere = new ArrayList<>();
            listaGeral.add(insere);
        }
        
        double angulo;
        if(graus == 360)
        {
            angulo = graus/(quantidadePontos);
        }
        else
        {
            angulo = graus/(quantidadePontos-1);
        }
        //System.out.println("angulo " + angulo);
        for(int i = 0; i < poligono.getListaP().size(); i++)
        {
            double X = 0, Y = 0;
            
            if("X".equals(eixo))
            {
                X = poligono.getListaP().get(i).getZ();
                Y = poligono.getListaP().get(i).getY();
            }
            else if("Y".equals(eixo))
            {
                X = poligono.getListaP().get(i).getX();
                Y = poligono.getListaP().get(i).getZ();
            }
            else if("Z".equals(eixo))
            {
                X = poligono.getListaP().get(i).getX();
                Y = poligono.getListaP().get(i).getY();
            }
            
            ArrayList<Pontos> aux;
            double raio;
            raio = calculaRaio(X, Y);
            aux = rotacionaPontos(angulo, X, Y, quantidadePontos, raio);
            
            //System.out.println("X = " + X);
            //System.out.println("Y = " + Y);
            //System.out.println("entrou");
            if("X".equals(eixo))
            {
                for(int j = 0; j < listaGeral.size(); j++)
                {
                    ArrayList<Pontos> auxilio = new ArrayList<>();
                    //auxilio = listaGeral.get(j);
                    for(int x = 0; x < listaGeral.get(j).size(); x++)
                    {
                        Pontos auxP = new Pontos();
                        
                        auxP.setX(listaGeral.get(j).get(x).getX());
                        auxP.setY(listaGeral.get(j).get(x).getY());
                        auxP.setZ(listaGeral.get(j).get(x).getZ());
                        
                        auxilio.add(auxP);
                    }
                    
                    Pontos auxP = new Pontos();
                    
                    auxP.setX(poligono.getListaP().get(i).getX());
                    auxP.setY(aux.get(j).getY());
                    auxP.setZ(aux.get(j).getX());
                    
                    auxilio.add(auxP);
                    listaGeral.set(j, auxilio);
                }
            }
            else if("Y".equals(eixo))
            { 
                for(int j = 0; j < listaGeral.size(); j++)
                {
                    ArrayList<Pontos> auxilio = new ArrayList<>();
                    
                    for(int x = 0; x < listaGeral.get(j).size(); x++)
                    {
                        Pontos auxP = new Pontos();
                        
                        auxP.setX(listaGeral.get(j).get(x).getX());
                        auxP.setY(listaGeral.get(j).get(x).getY());
                        auxP.setZ(listaGeral.get(j).get(x).getZ());
                        
                        auxilio.add(auxP);
                    }
                    
                    Pontos auxP = new Pontos();
                    
                    auxP.setX(aux.get(j).getX());
                    auxP.setY(poligono.getListaP().get(i).getY());
                    auxP.setZ(aux.get(j).getY());
                    
                    auxilio.add(auxP);
                    listaGeral.set(j, auxilio);
                }
            }
            else if("Z".equals(eixo))
            {
                for(int j = 0; j < listaGeral.size(); j++)
                {
                    ArrayList<Pontos> auxilio = new ArrayList<>();
                    
                    for(int x = 0; x < listaGeral.get(j).size(); x++)
                    {
                        Pontos auxP = new Pontos();
                        
                        auxP.setX(listaGeral.get(j).get(x).getX());
                        auxP.setY(listaGeral.get(j).get(x).getY());
                        auxP.setZ(listaGeral.get(j).get(x).getZ());
                        
                        auxilio.add(auxP);
                    }
                    
                    Pontos auxP = new Pontos();
                    
                    auxP.setX(aux.get(j).getX());
                    auxP.setY(aux.get(j).getY());
                    auxP.setZ(poligono.getListaP().get(i).getZ());
                    
                    auxilio.add(auxP);
                    listaGeral.set(j, auxilio);
                }
            }
        }
        
        int contador = listaGeral.size()-1;
        ArrayList<Pontos> aux = new ArrayList<>();
        for(int i = listaGeral.get(contador).size()-1; i >= 0 ; i--)
        {
            Pontos pontoN = new Pontos();

            pontoN.setX(listaGeral.get(contador).get(i).getX());
            pontoN.setY(listaGeral.get(contador).get(i).getY());
            pontoN.setZ(listaGeral.get(contador).get(i).getZ());

            aux.add(pontoN);
        }

        Faces faceAddDepois = new Faces();
        faceAddDepois.setListaPFaces(aux);
        
        for(int i = 1; i < listaGeral.size(); i++)
        {
            Faces face = new Faces();
            
            face.setListaPFaces(listaGeral.get(i));
            faces.add(face);
        }
        
        Poligono NovoPoligono = new Poligono();
        //NovoPoligono.setContorno(poligono.getContorno());
        //NovoPoligono.setCor(poligono.getCor());
        NovoPoligono.setListaP(poligono.getListaP());
        NovoPoligono.setListaFaces(faces);
        
        Poligono Novo;
        
        if (graus == 360)
        {
            Novo = ligaPontosRevolucaoIgual(NovoPoligono);
            
            int quantidade = quantidadePontos-1;
            
            Poligono NewPoligono = new Poligono();
            //NewPoligono.setContorno(Novo.getContorno());
            //NewPoligono.setCor(Novo.getCor());
            //NewPoligono.setListaP(Novo.getListaP());
            
            ArrayList<Faces> facesNew = new ArrayList<>();
            
            for(int i = 0; i < Novo.getListaFaces().size(); i++)
            {
                facesNew.add(Novo.getListaFaces().get(i));
            }
            
            for(int i = 0; i < quantidade; i++)
            {
                facesNew.remove(0);
            }
            
            NewPoligono.setListaP(facesNew.get(0).getListaPFaces());
            facesNew.remove(0);
            NewPoligono.setListaFaces(facesNew);
            
            return NewPoligono;
        }
        else
        {
            Novo = ligaPontosRevolucaoMenos(NovoPoligono);
            
            int quantidade = quantidadePontos-2;
            
            Poligono NewPoligono = new Poligono();
            //NewPoligono.setContorno(Novo.getContorno());
            //NewPoligono.setCor(Novo.getCor());
            NewPoligono.setListaP(Novo.getListaP());
            
            ArrayList<Faces> facesNew = new ArrayList<>();
            
            for(int i = 0; i < Novo.getListaFaces().size(); i++)
            {
                facesNew.add(Novo.getListaFaces().get(i));
            }
            
            for(int i = 0; i < quantidade; i++)
            {
                facesNew.remove(0);
            }
            
            facesNew.set(0, faceAddDepois);
            
            NewPoligono.setListaFaces(facesNew);
            
            return NewPoligono;
        }
    }
    
    public Poligono Extrusao(Poligono poligono, double tamanho)
    {
        Poligono NovoPoligono = new Poligono();
        
        //NovoPoligono.setContorno(poligono.getContorno());
        //NovoPoligono.setCor(poligono.getCor());
        NovoPoligono.setListaP(poligono.getListaP());
        
        Faces faceI = new Faces();
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < poligono.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(poligono.getListaP().get(i).getX());
            ponto.setY(poligono.getListaP().get(i).getY());
            ponto.setZ(poligono.getListaP().get(i).getZ() + tamanho);
            
            pontos.add(ponto);
        }
        
        faceI.setListaPFaces(pontos);
        
        Faces face = new Faces();
        ArrayList<Faces> faces = new ArrayList<>();
        pontos = new ArrayList<>();
        
        for(int i = 0; i < poligono.getListaP().size(); i++)
        {
            if(i < poligono.getListaP().size()-1)
            {
                pontos = new ArrayList<>();
                Pontos ponto = new Pontos();
            
                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(faceI.getListaPFaces().get(i).getX());
                ponto.setY(faceI.getListaPFaces().get(i).getY());
                ponto.setZ(faceI.getListaPFaces().get(i).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(faceI.getListaPFaces().get(i+1).getX());
                ponto.setY(faceI.getListaPFaces().get(i+1).getY());
                ponto.setZ(faceI.getListaPFaces().get(i+1).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(poligono.getListaP().get(i+1).getX());
                ponto.setY(poligono.getListaP().get(i+1).getY());
                ponto.setZ(poligono.getListaP().get(i+1).getZ());
                pontos.add(ponto);
                
                face.setListaPFaces(pontos);
                
                faces.add(face);
                face = new Faces();
            }
            else
            {
                pontos = new ArrayList<>();
                Pontos ponto = new Pontos();
            
                ponto.setX(poligono.getListaP().get(i).getX());
                ponto.setY(poligono.getListaP().get(i).getY());
                ponto.setZ(poligono.getListaP().get(i).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(faceI.getListaPFaces().get(i).getX());
                ponto.setY(faceI.getListaPFaces().get(i).getY());
                ponto.setZ(faceI.getListaPFaces().get(i).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(faceI.getListaPFaces().get(0).getX());
                ponto.setY(faceI.getListaPFaces().get(0).getY());
                ponto.setZ(faceI.getListaPFaces().get(0).getZ());
                pontos.add(ponto);
                
                ponto = new Pontos();
            
                ponto.setX(poligono.getListaP().get(0).getX());
                ponto.setY(poligono.getListaP().get(0).getY());
                ponto.setZ(poligono.getListaP().get(0).getZ());
                pontos.add(ponto);
                
                face.setListaPFaces(pontos);
                
                faces.add(face);
                face = new Faces();
            }
        }
        NovoPoligono.setListaFaces(faces);
        //ArrayList<Faces> ListaFinal = new ArrayList<>();
        
        //Faces aux = new Faces();
            
        //aux.setListaPFaces(faceI.getListaPFaces());
        //ListaFinal.add(aux);
        
        //for(int i = 0; i < faces.size(); i++)
        //{
        //    aux = new Faces();
            
        //    aux.setListaPFaces(faces.get(i).getListaPFaces());
        //    ListaFinal.add(aux);
        //}
        
        //NovoPoligono.setListaFaces(ListaFinal);
        
        return NovoPoligono;
    }
    
    public Poligono NormalizacaoPoligono(Poligono poligono)
    {
        Poligono novo = new Poligono();
        
        return novo;
    }
}
