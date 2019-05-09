package monsterGame;

public class GameBoard {
    private int heightWithFrame;
    private int widthWithFrame;
    private int lastRowBeforeFrame;
    private int lastColumnBeforeFrame;
    private int lastRowWithFrame;
    private int lastColumnWithFrame;


    private static final int FRAME = 2;

    private static final int FIRST_ROW_AFTER_FRAME = 1;
    private static final int FIRST_COLUMN_AFTER_FRAME = 1;

    public GameBoard(GameDifficulty gameDifficulty) {
        switch (gameDifficulty) {
            case EASY:
                this.heightWithFrame = GameDifficulty.EASY.getBoardHeight() + FRAME;
                this.widthWithFrame = GameDifficulty.EASY.getBoardWidth() + FRAME;
                this.lastRowBeforeFrame = GameDifficulty.EASY.getBoardHeight();
                this.lastColumnBeforeFrame = GameDifficulty.EASY.getBoardWidth();
                this.lastRowWithFrame = GameDifficulty.EASY.getBoardHeight() + 1;
                this.lastColumnWithFrame = GameDifficulty.EASY.getBoardWidth() + 1;
                break;
            case MEDIUM:
                this.heightWithFrame = GameDifficulty.MEDIUM.getBoardHeight() + FRAME;
                this.widthWithFrame = GameDifficulty.MEDIUM.getBoardWidth() + FRAME;
                this.lastRowBeforeFrame = GameDifficulty.MEDIUM.getBoardHeight();
                this.lastColumnBeforeFrame = GameDifficulty.MEDIUM.getBoardWidth();
                this.lastRowWithFrame = GameDifficulty.MEDIUM.getBoardHeight() + 1;
                this.lastColumnWithFrame = GameDifficulty.MEDIUM.getBoardWidth() + 1;
                break;
            case HARD:
                this.heightWithFrame = GameDifficulty.HARD.getBoardHeight() + FRAME;
                this.widthWithFrame = GameDifficulty.HARD.getBoardWidth() + FRAME;
                this.lastRowBeforeFrame = GameDifficulty.HARD.getBoardHeight();
                this.lastColumnBeforeFrame = GameDifficulty.HARD.getBoardWidth();
                this.lastRowWithFrame = GameDifficulty.HARD.getBoardHeight() + 1;
                this.lastColumnWithFrame = GameDifficulty.HARD.getBoardWidth() + 1;
                break;
        }
    }

    public int getHeightWithFrame() {
        return heightWithFrame;
    }

    public int getWidthWithFrame() {
        return widthWithFrame;
    }

    public int getLastColumnBeforeFrame() {
        return lastColumnBeforeFrame;
    }

    public int getLastRowBeforeFrame() {
        return lastRowBeforeFrame;
    }

    public int getLastColumnWithFrame() {
        return lastColumnWithFrame;
    }

    public int getLastRowWithFrame() {
        return lastRowWithFrame;
    }

    public static int getFirstRowAfterFrame() {
        return FIRST_ROW_AFTER_FRAME;
    }

    public static int getFirstColumnAfterFrame() {
        return FIRST_COLUMN_AFTER_FRAME;
    }
}