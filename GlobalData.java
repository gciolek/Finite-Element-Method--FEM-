import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GlobalData {
    private double H; //height
    private double L; //width
    private int nH;  //amount of nodes by H
    private int nL;  //amount od nodes by L
    public int ne;  //amount of all elements
    public int nh;  //amount of all nodes
    public double dx; //distance between nodes by L
    public double dy; //distance between nodes by H

    public int initTemp;
    public int simulationTime;
    public int simulationStepTime;
    public int ambientTemp;
    public int alfa;
    public int specificHeat;
    public int conductivity;
    public int density;

    public GlobalData() throws FileNotFoundException {
        File file = new File("C:\\Users\\ciole\\data.txt");
        Scanner read = new Scanner(file);

        this.H = read.nextDouble();
        this.L = read.nextDouble();
        this.nH = read.nextInt();
        this.nL = read.nextInt();

        this.initTemp = read.nextInt();
        this.simulationTime = read.nextInt();
        this.simulationStepTime = read.nextInt();
        this.ambientTemp = read.nextInt();
        this.alfa = read.nextInt();
        this.specificHeat = read.nextInt();
        this.conductivity = read.nextInt();
        this.density = read.nextInt();

        dx = L / (nL - 1);
        dy = H / (nH - 1);
        nh = nL * nH;
        ne = (nL - 1) * (nH - 1);


    }

    public int getnH() {
        return nH;
    }

    public int getnL() {
        return nL;
    }


    @Override
    public String toString() {
        return "GlobalData{" + "\n" +
                " H=" + H + "\n" +
                " L=" + L + "\n" +
                " nH=" + nH + "\n" +
                " nL=" + nL + "\n" +
                " ne=" + ne + "\n" +
                " nh=" + nh + "\n" +
                " dx=" + dx + "\n" +
                " dy=" + dy + "\n" +
                " initTemp=" + initTemp + "\n" +
                " simulationTime=" + simulationTime + "\n" +
                " simulationStepTime=" + simulationStepTime + "\n" +
                " ambientTemp=" + ambientTemp + "\n" +
                " alfa=" + alfa + "\n" +
                " specificHeat=" + specificHeat + "\n" +
                " conductivity=" + conductivity + "\n" +
                " density=" + density + "\n" +
                '}';
    }
}