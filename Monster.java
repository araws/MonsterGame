package monsterGame;

class Monster extends Character {

    Monster( int positionX, int positionY) {
        super( 1, positionX, positionY );
    }

    @Override
    public void move(MoveDirection.MoveDirectionOfCharacter dir) {
        super.move( dir );
    }
}
