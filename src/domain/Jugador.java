package domain;
/**
 * The "Jugador" class represents a player in the game.
 * It keeps track of the player's name, color, game statistics, and game token.
 */
public class Jugador {
    public String name;
    public String color;
    public static boolean win = false;
    public Fichas ficha;
    private Tablero tablero;
    private int snakes = 0, stairs = 0, modificadores = 0, casillaMax = 1, casillasEspeciales;

    /**
     * Constructor for the "Jugador" class.
     *
     * @param n       The name of the player.
     * @param c       The color of the player.
     * @param tablero The "Tablero" object representing the game board.
     */
    public Jugador(String n, String c, Tablero tablero) {
        this.name = n;
        this.color = c;
        this.tablero = tablero;
        ficha = new Fichas(c, tablero);
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of snakes encountered by the player.
     *
     * @return The number of snakes encountered.
     */
    public int getSnakes() {
        return snakes;
    }

    /**
     * Increments the number of snakes encountered by the player.
     */
    public void setSnakes() {
        snakes++;
    }

    /**
     * Gets the number of stairs encountered by the player.
     *
     * @return The number of stairs encountered.
     */
    public int getStairs() {
        return stairs;
    }

    /**
     * Increments the number of stairs encountered by the player.
     */
    public void setStairs() {
        stairs++;
    }

    /**
     * Gets the number of modifiers encountered by the player.
     *
     * @return The number of modifiers encountered.
     */
    public int getModificadores() {
        return modificadores;
    }

    /**
     * Increments the number of modifiers encountered by the player.
     */
    public void setModificadores() {
        modificadores++;
    }

    /**
     * Gets the maximum position reached by the player on the game board.
     *
     * @return The maximum position reached by the player.
     */
    public int getCasillaMaxima() {
        return casillaMax;
    }

    /**
     * Sets the maximum position reached by the player on the game board.
     *
     * @param c The maximum position reached by the player.
     */
    public void setCasillaMaxima(int c) {
        this.casillaMax = c;
    }

    /**
     * Increments the number of special squares encountered by the player.
     */
    public void setCasillasEspeciales() {
        casillasEspeciales++;
    }

    /**
     * Gets the number of special squares encountered by the player.
     *
     * @return The number of special squares encountered.
     */
    public int getCasillaEspeciales() {
        return casillasEspeciales;
    }
}
