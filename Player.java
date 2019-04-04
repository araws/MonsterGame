package projects.monsterGame;

public class Player extends Character {

    public Player(int liveQuantity, int positionX, int positionY) {
        super( liveQuantity, positionX, positionY );
    }

    @Override
    public void move(MoveDirection.MoveDirectionOfCharacter dir) {
        super.move( dir );
    }
}
