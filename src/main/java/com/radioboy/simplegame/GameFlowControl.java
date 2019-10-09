package com.radioboy.simplegame;

import com.radioboy.simplegame.game.GameCore;
import com.radioboy.simplegame.game.GameFlowInfo;
import com.radioboy.simplegame.game.board.Cell;
import com.radioboy.simplegame.game.board.Direction;
import com.radioboy.simplegame.game.board.Status;
import com.radioboy.simplegame.game.essentials.Controls;
import com.radioboy.simplegame.game.essentials.Difficulty;
import com.radioboy.simplegame.game.monster.Type;

public class GameFlowControl {
    private static GameCore core;
    private static Controls controls;

    public static void loadControls(Controls newControls) {
     controls = newControls;
    }

    public static void startGame(Difficulty difficulty) {
        if(controls == null)
            controls = new Controls();
        int life = 0;
        switch (difficulty) {
            case EASY:
                life = 200;
                break;
            case NORMAL:
                life = 140;
                break;
            case HARD:
                life = 100;
                break;
            default:
                break;
        }

        core = new GameCore(life);
    }

    public static void reset() {
        core = null;
    }

    public static GameFlowInfo move(char input)
    {
        GameFlowInfo info = new GameFlowInfo();

        Direction direction = controls.parseCharDirection(input);
        if(direction == Direction.UNKNOWN)
            info.error = "Unknown Direction: Player Did not move";
        Cell playerCell = core.getPlayerCell();
        Cell newPlayerCell = core.movePlayer(direction);
        // same instance of Cell
        if(playerCell == newPlayerCell)
            info.error = "Invalid move";
        if(info.error == null)
        {
            info.motionDone = direction.name();
            if(newPlayerCell.getStatus() ==  Status.UNCLEARED) {
                info.damageTaken = core.enterCombat(newPlayerCell.getMonsterType());
                info.isGameOver = core.isGameOver();
                info.monsterEncountered = newPlayerCell.getMonsterType().name();
                info.hp = core.getLeftHealth();
                if(core.getLeftHealth() > 0)
                {
                    newPlayerCell.levelClear();
                    if(newPlayerCell.getMonsterType() == Type.KING)
                        info.isVictory = true;
                }
            }
        }
        return info;
    }

    public static String getBoardString()
    {
        return core.boardToString();
    }
}
