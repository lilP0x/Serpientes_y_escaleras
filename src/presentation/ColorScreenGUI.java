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
import javax.swing.JOptionPane;

public class ColorScreenGUI extends JFrame{
	
	private JPanel panelPadre;
	private JLabel titulo;
	private JButton colorP1;
	private JButton colorP2;
	private JButton volver;
	private JButton siguiente;
	private JTextField nombre1;
	private JTextField nombre2;
	private String name1, name2,ultimoColor1,ultimoColor2;
	private String color1, color2;
	private String[] finalElementos = new String[2];
	private int i = 0;

	public ColorScreenGUI(){
		prepareElements();
		prepareScreen();
		prepareActions();
	}
	
	
	/**
	 * This method prepares the elements for the GUI screen. It creates and configures
	 * buttons, text fields, and labels, and adds them to the parent panel using the
	 * BorderLayout manager. It also sets the background color and layout for the parent
	 * and center panels. Additionally, it sets the focus listeners for the name text fields.
	 * Finally, it sets the dimensions and position of the center panel based on the screen size.
	 */
	public void prepareElements(){
		panelPadre = new JPanel(new BorderLayout());
		colorP1 = new JButton("Seleccion el color del jugador 1");
		colorP2 = new JButton("Seleccione el color del jugador 2");
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
		nombre2 = new JTextField();
		nombre2.setText("Nombre del jugador 2");
		nombre2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
		
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
	 * This method prepares the actions for the GUI elements. It adds window listener
	 * to handle the window closing event. It also adds action listeners to the color
	 * buttons, the "volver" button, and the "siguiente" button to handle their respective
	 * action events.
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
				}
			};
			colorP1.addActionListener(oyenteDeColor1);
		ActionListener oyenteDeColor2;
		oyenteDeColor2 =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					seleccionDeColor();
				}
			};
			colorP2.addActionListener(oyenteDeColor2);
			
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
	 * This method allows the user to select a color from a predefined list of colors.
	 * It displays a dialog box with a list of colors and captures the user's selection.
	 * The selected color is then assigned to either the first player's color or the second
	 * player's color based on the previous selection.
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
	            } else {
	                ultimoColor2 = elementosHex[j];
	            }
	            colorSeleccionado = true;
	        }
	    }
	}
	
	/**
	 * This method is called when the user clicks the "volver" button.
	 * It disposes of the current GUI window and creates a new instance of the
	 * ConfiguracionGUI class, displaying it to the user.
	 */
	public void volver(){
		this.dispose();
		ConfiguracionGUI c = new ConfiguracionGUI();
		c.setVisible(true);
		
		
	}
	
	/**
	 * This method is called when the user clicks the "siguiente" button.
	 * It retrieves the names and colors entered by the user, performs validation checks,
	 * and disposes of the current GUI window. Then it creates a new instance of the
	 * ConfigSASGUI class with the provided names and colors, and displays it to the user.
	 * If any validation errors occur, an appropriate error message is displayed.
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
		
		//System.out.println(finalElementos[0]);
		//System.out.println(finalElementos[1]);
		this.dispose();
		ConfigSASGUI d = new ConfigSASGUI(name1, name2, color1, color2);
		d.setVisible(true);
		
		
	}
	
	/**
	 * This method is called when the user tries to close the window.
	 * It displays a confirmation dialog asking if the user wants to exit.
	 * If the user selects "Yes," the window is closed and the application terminates.
	 * If the user selects "No," the window remains open.
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
	 * This method prepares the screen by setting the title, size, and location of the JFrame.
	 * It also calls the method to prepare the elements on the screen.
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
	
	
	
	