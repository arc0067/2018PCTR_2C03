package pl02;

import java.util.ArrayList;

/**
 * Clase que gestiona el uso del material. Se trata del objeto pasivo.
 * 
 * @author Alisson Romero Chila
 *
 */
public class Club {
	public static final int MAX_PELOTAS_NOVATOS = 5;
	public static final int MAX_PALOS_NOVATOS = 2;
	public static final int MAX_PELOTAS_EXPERTOS = 1;
	public static final int MIN_PALOS_EXPERTOS = 2;
	public static final int MAX_PALOS_EXPERTOS = 5;
	public static ArrayList<Jugador> jugadoresNovatos;
	public static ArrayList<Jugador> jugadoresExpertos;
	

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

	public ArrayList<Jugador> inicializarJugadoresNovatos() {
		ArrayList<Jugador> jugadoresNovatos = new ArrayList<Jugador>();
		for (int i = 0, ii = 1; i <= Simulador.NUM_NOVATOS && ii <= 14; i++, ii++) {
			jugadoresNovatos.add(new Jugador(ii));
		}
		return jugadoresNovatos;
	}

	public ArrayList<Jugador> inicializarJugadoresExpertos() {
		ArrayList<Jugador> jugadoresExpertos = new ArrayList<Jugador>();
		for (int j = 0, jj = 1; j <= Simulador.NUM_EXPERTOS && jj <= 14; j++, jj++) {
			jugadoresExpertos.add(new Jugador(jj));
		}
		return jugadoresExpertos;
	}

	public void reservar(int pelotas, int palos) {
		synchronized (this) {
			if (pelotas > 0 && pelotas <= MAX_PELOTAS_NOVATOS && palos > 0 && palos <= MAX_PALOS_NOVATOS) {
				decrementarNumPelotasIniciales();
				decrementarNumPalosIniciales();
				// Salida por pantalla
				for (Jugador j : getJugadoresNovatos()) {
					System.out.println(
							"Antes de reservar: " + j.getId() + "-" + " [ " + pelotas + " , " + palos + " ] reservar");
					System.out.println("Después de reservar: " + j.getId() + "-"  + " [ " + numPelotasIniciales + " , "
							+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
				}

			} else {
				if (pelotas > 0 && pelotas <= MAX_PELOTAS_EXPERTOS && palos >= MIN_PALOS_EXPERTOS && palos <= MAX_PALOS_EXPERTOS) {
					decrementarNumPelotasIniciales();
					decrementarNumPalosIniciales();
					// Salida por pantalla
					for (Jugador j : getJugadoresExpertos()) {
						System.out.println(
								"Antes de reservar: " + j.getId() + "+" + " [ " + pelotas + " , " + palos + " ] reservar");
						System.out.println("Después de reservar: " + j.getId() + "+" + " [ " + numPelotasIniciales + " , "
								+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
					}
				}
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

			} else {
				if (pelotas > 0 && pelotas <= getNumPelotasIniciales() && palos > 0 && palos <= getNumPalosIniciales()) {
					incrementarNumPelotasIniciales();
					incrementarNumPalosIniciales();
					// Salida por pantalla
					for (Jugador j : getJugadoresExpertos()) {
						System.out.println(
								"Antes de reservar: " + j.getId() + "+" + " [ " + pelotas + " , " + palos + " ] reservar");
						System.out.println("Después de reservar: " + j.getId() + "+" + " [ " + numPelotasIniciales + " , "
								+ numPalosIniciales + " , " + j.getJugar() + " ] reservar");
					}
				}
			}
		}
	}

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

	public int getNumPelotasIniciales() {
		return numPelotasIniciales;
	}

	public void setNumPelotasIniciales(int numPelotasIniciales) {
		this.numPelotasIniciales = numPelotasIniciales;
	}

	public int getNumPalosIniciales() {
		return numPalosIniciales;
	}

	public void setNumPalosIniciales(int numPalosIniciales) {
		this.numPalosIniciales = numPalosIniciales;
	}
	
	public static ArrayList<Jugador> getJugadoresNovatos() {
		return jugadoresNovatos;
	}

	public static void setJugadoresNovatos(ArrayList<Jugador> jugadoresNovatos) {
		Club.jugadoresNovatos = jugadoresNovatos;
	}

	public static ArrayList<Jugador> getJugadoresExpertos() {
		return jugadoresExpertos;
	}

	public static void setJugadoresExpertos(ArrayList<Jugador> jugadoresExpertos) {
		Club.jugadoresExpertos = jugadoresExpertos;
	}

}
