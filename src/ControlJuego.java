import java.time.LocalTime;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author Sebas Lavigne
 *
 */
public class ControlJuego {
	
	public final static int MINA = -1;
	public static final int SMALL = 10;
	public static final int MEDIUM = 16;
	public static final int BIG = 22;
	
	public static final int EASY = 5;
	public static final int NORMAL = 3;
	public static final int HARD = 2;
	
	
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	// Opciones de juego
	private int mineAmount;
	private int size;
	private int difficulty;
	
	// Tiempo de juego
	private LocalTime startTime;
	
	
	public ControlJuego() {
		
		// Valores por defecto de las opciones
		difficulty = ControlJuego.EASY;
		size = ControlJuego.SMALL;
		
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		//Establecemos cantidad de minas
		mineAmount = size*size / difficulty;
		
		//Creamos el tablero:
		tablero = new int[size][size];
		
		//Pone el tablero a cero
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = 0;
			}
		}
		/*
		 * Crea un vector con tantas casillas como tenga el tablero con minas y las reordena
		 */
		int[] linearMines = new int[size*size];
		Random rd = new Random();
		int aux, newPos;
		//TODO chequear que no se anadan mas minas que casillas haya en el tablero (no hace falta de momento)
		for (int i = 0; i < mineAmount; i++) {
			linearMines[i] = MINA;
		}
		//Mezcla las minas
		for (int i = 0; i < linearMines.length; i++) {
			newPos = rd.nextInt(linearMines.length);
			aux = linearMines[newPos];
			linearMines[newPos] = linearMines[i];
			linearMines[i] = aux;
		}
		
		//Pone las minas
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = linearMines[(size*i) + j];
			}
		}
		/*
		 * 
		 */
		
		//Reinicia puntuacion
		puntuacion = 0;
		
		populateFreeTiles();
	}


	/**
	 * Calcula las minas adjuntas de aquellas casillas que no tengan mina
	 */
	public void populateFreeTiles() {
		//Al final del m�todo hay que guardar el n�mero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
	}
	
	/**Cálculo de las minas adjuntas: 
	 * Para calcular el número de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdrán LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdrán 0.
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
		int count = 0;
		//El doble for recorre un cuadrado de 9 casillas con centro en (i,j)
		//Tambien recorre el centro, pero ese nunca tendra mina, el chequeo
		//se hace antes de llamar a este metodo
		for (int iLocal = i-1; iLocal < i+2; iLocal++) {
			for (int jLocal = j-1; jLocal < j+2; jLocal++) {
				//Si la coordenada se sale del tablero salta el catch
				try {
					//Si la coordenada contiene una mina, se suma al contador
					if ( tablero[iLocal][jLocal] == MINA ) {
						count++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}
			}
		}
		return count;
	}
	
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		puntuacion++;
		return tablero[i][j] != MINA;
	}
	
	
	
	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		//TODO para distinto numero de minas
		return puntuacion == size * size - mineAmount;
	}
	
	/**
	 * Debería usarse cuando el primer click de un jugador da en una mina
	 * Quita la mina de la casilla y la pone en otro lugar
	 * El nuevo lugar no puede ser el original ni puede tener ya una mina
	 * @param i
	 * @param j
	 */
	public void relocateMine(int i, int j) {
		Random rd = new Random();
		int iNew, jNew;
		boolean success = false;
		do {
			iNew = rd.nextInt(size);
			jNew = rd.nextInt(size);
			if (iNew != i && jNew != j && getMinasAlrededor(iNew, jNew) != MINA) {
				tablero[i][j] = 0;
				tablero[iNew][jNew] = MINA;
				populateFreeTiles();
				success = true;
			}
		} while ( !success );
		
		System.out.println("Se ha cambiado una mina de lugar");
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: "+puntuacion);
		System.out.println("LADO: "+size+", DIFFICULTAD: "+difficulty+", MINAS: "+mineAmount);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}


	/**
	 * @return the mineAmount
	 */
	public int getMineAmount() {
		return mineAmount;
	}


	/**
	 * @param mineAmount the difficulty to set
	 */
	public void setMineAmount(int mineAmount) {
		this.mineAmount = mineAmount;
	}


	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}


	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}


	/**
	 * @param difficultyModifier the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}


	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	
	
}
