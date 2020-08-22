/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author vilson
 */
public class Impressao3D {
    private ArrayList<Poligono> AllPoligonos;
    private GraphicsContext gc;
    private GraphicsContext gc2;
    private GraphicsContext gc3;
    private GraphicsContext gc4;

    public Impressao3D() {
        this.AllPoligonos = new ArrayList<>();
    }

    public ArrayList<Poligono> getAllPoligonos() {
        return AllPoligonos;
    }

    public void setAllPoligonos(ArrayList<Poligono> AllPoligonos) {
        this.AllPoligonos = AllPoligonos;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public GraphicsContext getGc2() {
        return gc2;
    }

    public void setGc2(GraphicsContext gc2) {
        this.gc2 = gc2;
    }

    public GraphicsContext getGc3() {
        return gc3;
    }

    public void setGc3(GraphicsContext gc3) {
        this.gc3 = gc3;
    }

    public GraphicsContext getGc4() {
        return gc4;
    }

    public void setGc4(GraphicsContext gc4) {
        this.gc4 = gc4;
    }
    
    public void ChamaTelas(Pontos VRP, boolean TelaMaximizada, boolean OcultacaoFaces, boolean Wireframe, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        if(TelaMaximizada == false)
        {
            for (int i = 0; i < AllPoligonos.size(); i++)
            {
                tela1(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 0, 0, 200, luzes, luzAmbiente);//mudar para vrp
                tela2(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 0, 200, 0, luzes, luzAmbiente);//mudar para vrp
                tela3(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 200, 0, 0, luzes, luzAmbiente);//mudar para vrp
                tela4(VRP.getX(), VRP.getY(), VRP.getZ(), AllPoligonos.get(i), OcultacaoFaces, Wireframe, luzes, luzAmbiente);
                //tela4(110, -140, 500, AllPoligonos.get(i), OcultacaoFaces, Wireframe);
            }
        }
        else
        {
            for (int i = 0; i < AllPoligonos.size(); i++)
            {
                tela1Max(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 0, 0, 200, luzes, luzAmbiente);//mudar para vrp
                tela2Max(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 0, 200, 0, luzes, luzAmbiente);//mudar para vrp
                tela3Max(AllPoligonos.get(i), Wireframe, OcultacaoFaces, 200, 0, 0, luzes, luzAmbiente);//mudar para vrp
                tela4Max(VRP.getX(), VRP.getY(), VRP.getZ(), AllPoligonos.get(i), OcultacaoFaces, Wireframe, luzes, luzAmbiente);
            }
        }
    }
    
    public void tela1(Poligono pol, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        //XY
        
        Poligono poligono = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            poligono = oculta.OcultacaoFaces(pol, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                poligono = pol;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                poligono = oculta.OcultacaoFaces(pol, VRP);
            }
        }
        
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX());
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY());
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();
                    
                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);
        
                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono, luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc, testeCor,1); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX());
                    pontoAux.setY(poligono.getListaP().get(i).getY());
                    pontoAux.setZ(poligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono, luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc, testeCor,1); //alterado gc1 gc2 gc3
            
        }
        
        if(Wireframe == true)
        {
            gc.beginPath();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            gc.moveTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getY()+133.75);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc.lineTo(poligono.getListaP().get(j).getX()+181, poligono.getListaP().get(j).getY()+133.75);
            }
            gc.lineTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getY()+133.75);
            gc.stroke();

            if(poligono.getListaFaces().isEmpty() == false)
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc.beginPath();
                        gc.setStroke(Color.BLACK);
                        gc.setLineWidth(1);
                        gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                        }
                        gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        gc.stroke();
                    }
                }
            }
        }
    }
    
    public void tela1Max(Poligono poli, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        Poligono pol = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            pol = oculta.OcultacaoFaces(poli, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                pol = poli;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                pol = oculta.OcultacaoFaces(poli, VRP);
            }
        }
        
        Poligono poligono = new Poligono();
        
        //poligono.setContorno(pol.getContorno());
        //poligono.setCor(pol.getCor());
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < pol.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(pol.getListaP().get(i).getX() * 2);
            ponto.setY(pol.getListaP().get(i).getY() * 2);
            ponto.setZ(pol.getListaP().get(i).getZ() * 2);
            pontos.add(ponto);
        }
        poligono.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        for(int i = 0; i < pol.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < pol.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(pol.getListaFaces().get(i).getListaPFaces().get(j).getX() * 2);
                ponto.setY(pol.getListaFaces().get(i).getListaPFaces().get(j).getY() * 2);
                ponto.setZ(pol.getListaFaces().get(i).getListaPFaces().get(j).getZ() * 2);
                pontos.add(ponto);
            }
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poligono.setListaFaces(faces);
        //XY
        
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX()+181);
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY()+133.75);
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();

                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);

                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc, testeCor,1); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX()+181);
                    pontoAux.setY(poligono.getListaP().get(i).getY()+133.75);
                    pontoAux.setZ(poligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc, testeCor,1); //alterado gc1 gc2 gc3
        }
        
        if(Wireframe == true)
        {
            gc.beginPath();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            gc.moveTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getY()+267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc.lineTo(poligono.getListaP().get(j).getX()+362, poligono.getListaP().get(j).getY()+267.5);
            }
            gc.lineTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getY()+267.5);
            gc.stroke();

            if(poligono.getListaFaces().isEmpty() == false)
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc.beginPath();
                        gc.setStroke(Color.BLACK);
                        gc.setLineWidth(1);
                        gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                        for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 267.5);
                        }
                        gc.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                        gc.stroke();
                    }
                }
            }
        }
    }
    
    public void tela2(Poligono pol, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        //XZ
        
        Poligono poligono = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            poligono = oculta.OcultacaoFaces(pol, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                poligono = pol;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                poligono = oculta.OcultacaoFaces(pol, VRP);
            }
        }
        
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX());
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY());
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();
                    
                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);
        
                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc2, testeCor,2); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX());
                    pontoAux.setY(poligono.getListaP().get(i).getY());
                    pontoAux.setZ(poligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc2, testeCor,2); //alterado gc1 gc2 gc3
        }

        if(Wireframe == true)
        {
            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc2.beginPath();
                    gc2.setStroke(Color.BLACK);
                    gc2.setLineWidth(1);
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
            gc2.setStroke(Color.BLACK);
            gc2.setLineWidth(1);
            gc2.moveTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getZ() + 133.75);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc2.lineTo(poligono.getListaP().get(j).getX()+181, poligono.getListaP().get(j).getZ() + 133.75);
            }
            gc2.lineTo(poligono.getListaP().get(0).getX()+181, poligono.getListaP().get(0).getZ() + 133.75);
            gc2.stroke();
        }
    }
    
    public void tela2Max(Poligono poli, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        Poligono pol = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            pol = oculta.OcultacaoFaces(poli, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                pol = poli;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                pol = oculta.OcultacaoFaces(poli, VRP);
            }
        }
        
        Poligono poligono = new Poligono();
        
        //poligono.setContorno(pol.getContorno());
        //poligono.setCor(pol.getCor());
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < pol.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(pol.getListaP().get(i).getX() * 2);
            ponto.setY(pol.getListaP().get(i).getY() * 2);
            ponto.setZ(pol.getListaP().get(i).getZ() * 2);
            pontos.add(ponto);
        }
        poligono.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        for(int i = 0; i < pol.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < pol.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(pol.getListaFaces().get(i).getListaPFaces().get(j).getX() * 2);
                ponto.setY(pol.getListaFaces().get(i).getListaPFaces().get(j).getY() * 2);
                ponto.setZ(pol.getListaFaces().get(i).getListaPFaces().get(j).getZ() * 2);
                pontos.add(ponto);
            }
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poligono.setListaFaces(faces);
        //XZ
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX()+181);
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY());
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ()+133.75);
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();

                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);

                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc2, testeCor,2); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX()+181);
                    pontoAux.setY(poligono.getListaP().get(i).getY());
                    pontoAux.setZ(poligono.getListaP().get(i).getZ()+133.75);
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc2, testeCor,2); //alterado gc1 gc2 gc3
        }

        if(Wireframe == true)
        {
            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc2.beginPath();
                    gc2.setStroke(Color.BLACK);
                    gc2.setLineWidth(1);
                    gc2.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 267.5);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc2.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ() + 267.5);
                    }
                    gc2.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 267.5);
                    gc2.stroke();
                }
            }

            gc2.beginPath();
            gc2.setStroke(Color.BLACK);
            gc2.setLineWidth(1);
            gc2.moveTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getZ() + 267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc2.lineTo(poligono.getListaP().get(j).getX()+362, poligono.getListaP().get(j).getZ() + 267.5);
            }
            gc2.lineTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getZ() + 267.5);
            gc2.stroke();
        }
    }
    
    public void tela3(Poligono pol, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        //YZ
        
        Poligono poligono = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            poligono = oculta.OcultacaoFaces(pol, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                poligono = pol;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                poligono = oculta.OcultacaoFaces(pol, VRP);
            }
        }
        
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX());
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY());
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();
                    
                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);
        
                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc3, testeCor,3); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX());
                    pontoAux.setY(poligono.getListaP().get(i).getY());
                    pontoAux.setZ(poligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, poligono,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc3, testeCor,3); //alterado gc1 gc2 gc3
        }

        if(Wireframe == true)
        {
            gc3.beginPath();
            gc3.setStroke(Color.BLACK);
            gc3.setLineWidth(1);
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
                    gc3.setStroke(Color.BLACK);
                    gc3.setLineWidth(1);
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
    }
    
     public void tela3Max(Poligono poli, boolean Wireframe, boolean OcultacaoFaces, double X, double Y, double Z, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        Poligono pol = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            pol = oculta.OcultacaoFaces(poli, VRP);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                pol = poli;
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                pol = oculta.OcultacaoFaces(poli, VRP);
            }
        }
        
        Poligono poligono = new Poligono();
        
        //poligono.setContorno(pol.getContorno());
        //poligono.setCor(pol.getCor());
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < pol.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(pol.getListaP().get(i).getX() * 2);
            ponto.setY(pol.getListaP().get(i).getY() * 2);
            ponto.setZ(pol.getListaP().get(i).getZ() * 2);
            pontos.add(ponto);
        }
        poligono.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        for(int i = 0; i < pol.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < pol.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(pol.getListaFaces().get(i).getListaPFaces().get(j).getX() * 2);
                ponto.setY(pol.getListaFaces().get(i).getListaPFaces().get(j).getY() * 2);
                ponto.setZ(pol.getListaFaces().get(i).getListaPFaces().get(j).getZ() * 2);
                pontos.add(ponto);
            }
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poligono.setListaFaces(faces);
        //YZ
        //tem problema
        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX());
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY()+133.75);
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ()+181);
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();

                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);

                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc3, testeCor,3); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX());
                    pontoAux.setY(poligono.getListaP().get(i).getY()+133.75);
                    pontoAux.setZ(poligono.getListaP().get(i).getZ()+181);
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, pol,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc3, testeCor,3); //alterado gc1 gc2 gc3
        }

        if(Wireframe == true)
        {
            gc3.beginPath();
            gc3.setStroke(Color.BLACK);
            gc3.setLineWidth(1);
            gc3.moveTo(poligono.getListaP().get(0).getZ() + 362, poligono.getListaP().get(0).getY() + 267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc3.lineTo(poligono.getListaP().get(j).getZ() + 362, poligono.getListaP().get(j).getY() + 267.5);
            }
            gc3.lineTo(poligono.getListaP().get(0).getZ() + 362, poligono.getListaP().get(0).getY() + 267.5);
            gc3.stroke();

            for(int i = 0; i < poligono.getListaFaces().size(); i++)
            {
                for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    gc3.beginPath();
                    gc3.setStroke(Color.BLACK);
                    gc3.setLineWidth(1);
                    gc3.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                    for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                    {
                        gc3.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getZ() + 362, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 267.5);
                    }
                    gc3.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getZ() + 362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                    gc3.stroke();
                }
            }
        }
    }
    
    public void tela4(double X, double Y, double Z, Poligono poligono, boolean OcultacaoFaces, boolean Wireframe, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        //Perspectiva
        
        ProjecaoPerspectiva projecao = new ProjecaoPerspectiva();

        Poligono novoPoligono = new Poligono();
        Pontos VRP = new Pontos();
        
        if(Wireframe == false)
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            Poligono novo = new Poligono();

            novo = oculta.OcultacaoFaces(poligono, VRP);

            novoPoligono = projecao.VisualizacaoPerspectiva(novo, X, Y, Z);
        }
        else
        {
            if(OcultacaoFaces == false)
            {
                novoPoligono = projecao.VisualizacaoPerspectiva(poligono, X, Y, Z);
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                Poligono novo = new Poligono();

                novo = oculta.OcultacaoFaces(poligono, VRP);

                novoPoligono = projecao.VisualizacaoPerspectiva(novo, X, Y, Z);
            }
        }
        
        //if((Wireframe==false)&&(novoPoligono.getCor().getTemCor() == true))
        if(Wireframe==false)
        {
            //String color = novoPoligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!novoPoligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < novoPoligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < novoPoligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getX());
                        pontoAux.setY(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getY());
                        pontoAux.setZ(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();
                    
                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);
        
                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, novoPoligono,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc4, testeCor, 1); //alterado gc1 gc2 gc3
                    //pintar.colorir(gc4, color,1); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
                
            }
            for(int i = 0; i < novoPoligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(novoPoligono.getListaP().get(i).getX());
                    pontoAux.setY(novoPoligono.getListaP().get(i).getY());
                    pontoAux.setZ(novoPoligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
                    
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, novoPoligono,luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc4, testeCor,1); //alterado gc1 gc2 gc3
        }
        
        if(Wireframe == true)
        {
            gc4.beginPath();
            gc4.setStroke(Color.BLACK);
            gc4.setLineWidth(1);
            gc4.moveTo(novoPoligono.getListaP().get(0).getX()+181, novoPoligono.getListaP().get(0).getY()+133.75);
            for (int j = 1; j < novoPoligono.getListaP().size(); j++)
            {
                gc4.lineTo(novoPoligono.getListaP().get(j).getX()+181, novoPoligono.getListaP().get(j).getY()+133.75);
            }
            gc4.lineTo(novoPoligono.getListaP().get(0).getX()+181, novoPoligono.getListaP().get(0).getY()+133.75);
            gc4.stroke();

            if(novoPoligono.getListaFaces().isEmpty() == false)
            {
                for(int i = 0; i < novoPoligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < novoPoligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc4.beginPath();
                        gc4.setStroke(Color.BLACK);
                        gc4.setLineWidth(1);
                        gc4.moveTo(novoPoligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, novoPoligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        for (int x = 1; x < novoPoligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc4.lineTo(novoPoligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+181, novoPoligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 133.75);
                        }
                        gc4.moveTo(novoPoligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+181, novoPoligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 133.75);
                        gc4.stroke();
                    }
                }
            }
        }
    }
    
    public void tela4Max(double X, double Y, double Z, Poligono pol, boolean OcultacaoFaces, boolean Wireframe, ArrayList<FonteLuminosa> luzes, LuzAmbiente luzAmbiente)
    {
        //Perspectiva
        
        ProjecaoPerspectiva projecao = new ProjecaoPerspectiva();

        Poligono novoPoligono = new Poligono();
        
        Pontos VRP = new Pontos();
        
        if(Wireframe == true)
        {
            if(OcultacaoFaces == false)
            {
                novoPoligono = projecao.VisualizacaoPerspectiva(pol, X, Y, Z);
            }
            else
            {
                OcultacaoDeFaces oculta = new OcultacaoDeFaces();

                VRP.setX(X);
                VRP.setY(Y);
                VRP.setZ(Z);

                Poligono novo = new Poligono();

                novo = oculta.OcultacaoFaces(pol, VRP);

                novoPoligono = projecao.VisualizacaoPerspectiva(novo, X, Y, Z);
            }
        }
        else
        {
            OcultacaoDeFaces oculta = new OcultacaoDeFaces();

            VRP.setX(X);
            VRP.setY(Y);
            VRP.setZ(Z);

            Poligono novo = new Poligono();

            novo = oculta.OcultacaoFaces(pol, VRP);

            novoPoligono = projecao.VisualizacaoPerspectiva(novo, X, Y, Z);
        }

        Poligono poligono = new Poligono();
        
        //poligono.setContorno(pol.getContorno());
        //poligono.setCor(pol.getCor());
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < novoPoligono.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(novoPoligono.getListaP().get(i).getX() * 2);
            ponto.setY(novoPoligono.getListaP().get(i).getY() * 2);
            ponto.setZ(novoPoligono.getListaP().get(i).getZ() * 2);
            pontos.add(ponto);
        }
        poligono.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        for(int i = 0; i < novoPoligono.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < novoPoligono.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getX() * 2);
                ponto.setY(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getY() * 2);
                ponto.setZ(novoPoligono.getListaFaces().get(i).getListaPFaces().get(j).getZ() * 2);
                pontos.add(ponto);
            }
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poligono.setListaFaces(faces);

        if(Wireframe==false)
        {
            //String color = poligono.getCor().getCor();
            //Poligono aux = new Poligono();
            //aux = poligono;
            ArrayList<Pontos> AllPontos = new ArrayList<>();
            if(!poligono.getListaFaces().isEmpty())
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        Pontos pontoAux = new Pontos();
                        pontoAux.setX(poligono.getListaFaces().get(i).getListaPFaces().get(j).getX()+181);
                        pontoAux.setY(poligono.getListaFaces().get(i).getListaPFaces().get(j).getY()+133.75);
                        pontoAux.setZ(poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                        AllPontos.add(pontoAux);
                    }
                    
                    SobreamentoConstante teste = new SobreamentoConstante();
                    
                    FonteLuminosa fonte = new FonteLuminosa();

                    ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
                    testeFonte.add(fonte);

                    String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, novoPoligono,luzAmbiente);
                    
                    Coloracao pintar = new Coloracao();
                    pintar.setPoligon(AllPontos);
                    pintar.colorir(gc4, testeCor,1); //alterado gc1 gc2 gc3
                    AllPontos = new ArrayList<>();
                }
            }
            for(int i = 0; i < poligono.getListaP().size(); i++)
            {
                    Pontos pontoAux = new Pontos();
                    pontoAux.setX(poligono.getListaP().get(i).getX()+181);
                    pontoAux.setY(poligono.getListaP().get(i).getY()+133.75);
                    pontoAux.setZ(poligono.getListaP().get(i).getZ());
                    AllPontos.add(pontoAux);
            }
            
            SobreamentoConstante teste = new SobreamentoConstante();
                    
            FonteLuminosa fonte = new FonteLuminosa();

            ArrayList<FonteLuminosa> testeFonte = new ArrayList<>();
            testeFonte.add(fonte);

            String testeCor = teste.Sombreamento(AllPontos, luzes, VRP, novoPoligono, luzAmbiente);
            
            Coloracao pintar = new Coloracao();
            pintar.setPoligon(AllPontos);
            pintar.colorir(gc4, testeCor,1); //alterado gc1 gc2 gc3
        }
        
        if(Wireframe == true)
        {
            gc4.beginPath();
            gc4.setStroke(Color.BLACK);
            gc4.setLineWidth(1);
            gc4.moveTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getY()+267.5);
            for (int j = 1; j < poligono.getListaP().size(); j++)
            {
                gc4.lineTo(poligono.getListaP().get(j).getX()+362, poligono.getListaP().get(j).getY()+267.5);
            }
            gc4.lineTo(poligono.getListaP().get(0).getX()+362, poligono.getListaP().get(0).getY()+267.5);
            gc4.stroke();

            if(poligono.getListaFaces().isEmpty() == false)
            {
                for(int i = 0; i < poligono.getListaFaces().size(); i++)
                {
                    for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
                    {
                        gc4.beginPath();
                        gc4.setStroke(Color.BLACK);
                        gc4.setLineWidth(1);
                        gc4.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                        for (int x = 1; x < poligono.getListaFaces().get(i).getListaPFaces().size(); x++)
                        {
                            gc4.lineTo(poligono.getListaFaces().get(i).getListaPFaces().get(x).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(x).getY() + 267.5);
                        }
                        gc4.moveTo(poligono.getListaFaces().get(i).getListaPFaces().get(0).getX()+362, poligono.getListaFaces().get(i).getListaPFaces().get(0).getY() + 267.5);
                        gc4.stroke();
                    }
                }
            }
        }
    }
}
