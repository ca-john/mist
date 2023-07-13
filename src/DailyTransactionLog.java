package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A class to represent/log all the transactions that take place within a given day into a file.
 */
public class DailyTransactionLog{

    public ArrayList<Transaction> transactions;

    public DailyTransactionLog() throws IOException {

        //clear log from previous day
        Writer output = new BufferedWriter(new FileWriter("Daily.txt", false));
        output.write("");
        output.close();

        transactions = new ArrayList<Transaction>();

    }

    public void write() throws IOException {
        Writer output = new BufferedWriter(new FileWriter("Daily.txt", true));
        Transaction[] loggo = new Transaction[this.transactions.size()];
        transactions.toArray(loggo);
        for (int i = 0; i < transactions.size(); i++) {

            Transaction trans = loggo[i];
            String logout = String.join(" ", trans.source);
            output.append(logout);
            output.append("\n");
        }

        output.close();
    }
}