package pl02;

public class Simulador {

	public static final int NUM_EXPERTOS = 7;
	public static final int NUM_NOVATOS = 7;
	public static final int NUM_PELOTAS = 20;
	public static final int NUM_PALOS = 20;
	public static final int NUM_VUELTAS = 5;

	public static void main(String[] args) {
		//Instancia al club
		Club club = Club.getSingletonClub(NUM_PELOTAS,NUM_PALOS);
		
		//Creacion de array de tantos hilos como jugadores
		Thread[] hilosJugadorNovato = new Thread[NUM_NOVATOS];
		Thread[] hilosJugadorExperto = new Thread[NUM_EXPERTOS];
		
		//Recorremos el array de novatos y lanzamos al jugador
		for(int i = 1; i <= hilosJugadorNovato.length; i++) {
			hilosJugadorNovato[i] = new Thread(new Jugador(i,false,NUM_VUELTAS,club));
			hilosJugadorNovato[i].start();
		}
		
		//Recorremos el array de novatos y lanzamos al jugador
		for(int j = 8; j <= hilosJugadorExperto.length; j++) {
			hilosJugadorNovato[j] = new Thread(new Jugador(j,true,NUM_VUELTAS,club));
			hilosJugadorNovato[j].start();
		}
	}

}
