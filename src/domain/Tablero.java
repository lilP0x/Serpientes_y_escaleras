package domain;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.util.Random;

/**
 * The Tablero class represents the game board.
 * It contains the matrix of cells, players, dice, snakes, and stairs.
 * It handles the initialization of the game board and the gameplay.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tablero {
    private Casilla[][] tablero;
    private HashMap<String, Jugador> jugadores = new HashMap<String, Jugador> ();
    private HashMap<String, Fichas> fichas = new HashMap<String, Fichas> ();
    private ArrayList<Snake> snakes = new ArrayList<Snake> ();
    private ArrayList<Stair> stairs = new ArrayList<Stair> ();
    private int turn = 0, valorDado;
    private int width;
    private Dado dado; 
    private String color1, color2, name1, name2;
    private boolean win = false;
    private int numeroCasillasEspeciales = 0;
    private int numberSnakes;
    private int numberStairs;
    private int numeroModificadores = 0;
    private boolean pve = false;
   
    /**
     * Creates a new Tablero object.
     *
     * @param jugador1 The name of player 1.
     * @param jugador2 The name of player 2.
     * @param color1 The color of player 1.
     * @param color2 The color of player 2.
     * @param snake The number of snakes in the game.
     * @param stair The number of stairs in the game.
     * @param width The width of the game board.
     * @param numeroModificadores The number of modifiers in the game.
     * @param porcentajeCasillasEspeciales The percentage of special cells on the game board.
     */
    public Tablero(String jugador1, String jugador2, String color1, String color2, 
                   int snake, int stair, int width, int numeroModificadores, double porcentajeCasillasEspeciales) {
        this.width = width;
        this.color1 = color1;
        this.color2 = color2;
        this.name1 = jugador1;
        this.name2 = jugador2;
        this.numberSnakes = snake;
        this.numberStairs = stair;
        this.numeroModificadores = numeroModificadores;
        tablero = new Casilla[width][width];
        agregarJugador(jugador1, color1);
        agregarJugador(jugador2, color2);
        iniciarCasillas(porcentajeCasillasEspeciales);
        dado = new Dado(numeroModificadores);
        makeSnakes(snake);
        makeStair(stair);
    }
    
    /**
     * Creates a new Tablero object in player vs. environment mode.
     *
     * @param jugador1 The name of the player.
     * @param jugador2 The name of the computer-controlled player.
     * @param color1 The color of the player.
     * @param color2 The color of the computer-controlled player.
     * @param snake The number of snakes in the game.
     * @param stair The number of stairs in the game.
     * @param width The width of the game board.
     * @param numeroModificadores The number of modifiers in the game.
     * @param porcentajeCasillasEspeciales The percentage of special cells on the game board.
     * @param pve True if the game is player vs. environment, False otherwise.
     */
    public Tablero(String jugador1, String jugador2, String color1, String color2, 
                   int snake, int stair, int width, int numeroModificadores, double porcentajeCasillasEspeciales, boolean pve) {
        this.width = width;
        this.color1 = color1;
        this.color2 = color2;
        this.name1 = jugador1;
        this.name2 = jugador2;
        this.numberSnakes = snake;
        this.numberStairs = stair;
        this.numeroModificadores = numeroModificadores;
        this.pve = pve;
        tablero = new Casilla[width][width];
        agregarJugador(jugador1, color1);
        agregarJugador(jugador2, color2);
        iniciarCasillas(porcentajeCasillasEspeciales);
        dado = new Dado(numeroModificadores);
        makeSnakes(snake);
        makeStair(stair);
    }
    
    /**
     * Creates the snakes in the game.
     *
     * @param numberSnakes The number of snakes to create.
     */
    public void makeSnakes(int numberSnakes) {
        Snake snake;
        for (int i = 0; i < numberSnakes; i++) {
            snake = new Snake(this);    
            snakes.add(snake);
        }
    }
    
    /**
     * Creates the stairs in the game.
     *
     * @param numberStair The number of stairs to create.
     */
    public void makeStair(int numberStair) {
        Stair stair;
        for (int i = 0; i < numberStair; i++) {
            stair = new Stair(this);    
            stairs.add(stair);
        }
    }
    
    /**
     * Creates a static stair at the specified position.
     *
     * @param i The row index of the stair.
     * @param j The column index of the stair.
     */
    public void makeStairStatic(int i, int j) {
        Stair e = new Stair(this, i, j);
        this.numberStairs = numberStairs + 1;
        stairs.add(e);
    }
    
    public void makeSnakesStatic(int i, int j) {
        Snake s = new Snake(this, i, j);
        this.numberSnakes = numberSnakes + 1;
        snakes.add(s);
    }

    /**
     * Checks if a player has reached the end of the game board and wins the game.
     *
     * @param ficha The player's game piece.
     */
    public void win(Fichas ficha) {
        int verificador = ficha.getPosition();
        int fin = width * width;
        if (verificador == fin) {
            win = true;
        }
    }

    /**
     * Plays a turn of the game.
     */
    public void jugarTurno() {
        valorDado = dado.lanzarDado();
        Fichas ficha_turn;
        Jugador jugador_turn;
        if (turn % 2 == 0) {
            jugador_turn = jugadores.get(name1);
            ficha_turn = fichas.get(color1);
            ficha_turn.jugar(valorDado);
            asignarMax(ficha_turn, jugador_turn);
            win(ficha_turn);
            verificarModificadores(valorDado - 1, jugador_turn);
            verificarCasillaEspecial(ficha_turn, jugador_turn);
            verificarSnakeStair(ficha_turn, jugador_turn);
        } else {
            jugador_turn = jugadores.get(name2);
            ficha_turn = fichas.get(color2);
            ficha_turn.jugar(valorDado);
            verificarModificadores(valorDado - 1, jugador_turn);
            asignarMax(ficha_turn, jugador_turn);
            win(ficha_turn);
            verificarCasillaEspecial(ficha_turn, jugador_turn);
            verificarSnakeStair(ficha_turn, jugador_turn);
        }
        messageVictory();
        turn++;
    }

    /**
     * Plays a turn in player vs. environment mode.
     */
    public void jugarTurnoPve() {
    	System.out.println("entre aqui");
            valorDado = dado.lanzarDado();
            Fichas ficha_turn;
            Jugador jugador_turn;
            jugador_turn = jugadores.get(name1);
            ficha_turn = fichas.get(color1);
            ficha_turn.jugar(valorDado);
            asignarMax(ficha_turn, jugador_turn);
            win(ficha_turn);
            verificarModificadores(valorDado - 1, jugador_turn);
            verificarCasillaEspecial(ficha_turn, jugador_turn);
            verificarSnakeStair(ficha_turn, jugador_turn);
            System.out.println("Player is at position " + ficha_turn.getPosition() + " and the dice value is: " + valorDado);
            turn++;
            PlayerMaquina(fichas.get(color2), jugadores.get(name2));
            messageVictory();
        
    }

    /**
     * Plays a turn for the computer-controlled player in player vs. environment mode.
     *
     * @param maquina The game piece of the computer-controlled player.
     * @param maquinaPlayer The computer-controlled player.
     */
    public void PlayerMaquina(Fichas maquina, Jugador maquinaPlayer) {
    	System.out.println("tambien aqui");
            int valorDadoMaquina = dado.lanzarDado();
            maquina.jugar(valorDadoMaquina);
            verificarModificadores(valorDado - 1, maquinaPlayer);
            asignarMax(maquina, maquinaPlayer);
            win(maquina);
            verificarCasillaEspecial(maquina, maquinaPlayer);
            verificarSnakeStair(maquina, maquinaPlayer);
            //System.out.println("Maquina is at position " + maquina.getPosition() + " and the dice value is: " + valorDadoMaquina
        
    }
    public void asignarMax(Fichas ficha_turn, Jugador jugador_turn) {
        int max = jugador_turn.getCasillaMaxima();
        if (max < ficha_turn.getPosition()) {
            max = ficha_turn.getPosition();
        }
        jugador_turn.setCasillaMaxima(max);
    }

    /**
     * Verifies if there is a modifier on the current dice roll and prompts the player to use it.
     *
     * @param movimiento The dice roll result.
     * @param ju The current player.
     */
    public void verificarModificadores(int movimiento, Jugador ju) {
        Jugador jugador_turn = ju;
        Cara cara = Dado.getCara(movimiento);
        Modifier mod = cara.getModificador();
        if (mod != null && numeroModificadores != 0) {
            jugador_turn.setModificadores();
            int resp = JOptionPane.showConfirmDialog(null, "This face has a modifier. Do you want to use it? " + mod.getTitle());
            if (JOptionPane.OK_OPTION == resp) {
                jugarModificador(mod);
            }
        }
    }

    /**
     * Applies the effect of a modifier to the current player's game piece.
     *
     * @param mod The modifier to be used.
     */
    public void jugarModificador(Modifier mod) {
        Fichas ficha_turn;
        if (turn % 2 == 0) {
            ficha_turn = fichas.get(color1);
            mod.doAction(ficha_turn);
        } else {
            ficha_turn = fichas.get(color2);
            mod.doAction(ficha_turn);
        }
    }

    /**
     * Checks if the player's game piece has landed on a snake or ladder and performs the corresponding action.
     *
     * @param ficha The player's game piece.
     * @param jugador_turn The current player.
     */
    public void verificarSnakeStair(Fichas ficha, Jugador jugador_turn) {
        for (Snake a : snakes) {
            if (ficha.getPosition() == a.getCasillaFinal()) {
                jugador_turn.setSnakes();
                a.action(ficha);
            }
        }
        for (Stair a : stairs) {
            if (ficha.getPosition() == a.getCasillaInicial()) {
                jugador_turn.setStairs();
                a.action(ficha);
            }
        }
    }

    /**
     * Checks if the player's game piece has landed on a special tile and performs the corresponding action.
     *
     * @param ficha The player's game piece.
     * @param jugador_turn The current player.
     */
    public void verificarCasillaEspecial(Fichas ficha, Jugador jugador_turn) {
        Fichas fic = ficha;
        for (int i = 0; i <= width - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (ficha.getPosition() == tablero[i][j].getNumero() && tablero[i][j].isEspecial()) {
                    tablero[i][j].action(fic);
                    jugador_turn.setCasillasEspeciales();
                }
            }
        }
    }

    
    /**
     * Displays a victory message if a player has won the game.
     */
    public void messageVictory() {
        if (win == true) {
            System.out.println("The player has won!");
        }
    }

    /**
     * Checks if a player has won the game.
     *
     * @return true if a player has won, false otherwise.
     */
    public boolean verifyWin() {
        return win;
    }

    /**
     * Initializes the game board with normal and special tiles.
     *
     * @param porcentaje The percentage of special tiles on the board.
     */
    private void iniciarCasillas(double porcentaje) {
        int casillasE = casillasEspeciales(porcentaje);
        iniciarCasillasEspeciales(casillasE);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (tablero[i][j] == null)
                    tablero[i][j] = new CasillaNormal(0);
            }
        }
        setCasillas();
    }

    /**
     * Calculates the number of special tiles based on the percentage provided.
     *
     * @param porcentaje The percentage of special tiles on the board.
     * @return The number of special tiles.
     */
    public int casillasEspeciales(double porcentaje) {
        double casillasEspeciales = (width * width) * porcentaje;
        numeroCasillasEspeciales = (int) casillasEspeciales;
        return (int) casillasEspeciales;
    }

    /**
     * Initializes the special tiles on the game board.
     *
     * @param casillasE The number of special tiles to be placed.
     */
    public void iniciarCasillasEspeciales(int casillasE) {
        int parar = 0, casillaEspecial, posicionX, posicionY;
        while (parar < casillasE) {
            Random rand = new Random();
            casillaEspecial = rand.nextInt(1, 4);
            posicionX = rand.nextInt(width);
            posicionY = rand.nextInt(width);
            if (tablero[posicionX][posicionY] == null && numberSnakes == 0 && numberStairs == 0) {
                setCasillasEspeciales(casillaEspecial, posicionX, posicionY);
                parar++;
            } else if (tablero[posicionX][posicionY] == null) {
                casillaEspecial = rand.nextInt(1, 6);
                setCasillasEspeciales(casillaEspecial, posicionX, posicionY);
                parar++;
            }
        }
    }

    /**
     * Sets the specified special tile on the game board.
     *
     * @param casillaEspecial The type of special tile.
     * @param posicionX The x-coordinate of the tile.
     * @param posicionY The y-coordinate of the tile.
     */
    public void setCasillasEspeciales(int casillaEspecial, int posicionX, int posicionY) {
        if (casillaEspecial == 1 && tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = new CasillaSaltarina(0);
        } else if (casillaEspecial == 2 && tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = new CasillaSaltariaInversa(0);
        } else if (casillaEspecial == 3 && tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = new CasillaMuerte(0);
        } else if (casillaEspecial == 5 && tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = new CasillaAvance(0, this);
        } else if (casillaEspecial == 6 && tablero[posicionX][posicionY] == null) {
            tablero[posicionX][posicionY] = new CasillaRetroceso(0, this);
        }
    }

    /**
     * Sets the positions of the tiles on the game board.
     */
    public void setCasillas() {
        if (width % 2 == 0) {
            setCasillasPair();
        } else {
            setCasillasOdd();
        }
    }

    /**
     * Sets the positions of the tiles on the game board for a board with an odd width.
     */
    private void setCasillasOdd() {
        int numero = 1;
        for (int i = width - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (int j = 0; j <= width - 1; j++) {
                    tablero[i][j].setPosition(numero);
                    tablero[i][j].setI(i);
                    tablero[i][j].setJ(j);
                    numero++;
                }
            } else {
                for (int k = width - 1; k >= 0; k--) {
                    tablero[i][k].setPosition(numero);
                    tablero[i][k].setI(i);
                    tablero[i][k].setJ(k);
                    numero++;
                }
            }
        }
    }
    private void setCasillasPair() {
        int numero = 1, k;
        for (int i = width - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (k = width - 1; k >= 0; k--) {
                    tablero[i][k].setPosition(numero);
                    tablero[i][k].setI(i);
                    tablero[i][k].setJ(k);
                    numero++;
                }
            } else {
                for (int j = 0; j <= width - 1; j++) {
                    tablero[i][j].setPosition(numero);
                    tablero[i][j].setI(i);
                    tablero[i][j].setJ(j);
                    numero++;
                }
            }
        }
    }

    /**
     * Gets the position of a specific tile on the game board.
     *
     * @param i The row index of the tile.
     * @param j The column index of the tile.
     * @return The position of the tile.
     */
    public int getCasillas(int i, int j) {
        int res = tablero[i][j].getPosition();
        return res;
    }

    /**
     * Adds a player to the game.
     *
     * @param nombre The name of the player.
     * @param color The color of the player.
     */
    private void agregarJugador(String nombre, String color) {
        Jugador jugador = new Jugador(nombre, color, this);
        Fichas ficha = new Fichas(color, this);
        jugadores.put(nombre, jugador);
        fichas.put(color, ficha);
    }

    /**
     * Calculates the number of special tiles on the game board.
     *
     * @return The number of special tiles.
     */
    public int numeroCasillasEspeciales() {
        int cont = 0;
        for (int i = 0; i <= width - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (tablero[i][j].isEspecial()) {
                    cont += 1;
                }
            }
        }
        return cont;
    }

    /**
     * Returns the width of the game board.
     *
     * @return The width of the game board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the current turn number.
     *
     * @return The turn number.
     */
    public int getTurno() {
        return turn;
    }

    /**
     * Gets the position of a player's piece on the game board.
     *
     * @param c The color of the player's piece.
     * @return The position of the player's piece.
     */
    public int[] getFichaPosition(String c) {
        Fichas ficha = fichas.get(c);
        int valor = ficha.getPosition();
        Casilla cas = buscarBox(valor);
        int[] res = cas.getIJ();

        return res;
    }

    /**
     * Gets the player's piece based on the current turn.
     *
     * @param turn The current turn number.
     * @return The player's piece.
     */
    public Fichas getFicha(int turn) {
        Fichas number;
        if (turn % 2 == 0) {
            number = fichas.get(color1);
        } else {
            number = fichas.get(color2);
        }
        return number;
    }

    /**
     * Gets the position of a player's piece on the game board.
     *
     * @param c The color of the player's piece.
     * @return The position of the player's piece.
     */
    public int getFichaPosition1(String c) {
        Fichas ficha = fichas.get(c);
        int valor = ficha.getPosition();
        return valor;
    }

    /**
     * Searches for a specific tile on the game board based on its value.
     *
     * @param valor The value of the tile to search for.
     * @return The tile with the specified value.
     */
    public Casilla buscarBox(int valor) {
        Casilla cas = tablero[0][0];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {

                if (tablero[i][j].getNumero() == valor) {
                    cas = tablero[i][j];
                }
            }
        }
        return cas;
    }

    /**
     * Gets the number of snakes owned by a player.
     *
     * @param name The name of the player.
     * @return The number of snakes owned by the player.
     */
    public int getNumberSnakes(String name) {
        Jugador jugador = jugadores.get(name);
        int valor = jugador.getSnakes();
        return valor;
    }

    /**
     * Gets the number of stairs owned by a player.
     *
     * @param name The name of the player.
     * @return The number of stairs owned by the player.
     */
    public int getNumberStairs(String name) {
        Jugador jugador = jugadores.get(name);
        int valor = jugador.getStairs();
        return valor;
    }

    /**
     * Gets the number of modifiers owned by a player.
     *
     * @param name The name of the player.
     * @return The number of modifiers owned by the player.
     */
    public int getNumberMod(String name) {
        Jugador jugador = jugadores.get(name);
        int valor = jugador.getModificadores();
        return valor;
    }

    /**
     * Gets the maximum box number reached by a player.
     *
     * @param name The name of the player.
     * @return The maximum box number reached by the player.
     */
    public int getBoxMax(String name) {
        Jugador jugador = jugadores.get(name);
        int valor = jugador.getCasillaMaxima();
        return valor;
    }
    /**
     * Gets the number of special boxes owned by a player.
     *
     * @param name The name of the player.
     * @return The number of special boxes owned by the player.
     */
    public int numerBoxPlayer(String name) {
        Jugador jugador = jugadores.get(name);
        int valor = jugador.getCasillaEspeciales();
        return valor;
    }

    /**
     * Gets the value of the dice.
     *
     * @return The value of the dice.
     */
    public int getDado() {
        return dado.getValorDado();
    }

    /**
     * Gets the number of special boxes on the game board.
     *
     * @return The number of special boxes.
     */
    public int getNumeroCasillasEspeciales() {
        return numeroCasillasEspeciales;
    }

    /**
     * Gets the game board matrix.
     *
     * @return The game board matrix.
     */
    public Casilla[][] getMatrixTablero() {
        return tablero;
    }

    /**
     * Gets the number of stairs on the game board.
     *
     * @return The number of stairs.
     */
    public int getNumeroStairs() {
        return numberStairs;
    }

    /**
     * Gets the number of snakes on the game board.
     *
     * @return The number of snakes.
     */
    public int getNumeroSnakes() {
        return numberSnakes;
    }

    /**
     * Gets the value of the dice.
     *
     * @return The value of the dice.
     */
    public int getValorDado() {
        return valorDado;
    }

    /**
     * Gets an array of stairs on the game board.
     *
     * @return An array of stairs.
     */
    public Stair[] getArrayStairs() {
        int i = 0;
        Stair[] s = new Stair[numberStairs];
        for (Stair a : stairs) {
            if (a instanceof Stair) {
                s[i] = a;
                i++;
            }
        }
        return s;
    }

    /**
     * Gets an array of snakes on the game board.
     *
     * @return An array of snakes.
     */
    public Snake[] getArraySnakes() {
        int i = 0;
        Snake[] e = new Snake[numberSnakes];
        for (Snake a : snakes) {
            if (a instanceof Snake) {
                e[i] = a;
                i++;
            }
        }
        return e;
    }

    /**
     * Gets an ArrayList of stairs on the game board.
     *
     * @return An ArrayList of stairs.
     */
    public ArrayList<Stair> getArrayStairs1() {
        return stairs;
    }

    /**
     * Gets an ArrayList of snakes on the game board.
     *
     * @return An ArrayList of snakes.
     */
    public ArrayList<Snake> getArraySnakes1() {
        return snakes;
    }

    /**
     * Gets the player's piece based on the color.
     *
     * @param c The color of the player's piece.
     * @return The player's piece.
     */
    public Fichas getFichaColor(String c) {
        Fichas f = fichas.get(c);
        return f;
    }

    /**
     * Checks if a player has won the game.
     *
     * @return True if a player has won, False otherwise.
     */
    public boolean getWin() {
        return win;
    }
}
