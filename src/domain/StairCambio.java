package domain;

import java.util.Random;

/**
 * The "StairCambio" class represents a special type of stair item in the game that triggers additional actions.
 * It extends the "Stair" class and provides methods to create a stair and perform its action with additional behavior.
 */
public class StairCambio extends Stair {
    private int casillaInicial;
    private int casillaFinal;
    private Tablero tablero;
    private int max;

    /**
     * Constructor for objects of class StairCambio.
     *
     * @param tablero The "Tablero" object representing the game board.
     */
    public StairCambio(Tablero tablero) {
        super(tablero);
        this.tablero = tablero;
    }

    /**
     * Constructor for objects of class StairCambio with specified initial and final positions.
     *
     * @param tb    The "Tablero" object representing the game board.
     * @param i     The initial position of the stair.
     * @param j     The final position of the stair.
     */
    public StairCambio(Tablero tb, int i, int j) {
        super(tb, i, j);
        this.tablero = tb;
        max = tablero.getWidth() * tablero.getWidth();
        this.casillaInicial = i;
        this.casillaFinal = j;
    }

    @Override
    public void makeStair() {
        int min = 1;
        Random rand = new Random();
        casillaInicial = rand.nextInt((max - tablero.getWidth() + 1)) + min;
        if ((casillaInicial + tablero.getWidth()) <= max) {
            casillaFinal = rand.nextInt((max - casillaInicial) + 1) + casillaInicial;
        } else {
            casillaFinal = max - 1;
        }
    }

    @Override
    public void action(Fichas ficha_turn) {
        if (ficha_turn.getPosition() == casillaInicial) {
            ficha_turn.setPosition(casillaFinal);
        }
        this.comportamiento(ficha_turn);
    }

    @Override
    public void comportamiento(Fichas ficha_turn) {
        if (ficha_turn.getPosition() == casillaFinal) {
            tablero.makeSnakesStatic(casillaInicial, casillaFinal);
            this.casillaInicial = 0;
            this.casillaFinal = 0;
        }
    }
}
