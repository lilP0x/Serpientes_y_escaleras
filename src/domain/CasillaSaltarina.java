package domain;

/**
 * The "CasillaSaltarina" class represents a square that causes the player to move forward by a certain number of squares.
 * It extends the abstract class "Casilla" and provides functionality for performing the action.
 */
public class CasillaSaltarina extends Casilla {
    private int x;
    public boolean especial = true;
    private int valor = 1;

    /**
     * Constructor for objects of class CasillaSaltarina.
     *
     * @param numero The number of the square.
     */
    public CasillaSaltarina(int numero) {
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
        if (ficha.getPosition() <= ficha.getMax()) {
            ficha.jugar(5);
        }
    }
}
