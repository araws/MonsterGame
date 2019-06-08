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

    private Player player;
    private List<Monster> monsterList = new ArrayList<>();

    private Random random = new Random();

    public void start() throws IOException {
        showWelcomeWindow();
        runMenu();
        showChosenDifficultyInfo();
        createPlayer();
        createMonsters();
        gameLoop();
    }

    private void showWelcomeWindow() {
        System.out.println("Welcome in Monster Game :)");
        System.out.println("You ('P') have to eliminate each monster ('M')");
        System.out.println("You can move by 1 field with numeric pad 789");
        System.out.println("                                         4 6");
        System.out.println("                                         123");
        System.out.println("You can randomly jump with 5");
        System.out.println();
        System.out.println("Choose the difficulty: 1 - EASY, 2 - MEDIUM, 3 - HARD");
    }

    private void showChosenDifficultyInfo() {
        System.out.println("You chose " + chosenGameDifficulty);
        System.out.println("You have " + chosenGameDifficulty.getNumberOfPlayerLives() + " lives");
        System.out.println("You have " + chosenGameDifficulty.getNumberOfJumps() + " jumps");
        System.out.println();
        System.out.println("GOOD LUCK :)");
        System.out.println();
    }

    public void runMenu() throws NullPointerException {

        try {
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
        } catch (IOException e) {
            System.out.println("Please choose 1, 2 or 3!!!");
        }
    }

    private void createPlayer() {
        player = new Player(chosenGameDifficulty.getNumberOfPlayerLives(), randomPositionXOnMap(), randomPositionYOnMap());
    }

    private void createMonsters() {
        for (int i = 0; i < chosenGameDifficulty.getNumberOfMonster(); i++) {
            monsterList.add(new Monster(randomPositionXOnMap(), randomPositionYOnMap()));
        }
    }

    private void gameLoop() throws IOException {
        while ((player.getLiveQuantity() > 0) && (!monsterList.isEmpty())) {
            refreshGameBoard();
            movePlayer();
            moveMonsterTowardPlayer();

            List<Monster> monsterToKillList = new ArrayList<>();
            checkCollisionsMonstersWithPlayer(monsterToKillList);
            monsterList.removeAll(monsterToKillList);

            if (monsterList.isEmpty()) {
                refreshGameBoard();
                System.err.println("VICTORY!!!");
            }
        }
    }

    private void refreshGameBoard() {
        for (int row = 0; row < gameBoard.getHeightWithFrame(); row++) {
            for (int column = 0; column < gameBoard.getWidthWithFrame(); column++) {
                renderGameBoard(row, column);
                renderPlayer(player, row, column);
                renderMonsters(row, column);

                System.out.print(gameBoard.getGameBoardPicture()[row][column]);
            }
            System.out.println();

        }
        System.out.println();
    }

    private void renderGameBoard(int row, int column) {
        if (row == 0) {
            gameBoard.getGameBoardPicture()[row][column] = '#';
        } else if (row == gameBoard.getLastRowWithFrame()) {
            gameBoard.getGameBoardPicture()[row][column] = '#';
        } else if (column == 0) {
            gameBoard.getGameBoardPicture()[row][column] = '#';
        } else if (column == gameBoard.getLastColumnWithFrame()) {
            gameBoard.getGameBoardPicture()[row][column] = '#';
        } else {
            gameBoard.getGameBoardPicture()[row][column] = ' ';
        }
    }

    private void renderPlayer(Player player, int row, int column) {
        if ((row == player.getPositionX()) && (column == player.getPositionY())) {
            gameBoard.getGameBoardPicture()[row][column] = 'P';
        }
    }

    private void renderMonsters(int row, int column) {
        for (Monster monster : monsterList) {
            if (monster.getLiveQuantity() != 0) {
                if ((row == monster.getPositionX()) && (column == monster.getPositionY())) {
                    gameBoard.getGameBoardPicture()[row][column] = 'M';
                }
            }
        }
    }

    private void movePlayer() throws IOException {

        BufferedReader directionReader = new BufferedReader(new InputStreamReader(System.in));
        String keyBoardDirection = directionReader.readLine();

        switch (keyBoardDirection) {
            case "8":
                if (player.getPositionX() > GameBoard.getFirstColumnAfterFrame()) {
                    player.move(MoveDirectionOfCharacter.N);
                } else movePlayer();
                break;
            case "9":
                if ((player.getPositionX() > GameBoard.getFirstColumnAfterFrame())
                        && (player.getPositionY() < gameBoard.getLastColumnBeforeFrame())) {
                    player.move(MoveDirectionOfCharacter.NE);
                } else movePlayer();
                break;
            case "6":
                if (player.getPositionY() < gameBoard.getLastColumnBeforeFrame()) {
                    player.move(MoveDirectionOfCharacter.E);
                } else movePlayer();
                break;
            case "3":
                if ((player.getPositionX() < gameBoard.getLastRowBeforeFrame())
                        && (player.getPositionY() < gameBoard.getLastColumnBeforeFrame())) {
                    player.move(MoveDirectionOfCharacter.SE);
                } else movePlayer();
                break;
            case "2":
                if (player.getPositionX() < gameBoard.getLastRowBeforeFrame()) {
                    player.move(MoveDirectionOfCharacter.S);
                } else movePlayer();
                break;
            case "1":
                if ((player.getPositionX() < gameBoard.getLastRowBeforeFrame())
                        && (player.getPositionY() > GameBoard.getFirstRowAfterFrame())) {
                    player.move(MoveDirectionOfCharacter.SW);
                } else movePlayer();
                break;
            case "4":
                if (player.getPositionY() > GameBoard.getFirstRowAfterFrame()) {
                    player.move(MoveDirectionOfCharacter.W);
                } else movePlayer();
                break;
            case "7":
                if ((player.getPositionX() > GameBoard.getFirstColumnAfterFrame())
                        && (player.getPositionY() > GameBoard.getFirstRowAfterFrame())) {
                    player.move(MoveDirectionOfCharacter.NW);
                } else movePlayer();
                break;
            case "5":
                if (numberOfPlayerJumpsLeft > 0) {
                    randomlySetPlayerPosition(player);
                    numberOfPlayerJumpsLeft--;
                } else movePlayer();
                break;
        }
    }

    private void moveMonsterTowardPlayer() {
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

    private void checkCollisionsMonstersWithPlayer(List<Monster> monsterToKillList) {
        for (Iterator<Monster> monsterIterator = monsterList.iterator(); monsterIterator.hasNext(); ) {
            Monster monster = monsterIterator.next();
            if ((monster.getPositionX() == this.player.getPositionX()) && (monster.getPositionY() == this.player.getPositionY())) {
                this.player.looseLive();
                if (this.player.getLiveQuantity() == 0) {
                    System.err.println("YOU'VE LOST!!!!!");
                    break;
                } else {
                    randomlySetPlayerPosition(this.player);
                    System.err.println("Quantity of lives left: " + this.player.getLiveQuantity());
                }
                monster.looseLive();
                if (monster.getLiveQuantity() == 0) {
                    monsterToKillList.add(monster);
                }
            }
            addKilledMonsterToMonsterToKillListLoop(monsterToKillList, monster);
        }
    }

    private void addKilledMonsterToMonsterToKillListLoop(List<Monster> monsterToKillList, Monster monster) {
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

    private int randomPositionXOnMap() {
        return random.nextInt(gameBoard.getHeightWithoutFrame()) + 1;
    }

    private int randomPositionYOnMap() {
        return random.nextInt(gameBoard.getWidthWithoutFrame()) + 1;
    }

    public void randomlySetPlayerPosition(Player player) {
        randomlySetPlayerPositionX(player);
        randomlySetPlayerPositionY(player);
    }

    private int randomlySetPlayerPositionX(Player player) {
        return player.setPositionX(randomPositionXOnMap());
    }

    private int randomlySetPlayerPositionY(Player player) {
        return player.setPositionY(randomPositionYOnMap());
    }
}