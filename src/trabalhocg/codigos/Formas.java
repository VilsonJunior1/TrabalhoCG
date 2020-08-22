/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.math.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;
import javafx.scene.paint.Color;
/**
 *
 * @author vilson
 */
public class Formas {
    
    private ArrayList<Poligono> AllPoligonos;
    private ArrayList<Pontos> listaIrregular;
    private Pontos VRP;
    private boolean TelaMaximizada;
    private boolean OcultacaoFaces;
    private boolean Wireframe;
    private ArrayList<FonteLuminosa> fontes = new ArrayList<>();
    private LuzAmbiente luz = new LuzAmbiente();

    public Formas() {
        this.AllPoligonos = new ArrayList<>();
        this.listaIrregular = new ArrayList<>();
        this.TelaMaximizada = false;
        this.OcultacaoFaces = false;
        this.Wireframe = false;
        this.VRP = new Pontos();
        this.VRP.setX(0);
        this.VRP.setY(0);
        this.VRP.setZ(100);
        this.fontes = new ArrayList<>();
        this.luz = new LuzAmbiente();
    }

    public ArrayList<Poligono> getAllPoligonos() {
        return AllPoligonos;
    }

    public void setAllPoligonos(ArrayList<Poligono> AllPoligonos) {
        this.AllPoligonos = AllPoligonos;
    }

    public ArrayList<Pontos> getListaIrregular() {
        return listaIrregular;
    }

    public void setListaIrregular(ArrayList<Pontos> listaIrregular) {
        this.listaIrregular = listaIrregular;
    }

    public Pontos getVRP() {
        return VRP;
    }

    public void setVRP(Pontos VRP) {
        this.VRP = VRP;
    }

    public boolean getTelaMaximizada() {
        return TelaMaximizada;
    }

    public void setTelaMaximizada(boolean TelaMaximizada) {
        this.TelaMaximizada = TelaMaximizada;
    }

    public boolean getOcultacaoFaces() {
        return OcultacaoFaces;
    }

    public void setOcultacaoFaces(boolean OcultacaoFaces) {
        this.OcultacaoFaces = OcultacaoFaces;
    }

    public boolean getWireframe() {
        return Wireframe;
    }

    public void setWireframe(boolean Wireframe) {
        this.Wireframe = Wireframe;
    }

    public ArrayList<FonteLuminosa> getFontes() {
        return fontes;
    }

    public void setFontes(ArrayList<FonteLuminosa> fontes) {
        this.fontes = fontes;
    }

    public LuzAmbiente getLuz() {
        return luz;
    }

    public void setLuz(LuzAmbiente luz) {
        this.luz = luz;
    }
    
    public void impressao(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4)
    {
        Impressao3D imprime = new Impressao3D();
        
        imprime.setAllPoligonos(AllPoligonos);
        imprime.setGc(gc);
        imprime.setGc2(gc2);
        imprime.setGc3(gc3);
        imprime.setGc4(gc4);
        imprime.ChamaTelas(VRP, TelaMaximizada, OcultacaoFaces, Wireframe, fontes, luz);
    }
    
    public void gravar(Poligono poligono)
    {
        /*System.out.println("Pontos:");
        for(int q=0;q<poligono.getListaP().size();q++)
        {
            System.out.println("Ponto["+q+"] x:"+poligono.getListaP().get(q).getX()+"  y:"+poligono.getListaP().get(q).getY());
        }
        System.out.println("");*/
        AllPoligonos.add(poligono);
    }
    
    public Poligono formas(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4, double XO, double YO, double X, double Y, int pontos)
    {
        Pontos auxiliar = new Pontos();
        Poligono poligono = new Poligono();
        ArrayList<Pontos> listaP = new ArrayList<>();
        double x1, y1, x, y;
        double angulo = 0, cos = 0, sin = 0, rad;
        angulo = 360.0/pontos;
        //rad = ((Math.PI*angulo)/180);
        //System.out.println("Angulo"+Math.toRadians(angulo));
        cos = Math.cos(Math.toRadians(angulo));
        sin = Math.sin(Math.toRadians(angulo));
        
        x1 = X - XO;
        y1 = Y - YO;
        
        auxiliar.setX((x1+XO));
        auxiliar.setY((y1+YO));
        listaP.add(auxiliar);
        auxiliar = new Pontos();
        for (int i = 0; i < pontos-1; i++)
        {
            x = (x1*cos) + (y1*sin);//talvez seja ao contrario so deus sabe
            y = (y1*cos) - (x1 *sin);
            auxiliar.setX((x+XO));
            auxiliar.setY((y+YO));
            listaP.add(auxiliar);
            auxiliar = new Pontos();
            x1 = x;
            y1 = y;
        }
        poligono.setListaP(listaP);//pode dar erro so deus sabe
        //poligono.setContorno(contorno);
        
        Impressao3D imprime = new Impressao3D();
        ArrayList<Poligono> auxilio = new ArrayList<>();
        auxilio.add(poligono);
        imprime.setAllPoligonos(auxilio);
        imprime.setGc(gc);
        imprime.setGc2(gc2);
        imprime.setGc3(gc3);
        imprime.setGc4(gc4);
        imprime.ChamaTelas(VRP, TelaMaximizada, OcultacaoFaces, Wireframe, fontes, luz);
        
        /*gc.beginPath();
        gc.setStroke(Color.web(contorno));
        gc.setLineWidth(2);
        gc.moveTo(listaP.get(0).getX(), listaP.get(0).getY());
  
        for (int i = 1; i < listaP.size(); i++)
        {
            gc.lineTo(listaP.get(i).getX(), listaP.get(i).getY());
           
        }
        gc.lineTo(listaP.get(0).getX(), listaP.get(0).getY());
       
        gc.stroke();*/
        
        return poligono;
    }
    
