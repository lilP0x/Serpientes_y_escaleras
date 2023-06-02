package domain;

/**
 * The "ModCambio" class represents a modifier that causes the player to switch positions with another player on the game board.
 * It extends the abstract class "Modifier" and provides functionality for performing the action.
 */
public class ModCambio extends Modifier {
    private int valor = 3;

    /**
     * Constructor for objects of the "ModCambio" class.
     *
     * @param v The value of the modifier.
     */
    public ModCambio(int v) {
        super(v);
        this.valor = v;
    }

    /**
     * Performs the action associated with the modifier using the given "Fichas" object.
     *
     * @param ficha The "Fichas" object to perform the action with.
     */
    @Override
    public void doAction(Fichas ficha) {
        ficha.jugarCambio();
    }

    /**
     * Gets the value of the modifier.
     *
     * @return The value of the modifier.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Gets the title of the modifier.
     *
     * @return The title of the modifier.
     */
    @Override
    public String getTitle() {
        return "cambio";
    }
}
