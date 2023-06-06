package presentation;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import domain.PoobStairsException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JOptionPane;

public class ColorScreenPveGUI extends JFrame{
	
	private JPanel panelPadre;
	private JLabel titulo;
	private JButton colorP1;
	private JLabel colorP2;
	private JButton volver;
	private JButton siguiente;
	private JTextField nombre1;
	private JLabel nombre2;
	private String name1, name2,ultimoColor1,ultimoColor2;
	private String color1, color2;
	private String[] finalElementos = new String[2];
	private int i = 0;
	private boolean pve = true;

	public ColorScreenPveGUI(){
		prepareElements();
		prepareScreen();
		prepareActions();
	}
	
	/**
	 * This method prepares the elements on the screen, including buttons, labels, and text fields.
	 * It also sets their properties such as text, background color, and event listeners.
	 */
	public void prepareElements(){
		panelPadre = new JPanel(new BorderLayout());
		colorP1 = new JButton("Seleccion el color del jugador 1");
		colorP2 = new JLabel("El color de Martin se selecciona aleatoriamente");
		siguiente = new JButton("Suiguiente");
		volver = new JButton("volver");
		nombre1 = new JTextField();
		nombre1.setText("Nombre del jugador 1");
		nombre1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
		nombre2 = new JLabel();
		nombre2.setText("Martin");
		
		panelPadre.setBackground(Color.cyan);
		JPanel panelCentro = new JPanel(new GridBagLayout());
	    panelCentro.setBackground(Color.cyan);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    panelCentro.add(colorP1, gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    panelCentro.add(colorP2, gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    panelCentro.add(volver, gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    panelCentro.add(nombre1, gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    panelCentro.add(nombre2, gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    panelCentro.add(siguiente, gbc);
	    panelPadre.add(panelCentro, BorderLayout.CENTER);
	    add(panelPadre);

	    titulo = new JLabel("Selecciona el color del jugador");
	    titulo.setHorizontalAlignment(JLabel.CENTER);
	    titulo.setVerticalAlignment(JLabel.CENTER);
	    titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
	    panelPadre.add(titulo, BorderLayout.NORTH);

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = (int) screenSize.getWidth();
	    int screenHeight = (int) screenSize.getHeight();

	    int panelX = screenWidth / 2 - 150;
	    int panelY = screenHeight / 2 - 50;
	    panelCentro.setBounds(panelX, panelY, 300, 100);
	}
	
	/**
	 * This method prepares the actions for various components, such as buttons and window closing event.
	 * It assigns appropriate event listeners to handle the actions.
	 */
	public void prepareActions(){
		
		WindowAdapter oyenteDeSalidaW;
		oyenteDeSalidaW = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exitWindow();
				}
			};
			this.addWindowListener(oyenteDeSalidaW);
		ActionListener oyenteDeColor1;
		oyenteDeColor1 =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					seleccionDeColor();
					seleccionDeColorPve();
				}
			};
			colorP1.addActionListener(oyenteDeColor1);
			
		ActionListener oyenteDeVolver;
		oyenteDeVolver =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					volver();
				}
			};
			volver.addActionListener(oyenteDeVolver);

		ActionListener oyenteDeSiguiente;
		oyenteDeSiguiente =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					siguiente();
				}
			};
			siguiente.addActionListener(oyenteDeSiguiente);
					
	}
	
	/**
	 * This method displays a dialog box to allow the user to select a color.
	 * It uses the provided list of colors and their corresponding hexadecimal values.
	 * The selected color is stored in the variable 'ultimoColor1'.
	 */
	public void seleccionDeColor() {
	    String[] elementos = {"blue", "black", "green", "lila", "55", "pink", "yellow", "orange"};
	    String[] elementosHex = {"#92c5FC", "#000000", "#70FF8B", "#E69DFB", "#14C89D", "#FF7987", "#FFC700", "#fc4b08"};
	    
	    String elementoSeleccionado = (String) JOptionPane.showInputDialog(
	            this,
	            "Selecciona un elemento",
	            "Elementos",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            elementos,
	            elementos[0]);

	    boolean colorSeleccionado = false;

	    for (int j = 0; j < elementos.length && !colorSeleccionado; j++) {
	        if (elementoSeleccionado.equals(elementos[j])) {
	            if (ultimoColor1 == null) {
	                ultimoColor1 = elementosHex[j];
	            }
	            colorSeleccionado = true;
	        }
	    }
	}
	
	/**
	 * This method selects a color for the computer player (PvE).
	 * It randomly selects a color from the provided list of colors and assigns it to 'ultimoColor2'.
	 */
	public void seleccionDeColorPve() {
	    String[] elementos = {"blue", "black", "green", "lila", "55", "pink", "yellow", "orange"};
	    String[] elementosHex = {"#92c5FC", "#000000", "#70FF8B", "#E69DFB", "#14C89D", "#FF7987", "#FFC700", "#fc4b08"};
	    Random rand = new Random();
	    int valor = rand.nextInt(1, 8);
	    ultimoColor2 = elementosHex[valor];
	}

	/**
	 * This method handles the action of going back to the previous screen.
	 * It disposes of the current window and opens the ConfiguracionGUI window.
	 */
	public void volver(){
	    this.dispose();
	    ConfiguracionGUI c = new ConfiguracionGUI();
	    c.setVisible(true);
	}

	
	/**
	 * This method handles the action of moving to the next screen.
	 * It retrieves the names and colors selected by the players.
	 * If the names are the same or the colors are the same, it throws a PoobStairsException with an appropriate message.
	 * Otherwise, it disposes of the current window and opens the ConfigSASGUI window with the provided information.
	 */
	public void siguiente(){
	    name1 = nombre1.getText();
	    name2 = nombre2.getText();
	    
	    color1 = ultimoColor1;
	    color2 = ultimoColor2;
	    if (name1.equals(name2)) {
	        try {
	            throw new PoobStairsException(PoobStairsException.SAME_NAME_PLAYER);
	        } catch (PoobStairsException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
	    }
	    if (color1.equals(color2)) {
	        try {
	            throw new PoobStairsException(PoobStairsException.SAME_COLOR_PLAYER);
	        } catch (PoobStairsException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
	    }
	    
	    this.dispose();
	    ConfigSASGUI d = new ConfigSASGUI(name1, name2, color1, color2, pve);
	    d.setVisible(true);
	}

	/**
	 * This method handles the action of closing the window.
	 * It displays a confirmation dialog to confirm if the user wants to exit.
	 * If the user chooses to exit, the application is closed.
	 * If the user chooses not to exit, the window is not closed.
	 */
	public void exitWindow(){
	    int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir","¿Salir?",JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION){
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    } else if (result == JOptionPane.NO_OPTION){
	        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    }
	}

	/**
	 * This method prepares the screen by setting its title, size, and location.
	 * It also calls the prepareElements() method to initialize the GUI elements.
	 */
	public void prepareScreen() {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = screenSize.width/2;
	    int height = screenSize.height/2;
	    setTitle("SnakesAndStairs");
	    setSize(width,height);
	    setLocationRelativeTo(null);
	    prepareElements();
	}
  
	   
}
	
	
	
	