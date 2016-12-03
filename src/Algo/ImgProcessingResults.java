package Algo;

import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Carioca on 03/12/2016.
 */
public class ImgProcessingResults {
    public int width;
    public int height;
    public Node []terrainGraph ;
    public Point startPoint;
    public Point finishPoint;

    public ImgProcessingResults(Node []graph, Point start, Point finish, int height, int width){
        this.terrainGraph = graph;
        this.width = width;
        this.height = height;
        this.startPoint = start;
        this.finishPoint = finish;
    }

    public boolean InitializeGraph(){
        for (int i = 0; i < height; i++){
            for (int j = 0 ; j < width; j++){
                terrainGraph[i * width + j].InitializeNode(width, finishPoint, new Point(j,i));
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node[] getTerrainGraph() {
        return terrainGraph;
    }

    public void setTerrainGraph(Node[] terrainGraph) {
        this.terrainGraph = terrainGraph;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }
}
