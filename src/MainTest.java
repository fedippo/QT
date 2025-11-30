import data.Data;
import database.DatabaseConnectionException;
import database.EmptySetException;
import database.NoValueException;
import mining.ClusteringRadiusException;
import mining.EmptyDatasetException;
import mining.QTMiner;
import java.sql.SQLException;
import static keyboardinput.Keyboard.*;

/**
 * La classe MainTest è la classe principale per l'esecuzione del programma.
 * Offre un menu interattivo per l'utente per scegliere tra il caricamento e
 * l'analisi di dati da un database (Clustering QT) o il caricamento di un clustering salvato da file.
 */
public class MainTest {

    /**
     * Punto di ingresso principale del programma.
     * Gestisce il loop del menu principale, consentendo all'utente di selezionare
     * l'operazione desiderata fino a quando non decide di uscire.
     *
     * @param args Array di argomenti della riga di comando (non utilizzato).
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
            else {caricaData();}

            do {
                System.out.println("Would you choose another option from the menu?(y/n)");
                choice = readChar();
            }while(choice != 'y' && choice != 'n');
        }
    }

    /**
     * Gestisce la logica per caricare un clustering salvato precedentemente su file.
     * Chiede all'utente il nome del file, tenta di inizializzare l'oggetto QTMiner
     * e stampa i risultati del clustering caricato.
     * Gestisce eventuali eccezioni durante il caricamento del file.
     */
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

    /**
     * Gestisce la logica per il caricamento dei dati dal database e l'esecuzione dell'algoritmo
     * di clustering QT (Quality Threshold).
     * 1. Richiede il nome della tabella del database e carica i dati, gestendo le eccezioni di connessione/dati.
     * 2. Se il caricamento ha successo, entra in un loop per eseguire l'algoritmo:
     * a. Richiede all'utente un raggio (>0).
     * b. Esegue il clustering (QTMiner.compute).
     * c. Stampa il risultato del clustering (numero di cluster e descrizione).
     * d. Offre la possibilità di salvare il risultato su file.
     */
    private static void caricaData(){
        System.out.print("dataset name:");
        String TableName = readString();
        Data data = null;

        try {
            data = new Data(TableName);
            System.out.println(data);

        }catch (DatabaseConnectionException | SQLException | EmptySetException | NoValueException e){
            return;
        }

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

            }catch(EmptyDatasetException | ClusteringRadiusException e) {
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