package train;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 *   <li>
 *     Son nom pour l'affichage.
 *   </li>
 *   <li>
 *     La position qu'il occupe dans le circuit (un élément avec une direction) : classe {@link Position}.
 *   </li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr>
 * Test if the first element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */
public class Train implements Runnable {
	private final String name;
	private  Position pos;
	private final Railway railway;
	private boolean isArrived = false;
	


	public Train(String name, Position p, Railway railway) throws BadPositionForTrainException {
		if (name == null || p == null || railway== null)
			throw new NullPointerException();

		// A train should be first be in a station
		if (!(p.getPos() instanceof Station))
			throw new BadPositionForTrainException(name);

		this.name = name;
		this.pos = p.clone();
		this.railway= railway;
	}
    
	
	public String getName() {
		return this.name;
		
	}
	
	public Position getPosition() {
		return this.pos;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}
	/** La classe move() permet de simuler le déplacement du train section par section jusqu'à la fin de la ligne de train */
	public void move() throws Exception {
		if (isArrived) return;
		Element currentElement = pos.getPos();
		Element nextElement = railway.getNext(pos);
		if (nextElement != null) {
			synchronized(nextElement) {
				
				currentElement.leave(this);
				nextElement.enter(this);
				pos.updatePosition(nextElement, pos.getDirection());
				 	
			}
			 
		}else {
			System.out.println("Le train " + name + " est arrivé à destination et libère la ligne.");
	        railway.releaseEntry(pos.getDirection()); // le train libère la ligne à l’arrivée
	        isArrived = true;
		}
		
	}

	@Override
	public void run() {
		
			try {
				railway.askEntry(pos.getDirection());//lorsqu'un train veut circuler il demande d'abord à verrouiller la railway 
				while(!isArrived) {
				move();
				Thread.sleep(2000);}
			}catch(Exception e) {
				System.out.println(name +"rencontre une erreur dans son déplacement:" + e.getMessage());
				
			}
	}
}