    public int selecionaObjeto3(double X, double Y)
    {
        int indice = -1, tamanho = AllPoligonos.size();
        boolean achou = false;
        //System.out.println("X = "+X+"\nY="+Y);
        for(int i = tamanho-1; i >= 0; i--)
        {
            if (achou == false)
            {
                //achou = PontoNoPoligono(AllPoligonos.get(i).getListaP(), X, Y);
                achou = PontoPoucaDistanciaPoligono3(AllPoligonos.get(i).getListaP(), X, Y);
            
                if (achou == true)
                {
                    indice = i;
                    return indice;
                }
                else
                {
                    for(int j = 0; j < AllPoligonos.get(i).getListaFaces().size(); j++)
                    {
                        achou = PontoPoucaDistanciaPoligono3(AllPoligonos.get(i).getListaFaces().get(j).getListaPFaces(), X, Y);
                        if(achou == true)
                        {
                            indice = i;
                            return indice;
                        }
                    }
                }
            }
        }
        return indice;
    }
    
    public int selecionaObjeto2(double X, double Y)
    {
        int indice = -1, tamanho = AllPoligonos.size();
        boolean achou = false;
        //System.out.println("X = "+X+"\nY="+Y);
        for(int i = tamanho-1; i >= 0; i--)
        {
            if (achou == false)
            {
                //achou = PontoNoPoligono(AllPoligonos.get(i).getListaP(), X, Y);
                achou = PontoPoucaDistanciaPoligono2(AllPoligonos.get(i).getListaP(), X, Y);
            
                if (achou == true)
                {
                    indice = i;
                    return indice;
                }
                else
                {
                    for(int j = 0; j < AllPoligonos.get(i).getListaFaces().size(); j++)
                    {
                        achou = PontoPoucaDistanciaPoligono2(AllPoligonos.get(i).getListaFaces().get(j).getListaPFaces(), X, Y);
                        if(achou == true)
                        {
                            indice = i;
                            return indice;
                        }
                    }
                }
            }
        }
        return indice;
    }
    
    public int selecionaObjeto(double X, double Y)
    {
        int indice = -1, tamanho = AllPoligonos.size();
        boolean achou = false;
        //System.out.println("X = "+X+"\nY="+Y);
        for(int i = tamanho-1; i >= 0; i--)
        {
            if (achou == false)
            {
                //achou = PontoNoPoligono(AllPoligonos.get(i).getListaP(), X, Y);
                achou = PontoPoucaDistanciaPoligono(AllPoligonos.get(i).getListaP(), X, Y);
            
                if (achou == true)
                {
                    indice = i;
                    return indice;
                }
                else
                {
                    for(int j = 0; j < AllPoligonos.get(i).getListaFaces().size(); j++)
                    {
                        achou = PontoPoucaDistanciaPoligono(AllPoligonos.get(i).getListaFaces().get(j).getListaPFaces(), X, Y);
                        if(achou == true)
                        {
                            indice = i;
                            return indice;
                        }
                    }
                }
            }
        }
        return indice;
    }
    
    public boolean PontoPoucaDistanciaPoligono3(ArrayList<Pontos> listaP, double X, double Y)
    {
        boolean achou = false;
        Pontos p1 = new Pontos();
        Pontos p2 = new Pontos();
        for (int i = 0; i < listaP.size(); i++)
        {
            p1.setX(listaP.get(i).getZ());
            p1.setY(listaP.get(i).getY());
            if(i == listaP.size()-1)
            {
                p2.setX(listaP.get(0).getZ());
                p2.setY(listaP.get(0).getY());
            }
            else
            {
                p2.setX(listaP.get(i+1).getZ());
                p2.setY(listaP.get(i+1).getY());
            }
            if (achou == false)
            {
                achou = distanciaPontoReta(X, Y, p1, p2);
                if(achou == true)
                {
                    return achou;
                }
            }
            p1 = new Pontos();
            p2 = new Pontos();
        }
        return achou;
    }
    
    public boolean PontoPoucaDistanciaPoligono2(ArrayList<Pontos> listaP, double X, double Y)
    {
        boolean achou = false;
        Pontos p1 = new Pontos();
        Pontos p2 = new Pontos();
        for (int i = 0; i < listaP.size(); i++)
        {
            p1.setX(listaP.get(i).getX());
            p1.setY(listaP.get(i).getZ());
            if(i == listaP.size()-1)
            {
                p2.setX(listaP.get(0).getX());
                p2.setY(listaP.get(0).getZ());
            }
            else
            {
                p2.setX(listaP.get(i+1).getX());
                p2.setY(listaP.get(i+1).getZ());
            }
            if (achou == false)
            {
                achou = distanciaPontoReta(X, Y, p1, p2);
                if(achou == true)
                {
                    return achou;
                }
            }
            p1 = new Pontos();
            p2 = new Pontos();
        }
        return achou;
    }
    
