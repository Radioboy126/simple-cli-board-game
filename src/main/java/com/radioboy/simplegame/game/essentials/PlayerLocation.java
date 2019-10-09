package com.radioboy.simplegame.game.essentials;

class PlayerLocation
{
    private int x,y;
    PlayerLocation()
    {
        x = -1;
        y = -1;
    }

    void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
