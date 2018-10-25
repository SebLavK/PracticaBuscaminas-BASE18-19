import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VentanaPrincipal {

	//La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	
	//Todos los botones se meten en un panel independiente.
	//Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel [][] panelesJuego;
	JButton [][] botonesJuego;
	
	//Correspondencia de colores para las minas:
	Color correspondenciaColores [] = {Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};
	
	JButton botonEmpezar;
	JTextField pantallaPuntuacion;
	
	
	//LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;
	
	
	//Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}
	
	//Inicializa todos los componentes del frame
	public void inicializarComponentes(){
		
		//Definimos el layout:
		ventana.setLayout(new GridBagLayout());
		
		//Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1,1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1,1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10,10));
		
		
		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));
		
			
		//Colocamos los componentes:
		//AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		//VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		//AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		//ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);
		
		//Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1,1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		
		//Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}
		
		//BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
		
	}
	
	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el programa
	 */
	public void inicializarListeners(){
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[0].length; j++) {
				botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
			}
		}
		
		botonEmpezar.addActionListener((e) -> reiniciarJuego()); 
	}
	
	
	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda
	 * Saca el botón que haya en la celda determinada y añade un JLabel centrado y no editable con el número de minas alrededor.
	 * Se pinta el color del texto según la siguiente correspondecia (consultar la variable correspondeciaColor):
	 * - 0 : negro
	 * - 1 : cyan
	 * - 2 : verde
	 * - 3 : naranja
	 * - 4 ó más : rojo 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i , int j) {
		JPanel localPanel = panelesJuego[i][j];
		int value = juego.getMinasAlrededor(i, j);
		JLabel localLabel = new JLabel(Integer.toString(value));
		if (value < 0) {
			value = 0;
		}
			localLabel.setForeground(correspondenciaColores[value]);
			localLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//TODO comentar
		localPanel.remove(botonesJuego[i][j]);
		localPanel.add(localLabel);
		
		refrescarPantalla();
	}
	
	
	/**
	 * Muestra una ventana que indica el fin del juego
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha explotado una mina (true) o bien porque hemos desactivado todas (false) 
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		int action;
		
		//Desactiva todos los botones
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[0].length; j++) {
				botonesJuego[i][j].removeActionListener(botonesJuego[i][j].getActionListeners()[0]);
			}
		}
		
		
		//Muestra el mensaje
		String message, title;
		int messageType;
		if (porExplosion) {
			message = "¡Has pisado una mina!";
			title = "¡Has perdido!";
			messageType = JOptionPane.ERROR_MESSAGE;
		} else {
			message = "¡Has evitado todas las minas!";
			title = "¡Has ganado!";
			messageType = JOptionPane.INFORMATION_MESSAGE;
		}
		message += "\nFin del juego.\nPuntuación: "+juego.getPuntuacion();
		message += "\n\n¿Volver a jugar?";
		action = JOptionPane.showConfirmDialog(this.panelJuego, message, title,
				JOptionPane.YES_NO_OPTION, messageType);
		
		if (action == JOptionPane.OK_OPTION) {
			reiniciarJuego();
		} else {
			ventana.dispatchEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSING));
		}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(juego.getPuntuacion()));
	}
	
	/**
	 * Abre todas las casillas que están alrededor de un boton
	 * @param i
	 * @param j
	 * @pre: se ha pulsado un boton con numero 0
	 * @post: todos los botones colindantes se abren
	 */
	public void abrirAlrededores(int i, int j) {
		ActionEvent event = new ActionEvent(botonesJuego[i][j], ActionEvent.ACTION_PERFORMED, "");
		//Recorre las casillas circundantes
		for (int iLocal = i-1; iLocal < i+2; iLocal++) {
			for (int jLocal = j-1; jLocal < j+2; jLocal++) {
				try {
					if (iLocal != i && jLocal != j) {//Si no es el centro
						/*Peligroso hacerlo con este metodo, si se abre el de al lado, este abre el que lo abrio
						//Obtiene el listener del boton y le lanza un evento
						botonesJuego[iLocal][jLocal].getActionListeners()[0].actionPerformed(event);
						*/
						juego.abrirCasilla(iLocal, jLocal);
						mostrarNumMinasAlrededor(iLocal, jLocal);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}
			}
		}
		actualizarPuntuacion();
	}
	
	/**
	 * Reinicia la partida, bien porque se termino el juego y se quiere volver a jugar
	 * o porque el jugador pulsó el boton Empezar
	 */
	public void reiniciarJuego() {
		ventana.setContentPane(new JPanel());
		juego.inicializarPartida();
		inicializar();
		refrescarPantalla();
	}
	
	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla(){
		ventana.revalidate(); 
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar(){
		//IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners();
		//TODO
		juego.depurarTablero();
	}



}
