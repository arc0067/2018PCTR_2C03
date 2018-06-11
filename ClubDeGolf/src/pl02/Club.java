package pl02;

public class Club {
	
	private int numPelotasIniciales = 0;
	private int numPalosIniciales = 0;
	
	private static final Object Lock = Club.class;
	/**
	 * Instancia: Singleton. Garantiza que la clase Club solo tenga
	 * una instancia.
	 * Evita inicializacion retardada.
	 */
	private static Club club;
	
	
	private Club(int numPelotasIniciales, int numPalosIniciales) {
		this.numPelotasIniciales = numPelotasIniciales;
		this.numPalosIniciales = numPalosIniciales;
	}
	
	public static Club getSingletonClub(int numPelotasIniciales, int numPalosIniciales) {
		synchronized (Lock) {
			if (club == null) {
				club = new Club(numPelotasIniciales,numPalosIniciales);
			}
			return club;
		}

	}
	
	public void reservar(int pelotas, int palos) {
		synchronized (this) {
			if( )
		}
	}
	
	public void devolver(int pelotas, int palos) {
		
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

}
