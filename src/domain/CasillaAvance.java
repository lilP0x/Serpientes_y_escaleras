package domain;
import java.util.ArrayList;

/**
 * The "CasillaAvance" class represents a special square that advances the player's position.
 * It extends the abstract class "Casilla" and provides functionality for finding stairs and performing the action.
 */
public class CasillaAvance extends Casilla {
    private int x, casillaInicialStair;
    public boolean especial = true;
    private Tablero tablero;
    private int valor = 5;

    /**
     * Constructor for the "CasillaAvance" class.
     *
     * @param numero The number of the square.
     * @param tb     The game board associated with the square.
     */
    public CasillaAvance(int numero, Tablero tb) {
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
     * Finds the nearest stair to the square.
     *
     * @return The distance to the nearest stair. Returns a positive value if the stair is ahead,
     *         or a negative value if the stair is behind.
     */
    public int findStair() {
        int menor = 1000, cabeza = 1000;
        ArrayList<Stair> stairs = tablero.getArrayStairs1();
        for (Stair a : stairs) {
            int comprobador = Math.abs((a.getCasillaInicial() - x));
            if (comprobador < menor) {
                menor = comprobador;
                cabeza = a.getCasillaInicial();
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
        movimiento = findStair();
        ficha.setPosition(movimiento + casillaInicial);
    }
}
