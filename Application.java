package monsterGame;

import java.io.IOException;

public class Application {
    public static void main(String[] args){
        Game game = new Game();
        try {
            game.start();
        } catch (IOException | NullPointerException e) {
            System.err.println("Wrong key!!");
        }
    }
}