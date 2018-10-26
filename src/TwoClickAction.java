import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
*@author Sebas Lavigne
*
*/

public class TwoClickAction extends MouseAdapter {

	private VentanaPrincipal ventana;
	private int i,j;
	
	
	
	/**
	 * @param ventana
	 * @param i
	 * @param j
	 */
	public TwoClickAction(VentanaPrincipal ventana, int i, int j) {
		super();
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		//Si ambos botones estan pulsados
		if (SwingUtilities.isLeftMouseButton(e) && SwingUtilities.isRightMouseButton(e)) {
			ventana.abrirAlrededores(i, j, false);;
		}
	}
	
	
}

