package presentation;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaDeInicioGUI extends JFrame {
    public static final String TITLE_MESSAGE = "Stairs And Snakes";
    public static final Font TITLE_FONT = new Font("Playball", 0, 62);
    private JPanel panelpres;
    private JLabel pres;
    private JButton iniciar;
    private JButton continuar;
    private JButton salir;
    private JPanel panelCentro;
    private static String ruta = "C:\\Users\\juan.fernandez-g\\Documents\\Proyecto Final\\Proyecto Final\\dinopartedeabajo.jpeg";
    private static String ruta2 = "C:\\Users\\juan.fernandez-g\\Documents\\Proyecto Final\\Proyecto Final\\dinopartedearriba.jpeg";

    public PantallaDeInicioGUI() {
        prepareElements();
        prepareActions();
        prepareScreen();
    }

    
    /**
     * Prepares the elements on the game board.
     * Sets up the presentation panel and the center panel with buttons.
     * Configures the layout and adds the panels to the game board using GridBagConstraints.
     */
    public void prepareElementsBoard() {
        JPanel panelpresent = preparePres();
        JPanel panelCentro = panelBotones();
        setLayout(new GridBagLayout());
        setBackground(Color.CYAN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        add(panelpresent, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(panelCentro, gbc);

    }
    
    /**
     * Creates and configures the presentation panel on the game board.
     * Sets the layout to GridBagLayout and adds a centered JLabel with the title.
     * 
     * @return The configured presentation panel.
     */
    public JPanel preparePres() {

        panelpres = new JPanel();
           
        panelpres.setLayout(new GridBagLayout());

        pres = new JLabel("POOBSTAIRS");
        pres.setHorizontalAlignment(JLabel.CENTER);
        pres.setVerticalAlignment(JLabel.CENTER);
        pres.setFont(new Font("Monospaced", Font.BOLD, 64));

        panelpres.add(pres);
        return panelpres;
    }



    /**
     * Creates and configures the center panel with buttons on the game board.
     * Sets the background color to CYAN and the layout to GridBagLayout.
     * Adds three buttons (Nuevo juego, Continuar, Salir) to the panel with specific font, background, and foreground colors.
     * 
     * @return The configured center panel with buttons.
     */
    public JPanel panelBotones() {
        panelCentro = new JPanel();
           
        panelCentro.setBackground(Color.CYAN);
        panelCentro.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(1, 0, 1, 0); // Espacio vertical entre los componentes

        iniciar = new JButton("Nuevo juego");
        iniciar.setFont(new Font("Courier New", Font.PLAIN, 12));
        iniciar.setBackground(new Color(150, 200, 100));
        iniciar.setForeground(Color.WHITE);
        continuar = new JButton("Continuar");
        continuar.setFont(new Font("Courier New", Font.PLAIN, 12));
        continuar.setBackground(new Color(150, 200, 100));
        continuar.setForeground(Color.WHITE);
        salir = new JButton("Salir");
        salir.setFont(new Font("Courier New", Font.PLAIN, 12));
        salir.setBackground(new Color(150, 200, 100));
        salir.setForeground(Color.WHITE);

        panelCentro.add(iniciar, constraints);

        constraints.gridy = 1;
        panelCentro.add(continuar, constraints);

        constraints.gridy = 2;
        panelCentro.add(salir, constraints);

        return panelCentro;
    }
    
    /**
     * Sets up the actions (event listeners) for the buttons in the game board.
     * Creates and configures action listeners for the "Salir" button, window closing event, and "Nuevo juego" button.
     * Adds the action listeners to the corresponding buttons.
     * 
     */
    public void prepareActions() {
        ActionListener oyenteDeSalida;
        oyenteDeSalida = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        };
        salir.addActionListener(oyenteDeSalida);
        WindowAdapter oyenteDeSalidaW;
        oyenteDeSalidaW = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitWindow();
            }
        };
        this.addWindowListener(oyenteDeSalidaW);
        ActionListener oyenteDeInicio;
        oyenteDeInicio = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pantallaDeJugadores();
            }
        };
        iniciar.addActionListener(oyenteDeInicio);
    }
    
    /**
     * Handles the action when the user wants to exit the application.
     * Displays a confirmation dialog asking if the user is sure they want to exit.
     * If the user chooses to exit, the application is closed by hiding and disposing the game board window.
     * If the user chooses not to exit, nothing happens.
     */
    public void exitApp() {
        int option = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere salir?", "confirmacion", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.out.println("adios");
            setVisible(false);
            dispose();
        } else if (option == JOptionPane.NO_OPTION) {

        }
    }
    
    
    /**
     * Transitions from the current game board window to the player configuration screen.
     * Disposes of the current window and opens a new ConfiguracionGUI window for player configuration.
     */
    public void pantallaDeJugadores() {
        this.dispose();
        ConfiguracionGUI c = new ConfiguracionGUI();
        c.setVisible(true);
    }
    
    /**
     * Adjusts the size and position of the panelCentro component based on the screen size.
     * Centers the panel on the screen.
     */
    public void prepareScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Se ajusta el tamaño y posición del panelCentro
        int panelX = screenWidth / 2 - 150;
        int panelY = screenHeight / 2 - 50;
        panelCentro.setBounds(panelX, panelY, 300, 100);
    }
    
    /**
     * Prepares the elements of the application's main frame.
     */
    public void prepareElements() {
        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setTitle("SnakesAndStairs");
        setSize(width, height);
        setLocationRelativeTo(null);
        prepareElementsBoard();

    }
    
    /**
     * The main entry point of the application.
     * Creates and displays the main frame.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	PantallaDeInicioGUI s = new PantallaDeInicioGUI();
        s.setVisible(true);

        // Cargar y establecer la imagen de fondo
           
        
    }

    /**
     * Handles the window closing event.
     * Displays a confirmation dialog and sets the default close operation accordingly.
     */
    public void exitWindow() {
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir", "¿Salir?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (result == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
}

