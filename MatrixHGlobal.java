import java.io.FileNotFoundException;

public class MatrixHGlobal {

    public double[][] HGlobal;

    public MatrixHGlobal(Grid grid, UniversalElement elementUni) throws FileNotFoundException {
        HGlobal = new double[grid.nodesAll.length][grid.nodesAll.length];

        for(int i=0; i<grid.elementsAll.length;i++){
            //calculate matrix H local for each element
            grid.elementsAll[i].calculateHLocal(elementUni,grid.nodesAll);

            //calculate matrix H local with boundary conditions for each element
            grid.elementsAll[i].calculateHBC(elementUni, grid.nodesAll);

            //agregation of matrix H local and matrix H with boundary condition
            for(int j=0;j<grid.elementsAll[i].matrixHLocal.length;j++){
                for(int k=0;k<grid.elementsAll[i].matrixHLocal.length;k++){
                    HGlobal[grid.elementsAll[i].ID[j]][grid.elementsAll[i].ID[k]]+=grid.elementsAll[i].matrixHLocal.HLocal[j][k];
                    HGlobal[grid.elementsAll[i].ID[j]][grid.elementsAll[i].ID[k]]+=grid.elementsAll[i].MatrixHBC[j][k];
                }
            }

        }

    }


    public void print(){
        System.out.println("Matrix H global");
        for(int i=0;i<HGlobal.length;i++){
            for(int j=0;j<HGlobal[i].length;j++){
                System.out.print(HGlobal[i][j]+ ", ");
            }
            System.out.println();
        }
    }
}
