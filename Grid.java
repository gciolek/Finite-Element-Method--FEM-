import java.io.FileNotFoundException;

public class Grid {
    public Node[] nodesAll;
    public Element[] elementsAll;

    public Grid(GlobalData globalData) throws FileNotFoundException {
        createNodes(globalData);
        createElements(globalData);
    }

    private void createNodes(GlobalData globalData){
        nodesAll = new Node[globalData.nh];
        double varX=0, varY=0;
        boolean BC;
        for(int i=0;i<globalData.nh;i++,varY+=globalData.dy){
            if(i%(globalData.getnH())==0 && i!=0){
                varY=0;
                varX+=globalData.dx;
            }
            if(varX==0||varY==0||varX==(globalData.getnH()-1)*globalData.dx||varY==(globalData.getnL()-1)*globalData.dy){
                BC=true;
            }
            else
                BC=false;
            nodesAll[i]=new Node(varX,varY,globalData.initTemp,BC);
        }

    }

    private void createElements(GlobalData globalData) throws FileNotFoundException {
        elementsAll = new Element[globalData.ne];
        int [] elementID = new int[4];
        for(int i=0, j=0;i<globalData.ne;i++,j++){
            if(i%(globalData.getnH()-1)==0 && i!=0){
                j++;
            }
            elementID[0]=j;
            elementID[1]=j+globalData.getnH();
            elementID[2]=j+globalData.getnH()+1;
            elementID[3]=elementID[0]+1;
            elementsAll[i] = new Element(elementID);
        }
    }

    void printNodes() {
        System.out.println("\n");
        for(int i=0;i<nodesAll.length;i++) {
            System.out.println("Węzęł" + i+ " x: " + nodesAll[i].x + ", y: " + nodesAll[i].y+ " BC="+ nodesAll[i].BC );
        }
        System.out.println("\n");


    }
    public void printElement() {
        for(int i=0;i<elementsAll.length;i++) {
            System.out.print("Element"+ i+ " ID"+ " ");
            for (int j = 0; j < 4; j++) {
                System.out.print(elementsAll[i].ID[j]+ " , ");
                if(j==3){
                    System.out.println("\n");
                }
            }
        }

    }
}
