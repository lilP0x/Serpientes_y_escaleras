package domain;
/**
 * Write a description of class CasillaPreguntona here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CasillaPreguntona extends Casilla
{
    private int x;
    private boolean especial = true;
    private int valor = 4;
    /**
     * Constructor for objects of class CasillaPreguntona
     */
    public CasillaPreguntona(int numero)
    {
        super(numero);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }

	@Override
	void action(Fichas ficha) {
		// TODO Auto-generated method stub
		System.out.println("perra, literal eres una perrrrrra");
	}
}
