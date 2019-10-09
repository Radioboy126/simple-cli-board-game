package com.radioboy.simplegame.game.essentials;

import com.radioboy.simplegame.game.board.Direction;

public class Controls {
    private final char UP;
    private final char DOWN;
    private final char LEFT;
    private final char RIGHT;


    public Controls(char up, char down, char left, char right)
    {
        up = Character.toLowerCase(up);
        down = Character.toLowerCase(down);
        left = Character.toLowerCase(left);
        right = Character.toLowerCase(right);
       this.UP = up;
       this.DOWN = down;
       this.LEFT = left;
       this.RIGHT = right;
    }

    public Controls()
    {
        this('w','s','a','d');
    }

    public Direction parseCharDirection(char inputDirection)
    {
        inputDirection = Character.toLowerCase(inputDirection);
        Direction resultingDirection = Direction.UNKNOWN;
        if(inputDirection == UP)
        {
            resultingDirection = Direction.UP;
        }
        else if(inputDirection == DOWN)
        {
            resultingDirection = Direction.DOWN;
        }
        else if(inputDirection == LEFT )
        {
            resultingDirection = Direction.LEFT;
        }
        else if(inputDirection == RIGHT)
        {
            resultingDirection = Direction.RIGHT;
        }
        return  resultingDirection;
    }


}
