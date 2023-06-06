package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import domain.*;





public class PoobStairsGUI extends JFrame {
	private PantallaDeInicioGUI pantalla;
	private String namep1,namep2;
	private JPanel panelBotones;
	private JPanel panelDerecha;
	private JPanel panelTablero;
	private JPanel panelPlayer1;
	private JPanel panelPlayer2;
	private JPanel panelDado;
	private JButton lanzar,p1,p2,finalizar,rendirse;
	private JLabel etiqueta,aux;
    private ImageIcon imagen1;
    private ImageIcon imagen2;
    private ImageIcon imagen3;
    private ImageIcon imagen4;
    private ImageIcon imagen5;
    private ImageIcon imagen6;
    private ImageIcon imagen7;
    private ImageIcon imagen8;
    private ImageIcon imagen9;
    private ImageIcon imagen10;
    private ImageIcon imagen11;
	private JPanel [][] board;
	private String colorP1, colorP2;
	private static String ruta1 ="/recursos/cara 1.jpg";
	private static String ruta2 = "/recursos/cara 2.jpg";
	private static String ruta3 = "/recursos/cara 3.jpg";
	private static String ruta4 = "/recursos/cara 4.jpg";
	private static String ruta5 = "/recursos/cara 5.jpg";
	private static String ruta6 = "/recursos/cara 6.jpg";
	private static String ruta7 ="/recursos/saltarina.png";
	private static String ruta8 = "/recursos/saltarinaInversa.jpg";
	private static String ruta9 = "/recursos/muerte.jpg";
	private static String ruta10 = "/recursos/avance.png";
	private static String ruta11 = "/recursos/retroceso.png";

	private int width;
	private int snakes;
	private int stairs;
	private Double casillasEspeciales;
	private int modificador;
	private SnakesAndStairs juego;
	private int[] posiciones = new int[4];
	private JLabel[][] labelMatrixPlayer1;
	private JLabel[][] labelMatrixPlayer2;
	private boolean pve = false;
	
	
	public PoobStairsGUI(String nombre1,String nombre2,String colorp1, String colorp2,int width, int snakes,int stairs,Double casillasEsp,int modifier){
		juego = new SnakesAndStairs(nombre1, nombre2, colorp1,  colorp2, width, snakes,stairs, casillasEsp, modifier);
		this.namep1 = nombre1;
		this.namep2 = nombre2;
		this.colorP1 = colorp1;
		this.colorP2 = colorp2;
		this.width = width;
		this.snakes = snakes;
		this.stairs = stairs;
		this.casillasEspeciales = casillasEsp;
		this.modificador = modifier; 
		prepareScreen();
		prepareElements();
		prepareActions();
		prepareTablero();
		//System.out.println("el numero de casillas especiales es " + juego.getCasillasEspeciales());
	}
	
	public PoobStairsGUI(String nombre1,String nombre2,String colorp1, String colorp2,int width, int snakes,int stairs,Double casillasEsp,int modifier, boolean pve){
		juego = new SnakesAndStairs(nombre1, nombre2, colorp1,  colorp2, width, snakes,stairs, casillasEsp, modifier, pve);
		this.namep1 = nombre1;
		this.namep2 = nombre2;
		this.colorP1 = colorp1;
		this.colorP2 = colorp2;
		this.width = width;
		this.snakes = snakes;
		this.stairs = stairs;
		this.casillasEspeciales = casillasEsp;
		this.modificador = modifier; 
		this.pve = pve;
		System.out.println(pve);
		prepareScreen();
		prepareElements();
		prepareActions();
		prepareTablero();
		//System.out.println("el numero de casillas especiales es " + juego.getCasillasEspeciales());
	}
	
