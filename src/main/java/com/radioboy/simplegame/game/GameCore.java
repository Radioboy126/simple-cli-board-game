package com.radioboy.simplegame.game;

import com.radioboy.simplegame.game.board.Board;
import com.radioboy.simplegame.game.board.Cell;
import com.radioboy.simplegame.game.board.Direction;
import com.radioboy.simplegame.game.board.Status;
import com.radioboy.simplegame.game.essentials.Player;
import com.radioboy.simplegame.game.monster.Type;
import com.radioboy.simplegame.game.util.Misc;

public class GameCore {
    private Player mainPlayer;
    private Board gameBoard;


    public GameCore(int playerLife) {
        mainPlayer = new Player(playerLife);
        gameBoard = new Board();
        gameBoard.generateTerrain();
        placeRandomMainPlayer();

    }

    public void placeRandomMainPlayer() {
        int[] loc = mainPlayer.getLocation();
        int x = loc[0];
        int y = loc[1];
        if (x == -1 || y == -1) {
            boolean validPlacement = false;
            do{
                x = Misc.generateRandomNumber(gameBoard.getSize() - 1);
                y = Misc.generateRandomNumber(gameBoard.getSize() - 1);
                Cell testedCell = gameBoard.getCell(x,y);
                if(testedCell.getStatus() == Status.UNOCCUPIED) {
                    mainPlayer.movePlayer(x, y);
                    validPlacement = true;
                }
            }while(!validPlacement);

        }
        //if player is within "board"
    }

    public Cell movePlayer(Direction direction) {

        int x = mainPlayer.getLocation()[0];
        int y = mainPlayer.getLocation()[1];
        Cell boardCell = gameBoard.getCell(x, y);
        // see Board class for directional guide
        switch (direction) {
            case UP:
                if (!((y - 1) < 0)) {
                    mainPlayer.movePlayer(x, y - 1);
                    boardCell = gameBoard.getCell(x, y - 1);
                }

                break;
            case DOWN:
                if (!((y + 1) > (gameBoard.getSize() - 1))) {
                    mainPlayer.movePlayer(x, y + 1);
                    boardCell = gameBoard.getCell(x, y + 1);
                }
                break;
            case LEFT:
                if (!((x - 1) < 0)) {
                    mainPlayer.movePlayer(x - 1, y);
                    boardCell = gameBoard.getCell(x - 1, y);
                }
                break;
            case RIGHT:
                if (!((x + 1) > (gameBoard.getSize() - 1))) {
                    mainPlayer.movePlayer(x + 1, y);
                    boardCell = gameBoard.getCell(x + 1, y );
                }
                break;
            default:
                break;
        }
        return boardCell;
    }

    public int enterCombat(Type monster)
    {
        return mainPlayer.takeDamage(monster);
    }

    public Cell getPlayerCell()
    {
        int x = mainPlayer.getLocation()[0];
        int y = mainPlayer.getLocation()[1];
        return gameBoard.getCell(x,y);
    }

    public boolean isGameOver() {
        return !mainPlayer.isAlive();
    }

    public int getLeftHealth()
    {
        return mainPlayer.getHealthpoints();
    }

    public String boardToString() {
        StringBuilder string = new StringBuilder();
        int[] loc = mainPlayer.getLocation();
        for (int j = 0; j < gameBoard.getSize(); j++) {
            for (int i = 0; i < gameBoard.getSize(); i++) {
                if(i == loc[0] && j == loc[1] )
                {
                    string.append(String.format("[%1s]", 'P'));
                    continue;
                }
                switch (gameBoard.getCell(i, j).getStatus()) {
                    case UNOCCUPIED:
                        string.append(String.format("[%1s]", '-'));
                        break;
                    case UNCLEARED:
                        string.append(String.format("[%1s]", 'E'));
                        break;
                    case CLEARED:
                        string.append(String.format("[%1s]", 'C'));
                        break;
                    default:
                        break;
                }
            }
            string.append(System.lineSeparator());
        }
        return string.toString();
    }
}
