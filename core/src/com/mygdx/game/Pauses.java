package com.mygdx.game;

public class Pauses {
    private static int counter;
     private boolean pause;

     public boolean isPause(long ms) {
         if(getPause(ms) && !pause) {
             pause=true;
         }
         return pause;
     }

    private boolean getPause(long ms) {
        long need = ms /16;
        counter++;
        if (counter>need) {
            counter=0;
            return true;
        } else {
            return false;
        }
    }
}