	/**
	 * Displays a confirmation dialog when attempting to close the window and takes actions
	 * based on the user's selected response.
	 */
	public void exitWindow(){
		int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir","¿Salir?",JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_NO_OPTION){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else if (result == JOptionPane.NO_OPTION){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
	/**
	 * Prepares and arranges the elements of the window layout, including the game board and
	 * the right panel.
	 */
	public void prepareElements() {
	    JPanel derecha = prepareBoardInfo();
	    JPanel tablero = prepareBoard(width);
	    preparePlayers();
	    setLayout(new GridBagLayout());

	    tablero.setPreferredSize(new Dimension(300, 300)); // Tamaño preferido del tablero
	    derecha.setPreferredSize(new Dimension(300, 300)); // Tamaño preferido del panel derecho
	    

	    GridBagConstraints constraints = new GridBagConstraints();

	    // Tablero
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weightx = 0.7; // Peso horizontal para el tablero
	    constraints.weighty = 1.0; // Peso vertical para el tablero
	    constraints.fill = GridBagConstraints.BOTH;
	    add(tablero, constraints);

	    // Panel derecho
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    constraints.weightx = 0.2; // Peso horizontal para el panel derecho
	    constraints.weighty = 1.0; // Peso vertical para el panel derecho
	    constraints.fill = GridBagConstraints.BOTH;
	    add(derecha, constraints);
	    
	

	}

	/**
	 * Prepares and returns a JPanel containing a dice representation.
	 *
	 * @return The JPanel containing the dice representation.
	 */
	private JPanel preparepDado() {
		panelDado = new JPanel();
		etiqueta = new JLabel();
		panelDado.setLayout(new GridBagLayout());

	    GridBagConstraints constraints = new GridBagConstraints();

	    // Tablero
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.weightx = 1.0; // Peso horizontal para el tablero
	    constraints.weighty = 1.0; // Peso vertical para el tablero
	    constraints.fill = GridBagConstraints.BOTH;
	    panelDado.add(etiqueta);
	
		
		return panelDado;
	}

	/**
	 * Prepares and returns a JPanel containing buttons for the game actions.
	 *
	 * @return The JPanel containing the buttons.
	 */
	private JPanel prepareBotones() {
		panelBotones = new JPanel();
		lanzar = new JButton("lanzar");
		
		panelBotones.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5); // Espacio vertical entre los componentes

        lanzar.setFont(new Font("Courier New", Font.PLAIN, 12));
        lanzar.setBackground(new Color(150, 200, 100));
        lanzar.setForeground(Color.WHITE);
        finalizar = new JButton("finalizar");
        finalizar.setFont(new Font("Courier New", Font.PLAIN, 12));
        finalizar.setBackground(new Color(150, 200, 100));
        finalizar.setForeground(Color.WHITE);
        rendirse = new JButton("rendirse");
        rendirse.setFont(new Font("Courier New", Font.PLAIN, 12));
        rendirse.setBackground(new Color(150, 200, 100));
        rendirse.setForeground(Color.WHITE);

        
        constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.gridwidth = 1;
        panelBotones.add(lanzar, constraints);
	    constraints.gridx = 2;
	    constraints.gridy = 0;
        panelBotones.add(rendirse, constraints);
        constraints.gridx = 1;
	    constraints.gridy = 0;
        panelBotones.add(finalizar, constraints);
		
		
		return panelBotones;
	}

	/**
	 * Prepares and returns a JPanel representing the game board with a specified size.
	 *
	 * @param tamaño The size of the game board.
	 * @return The JPanel representing the game board.
	 */
	public JPanel prepareBoard(int tamaño) {
	    panelTablero = new JPanel();
	    panelTablero.setLayout(new GridLayout(width, width));
	    board = new JPanel[width][width];
	    
	    if (tamaño % 2 != 0) {prepareNumbersPair(tamaño, board, panelTablero);
	    
	    }else{prepareNumbersOdd(tamaño, board, panelTablero);
	    
	    } 
	    
	    
	    for (int i = 0; i < width; i++) {
	        for (int j = 0; j < width; j++) {
	            panelTablero.add(board[i][j]);
	        }
	    }
	    
	    imagen7 = new ImageIcon(getClass().getResource(ruta7));
        imagen8 = new ImageIcon(getClass().getResource(ruta8));
        imagen9 = new ImageIcon(getClass().getResource(ruta9));
        imagen10 = new ImageIcon(getClass().getResource(ruta10));
        imagen11 = new ImageIcon(getClass().getResource(ruta11));
	    
	    
	    
	    return panelTablero;
	}
	
