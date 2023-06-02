package domain;

/**
 * The "Cara" class represents a face of an object or entity.
 * It has an integer value and an associated modifier.
 */
public class Cara {
    private int valor = 0;
    private Modifier modi;

    /**
     * Default constructor for the "Cara" class.
     * Creates an instance of "Cara" without initializing any values.
     */
    public Cara() {
    }

    /**
     * Sets the value of the face.
     *
     * @param valor The integer value of the face.
     */
    public void setCara(int valor) {
        this.valor = valor;
    }

    /**
     * Sets the modifier of the face.
     *
     * @param mod The modifier to associate with the face.
     */
    public void setModificador(Modifier mod) {
        this.modi = mod;
    }

    /**
     * Gets the associated modifier of the face.
     *
     * @return The associated modifier.
     */
    public Modifier getModificador() {
        return modi;
    }

    /**
     * Sets an advance modifier for the face.
     *
     * @param mod The advance modifier to associate.
     */
    public void setModificador(ModAvance mod) {
        this.modi = mod;
    }

    /**
     * Sets a backward modifier for the face.
     *
     * @param mod The backward modifier to associate.
     */
    public void setModificador(ModRetroceso mod) {
        this.modi = mod;
    }
}