package Algo;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Konstantin on 02.12.2016.
 */
public final class MapScanner {
    private static final int _BLACK = -16777216;

    public static ImgProcessingResults GetGraph(String terrainImgPath) throws IOException {
        File f = new File(terrainImgPath);
        BufferedImage image = ImageIO.read(f);
        int width = image.getWidth();
        int height = image.getHeight();
        Node[] graph = new Node[width * height];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int crntClr = image.getRGB(j, i);
                LinkedList<Pair<Integer, Double>> neighbours = new LinkedList<>();
                if(crntClr > _BLACK){
                    if (i > 0 && image.getRGB(j, i - 1) > _BLACK ) { // upper pixel
                        neighbours.add(new Pair<>((i-1) * width + j, 1.0));
                    }
                    if (i > 0 && j < width - 1 && image.getRGB(j + 1, i - 1) > _BLACK) { // upper right pixel
                        neighbours.add(new Pair<>((i-1) * width + j + 1, 1.4));
                    }
                    if (j < width - 1 && image.getRGB(j + 1, i) > _BLACK) { // right pixel
                        neighbours.add(new Pair<>(i * width + j + 1, 1.0));
                    }
                    if (i < height - 1 && j < width - 1 && image.getRGB(j + 1, i + 1) > _BLACK) { // lower right pixel
                        neighbours.add(new Pair<>((i+1) * width + j + 1, 1.4));
                    }
                    if (i < height - 1 && image.getRGB(j, i + 1) > _BLACK) { // lower pixel
                        neighbours.add(new Pair<>((i+1) * width + j, 1.0));
                    }
                    if (i < height - 1 && j > 0 && image.getRGB(j - 1, i + 1) > _BLACK) { // lower left pixel
                        neighbours.add(new Pair<>((i+1) * width + j - 1, 1.4));
                    }
                    if (j > 0 && image.getRGB(j - 1, i) > _BLACK) { // left pixel
                        neighbours.add(new Pair<>(i * width + j - 1, 1.0));
                    }
                    if (j > 0 && i > 0 && image.getRGB(j - 1, i - 1) > _BLACK ) { // upper left pixel
                        neighbours.add(new Pair<>((i-1) * width + j - 1, 1.4));
                    }
                }
                graph[i * width + j] = new Node(neighbours);
            }
        }

        ImgProcessingResults img = new ImgProcessingResults(graph, new Point(5,5), new Point(width-5, height-5), height, width);
        return img;
    }

    public static void ImgCorrection(String path) throws IOException {
        File f = new File(path);
        BufferedImage image = ImageIO.read(f);
        int width = image.getWidth();
        int height = image.getHeight();
        int crntClr;
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                crntClr = image.getRGB(j, i);
                if (crntClr < -2000000){//-1 && crntClr != -65536) {
                    image.setRGB(j, i, _BLACK);
                }
                else{
                    image.setRGB(j, i, -1);
                }
            }
        }
        ImageIO.write(image, "png", f);
    }
}

//
//
//for(int i = 0; i < height; i++) {
//        for (int j = 0; j < width; j++) {
//        crntClr = image.getRGB(j,i);
//        if(crntClr != -1 && crntClr != -65536){
//        image.setRGB(j,i,_BLACK);
//        }
//        boolean absent = true;
//        for(int k=0; k<colors.size(); k++){
//        if(colors.get(k) == crntClr){
//        absent = false;
//        break;
//        }
//
//        }
//        if(absent){
//        colors.add(crntClr);
////                    cNum ++;
//        }
//        }
//        }
//        int max = -1000000000;
//        for(int k=1; k<colors.size(); k++){
//        if(colors.get(k) > max){
//        max = colors.get(k);
//        }
//
//        }
//        max = new Color(255,0,0).getRGB();
//        ImageIO.write(image, "png", f);