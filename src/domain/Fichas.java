package domain;
/**
 * The "Fichas" class represents a player's game piece or token.
 * It keeps track of the position and color of the token on the game board.
 */
public class Fichas {
    public int position;
    public String color;
    private Tablero tablero;
    private int max;

    /**
     * Constructor for the "Fichas" class.
     *
     * @param color   The color of the token.
     * @param tablero The "Tablero" object representing the game board.
     */
    public Fichas(String color, Tablero tablero) {
        this.color = color;
        this.tablero = tablero;
        this.max = tablero.getWidth() * tablero.getWidth();
        position = 1;
    }

    /**
     * Constructor for the "Fichas" class.
     *
     * @param color The color of the token.
     */
    public Fichas(String color) {
        this.color = color;
        this.position = 1;
        this.max = 100;
    }

    /**
     * Gets the current position of the token.
     *
     * @return The current position of the token.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the position of the token.
     *
     * @param position The new position of the token.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Moves the token by the given number of positions.
     *
     * @param position The number of positions to move the token.
     */
    public void jugar(int position) {
        if ((this.position + position) > max) {
            this.position += 0;
        } else {
            this.position += position;
        }
    }

    /**
     * Moves the token to the position of another player's token and swaps their positions.
     */
    public void jugarCambio() {
        Fichas ficha = tablero.getFicha(tablero.getTurno() + 1);
        int aux = position;
        setPosition(ficha.getPosition());
        ficha.setPosition(aux);
    }

    /**
     * Gets the maximum position on the game board.
     *
     * @return The maximum position on the game board.
     */
    public int getMax() {
        return max;
    }
}
