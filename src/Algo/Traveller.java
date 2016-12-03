package Algo;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Konstantin on 02.12.2016.
 */
    public class Traveller {
        public static boolean TravelAcrossImage(String terrainImgPath, String resultPath, String illustrationPath) throws IOException {
            ImgProcessingResults preProcessedImage = MapScanner.GetGraph(terrainImgPath);
            preProcessedImage.InitializeGraph();
            int destPointNumber = FindPath(preProcessedImage);
            DrawResultImage(destPointNumber, terrainImgPath,resultPath,preProcessedImage);
//            int destPointNumber = FindLowestRadiationPathLimitedBy1300Moves(preProcessedImage, nodesCosts);
//            DrawResultImage(destPointNumber, "mapnew.png", resultPath, preProcessedImage, nodesCosts);
            return false;

        }

    static int  FindPath(ImgProcessingResults imgData){
            int targetPointNumber = A_Star(imgData);
//            int pathLength = CountPathLength(targetPointNumber, nodesCosts, preProcessedImage);
//            while(pathLength > STEPS_NUMBER_LIMIT){
//                stepCostCft = RedefineStepCostCft(stepCostCft, pathLength);
//                nodesCosts = InitializeNodesCosts(preProcessedImage);
//                targetPointNumber = A_Star(stepCostCft, preProcessedImage, nodesCosts);
//                pathLength = CountPathLength(targetPointNumber, nodesCosts, preProcessedImage);
//            }
            return targetPointNumber;
        }

    static int A_Star(ImgProcessingResults imgData){
            int imgWidth = imgData.width;
            int imgHeight = imgData.height;
            int startPointNumber = imgData.startPoint.y * imgWidth + imgData.startPoint.x;
            int finishPointNumber = imgData.finishPoint.y * imgWidth + imgData.finishPoint.x;
            Node []graph = imgData.getTerrainGraph();
            Queue<Node> openList = new PriorityQueue<>(imgHeight * imgWidth / 2, new NodeCostComparator());
            ArrayList<Node> closedList = new ArrayList<>();
            openList.add(graph[startPointNumber]);
            File f = new File("map.png");
            File resultFile = new File("map_dbg.png");
            int pixelColor = new Color(247, 255, 18).getRGB();
            try {
                BufferedImage image = ImageIO.read(f);
                while (!openList.isEmpty()) {
                    Node parent = openList.poll();
                    if (closedList.contains(parent))
                        continue;
                    double prntDisTo = parent.getG();
                    // for each child
                    for (Pair<Integer, Double> iterEdge :
                            graph[parent.getNodeNumber()].getNeighbours()
                            ) {
                        int childNodeNumber = iterEdge.getKey();
                        // set parent to current node
                        if (childNodeNumber == finishPointNumber) {
                            graph[childNodeNumber].setPrntNumber(parent.nodeNumber);
                            ImageIO.write(image, "png", resultFile);
                            return childNodeNumber;
                        }
                        int childH = graph[childNodeNumber].getH();
                        double stepCost = iterEdge.getValue();
                        double childDistTo = prntDisTo + stepCost;
                        double childF = childDistTo + childH;
                        if (openList.contains(graph[childNodeNumber]) &&
                                graph[childNodeNumber].getF() < childF)
                            continue;
                        if (closedList.contains(graph[childNodeNumber]) &&
                                graph[childNodeNumber].getF() < childF)
                            continue;
                        graph[childNodeNumber].setG(childDistTo);
                        graph[childNodeNumber].setPrntNumber(parent.getNodeNumber());
                        openList.add(graph[childNodeNumber]);
                    }

                    image.setRGB(parent.nodeNumber % imgWidth, parent.nodeNumber / imgWidth, pixelColor);



                    closedList.add(graph[parent.getNodeNumber()]);
                }

            }
            catch (IOException e) {

            }

            return -2;
        }

    static void DrawResultImage(int trgPoint, String path, String resultPath, ImgProcessingResults imgData){
        File f = new File(path);
        File resultFile = new File(resultPath);
        int imgWidth = imgData.getWidth();
        Node []graph = imgData.getTerrainGraph();
        int prntNumber = graph[trgPoint].getPrntNumber();
        int pixelColor = new Color(33, 25, 255).getRGB();
        try {
            BufferedImage image = ImageIO.read(f);
            while (prntNumber != -1){
                image.setRGB(prntNumber % imgWidth, prntNumber / imgWidth, pixelColor);
                prntNumber = graph[prntNumber].getPrntNumber();
            }
            ImageIO.write(image, "png", resultFile);
        }
        catch (IOException e) {

        }
    }
}
