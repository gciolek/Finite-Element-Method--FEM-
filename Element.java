import java.io.FileNotFoundException;

public class Element {
    GlobalData globalData = new GlobalData();
    public int [] ID ;
    public MatrixHLocal matrixHLocal = new MatrixHLocal();
    public MatrixCLocal matrixCLocal = new MatrixCLocal();
    public double [][] MatrixHBC = new double[4][4];
    public double [] vectorPLocal = new double[4];

    public Element(int []IDies) throws FileNotFoundException {
        ID = new int[4];
        for(int i=0;i<4;i++){
            ID[i] = IDies[i];
        }
    }

    public void calculateHLocal(UniversalElement elementUni, Node []nodesAll) throws FileNotFoundException {
        Jakobian jakobian = new Jakobian(elementUni,nodesAll,ID);
        matrixHLocal.calculate(elementUni, jakobian);

    }

    public void calculateCLocal(UniversalElement elementUni, Node []nodesAll) throws FileNotFoundException {
        Jakobian jakobian = new Jakobian(elementUni,nodesAll,ID);
        matrixCLocal.calculate(elementUni, jakobian);

    }

    public void calculateHBC(UniversalElement elementUni, Node []nodesAll){
        for(int i=0;i<4;i++){
            if(i==3){
                if(nodesAll[ID[0]].BC && nodesAll[ID[3]].BC){
                    double detjJBC = Math.sqrt(Math.pow( nodesAll[ID[3]].x - nodesAll[ID[0]].x,2) + Math.pow( nodesAll[ID[3]].y - nodesAll[ID[0]].y,2))/2;
                    for(int j=0;j<MatrixHBC.length;j++){
                        for(int k=0;k<MatrixHBC.length;k++){
                            MatrixHBC[j][k]+=globalData.alfa*elementUni.shapeFunBC[i][0][j]*elementUni.shapeFunBC[i][0][k]*detjJBC;
                            MatrixHBC[j][k]+=globalData.alfa*elementUni.shapeFunBC[i][1][j]*elementUni.shapeFunBC[i][1][k]*detjJBC;
                        }
                    }

                }
                break;
            }
            if(nodesAll[ID[i]].BC && nodesAll[ID[i+1]].BC){
                double detjJBC = Math.sqrt(Math.pow( nodesAll[ID[3]].x - nodesAll[ID[0]].x,2) + Math.pow( nodesAll[ID[3]].y - nodesAll[ID[0]].y,2))/2;
                for(int j=0;j<MatrixHBC.length;j++){
                    for(int k=0;k<MatrixHBC.length;k++){
                        MatrixHBC[j][k]+=globalData.alfa*elementUni.shapeFunBC[i][0][j]*elementUni.shapeFunBC[i][0][k]*detjJBC;
                        MatrixHBC[j][k]+=globalData.alfa*elementUni.shapeFunBC[i][1][j]*elementUni.shapeFunBC[i][1][k]*detjJBC;
                    }
                }
            }
        }

    }

    //calculate local vector P
    public void calculateVectorP(UniversalElement elementUni, Node []nodesAll){
        for(int i=0;i<4;i++){
            if(i==3){
                if(nodesAll[ID[0]].BC && nodesAll[ID[3]].BC){
                    double detjJP = Math.sqrt(Math.pow( nodesAll[ID[3]].x - nodesAll[ID[0]].x,2) + Math.pow( nodesAll[ID[3]].y - nodesAll[ID[0]].y,2))/2;
                    for(int j=0;j<MatrixHBC.length;j++){
                        vectorPLocal[j]+=globalData.alfa*globalData.ambientTemp*elementUni.shapeFunBC[i][0][j]*detjJP;
                        vectorPLocal[j]+=globalData.alfa*globalData.ambientTemp*elementUni.shapeFunBC[i][1][j]*detjJP;
                    }

                }
                break;
            }
            if(nodesAll[ID[i]].BC && nodesAll[ID[i+1]].BC){
                double detjJP = Math.sqrt(Math.pow( nodesAll[ID[3]].x - nodesAll[ID[0]].x,2) + Math.pow( nodesAll[ID[3]].y - nodesAll[ID[0]].y,2))/2;
                for(int j=0;j<MatrixHBC.length;j++){
                    vectorPLocal[j]+=globalData.alfa*globalData.ambientTemp*elementUni.shapeFunBC[i][0][j]*detjJP;
                    vectorPLocal[j]+=globalData.alfa*globalData.ambientTemp*elementUni.shapeFunBC[i][1][j]*detjJP;

                }
            }
        }
    }

}
