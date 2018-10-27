import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
*@author Sebas Lavigne
*/

public class EndGameDialog extends Thread{
	private VentanaPrincipal ventanaPrincipal;
	private String message, title;
	private int messageType;

	
	
	public EndGameDialog(VentanaPrincipal ventanaPrincipal, String message, String title, int messageType) {
		super();
		this.ventanaPrincipal = ventanaPrincipal;
		this.message = message;
		this.title = title;
		this.messageType = messageType;
	}



	@Override
	public void run() {
		try {
			Thread.sleep(VentanaPrincipal.DIALOG_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int action = JOptionPane.showConfirmDialog(ventanaPrincipal.panelJuego, message, title,
				JOptionPane.YES_NO_OPTION, messageType);
		if (action == JOptionPane.OK_OPTION) {
			ventanaPrincipal.reiniciarJuego();
		} else {
			ventanaPrincipal.ventana.dispatchEvent(new WindowEvent(ventanaPrincipal.ventana, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
}
