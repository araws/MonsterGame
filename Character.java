package projects.monsterGame;

import java.awt.*;

public class Character {

    private int liveQuantity;

    private int positionX;
    private int positionY;

    public Character(int liveQuantity, int positionX, int positionY) {
        this.liveQuantity = liveQuantity;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }


    public void move(MoveDirection.MoveDirectionOfCharacter dir){

        switch (dir) {

            case N:
                break;
            case NE:
                break;
            case E:
                break;
            case SE:
                break;
            case S:
                break;
            case SW:
                break;
            case W:
                break;
            case NW:
                break;
        }
    }
}