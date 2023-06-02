package domain;
import java.util.Random;

/**
 * The "Dado" class represents a dice used in the game.
 * It provides functionality for rolling the dice and setting modifiers on its faces.
 */
public class Dado {
    public static Cara[] resultados = new Cara[6];
    private int valorDado;
    private int numeroModificadores;

    /**
     * Constructor for the "Dado" class.
     *
     * @param modificador The number of modifiers to set on the dice.
     */
    public Dado(int modificador) {
        this.numeroModificadores = modificador;
        for (int i = 0; i < 6; i++) {
            resultados[i] = new Cara();
            resultados[i].setCara(i + 1);
        }
    }

    /**
     * Rolls the dice and returns the value obtained.
     *
     * @return The value obtained from rolling the dice.
     */
    public int lanzarDado() {
        valorDado = (int) Math.floor(Math.random() * 6 + 1);
        setModificadores();
        return valorDado;
    }

    /**
     * Sets modifiers on the dice faces.
     */
    public void setModificadores() {
        int n = 0, c, y;
        while (n <= numeroModificadores) {
            Random rand = new Random();
            y = rand.nextInt(0, 6);
            c = rand.nextInt(1, 4);
            if (resultados[y].getModificador() == null) {
                if (c == 1) {
                    resultados[y].setModificador(new ModAvance(c));
                    n++;
                } else if (c == 2) {
                    resultados[y].setModificador(new ModRetroceso(c));
                    n++;
                } else if (c == 3) {
                    resultados[y].setModificador(new ModCambio(c));
                    n++;
                }
            }
            n++;
        }
    }

    /**
     * Returns the face of the dice with the specified number.
     *
     * @param c The number of the dice face.
     * @return The "Cara" object representing the dice face.
     */
    public static Cara getCara(int c) {
        return resultados[c];
    }

    /**
     * Gets the value obtained from rolling the dice.
     *
     * @return The value obtained from rolling the dice.
     */
    public int getValorDado() {
        return valorDado;
    }
}
