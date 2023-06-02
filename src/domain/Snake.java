package domain;

import java.util.Random;

/**
 * The "Snake" class represents a snake item in the game.
 * It implements the "Item" interface and provides functionality for creating a snake, performing the action, and getting the positions.
 */
public class Snake implements Item {
    private int casillaInicial;
    private int casillaFinal;
    private Tablero tablero;
    private int max;

    /**
     * Constructor for objects of class Snake.
     *
     * @param tablero The "Tablero" object representing the game board.
     */
    public Snake(Tablero tablero) {
        this.tablero = tablero;
        max = tablero.getWidth() * tablero.getWidth();
        makeSnake();
    }

    /**
     * Constructor for objects of class Snake with specified initial and final positions.
     *
     * @param tb    The "Tablero" object representing the game board.
     * @param i     The initial position of the snake.
     * @param j     The final position of the snake.
     */
    public Snake(Tablero tb, int i, int j) {
        this.tablero = tb;
        max = tablero.getWidth() * tablero.getWidth();
        this.casillaInicial = i;
        this.casillaFinal = j;
    }

    /**
     * Generates random initial and final positions for the snake.
     */
    public void makeSnake() {
        int min = 1;
        Random rand = new Random();
        casillaInicial = rand.nextInt((max - tablero.getWidth() + 1)) + min;
        if ((casillaInicial + tablero.getWidth()) <= max) {
            casillaFinal = rand.nextInt((max - casillaInicial) + 1) + casillaInicial;
        } else {
            casillaFinal = max - 1;
        }
    }

    /**
     * Performs the action associated with the snake using the given "Fichas" object.
     *
     * @param ficha_turn The "Fichas" object to perform the action with.
     */
    public void action(Fichas ficha_turn) {
        if (ficha_turn.getPosition() == casillaFinal)
            ficha_turn.setPosition(casillaInicial);
    }

    /**
     * Gets the final position of the snake.
     *
     * @return The final position of the snake.
     */
    public int getCasillaFinal() {
        return casillaFinal;
    }

    /**
     * Gets the initial position of the snake.
     *
     * @return The initial position of the snake.
     */
    public int getCasillaInicial() {
        return casillaInicial;
    }

    /**
     * Gets the positions of the snake.
     *
     * @return An array containing the initial and final positions of the snake.
     */
    public int[] positionSnake() {
        int[] position = new int[2];
        position[0] = casillaInicial;
        position[1] = casillaFinal;
        return position;
    }

    @Override
    public void comportamiento(Fichas ficha_turn) {
    }
}