	/**
	 * Prepares and sets up the players on the game board.
	 */
	public void preparePlayers(){ 
		p1 = new JButton(colorP1);
		p2 = new JButton(colorP2);
		p1.setBackground(Color.decode(colorP1));
		p2.setBackground(Color.decode(colorP2));
		board[width-1][0].add(p1,BorderLayout.NORTH);
		board[width-1][0].add(p2,BorderLayout.SOUTH);
		posiciones[0] = width -1;
		posiciones[1] = 0;
		posiciones[2] = width -1;
		posiciones[3] = 0;

		
	}
	
	/**
	 * Prepares the numbers on the game board for odd-sized boards.
	 *
	 * @param tamaño       The size of the game board.
	 * @param board        The 2D array representing the game board.
	 * @param panelTablero The JPanel representing the game board.
	 */
	private void prepareNumbersOdd(int tamaño, JPanel[][] board, JPanel panelTablero) {
		int numCasilla = 1;
		for (int i = tamaño - 1; i >= 0; i--) {
			if (i % 2 != 0) {
				for (int j = 0; j <= tamaño - 1; j++) {
					board[i][j] = new JPanel();
					board[i][j].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					panelTablero.add(board[i][j]);
					if (numCasilla % 2 != 0) {
						board[i][j].setBackground(Color.gray);
					} else {
						board[i][j].setBackground(Color.white);
					}
					numCasilla++;
				}
			} else {
				for (int k = tamaño - 1; k >= 0; k--) {
					board[i][k] = new JPanel();
					board[i][k].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					panelTablero.add(board[i][k]);
					if (numCasilla % 2 != 0) {
						board[i][k].setBackground(Color.gray);
					} else {
						board[i][k].setBackground(Color.white);
					}
					numCasilla++;
				}
			}
		}
	}
	
	/**
	 * Prepares the numbers on the game board for even-sized boards.
	 *
	 * @param tamaño       The size of the game board.
	 * @param board        The 2D array representing the game board.
	 * @param panelTablero The JPanel representing the game board.
	 */
	private void prepareNumbersPair(int tamaño, JPanel[][] board, JPanel panelTablero) {
		int numCasilla = 1;
		for (int i = tamaño - 1; i >= 0; i--) {
			if (i % 2 == 0) {
				for (int j = 0; j <= tamaño - 1; j++) {
					board[i][j] = new JPanel();
					board[i][j].setName(String.valueOf(numCasilla));
					board[i][j].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					panelTablero.add(board[i][j]);
					if (numCasilla % 2 != 0) {
						board[i][j].setBackground(Color.gray);
					} else {
						board[i][j].setBackground(Color.white);
					}
					numCasilla++;
				}
			} else {
				for (int k = tamaño - 1; k >= 0; k--) {
					board[i][k] = new JPanel();
					board[i][k].setName(String.valueOf(numCasilla));
					board[i][k].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					panelTablero.add(board[i][k]);
					if (numCasilla % 2 != 0) {
						board[i][k].setBackground(Color.gray);
					} else {
						board[i][k].setBackground(Color.white);
					}
					numCasilla++;
				}
			}
		}
	}

