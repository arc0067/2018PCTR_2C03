package pl02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Jugador implements Runnable {
	
	protected int id;
	private boolean experto;
	private int numVueltas;
	private Club club;
	private Random rand = new Random();
	
	public Jugador(int id) {
		this.id=id;
	}
	
	public Jugador(int id, boolean experto, int numVueltas, Club elClub) {
		this.id = id;
		this.experto = experto;
		this.numVueltas = numVueltas;
		this.club = elClub;
	}

	@Override
	public void run() {
		int jugar = ThreadLocalRandom.current().nextInt(0,999);
		int descansar = ThreadLocalRandom.current().nextInt(0,999);
		while(numVueltas != 0) {
			try {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
				//reservar
				club.reservar(club.getNumPelotasIniciales(),club.getNumPalosIniciales());
				//jugar
				TimeUnit.MILLISECONDS.sleep(jugar);
				//devolver
				club.devolver(club.getNumPelotasIniciales(), club.getNumPalosIniciales());
				//descansar
				TimeUnit.MILLISECONDS.sleep(descansar);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isExperto() {
		return experto;
	}

	public void setExperto(boolean experto) {
		this.experto = experto;
	}

	public int getNumVueltas() {
		return numVueltas;
	}

	public void setNumVueltas(int numVueltas) {
		this.numVueltas = numVueltas;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

}
