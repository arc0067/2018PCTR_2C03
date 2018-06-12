package pl02;

import java.util.ArrayList;

/**
 * Clase que gestiona el uso del material. Se trata del objeto pasivo.
 * 
 * @author Alisson Romero Chila
 *
 */
public class Club {
	// Definicion de constantes para controlar las invariantes.
	public static final int MAX_PELOTAS_NOVATOS = 5;
	public static final int MAX_PALOS_NOVATOS = 2;
	public static final int MAX_PELOTAS_EXPERTOS = 1;
	public static final int MIN_PALOS_EXPERTOS = 2;
	public static final int MAX_PALOS_EXPERTOS = 5;
	// Definicion de arrays para gestionar a los jugadores.
	public static ArrayList<Jugador> jugadoresNovatos;
	public static ArrayList<Jugador> jugadoresExpertos;
	// Materiales disponibles.
	private int numPelotasIniciales;
	private int numPalosIniciales;

	private static final Object Lock = Club.class;
	/**
	 * Instancia: Singleton. Se garantiza que solo tenga una instancia. Evitando la
	 * inicializacion retardada.
	 */
	private static Club club;

	// El contructor privado evita que se cree un constructor por defecto
	private Club(int numPelotasIniciales, int numPalosIniciales) {
		this.numPelotasIniciales = numPelotasIniciales;
		this.numPalosIniciales = numPalosIniciales;
		jugadoresNovatos = inicializarJugadoresNovatos();
		jugadoresExpertos = inicializarJugadoresExpertos();
	}

	public static Club getSingletonClub(int numPelotasIniciales, int numPalosIniciales) {
		synchronized (Lock) {
			if (club == null) {
				club = new Club(numPelotasIniciales, numPalosIniciales);
			}
			return club;
		}

	}
	
	//Inicializacion del array de los jugadores.
	public ArrayList<Jugador> inicializarJugadoresNovatos() {
		ArrayList<Jugador> jugadoresNovatos = new ArrayList<Jugador>();
		for (int i = 1; i <= 14; i++) {
			jugadoresNovatos.add(new Jugador(i));
		}
		return jugadoresNovatos;
	}

	public ArrayList<Jugador> inicializarJugadoresExpertos() {
		ArrayList<Jugador> jugadoresExpertos = new ArrayList<Jugador>();
		for (int j = 1; j <= 14; j++) {
			jugadoresExpertos.add(new Jugador(j));
		}
		return jugadoresExpertos;
	}

	public void reservar(int pelotas, int palos) {
		synchronized (this) {

			if (pelotas > 0 && pelotas <= getNumPelotasIniciales() && palos > 0 && palos <= getNumPalosIniciales()) {
				decrementarNumPelotasIniciales();
				decrementarNumPalosIniciales();
				// Salida por pantalla
				for (Jugador j : getJugadoresNovatos()) {
					System.out.println(
							"Antes de reservar: " + j.getId() + "-" + " [ " + pelotas + " , " + palos + " ] reservar");
					System.out.println("Después de reservar: " + j.getId() + "-" + " [ " + numPelotasIniciales + " , "
							+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
				}
				assert numPalosIniciales <= MAX_PALOS_NOVATOS;
				assert numPelotasIniciales <= MAX_PELOTAS_NOVATOS;

				decrementarNumPelotasIniciales();
				decrementarNumPalosIniciales();
				// Salida por pantalla
				for (Jugador j : getJugadoresExpertos()) {
					System.out.println(
							"Antes de reservar: " + j.getId() + "+" + " [ " + pelotas + " , " + palos + " ] reservar");
					System.out.println("Después de reservar: " + j.getId() + "+" + " [ " + numPelotasIniciales + " , "
							+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
				}
				assert MIN_PALOS_EXPERTOS >= numPalosIniciales;
				assert numPalosIniciales <= MAX_PALOS_EXPERTOS;
				assert numPelotasIniciales == MAX_PELOTAS_EXPERTOS;
			}
		}
	}

	public void devolver(int pelotas, int palos) {
		synchronized (this) {

			if (pelotas > 0 && pelotas <= getNumPelotasIniciales() && palos > 0 && palos <= getNumPalosIniciales()) {
				incrementarNumPelotasIniciales();
				incrementarNumPalosIniciales();
				// Salida por pantalla
				for (Jugador j : getJugadoresNovatos()) {
					System.out.println(
							"Antes de devolver: " + j.getId() + "-" + " [ " + pelotas + " , " + palos + " ] devolver");
					System.out.println("Después de devolver: " + j.getId() + "-" + " [ " + numPelotasIniciales + " , "
							+ numPalosIniciales + " , " + j.getJugar() + " ] devolver");
				}
				//Invariantes
				assert numPalosIniciales <= MAX_PALOS_NOVATOS;
				assert numPelotasIniciales <= MAX_PELOTAS_NOVATOS;

				incrementarNumPelotasIniciales();
				incrementarNumPalosIniciales();
				// Salida por pantalla
				for (Jugador j : getJugadoresExpertos()) {
					System.out.println(
							"Antes de reservar: " + j.getId() + "+" + " [ " + pelotas + " , " + palos + " ] reservar");
					System.out.println("Después de reservar: " + j.getId() + "+" + " [ " + numPelotasIniciales + " , "
							+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
				}
				//Invariantes
				assert MIN_PALOS_EXPERTOS >= numPalosIniciales;
				assert numPalosIniciales <= MAX_PALOS_EXPERTOS;
				assert numPelotasIniciales == MAX_PELOTAS_EXPERTOS;
			}
		}
	}
	
	//Definicion de decrementos/incrementos
	public void decrementarNumPelotasIniciales() {
		numPelotasIniciales--;
	}

	public void decrementarNumPalosIniciales() {
		numPalosIniciales--;
	}

	public void incrementarNumPelotasIniciales() {
		numPelotasIniciales++;
	}

	public void incrementarNumPalosIniciales() {
		numPalosIniciales++;
	}
	
	//Definicion de getters
	public int getNumPelotasIniciales() {
		return numPelotasIniciales;
	}

	public int getNumPalosIniciales() {
		return numPalosIniciales;
	}

	public static ArrayList<Jugador> getJugadoresNovatos() {
		return jugadoresNovatos;
	}

	public static ArrayList<Jugador> getJugadoresExpertos() {
		return jugadoresExpertos;
	}

}
