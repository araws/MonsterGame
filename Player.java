package monsterGame;

public class Player extends Character {

    Player() {

    }

    Player(int liveQuantity, int positionX, int positionY) {
        super( liveQuantity, positionX, positionY );
    }

    @Override
    public void move(MoveDirection.MoveDirectionOfCharacter dir) {
        super.move( dir );
    }
}
