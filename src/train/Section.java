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
	public Section(String name) {
		super(name);
	}
	

	public synchronized void enter(Train train) {
		this.train= train;
		System.out.println("le train entre dans la section "+ this.toString());
		
	}


	public synchronized void leave(Train train) {
		if(this.train==train) {
			this.train= null;
		}
		System.out.println("le train sort de la section "+ this.toString());
		
	}
	
}
