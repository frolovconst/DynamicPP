package Algo;

import java.util.Comparator;

/**
 * Created by Carioca on 03/12/2016.
 */
public class NodeCostComparator implements Comparator<Node> {
    @Override
    public int compare(Node Node1, Node Node2){
        if(Node1.getF() < Node2.getF()){
            return -1;
        }
        if(Node1.getF() == Node2.getF())
            return 0;
        else
            return 1;
    }

}
