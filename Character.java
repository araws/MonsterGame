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
                this.positionX += MoveDirection.MoveDirectionOfCharacter.N.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.N.getChangeDirectionY();
                break;
            case NE:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.NE.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.NE.getChangeDirectionY();
                break;
            case E:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.E.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.E.getChangeDirectionY();
                break;
            case SE:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.SE.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.SE.getChangeDirectionY();
                break;
            case S:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.S.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.S.getChangeDirectionY();
                break;
            case SW:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.SW.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.SW.getChangeDirectionY();
                break;
            case W:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.W.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.W.getChangeDirectionY();
                break;
            case NW:
                this.positionX += MoveDirection.MoveDirectionOfCharacter.NW.getChangeDirectionX();
                this.positionY += MoveDirection.MoveDirectionOfCharacter.NW.getChangeDirectionY();
                break;
        }
    }
}