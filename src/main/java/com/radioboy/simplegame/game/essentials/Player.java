package com.radioboy.simplegame.game.essentials;

import com.radioboy.simplegame.game.monster.Type;
import com.radioboy.simplegame.game.util.Misc;

public class Player {
    private boolean isAlive;
    private int healthpoints;
    private PlayerLocation location;



    public Player(int grantedLife)
    {
        isAlive = true;
        healthpoints = grantedLife;
        location = new PlayerLocation();
    }

    public int dodge()
    {
        return Misc.generateRandomNumber(10);
    }

    public int takeDamage(Type type)
    {
        int damageTaken = 0;
        if(type == Type.UNKNOWN)
            return -1;
        if(type == Type.JULES)
        {
            damageTaken = Integer.MAX_VALUE;
        }
        else
        {
            int expectedDamage = Misc.generateRandomNumber(10,45);
            int dodge = dodge();
            //if you "dodge" more damage than you expect you fumble and take the full hit
            damageTaken = (dodge >= 5) ? expectedDamage - dodge*2 : expectedDamage;
        }
        healthpoints = Math.max(healthpoints - damageTaken , 0);
        if(healthpoints == 0)
            isAlive = false;
        return damageTaken;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void movePlayer(int x, int y)
    {
        location.setLocation(x,y);
    }

    public int[] getLocation() {
        return new int[]{location.getX(),location.getY()};
    }

    public int getHealthpoints() {
        return healthpoints;
    }
}
