package com.radioboy.simplegame.game.monster;

public class Monster {
    public static final Monster DEFAULT_MONSTER = new Monster(Type.UNKNOWN);
    private Type monsterType;

    public Monster(Type monsterType) {
        this.monsterType = monsterType;
    }

    public Type getMonsterType() {
        return monsterType;
    }

}
