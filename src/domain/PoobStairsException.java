package domain;

public class PoobStairsException extends Exception{
	
	public static final String SAME_COLOR_PLAYER = "Los jugadores tienen color igual";
	public static final String SAME_NAME_PLAYER = "Los jugadores tienen nombre igual";
	public static final String BOARD_NULL = "El tablero tiene tamaño cero";
	public static final String BOARD_OVER_SIZE = "El tablero tiene un tamaño invalido";
	public static final String DIE_NULL_MODIFICATOR = "El dado debe tener almenos un modificador";
	public static final String DOUBLE_NEED_IT = "La probabilidad debe ser un double";
	public static final String COLOR_NULL = "El jugador no escogio ningun color";
	public static final String NAME_NULL = "El jugador no tiene nombre";
	public static final String WIN = "El jugador gano";
	
	public PoobStairsException(String message) {
		
		super(message);
		
	}

}
