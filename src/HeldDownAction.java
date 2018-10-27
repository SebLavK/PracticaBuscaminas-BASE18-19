import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * 
 * @author Sebas Lavigne
 *
 */

public class HeldDownAction extends MouseAdapter{
	
	private VentanaPrincipal ventana;
	
	public HeldDownAction(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Si se lanzo el evento pulsando el boton empezar
		if (e.getSource() instanceof JButton && "botonEmpezar".equals(((JButton) e.getSource()).getName())) {
			ventana.botonEmpezar.setIcon(ventana.icons.getSmileyDownVersion(SweeperIcons.BASE));
		} else {
			ventana.botonEmpezar.setIcon(ventana.icons.getSmiley(SweeperIcons.WORRY));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (! ventana.endGame) {
			ventana.botonEmpezar.setIcon(ventana.icons.getSmiley(SweeperIcons.BASE));
		}
	}

	
}
