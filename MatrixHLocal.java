import java.io.FileNotFoundException;

public class MatrixHLocal {
    GlobalData globalData = new GlobalData();
    public int length =4;
    public double[][] HLocal = new double[4][4];
    private double[][] dN_dx = new double[4][4];
    private double[][] dN_dy = new double[4][4];

    public MatrixHLocal() throws FileNotFoundException {
    }

    public void calculate(UniversalElement elementUni, Jakobian jakobian){
        setdN_dxMatrix( jakobian.inverseJac, elementUni.derivNKsi,elementUni.derivNEta);
        setdN_dyMatrix(jakobian.inverseJac, elementUni.derivNKsi, elementUni.derivNEta);

        for(int i=0;i<HLocal.length;i++){
            for(int j=0; j< HLocal[i].length;j++){
                for(int k=0;k<dN_dx.length;k++){
                    HLocal[i][j]+=(dN_dx[k][j]*dN_dx[k][i])*globalData.conductivity*jakobian.detJ[k];
                    HLocal[i][j]+=(dN_dy[k][j]*dN_dy[k][i])*globalData.conductivity*jakobian.detJ[k];
                }
            }
        }

    }

    private void setdN_dxMatrix(double [][]inverseJac, double [][]derivNKsi, double[][]derivNEta){

        //fill matrix dN/dx
        for(int i=0;i<4;i++) {
            dN_dx[0][i] = inverseJac[0][0] * derivNKsi[0][i] + inverseJac[0][1] * derivNEta[0][i];
            dN_dx[1][i] = inverseJac[1][0] * derivNKsi[1][i] + inverseJac[1][1] * derivNEta[1][i];
            dN_dx[2][i] = inverseJac[2][0] * derivNKsi[2][i] + inverseJac[2][1] * derivNEta[2][i];
            dN_dx[3][i] = inverseJac[3][0] * derivNKsi[3][i] + inverseJac[3][1] * derivNEta[3][i];

        }

    }

    private void setdN_dyMatrix(double [][]inverseJac, double [][]derivNKsi, double[][]derivNEta) {

        //fill matrix dN/dy
        for (int i = 0; i < 4; i++) {
            dN_dy[0][i] = inverseJac[0][2] * derivNKsi[0][i] + inverseJac[0][3] * derivNEta[0][i];
            dN_dy[1][i] = inverseJac[1][2] * derivNKsi[1][i] + inverseJac[1][3] * derivNEta[1][i];
            dN_dy[2][i] = inverseJac[2][2] * derivNKsi[2][i] + inverseJac[2][3] * derivNEta[2][i];
            dN_dy[3][i] = inverseJac[3][2] * derivNKsi[3][i] + inverseJac[3][3] * derivNEta[3][i];

        }
    }

}
