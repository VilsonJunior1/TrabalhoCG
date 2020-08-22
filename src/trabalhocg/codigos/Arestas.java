/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

/**
 *
 * @author vilson
 */
public class Arestas {
    private double X1;
    private double Y1;
    private double X2;
    private double Y2;
    private double M;

    public Arestas() {
        this.X1 = 0;
        this.Y1 = 0;
        this.X2 = 0;
        this.Y2 = 0;
        this.M = 0;
    }

    public double getX1() {
        return X1;
    }

    public void setX1(double X1) {
        this.X1 = X1;
    }

    public double getY1() {
        return Y1;
    }

    public void setY1(double Y1) {
        this.Y1 = Y1;
    }

    public double getX2() {
        return X2;
    }

    public void setX2(double X2) {
        this.X2 = X2;
    }

    public double getY2() {
        return Y2;
    }

    public void setY2(double Y2) {
        this.Y2 = Y2;
    }

    public double getM() {
        return M;
    }

    public void setM(double M) {
        this.M = M;
    }
}
