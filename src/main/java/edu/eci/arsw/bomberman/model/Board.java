package edu.eci.arsw.bomberman.model;

public class Board {
    static elem[][] gameboard = new elem[30][30];

    public Board(){
        GenerateBoard();
        GeneratePlayer();
    }

    private void GeneratePlayer(){
        gameboard[0][0] = new Player(0,0);
        gameboard[30][30] = new Player(30,30);
    }



    private void GenerateBoard() {
        for (int i = 0; i != 30; i++) {
            for (int j = 0; j != 30; j++) {
                gameboard[i][j] = new block(i, j);
            }
        }

    }
}
