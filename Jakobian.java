
public class Jakobian {

    public double [] detJ = new double[4];
    public double [][] matrixJac = new double[4][4];
    public double [][] inverseJac = new double[4][4];

    public Jakobian(UniversalElement universalElement, Node nodesAll[], int[] ID){
        for(int i=0;i<4;i++) {
            matrixJac[i][0] = universalElement.setderiveNKsi()[i][0] * nodesAll[ID[0]].x + universalElement.setderiveNKsi()[i][1] * nodesAll[ID[1]].x + universalElement.setderiveNKsi()[i][2] * nodesAll[ID[2]].x + universalElement.setderiveNKsi()[i][3] * nodesAll[ID[3]].x;
            matrixJac[i][1] = universalElement.setderiveNKsi()[i][0] * nodesAll[ID[0]].y + universalElement.setderiveNKsi()[i][1] * nodesAll[ID[1]].y + universalElement.setderiveNKsi()[i][2] * nodesAll[ID[2]].y + universalElement.setderiveNKsi()[i][3] * nodesAll[ID[3]].y;
            matrixJac[i][2] = universalElement.setderiveNEta()[i][0] * nodesAll[ID[0]].x + universalElement.setderiveNEta()[i][1] * nodesAll[ID[1]].x + universalElement.setderiveNEta()[i][2] * nodesAll[ID[2]].x + universalElement.setderiveNEta()[i][3] * nodesAll[ID[3]].x;
            matrixJac[i][3] = universalElement.setderiveNEta()[i][0] * nodesAll[ID[0]].y + universalElement.setderiveNEta()[i][1] * nodesAll[ID[1]].y + universalElement.setderiveNEta()[i][2] * nodesAll[ID[2]].y + universalElement.setderiveNEta()[i][3] * nodesAll[ID[3]].y;

            detJ[i] = matrixJac[i][0] * matrixJac[i][3] - matrixJac[i][1] * matrixJac[i][2];


            inverseJac[i][0] = matrixJac[i][3]/detJ[i];
            inverseJac[i][1] = -matrixJac[i][2]/detJ[i];
            inverseJac[i][2] = -matrixJac[i][1]/detJ[i];
            inverseJac[i][3] = matrixJac[i][0]/detJ[i];

        }

    }


    public void printJac() {
        System.out.println("Jacobi matrix");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrixJac[j][i] + "  ");
            }
            System.out.println();
        }
    }

    public void printInverseJac(double [][] inverseJac) {
        System.out.println("Inverted Jacobi matrix");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(inverseJac[j][i] + "  ");
                }
            System.out.println();
            }
        }
}

