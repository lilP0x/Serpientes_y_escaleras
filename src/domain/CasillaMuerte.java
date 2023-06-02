package domain;

/**
 * The "CasillaMuerte" class represents a special square that causes the player to go back to the starting position.
 * It extends the abstract class "Casilla" and provides functionality for performing the action.
 */
public class CasillaMuerte extends Casilla {
    private int x;
    private boolean especial = true;
    private int valor = 3;

    /**
     * Constructor for the "CasillaMuerte" class.
     *
     * @param numero The number of the square.
     */
    public CasillaMuerte(int numero) {
        super(numero);
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
        ficha.setPosition(1);

    }
}
