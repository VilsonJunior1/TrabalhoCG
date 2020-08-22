/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

import java.util.ArrayList;

/**
 *
 * @author vilson
 */
public class Transformacoes2D {
    private Poligono Poligon;

    public Transformacoes2D() {
        this.Poligon = new Poligono();
    }

    public Poligono getPoligon() {
        return Poligon;
    }

    public void setPoligon(Poligono Poligon) {
        this.Poligon = Poligon;
    }
    
    private double[][] MultiplicaMatriz(double[][] M1, double[][] M2)
    {
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        int m, n;
        m = 4;
        n = qtdPontos;
        
        double[][] M3 = new double [m][n];
        
        double soma = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    soma = soma + M1[i][k]*M2[k][j];
                }
                M3[i][j] = soma;
                soma = 0;
            }
        }
        
        return M3;
    }
    
    public Poligono Translacao1(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        double dx, dy, dz;
        
        dx = Xfinal - Xinicial;
        dy = Yfinal - Yinicial;
        dz = 1;
        
        M1[0][0] = 1;
        M1[0][1] = 0;
        M1[0][2] = 0;
        M1[0][3] = dx;
        
        M1[1][0] = 0;
        M1[1][1] = 1;
        M1[1][2] = 0;
        M1[1][3] = dy;
        
        M1[2][0] = 0;
        M1[2][1] = 0;
        M1[2][2] = 1;
        M1[2][3] = dz;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX();
            M2[1][i] = Poligon.getListaP().get(i).getY();
            M2[2][i] = Poligon.getListaP().get(i).getZ();
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i]);
            ponto.setY(M3[1][i]);
            ponto.setZ(M3[2][i]);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade]);
                ponto.setY(M3[1][j + quantidade]);
                ponto.setZ(M3[2][j + quantidade]);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Translacao2(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        double dx, dy, dz;
        
        dx = Xfinal - Xinicial;
        dz = Yfinal - Yinicial;
        dy = 1;
        
        M1[0][0] = 1;
        M1[0][1] = 0;
        M1[0][2] = 0;
        M1[0][3] = dx;
        
        M1[1][0] = 0;
        M1[1][1] = 1;
        M1[1][2] = 0;
        M1[1][3] = dy;
        
        M1[2][0] = 0;
        M1[2][1] = 0;
        M1[2][2] = 1;
        M1[2][3] = dz;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX();
            M2[1][i] = Poligon.getListaP().get(i).getY();
            M2[2][i] = Poligon.getListaP().get(i).getZ();
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i]);
            ponto.setY(M3[1][i]);
            ponto.setZ(M3[2][i]);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade]);
                ponto.setY(M3[1][j + quantidade]);
                ponto.setZ(M3[2][j + quantidade]);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Translacao3(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        double dx, dy, dz;
        
        dz = Xfinal - Xinicial;
        dy = Yfinal - Yinicial;
        dx = 1;
        
        M1[0][0] = 1;
        M1[0][1] = 0;
        M1[0][2] = 0;
        M1[0][3] = dx;
        
        M1[1][0] = 0;
        M1[1][1] = 1;
        M1[1][2] = 0;
        M1[1][3] = dy;
        
        M1[2][0] = 0;
        M1[2][1] = 0;
        M1[2][2] = 1;
        M1[2][3] = dz;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX();
            M2[1][i] = Poligon.getListaP().get(i).getY();
            M2[2][i] = Poligon.getListaP().get(i).getZ();
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i]);
            ponto.setY(M3[1][i]);
            ponto.setZ(M3[2][i]);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade]);
                ponto.setY(M3[1][j + quantidade]);
                ponto.setZ(M3[2][j + quantidade]);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Escala1(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin, XminP, XmaxP, YminP, YmaxP, ZminP, ZmaxP;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        Zmax = Poligon.getListaP().get(0).getZ();
        Zmin = Poligon.getListaP().get(0).getZ();
        
        XmaxP = Poligon.getListaP().get(0).getX();
        XminP = Poligon.getListaP().get(0).getX();
        YmaxP = Poligon.getListaP().get(0).getY();
        YminP = Poligon.getListaP().get(0).getY();
        ZmaxP = Poligon.getListaP().get(0).getZ();
        ZminP = Poligon.getListaP().get(0).getZ();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
            if (Zmax < Poligon.getListaP().get(i).getZ())
            {
                Zmax = Poligon.getListaP().get(i).getZ();
            }
            if (Zmin > Poligon.getListaP().get(i).getZ())
            {
                Zmin = Poligon.getListaP().get(i).getZ();
            }
            if (XmaxP < Poligon.getListaP().get(i).getX())
            {
                XmaxP = Poligon.getListaP().get(i).getX();
            }
            if (XminP > Poligon.getListaP().get(i).getX())
            {
                XminP = Poligon.getListaP().get(i).getX();
            }
            if (YmaxP < Poligon.getListaP().get(i).getY())
            {
                YmaxP = Poligon.getListaP().get(i).getY();
            }
            if (YminP > Poligon.getListaP().get(i).getY())
            {
                YminP = Poligon.getListaP().get(i).getY();
            }
            if (ZmaxP < Poligon.getListaP().get(i).getZ())
            {
                ZmaxP = Poligon.getListaP().get(i).getZ();
            }
            if (ZminP > Poligon.getListaP().get(i).getZ())
            {
                ZminP = Poligon.getListaP().get(i).getZ();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Zmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Zmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                }
            }
        }
        
        Xmin += 181;
        Xmax += 181;
        Ymin += 133.75;
        Ymax += 133.75;
        
        double Xmedio, Ymedio, Zmedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        Zmedio = ((Zmax - Zmin)/2) + Zmin;
        
        Xmin -= 8;
        Xmax += 8;
        Ymin -= 8;
        Ymax += 8;
        Zmin -= 8;
        Zmax += 8;
        
        if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {//certo
            //System.out.println("entrou xmax");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {//certo
            //System.out.println("entrou xmin");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmedio+5)) && (Xinicial >= (Xmedio-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Ymedio+5)) && (Yinicial >= (Ymedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dy = 1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Ymedio+5)) && (Yinicial >= (Ymedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = 1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dy = ((Yinicial - Yfinal)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmedio+5)) && (Xinicial >= (Xmedio-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yinicial - Yfinal)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {//correto
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = ((Xfinal - Xinicial)/200)+1;
            dz = ((Xfinal - Xinicial)/200)+1;
            //dy = ((Yinicial - Yfinal)/200)+1;
            //dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else
        {
            return Poligon;
        }
    }
    
    public Poligono Escala2(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin, XminP, XmaxP, YminP, YmaxP, ZminP, ZmaxP;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        Zmax = Poligon.getListaP().get(0).getZ();
        Zmin = Poligon.getListaP().get(0).getZ();
        
        XmaxP = Poligon.getListaP().get(0).getX();
        XminP = Poligon.getListaP().get(0).getX();
        YmaxP = Poligon.getListaP().get(0).getY();
        YminP = Poligon.getListaP().get(0).getY();
        ZmaxP = Poligon.getListaP().get(0).getZ();
        ZminP = Poligon.getListaP().get(0).getZ();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
            if (Zmax < Poligon.getListaP().get(i).getZ())
            {
                Zmax = Poligon.getListaP().get(i).getZ();
            }
            if (Zmin > Poligon.getListaP().get(i).getZ())
            {
                Zmin = Poligon.getListaP().get(i).getZ();
            }
            if (XmaxP < Poligon.getListaP().get(i).getX())
            {
                XmaxP = Poligon.getListaP().get(i).getX();
            }
            if (XminP > Poligon.getListaP().get(i).getX())
            {
                XminP = Poligon.getListaP().get(i).getX();
            }
            if (YmaxP < Poligon.getListaP().get(i).getY())
            {
                YmaxP = Poligon.getListaP().get(i).getY();
            }
            if (YminP > Poligon.getListaP().get(i).getY())
            {
                YminP = Poligon.getListaP().get(i).getY();
            }
            if (ZmaxP < Poligon.getListaP().get(i).getZ())
            {
                ZmaxP = Poligon.getListaP().get(i).getZ();
            }
            if (ZminP > Poligon.getListaP().get(i).getZ())
            {
                ZminP = Poligon.getListaP().get(i).getZ();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Zmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Zmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                }
            }
        }
        
        Xmin += 181;
        Xmax += 181;
        Zmin += 133.75;
        Zmax += 133.75;
        
        double Xmedio, Ymedio, Zmedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        Zmedio = ((Zmax - Zmin)/2) + Zmin;
        
        Xmin -= 8;
        Xmax += 8;
        Ymin -= 8;
        Ymax += 8;
        Zmin -= 8;
        Zmax += 8;
        
        if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Zmax+5)) && (Yinicial >= (Zmax-5))))
        {//certo
            //System.out.println("entrou xmax");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dz = ((Yfinal - Yinicial)/200)+1;
            dy = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Zmax+5)) && (Yinicial >= (Zmax-5))))
        {//certo
            //System.out.println("entrou xmin");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dz = ((Yfinal - Yinicial)/200)+1;
            dy = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmedio+5)) && (Xinicial >= (Xmedio-5))) && ((Yinicial <= (Zmax+5)) && (Yinicial >= (Zmax-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dz = ((Yfinal - Yinicial)/200)+1;
            dy = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Zmedio+5)) && (Yinicial >= (Zmedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dy = 1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Zmedio+5)) && (Yinicial >= (Zmedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = 1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+5)) && (Xinicial >= (Xmin-5))) && ((Yinicial <= (Zmin+5)) && (Yinicial >= (Zmin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200)+1;
            dz = ((Yinicial - Yfinal)/200)+1;
            dy = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmedio+5)) && (Xinicial >= (Xmedio-5))) && ((Yinicial <= (Zmin+5)) && (Yinicial >= (Zmin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dz = ((Yinicial - Yfinal)/200)+1;
            dy = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmax+5)) && (Xinicial >= (Xmax-5))) && ((Yinicial <= (Zmin+5)) && (Yinicial >= (Zmin-5))))
        {//correto
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = ((Xfinal - Xinicial)/200)+1;
            dz = ((Xfinal - Xinicial)/200)+1;
            //dy = ((Yinicial - Yfinal)/200)+1;
            //dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY();
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i]);
                ponto.setZ(M3[2][i] - 133.75 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade]);
                    ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else
        {
            return Poligon;
        }
    }
    
    public Poligono Escala3(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin, XminP, XmaxP, YminP, YmaxP, ZminP, ZmaxP;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        Zmax = Poligon.getListaP().get(0).getZ();
        Zmin = Poligon.getListaP().get(0).getZ();
        
        XmaxP = Poligon.getListaP().get(0).getX();
        XminP = Poligon.getListaP().get(0).getX();
        YmaxP = Poligon.getListaP().get(0).getY();
        YminP = Poligon.getListaP().get(0).getY();
        ZmaxP = Poligon.getListaP().get(0).getZ();
        ZminP = Poligon.getListaP().get(0).getZ();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
            if (Zmax < Poligon.getListaP().get(i).getZ())
            {
                Zmax = Poligon.getListaP().get(i).getZ();
            }
            if (Zmin > Poligon.getListaP().get(i).getZ())
            {
                Zmin = Poligon.getListaP().get(i).getZ();
            }
            if (XmaxP < Poligon.getListaP().get(i).getX())
            {
                XmaxP = Poligon.getListaP().get(i).getX();
            }
            if (XminP > Poligon.getListaP().get(i).getX())
            {
                XminP = Poligon.getListaP().get(i).getX();
            }
            if (YmaxP < Poligon.getListaP().get(i).getY())
            {
                YmaxP = Poligon.getListaP().get(i).getY();
            }
            if (YminP > Poligon.getListaP().get(i).getY())
            {
                YminP = Poligon.getListaP().get(i).getY();
            }
            if (ZmaxP < Poligon.getListaP().get(i).getZ())
            {
                ZmaxP = Poligon.getListaP().get(i).getZ();
            }
            if (ZminP > Poligon.getListaP().get(i).getZ())
            {
                ZminP = Poligon.getListaP().get(i).getZ();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Zmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Zmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                }
            }
        }
        
        Zmin += 181;
        Zmax += 181;
        Ymin += 133.75;
        Ymax += 133.75;
        
        double Xmedio, Ymedio, Zmedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        Zmedio = ((Zmax - Zmin)/2) + Zmin;
        
        Xmin -= 8;
        Xmax += 8;
        Ymin -= 8;
        Ymax += 8;
        Zmin -= 8;
        Zmax += 8;
        
        if (((Xinicial <= (Zmax+5)) && (Xinicial >= (Zmax-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {//certo
            //System.out.println("entrou xmax");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dz = ((Xfinal - Xinicial)/200)+1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dx = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i] - 181 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmin+5)) && (Xinicial >= (Zmin-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {//certo
            //System.out.println("entrou xmin");
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dz = ((Xinicial - Xfinal)/200)+1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dx = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i] - 181 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmedio+5)) && (Xinicial >= (Zmedio-5))) && ((Yinicial <= (Ymax+5)) && (Yinicial >= (Ymax-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yfinal - Yinicial)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i] - 181);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade] - 181);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmin+5)) && (Xinicial >= (Zmin-5))) && ((Yinicial <= (Ymedio+5)) && (Yinicial >= (Ymedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dz = ((Xinicial - Xfinal)/200)+1;
            dy = 1;
            dx = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i] - 181 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmax+5)) && (Xinicial >= (Zmax-5))) && ((Yinicial <= (Ymedio+5)) && (Yinicial >= (Ymedio-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dz = ((Xfinal - Xinicial)/200)+1;
            dy = 1;
            dx = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i] - 181 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmin+5)) && (Xinicial >= (Zmin-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dz = ((Xinicial - Xfinal)/200)+1;
            dy = ((Yinicial - Yfinal)/200)+1;
            dx = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmax;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmax;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i] - 181 + Zmax);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmax);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmedio+5)) && (Xinicial >= (Zmedio-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yinicial - Yfinal)/200)+1;
            dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i] - 181);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade] - 181);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Zmax+5)) && (Xinicial >= (Zmax-5))) && ((Yinicial <= (Ymin+5)) && (Yinicial >= (Ymin-5))))
        {//correto
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200)+1;
            dy = ((Xfinal - Xinicial)/200)+1;
            dz = ((Xfinal - Xinicial)/200)+1;
            //dy = ((Yinicial - Yfinal)/200)+1;
            //dz = 1;

            M1[0][0] = dx;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = dy;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = dz;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX();
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmin;
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmin;
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i]);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i] - 181 + Zmin);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade]);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade] - 181 + Zmin);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else
        {
            return Poligon;
        }
    }
    
    public Poligono Rotaciona1(double angulo)
    {
        double Xmax, Xmin, Ymax, Ymin;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                }
            }
        }
        
        Xmin += 181;
        Xmax += 181;
        Ymin += 133.75;
        Ymax += 133.75;
        
        Xmin -= 8;
        Xmax += 8;
        Ymin -= 8;
        Ymax += 8;
        
        double Xmedio, Ymedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        M1[0][0] = Math.cos(Math.toRadians(angulo));
        M1[0][1] = -(Math.sin(Math.toRadians(angulo)));
        M1[0][2] = 0;
        M1[0][3] = 0;

        M1[1][0] = Math.sin(Math.toRadians(angulo));
        M1[1][1] = Math.cos(Math.toRadians(angulo));
        M1[1][2] = 0;
        M1[1][3] = 0;

        M1[2][0] = 0;
        M1[2][1] = 0;
        M1[2][2] = 1;
        M1[2][3] = 0;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmedio;
            M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymedio;
            M2[2][i] = Poligon.getListaP().get(i).getZ();
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmedio;
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymedio;
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i] - 181 + Xmedio);
            ponto.setY(M3[1][i] - 133.75 + Ymedio);
            ponto.setZ(M3[2][i]);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade] - 181 + Xmedio);
                ponto.setY(M3[1][j + quantidade] - 133.75 + Ymedio);
                ponto.setZ(M3[2][j + quantidade]);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Rotaciona2(double angulo)
    {
        double Xmax, Xmin, Zmax, Zmin;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Zmax = Poligon.getListaP().get(0).getY();
        Zmin = Poligon.getListaP().get(0).getY();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Zmax < Poligon.getListaP().get(i).getZ())
            {
                Zmax = Poligon.getListaP().get(i).getZ();
            }
            if (Zmin > Poligon.getListaP().get(i).getZ())
            {
                Zmin = Poligon.getListaP().get(i).getZ();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Zmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Zmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                }
            }
        }
        
        Xmin += 181;
        Xmax += 181;
        Zmin += 133.75;
        Zmax += 133.75;
        
        Xmin -= 8;
        Xmax += 8;
        Zmin -= 8;
        Zmax += 8;
        
        double Xmedio, Zmedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Zmedio = ((Zmax - Zmin)/2) + Zmin;
        
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        M1[0][0] = Math.cos(Math.toRadians(angulo));
        M1[0][1] = 0;
        M1[0][2] = Math.sin(Math.toRadians(angulo));
        M1[0][3] = 0;

        M1[1][0] = 0;
        M1[1][1] = 1;
        M1[1][2] = 0;
        M1[1][3] = 0;

        M1[2][0] = -(Math.sin(Math.toRadians(angulo)));
        M1[2][1] = 0;
        M1[2][2] = Math.cos(Math.toRadians(angulo));
        M1[2][3] = 0;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmedio;
            M2[1][i] = Poligon.getListaP().get(i).getY();
            M2[2][i] = Poligon.getListaP().get(i).getZ() + 133.75 - Zmedio;
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmedio;
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 133.75 - Zmedio;
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i] - 181 + Xmedio);
            ponto.setY(M3[1][i]);
            ponto.setZ(M3[2][i] - 133.75 + Zmedio);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade] - 181 + Xmedio);
                ponto.setY(M3[1][j + quantidade]);
                ponto.setZ(M3[2][j + quantidade] - 133.75 + Zmedio);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Rotaciona3(double angulo)
    {
        double Zmax, Zmin, Ymax, Ymin;
        
        Zmax = Poligon.getListaP().get(0).getX();
        Zmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Zmax < Poligon.getListaP().get(i).getZ())
            {
                Zmax = Poligon.getListaP().get(i).getZ();
            }
            if (Zmin > Poligon.getListaP().get(i).getZ())
            {
                Zmin = Poligon.getListaP().get(i).getZ();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Zmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Zmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ())
                    {
                        Zmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                }
            }
        }
        
        Zmin += 181;
        Zmax += 181;
        Ymin += 133.75;
        Ymax += 133.75;
        
        Zmin -= 8;
        Zmax += 8;
        Ymin -= 8;
        Ymax += 8;
        
        double Zmedio, Ymedio;
        Zmedio = ((Zmax - Zmin)/2) + Zmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        
        int qtdPontos = 0;
        
        qtdPontos = Poligon.getListaP().size();
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        Poligono poli = new Poligono();
        
        double[][] M1 = new double [4][4];
        double[][] M2 = new double [4][qtdPontos];
        double[][] M3;
        
        M1[0][0] = 1;
        M1[0][1] = 0;
        M1[0][2] = 0;
        M1[0][3] = 0;

        M1[1][0] = 0;
        M1[1][1] = Math.cos(Math.toRadians(angulo));
        M1[1][2] = -(Math.sin(Math.toRadians(angulo)));
        M1[1][3] = 0;

        M1[2][0] = 0;
        M1[2][1] = Math.sin(Math.toRadians(angulo));
        M1[2][2] = Math.cos(Math.toRadians(angulo));
        M1[2][3] = 0;
        
        M1[3][0] = 0;
        M1[3][1] = 0;
        M1[3][2] = 0;
        M1[3][3] = 1;
        
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            M2[0][i] = Poligon.getListaP().get(i).getX();
            M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymedio;
            M2[2][i] = Poligon.getListaP().get(i).getZ() + 181 - Zmedio;
            M2[3][i] = 1;
        }
        
        int quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymedio;
                M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ() + 181 - Zmedio;
                M2[3][j + quantidade] = 1;
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
        }
        
        M3 = MultiplicaMatriz(M1, M2);
        
        ArrayList<Pontos> pontos = new ArrayList<>();
        for(int i = 0; i < Poligon.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            ponto.setX(M3[0][i]);
            ponto.setY(M3[1][i] - 133.75 + Ymedio);
            ponto.setZ(M3[2][i] - 181 + Zmedio);
            
            pontos.add(ponto);
        }
        poli.setListaP(pontos);
        
        ArrayList<Faces> faces = new ArrayList<>();
        quantidade = Poligon.getListaP().size();
        
        for(int i = 0; i < Poligon.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            pontos = new ArrayList<>();
            for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][j + quantidade]);
                ponto.setY(M3[1][j + quantidade] - 133.75 + Ymedio);
                ponto.setZ(M3[2][j + quantidade] - 181 + Zmedio);

                pontos.add(ponto);
            }
            quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poli.setListaFaces(faces);
        //poli.setContorno(Poligon.getContorno());
        //poli.setCor(Poligon.getCor());
        
        return poli;
    }
    
    public Poligono Cisalhamento1(double Xinicial, double Xfinal, double Yinicial, double Yfinal)
    {
        double Xmax, Xmin, Ymax, Ymin;
        
        Xmax = Poligon.getListaP().get(0).getX();
        Xmin = Poligon.getListaP().get(0).getX();
        Ymax = Poligon.getListaP().get(0).getY();
        Ymin = Poligon.getListaP().get(0).getY();
        for (int i = 0; i < Poligon.getListaP().size(); i++)
        {
            if (Xmax < Poligon.getListaP().get(i).getX())
            {
                Xmax = Poligon.getListaP().get(i).getX();
            }
            if (Xmin > Poligon.getListaP().get(i).getX())
            {
                Xmin = Poligon.getListaP().get(i).getX();
            }
            if (Ymax < Poligon.getListaP().get(i).getY())
            {
                Ymax = Poligon.getListaP().get(i).getY();
            }
            if (Ymin > Poligon.getListaP().get(i).getY())
            {
                Ymin = Poligon.getListaP().get(i).getY();
            }
        }
        if(Poligon.getListaFaces().isEmpty() == false)
        {
            for (int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    if (Xmax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Xmin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX())
                    {
                        Xmin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX();
                    }
                    if (Ymax < Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymax = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                    if (Ymin > Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY())
                    {
                        Ymin = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY();
                    }
                }
            }
        }
        
        Xmin += 181;
        Xmax += 181;
        Ymin += 133.75;
        Ymax += 133.75;
        
        double Xmedio, Ymedio;
        Xmedio = ((Xmax - Xmin)/2) + Xmin;
        Ymedio = ((Ymax - Ymin)/2) + Ymin;
        
        Xmin -= 8;
        Xmax += 8;
        Ymin -= 8;
        Ymax += 8;
        
        if (((Xinicial <= (Xmedio+8)) && (Xinicial >= (Xmedio-8))) && ((Yinicial <= (Ymax+4)) && (Yinicial >= (Ymax-4))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xfinal - Xinicial)/200);
            dy = 1;
            dz = 1;

            M1[0][0] = 1;
            M1[0][1] = dx;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = 1;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = 1;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymin;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymin;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i] - 133.75 + Ymin);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymin);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmin+4)) && (Xinicial >= (Xmin-4))) && ((Yinicial <= (Ymedio+8)) && (Yinicial >= (Ymedio-8))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yinicial - Yfinal)/200);
            dz = 1;

            M1[0][0] = 1;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = dy;
            M1[1][1] = 1;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = 1;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmax;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmax;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmax);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmax);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmax+4)) && (Xinicial >= (Xmax-4))) && ((Yinicial <= (Ymedio+8)) && (Yinicial >= (Ymedio-8))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = 1;
            dy = ((Yfinal - Yinicial)/200);
            dz = 1;

            M1[0][0] = 1;
            M1[0][1] = 0;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = dy;
            M1[1][1] = 1;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = 1;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181 - Xmin;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181 - Xmin;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181 + Xmin);
                ponto.setY(M3[1][i] - 133.75);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181 + Xmin);
                    ponto.setY(M3[1][j + quantidade] - 133.75);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else if (((Xinicial <= (Xmedio+8)) && (Xinicial >= (Xmedio-8))) && ((Yinicial <= (Ymin+4)) && (Yinicial >= (Ymin-4))))
        {
            int qtdPontos = 0;
        
            qtdPontos = Poligon.getListaP().size();
            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                qtdPontos += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            Poligono poli = new Poligono();

            double[][] M1 = new double [4][4];
            double[][] M2 = new double [4][qtdPontos];
            double[][] M3;

            double dx, dy, dz;

            dx = ((Xinicial - Xfinal)/200);
            dy = 1;
            dz = 1;

            M1[0][0] = 1;
            M1[0][1] = dx;
            M1[0][2] = 0;
            M1[0][3] = 0;

            M1[1][0] = 0;
            M1[1][1] = 1;
            M1[1][2] = 0;
            M1[1][3] = 0;

            M1[2][0] = 0;
            M1[2][1] = 0;
            M1[2][2] = 1;
            M1[2][3] = 0;

            M1[3][0] = 0;
            M1[3][1] = 0;
            M1[3][2] = 0;
            M1[3][3] = 1;

            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                M2[0][i] = Poligon.getListaP().get(i).getX() + 181;
                M2[1][i] = Poligon.getListaP().get(i).getY() + 133.75 - Ymax;
                M2[2][i] = Poligon.getListaP().get(i).getZ();
                M2[3][i] = 1;
            }

            int quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    M2[0][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getX() + 181;
                    M2[1][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getY() + 133.75 - Ymax;
                    M2[2][j + quantidade] = Poligon.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                    M2[3][j + quantidade] = 1;
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
            }

            M3 = MultiplicaMatriz(M1, M2);
            
            //to aqui

            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int i = 0; i < Poligon.getListaP().size(); i++)
            {
                Pontos ponto = new Pontos();
                ponto.setX(M3[0][i] - 181);
                ponto.setY(M3[1][i] - 133.75 + Ymax);
                ponto.setZ(M3[2][i]);

                pontos.add(ponto);
            }
            poli.setListaP(pontos);

            ArrayList<Faces> faces = new ArrayList<>();
            quantidade = Poligon.getListaP().size();

            for(int i = 0; i < Poligon.getListaFaces().size(); i++)
            {
                Faces face = new Faces();
                pontos = new ArrayList<>();
                for(int j = 0; j < Poligon.getListaFaces().get(i).getListaPFaces().size(); j++)
                {
                    Pontos ponto = new Pontos();
                    ponto.setX(M3[0][j + quantidade] - 181);
                    ponto.setY(M3[1][j + quantidade] - 133.75 + Ymax);
                    ponto.setZ(M3[2][j + quantidade]);

                    pontos.add(ponto);
                }
                quantidade += Poligon.getListaFaces().get(i).getListaPFaces().size();
                face.setListaPFaces(pontos);
                faces.add(face);
            }
            poli.setListaFaces(faces);
            //poli.setContorno(Poligon.getContorno());
            //poli.setCor(Poligon.getCor());

            return poli;
        }
        else
        {
            return Poligon;
        }
    }
}
