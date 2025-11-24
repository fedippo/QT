import data.Data;
import mining.ClusteringRadiusException;
import mining.EmptyDatasetException;
import mining.QTMiner;

import java.io.File;

import static keyboardinput.Keyboard.*;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        char choice = 'y';

        while(choice=='y'){
            int selection = 0;
            while(selection != 1 && selection != 2) {
                System.out.println("Select an option:");
                System.out.println("(1) Load Cluster from file");
                System.out.println("(2) Load Data");
                System.out.print("Answer:");
                selection = readInt();
            }

            if(selection == 1){caricaFile();}
            else if(selection == 2){caricaData();}

            do {
                System.out.println("Would you choose another option from the menu?(y/n)");
                choice = readChar();
            }while(choice != 'y' && choice != 'n');
        }
    }

    private static void caricaFile() {
        System.out.print("archive name:");
        String fileName = readString();
        try {
            QTMiner qt = new QTMiner(fileName);
            System.out.println(qt.getC().toString());
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void caricaData(){
        Data data =new Data();
        System.out.println(data);

        char choice = 'y';

        while(choice=='y') {
            double radius = 0;
            while(radius<=0) {
                System.out.println("Insert radius (>0):");
                radius = readDouble();
            }

            QTMiner qt = new QTMiner(radius);

            try {

                int numIter = qt.compute(data);
                System.out.println("Number of clusters:" + numIter);
                System.out.println(qt.getC().toString(data));

            }catch(EmptyDatasetException e) {
                System.err.println(e.getMessage());
            }catch(ClusteringRadiusException e) {
                System.err.println(e.getMessage());
            }

            do {
                System.out.println("Vuoi salvare su file? (y/n)");
                choice = readChar();
            }while(choice != 'y' && choice != 'n');
            if (choice == 'y') {
                System.out.println("nome del file:");
                String fileName = readString();
                try {
                    qt.salva(fileName);
                }catch (Exception e) {System.err.println(e.getMessage());}
            }

            do {
                System.out.println("New execution? (y/n)");
                choice = readChar();
            }while(choice != 'y' && choice != 'n');
        }
    }
}