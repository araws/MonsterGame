package projects.monsterGame;

import projects.monsterGame.MoveDirection.MoveDirectionOfCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {

    char[][] gameBoard = null;

    List<Monster> monsterList = new ArrayList<>();

    public void start() throws IOException {
        System.out.println( "Welcome to game 'Monster'" );
//        System.out.println( "Choose the difficulty: 1 - EASY, 2 - MEDIUM, 3 - HARD" );

        Player player = new Player( 3, 10, 10 );

        BufferedReader directionReader = new BufferedReader( new InputStreamReader( System.in ) );

        monsterList.add( new Monster( 3, 4 ) );
        monsterList.add( new Monster( 4, 6 ) );
        monsterList.add( new Monster( 6, 7 ) );
        monsterList.add( new Monster( 8, 22 ) );
        monsterList.add( new Monster( 9, 19 ) );
        monsterList.add( new Monster( 11, 21 ) );
        monsterList.add( new Monster( 13, 2 ) );
        monsterList.add( new Monster( 14, 7 ) );
        monsterList.add( new Monster( 14, 5 ) );
        monsterList.add( new Monster( 15, 23 ) );

        gameBoard = new char[17][27];

        refreshGameBoard( player );

        while(true) {
            movePlayer( player, directionReader );
            moveMonsterTowardPlayer( player );
            refreshGameBoard( player );
        }
    }

    private void moveMonsterTowardPlayer(Player player) {
        for (Monster monster : monsterList) {
            int monsterPositionX = monster.getPositionX();
            int monsterPositionY = monster.getPositionY();
            int playerPositionX = player.getPositionX();
            int playerPositionY = player.getPositionY();

            if ((monsterPositionX < playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.SE );
            } else if ((monsterPositionX < playerPositionX) && (monsterPositionY == playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.S );
            } else if ((monsterPositionX < playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.SW );
            } else if ((monsterPositionX == playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.W );
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.NW );
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY == playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.N );
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.NE );
            } else if ((monsterPositionX == playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move( MoveDirectionOfCharacter.E );
            }
        }
    }

    private void movePlayer(Player player, BufferedReader directionReader) throws IOException {

        String keyBoardDirection = directionReader.readLine();
        switch (keyBoardDirection) {
            case "8":
               player.move( MoveDirectionOfCharacter.N );
                break;
            case "9":
                player.move( MoveDirectionOfCharacter.NE );
                break;
            case "6":
                player.move( MoveDirectionOfCharacter.E );
                break;
            case "3":
                player.move( MoveDirectionOfCharacter.SE );
                break;
            case "2":
                player.move( MoveDirectionOfCharacter.S );
                break;
            case "1":
                player.move( MoveDirectionOfCharacter.SW );
                break;
            case "4":
                player.move( MoveDirectionOfCharacter.W );
                break;
            case "7":
                player.move( MoveDirectionOfCharacter.NW );
                break;
        }
    }

    private void refreshGameBoard(Player player) {
        for (int row = 0; row < 17; row++) {
            for (int column = 0; column < 27; column++) {
                if (row == 0) {
                    gameBoard[row][column] = '#';
                } else if (row == 16) {
                    gameBoard[row][column] = '#';
                } else if (column == 0) {
                    gameBoard[row][column] = '#';
                } else if (column == 26) {
                    gameBoard[row][column] = '#';
                } else {
                    gameBoard[row][column] = ' ';
                }

                if ((row == player.getPositionX()) && (column == player.getPositionY())) {
                    gameBoard[row][column] = 'P';
                }

                for (Monster monster : monsterList) {
                    if ((row == monster.getPositionX()) && (column == monster.getPositionY())) {
                        gameBoard[row][column] = 'M';
                    }
                }
                System.out.print( gameBoard[row][column] );
            }
            System.out.println();

        }
        System.out.println();
    }
}

/*public enum GameDifficulty {
        EASY( 25, 15, 10, 4, 3 ),
        MEDIUM( 35, 18, 20, 3, 2 ),
        HARD( 50, 23, 30, 2, 1 );

        private int boardWidth;
        private int boardHeight;
        private int numberOfMonster;
        private int numberOfPlayerLives;
        private int numberOfJumps;


        GameDifficulty(int boardWidth, int boardHeight, int numberOfMonster, int numberOfPlayerLives, int numberOfJumps) {
            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;
            this.numberOfMonster = numberOfMonster;
            this.numberOfPlayerLives = numberOfPlayerLives;
            this.numberOfJumps = numberOfJumps;
        }

        public int getBoardWidth() {
            return boardWidth;
        }

        public int getBoardHeight() {
            return boardHeight;
        }

        public int getNumberOfMonster() {
            return numberOfMonster;
        }

        public int getNumberOfPlayerLives() {
            return numberOfPlayerLives;
        }

        public int getNumberOfJumps() {
            return numberOfJumps;
        }
    }*/

/*
class GameBoard {

    int x;
    int y;

    Game.GameDifficulty gameBoardField;

    {
        switch (gameBoardField) {
            case EASY:
                this.x = Game.GameDifficulty.EASY.getBoardHeight();
                this.y = Game.GameDifficulty.EASY.getBoardWidth();
                break;
            case MEDIUM:
                this.x = Game.GameDifficulty.MEDIUM.getBoardHeight();
                this.y = Game.GameDifficulty.MEDIUM.getBoardWidth();
                break;
            case HARD:
                this.x = Game.GameDifficulty.HARD.getBoardHeight();
                this.y = Game.GameDifficulty.HARD.getBoardWidth();
                break;
        }
    }
}*/
