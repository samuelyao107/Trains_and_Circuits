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
public class Railway implements Runnable {
	private final Element[] elements;
    private Train train;
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
	
	public Element getNext(Element elem) {
		Iterator<Element> it = Arrays.asList(elements).iterator();
		while(it.hasNext()) {
			Element current = it.next();
			if(Objects.equals(current, elem)) {
				if(it.hasNext()) {
					return it.next();
				}
			}
		}
		
		return null;
	}
	
	public void addTrain(Train train) {
		this.train = train;
	}

	@Override
	public void run() {
		boolean cond = true;
		while(cond) {
			Element elem = getNext(train.getPos().getPosElem());
			train.move(elem);
			if(Objects.equals(train.getPos().getPosElem(), null))
				cond = false;
		}
	}
}
