package pl02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Jugador implements Runnable {
	
	private int id;
	private boolean experto;
	private int numVueltas;
	private Club club;
	private Random rand = new Random();
	
	public Jugador() {
			
	}
	
	public Jugador(int id, boolean experto, int numVueltas, Club elClub) {
		this.id = id;
		this.experto = experto;
		this.numVueltas = numVueltas;
		this.club = elClub;
	}

	@Override
	public void run() {
		int aleatorio = ThreadLocalRandom.current().nextInt(0,999);
		while(numVueltas != 0) {
			try {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
				//reservar
				club.reservar(club.getNumPelotasIniciales(),club.getNumPalosIniciales());
				//jugar
				TimeUnit.MILLISECONDS.sleep(aleatorio);
				//devolver
				club.devolver(club.getNumPelotasIniciales(), club.getNumPalosIniciales());
				//descansar
				TimeUnit.MILLISECONDS.sleep(aleatorio);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
