import data.Data;
import mining.QTMiner;
import static keyboardinput.Keyboard.*;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Data data =new Data();
		System.out.println(data);

		char choice = 'y';

		do {
			System.out.println("Insert radius (>0):");
			double radius = readDouble();
			QTMiner qt = new QTMiner(radius);
			int numIter = qt.compute(data);
			if(numIter == 1) {
				System.out.println("14 tuples in one cluster!");
				continue;
			}
			System.out.println("Number of clusters:" + numIter);
			System.out.println(qt.getC().toString(data));
			do {
				System.out.println("New execution? (y/n)");
				choice = readChar();
			}while(choice != 'y' && choice != 'n');
		}while(choice=='y');
	}

}
