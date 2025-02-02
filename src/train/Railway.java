package train;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	private boolean isDirectionOccupiedLR = false;
    private boolean isDirectionOccupiedRL = false;
    private int activeTrainsLR = 0; // Nombre de trains circulant de gauche à droite
    private int activeTrainsRL = 0; // Nombre de trains circulant de droite à gauche
    
	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
	
	/** la méthode getNext() permet de récupérer l'élément suivant à parcourir pour un train en fonction de sa position courante et de sa direction de circulation*/
	public Element getNext(Position pos) {
		
		Element current = pos.getPos();
        Direction direction = pos.getDirection();

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == current) {
                if (direction == Direction.LR && i + 1 < elements.length) return elements[i + 1];
                if (direction == Direction.RL && i - 1 >= 0) return elements[i - 1];
            }
        }
        return null;
		
	}
	/** askEntry() bloque un train tant qu’un autre circule dans le sens opposé */
	 public synchronized void askEntry(Direction dir) throws InterruptedException {
	        // Attendre tant qu’un train circule déjà dans l’autre sens
	        while ((dir == Direction.LR && (isDirectionOccupiedRL || activeTrainsRL>0)) ||
	               (dir == Direction.RL && (isDirectionOccupiedLR||activeTrainsLR>0))) {
	        	
	            wait();
	        }
	        // Bloquer la direction pour empêcher d’autres trains de démarrer en sens inverse
	        if (dir == Direction.LR) {
	            isDirectionOccupiedLR = true;
	            activeTrainsLR++;
	        } else {
	            isDirectionOccupiedRL = true;
	            activeTrainsRL++;
	            
	        }
	  }
	 
	 
	 /** releaseEntry() bloque un train tant qu’un autre circule dans le sens opposé */
	 public synchronized void releaseEntry(Direction direction) {
		    if (direction == Direction.LR) {
		        activeTrainsLR--;
		        if (activeTrainsLR == 0) { 
		        //  Si il n'y a plus de trains sur cette ligne circulant dans la direction LR, libérer la ligne
		            isDirectionOccupiedLR = false;
		            notifyAll(); //  Réveiller les trains RL en attente
		        }
		    } else {
		        activeTrainsRL--;
		        if (activeTrainsRL == 0) { 
		        	//  Si il n'y a plus de trains sur cette ligne circulant dans la direction RL, libérer la ligne
		            isDirectionOccupiedRL = false;
		            notifyAll(); //  Réveiller les trains LR en attente
		        }
		    }
		}

	 
	 
}
