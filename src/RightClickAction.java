import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
*@author Sebas Lavigne
*
*/

public class RightClickAction extends MouseAdapter {
	
	private VentanaPrincipal ventana;
	private int i,j;
	
	

	/**
	 * @param ventana
	 * @param i
	 * @param j
	 */
	public RightClickAction(VentanaPrincipal ventana, int i, int j) {
		super();
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		//Si se pulso el boton derecho
		if (e.getButton() == MouseEvent.BUTTON3) {
			ventana.flagButton(i, j);
		}
	}
	
}

