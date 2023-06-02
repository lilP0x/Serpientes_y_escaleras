package domain;

import java.util.Random;

/**
 * The "Stair" class represents a stair item in the game.
 * It implements the "Item" interface and provides methods to create a stair and perform its action.
 */
public class Stair implements Item {
    private int casillaInicial;
    private int casillaFinal;
    private Tablero tablero;
    private int max;

    /**
     * Constructor for objects of class Stair.
     *
     * @param tablero The "Tablero" object representing the game board.
     */
    public Stair(Tablero tablero) {
        this.tablero = tablero;
        max = tablero.getWidth() * tablero.getWidth();
        makeStair();
    }

    /**
     * Constructor for objects of class Stair with specified initial and final positions.
     *
     * @param tb    The "Tablero" object representing the game board.
     * @param i     The initial position of the stair.
     * @param j     The final position of the stair.
     */
    public Stair(Tablero tb, int i, int j) {
        this.tablero = tb;
        max = tablero.getWidth() * tablero.getWidth();
        this.casillaInicial = i;
        this.casillaFinal = j;
    }

    public void makeStair() {
        int min = 1;
        Random rand = new Random();
        casillaInicial = rand.nextInt((max - tablero.getWidth()) + 1) + min;
        if ((casillaInicial + tablero.getWidth()) < max) {
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
    }

    public int getCasillaInicial() {
        return casillaInicial;
    }
    
    public int getCasillaFinal() {
        return casillaFinal;
    }

    public int[] positionStair() {
        int[] position = new int[2];
        position[0] = casillaInicial;
        position[1] = casillaFinal;
        return position;
    }

    @Override
    public void comportamiento(Fichas ficha_turn) {
        // TODO: Implement behavior for the stair
    }
}
