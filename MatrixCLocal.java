import java.io.FileNotFoundException;

public class MatrixCLocal {

    public double[][] CLocal = new double[4][4];
    GlobalData globalData = new GlobalData();
    int length=4;

    public MatrixCLocal() throws FileNotFoundException {
    }

    public void calculate(UniversalElement elementUni, Jakobian jakobian){

        for(int i=0;i<CLocal.length;i++){
            for(int j=0; j< CLocal[i].length;j++){
                for(int k=0;k<elementUni.shapeFun.length;k++){
                    CLocal[i][j]+=(elementUni.shapeFun[k][j]*elementUni.shapeFun[k][i])*globalData.specificHeat*globalData.density*jakobian.detJ[k];
                }
            }
        }

    }
}
