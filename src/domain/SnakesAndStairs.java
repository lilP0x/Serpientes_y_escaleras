package domain;

/**
 * SnakesAndStairs class represents the game "Snakes and Stairs".
 * It manages the game board and player turns.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakesAndStairs {
    private Casilla[][] casi;
    private Tablero tablero;
    private int turn = -1;
    private String color1, color2, name1, name2;
    private Snake[] s;
    private Stair[] e;
    private boolean win = false, pve = false;

    /**
     * Constructor for SnakesAndStairs class.
     * 
     * @param nombre1       The name of the first player.
     * @param nombre2       The name of the second player.
     * @param colorp1       The color of the first player's piece.
     * @param colorp2       The color of the second player's piece.
     * @param width         The width of the game board.
     * @param snakes        The number of snakes on the game board.
     * @param stairs        The number of stairs on the game board.
     * @param casillasEsp   The percentage of special boxes on the game board.
     * @param modifier      The number of modifiers on the game board.
     */
    public SnakesAndStairs(String nombre1, String nombre2, String colorp1, String colorp2, int width, int snakes, int stairs, Double casillasEsp, int modifier) {
        this.color1 = colorp1;
        this.color2 = colorp2;
        this.name1 = nombre1;
        this.name2 = nombre2;

        tablero = new Tablero(nombre1, nombre2, colorp1, colorp2, snakes, stairs, width, modifier, casillasEsp);
        casi = tablero.getMatrixTablero();
        s = tablero.getArraySnakes();
        e = tablero.getArrayStairs();
    }
    
    /**
     * Constructor for SnakesAndStairs class with PvE (Player vs Environment) mode.
     * 
     * @param nombre1       The name of the player.
     * @param nombre2       The name of the computer opponent.
     * @param colorp1       The color of the player's piece.
     * @param colorp2       The color of the computer opponent's piece.
     * @param width         The width of the game board.
     * @param snakes        The number of snakes on the game board.
     * @param stairs        The number of stairs on the game board.
     * @param casillasEsp   The percentage of special boxes on the game board.
     * @param modifier      The number of modifiers on the game board.
     * @param pve           Flag indicating PvE mode.
     */
    public SnakesAndStairs(String nombre1, String nombre2, String colorp1, String colorp2, int width, int snakes, int stairs, Double casillasEsp, int modifier, boolean pve) {
        this.color1 = colorp1;
        this.color2 = colorp2;
        this.name1 = nombre1;
        this.name2 = nombre2;
        this.pve = pve;

        tablero = new Tablero(nombre1, nombre2, colorp1, colorp2, snakes, stairs, width, modifier, casillasEsp, true);
        casi = tablero.getMatrixTablero();
        s = tablero.getArraySnakes();
        e = tablero.getArrayStairs();
    }

    /**
     * Gets the position of a snake on the game board.
     * 
     * @param i The index of the snake.
     * @return An array containing the position of the snake.
     */
    public int[] positionSnake(int i) {
        int[] position = s[i].positionSnake();
        return position;
    }

    /**
     * Gets the position of a stair on the game board.
     * 
     * @param i The index of the stair.
     * @return An array containing the position of the stair.
     */
    public int[] positionStair(int i) {
        int[] position = e[i].positionStair();
        return position;
    }

    /**
     * Plays a turn in the game.
     */
    public void jugar() {
        if (pve) {
            tablero.jugarTurnoPve();
            System.out.println("pve");
            turn++;
        } else {
            tablero.jugarTurno();
            System.out.println("pvp");
        }
        turn++;
    }

    /**
     * Gets the game board.
     * 
     * @return The game board.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Checks if PvE (Player vs Environment) mode is enabled.
     * 
     * @return True if PvE mode is enabled, False otherwise.
     */
    public boolean getPve() {
        return pve;
    }

    /**
     * Gets the value of the dice.
     * 
     * @return The value of the dice.
     */
    public int dado() {
        return tablero.getDado();
    }

    /**
     * Gets the names of the players.
     * 
     * @return An array containing the names of the players.
     */
    public String[] getNames() {
        String[] names = new String[2];
        names[0] = name1;
        names[1] = name2;
        return names;
    }

    /**
     * Gets the colors of the players' pieces.
     * 
     * @return An array containing the colors of the players' pieces.
     */
    public String[] getColors() {
        String[] colors = new String[2];
        colors[0] = color1;
        colors[1] = color2;
        return colors;
    }
    


        /**
         * Gets the position of the current player's piece on the game board.
         * 
         * @return An array containing the position of the current player's piece.
         */
        public int[] positionFicha() {
            int[] position;
            if (turn % 2 == 0) {
                position = tablero.getFichaPosition(color1);
            } else {
                position = tablero.getFichaPosition(color2);
            }
            return position;
        }

        /**
         * Gets the position of the computer opponent's piece on the game board in PvE mode.
         * 
         * @return An array containing the position of the computer opponent's piece.
         */
        public int[] positionFichasPve() {
            int[] position;
            position = tablero.getFichaPosition(color1);
            return position;
        }

        /**
         * Gets the position of the first player's piece on the game board.
         * 
         * @return The position of the first player's piece.
         */
        public int positionFicha1() {
            int position = 0;
            if (turn % 2 == 0) {
                position = tablero.getFichaPosition1(color1);
            } else {
                position = tablero.getFichaPosition1(color2);
            }
            return position;
        }

        /**
         * Gets the number of snakes owned by the current player.
         * 
         * @return The number of snakes owned by the current player.
         */
        public int getSnakes() {
            int number;
            if (turn % 2 == 0) {
                number = tablero.getNumberSnakes(name1);
            } else {
                number = tablero.getNumberSnakes(name2);
            }
            return number;
        }

        /**
         * Gets the number of stairs owned by the current player.
         * 
         * @return The number of stairs owned by the current player.
         */
        public int getStairs() {
            int number;
            if (turn % 2 == 0) {
                number = tablero.getNumberStairs(name1);
            } else {
                number = tablero.getNumberStairs(name2);
            }
            return number;
        }

        /**
         * Gets the maximum box number on the game board owned by the current player.
         * 
         * @return The maximum box number owned by the current player.
         */
        public int casillaMaxima() {
            int number;
            if (turn % 2 == 0) {
                number = tablero.getBoxMax(name1);
            } else {
                number = tablero.getBoxMax(name2);
            }
            return number;
        }

        /**
         * Gets the number of boxes owned by the current player.
         * 
         * @return The number of boxes owned by the current player.
         */
        public int numerBoxPlayer() {
            int number;
            if (turn % 2 == 0) {
                number = tablero.numerBoxPlayer(name1);
            } else {
                number = tablero.numerBoxPlayer(name2);
            }
            return number;
        }

        /**
         * Gets the number of modifiers owned by the current player.
         * 
         * @return The number of modifiers owned by the current player.
         */
        public int numerModPlayer() {
            int number;
            if (turn % 2 == 0) {
                number = tablero.getNumberMod(name1);
            } else {
                number = tablero.getNumberMod(name2);
            }
            return number;
        }

        /**
         * Gets the number of special boxes with a given number on the game board.
         * 
         * @param numero The number of the special box.
         * @return The number of special boxes with the given number.
         */
        public int casillasEspeciales(int numero) {
            int numeroC = 0;
            Casilla[][] tb = tablero.getMatrixTablero();
            for (int i = 0; i <= tablero.getWidth() - 1; i++) {
                for (int j = 0; j <= tablero.getWidth() - 1; j++) {
                    if (numero == tb[i][j].getValor()) {
                        numeroC++;
                    }
                }
            }
            return numeroC;
        }

        /**
         * Gets the values of the special boxes on the game board.
         * 
         * @return An array containing the values of the special boxes.
         */
        public int[] setCasillasEspeciales() {
            int k = 0;
            int[] valoresCasillasEspeciales = new int[tablero.getNumeroCasillasEspeciales()];
            Casilla[][] tb = tablero.getMatrixTablero();
            for (int i = 0; i <= tablero.getWidth() - 1; i++) {
                for (int j = 0; j <= tablero.getWidth() - 1; j++) {
                    if (tb[i][j].isEspecial() && k < tablero.getNumeroCasillasEspeciales()) {
                        valoresCasillasEspeciales[k] = tb[i][j].getValor();
                        k++;
                    }
                }
            }
            return valoresCasillasEspeciales;
        }

        /**
         * Gets the positions (i, j) of the special boxes on the game board.
         * 
         * @return A 2D array containing the positions (i, j) of the special boxes.
         */
        public int[][] posicionesIj() {
            int[] IJ;
            int k = 0;
            int[][] posicionesCasillasEspeciales = new int[tablero.numeroCasillasEspeciales() + 1][2];
            Casilla[][] tb = tablero.getMatrixTablero();
            for (int i = 0; i <= tablero.getWidth() - 1; i++) {
                for (int j = 0; j <= tablero.getWidth() - 1; j++) {
                    if (tb[i][j].isEspecial()) {
                        IJ = tb[i][j].getIJ();
                        posicionesCasillasEspeciales[k][0] = IJ[0];
                        posicionesCasillasEspeciales[k][1] = IJ[1];
                        k++;
                    }
                }
            }
            return posicionesCasillasEspeciales;
        }

        /**
         * Gets the current turn number.
         * 
         * @return The current turn number.
         */
        public int getTurno() {
            return turn;
        }

        /**
         * Checks if a player has won the game.
         * 
         * @return True if a player has won, False otherwise.
         */
        public boolean getWin() {
            win = tablero.verifyWin();
            return win;
        }

        /**
         * Gets the box at the specified position (i, j) on the game board.
         * 
         * @param i The row index.
         * @param j The column index.
         * @return The box at the specified position.
         */
        public Casilla casillaIJ(int i, int j) {
            Casilla cas = casi[i][j];
            return cas;
        }

        /**
         * Gets the game board matrix.
         * 
         * @return The game board matrix.
         */
        public Casilla[][] getMatrizTablero() {
            return casi;
        }

        /**
         * Gets the array of snakes.
         * 
         * @return The array of snakes.
         */
        public Snake[] getSnakesI() {
            return s;
        }

        /**
         * Gets the array of stairs.
         * 
         * @return The array of stairs.
         */
        public Stair[] getStairI() {
            return e;
        }
    }
