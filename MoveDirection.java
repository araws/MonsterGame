package projects.monsterGame;

public class MoveDirection {

    public enum MoveDirectionOfCharacter {
        N ( -1, 0 ),
        NE ( -1, 1 ),
        E ( 0, 1 ),
        SE ( 1, 1 ),
        S ( 1, 0 ),
        SW ( 1, -1 ),
        W ( 0, -1 ),
        NW ( -1, -1 );

        private int changeDirectionX;
        private int changeDirectionY;

        MoveDirectionOfCharacter(int changeDirectionX, int changeDirectionY) {
            this.changeDirectionX = changeDirectionX;
            this.changeDirectionY = changeDirectionY;
        }

        public int getChangeDirectionX() {
            return changeDirectionX;
        }

        public int getChangeDirectionY() {
            return changeDirectionY;
        }
    }
}
