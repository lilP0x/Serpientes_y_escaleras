package domain;

/**
 * The "ModAvance" class represents a modifier that causes the player to move forward on the game board.
 * It extends the abstract class "Modifier" and provides functionality for performing the action.
 */
public class ModAvance extends Modifier {
    private int valor = 1;

    /**
     * Constructor for objects of the "ModAvance" class.
     *
     * @param v The value of the modifier.
     */
    public ModAvance(int v) {
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
        ficha.jugar(1);
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
    public String getTitle() {
        return "avance";
    }
}
