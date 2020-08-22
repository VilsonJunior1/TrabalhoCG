/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg.codigos;

/**
 *
 * @author Vilson
 */
public class FonteLuminosa {

    private double Lx;
    private double Ly;
    private double Lz;
    private double ILR;
    private double ILG;
    private double ILB;

    public FonteLuminosa() {
        this.Lx = 0;//100;
        this.Ly = 0;//-100;
        this.Lz = 0;//500;
        this.ILR = 0;//255;
        this.ILG = 0;//200;
        this.ILB = 0;//100;
    }

    public double getLx() {
        return Lx;
    }

    public void setLx(double Lx) {
        this.Lx = Lx;
    }

    public double getLy() {
        return Ly;
    }

    public void setLy(double Ly) {
        this.Ly = Ly;
    }

    public double getLz() {
        return Lz;
    }

    public void setLz(double Lz) {
        this.Lz = Lz;
    }

    public double getILR() {
        return ILR;
    }

    public void setILR(double ILR) {
        this.ILR = ILR;
    }

    public double getILG() {
        return ILG;
    }

    public void setILG(double ILG) {
        this.ILG = ILG;
    }

    public double getILB() {
        return ILB;
    }

    public void setILB(double ILB) {
        this.ILB = ILB;
    }

    
    
}
