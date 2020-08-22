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
public class OcultacaoDeFaces {
    
    private ArrayList<Faces> ordenaFaces(double[] vetor, ArrayList<Faces> poligono)
    {
        ArrayList<Faces> faces = new ArrayList<>();
        
        int ordemVet[] = new int[vetor.length];
        for(int i=0;i<ordemVet.length;i++){
            ordemVet[i]=-1;
        }
        int cont=-1;
        do
        {
            cont++;
            int aux=0;
            double min=99999999999999.0;
            for(int i = 0; i < poligono.size(); i++)
            {
                //System.out.println(i);
                boolean tem=false;
                for(int j=0;j<ordemVet.length;j++)
                {
                    
                    if(i==ordemVet[j]){
                        tem=true;
                    }
                        
                }
                if((!tem)&&(min>vetor[i])){
                    min=vetor[i];
                    aux=i;
                }
            }
                ordemVet[cont]=aux;
                
        }
        while(!(cont==vetor.length-1));
        Faces face = new Faces();
        for(int i = ordemVet.length-1; i >=0; i--)
        {
            int aux=ordemVet[i];
            face = new Faces();
            face.setListaPFaces(poligono.get(aux).getListaPFaces());
            
            faces.add(face);
            
        }
        return faces;
    }
    
    private double calculaFace(ArrayList<Pontos> pontos, Pontos VRP)
    {
        double Xmax, Xmin, Ymax, Ymin, Zmax, Zmin;
        
        Xmax = pontos.get(0).getX();
        Xmin = pontos.get(0).getX();
        Ymax = pontos.get(0).getY();
        Ymin = pontos.get(0).getY();
        Zmax = pontos.get(0).getZ();
        Zmin = pontos.get(0).getZ();
        
        for(int i = 0; i < pontos.size(); i++)
        {
            if(Xmax < pontos.get(i).getX())
            {
                Xmax = pontos.get(i).getX();
            }
            if(Xmin > pontos.get(i).getX())
            {
                Xmin = pontos.get(i).getX();
            }
            if(Ymax < pontos.get(i).getY())
            {
                Ymax = pontos.get(i).getY();
            }
            if(Ymin > pontos.get(i).getY())
            {
                Ymin = pontos.get(i).getY();
            }
            if(Zmax < pontos.get(i).getZ())
            {
                Zmax = pontos.get(i).getZ();
            }
            if(Zmin > pontos.get(i).getZ())
            {
                Zmin = pontos.get(i).getZ();
            }
        }
        
        double x, y, z;
        
        x = (Xmax + Xmin)/2;
        y = (Ymax + Ymin)/2;
        z = (Zmax + Zmin)/2;
        
        double d;
        
        d = Math.sqrt((((VRP.getX() - x)*(VRP.getX() - x)) + ((VRP.getY() - y)*(VRP.getY() - y)) + ((VRP.getZ() - z)*(VRP.getZ() - z))));
        
        return d;
    }
    
    private Poligono AlgoritmoDoPintor(Poligono poligono, Pontos VRP)
    {
        int tamanho;
        
        tamanho = poligono.getListaFaces().size()+1;
        
        ArrayList<Faces> faces = new ArrayList<>();
        
        Faces face = new Faces();
        
        face.setListaPFaces(poligono.getListaP());
        
        faces.add(face);
        
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            face = new Faces();
            
            face.setListaPFaces(poligono.getListaFaces().get(i).getListaPFaces());
            
            faces.add(face);
        }
        
        double[] vetor = new double[tamanho];
        
