package com.radioboy.simplegame.game.board;

import com.radioboy.simplegame.game.monster.Monster;
import com.radioboy.simplegame.game.monster.Type;

public class Cell {
    private Status status;
    private Monster monster;

    public Cell()
    {
        this.status = Status.UNKNOWN;
        this.monster = Monster.DEFAULT_MONSTER;
    }

    public Status getStatus()
    {
        return status;
    }
    public void levelClear()
    {
        status = Status.CLEARED;
    }

    public void occupy(Monster monster)
    {
        status = Status.UNCLEARED;
        this.monster = monster;
    }

    public void setUnoccupied()
    {
        status = Status.UNOCCUPIED;
    }

    public Type getMonsterType() {
        return monster.getMonsterType();
    }
}
