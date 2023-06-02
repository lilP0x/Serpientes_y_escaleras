package domain;

/**
 * The "Casilla" class is an abstract class that represents a game square.
 * It contains information about the square's number, value, and position.
 * It also provides methods for setting and getting the position, checking if it's a special square,
 * getting the value, and performing an action on the square.
 */
public abstract class Casilla {
    public Fichas fichas_turn = null;
    public int numero;
    public int valor, i, j;

    /**
     * Constructor for the "Casilla" class.
     *
     * @param numero The number of the square.
     */
    public Casilla(int numero) {
    }

    /**
     * Sets the position of the square.
     *
     * @param c The position to set.
     */
    public void setPosition(int c) {
        numero = c;
    }

    /**
     * Gets the position of the square.
     *
     * @return The position of the square.
     */
    public int getPosition() {
        return numero;
    }

    /**
     * Checks if the square is a special square.
     *
     * @return True if the square is special, false otherwise.
     */
    public boolean isEspecial() {
        return false;
    }

    /**
     * Gets the value of the square.
     *
     * @return The value of the square.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Sets the row index of the square.
     *
     * @param num The row index to set.
     */
    public void setI(int num) {
        this.i = num;
    }

    /**
     * Sets the column index of the square.
     *
     * @param num The column index to set.
     */
    public void setJ(int num) {
        this.j = num;
    }

    /**
     * Gets the row and column indices of the square.
     *
     * @return An array containing the row and column indices.
     */
    public int[] getIJ() {
        int[] IJ = new int[2];
        IJ[0] = i;
        IJ[1] = j;
        return IJ;
    }

    /**
     * Gets the number of the square.
     *
     * @return The number of the square.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Performs an action on the square with the given "Fichas" object.
     *
     * @param ficha The "Fichas" object to perform the action with.
     */
    abstract void action(Fichas ficha);
}
