/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitclassifier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;
import javax.swing.JFileChooser;

/**
 *
 * @author dgiai
 */
public class TraitClassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        String file = fc.getSelectedFile().getAbsolutePath();
        ArrayList<TransactionType> classifiers = new ArrayList<TransactionType>(50);
        BufferedWriter bw;
        FileWriter fw = new FileWriter(
                file.substring(0, file.length() - 4) + "(CLASSIFIED).txt");
        bw = new BufferedWriter(fw);
        //classifiers.add(new TransactionType("", new String[]{}));
        classifiers.add(new TransactionType("Drinking", new String[]{"bar", "night club", "club", "wine", "beer", "brewery"}));
        classifiers.add(new TransactionType("Student", new String[]{"student", "student loan"}));
        classifiers.add(new TransactionType("Coffee", new String[]{"coffee", "starbucks"}));
        classifiers.add(new TransactionType("Movies", new String[]{"theatre", "netflix", "movie", "movie ticket"}));
        classifiers.add(new TransactionType("Travel", new String[]{"flight", "resort", "hotel"}));
        classifiers.add(new TransactionType("Eating Out", new String[]{"restaurant"}));
        classifiers.add(new TransactionType("Parent", new String[]{"baby", "babies", "buybuybaby", "prenatal"}));
        classifiers.add(new TransactionType("No-car", new String[]{"public transportation", "bus", "uber", "taxi", "lyft", "train"}));
        classifiers.add(new TransactionType("Utility", new String[]{"gas", "electric", "cable"}));
        classifiers.add(new TransactionType("Moving", new String[]{"furniture", "move", "depot"}));
        classifiers.add(new TransactionType("Eating-in", new String[]{"whole foods", "grocer", "grubhub"}));

        try (Stream<String> inputLines = Files.lines(Paths.get(file))) {

            for (String line : (Iterable<String>) inputLines::iterator) {

                String[] transData = line.split(",");
                String transaction = transData[0];
                bw.write(line);
                for (TransactionType t : classifiers) {
                    if (t.ofThisType(transaction)) {
                        bw.write(" ---- " + t.getType());
                        break;
                    }
                }
                bw.newLine();

            }
        }
        
        bw.close();
        fw.close();
    }

}
