/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Vilson
 */
public class SobreamentoConstante {

    public SobreamentoConstante() {
    }
    
    private boolean orientaVisualizacao(ArrayList<Pontos> pontos)
    {
       int horario = 0, antiHorario = 0;
       
       Pontos a = new Pontos();
       Pontos b = new Pontos();
       Pontos c = new Pontos();
       
       for(int i = 0; i < pontos.size(); i++)
       {
           if(i == pontos.size()-1)
           {
               c.setX(pontos.get(i).getX());
               c.setY(pontos.get(i).getY());
               c.setZ(pontos.get(i).getZ());
               
               a.setX(pontos.get(0).getX());
               a.setY(pontos.get(0).getY());
               a.setZ(pontos.get(0).getZ());
               
               b.setX(pontos.get(1).getX());
               b.setY(pontos.get(1).getY());
               b.setZ(pontos.get(1).getZ());
           }
           else if(i == pontos.size()-2)
           {
               c.setX(pontos.get(i).getX());
               c.setY(pontos.get(i).getY());
               c.setZ(pontos.get(i).getZ());
               
               a.setX(pontos.get(i+1).getX());
               a.setY(pontos.get(i+1).getY());
               a.setZ(pontos.get(i+1).getZ());
               
               b.setX(pontos.get(0).getX());
               b.setY(pontos.get(0).getY());
               b.setZ(pontos.get(0).getZ());
           }
           else{
               c.setX(pontos.get(i).getX());
               c.setY(pontos.get(i).getY());
               c.setZ(pontos.get(i).getZ());
               
               a.setX(pontos.get(i+1).getX());
               a.setY(pontos.get(i+1).getY());
               a.setZ(pontos.get(i+1).getZ());
               
               b.setX(pontos.get(i+2).getX());
               b.setY(pontos.get(i+2).getY());
               b.setZ(pontos.get(i+2).getZ());
           }
           
           double s, determinante;
           
           Pontos A = new Pontos();
           Pontos B = new Pontos();
           
           double aux;
           
           aux = b.getX() - a.getX();
           A.setX(aux);
           
           aux = b.getY() - a.getY();
           A.setY(aux);
           
           aux = b.getZ() - a.getZ();
           A.setZ(aux);
           
           aux = c.getX() - a.getX();
           B.setX(aux);
           
           aux = c.getY() - a.getY();
           B.setY(aux);
           
           aux = c.getZ() - a.getZ();
           B.setZ(aux);
           
           determinante = (A.getY() * B.getZ()) + (A.getX() * B.getY()) + (B.getX() * A.getZ()) - (A.getY() * B.getX()) - (A.getZ() * B.getY()) - (B.getZ() * A.getX());
           s = (0.5) * determinante;
           
           if(s > 0)
           {
               antiHorario++;
           }
           if(s < 0)
           {
               horario++;
           }
       }
       
       if(horario > antiHorario)
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    
    private Pontos normal_Face(ArrayList<Pontos> pontos)
    {
        Pontos a = new Pontos();
        Pontos b = new Pontos();
        Pontos c = new Pontos();
        
        boolean resposta = orientaVisualizacao(pontos);
        
        if(resposta = true)//horario
        {
            a.setX(pontos.get(2).getX());
            a.setY(pontos.get(2).getY());
            a.setZ(pontos.get(2).getZ());

            b.setX(pontos.get(1).getX());
            b.setY(pontos.get(1).getY());
            b.setZ(pontos.get(1).getZ());

            c.setX(pontos.get(0).getX());
            c.setY(pontos.get(0).getY());
            c.setZ(pontos.get(0).getZ());
        }
        else//anti horario
        {
            a.setX(pontos.get(0).getX());
            a.setY(pontos.get(0).getY());
            a.setZ(pontos.get(0).getZ());

            b.setX(pontos.get(1).getX());
            b.setY(pontos.get(1).getY());
            b.setZ(pontos.get(1).getZ());

            c.setX(pontos.get(2).getX());
            c.setY(pontos.get(2).getY());
            c.setZ(pontos.get(2).getZ());
        }
        
        Pontos vetor1 = new Pontos();
        Pontos vetor2 = new Pontos();
        
        Pontos N = new Pontos();
        
        double valor;
        //valor = (vetor1.getY() * vetor2.getZ()) - (vetor1.getZ() * vetor2.getY());
        valor = (c.getY() - b.getY()) * (a.getZ() - b.getZ()) - (a.getY() - b.getY())*(c.getZ() - b.getZ());
        
        N.setX(valor);
        
        //valor = (vetor1.getZ() * vetor2.getX()) - (vetor2.getZ() * vetor1.getX());
        valor = (c.getZ() - b.getZ())*(a.getX() - b.getX())-(a.getZ() - b.getZ())*(c.getX() - b.getX());
        
        N.setY(valor);
        
        //valor = (vetor1.getX() * vetor2.getY()) - (vetor1.getY() * vetor2.getX());
        valor = (c.getX() - b.getX())*(a.getY() - b.getY())-(a.getX() - b.getX())*(c.getY() - b.getY());
        
        N.setZ(valor);
        
        double norma;
        
        norma = Math.sqrt(((N.getX()*N.getX()) + (N.getY()*N.getY()) + (N.getZ()*N.getZ())));
        
        Pontos aux = new Pontos();
        
        aux.setX(N.getX());
        aux.setY(N.getY());
        aux.setZ(N.getZ());
        
        N.setX(aux.getX()/norma);
        N.setY(aux.getY()/norma);
        N.setZ(aux.getZ()/norma);
        
        return N;
    }
    
    private double quantidadeLuzR(ArrayList<Pontos> Poligon, FonteLuminosa luz, Pontos VRP, Poligono poligono, LuzAmbiente luzAmbiente)
    {
        double Xmin, Ymin, Zmin, Xmax, Ymax, Zmax;
        
        Xmin = Poligon.get(0).getX();
        Ymin = Poligon.get(0).getY();
        Zmin = Poligon.get(0).getZ();
        Xmax = Poligon.get(0).getX();
        Ymax = Poligon.get(0).getY();
        Zmax = Poligon.get(0).getZ();
        
        for(int i = 1; i < Poligon.size(); i++)
        {
            if(Xmin > Poligon.get(i).getX())
            {
                Xmin = Poligon.get(i).getX();
            }
            if(Ymin > Poligon.get(i).getY())
            {
                Ymin = Poligon.get(i).getY();
            }
            if(Zmin > Poligon.get(i).getZ())
            {
                Zmin = Poligon.get(i).getZ();
            }
            if(Xmax < Poligon.get(i).getX())
            {
                Xmax = Poligon.get(i).getX();
            }
            if(Ymax < Poligon.get(i).getY())
            {
                Ymax = Poligon.get(i).getY();
            }
            if(Zmax < Poligon.get(i).getZ())
            {
                Zmax = Poligon.get(i).getZ();
            }
        }
        
        double Xcentro, Ycentro, Zcentro;
        
        Xcentro = (Xmax+Xmin)/2;
        Ycentro = (Ymax+Ymin)/2;
        Zcentro = (Zmax+Zmin)/2;
        
        Pontos normalFace = new Pontos();
        double aux;
        
        normalFace = normal_Face(Poligon);
        
        Pontos vetorL = new Pontos();
        vetorL.setX(luz.getLx() - Xcentro);
        vetorL.setY(luz.getLy() - Ycentro);
        vetorL.setZ(luz.getLz() - Zcentro);
        
        double normalVetorL;
        
        aux = (vetorL.getX() * vetorL.getX()) + (vetorL.getY() * vetorL.getY()) + (vetorL.getZ() * vetorL.getZ());
        normalVetorL = Math.sqrt(aux);
        
        vetorL.setX(vetorL.getX() / normalVetorL);
        vetorL.setY(vetorL.getY() / normalVetorL);
        vetorL.setZ(vetorL.getZ() / normalVetorL);
        
        double Ia, Id, Is;
        
        Ia = luzAmbiente.getILAR() * poligono.getKaR();
        
        double produtoEscalar;
        
        produtoEscalar = (normalFace.getX() * vetorL.getX()) + (normalFace.getY() * vetorL.getY()) + (normalFace.getZ() * vetorL.getZ());
        
        if(produtoEscalar > 0)
        {
            Id = luz.getILR() * poligono.getKdR() * produtoEscalar;
        
            Pontos R = new Pontos();

            aux = ((2 * produtoEscalar) * normalFace.getX()) - vetorL.getX();
            R.setX(aux);
            aux = ((2 * produtoEscalar) * normalFace.getY()) - vetorL.getY();
            R.setY(aux);
            aux = ((2 * produtoEscalar) * normalFace.getZ()) - vetorL.getZ();
            R.setZ(aux);

            Pontos S = new Pontos();

            aux = VRP.getX() - Xcentro;
            S.setX(aux);
            aux = VRP.getY() - Ycentro;
            S.setY(aux);
            aux = VRP.getZ() - Zcentro;
            S.setZ(aux);

            double normalS;

            aux = (S.getX() * S.getX()) + (S.getY() * S.getY()) + (S.getZ() * S.getZ());
            normalS = Math.sqrt(aux);

            aux = S.getX() / normalS;
            S.setX(aux);
            aux = S.getY() / normalS;
            S.setY(aux);
            aux = S.getZ() / normalS;
            S.setZ(aux);

            produtoEscalar = (R.getX() * S.getX()) + (R.getY() * S.getY()) + (R.getZ() * S.getZ());

            if(produtoEscalar > 0)
            {
                Is = luz.getILR() * poligono.getKsR() * (Math.pow(produtoEscalar, (poligono.getN())));
            }
            else 
            {
                Is = 0;
            }
        }
        else
        {
            Id = 0;
            Is = 0;
        }
        
        double It;
        
        It = Ia + Id + Is;
        
        return It;
    }
    
    private double quantidadeLuzG(ArrayList<Pontos> Poligon, FonteLuminosa luz, Pontos VRP, Poligono poligono, LuzAmbiente luzAmbiente)
    {
        double Xmin, Ymin, Zmin, Xmax, Ymax, Zmax;
        
        Xmin = Poligon.get(0).getX();
        Ymin = Poligon.get(0).getY();
        Zmin = Poligon.get(0).getZ();
        Xmax = Poligon.get(0).getX();
        Ymax = Poligon.get(0).getY();
        Zmax = Poligon.get(0).getZ();
        
        for(int i = 1; i < Poligon.size(); i++)
        {
            if(Xmin > Poligon.get(i).getX())
            {
                Xmin = Poligon.get(i).getX();
            }
            if(Ymin > Poligon.get(i).getY())
            {
                Ymin = Poligon.get(i).getY();
            }
            if(Zmin > Poligon.get(i).getZ())
            {
                Zmin = Poligon.get(i).getZ();
            }
            if(Xmax < Poligon.get(i).getX())
            {
                Xmax = Poligon.get(i).getX();
            }
            if(Ymax < Poligon.get(i).getY())
            {
                Ymax = Poligon.get(i).getY();
            }
            if(Zmax < Poligon.get(i).getZ())
            {
                Zmax = Poligon.get(i).getZ();
            }
        }
        
        double Xcentro, Ycentro, Zcentro;
        
        Xcentro = (Xmax+Xmin)/2;
        Ycentro = (Ymax+Ymin)/2;
        Zcentro = (Zmax+Zmin)/2;
        
        Pontos normalFace = new Pontos();
        double aux;
        
        normalFace = normal_Face(Poligon);
        
        Pontos vetorL = new Pontos();
        vetorL.setX(luz.getLx() - Xcentro);
        vetorL.setY(luz.getLy() - Ycentro);
        vetorL.setZ(luz.getLz() - Zcentro);
        
        double normalVetorL;
        
        aux = (vetorL.getX() * vetorL.getX()) + (vetorL.getY() * vetorL.getY()) + (vetorL.getZ() * vetorL.getZ());
        normalVetorL = Math.sqrt(aux);
        
        vetorL.setX(vetorL.getX() / normalVetorL);
        vetorL.setY(vetorL.getY() / normalVetorL);
        vetorL.setZ(vetorL.getZ() / normalVetorL);
        
        double Ia, Id, Is;
        
        Ia = luzAmbiente.getILAG() * poligono.getKaG();
        
        double produtoEscalar;
        
        produtoEscalar = (normalFace.getX() * vetorL.getX()) + (normalFace.getY() * vetorL.getY()) + (normalFace.getZ() * vetorL.getZ());
        
        if(produtoEscalar > 0)
        {
            Id = luz.getILG() * poligono.getKdG() * produtoEscalar;
        
            Pontos R = new Pontos();

            aux = ((2 * produtoEscalar) * normalFace.getX()) - vetorL.getX();
            R.setX(aux);
            aux = ((2 * produtoEscalar) * normalFace.getY()) - vetorL.getY();
            R.setY(aux);
            aux = ((2 * produtoEscalar) * normalFace.getZ()) - vetorL.getZ();
            R.setZ(aux);

            Pontos S = new Pontos();

            aux = VRP.getX() - Xcentro;
            S.setX(aux);
            aux = VRP.getY() - Ycentro;
            S.setY(aux);
            aux = VRP.getZ() - Zcentro;
            S.setZ(aux);

            double normalS;

            aux = (S.getX() * S.getX()) + (S.getY() * S.getY()) + (S.getZ() * S.getZ());
            normalS = Math.sqrt(aux);

            aux = S.getX() / normalS;
            S.setX(aux);
            aux = S.getY() / normalS;
            S.setY(aux);
            aux = S.getZ() / normalS;
            S.setZ(aux);

            produtoEscalar = (R.getX() * S.getX()) + (R.getY() * S.getY()) + (R.getZ() * S.getZ());

            if(produtoEscalar > 0)
            {
                Is = luz.getILG() * poligono.getKsG() * (Math.pow(produtoEscalar, (poligono.getN())));
            }
            else 
            {
                Is = 0;
            }
        }
        else
        {
            Id = 0;
            Is = 0;
        }
        
        double It;
        
        It = Ia + Id + Is;
        
        return It;
    }
    
    private double quantidadeLuzB(ArrayList<Pontos> Poligon, FonteLuminosa luz, Pontos VRP, Poligono poligono, LuzAmbiente luzAmbiente)
    {
        double Xmin, Ymin, Zmin, Xmax, Ymax, Zmax;
        
        Xmin = Poligon.get(0).getX();
        Ymin = Poligon.get(0).getY();
        Zmin = Poligon.get(0).getZ();
        Xmax = Poligon.get(0).getX();
        Ymax = Poligon.get(0).getY();
        Zmax = Poligon.get(0).getZ();
        
        for(int i = 1; i < Poligon.size(); i++)
        {
            if(Xmin > Poligon.get(i).getX())
            {
                Xmin = Poligon.get(i).getX();
            }
            if(Ymin > Poligon.get(i).getY())
            {
                Ymin = Poligon.get(i).getY();
            }
            if(Zmin > Poligon.get(i).getZ())
            {
                Zmin = Poligon.get(i).getZ();
            }
            if(Xmax < Poligon.get(i).getX())
            {
                Xmax = Poligon.get(i).getX();
            }
            if(Ymax < Poligon.get(i).getY())
            {
                Ymax = Poligon.get(i).getY();
            }
            if(Zmax < Poligon.get(i).getZ())
            {
                Zmax = Poligon.get(i).getZ();
            }
        }
        
        double Xcentro, Ycentro, Zcentro;
        
        Xcentro = (Xmax+Xmin)/2;
        Ycentro = (Ymax+Ymin)/2;
        Zcentro = (Zmax+Zmin)/2;
        
        Pontos normalFace = new Pontos();
        double aux;
        
        normalFace = normal_Face(Poligon);
        
        Pontos vetorL = new Pontos();
        vetorL.setX(luz.getLx() - Xcentro);
        vetorL.setY(luz.getLy() - Ycentro);
        vetorL.setZ(luz.getLz() - Zcentro);
        
        double normalVetorL;
        
        aux = (vetorL.getX() * vetorL.getX()) + (vetorL.getY() * vetorL.getY()) + (vetorL.getZ() * vetorL.getZ());
        normalVetorL = Math.sqrt(aux);
        
        vetorL.setX(vetorL.getX() / normalVetorL);
        vetorL.setY(vetorL.getY() / normalVetorL);
        vetorL.setZ(vetorL.getZ() / normalVetorL);
        
        double Ia, Id, Is;
        
        Ia = luzAmbiente.getILAB() * poligono.getKaB();
        
        double produtoEscalar;
        
        produtoEscalar = (normalFace.getX() * vetorL.getX()) + (normalFace.getY() * vetorL.getY()) + (normalFace.getZ() * vetorL.getZ());
        
        if(produtoEscalar > 0)
        {
            Id = luz.getILB() * poligono.getKdB() * produtoEscalar;
        
            Pontos R = new Pontos();

            aux = ((2 * produtoEscalar) * normalFace.getX()) - vetorL.getX();
            R.setX(aux);
            aux = ((2 * produtoEscalar) * normalFace.getY()) - vetorL.getY();
            R.setY(aux);
            aux = ((2 * produtoEscalar) * normalFace.getZ()) - vetorL.getZ();
            R.setZ(aux);

            Pontos S = new Pontos();

            aux = VRP.getX() - Xcentro;
            S.setX(aux);
            aux = VRP.getY() - Ycentro;
            S.setY(aux);
            aux = VRP.getZ() - Zcentro;
            S.setZ(aux);

            double normalS;

            aux = (S.getX() * S.getX()) + (S.getY() * S.getY()) + (S.getZ() * S.getZ());
            normalS = Math.sqrt(aux);

            aux = S.getX() / normalS;
            S.setX(aux);
            aux = S.getY() / normalS;
            S.setY(aux);
            aux = S.getZ() / normalS;
            S.setZ(aux);

            produtoEscalar = (R.getX() * S.getX()) + (R.getY() * S.getY()) + (R.getZ() * S.getZ());

            if(produtoEscalar > 0)
            {
                Is = luz.getILB() * poligono.getKsB() * (Math.pow(produtoEscalar, (poligono.getN())));
            }
            else 
            {
                Is = 0;
            }
        }
        else
        {
            Id = 0;
            Is = 0;
        }
        
        double It;
        
        It = Ia + Id + Is;
        
        return It;
    }
    
    public String Sombreamento(ArrayList<Pontos> Poligon, ArrayList<FonteLuminosa> luzes, Pontos VRP, Poligono poligono, LuzAmbiente luzAmbiente)
    {
        double R = 0, G = 0, B = 0;
        
        for(int i = 0; i < luzes.size(); i++)
        {
            double r, g, b;
            r = quantidadeLuzR(Poligon, luzes.get(i), VRP, poligono, luzAmbiente);
            if(r < 0)
            {
                r = 0;
            }
            if(r > 255)
            {
                r = 255;
            }
            R+=r;
            g = quantidadeLuzG(Poligon, luzes.get(i), VRP, poligono, luzAmbiente);
            if(g < 0)
            {
                g = 0;
            }
            if(g > 255)
            {
                g = 255;
            }
            G+=g;
            b = quantidadeLuzB(Poligon, luzes.get(i), VRP, poligono, luzAmbiente);
            if(b < 0)
            {
                b = 0;
            }
            if(b > 255)
            {
                b = 255;
            }
            B+=b;
        }
        
        if(R < 0)
        {
            R = 0;
        }
        if(R > 255)
        {
            R = 255;
        }
        if(G < 0)
        {
            G = 0;
        }
        if(G > 255)
        {
            G = 255;
        }
        if(B < 0)
        {
            B = 0;
        }
        if(B > 255)
        {
            B = 255;
        }
        
        //Color cor = new Color(R, G, B, 1);
        
        Color cor;
        
        cor = Color.rgb((int)R, (int)G, (int)B);
        
        String novaCor;
        
        novaCor = cor.toString();
        
        return novaCor;
    }
    
}
