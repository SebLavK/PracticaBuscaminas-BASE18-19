import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
*@author Sebas Lavigne
*/

public class VentanaOpciones {
	
	public static final String[] TEXTO_OPCIONES = {"Fácil","Medio","Difícil","Custom"};
	
	private ControlJuego juego;
	
	private JPanel panelOpciones, panelDifficulty, panelSize;
	private JRadioButton[] rbDifficulty, rbSize;
	private ButtonGroup bgDifficulty, bgSize;
	private JTextField tfDifficulty, tfSize;
	
	public VentanaOpciones(VentanaPrincipal ventanaPrincipal, ControlJuego juego) {
		this.juego = juego;
		
		initializeComponents();
		getInitialValues();
		initializeListeners();
		
		JOptionPane.showMessageDialog(ventanaPrincipal.ventana, panelOpciones);
	}
	
	/**
	 * Inicializa los el panel que se le da al MessageDialog
	 */
	public void initializeComponents() {
		panelOpciones = new JPanel();
		panelDifficulty = new JPanel();
		panelSize = new JPanel();
		rbDifficulty = new JRadioButton[TEXTO_OPCIONES.length];
		rbSize = new JRadioButton[TEXTO_OPCIONES.length];
		
		//Paneles
		panelOpciones.setLayout(new GridLayout(1,2));
		panelDifficulty.setLayout(new GridLayout(5, 1));
		panelSize.setLayout(new GridLayout(5, 1));
		
		panelOpciones.add(panelDifficulty);
		panelOpciones.add(panelSize);
		
		panelDifficulty.setBorder(BorderFactory.createTitledBorder("Dificultad"));
		panelSize.setBorder(BorderFactory.createTitledBorder("Tamaño"));
		
		//RadioButtons
		bgDifficulty = new ButtonGroup();
		bgSize = new ButtonGroup();
		for (int i = 0; i < TEXTO_OPCIONES.length; i++) {
			rbDifficulty[i] = new JRadioButton(TEXTO_OPCIONES[i]);
			rbSize[i] = new JRadioButton(TEXTO_OPCIONES[i]);
			
			rbDifficulty[i].setActionCommand("diff"+i);
			rbSize[i].setActionCommand("size"+i);
			
			panelDifficulty.add(rbDifficulty[i]);
			panelSize.add(rbSize[i]);
			
			bgDifficulty.add(rbDifficulty[i]);
			bgSize.add(rbSize[i]);
		}
		
		//TextFields
		tfDifficulty = new JTextField();
		tfDifficulty.setEditable(false);
		tfSize= new JTextField();
		tfSize.setEditable(false);
		panelDifficulty.add(tfDifficulty);
		panelSize.add(tfSize);
		
	}
	
	/**
	 * Inicializa los listeners
	 */
	public void initializeListeners() {
		ActionListener radioButtonPressed = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (((JRadioButton) e.getSource()).getActionCommand()) {
				case "diff0":
					tfDifficulty.setEditable(false);
					juego.setDifficulty(ControlJuego.EASY);
					break;
				case "diff1":
					tfDifficulty.setEditable(false);
					juego.setDifficulty(ControlJuego.NORMAL);
					break;
				case "diff2":
					tfDifficulty.setEditable(false);
					juego.setDifficulty(ControlJuego.HARD);
					break;
				case "diff3":
					tfDifficulty.setEditable(true);
					break;

				case "size0":
					tfSize.setEditable(false);
					juego.setSize(ControlJuego.SMALL);
					break;
				case "size1":
					tfSize.setEditable(false);
					juego.setSize(ControlJuego.MEDIUM);
					break;
				case "size2":
					tfSize.setEditable(false);
					juego.setSize(ControlJuego.BIG);
					break;
				case "size3":
					tfSize.setEditable(true);
					break;

				}

			}
		};

		for (int i = 0; i < TEXTO_OPCIONES.length; i++) {
			rbDifficulty[i].addActionListener(radioButtonPressed);
			rbSize[i].addActionListener(radioButtonPressed);
		}
		
		tfDifficulty.getDocument().addDocumentListener(new DocumentListener() {
			
			/**
			 * La dificultad es la relacion entre casillas libres / minas
			 * Cuanto mas bajo sea su valor, mas dificil es el juego
			 * Si el jugador pone un valor no valido, por defecto se pone facil
			 */
			private void update() {
				int value;
				try {
					value = Integer.parseInt(tfDifficulty.getText());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					value = -1;
				}
				
				if (value > 0) {
					juego.setDifficulty(value);
				} else {
					juego.setDifficulty(ControlJuego.EASY);
				}
				System.out.println("New diff: "+value);
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("removeUpdate");
				update();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
				update();
			}
		});
		
		tfSize.getDocument().addDocumentListener(new DocumentListener() {
			
			private void update() {
				int value;
				try {
					value = Integer.parseInt(tfSize.getText());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					value = -1;
				}
				
				if (value > 3) {
					juego.setSize(value);
				} else {
					juego.setDifficulty(ControlJuego.SMALL);
				}
				System.out.println("New size: "+value);
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("removeUpdate");
				update();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
				update();
			}
		});
	}
	
	/**
	 *La primera vez que se inicia el programa, el juego arranca con valores por defecto
	 *(facil, pequeño)
	 *Cada vez que se reinicie la partida, este panel "recuerda" los valores anteriores
	 *y permite al jugador reiniciar rapidamente sin tener que cambiar los valores 
	 */
	public void getInitialValues() {
		switch(juego.getDifficulty()) {
		case ControlJuego.EASY:
			rbDifficulty[0].setSelected(true);
			break;
		case ControlJuego.NORMAL:
			rbDifficulty[1].setSelected(true);
			break;
		case ControlJuego.HARD:
			rbDifficulty[2].setSelected(true);
			break;
		default:
			rbDifficulty[3].setSelected(true);
			tfDifficulty.setText(Integer.toString(juego.getDifficulty()));
			tfDifficulty.setEditable(true);
			break;
		}
		System.out.println(juego.getSize());
		switch(juego.getSize()) {
		case ControlJuego.SMALL:
			rbSize[0].setSelected(true);
			break;
		case ControlJuego.MEDIUM:
			rbSize[1].setSelected(true);
			break;
		case ControlJuego.BIG:
			rbSize[2].setSelected(true);
			break;
		default:
			rbSize[3].setSelected(true);
			tfSize.setText(Integer.toString(juego.getSize()));
			tfSize.setEditable(true);
			break;
		}
	}

}
