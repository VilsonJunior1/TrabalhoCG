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
public class ProjecaoPerspectiva {
    
    private double[][] MultiplicaMatriz(double[][] M1, double[][] M2,int m,int n)
    {
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
    
    private double[][] MSruSrc(Pontos P, Pontos VRP)
    {
        double[][] matrizTranslacao = new double[4][4];
        
        matrizTranslacao[0][0] = 1;
        matrizTranslacao[0][1] = 0;
        matrizTranslacao[0][2] = 0;
        matrizTranslacao[0][3] = -VRP.getX();
        
        matrizTranslacao[1][0] = 0;
        matrizTranslacao[1][1] = 1;
        matrizTranslacao[1][2] = 0;
        matrizTranslacao[1][3] = -VRP.getY();
        
        matrizTranslacao[2][0] = 0;
        matrizTranslacao[2][1] = 0;
        matrizTranslacao[2][2] = 1;
        matrizTranslacao[2][3] = -VRP.getZ();
        
        matrizTranslacao[3][0] = 0;
        matrizTranslacao[3][1] = 0;
        matrizTranslacao[3][2] = 0;
        matrizTranslacao[3][3] = 1;

        Pontos aux = new Pontos();
        
        Pontos N = new Pontos();
        aux.setX(((VRP.getX())-(P.getX())));
        aux.setY(((VRP.getY())-(P.getY())));
        aux.setZ(((VRP.getZ())-(P.getZ())));
        
        double norma;
        
        norma = Math.sqrt(((aux.getX()*aux.getX()) + (aux.getY()*aux.getY()) + (aux.getZ()*aux.getZ())));
        
        N.setX(aux.getX()/norma);
        N.setY(aux.getY()/norma);
        N.setZ(aux.getZ()/norma);
        
        Pontos V = new Pontos();
        Pontos Y = new Pontos();
        aux = new Pontos();
        
        Y.setX(0);
        Y.setY(1);
        Y.setZ(0);
        
        norma = (Y.getX() * N.getX()) + (Y.getY() * N.getY()) + (Y.getZ() * N.getZ());
        
        Pontos aux2 = new Pontos();
        aux2.setX(N.getX()*norma);
        aux2.setY(N.getY()*norma);
        aux2.setZ(N.getZ()*norma);
        
        aux.setX(0 - aux2.getX());
        aux.setY(1 - aux2.getY());
        aux.setZ(0 - aux2.getZ());
        
        norma = Math.sqrt((aux.getX()*aux.getX()) + (aux.getY()*aux.getY()) + (aux.getZ()*aux.getZ()));
        
        V.setX(aux.getX() / norma);
        V.setY(aux.getY() / norma);
        V.setZ(aux.getZ() / norma);
        
        Pontos U = new Pontos();
        
        double valor;
        valor = (V.getY() * N.getZ()) - (V.getZ() * N.getY());
        
        U.setX(valor);
        
        valor = (V.getZ() * N.getX()) - (N.getZ() * V.getX());
        
        U.setY(valor);
        
        valor = (V.getX() * N.getY()) - (V.getY() * N.getX());
        
        U.setZ(valor);
        
        double[][] R = new double[4][4];
        
        R[0][0] = U.getX();
        R[0][1] = U.getY();
        R[0][2] = U.getZ();
        R[0][3] = 0;
        
        R[1][0] = V.getX();
        R[1][1] = V.getY();
        R[1][2] = V.getZ();
        R[1][3] = 0;
        
        R[2][0] = N.getX();
        R[2][1] = N.getY();
        R[2][2] = N.getZ();
        R[2][3] = 0;
        
        R[3][0] = 0;
        R[3][1] = 0;
        R[3][2] = 0;
        R[3][3] = 1;
        
        double[][] matrizFinal;
        matrizFinal = MultiplicaMatriz(R, matrizTranslacao, 4, 4);
        
        return matrizFinal;
    }
    
    private double[][] Perspectiva(Pontos P, Pontos VRP)
    {
        double[][] mSruSrc = new double [4][4];
        
        mSruSrc = MSruSrc(P, VRP);
        
        double Dp;
        
        double[][] aux = new double[4][4];
        double[][] aux2 = new double[4][1];
        
        aux2[0][0] = P.getX();
        aux2[1][0] = P.getY();
        aux2[2][0] = P.getZ();
        aux2[3][0] = 1;
        
        aux = MultiplicaMatriz(mSruSrc, aux2, 4, 1);
        
        Dp = -(aux[2][0]);
        
        double[][] MatrizPers = new double[4][4];
        
        MatrizPers[0][0] = 1;
        MatrizPers[0][1] = 0;
        MatrizPers[0][2] = 0;
        MatrizPers[0][3] = 0;
        
        MatrizPers[1][0] = 0;
        MatrizPers[1][1] = 1;
        MatrizPers[1][2] = 0;
        MatrizPers[1][3] = 0;
        
        MatrizPers[2][0] = 0;
        MatrizPers[2][1] = 0;
        MatrizPers[2][2] = 1;
        MatrizPers[2][3] = 0;
        
        MatrizPers[3][0] = 0;
        MatrizPers[3][1] = 0;
        MatrizPers[3][2] = -(1/Dp);
        MatrizPers[3][3] = 0;
        
        return MatrizPers;
    }
    
    private Poligono Pontos(Poligono poligono, Pontos P, Pontos VRP)
    {
        double[][] mSruSrc;
        
        mSruSrc = MSruSrc(P, VRP);
        
        int quantidadePontos;
        
        quantidadePontos = poligono.getListaP().size();
        
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            quantidadePontos += poligono.getListaFaces().get(i).getListaPFaces().size();
        }
        
        double[][] matrizPontos = new double[4][quantidadePontos];
        
        for(int i = 0; i < poligono.getListaP().size(); i++)
        {
            matrizPontos[0][i] = poligono.getListaP().get(i).getX();
            matrizPontos[1][i] = poligono.getListaP().get(i).getY();
            matrizPontos[2][i] = poligono.getListaP().get(i).getZ();
            matrizPontos[3][i] = 1;
        }
        
        int tamanho = poligono.getListaP().size();
        
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                matrizPontos[0][j + tamanho] = poligono.getListaFaces().get(i).getListaPFaces().get(j).getX();
                matrizPontos[1][j + tamanho] = poligono.getListaFaces().get(i).getListaPFaces().get(j).getY();
                matrizPontos[2][j + tamanho] = poligono.getListaFaces().get(i).getListaPFaces().get(j).getZ();
                matrizPontos[3][j + tamanho] = 1;
            }
            tamanho += poligono.getListaFaces().get(i).getListaPFaces().size();
        }
        
        double[][] matrizResultado = MultiplicaMatriz(mSruSrc, matrizPontos, 4, quantidadePontos);
        
        double[][] MPers = Perspectiva(P, VRP);
        
        double[][] matrizResultadoF = MultiplicaMatriz(MPers, matrizResultado, 4, quantidadePontos);
        
        for(int i = 0; i < quantidadePontos; i++)
        {
            matrizResultadoF[0][i] = (matrizResultadoF[0][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[1][i] = (matrizResultadoF[1][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[2][i] = (matrizResultadoF[2][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[3][i] = (matrizResultadoF[3][i]) / (matrizResultadoF[3][i]);
        }
        
        Poligono novo = new Poligono();
        
        ArrayList<Pontos> listaP = new ArrayList<>();
        
        for(int i = 0; i < poligono.getListaP().size(); i++)
        {
            Pontos ponto = new Pontos();
            
            ponto.setX(matrizResultado[0][i]);
            ponto.setY(matrizResultado[1][i]);
            ponto.setZ(matrizResultado[2][i]);
            
            listaP.add(ponto);
        }
        
        ArrayList<Faces> listaFaces = new ArrayList<>();
        tamanho = poligono.getListaP().size();
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            
            ArrayList<Pontos> ListaPFaces = new ArrayList<>();
            for(int j = 0; j < poligono.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(matrizResultado[0][j + tamanho]);
                ponto.setY(matrizResultado[1][j + tamanho]);
                ponto.setZ(matrizResultado[2][j + tamanho]);
                
                ListaPFaces.add(ponto);
            }
            tamanho += poligono.getListaFaces().get(i).getListaPFaces().size();
            face.setListaPFaces(ListaPFaces);
            listaFaces.add(face);
        }
        
        //novo.setContorno(poligono.getContorno());
        //novo.setCor(poligono.getCor());
        novo.setListaP(listaP);
        novo.setListaFaces(listaFaces);
        
        return novo;
    }
    
    public Poligono VisualizacaoPerspectiva(Poligono poligono, double X, double Y, double Z)
    {
        Pontos P = new Pontos();
        
        P.setX(0);
        P.setY(0);
        P.setZ(0);
        
        Pontos VRP = new Pontos();
        
        VRP.setX(X);
        VRP.setY(Y);
        VRP.setZ(Z);
        
        Poligono Final = new Poligono();
        Final = Pontos(poligono, P, VRP);
        
        return Final;
    }
    
    public Pontos atualizaVRP(double XO, double YO, double X, double Y, Pontos VRP)
    {
        Pontos Novo = new Pontos();
        
        double angulo = (X - XO);
        
        angulo /= 80.0;
        //double angulo = descobreAngulo(XO, X, YO, Y);
        
        double cos, sin;
        
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        
        double[][] matriz = new double[4][4];
        
        matriz[0][0] = cos;
        matriz[0][1] = 0;
        matriz[0][2] = -sin;
        matriz[0][3] = 0;
        
        matriz[1][0] = 0;
        matriz[1][1] = 1;
        matriz[1][2] = 0;
        matriz[1][3] = 0;
        
        matriz[2][0] = sin;
        matriz[2][1] = 0;
        matriz[2][2] = cos;
        matriz[2][3] = 0;
        
        matriz[3][0] = 0;
        matriz[3][1] = 0;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        
        double[][] matrizM = new double[4][1];
        
        matrizM[0][0] = VRP.getX();
        matrizM[1][0] = VRP.getY();
        matrizM[2][0] = VRP.getZ();
        matrizM[3][0] = 1;
        
        double[][] resposta;
        
        resposta = MultiplicaMatriz(matriz, matrizM, 4, 1);
        
        angulo = (Y - YO);
        
        //angulo = descobreAngulo(YO, Y, XO, X);
        angulo /= 80.0;
        
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        
        matriz[0][0] = 1;
        matriz[0][1] = 0;
        matriz[0][2] = 0;
        matriz[0][3] = 0;
        
        matriz[1][0] = 0;
        matriz[1][1] = cos;
        matriz[1][2] = -sin;
        matriz[1][3] = 0;
        
        matriz[2][0] = 0;
        matriz[2][1] = sin;
        matriz[2][2] = cos;
        matriz[2][3] = 0;
        
        matriz[3][0] = 0;
        matriz[3][1] = 0;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        
        double[][] respostaF;
        
        respostaF = MultiplicaMatriz(matriz, resposta, 4, 1);
        
        Novo.setX(respostaF[0][0]);
        Novo.setY(respostaF[1][0]);
        Novo.setZ(respostaF[2][0]);
        
        return Novo;
    }
    
    private double descobreAngulo(double XO, double X, double YO, double Y)
    {
        double x, y;
        
        x = ((X - XO) / 2) + XO;
        y = ((Y - YO) / 2) + YO;
        
        
        double angulo;
        
        double a, b, c;
        
        a = Math.sqrt((((XO - x)*(XO - x)) + ((YO - y)*(YO - y))));
        c = Math.sqrt((((XO - X)*(X - XO)) + ((Y - YO)*(Y - YO))));
        b = Math.sqrt((((X - x)*(X - x)) + ((Y - y)*(Y - y))));
        
        if(a < 0)
        {
            a = -a;
        }
        if(b < 0)
        {
            b = -b;
        }
        if(c < 0)
        {
            c = -c;
        }
        
        double resultado;
        
        resultado = ((c*c) - (a*a) - (b*b)) / ((-2)*a*b);
        
        angulo = 1 / Math.cos(Math.toRadians(resultado));
        
        return angulo;
    }
}
