package Testbench;

import Algo.MapScanner;

import java.io.IOException;

/**
 * Created by Konstantin on 02.12.2016.
 */
public class Testing {
    public static void main(String []args){
        try {
            MapScanner.GetGraph("map.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