        vetor[0] = calculaFace(poligono.getListaP(), VRP);
        
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            vetor[i+1] = calculaFace(poligono.getListaFaces().get(i).getListaPFaces(), VRP);
        }
        
        
        ArrayList<Faces> facesF = new ArrayList<>();
        
        facesF = ordenaFaces(vetor, faces);
        
        Poligono pol = new Poligono();
        
        pol.setListaP(facesF.get(vetor.length-1).getListaPFaces());
        facesF.remove(vetor.length-1);
        pol.setListaFaces(facesF);
        
        return pol;
        
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
    
    private boolean FaceVisivel(ArrayList<Pontos> pontos, Pontos VRP, Pontos P)
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
        
        /*vetor1.setX(c.getX() - b.getX());
        vetor1.setY(c.getY() - b.getY());
        vetor1.setZ(c.getZ() - b.getZ());
        
        vetor2.setX(a.getX() - b.getX());
        vetor2.setY(a.getY() - b.getY());
        vetor2.setZ(a.getZ() - b.getZ());*/
        
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
        
        Pontos n = new Pontos();
        
        Pontos aux2 = new Pontos();
        
        aux2.setX(VRP.getX() - P.getX());
        aux2.setY(VRP.getY() - P.getY());
        aux2.setZ(VRP.getZ() - P.getZ());
        
        double norma2 = Math.sqrt((((aux2.getX())*(aux2.getX())) + ((aux2.getY())*(aux2.getY())) + ((aux2.getZ())*(aux2.getZ()))));
        
        n.setX(((aux2.getX())/norma2));
        n.setY(((aux2.getY())/norma2));
        n.setZ(((aux2.getZ())/norma2));
        
        double produtoEscalar;
        
        produtoEscalar = (N.getX()*n.getX()) + (N.getY()*n.getY()) + (N.getZ()*n.getZ());
        
        if(produtoEscalar >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private Poligono FacesVisiveis(Poligono pol, Pontos VRP, Pontos P)
    {
        Poligono poligono = new Poligono();
        
        //poligono.setContorno(pol.getContorno());
        //poligono.setCor(pol.getCor());
        poligono.setListaP(pol.getListaP());
        poligono.setKaB(pol.getKaB());
        poligono.setKaG(pol.getKaG());
        poligono.setKaR(pol.getKaR());
        poligono.setKdB(pol.getKdB());
        poligono.setKdG(pol.getKdG());
        poligono.setKdR(pol.getKdR());
        poligono.setKsB(pol.getKsB());
        poligono.setKsG(pol.getKsG());
        poligono.setKsR(pol.getKsR());
        poligono.setN(pol.getN());
        
        ArrayList<Faces> faces = new ArrayList<>();
        for(int i = 0; i < pol.getListaFaces().size(); i++)
        {
            Faces face = new Faces();
            
            ArrayList<Pontos> pontos = new ArrayList<>();
            for(int j = 0; j < pol.getListaFaces().get(i).getListaPFaces().size(); j++)
            {
                Pontos ponto = new Pontos();
                
                ponto.setX(pol.getListaFaces().get(i).getListaPFaces().get(j).getX());
                ponto.setY(pol.getListaFaces().get(i).getListaPFaces().get(j).getY());
                ponto.setZ(pol.getListaFaces().get(i).getListaPFaces().get(j).getZ());
                
                pontos.add(ponto);
            }
            face.setListaPFaces(pontos);
            faces.add(face);
        }
        poligono.setListaFaces(faces);
        
        //boolean inverte = false;
        for(int i = 0; i < poligono.getListaFaces().size(); i++)
        {
            /*if(i == poligono.getListaFaces().size()-5)
            {
                inverte = true;
            }*/
            
            boolean resposta;
            resposta = FaceVisivel(poligono.getListaFaces().get(i).getListaPFaces(), VRP, P);
            /*if(inverte == true)
            {
                resposta = !resposta;
            }*/
            if(resposta == false)
            {
                Faces face = new Faces();
                face.setListaPFaces(poligono.getListaFaces().get(i).getListaPFaces());
                face.setVisivel(false);
                
                /*ArrayList<Faces> ajuda = poligono.getListaFaces();
                ajuda.set(i, face);
                poligono.setListaFaces(ajuda);*/      
                
                ArrayList<Faces> ajuda = poligono.getListaFaces();
                ajuda.remove(i);
                poligono.setListaFaces(ajuda);
                i--;
            }
        }
        
        boolean resposta;
        resposta = FaceVisivel(poligono.getListaP(), VRP, P);
        if(resposta == false)
        {
            ArrayList<Faces> ajuda = poligono.getListaFaces();
            
            poligono.setListaP(ajuda.get(0).getListaPFaces());
            
            ajuda.remove(0);
            
            poligono.setListaFaces(ajuda);
            /*ArrayList<Pontos> novo = new ArrayList<>();
            
            poligono.setListaP(novo);*/
        }
        
        return poligono;
    }
    
    public Poligono OcultacaoFaces(Poligono poligono, Pontos VRP){
        
        Pontos P = new Pontos();
        
        P.setX(0);
        P.setY(0);
        P.setZ(0);
        
        Poligono resposta;
        
        resposta = FacesVisiveis(poligono, VRP, P);
        
        Poligono resposta2;
        
        resposta2 = AlgoritmoDoPintor(resposta, VRP);
        
        return resposta2;
    }
}
