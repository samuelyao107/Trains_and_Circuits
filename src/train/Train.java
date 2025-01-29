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
	//private Section sec;


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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}
	
	public void move() throws Exception {
		Element currentElement = pos.getPos();
		Element nextElement = railway.getNext(pos);
		if (nextElement != null) {
			 currentElement.leave(this);
			 nextElement.enter(this);
			 pos.updatePosition(nextElement, pos.getDirection());
			 	
		}else {}
		
		
		
	}

	@Override
	public void run() {
		while(true) {
			try {
				move();
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(name +"rencontre une erreur dans son déplacement:" + e.getMessage());
				break;
			}
			
		}
		
	}
}
