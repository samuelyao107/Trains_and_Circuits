package train;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {
	private final int size;
	 private int occupied;

	public Station(String name, int size) {
		super(name);
		this.occupied =0;
		if(name == null || size <=0)
			throw new NullPointerException();
		this.size = size;
	}

	@Override
	/** Un train peut entrer dans une station tant qu'il y'a un quai disponible */
	public synchronized void enter(Train train) throws InterruptedException {
		
		while(occupied>=size) {
			System.out.println("le train "+ train.getName()+" ne peut entrer dans la gare "+ this.toString()+" car tous les quais sont occupés");
			wait();
		}
		occupied ++;
		System.out.println("le train "+train.getName()+ " entre dans la gare "+ this.toString());
		
	}

	@Override
	/** Un train peut quitter une station à tout moment*/
	public synchronized void leave(Train train) {
		if (occupied> 0) {
			System.out.println("le train "+  train.getName()+" sort dans la gare "+ this.toString());
			notifyAll();
		}
		
		
	}

	
}
