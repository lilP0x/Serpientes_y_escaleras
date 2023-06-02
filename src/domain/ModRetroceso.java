package domain;

/**
 * The "ModRetroceso" class represents a modifier that causes the player to move backward on the board.
 * It extends the abstract class "Modifier" and provides functionality for performing the action and getting the title.
 */
public class ModRetroceso extends Modifier {
    private int valor = 2;

    /**
     * Constructor for the "ModRetroceso" class.
     *
     * @param v The value of the modifier.
     */
    public ModRetroceso(int v) {
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
        ficha.jugar(-1);
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
        return "retroceso";
    }
}
