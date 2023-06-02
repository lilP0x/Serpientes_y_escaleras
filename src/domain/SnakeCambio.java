package domain;

import java.util.Random;

/**
 * The "SnakeCambio" class represents a snake item with a special behavior in the game.
 * It extends the "Snake" class and overrides the "action" and "comportamiento" methods.
 */
public class SnakeCambio extends Snake {
    private int casillaInicial;
    private int casillaFinal;
    private Tablero tablero;
    private int max;

    /**
     * Constructor for objects of class SnakeCambio.
     *
     * @param tablero The "Tablero" object representing the game board.
     */
    public SnakeCambio(Tablero tablero) {
        super(tablero);
        this.tablero = tablero;
    }

    /**
     * Constructor for objects of class SnakeCambio with specified initial and final positions.
     *
     * @param tb    The "Tablero" object representing the game board.
     * @param i     The initial position of the snake.
     * @param j     The final position of the snake.
     */
    public SnakeCambio(Tablero tb, int i, int j) {
        super(tb);
        this.tablero = tb;
        max = 100;
        this.casillaInicial = i;
        this.casillaFinal = j;
    }

    @Override
    public void action(Fichas ficha_turn) {
        if (ficha_turn.getPosition() == casillaFinal) {
            ficha_turn.setPosition(casillaInicial);
            this.comportamiento(ficha_turn);
        }
    }

    @Override
    public void comportamiento(Fichas ficha_turn) {
        if (ficha_turn.getPosition() == casillaInicial) {
            tablero.makeStairStatic(casillaInicial, casillaFinal);
            this.casillaInicial = 0;
            this.casillaFinal = 0;
        }
    }
}
