package presentation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigSASGUI extends JFrame {
    private int width, snakes, stairs, modificador;
    private Image backgroundImage;
    private static String ruta1 ="/recursos/fondo.jpg";
    private ImageIcon imageIcon;
    private Double casillasEspeciales;
    private JPanel panelPadre;
    private JLabel titulo,eti, wid,sn,st,mod,esp;
    private JTextField widths, serpientes, escaleras, especialBox, modifier;
    private JButton juego;
    private String name1, name2, color1, color2;
    private boolean pve = false;

    public ConfigSASGUI(String name1, String name2, String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.name1 = name1;
        this.name2 = name2;
        prepareScreen();
        prepareElements();
        prepareActions();
    }
    
    public ConfigSASGUI(String name1, String name2, String color1, String color2, boolean pve) {
        this.color1 = color1;
        this.color2 = color2;
        this.name1 = name1;
        this.name2 = name2;
        this.pve = pve;
        prepareScreen();
        prepareElements();
        prepareActions();
    }
    
    /**
     * Prepares the initial screen of the application.
     * Sets the title, size, and location of the main window based on the screen size.
     */
    public void prepareScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setTitle("SnakesAndStairs");
        setSize(width, height);
        setLocationRelativeTo(null);
    }
    
    /**
     * Prepares the elements of the GUI.
     * Sets up the text fields, labels, and buttons.
     */
    public void prepareElements() {
        panelPadre = new JPanel(new GridBagLayout());
        wid = new JLabel("tamaño del tablero");
        st = new JLabel("numero de escaleras");
        sn = new JLabel("numero de serpientes");
        mod = new JLabel("numero de modificadores");
        esp = new JLabel("numero de casillas especiales");
        panelPadre.setBackground(Color.CYAN);
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension size = new Dimension(200, 30);
        gbc.insets = new Insets(5, 5, 5, 5);

        widths = new JTextField();
        widths.setFont(new Font("Courier New", Font.PLAIN, 12));
        widths.setText("Numero de filas");
        widths.setPreferredSize(size);
        widths.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
        
        gbc.gridx = 0;  // Columna 0
		gbc.gridy = 0;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(wid, gbc);

        gbc.gridx = 0;  // Columna 0
		gbc.gridy = 1;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(widths, gbc);

        serpientes = new JTextField();
        serpientes.setFont(new Font("Courier New", Font.PLAIN, 12));
        serpientes.setText("Numero de serpientes");
        serpientes.setPreferredSize(size);
        serpientes.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
        
        gbc.gridx = 0;  // Columna 0
		gbc.gridy = 2;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(sn, gbc);
        
        gbc.gridx = 0;  // Columna 0
		gbc.gridy = 3;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0;  // Peso horizontal
		//gbc.weighty = 0;  // Peso vertical
        panelPadre.add(serpientes, gbc);

        escaleras = new JTextField();
        escaleras.setFont(new Font("Courier New", Font.PLAIN, 12));
        escaleras.setText("Numero de escaleras");
        escaleras.setPreferredSize(size);
        escaleras.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
        gbc.gridx = 1;  // Columna 0
		gbc.gridy = 0;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(st, gbc);
        
        gbc.gridx = 1;  // Columna 0
		gbc.gridy = 1;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0;  // Peso horizontal
		//gbc.weighty = 0;  // Peso vertical
        panelPadre.add(escaleras, gbc);

        especialBox = new JTextField();
        especialBox.setFont(new Font("Courier New", Font.PLAIN, 12));
        especialBox.setText("probabilidad de casillas especiales");
        especialBox.setPreferredSize(size);
        especialBox.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
        gbc.gridx = 1;  // Columna 0
		gbc.gridy = 2;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(esp, gbc);
        
        gbc.gridx = 1;  // Columna 0
		gbc.gridy = 3;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0;  // Peso horizontal
		//gbc.weighty = 0;  // Peso vertical
        panelPadre.add(especialBox, gbc);

        modifier = new JTextField();
        modifier.setFont(new Font("Courier New", Font.PLAIN, 12));
        modifier.setText("Numero de caras");
        modifier.setPreferredSize(size);
        modifier.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                source.setText("");
            }

            public void focusLost(FocusEvent e) {
                // No se realiza ninguna acción cuando se pierde el foco
            }
        });
        gbc.gridx = 2;  // Columna 0
		gbc.gridy = 0;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0.2;  // Peso horizontal
		//gbc.weighty = 0.2;  // Peso vertical
        panelPadre.add(mod, gbc);
        
        gbc.gridx = 2;  // Columna 0
		gbc.gridy = 1;  // Fila 0
		gbc.gridwidth = 1;  // Ancho en celdas: 1
		gbc.gridheight = 1;  // Alto en celdas: 1
		//gbc.weightx = 0;  // Peso horizontal
		//gbc.weighty = 0;  // Peso vertical
        panelPadre.add(modifier, gbc);
        
        
        juego = new JButton("Que comience el juego");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPadre.add(juego, gbc);

        add(panelPadre);
    }
    
    /**
     * Prepares the actions for the elements in the GUI.
     * Sets up the action listener for the "juego" button
     * and the window closing event listener.
     */
    public void prepareActions() {
        juego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        WindowAdapter oyenteDeSalidaW;
		oyenteDeSalidaW = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exitWindow();
				}
			};
			this.addWindowListener(oyenteDeSalidaW);
    }

    /**
     * Sets the value of 'width' variable based on the text in the 'widths' text field.
     * Parses the text as an integer.
     */
    public void setWidth() {
        width = Integer.parseInt(widths.getText());
    }

    /**
     * Sets the value of 'snakes' variable based on the text in the 'serpientes' text field.
     * Parses the text as an integer.
     */
    public void setSnakes() {
        snakes = Integer.parseInt(serpientes.getText());
    }

    /**
     * Sets the value of 'stairs' variable based on the text in the 'escaleras' text field.
     * Parses the text as an integer.
     */
    public void setStairs() {
        stairs = Integer.parseInt(escaleras.getText());
    }

    /**
     * Sets the value of 'casillasEspeciales' variable based on the text in the 'especialBox' text field.
     * Parses the text as a double.
     */
    public void setCasillasEspeciales() {
        casillasEspeciales = Double.parseDouble(especialBox.getText());
    }

    /**
     * Sets the value of 'modificador' variable based on the text in the 'modifier' text field.
     * Parses the text as an integer.
     */
    /**
     * This method sets the value of the 'modificador' variable by parsing the text
     * from the 'modifier' text field as an integer.
     */
    public void setModificador() {
        modificador = Integer.parseInt(modifier.getText());
    }

    /**
     * This method is called when the "Que comience el juego" button is clicked.
     * It extracts the values from the text fields by invoking the setter methods,
     * sets the corresponding variables, disposes the current frame, and creates a new
     * instance of the 'PoobStairsGUI' class. Finally, it sets the visibility of the new
     * frame to true.
     */
    public void startGame() {
        setWidth();
        setSnakes();
        setStairs();
        setCasillasEspeciales();
        setModificador();
        this.dispose();
        if (pve) {
            PoobStairsGUI p = new PoobStairsGUI(name1, name2, color1, color2, width, snakes, stairs, casillasEspeciales, modificador, pve);
            p.setVisible(true);
        } else {
            PoobStairsGUI p = new PoobStairsGUI(name1, name2, color1, color2, width, snakes, stairs, casillasEspeciales, modificador);
            p.setVisible(true);
        }
    }

    /**
     * This method is called when the window is closing. It displays a confirmation dialog
     * asking the user if they want to exit. If the user selects "Yes," the window is closed
     * with 'setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)'. If the user selects "No," the
     * window is not closed and 'setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE)' is set.
     */
    public void exitWindow() {
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir", "¿Salir?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (result == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    
    
    public void BackgroundPanel() {
        ImageIcon imageIcon = new ImageIcon(ruta1);
        backgroundImage = imageIcon.getImage();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(backgroundImage, 0, 0, this);
        //BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
       // setContentPane(backgroundPanel);
    }
    
    
}

    
   