    public boolean PontoPoucaDistanciaPoligono(ArrayList<Pontos> listaP, double X, double Y)
    {
        boolean achou = false;
        Pontos p1 = new Pontos();
        Pontos p2 = new Pontos();
        for (int i = 0; i < listaP.size(); i++)
        {
            p1.setX(listaP.get(i).getX());
            p1.setY(listaP.get(i).getY());
            if(i == listaP.size()-1)
            {
                p2.setX(listaP.get(0).getX());
                p2.setY(listaP.get(0).getY());
            }
            else
            {
                p2.setX(listaP.get(i+1).getX());
                p2.setY(listaP.get(i+1).getY());
            }
            if (achou == false)
            {
                achou = distanciaPontoReta(X, Y, p1, p2);
                if(achou == true)
                {
                    return achou;
                }
            }
            p1 = new Pontos();
            p2 = new Pontos();
        }
        return achou;
    }
    
    public boolean PontoNoPoligono(ArrayList<Pontos> listaP, double X, double Y)
    {
        int contador = 0;
        int pontos = listaP.size();
        
        double xinters;
        
        Pontos p1 = new Pontos();
        Pontos p2 = new Pontos();
        
        p1.setX(listaP.get(0).getX());
        p1.setY(listaP.get(0).getY());
        for (int i = 0; i < pontos; i++)
        {
            p2.setX(listaP.get((i+1) % pontos).getX());
            p2.setY(listaP.get((i+1) % pontos).getY());
            
            if(Y > Math.min(p1.getY(), p2.getY()))
            {
                if(Y <= Math.max(p1.getY(), p2.getY()))
                {
                    if(X <= Math.max(p1.getX(), p2.getX()))
                    {
                        if(p1.getX() != p2.getY())
                        {
                            xinters = (Y - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) + p1.getX();
                            if (p1.getX() == p2.getX() || X <= xinters)
                            {
                                contador++;
                            }
                        }
                    }
                }
            }
            p1.setX(p2.getX());
            p1.setY(p2.getY());
            p2 = new Pontos();
        }
        
        boolean pontoNoPoligono;
        
        if(contador % 2 == 0)
        {
            pontoNoPoligono = false;
        }
        else
        {
            pontoNoPoligono = true;
        }
        
        
        return pontoNoPoligono;
    }
    
    public boolean distanciaPontoReta(double X, double Y, Pontos ponto1, Pontos ponto2)
    {
        double L = 0;
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        x1 = ponto1.getX();
        y1 = ponto1.getY();
        x2 = ponto2.getX();
        y2 = ponto2.getY();
        L = distanceToLineSegment(x1, y1, x2, y2, X, Y);
        
        boolean distancia;
        if (L <= 3.0)
        {
            distancia = true;
        }
        else
        {
            distancia = false;
        }
        
        return distancia;
    }
    
    public double distanceToLineSegment(double lx1,double ly1,double lx2,double ly2, double px, double py)
    {
        return Math.sqrt(distanceSquaredToLineSegment(lx1, ly1, lx2, ly2, px, py));
    }
    
    public double distanceSquaredToLineSegment(double lx1,double ly1,double lx2,double ly2,double px,double py)
    {
        double ldx = lx2 - lx1;
        double ldy = ly2 - ly1;
        double lineLengthSquared = ldx*ldx + ldy*ldy;
        return distanceSquaredToLineSegment2(lx1, ly1, ldx, ldy, lineLengthSquared, px, py);
    }
    
    public double distanceSquaredToLineSegment2(double lx1,double ly1,double ldx,double ldy,double lineLengthSquared,double px, double py)
    {
        double t;
        /*if(!lineLengthSquared)
        {
            t = 0;
        }*/
        if(lineLengthSquared == 0)
        {
            t = 0;
        }
        else
        {
            t = ((px - lx1) * ldx + (py - ly1) * ldy) / lineLengthSquared;
            
            if(t < 0)
            {
                t = 0;
            }
            else if (t > 1)
            {
                t = 1;
            }
        }
        
        double lx = lx1 + t * ldx;
        double ly = ly1 + t * ldy;
        double dx = px - lx;
        double dy = py - ly;
        
        return dx*dx + dy*dy;
    }
    
