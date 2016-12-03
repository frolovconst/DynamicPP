package Algo;

import javafx.util.Pair;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Konstantin on 02.12.2016.
 */
public class Node {
    int h;
    double f;
    double g;
    int nodeNumber;
    int prntNumber;
    Point coords;
    LinkedList<Pair<Integer, Double>> neighbours;

    public Node(LinkedList<Pair<Integer, Double>> neighbours){
        this.neighbours = neighbours;
    }

    public Node(int img_dim, int dstNum, int myNum) {
//        Pair<Integer, Integer> myCoords = OneDimToTwoDim(myNum, img_dim);
//        Pair<Integer, Integer> dstCoords = OneDimToTwoDim(dstNum, img_dim);
//        this.h = Math.abs(dstCoords.getKey() - myCoords.getKey()) + Math.abs(dstCoords.getValue() - myCoords.getValue());
        this.f = 0.0;
        this.g = 0.0;
        this.nodeNumber = myNum;
        this.prntNumber = -1;
    }

    public boolean InitializeNode(int imgWidth, Point dest, Point myCoords){
        this.coords = myCoords;
        this.h = Math.abs(dest.x - myCoords.x) + Math.abs(dest.y - myCoords.y);
        this.f = 0.0;
        this.g = 0.0;
        this.nodeNumber = TwoDimToOneDim(imgWidth, myCoords);
        this.prntNumber = -1;
        return false;
    }

    public void setG(double g){
        this.g = g;
        this.f = this.h + g;
    }

    int TwoDimToOneDim(int width, Point coords){
        return coords.y * width + coords.x;
    }

    Point OneDimToTwoDim(int Pos, int img_dim){
        int xCoord = Pos % img_dim;
        int yCoord = Pos / img_dim;
        return new Point(xCoord, yCoord);
    }

    public int getH() {
        return h;
    }

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public int getPrntNumber() {
        return prntNumber;
    }

    public void setPrntNumber(int prntNumber) {
        this.prntNumber = prntNumber;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public LinkedList<Pair<Integer, Double>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(LinkedList<Pair<Integer, Double>> neighbours) {
        this.neighbours = neighbours;
    }
}
