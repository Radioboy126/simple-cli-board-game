package com.radioboy.simplegame.game.board;

import com.radioboy.simplegame.game.monster.Monster;
import com.radioboy.simplegame.game.monster.Type;
import com.radioboy.simplegame.game.util.Misc;

public class Board {
    private int size;
    private Cell[][] board;
    /*
    reminder:
    to move up:     y - 1
    to move down:   y + 1
    to move left:   x - 1
    to move right:  x + 1

            X
       0 1 2 3 4 5
     [             ] 0
     [             ] 1
     [             ] 2  Y
     [             ] 3
     [             ] 4
     [             ] 5

     */


    public Board(int size) {
        this.size = size;
        board = new Cell[size][size];
    }


    public Board() {
        this(6);
    }

    public void generateTerrain() {
        //required as we dont instantiate new cells
        populateEmptyCells();

        for (Type type : Type.values()) {
            boolean wasPlaced = false;
            while (!wasPlaced) {


                if (type == Type.UNKNOWN)
                    wasPlaced = true;
                else {
                    int x, y = 0;
                    x = Misc.generateRandomNumber(size - 1);
                    y = Misc.generateRandomNumber(size - 1);
                    if (board[x][y].getStatus() == Status.UNOCCUPIED) {
                        board[x][y].occupy(new Monster(type));
                        wasPlaced = true;
                    }
                }
            }
        }
    }

    private void populateEmptyCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Cell();
                    board[i][j].setUnoccupied();
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return size;
    }


}
