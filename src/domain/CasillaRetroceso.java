package domain;

import java.util.ArrayList;

/**
 * The "CasillaRetroceso" class represents a square that causes the player to move back on the board.
 * It extends the abstract class "Casilla" and provides functionality for performing the action.
 */
public class CasillaRetroceso extends Casilla {
    private int x;
    public boolean especial = true;
    private Tablero tablero;
    private int valor = 6;

    /**
     * Constructor for objects of class CasillaRetroceso.
     *
     * @param numero The number of the square.
     * @param tb The "Tablero" object representing the game board.
     */
    public CasillaRetroceso(int numero, Tablero tb) {
        super(numero);
        this.tablero = tb;
        this.x = numero;
    }

    /**
     * Checks if the square is a special square.
     *
     * @return True if the square is special, false otherwise.
     */
    @Override
    public boolean isEspecial() {
        return especial;
    }

    /**
     * Finds the nearest snake's head to the current square.
     *
     * @return The number of squares to move back if a snake is found, otherwise 0.
     */
    public int findStair() {
        int menor = 1000, cabeza = 1000;
        ArrayList<Snake> snakes = tablero.getArraySnakes1();
        for (Snake a : snakes) {
            int comprobador = Math.abs((a.getCasillaFinal() - x));
            if (comprobador < menor) {
                menor = comprobador;
                cabeza = a.getCasillaFinal();
            }
        }
        if (cabeza - x > 1) {
            return menor;
        }
        return -menor;
    }

    /**
     * Gets the value of the square.
     *
     * @return The value of the square.
     */
    @Override
    public int getValor() {
        return valor;
    }

    /**
     * Performs the action associated with the square using the given "Fichas" object.
     *
     * @param ficha The "Fichas" object to perform the action with.
     */
    @Override
    void action(Fichas ficha) {
        int movimiento = 0, casillaInicial = ficha.getPosition();
        if (x == ficha.getPosition()) {
            movimiento = findStair();
            ficha.setPosition(movimiento + casillaInicial);
        }
    }
}
