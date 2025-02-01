package train;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {
		Station A = new Station("GareA", 3);
		Station D = new Station("GareD", 3);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position p = new Position(A, Direction.LR);
		Position p2 = new Position(D, Direction.RL);
		try {
			Train t1 = new Train("1", p, r);
			Train t2 = new Train("2", p, r);
			Train t3 = new Train("3", p2,r);
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(t3);
			
			//Création des threads à partir des trains
			 Thread trainThread1 = new Thread(t1);
			 Thread trainThread2 = new Thread(t2);
			 Thread trainThread3 = new Thread(t3);
	         trainThread1.start();
	         trainThread2.start();
	         trainThread3.start();
	        

		} catch (BadPositionForTrainException e) {
			System.out.println("Le train " + e.getMessage());
		}

	}
}
