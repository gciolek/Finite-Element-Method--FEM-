import static java.lang.Math.sqrt;

public class UniversalElement {
    public double [][] derivNKsi = new double[4][4];
    public double [][] derivNEta = new double[4][4];
    public final double[][] shapeFun = new double[4][4];

    public double[][][] shapeFunBC = new double[4][2][4];

    public final double [] ksi = {(-1)/(sqrt(3)) , 1/(sqrt(3)) , 1/(sqrt(3)), (-1)/(sqrt(3))};
    public final double [] eta = {(-1)/(sqrt(3)) , (-1)/(sqrt(3)) , 1/(sqrt(3)), 1/(sqrt(3))};

    public double []ksiBC = new double[]{(-1)/(sqrt(3)) , 1/(sqrt(3)) , 1, 1 , 1/(sqrt(3)), (-1)/(sqrt(3)), -1 , -1};
    public double []etaBC = new double[]{-1, -1, (-1)/(sqrt(3)) , 1/(sqrt(3)), 1, 1 , 1/(sqrt(3)), (-1)/(sqrt(3))};

    //fill array with shape functions for integration points
    public void setShape(){
        for(int i=0;i<4;i++){
            shapeFun[i][0]=0.25*((1.0-ksi[i])*(1.0-eta[i]));
            shapeFun[i][1]=0.25*((1.0+ksi[i])*(1.0-eta[i]));
            shapeFun[i][2]=0.25*((1.0+ksi[i])*(1.0+eta[i]));
            shapeFun[i][3]=0.25*((1.0-ksi[i])*(1.0+eta[i]));
        }
    }

    //fill array with dN/dKsi for integration points
    public double[][] setderiveNKsi(){
        for(int i=0; i<4; i++){
            derivNKsi[i][0] = (-0.25*(1.0-eta[i]));
            derivNKsi[i][1] = (0.25*(1.0-eta[i]));
            derivNKsi[i][2] = (0.25*(1.0+eta[i]));
            derivNKsi[i][3] = (-0.25*(1.0+eta[i]));
        }
        return derivNKsi;
    }

    //fill array with dN/dEta for integration points
    public double[][] setderiveNEta(){
        for(int i=0; i<4; i++){
            derivNEta[i][0] = (-0.25*(1.0-ksi[i]));
            derivNEta[i][1] = (-0.25*(1.0+ksi[i]));
            derivNEta[i][2] = (0.25*(1.0+ksi[i]));
            derivNEta[i][3] = (0.25*(1.0-ksi[i]));
        }
        return derivNEta;
    }

    public void setShapeBC(){
        for(int i=0;i<2;i++) {
            shapeFunBC[0][i][0] = 0.25 * ((1.0 - ksiBC[i]) * (1.0 - etaBC[i]));
            shapeFunBC[0][i][1] = 0.25 * ((1.0 + ksiBC[i]) * (1.0 - etaBC[i]));
            shapeFunBC[0][i][2] = 0.25 * ((1.0 + ksiBC[i]) * (1.0 + etaBC[i]));
            shapeFunBC[0][i][3] = 0.25 * ((1.0 - ksiBC[i]) * (1.0 + etaBC[i]));

        }
        for(int i=0;i<2;i++) {
            shapeFunBC[1][i][0] = 0.25 * ((1.0 - ksiBC[i+2]) * (1.0 - etaBC[i+2]));
            shapeFunBC[1][i][1] = 0.25 * ((1.0 + ksiBC[i+2]) * (1.0 - etaBC[i+2]));
            shapeFunBC[1][i][2] = 0.25 * ((1.0 + ksiBC[i+2]) * (1.0 + etaBC[i+2]));
            shapeFunBC[1][i][3] = 0.25 * ((1.0 - ksiBC[i+2]) * (1.0 + etaBC[i+2]));

        }
        for(int i=0;i<2;i++) {
            shapeFunBC[2][i][0] = 0.25 * ((1.0 - ksiBC[i+4]) * (1.0 - etaBC[i+4]));
            shapeFunBC[2][i][1] = 0.25 * ((1.0 + ksiBC[i+4]) * (1.0 - etaBC[i+4]));
            shapeFunBC[2][i][2] = 0.25 * ((1.0 + ksiBC[i+4]) * (1.0 + etaBC[i+4]));
            shapeFunBC[2][i][3] = 0.25 * ((1.0 - ksiBC[i+4]) * (1.0 + etaBC[i+4]));

        }
        for(int i=0;i<2;i++) {
            shapeFunBC[3][i][0] = 0.25 * ((1.0 - ksiBC[i+6]) * (1.0 - etaBC[i+6]));
            shapeFunBC[3][i][1] = 0.25 * ((1.0 + ksiBC[i+6]) * (1.0 - etaBC[i+6]));
            shapeFunBC[3][i][2] = 0.25 * ((1.0 + ksiBC[i+6]) * (1.0 + etaBC[i+6]));
            shapeFunBC[3][i][3] = 0.25 * ((1.0 - ksiBC[i+6]) * (1.0 + etaBC[i+6]));

        }
    }


    public void printArray() {
        System.out.println("Array N ");
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(shapeFun[j][i]+"  ");
                if(j==3){
                    System.out.println("\n");
                }
            }
        }
        System.out.println("\n");

        System.out.println("Array dN/dKsi");

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(derivNKsi[j][i]+ "  ");
                if(j==3){
                    System.out.println("\n");
                }
            }
        }
        System.out.println("\n");

        System.out.println("Array dN/dEta");
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(derivNEta[j][i]+"  ");
                if(j==3){
                    System.out.println("\n");
                }
            }
        }

        System.out.println("Array Shape BC");
        for(int i=0;i<4;i++) {
            System.out.println(" Powierzchnia"+(i+1));
            for (int j = 0; j < 2; j++) {
                System.out.println(" PC"+(j+1));
                for (int k = 0; k < 4; k++) {
                    System.out.print(" N"+(k+1));
                    System.out.print(shapeFunBC[i][j][k] + "  ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}

