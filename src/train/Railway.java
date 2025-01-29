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
}
