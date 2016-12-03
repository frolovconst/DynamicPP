package Testbench;

import Algo.MapScanner;
import Algo.Traveller;

import java.io.IOException;

/**
 * Created by Konstantin on 02.12.2016.
 */
public class Testing {
    public static void main(String []args){
        try {
//            MapScanner.ImgCorrection("map.jpg");
            Traveller.TravelAcrossImage("map.png", "map_results.png", "map_debug.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
