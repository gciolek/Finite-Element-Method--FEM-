import java.io.FileNotFoundException;

public class MatrixCGlobal {

    public double [][] CGlobal;

    public MatrixCGlobal(Grid grid, UniversalElement elementUni) throws FileNotFoundException {
        CGlobal = new double[grid.nodesAll.length][grid.nodesAll.length];

        for(int i=0; i<grid.elementsAll.length;i++){
            //calculate matrix C local for each element
            grid.elementsAll[i].calculateCLocal(elementUni,grid.nodesAll);

            //agregation of matrix C local
            for(int j=0;j<grid.elementsAll[i].matrixCLocal.length;j++){
                for(int k=0;k<grid.elementsAll[i].matrixCLocal.length;k++){
                    CGlobal[grid.elementsAll[i].ID[j]][grid.elementsAll[i].ID[k]]+=grid.elementsAll[i].matrixCLocal.CLocal[j][k];
                }
            }

        }

    }

    public void print(){
        System.out.println("Matrix C Global");
        for(int i=0;i< CGlobal.length;i++){
            for(int j=0;j<CGlobal[i].length;j++){
                System.out.print(CGlobal[i][j]+ ", ");
            }
            System.out.println();
        }
    }
}
