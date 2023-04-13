package com.mygdx.game.DDD.Unit;

import java.util.ArrayList;

public class Sniper extends Scooter {


    public Sniper(ArrayList <BaseUnit> name,int x, int y, int gangSize) {
        super(name, 12, 10, 15, 0, gangSize, 9, 5, new int[]{8,10}, x, y,"Sniper", 0, 2);
        

    }

    @Override
    public String getTYPE() {
        return "Sniper";
    }
}
    
