import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{

	private VentanaPrincipal ventana;
	private int i,j;

	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 *Si es el primer click y si se pulso en una mina, se recoloca la mina
	 *Tambien guarda la hora de inicio de juego y lanza el contador de tiempo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventana.firstClick) {
			ventana.firstClick = false;
			if (ventana.getJuego().getMinasAlrededor(i, j) == ControlJuego.MINA) {
				ventana.getJuego().relocateMine(i, j);
			}
			ventana.getJuego().setStartTime(LocalTime.now());
			ventana.timeUpdater.start();
		}
		if (ventana.botonesJuego[i][j].getActionCommand().equals("-")) {
			ventana.destaparBoton(i, j);
		}
	}

}
