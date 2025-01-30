package train;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {
	private Train train;
	
	 private Direction directionOccupied;
	
	
	public Section(String name) {
		super(name);
	}
	
/** La méthode enter() doit pouvoir vérifier qu'un seul train ne peut rentrer dans la section
 * Cette méthode doit aussi prendre en compte le fait qu'un autre train peut vouloi entrer par le sens opposé*/
	public synchronized void enter(Train train) throws InterruptedException {
		while(this.train != null || (directionOccupied != null && directionOccupied != train.getPosition().getDirection())) {
			System.out.println("le train "+ train.getName()+" ne peut entrer dans la section "+ this.toString()+" car elle est occupée par le train "+ this.train.getName());
			wait();
		}
		
		this.train= train;
		directionOccupied = train.getPosition().getDirection();
		System.out.println("le train "+train.getName()+" entre dans la section "+ this.toString());
		
	}


	public synchronized void leave(Train train) {
		if(this.train==train) {
			System.out.println("le train "+train.getName()+" sort de la section "+ this.toString());
			this.train= null;
			directionOccupied = null;
			notifyAll();
		}
		
		
	}
	 public synchronized boolean OppositeMove(Direction trainDirection) {
	        return (this.train != null && directionOccupied != trainDirection);
	    }
	
}
