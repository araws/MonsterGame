package monsterGame;

import java.util.Random;

public class Player extends Character {

    Player(int liveQuantity, int positionX, int positionY) {
        super( liveQuantity, positionX, positionY );
    }

    private Random random = new Random();

    @Override
    public void move(MoveDirection.MoveDirectionOfCharacter dir) {
        super.move( dir );
    }

    public void flyToRandomPoint() {
        setPositionX( random.nextInt( 15 ) + 1 );
        setPositionY( random.nextInt( 25 ) + 1 );
    }
}
