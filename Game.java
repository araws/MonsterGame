package monsterGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static monsterGame.MoveDirection.MoveDirectionOfCharacter;

public class Game {

    private GameDifficulty chosenGameDifficulty;
    private GameBoard gameBoard;

    private int numberOfPlayerJumpsLeft;

    private List<Monster> monsterList = new ArrayList<>();


    private Player player = new Player();
    private Monster monster = new Monster();
    private Random random = new Random();


    public void start() throws IOException {

        runMenu();

        System.out.println("Chosen difficulty: " + chosenGameDifficulty);

        BufferedReader directionReader = new BufferedReader(new InputStreamReader(System.in));

        Player tempPlayer = new Player(chosenGameDifficulty.getNumberOfPlayerLives(), randomlySetPlayerPositionX(player), randomlySetPlayerPositionY(player));

        for (int i = 0; i < chosenGameDifficulty.getNumberOfMonster(); i++) {
            monsterList.add(new Monster(randomlySetMonsterPositionX(monster), randomlySetMonsterPositionY(monster)));
        }

        gameBoard.getGameBoardPicture();

        refreshGameBoard(tempPlayer);

        while ((tempPlayer.getLiveQuantity() > 0) && (!monsterList.isEmpty())) {
            gameLoop(tempPlayer, directionReader);
        }
    }

    public void runMenu() throws IOException {

        System.out.println("Welcome to game 'Monster'");
        System.out.println("Choose the difficulty: 1 - EASY, 2 - MEDIUM, 3 - HARD");

        BufferedReader directionReader = new BufferedReader(new InputStreamReader(System.in));
        String keyChosenDifficulty = directionReader.readLine();
        switch (keyChosenDifficulty) {
            case "1":
                chosenGameDifficulty = GameDifficulty.EASY;
                break;
            case "2":
                chosenGameDifficulty = GameDifficulty.MEDIUM;
                break;
            case "3":
                chosenGameDifficulty = GameDifficulty.HARD;
                break;
        }
        gameBoard = new GameBoard(chosenGameDifficulty);
        numberOfPlayerJumpsLeft = chosenGameDifficulty.getNumberOfJumps();
    }

    private void gameLoop(Player player, BufferedReader directionReader) throws IOException {

        movePlayer(player, directionReader);
        List<Monster> monsterToKillList = new ArrayList<>();

        moveMonsterTowardPlayer(player);

        for (Iterator<Monster> monsterIterator = monsterList.iterator(); monsterIterator.hasNext(); ) {
            Monster monster = monsterIterator.next();
            if ((monster.getPositionX() == player.getPositionX()) && (monster.getPositionY() == player.getPositionY())) {
                player.looseLive();
                if (player.getLiveQuantity() == 0) {
                    System.err.println("YOU'VE LOST!!!!!");
                    break;
                } else {
                    randomlySetPlayerPosition(player);
                    System.err.println("Quantity of lives left: " + player.getLiveQuantity());
                }
                monster.looseLive();
                if (monster.getLiveQuantity() == 0) {
                    monsterToKillList.add(monster);
                }
            }
            addKilledMonsterToMonsterToKillList(monsterToKillList, monster);
        }
        monsterList.removeAll(monsterToKillList);

        if (monsterList.isEmpty()) {
            System.err.println("VICTORY!!!");
        }

        refreshGameBoard(player);
    }

