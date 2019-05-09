package monsterGame;

import java.util.Random;

public class Player extends Character {

    private Random random = new Random();

    Player(int liveQuantity, int positionX, int positionY) {
        super( liveQuantity, positionX, positionY );
    }

    @Override
    public void move(MoveDirection.MoveDirectionOfCharacter dir) {
        super.move( dir );
    }
}
