package pl02;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Clase Hilo que implementa la interfaz Runnable.
 * 
 * @author Alisson Romero Chila
 *
 */
public class Jugador implements Runnable {
	/**
	 * Atributo tipo int.
	 */
	private int id;
	/**
	 * Atributo tipo boolean.
	 */
	private boolean experto;
	/**
	 * Atributo tipo int.
	 */
	private int numVueltas;
	/**
	 * Objeto tipo Club.
	 */
	private Club club;
	/**
	 * Objeto tipo Random.
	 */
	private Random rand = new Random();
	/**
	 * Atributo tipo int.
	 */
	private int jugar = ThreadLocalRandom.current().nextInt(0, 999);
	/**
	 * Atributo tipo int.
	 */
	private int descansar = ThreadLocalRandom.current().nextInt(0, 999);

	/**
	 * Definicion constructor solo con el id.
	 * 
	 * @param id
	 *            identificador.
	 */
	public Jugador(int id) {
		this.id = id;
	}

	/**
	 * Definicion constructor con el identificador y el tipo de jugador.
	 * 
	 * @param id
	 *            id.
	 * @param experto
	 *            experto.
	 * @param numVueltas
	 *            numvueltas.
	 * @param elClub
	 *            elClub.
	 */
	public Jugador(int id, boolean experto, int numVueltas, Club elClub) {
		this.id = id;
		this.experto = experto;
		this.numVueltas = numVueltas;
		this.club = elClub;
	}

	@Override
	public void run() {

		while (getNumVueltas() <= Simulador.NUM_VUELTAS) {
			try {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
				// reservar
				club.reservar(club.getNumPelotasIniciales(), club.getNumPalosIniciales());
				// jugar
				TimeUnit.MILLISECONDS.sleep(jugar);
				// devolver
				club.devolver(club.getNumPelotasIniciales(), club.getNumPalosIniciales());
				// descansar
				TimeUnit.MILLISECONDS.sleep(descansar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// Implementacion de getters
	public int getId() {
		return id;
	}

	public boolean isExperto() {
		return experto;
	}

	public int getNumVueltas() {
		return numVueltas;
	}

	public Club getClub() {
		return club;
	}

	public int getJugar() {
		return jugar;
	}

	public int getDescansar() {
		return descansar;
	}

}
