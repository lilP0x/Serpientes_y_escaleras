package domain;

/**
 * The "CasillaNormal" class represents a normal square in the game.
 * It extends the abstract class "Casilla" and provides an empty action method.
 */
public class CasillaNormal extends Casilla {
    public Fichas fichas_turn = null;
    public int numero;
    public int valor = 0, i, j;

    /**
     * Constructor for the "CasillaNormal" class.
     *
     * @param numero The number of the square.
     */
    public CasillaNormal(int numero) {
        super(numero);
        this.numero = numero;
    }

    /**
     * Performs an empty action on the square.
     *
     * @param ficha The "Fichas" object to perform the action with.
     */
    @Override
    void action(Fichas ficha) {
        // Empty action
    }
}
