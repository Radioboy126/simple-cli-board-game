package com.radioboy.simplegame.game.util;


import java.util.Random;

public class Misc {
    public static  int generateRandomNumber(int lowerBound, int upperBound)
    {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound ) + lowerBound;
    }
    //start assumed to be zero
    public static int generateRandomNumber(int upperBound)
    {
        return generateRandomNumber(0,upperBound);
    }


}
