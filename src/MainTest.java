import data.Data;
import mining.ClusteringRadiusException;
import mining.EmptyDatasetException;
import mining.QTMiner;
import static keyboardinput.Keyboard.*;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Data data =new Data();
		System.out.println(data);

		char choice;
		double radius;

		do {
			do {
				System.out.println("Insert radius (>0):");
				radius = readDouble();
			}while(radius<=0);

			QTMiner qt = new QTMiner(radius);

			try {

				int numIter = qt.compute(data);
				System.out.println("Number of clusters:" + numIter);
				System.out.println(qt.getC().toString(data));

			}catch(EmptyDatasetException e) {
				System.out.println(e.getMessage());
			}catch(ClusteringRadiusException e) {
				System.out.println(e.getMessage());
			}

			do {
				System.out.println("New execution? (y/n)");
				choice = readChar();
			}while(choice != 'y' && choice != 'n');
		}while(choice=='y');
	}
}