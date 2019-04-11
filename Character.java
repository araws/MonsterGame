package projects.monsterGame;

import projects.monsterGame.MoveDirection.MoveDirectionOfCharacter;

public class Character implements Movable {

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

    @Override
    public void move(MoveDirectionOfCharacter dir){

        switch (dir) {

            case N:
                this.positionX += MoveDirectionOfCharacter.N.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.N.getChangeDirectionY();
                break;
            case NE:
                this.positionX += MoveDirectionOfCharacter.NE.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.NE.getChangeDirectionY();
                break;
            case E:
                this.positionX += MoveDirectionOfCharacter.E.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.E.getChangeDirectionY();
                break;
            case SE:
                this.positionX += MoveDirectionOfCharacter.SE.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.SE.getChangeDirectionY();
                break;
            case S:
                this.positionX += MoveDirectionOfCharacter.S.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.S.getChangeDirectionY();
                break;
            case SW:
                this.positionX += MoveDirectionOfCharacter.SW.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.SW.getChangeDirectionY();
                break;
            case W:
                this.positionX += MoveDirectionOfCharacter.W.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.W.getChangeDirectionY();
                break;
            case NW:
                this.positionX += MoveDirectionOfCharacter.NW.getChangeDirectionX();
                this.positionY += MoveDirectionOfCharacter.NW.getChangeDirectionY();
                break;
        }
    }
}