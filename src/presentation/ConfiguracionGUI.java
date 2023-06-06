package presentation;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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


public class ConfiguracionGUI extends JFrame{

	private JPanel panelPadre;
	private JLabel titulo;
	
	private JButton pvp;
	private JButton pve;
	private JButton volver;
	

	public ConfiguracionGUI(){
		prepareElements();
		prepareScreen();
		prepareActions();
	}
	
	/**
	 * Sets up the graphical elements for the game mode selection screen.
	 * Adds buttons and labels to the panel.
	 */
	public void prepareElements(){
	    panelPadre = new JPanel(new BorderLayout());
	    pvp = new JButton("PVP");
	    pve = new JButton("PVE");
	    volver = new JButton("volver");
	    JPanel panelCentro = new JPanel(new GridBagLayout());
	    panelCentro.setBackground(Color.cyan);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    panelCentro.add(pvp, gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    panelCentro.add(pve, gbc);
	    panelPadre.add(panelCentro, BorderLayout.CENTER);
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    panelCentro.add(volver, gbc);
	    panelPadre.add(panelCentro, BorderLayout.CENTER);
	    add(panelPadre);
	    
	    
	    titulo = new JLabel("SELECCIONA EL MODO DE JUEGO");
	    titulo.setHorizontalAlignment(JLabel.CENTER);
	    titulo.setVerticalAlignment(JLabel.CENTER);
	    titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
	    panelPadre.add(titulo, BorderLayout.NORTH);

	    
	    
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = (int) screenSize.getWidth();
	    int screenHeight = (int) screenSize.getHeight();

	    int panelX = screenWidth / 2 - 150;
	    int panelY = screenHeight / 2 - 50;
	    panelCentro.setBounds(panelX, panelY, 300, 100);
	}

	/**
	 * Sets up the size and position of the game window based on the screen size.
	 * Centers the window on the screen.
	 */
	public void prepareScreen(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width/2;
		int height = screenSize.height/2;
		setTitle("SnakesAndStairs");
		setSize(width,height);
		setLocationRelativeTo(null);
	}
	
	
	/**
	 * Sets up the event listeners for the buttons and window closing action.
	 * - Sets a window listener to handle the window closing event.
	 * - Sets an action listener for the "PVP" button to navigate to the color selection screen.
	 * - Sets an action listener for the "PVE" button to navigate to the color selection screen for PVE mode.
	 * - Sets an action listener for the "Volver" button to navigate back to the previous screen.
	 */
	public void prepareActions(){
		WindowAdapter oyenteDeSalidaW;
		oyenteDeSalidaW = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exitWindow();
				}
			};
			this.addWindowListener(oyenteDeSalidaW);
		ActionListener oyenteDeSalida;
		oyenteDeSalida =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pantallaDeColores();
				}
			};
			pvp.addActionListener(oyenteDeSalida);
			
			this.addWindowListener(oyenteDeSalidaW);
			
		ActionListener oyenteDepve;
		oyenteDepve =new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pantallaDeColoresPve();
				}
			};
			pve.addActionListener(oyenteDepve);

			ActionListener oyenteDeVolver;
			oyenteDeVolver =new ActionListener(){
					public void actionPerformed(ActionEvent e){
						volver();
					}
				};
				volver.addActionListener(oyenteDeVolver);
						
		}
	
	/**
	 * Navigates back to the previous screen by disposing the current screen and
	 * creating a new instance of the "PantallaDeInicioGUI" class to display the
	 * initial screen.
	 */
	public void volver(){
		this.dispose();
		PantallaDeInicioGUI c = new PantallaDeInicioGUI();
		c.setVisible(true);
			
			
		}
	
	/**
	 * Navigates to the "ColorScreenGUI" screen by disposing the current screen and
	 * creating a new instance of the "ColorScreenGUI" class to display the color selection screen.
	 */
	public void pantallaDeColores(){
		this.dispose();
		ColorScreenGUI d = new ColorScreenGUI();
		d.setVisible(true);
	}
	
	
	/**
	 * Navigates to the "ColorScreenPveGUI" screen by disposing the current screen and
	 * creating a new instance of the "ColorScreenPveGUI" class to display the color selection screen.
	 */
	public void pantallaDeColoresPve() {
	this.dispose();
		ColorScreenPveGUI d = new ColorScreenPveGUI();
		d.setVisible(true);
	}
	
	/**
	 * Handles the window closing event and prompts the user with a confirmation dialog.
	 * If the user chooses to exit, the application is closed.
	 * If the user chooses not to exit, the window closing operation is canceled.
	 */
	public void exitWindow(){
		int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir","¿Salir?",JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_NO_OPTION){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else if (result == JOptionPane.NO_OPTION){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
}