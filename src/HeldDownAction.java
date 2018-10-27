import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		ventana.botonEmpezar.setIcon(ventana.icons.getSmiley(SweeperIcons.WORRY));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ventana.botonEmpezar.setIcon(ventana.icons.getSmiley(SweeperIcons.BASE));
	}

	
}
