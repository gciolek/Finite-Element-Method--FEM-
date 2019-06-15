import java.io.FileNotFoundException;

public class VectorPGlobal {
    public double [] vectorPGlobal;

    public VectorPGlobal(Grid grid, UniversalElement elementUni) throws FileNotFoundException {
        vectorPGlobal = new double[grid.nodesAll.length];

        for(int i=0; i<grid.elementsAll.length;i++){

            //calculate vector P local for each element
            grid.elementsAll[i].calculateVectorP(elementUni, grid.nodesAll);

            //agregation of vector P local to P global
            for(int j=0;j<grid.elementsAll[i].vectorPLocal.length;j++){
                    vectorPGlobal[grid.elementsAll[i].ID[j]]+=grid.elementsAll[i].vectorPLocal[j];
            }

        }

    }


    public void print(){
        System.out.println("Vector P");
        for(int i=0;i<vectorPGlobal.length;i++){
            System.out.print(vectorPGlobal[i]+ ", ");
        }
    }
}

