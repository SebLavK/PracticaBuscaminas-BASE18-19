import java.time.Duration;
import java.time.LocalTime;

/**
*@author Sebas Lavigne
*/

public class TimeUpdater extends Thread {
	
	//TODO usar java.swing.Timer

	private VentanaPrincipal ventanaPrincipal;
	private boolean ticking;
	
	public TimeUpdater(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		ticking = true;
	}

	@Override
	public void run() {
		long timeSince;
		LocalTime startTime = ventanaPrincipal.getJuego().getStartTime();
		while(ticking) {
			timeSince = Duration.between(startTime, LocalTime.now()).getSeconds();
			ventanaPrincipal.playtime.setText(Long.toString(timeSince));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopTimer() {
		ticking = false;
	}
	
	
	
}
