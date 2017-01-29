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
        //JFileChooser fc = new JFileChooser();
        //int returnVal = fc.showSaveDialog(null);
        //String file = fc.getSelectedFile().getAbsolutePath();

        for (int i = 0; i < 100; i++) {
            String file = "D:\\College\\Intuit Programming Challenge\\rit-challenge-master\\rit-challenge-master\\transaction-data\\user-" + i + "(SUMMARY).txt";
            ArrayList<TransactionType> classifiers = new ArrayList<TransactionType>(50);
            BufferedWriter bw;
            FileWriter fw = new FileWriter(
                    file.substring(0, file.length() - 4) + "(CLASSIFIED).txt");
            bw = new BufferedWriter(fw);
            //classifiers.add(new TransactionType("", new String[]{}));
            classifiers.add(new TransactionType("Fiscally irresponsible", new String[]{"negative", "overdraft", "overdue", "late"}));
            classifiers.add(new TransactionType("Drinking", new String[]{"bar", "night club", "club", "wine", "beer", "brewery"}));
            classifiers.add(new TransactionType("Student", new String[]{"student", "student loan", "ology", "math", "science"}));
            classifiers.add(new TransactionType("Coffee", new String[]{"coffee", "starbucks"}));
            classifiers.add(new TransactionType("Movies", new String[]{"theatre", "movie", "movie ticket"}));
            classifiers.add(new TransactionType("Travel", new String[]{"flight", "resort", "hotel"}));
            classifiers.add(new TransactionType("Eating Out", new String[]{"restaurant"}));
            classifiers.add(new TransactionType("Parent", new String[]{"baby", "babies", "buybuybaby", "prenatal"}));
            classifiers.add(new TransactionType("No-car", new String[]{"public transportation", "uber", "taxi", "lyft", "train", "bike"}));
            classifiers.add(new TransactionType("Utility", new String[]{"gas", "electric", "cable", "water"}));
            classifiers.add(new TransactionType("Moving", new String[]{"furniture", "move", "depot", "inn"}));
            classifiers.add(new TransactionType("Eating-in", new String[]{"whole foods", "grocer", "grubhub", "market"}));
            classifiers.add(new TransactionType("Musical", new String[]{"guitar", "music", "lessons", "concert"}));
            classifiers.add(new TransactionType("Artsy", new String[]{"art", "paint", "craft"}));
            classifiers.add(new TransactionType("Married", new String[]{"wedding", "kay", "jewelry"}));
            classifiers.add(new TransactionType("Divorced", new String[]{"divorce", "lawyer"}));
            classifiers.add(new TransactionType("Home/Apartment Owner", new String[]{"hous"}));
            classifiers.add(new TransactionType("Misc. Entertainment", new String[]{"bowling", "ice skating"}));
            classifiers.add(new TransactionType("Literaturey", new String[]{"library", "museum", "book store"}));
            classifiers.add(new TransactionType("Sci-fi", new String[]{"star", "geek"}));
            classifiers.add(new TransactionType("Gamer", new String[]{"game", "playstation"}));
            classifiers.add(new TransactionType("Pet-owner", new String[]{"pet", "cat", "dog"}));
            classifiers.add(new TransactionType("Cord-cutter", new String[]{"netflix", "tv", "podcast", "dvd"}));
            classifiers.add(new TransactionType("Athletic", new String[]{"athlet", "gym", "sport"}));
            classifiers.add(new TransactionType("Healthy", new String[]{"gnc", "vitamin"}));
            classifiers.add(new TransactionType("Sports", new String[]{"nfl", "nba"}));
            classifiers.add(new TransactionType("Income", new String[]{"paycheck"}));
            classifiers.add(new TransactionType("Misc.", new String[]{"fedex", "shipping"}));
            classifiers.add(new TransactionType("Credit Card", new String[]{"credit", "card"}));

            try (Stream<String> inputLines = Files.lines(Paths.get(file))) {

                for (String line : (Iterable<String>) inputLines::iterator) {

                    String[] transData = line.split(",");
                    String transaction = transData[0].toLowerCase();
                    for (TransactionType t : classifiers) {
                        if (t.ofThisType(transaction)) {
                            bw.write(t.getType() + "---------------------------");
                            break;
                        }
                    }
                    bw.write(line);
                    bw.newLine();

                }
            }

            bw.close();
            fw.close();
        }

    }
}
