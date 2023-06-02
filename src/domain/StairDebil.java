package domain;

import java.util.Random;

public class StairDebil extends Stair{

    private int casillaInicial, casillaFinal;
    private Tablero tablero;
    private int max;
    
    public StairDebil(Tablero tablero){
    	super(tablero);
        this.tablero = tablero;  
        max = tablero.getWidth() * tablero.getWidth();

    }
  
    public StairDebil(Tablero tb, int i, int j) {
    	super(tb, i, j);
        this.tablero = tb;  
        max = tablero.getWidth() * tablero.getWidth();
        this.casillaInicial = i;
        this.casillaFinal = j;
    }
    
    @Override
    public void makeStair(){
        int min = 1;
        Random rand = new Random();
        casillaInicial = rand.nextInt((max - tablero.getWidth()) + 1) + min;
        if((casillaInicial + tablero.getWidth()) < max){
            casillaFinal = rand.nextInt((max-casillaInicial) +1) + casillaInicial;
        }else{
            casillaFinal = max-1;
        }
    }
    
    @Override
    public void action(Fichas ficha_turn){
        if (ficha_turn.getPosition() == casillaInicial)
            ficha_turn.setPosition(casillaFinal);
        	comportamiento(ficha_turn);
    }
    
	@Override
	public void comportamiento(Fichas ficha_turn) {	
		int[] nuevaCC = new int[2];
			nuevaCC = verificarMitad();
			System.out.println(nuevaCC[0]);
			System.out.println(nuevaCC[1]);
			int nuevaCasillaFinal = tablero.getCasillas(nuevaCC[0], nuevaCC[1]);
			this.casillaFinal = nuevaCasillaFinal;

	}
	
	public int[] verificarMitad() {
		int[] mitad = new int[2], aux = new int[2], aux2 = new int[2];
		Casilla cas = tablero.buscarBox(casillaFinal), cas1 = tablero.buscarBox(casillaInicial);
		aux = cas1.getIJ();
		aux2 = cas.getIJ();
		mitad[0] = (aux2[0] - (int)(Math.abs((aux[0] - aux2[0])/2)));
		mitad[1] = (aux2[1] -(int)(Math.abs((aux[1] - aux2[1])/2)));
		
		return mitad;
	}

}