	/**
	 * Creates a matrix of JLabels with the given number of rows and columns, initialized with the provided text.
	 *
	 * @param rows       The number of rows in the label matrix.
	 * @param columns    The number of columns in the label matrix.
	 * @param labelText  The text to be displayed in each label.
	 * @return The created label matrix.
	 */
	public JLabel[][] createLabelMatrix(int rows, int columns,String [][] labelText) {
	    JLabel[][] labelMatrix = new JLabel[rows][columns];
	    
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < columns; j++) {
	            labelMatrix[i][j] = new JLabel(labelText[i][j]);
	        }
	    }
	    
	    return labelMatrix;
	}

	
	
	/**
	 * Prepares the panel for displaying board information, including player details, dice, and buttons.
	 *
	 * @return The prepared JPanel containing the board information.
	 */
	public JPanel prepareBoardInfo() {
	    panelDerecha = new JPanel(new GridLayout(4, 1));
	    panelPlayer1 = new JPanel();
	    panelPlayer2 = new JPanel();
	    imagen1 = new ImageIcon(getClass().getResource(ruta1));
        imagen2 = new ImageIcon(getClass().getResource(ruta2));
        imagen3 = new ImageIcon(getClass().getResource(ruta3));
        imagen4 = new ImageIcon(getClass().getResource(ruta4));
        imagen5 = new ImageIcon(getClass().getResource(ruta5));
        imagen6 = new ImageIcon(getClass().getResource(ruta6));
        

      
	    panelPlayer1.setOpaque(true);
	    panelPlayer2.setOpaque(true);
	    panelPlayer1.setBackground(Color.decode(colorP1)); 
	    panelPlayer2.setBackground(Color.decode(colorP2)); 
	    panelPlayer1.setLayout(new GridLayout(6, 2));
	    String[][] labelText = {
	        {"Nombre", namep1},
	        {"Escaleras", Integer.toString(juego.getStairs())},
	        {"Serpientes", Integer.toString(juego.getSnakes())},
	        {"Casilla mas lejana", Integer.toString(juego.casillaMaxima())},
	        {"Modificadores","0"},
	        {"Casillas Especiales", "0"},
	    };
	    labelMatrixPlayer1 = createLabelMatrix(6, 2, labelText);

	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 2; j++) {
	            panelPlayer1.add(labelMatrixPlayer1[i][j]);
	        }
	    }
	    panelPlayer1.repaint();
	    panelDerecha.add(panelPlayer1);

	    panelPlayer2.setLayout(new GridLayout(6, 2));
	    String[][] labelText2 = {
	        {"Nombre", namep2},
	        {"Escaleras", Integer.toString(juego.getStairs())},
	        {"Serpientes", Integer.toString(juego.getSnakes())},
	        {"Casilla mas lejana", Integer.toString(juego.casillaMaxima())},
	        {"Modificadores", "0"},
	        {"Casillas Especiales", "0"},
	    };
	    labelMatrixPlayer2 = createLabelMatrix(6, 2, labelText2);

	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 2; j++) {
	        	
	            panelPlayer2.add(labelMatrixPlayer2[i][j]);
	        }
	    }
	    panelPlayer2.repaint();
	    panelDerecha.add(panelPlayer2);
	    
	    JPanel dado = preparepDado();
	    JPanel botones = prepareBotones();
	    
	    panelDerecha.add(dado);
	    panelDerecha.add(botones);
	    
	   
	    return panelDerecha;
	}
	
	/**
	 * Updates the information of the players displayed on the board.
	 * This method is called to update the player details after a turn change.
	 */
	public void cambiarInfoPlayers() {
		int turn = juego.getTurno();

		if (turn % 2 == 0) {
			labelMatrixPlayer1[0][1].setText(namep1);
			labelMatrixPlayer1[1][1].setText(Integer.toString(juego.getStairs()));
			labelMatrixPlayer1[2][1].setText(Integer.toString(juego.getSnakes()));
			labelMatrixPlayer1[3][1].setText(Integer.toString(juego.casillaMaxima()));
			labelMatrixPlayer1[4][1].setText(Integer.toString(juego.numerModPlayer()));
			labelMatrixPlayer1[5][1].setText(Integer.toString(juego.numerBoxPlayer()));
			panelPlayer2.repaint();
		} else {
			labelMatrixPlayer2[0][1].setText(namep2);
			labelMatrixPlayer2[1][1].setText(Integer.toString(juego.getStairs()));
			labelMatrixPlayer2[2][1].setText(Integer.toString(juego.getSnakes()));
			labelMatrixPlayer2[3][1].setText(Integer.toString(juego.casillaMaxima()));
			labelMatrixPlayer2[4][1].setText(Integer.toString(juego.numerModPlayer()));
			labelMatrixPlayer2[5][1].setText(Integer.toString(juego.numerBoxPlayer()));
			panelPlayer1.repaint();
		}
		
	}
	
	public void prepareMovePve() {
		int[] turn1 = juego.positionFicha();
		int[] turn2 = juego.positionFichasPve();
		System.out.println(turn1[0]);
		System.out.println(turn1[1]);
		System.out.println(turn2[0]);
		System.out.println(turn2[1]);
		prepareMovePlayersPve(turn1[0], turn1[1]);
		prepareMovePlayersMaquina(turn2[0], turn2[1]);
		this.repaint();
	}
	
	public void prepareMovePlayersPve(int i, int j){

		board[posiciones[0]][posiciones[1]].remove(p1);
		board[i][j].add(p1,BorderLayout.NORTH);
		posiciones[0] = i;
		posiciones[1] = j;
		
	}
	
	public void prepareMovePlayersMaquina(int k, int x) {
		board[posiciones[2]][posiciones[3]].remove(p2);
		board[k][x].add(p2,BorderLayout.NORTH);
		posiciones[2] = k;
		posiciones[3] = x;
		
	}

	/**
	 * Prepares the actions for the buttons in the user interface.
	 * This method sets up the action listeners for the "lanzar", "rendirse", and "finalizar" buttons.
	 */
	public void prepareActions() {
		
		WindowAdapter oyenteDeSalidaW;
		oyenteDeSalidaW = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exitWindow();
				}
			};
			this.addWindowListener(oyenteDeSalidaW);
		
		
		lanzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	juego.jugar();
                prepareDado();
                if (pve) {
                	prepareMovePve();
                } else {
                	prepareMove();
                }
                cambiarInfoPlayers();
                win();
                winExit();
            }
        });
		
		rendirse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	rendirse();
            }
        });
		
		finalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fin();
            }
        });
		
		
	}
	
	/**
	 * Prepares the movement of the players in the game.
	 * This method retrieves the current position of the player's game piece using the juego.positionFicha() method,
	 * updates the player's position on the board using the prepareMovePlayers() method, and repaints the UI.
	 */
	public void prepareMove() {
		
		int[] turn1 = juego.positionFicha();
		prepareMovePlayers(turn1[0], turn1[1]);
		this.repaint();
	}
	
	/**
	 * Updates the positions of the players on the game board.
	 * This method receives the row and column indices of the new position (i, j) for the player's game piece.
	 * It removes the player's game piece from the current position on the board and adds it to the new position.
	 * The method also updates the position variables (posiciones) accordingly.
	 * 
	 * @param i The row index of the new position.
	 * @param j The column index of the new position.
	 */
	public void prepareMovePlayers(int i, int j){
		int valor = juego.getTurno();
		if(valor % 2 == 0) {
			board[posiciones[0]][posiciones[1]].remove(p1);
			board[i][j].add(p1,BorderLayout.NORTH);
			posiciones[0] = i;
			posiciones[1] = j;
			
			
		}else {
			board[posiciones[2]][posiciones[3]].remove(p2);
			board[i][j].add(p2,BorderLayout.NORTH);
			posiciones[2] = i;
			posiciones[3] = j;
			
		}
	}
	
	/**
	 * Closes the current game window and returns to the starting screen or main menu.
	 * Creates a new instance of the PantallaDeInicioGUI class and sets it as visible.
	 */
	private void rendirse() {
		this.dispose();
		PantallaDeInicioGUI l = new PantallaDeInicioGUI();
    	l.setVisible(true);
	}
	
	/**
	 * Terminates the application by invoking the system exit with a status of 0.
	 * This method is used to gracefully exit the program.
	 */
	private void fin() {
		
		System.exit(0);
	}
	
	/**
	 * Prepares the dice display based on the value obtained from rolling the dice.
	 * This method scales the dice images and sets the appropriate image based on the dice value.
	 */
	public void prepareDado() {
		Image img1 = imagen1.getImage();
		Image img2 = imagen2.getImage();
		Image img3 = imagen3.getImage();
		Image img4 = imagen4.getImage();
		Image img5 = imagen5.getImage();
		Image img6 = imagen6.getImage();
		
		int nuevoAncho = 170; 
		int nuevoAlto = 170; 

		Image nuevaImagen = img1.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen2 = img2.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen3 = img3.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen4 = img4.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen5 = img5.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen6 = img6.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

		ImageIcon imagenEscalada = new ImageIcon(nuevaImagen);
		ImageIcon imagenEscalada2 = new ImageIcon(nuevaImagen2);
		ImageIcon imagenEscalada3 = new ImageIcon(nuevaImagen3);
		ImageIcon imagenEscalada4 = new ImageIcon(nuevaImagen4);
		ImageIcon imagenEscalada5 = new ImageIcon(nuevaImagen5);
		ImageIcon imagenEscalada6 = new ImageIcon(nuevaImagen6);

		int val = juego.dado();
		

		if (val == 1) {
			etiqueta.setIcon(imagenEscalada);
		}
		else if (val == 2) {
			etiqueta.setIcon(imagenEscalada2);
		}
		else if (val == 3) {
			etiqueta.setIcon(imagenEscalada3);
		}
		else if (val == 4) {
			etiqueta.setIcon(imagenEscalada4);
		}
		else if (val == 5) {
			etiqueta.setIcon(imagenEscalada5);
		}
		else if (val == 6) {
			etiqueta.setIcon(imagenEscalada6);
		}
		
	}
	
	/**
	 * Places a special tile on the specified position of the game board.
	 * This method scales the special tile images and sets the appropriate image based on the tile value.
	 *
	 * @param i The row index of the position.
	 * @param j The column index of the position.
	 */
	public void ponerCasillaEspecial(int i, int j) {
		
		Image img1 = imagen7.getImage();
		Image img2 = imagen8.getImage();
		Image img3 = imagen9.getImage();
		Image img4 = imagen10.getImage();
		Image img5 = imagen11.getImage();
		
		
		int nuevoAncho = 50; 
		int nuevoAlto = 50; 

		Image nuevaImagen = img1.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen2 = img2.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen3 = img3.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen4 = img4.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		Image nuevaImagen5 = img5.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

		ImageIcon imagenEscalada = new ImageIcon(nuevaImagen);
		ImageIcon imagenEscalada2 = new ImageIcon(nuevaImagen2);
		ImageIcon imagenEscalada3 = new ImageIcon(nuevaImagen3);
		ImageIcon imagenEscalada4 = new ImageIcon(nuevaImagen4);
		ImageIcon imagenEscalada5 = new ImageIcon(nuevaImagen5);
		aux = new JLabel(); 
		
		Casilla cas = juego.casillaIJ(i,j);
		if (cas.getValor() == 1) {
			aux.setIcon(imagenEscalada);
		}
		else if (cas.getValor()== 2) {
			aux.setIcon(imagenEscalada2);
		}
		else if (cas.getValor()== 3) {
			aux.setIcon(imagenEscalada3);
		}
		else if (cas.getValor() == 5) {
			aux.setIcon(imagenEscalada4);
		}
		else if (cas.getValor() == 6) {
			aux.setIcon(imagenEscalada5);
		}
		board[i][j].add(aux);
		
}
	/**
	 * Prepares the game board by placing special tiles on their respective positions.
	 * This method retrieves the positions of the special tiles from the game and calls the
	 * ponerCasillaEspecial() method to place the tiles on the board.
	 */
	public void prepareTablero() {
		int k = 0;
		int[][] es = juego.posicionesIj(); 

		for (int i = 0; i <= width ; i++) {
			for(int j = 0; j <= width ; j++) {
				if (es[k][0] == i && es[k][1] == j) {
					ponerCasillaEspecial(i,j);
					k++;
				}
					
			}
		}
		
	}
	
	/**
	 * Overrides the paint method to perform custom painting operations.
	 * This method is responsible for painting the snakes and stairs on the game board.
	 *
	 * @param g The Graphics object to paint on.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		prepareSnakes(g);
		prepareStair(g);
	}
	
	/**
	 * Prepares and paints the snakes on the game board.
	 *
	 * @param g The Graphics object to paint on.
	 */
	public void prepareSnakes(Graphics g) {
		int casiInicial= 0, casiFinal= 0;
		int[] posi;
		Snake[] f = juego.getSnakesI();
		for (Snake a: f) {
			posi = a.positionSnake(); 
			casiInicial = posi[0];
			casiFinal = posi[1];
			paintSnake(casiInicial, casiFinal, g);
		}
	}
	
	/**
	 * Paints a snake on the game board given its initial and final positions.
	 *
	 * @param c The initial position of the snake.
	 * @param b The final position of the snake.
	 * @param g The Graphics object to paint on.
	 */
	public void paintSnake(int c, int b, Graphics g) {
		int xI, xF, yI, yF;
		
		Tablero tb = juego.getTablero();
		Casilla cas = tb.buscarBox(c);
		Casilla cas1 = tb.buscarBox(b);
		int[] posI = cas.getIJ();

		int[] posF = cas1.getIJ();

		JPanel head = board[posI[0]][posI[1]];
		JPanel tail = board[posF[0]][posF[1]];
		
		xI = head.getX() + head.getWidth()/2; 
	
		yI = head.getY() + head.getWidth() ; 
		
		xF = tail.getX() + tail.getWidth()/2;
		
		yF = tail.getY() + tail.getWidth() ; 

		
		//System.out.println("la serpiente tiene casilla inicial en " + c + " y final en " + b);
		g.setColor(Color.GREEN);
		g.drawLine(xI, yI, xF, yF);
		
	}
	
	/**
	 * Prepares and paints the stairs on the game board.
	 *
	 * @param g The Graphics object to paint on.
	 */
	public void prepareStair(Graphics g) {
		int casiInicial= 0, casiFinal= 0;
		int[] posi;
		Stair[] f = juego.getStairI();
		for (Stair a: f) {
			posi = a.positionStair(); 
			casiInicial = posi[0];
			casiFinal = posi[1];
			paintStair(casiInicial, casiFinal, g);
		}
	}

	/**
	 * Paints an individual stair on the game board.
	 *
	 * @param c The index of the start box of the stair.
	 * @param b The index of the end box of the stair.
	 * @param g The Graphics object to paint on.
	 */
	public void paintStair(int c, int b, Graphics g) {
		int xI, xF, yI, yF;
		
		Tablero tb = juego.getTablero();
		Casilla cas = tb.buscarBox(c);
		Casilla cas1 = tb.buscarBox(b);
		int[] posI = cas.getIJ();

		int[] posF = cas1.getIJ();

		JPanel head = board[posI[0]][posI[1]];
		JPanel tail = board[posF[0]][posF[1]];
		
		xI = head.getX() + head.getWidth()/2; 
	
		yI = head.getY() + head.getWidth() ; 

		xF = tail.getX() + tail.getWidth()/2;
	
		yF = tail.getY() + tail.getWidth() ; 
	
	
		g.setColor(Color.ORANGE);
		g.drawLine(xI, yI, xF, yF);
		
	}
	
	/**
	 * Checks if a player has won the game and displays a victory message if so.
	 */
	public void win() {
		if (juego.getWin()) {
	        try {
	            throw new PoobStairsException(PoobStairsException.WIN);
	       } catch (PoobStairsException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Victoria", JOptionPane.ERROR_MESSAGE);
	  
	            return; 
	        }
	        
	    }
	}
	
	/**
	 * Checks if a player has won the game and exits the program if so.
	 */
	public void winExit() {
		if(juego.getWin()) {
			System.exit(0);
		}
	}
	
	/**
	 * Prepares the screen or window for the game.
	 * Sets the title, size, and location of the window based on the screen size.
	 */
	public void prepareScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		setTitle("SnakesAndStairs");
		setSize(width,height);
		setLocationRelativeTo(null);

	}	
}