    private void moveMonsterTowardPlayer(Player player) {
        for (Monster monster : monsterList) {
            int monsterPositionX = monster.getPositionX();
            int monsterPositionY = monster.getPositionY();
            int playerPositionX = player.getPositionX();
            int playerPositionY = player.getPositionY();

            if ((monsterPositionX < playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.SE);
            } else if ((monsterPositionX < playerPositionX) && (monsterPositionY == playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.S);
            } else if ((monsterPositionX < playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.SW);
            } else if ((monsterPositionX == playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.W);
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY > playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.NW);
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY == playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.N);
            } else if ((monsterPositionX > playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.NE);
            } else if ((monsterPositionX == playerPositionX) && (monsterPositionY < playerPositionY)) {
                monster.move(MoveDirectionOfCharacter.E);
            }
        }
    }

    private void movePlayer(Player player, BufferedReader directionReader) throws IOException {

        String keyBoardDirection = directionReader.readLine();

        switch (keyBoardDirection) {
            case "8":
                if (player.getPositionX() > GameBoard.getFirstColumnAfterFrame()) {
                    player.move(MoveDirectionOfCharacter.N);
                } else movePlayer(player, directionReader);
                break;
            case "9":
                if ((player.getPositionX() > GameBoard.getFirstColumnAfterFrame())
                        && (player.getPositionY() < gameBoard.getLastColumnBeforeFrame())) {
                    player.move(MoveDirectionOfCharacter.NE);
                } else movePlayer(player, directionReader);
                break;
            case "6":
                if (player.getPositionY() < gameBoard.getLastColumnBeforeFrame()) {
                    player.move(MoveDirectionOfCharacter.E);
                } else movePlayer(player, directionReader);
                break;
            case "3":
                if ((player.getPositionX() < gameBoard.getLastRowBeforeFrame())
                        && (player.getPositionY() < gameBoard.getLastColumnBeforeFrame())) {
                    player.move(MoveDirectionOfCharacter.SE);
                } else movePlayer(player, directionReader);
                break;
            case "2":
                if (player.getPositionX() < gameBoard.getLastRowBeforeFrame()) {
                    player.move(MoveDirectionOfCharacter.S);
                } else movePlayer(player, directionReader);
                break;
            case "1":
                if ((player.getPositionX() < gameBoard.getLastRowBeforeFrame())
                        && (player.getPositionY() > GameBoard.getFirstRowAfterFrame())) {
                    player.move(MoveDirectionOfCharacter.SW);
                } else movePlayer(player, directionReader);
                break;
            case "4":
                if (player.getPositionY() > GameBoard.getFirstRowAfterFrame()) {
                    player.move(MoveDirectionOfCharacter.W);
                } else movePlayer(player, directionReader);
                break;
            case "7":
                if ((player.getPositionX() > GameBoard.getFirstColumnAfterFrame())
                        && (player.getPositionY() > GameBoard.getFirstRowAfterFrame())) {
                    player.move(MoveDirectionOfCharacter.NW);
                } else movePlayer(player, directionReader);
                break;
            case "5":
                if (numberOfPlayerJumpsLeft > 0) {
                    randomlySetPlayerPosition(player);
                    numberOfPlayerJumpsLeft--;
                } else movePlayer(player, directionReader);
                break;
        }
    }

     private void refreshGameBoard(Player player) {
        for (int row = 0; row < gameBoard.getHeightWithFrame(); row++) {
            for (int column = 0; column < gameBoard.getWidthWithFrame(); column++) {
                if (row == 0) {gameBoard.getGameBoardPicture()[row][column] = '#';
                } else if (row == gameBoard.getLastRowWithFrame()) {
                    gameBoard.getGameBoardPicture()[row][column] = '#';
                } else if (column == 0) {
                    gameBoard.getGameBoardPicture()[row][column] = '#';
                } else if (column == gameBoard.getLastColumnWithFrame()) {
                    gameBoard.getGameBoardPicture()[row][column] = '#';
                } else {
                    gameBoard.getGameBoardPicture()[row][column] = ' ';
                }

                if ((row == player.getPositionX()) && (column == player.getPositionY())) {
                    gameBoard.getGameBoardPicture()[row][column] = 'P';
                }

                for (Monster monster : monsterList) {
                    if (monster.getLiveQuantity() != 0) {
                        if ((row == monster.getPositionX()) && (column == monster.getPositionY())) {
                            gameBoard.getGameBoardPicture()[row][column] = 'M';
                        }
                    }
                }
                System.out.print(gameBoard.getGameBoardPicture()[row][column]);
            }
            System.out.println();

        }
        System.out.println();
    }

    private void addKilledMonsterToMonsterToKillList(List<Monster> monsterToKillList, Monster monster) {
        for (Iterator<Monster> killingMonsterIterator = monsterList.iterator(); killingMonsterIterator.hasNext(); ) {
            Monster monsterToKill = killingMonsterIterator.next();
            if ((monster.getPositionX() == monsterToKill.getPositionX())
                    && ((monster.getPositionY() == monsterToKill.getPositionY())
                    && (!monster.equals(monsterToKill)))) {
                monsterToKill.looseLive();
                if (monsterToKill.getLiveQuantity() == 0) {
                    monsterToKillList.add(monsterToKill);
                }
                monster.looseLive();
                if (monster.getLiveQuantity() == 0) {
                    monsterToKillList.add(monster);
                }
            }

        }
    }

    public void randomlySetPlayerPosition(Player player) {
        randomlySetPlayerPositionX(player);
        randomlySetPlayerPositionY(player);
    }

    public int randomlySetPlayerPositionX(Player player) {
        return player.setPositionX(random.nextInt(gameBoard.getHeightWithoutFrame()) + 1);
    }

    public int randomlySetPlayerPositionY(Player player) {
        return player.setPositionY(random.nextInt(gameBoard.getWidthWithoutFrame()) + 1);
    }

    public int randomlySetMonsterPositionX(Monster monster) {
        return monster.setPositionX(random.nextInt(gameBoard.getHeightWithoutFrame()) + 1);
    }

    public int randomlySetMonsterPositionY(Monster monster) {
        return monster.setPositionY(random.nextInt(gameBoard.getWidthWithoutFrame()) + 1);
    }
}