    public void selecionado(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4, int indice, String tela)
    {
        if(TelaMaximizada == false)
        {
            if(tela.equals("XY"))
            {
                gc.beginPath();
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(2);
                gc.moveTo(AllPoligonos.get(indice).getListaP().get(0).getX()+181, AllPoligonos.get(indice).getListaP().get(0).getY()+133.75);

                for (int i = 1; i < AllPoligonos.get(indice).getListaP().size(); i++)
                {
                    gc.lineTo(AllPoligonos.get(indice).getListaP().get(i).getX()+181, AllPoligonos.get(indice).getListaP().get(i).getY()+133.75);

                }
                gc.lineTo(AllPoligonos.get(indice).getListaP().get(0).getX()+181, AllPoligonos.get(indice).getListaP().get(0).getY()+133.75);

                gc.stroke();

                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc.beginPath();
                        gc.setStroke(Color.BLUE);
                        gc.setLineWidth(2);
                        gc.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc.lineTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                        }
                        gc.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        gc.stroke();
                    }
                }
            }
            else if(tela.equals("XZ"))
            {
                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc2.beginPath();
                        gc2.setStroke(Color.BLUE);
                        gc2.setLineWidth(2);
                        gc2.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ() + 133.75);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc2.lineTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getZ() + 133.75);
                        }
                        gc2.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()+181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ() + 133.75);
                        gc2.stroke();
                    }
                }

                gc2.beginPath();
                gc2.setStroke(Color.BLUE);
                gc2.setLineWidth(2);
                gc2.moveTo(AllPoligonos.get(indice).getListaP().get(0).getX()+181, AllPoligonos.get(indice).getListaP().get(0).getZ() + 133.75);
                for (int j = 1; j < AllPoligonos.get(indice).getListaP().size(); j++)
                {
                    gc2.lineTo(AllPoligonos.get(indice).getListaP().get(j).getX()+181, AllPoligonos.get(indice).getListaP().get(j).getZ() + 133.75);
                }
                gc2.lineTo(AllPoligonos.get(indice).getListaP().get(0).getX()+181, AllPoligonos.get(indice).getListaP().get(0).getZ() + 133.75);
                gc2.stroke();
            }
            else if(tela.equals("YZ"))
            {
                gc3.beginPath();
                gc3.setStroke(Color.BLUE);
                gc3.setLineWidth(2);
                gc3.moveTo(AllPoligonos.get(indice).getListaP().get(0).getZ() + 181, AllPoligonos.get(indice).getListaP().get(0).getY() + 133.75);
                for (int j = 1; j < AllPoligonos.get(indice).getListaP().size(); j++)
                {
                    gc3.lineTo(AllPoligonos.get(indice).getListaP().get(j).getZ() + 181, AllPoligonos.get(indice).getListaP().get(j).getY() + 133.75);
                }
                gc3.lineTo(AllPoligonos.get(indice).getListaP().get(0).getZ() + 181, AllPoligonos.get(indice).getListaP().get(0).getY() + 133.75);
                gc3.stroke();

                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc3.beginPath();
                        gc3.setStroke(Color.BLUE);
                        gc3.setLineWidth(2);
                        gc3.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ() + 181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc3.lineTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getZ() + 181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                        }
                        gc3.moveTo(AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ() + 181, AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        gc3.stroke();
                    }
                }
            }
        }
        else
        {
            if(tela.equals("XY"))
            {
                gc.beginPath();
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(2);
                gc.moveTo((AllPoligonos.get(indice).getListaP().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaP().get(0).getY()*2)+267.5);

                for (int i = 1; i < AllPoligonos.get(indice).getListaP().size(); i++)
                {
                    gc.lineTo((AllPoligonos.get(indice).getListaP().get(i).getX()*2)+362, (AllPoligonos.get(indice).getListaP().get(i).getY()*2)+267.5);

                }
                gc.lineTo((AllPoligonos.get(indice).getListaP().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaP().get(0).getY()*2)+267.5);

                gc.stroke();

                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc.beginPath();
                        gc.setStroke(Color.BLUE);
                        gc.setLineWidth(2);
                        gc.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc.lineTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getY()*2) + 267.5);
                        }
                        gc.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                        gc.stroke();
                    }
                }
            }
            else if(tela.equals("XZ"))
            {
                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc2.beginPath();
                        gc2.setStroke(Color.BLUE);
                        gc2.setLineWidth(2);
                        gc2.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 267.5);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc2.lineTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getZ()*2) + 267.5);
                        }
                        gc2.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 267.5);
                        gc2.stroke();
                    }
                }

                gc2.beginPath();
                gc2.setStroke(Color.BLUE);
                gc2.setLineWidth(2);
                gc2.moveTo(AllPoligonos.get(indice).getListaP().get(0).getX()+362, AllPoligonos.get(indice).getListaP().get(0).getZ() + 267.5);
                for (int j = 1; j < AllPoligonos.get(indice).getListaP().size(); j++)
                {
                    gc2.lineTo(AllPoligonos.get(indice).getListaP().get(j).getX()+362, AllPoligonos.get(indice).getListaP().get(j).getZ() + 267.5);
                }
                gc2.lineTo(AllPoligonos.get(indice).getListaP().get(0).getX()+362, AllPoligonos.get(indice).getListaP().get(0).getZ() + 267.5);
                gc2.stroke();
            }
            else if(tela.equals("YZ"))
            {
                gc3.beginPath();
                gc3.setStroke(Color.BLUE);
                gc3.setLineWidth(2);
                gc3.moveTo((AllPoligonos.get(indice).getListaP().get(0).getZ()*2) + 362, (AllPoligonos.get(indice).getListaP().get(0).getY()*2) + 267.5);
                for (int j = 1; j < AllPoligonos.get(indice).getListaP().size(); j++)
                {
                    gc3.lineTo((AllPoligonos.get(indice).getListaP().get(j).getZ()*2) + 362, (AllPoligonos.get(indice).getListaP().get(j).getY()*2) + 267.5);
                }
                gc3.lineTo((AllPoligonos.get(indice).getListaP().get(0).getZ()*2) + 362, (AllPoligonos.get(indice).getListaP().get(0).getY()*2) + 267.5);
                gc3.stroke();

                for(int i = 0; i < AllPoligonos.get(indice).getListaFaces().size(); i++)
                {
                    for(int j = 0; j < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc3.beginPath();
                        gc3.setStroke(Color.BLUE);
                        gc3.setLineWidth(2);
                        gc3.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                        for (int x = 1; x < AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc3.lineTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getZ()*2) + 362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(x).getY()*2) + 267.5);
                        }
                        gc3.moveTo((AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 362, (AllPoligonos.get(indice).getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                        gc3.stroke();
                    }
                }
            }
        }
    }
    
    public void Irregular(double X, double Y)
    {
        Pontos auxilio = new Pontos();
        auxilio.setX(X);
        auxilio.setY(Y);
        listaIrregular.add(auxilio);
    }
    
    public void imprimeIrregular(GraphicsContext gc)
    {
        if(TelaMaximizada == false)
        {
            gc.beginPath();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.moveTo(listaIrregular.get(0).getX() + 181, listaIrregular.get(0).getY() + 133.75);

            for (int i = 1; i < listaIrregular.size(); i++)
            {
                gc.lineTo(listaIrregular.get(i).getX() + 181, listaIrregular.get(i).getY() + 133.75);

            }
            //gc.lineTo(listaIrregular.get(0).getX(), listaIrregular.get(0).getY());

            gc.stroke();
        }
        else
        {
            gc.beginPath();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.moveTo(listaIrregular.get(0).getX() + 362, listaIrregular.get(0).getY() + 267.5);

            for (int i = 1; i < listaIrregular.size(); i++)
            {
                gc.lineTo(listaIrregular.get(i).getX() + 362, listaIrregular.get(i).getY() + 267.5);

            }
            //gc.lineTo(listaIrregular.get(0).getX(), listaIrregular.get(0).getY());

            gc.stroke();
        }
    }
    
    public void imprimeTranformacao(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4, Poligono poligono)
    {
        if(TelaMaximizada == false)
        {
            gc.beginPath();
            gc.setStroke(Color.GOLD);
            gc.setLineWidth(2);
            gc.moveTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getY()+133.75);

            for (int i = 1; i < poligono.getListaP().size(); i++)
            {
                gc.lineTo(poligono.getListaP().get(i).getX()+181, poligono.getListaP().get(i).getY()+133.75);

            }
            gc.lineTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getY()+133.75);

            gc.stroke();

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc.beginPath();
                    gc.setStroke(Color.GOLD);
                    gc.setLineWidth(2);
                    gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                    }
                    gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                    gc.stroke();
                }
            }

            //tela2

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc2.beginPath();
                    gc2.setStroke(Color.GOLD);
                    gc2.setLineWidth(2);
                    gc2.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 133.75);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc2.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ() + 133.75);
                    }
                    gc2.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 133.75);
                    gc2.stroke();
                }
            }

            gc2.beginPath();
            gc2.setStroke(Color.GOLD);
            gc2.setLineWidth(2);
            gc2.moveTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getZ() + 133.75);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc2.lineTo(poligono.getListaP().get(j).getX()+181, poligono.getListaP().get(j).getZ() + 133.75);
            }
            gc2.lineTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getZ() + 133.75);
            gc2.stroke();

            //tela3

            gc3.beginPath();
            gc3.setStroke(Color.GOLD);
            gc3.setLineWidth(2);
            gc3.moveTo(poligono.getListaP().get(0).getZ() + 181, poligono.getListaP().get(0).getY() + 133.75);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc3.lineTo(poligono.getListaP().get(j).getZ() + 181, poligono.getListaP().get(j).getY() + 133.75);
            }
            gc3.lineTo(poligono.getListaP().get(0).getZ() + 181, poligono.getListaP().get(0).getY() + 133.75);
            gc3.stroke();

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc3.beginPath();
                    gc3.setStroke(Color.GOLD);
                    gc3.setLineWidth(2);
                    gc3.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc3.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ() + 181, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                    }
                    gc3.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                    gc3.stroke();
                }
            }
        }
        else
        {
            gc.beginPath();
            gc.setStroke(Color.GOLD);
            gc.setLineWidth(2);
            gc.moveTo((poligono.getListaP().get(0).getX()*2)+362, (poligono.getListaP().get(0).getY()*2)+267.5);

            for (int i = 1; i < poligono.getListaP().size(); i++)
            {
                gc.lineTo((poligono.getListaP().get(i).getX()*2)+362, (poligono.getListaP().get(i).getY()*2)+267.5);

            }
            gc.lineTo((poligono.getListaP().get(0).getX()*2)+362, (poligono.getListaP().get(0).getY()*2)+267.5);

            gc.stroke();

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc.beginPath();
                    gc.setStroke(Color.GOLD);
                    gc.setLineWidth(2);
                    gc.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc.lineTo((poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(x).getY()*2) + 267.5);
                    }
                    gc.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                    gc.stroke();
                }
            }

            //tela2

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc2.beginPath();
                    gc2.setStroke(Color.GOLD);
                    gc2.setLineWidth(2);
                    gc2.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 267.5);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc2.lineTo((poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ()*2) + 267.5);
                    }
                    gc2.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()*2)+362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 267.5);
                    gc2.stroke();
                }
            }

            gc2.beginPath();
            gc2.setStroke(Color.GOLD);
            gc2.setLineWidth(2);
            gc2.moveTo((poligono.getListaP().get(0).getX()*2)+362, (poligono.getListaP().get(0).getZ()*2) + 267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc2.lineTo((poligono.getListaP().get(j).getX()*2)+362, (poligono.getListaP().get(j).getZ()*2) + 267.5);
            }
            gc2.lineTo((poligono.getListaP().get(0).getX()*2)+362, (poligono.getListaP().get(0).getZ()*2) + 267.5);
            gc2.stroke();

            //tela3

            gc3.beginPath();
            gc3.setStroke(Color.GOLD);
            gc3.setLineWidth(2);
            gc3.moveTo((poligono.getListaP().get(0).getZ()*2) + 362, (poligono.getListaP().get(0).getY()*2) + 267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc3.lineTo((poligono.getListaP().get(j).getZ()*2) + 362, (poligono.getListaP().get(j).getY()*2) + 267.5);
            }
            gc3.lineTo((poligono.getListaP().get(0).getZ()*2) + 362, (poligono.getListaP().get(0).getY()*2) + 267.5);
            gc3.stroke();

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc3.beginPath();
                    gc3.setStroke(Color.GOLD);
                    gc3.setLineWidth(2);
                    gc3.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc3.lineTo((poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ()*2) + 362, (poligono.getListaFaces().get(i).getListaPFaces().get(x).getY()*2) + 267.5);
                    }
                    gc3.moveTo((poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ()*2) + 362, (poligono.getListaFaces().get(i).getListaPFaces().get(0).getY()*2) + 267.5);
                    gc3.stroke();
                }
            }
        }
    }
    
    public void imprimePControleEscala(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4, Poligono poligono)
    {
        if(TelaMaximizada == false)
        {
            double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin;
        
            Xmax = poligono.getListaP().get(0).getX();
            Xmin = poligono.getListaP().get(0).getX();
            Ymax = poligono.getListaP().get(0).getY();
            Ymin = poligono.getListaP().get(0).getY();
            Zmax = poligono.getListaP().get(0).getZ();
            Zmin = poligono.getListaP().get(0).getZ();
            for (int i = 0; i < poligono.getListaP().size(); i++)
            {
                if (Xmax < poligono.getListaP().get(i).getX())
                {
                    Xmax = poligono.getListaP().get(i).getX();
                }
                if (Xmin > poligono.getListaP().get(i).getX())
                {
                    Xmin = poligono.getListaP().get(i).getX();
                }
                if (Ymax < poligono.getListaP().get(i).getY())
                {
                    Ymax = poligono.getListaP().get(i).getY();
                }
                if (Ymin > poligono.getListaP().get(i).getY())
                {
                    Ymin = poligono.getListaP().get(i).getY();
                }
                if (Zmax < poligono.getListaP().get(i).getZ())
                {
                    Zmax = poligono.getListaP().get(i).getZ();
                }
                if (Zmin > poligono.getListaP().get(i).getZ())
                {
                    Zmin = poligono.getListaP().get(i).getZ();
                }
            }
            if(poligono.getListaFaces().isEmpty() == false)
            {
                for (int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        if (Xmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Xmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Ymax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Ymin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Zmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                        {
                            Zmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                        }
                        if (Zmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                        {
                            Zmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                        }
                    }
                }
            }

            double Xmedio, Ymedio, Zmedio;
            Xmedio = ((Xmax - Xmin)/2) + Xmin;
            Ymedio = ((Ymax - Ymin)/2) + Ymin;
            Zmedio = ((Zmax - Zmin)/2) + Zmin;

            gc.setFill(Color.BLACK);

            gc.fillRect(181+Xmin-8, 133.75+Ymax+8, 5, 5);
            gc.fillRect(181+Xmedio, 133.75+Ymax+8, 5, 5);
            gc.fillRect(181+Xmax+8, 133.75+Ymax+8, 5, 5);

            gc.fillRect(181+Xmin-8, 133.75+Ymedio, 5, 5);
            gc.fillRect(181+Xmax+8, 133.75+Ymedio, 5, 5);

            gc.fillRect(181+Xmin-8, 133.75+Ymin-8, 5, 5);
            gc.fillRect(181+Xmedio, 133.75+Ymin-8, 5, 5);

            gc.setFill(Color.RED);
            gc.fillRect(181+Xmax+8, 133.75+Ymin-8, 5, 5);

            gc2.setFill(Color.BLACK);

            gc2.fillRect(181+Xmin-8, 133.75+Zmax+8, 5, 5);
            gc2.fillRect(181+Xmedio, 133.75+Zmax+8, 5, 5);
            gc2.fillRect(181+Xmax+8, 133.75+Zmax+8, 5, 5);

            gc2.fillRect(181+Xmin-8, 133.75+Zmedio, 5, 5);
            gc2.fillRect(181+Xmax+8, 133.75+Zmedio, 5, 5);

            gc2.fillRect(181+Xmin-8, 133.75+Zmin-8, 5, 5);
            gc2.fillRect(181+Xmedio, 133.75+Zmin-8, 5, 5);

            gc2.setFill(Color.RED);
            gc2.fillRect(181+Xmax+8, 133.75+Zmin-8, 5, 5);

            gc3.setFill(Color.BLACK);

            gc3.fillRect(181+Zmin-8, 133.75+Ymax+8, 5, 5);
            gc3.fillRect(181+Zmedio, 133.75+Ymax+8, 5, 5);
            gc3.fillRect(181+Zmax+8, 133.75+Ymax+8, 5, 5);

            gc3.fillRect(181+Zmin-8, 133.75+Ymedio, 5, 5);
            gc3.fillRect(181+Zmax+8, 133.75+Ymedio, 5, 5);

            gc3.fillRect(181+Zmin-8, 133.75+Ymin-8, 5, 5);
            gc3.fillRect(181+Zmedio, 133.75+Ymin-8, 5, 5);

            gc3.setFill(Color.RED);
            gc3.fillRect(181+Zmax+8, 133.75+Ymin-8, 5, 5);
        }
        else
        {
            double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin;
        
            Xmax = poligono.getListaP().get(0).getX();
            Xmin = poligono.getListaP().get(0).getX();
            Ymax = poligono.getListaP().get(0).getY();
            Ymin = poligono.getListaP().get(0).getY();
            Zmax = poligono.getListaP().get(0).getZ();
            Zmin = poligono.getListaP().get(0).getZ();
            for (int i = 0; i < poligono.getListaP().size(); i++)
            {
                if (Xmax < poligono.getListaP().get(i).getX())
                {
                    Xmax = poligono.getListaP().get(i).getX();
                }
                if (Xmin > poligono.getListaP().get(i).getX())
                {
                    Xmin = poligono.getListaP().get(i).getX();
                }
                if (Ymax < poligono.getListaP().get(i).getY())
                {
                    Ymax = poligono.getListaP().get(i).getY();
                }
                if (Ymin > poligono.getListaP().get(i).getY())
                {
                    Ymin = poligono.getListaP().get(i).getY();
                }
                if (Zmax < poligono.getListaP().get(i).getZ())
                {
                    Zmax = poligono.getListaP().get(i).getZ();
                }
                if (Zmin > poligono.getListaP().get(i).getZ())
                {
                    Zmin = poligono.getListaP().get(i).getZ();
                }
            }
            if(poligono.getListaFaces().isEmpty() == false)
            {
                for (int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        if (Xmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Xmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Ymax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Ymin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Zmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                        {
                            Zmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                        }
                        if (Zmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                        {
                            Zmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                        }
                    }
                }
            }

            Xmax = Xmax*2;
            Xmin = Xmin*2;
            Ymax = Ymax*2;
            Ymin = Ymin*2;
            Zmax = Zmax*2;
            Zmin = Zmin*2;
            double Xmedio, Ymedio, Zmedio;
            Xmedio = ((Xmax - Xmin)/2) + Xmin;
            Ymedio = ((Ymax - Ymin)/2) + Ymin;
            Zmedio = ((Zmax - Zmin)/2) + Zmin;

            gc.setFill(Color.BLACK);

            gc.fillRect(362+Xmin-8, 267.5+Ymax+8, 5, 5);
            gc.fillRect(362+Xmedio, 267.5+Ymax+8, 5, 5);
            gc.fillRect(362+Xmax+8, 267.5+Ymax+8, 5, 5);

            gc.fillRect(362+Xmin-8, 267.5+Ymedio, 5, 5);
            gc.fillRect(362+Xmax+8, 267.5+Ymedio, 5, 5);

            gc.fillRect(362+Xmin-8, 267.5+Ymin-8, 5, 5);
            gc.fillRect(362+Xmedio, 267.5+Ymin-8, 5, 5);

            gc.setFill(Color.RED);
            gc.fillRect(362+Xmax+8, 267.5+Ymin-8, 5, 5);

            gc2.setFill(Color.BLACK);

            gc2.fillRect(362+Xmin-8, 267.5+Zmax+8, 5, 5);
            gc2.fillRect(362+Xmedio, 267.5+Zmax+8, 5, 5);
            gc2.fillRect(362+Xmax+8, 267.5+Zmax+8, 5, 5);

            gc2.fillRect(362+Xmin-8, 267.5+Zmedio, 5, 5);
            gc2.fillRect(362+Xmax+8, 267.5+Zmedio, 5, 5);

            gc2.fillRect(362+Xmin-8, 267.5+Zmin-8, 5, 5);
            gc2.fillRect(362+Xmedio, 267.5+Zmin-8, 5, 5);

            gc2.setFill(Color.RED);
            gc2.fillRect(362+Xmax+8, 267.5+Zmin-8, 5, 5);

            gc3.setFill(Color.BLACK);

            gc3.fillRect(362+Zmin-8, 267.5+Ymax+8, 5, 5);
            gc3.fillRect(362+Zmedio, 267.5+Ymax+8, 5, 5);
            gc3.fillRect(362+Zmax+8, 267.5+Ymax+8, 5, 5);

            gc3.fillRect(362+Zmin-8, 267.5+Ymedio, 5, 5);
            gc3.fillRect(362+Zmax+8, 267.5+Ymedio, 5, 5);

            gc3.fillRect(362+Zmin-8, 267.5+Ymin-8, 5, 5);
            gc3.fillRect(362+Zmedio, 267.5+Ymin-8, 5, 5);

            gc3.setFill(Color.RED);
            gc3.fillRect(362+Zmax+8, 267.5+Ymin-8, 5, 5);
        }
    }
    
    public void imprimePControleCisalhamento(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3, GraphicsContext gc4, Poligono poligono)
    {
        if(TelaMaximizada == false)
        {
            double Xmax, Xmin, Ymax, Ymin;
        
            Xmax = poligono.getListaP().get(0).getX();
            Xmin = poligono.getListaP().get(0).getX();
            Ymax = poligono.getListaP().get(0).getY();
            Ymin = poligono.getListaP().get(0).getY();
            for (int i = 0; i < poligono.getListaP().size(); i++)
            {
                if (Xmax < poligono.getListaP().get(i).getX())
                {
                    Xmax = poligono.getListaP().get(i).getX();
                }
                if (Xmin > poligono.getListaP().get(i).getX())
                {
                    Xmin = poligono.getListaP().get(i).getX();
                }
                if (Ymax < poligono.getListaP().get(i).getY())
                {
                    Ymax = poligono.getListaP().get(i).getY();
                }
                if (Ymin > poligono.getListaP().get(i).getY())
                {
                    Ymin = poligono.getListaP().get(i).getY();
                }
            }
            if(poligono.getListaFaces().isEmpty() == false)
            {
                for (int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        if (Xmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Xmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Ymax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Ymin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                    }
                }
            }

            double Xmedio, Ymedio, Zmedio;
            Xmedio = ((Xmax - Xmin)/2) + Xmin;
            Ymedio = ((Ymax - Ymin)/2) + Ymin;

            gc.setFill(Color.BLACK);

            gc.fillRect(181+Xmedio, 133.75+Ymax+8, 8, 4);

            gc.fillRect(181+Xmin-8, 133.75+Ymedio, 4, 8);
            gc.fillRect(181+Xmax+8, 133.75+Ymedio, 4, 8);

            gc.fillRect(181+Xmedio, 133.75+Ymin-8, 8, 4);
        }
        else
        {
            double Xmax, Xmin, Ymax, Ymin;
        
            Xmax = poligono.getListaP().get(0).getX();
            Xmin = poligono.getListaP().get(0).getX();
            Ymax = poligono.getListaP().get(0).getY();
            Ymin = poligono.getListaP().get(0).getY();
            for (int i = 0; i < poligono.getListaP().size(); i++)
            {
                if (Xmax < poligono.getListaP().get(i).getX())
                {
                    Xmax = poligono.getListaP().get(i).getX();
                }
                if (Xmin > poligono.getListaP().get(i).getX())
                {
                    Xmin = poligono.getListaP().get(i).getX();
                }
                if (Ymax < poligono.getListaP().get(i).getY())
                {
                    Ymax = poligono.getListaP().get(i).getY();
                }
                if (Ymin > poligono.getListaP().get(i).getY())
                {
                    Ymin = poligono.getListaP().get(i).getY();
                }
            }
            if(poligono.getListaFaces().isEmpty() == false)
            {
                for (int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        if (Xmax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Xmin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getX())
                        {
                            Xmin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                        }
                        if (Ymax < poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymax = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                        if (Ymin > poligono.getListaFaces().get(i).getListaPFaces().get(j).getY())
                        {
                            Ymin = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                        }
                    }
                }
            }
            
            Xmax = Xmax * 2;
            Xmin = Xmin * 2;
            Ymax = Ymax * 2;
            Ymin = Ymin * 2;

            double Xmedio, Ymedio, Zmedio;
            Xmedio = ((Xmax - Xmin)/2) + Xmin;
            Ymedio = ((Ymax - Ymin)/2) + Ymin;

            gc.setFill(Color.BLACK);

            gc.fillRect(362+Xmedio, 267.5+Ymax+8, 8, 4);

            gc.fillRect(362+Xmin-8, 267.5+Ymedio, 4, 8);
            gc.fillRect(362+Xmax+8, 267.5+Ymedio, 4, 8);

            gc.fillRect(362+Xmedio, 267.5+Ymin-8, 8, 4);
        }
    }    
}
