package pl02;

/**
 * Simulador. Clase que se encarga de configurar y ejecutar el sistema.
 * Creará los elementos que lo constituyen: jugadores, club y material
 * 
 * @author Alisson Romero Chila
 *
 */
public class Simulador {
	/**
	 * Numero maximo de jugadores expertos. 
	 */
	public static final int NUM_EXPERTOS = 7;
	/**
	 * Numero maximo de jugadores novatos.
	 */
	public static final int NUM_NOVATOS = 7;
	/**
	 * Material maximo de pelotas.
	 */
	public static final int NUM_PELOTAS = 20;
	/**
	 * Material maximo de palos.
	 */
	public static final int NUM_PALOS = 20;
	/**
	 * Numero maximo que se repite el comportamiento.
	 */
	public static final int NUM_VUELTAS = 5;

	
	public static void main(String[] args) {
		//Instancia al club
		Club club = Club.getSingletonClub(NUM_PELOTAS,NUM_PALOS);
		
		//Creacion de arrays de tantos hilos como jugadores
		Thread[] hilosJugadorNovato = new Thread[NUM_NOVATOS];
		Thread[] hilosJugadorExperto = new Thread[NUM_EXPERTOS];
		
		//Recorremos el array de novatos y lanzamos a los jugadores
		for(int i = 1, ii= 1; i <= hilosJugadorNovato.length && ii<= 14; i++,ii++) {
			hilosJugadorNovato[i] = new Thread(new Jugador(ii,false,NUM_VUELTAS,club));
			hilosJugadorNovato[i].start();
		}
		
		//Recorremos el array de expertos y lanzamos a los jugadores
		for(int j = 1, jj=1; j <= hilosJugadorExperto.length && jj<= 14; j++, jj++) {
			hilosJugadorNovato[j] = new Thread(new Jugador(jj,true,NUM_VUELTAS,club));
			hilosJugadorNovato[j].start();
		}
	}

}
