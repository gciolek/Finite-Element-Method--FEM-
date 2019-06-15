import java.io.FileNotFoundException;

public class Menu {

    public static void main(String[] argv) throws FileNotFoundException {

        GlobalData globalData = new GlobalData();

        System.out.println(globalData);
        Grid grid = new Grid(globalData);
        grid.printNodes();
        grid.printElement();

        UniversalElement elementUni = new UniversalElement();
        elementUni.setShape();
        elementUni.setderiveNKsi();
        elementUni.setderiveNEta();
        elementUni.setShapeBC();
        //elementUni.printArray();


        for(int step=0;step<10;step++){

            double [][] HplusCdT = new double[globalData.nh][globalData.nh];
            double [] CdT = new double[globalData.nh];
            double [] VectorP_CdT = new double[globalData.nh];


            MatrixHGlobal matrixHGlobal = new MatrixHGlobal(grid, elementUni);
            //matrixHGlobal.print();

            MatrixCGlobal matrixCGlobal = new MatrixCGlobal(grid, elementUni);
            //matrixCGlobal.print();

            VectorPGlobal vectorPGlobal = new VectorPGlobal(grid,elementUni);
            //vectorPGlobal.print();

            for(int i=0;i<HplusCdT.length;i++){
                for(int j=0;j<HplusCdT.length;j++){
                    HplusCdT[i][j]=matrixHGlobal.HGlobal[i][j]+(matrixCGlobal.CGlobal[i][j]/globalData.simulationStepTime);
                    CdT[i]+=(matrixCGlobal.CGlobal[i][j]/globalData.simulationStepTime)*grid.nodesAll[j].t;
                }
                VectorP_CdT[i]=CdT[i]+vectorPGlobal.vectorPGlobal[i];
            }


            double [][] HplusP = new double[grid.nodesAll.length][grid.nodesAll.length+1];
            for(int i=0;i<grid.nodesAll.length;i++){
                for(int j=0; j<grid.nodesAll.length+1;j++){
                    if(j==grid.nodesAll.length){
                        HplusP[i][j]=VectorP_CdT[i];
                    }
                    else
                        HplusP[i][j]=HplusCdT[i][j];
                }
            }

            //solve problem with Gauss method
            double m, s;
            double[] searchValue = new double[grid.nodesAll.length];
            for (int i = 0; i < grid.nodesAll.length; i++) {
                searchValue[i] = 0;
            }

            //removal
            for (int i = 0; i < grid.nodesAll.length - 1; i++)	{
                for (int j = i + 1; j < grid.nodesAll.length; j++)	{

                    m = -HplusP[j][i] / HplusP[i][i];

                    for (int k = i + 1; k <= grid.nodesAll.length; k++)
                        HplusP[j][k] += m * HplusP[i][k];
                }
            }

            // calculate unknown values
            for (int i = grid.nodesAll.length - 1; i >= 0; i--){

                s = HplusP[i][grid.nodesAll.length];

                for (int j = grid.nodesAll.length - 1; j >= i + 1; j--)
                    s -= HplusP[i][j] * searchValue[j];

                searchValue[i] = s / HplusP[i][i];
            }


            for(int i=0;i<16;i++){
                System.out.print(searchValue[i]+" ");
                grid.nodesAll[i].t=searchValue[i];
            }
            System.out.println();

        }


    }
}
