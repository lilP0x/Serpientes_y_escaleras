package domain;

/**
 * The "Modifier" class is an abstract class that represents a modifier in the game.
 * It provides a common structure for all types of modifiers and defines abstract methods for performing the action and getting the title.
 */
public abstract class Modifier {
    protected int valor;
    private Fichas ficha;

    /**
     * Constructor for the "Modifier" class.
     *
     * @param v The value of the modifier.
     */
    public Modifier(int v) {
        this.valor = v;
    }

    /**
     * Performs the action associated with the modifier using the given "Fichas" object.
     *
     * @param ficha The "Fichas" object to perform the action with.
     */
    abstract void doAction(Fichas ficha);

    /**
     * Gets the title of the modifier.
     *
     * @return The title of the modifier.
     */
    abstract String getTitle();

    /**
     * Gets the value of the modifier.
     *
     * @return The value of the modifier.
     */
    public int getValor() {
        return valor;
    }
}
