/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchasesimplifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;
import javax.swing.JFileChooser;

/**
 *
 * @author dgiai
 */
public class PurchaseSimplifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //JFileChooser fc = new JFileChooser();
        //int returnVal = fc.showSaveDialog(null);
        //HashMap<String, Integer> count = new HashMap();
        //String file = fc.getSelectedFile().getAbsolutePath();
        String file = "";
        for (int i = 0; i < 100; i++) {
            HashMap<String, Double> transactions = new HashMap();
            file = "D:\\College\\Intuit Programming Challenge\\"
                    + "rit-challenge-master\\rit-challenge-master\\"
                    + "transaction-data\\user-" + i + "(CLASSIFIED).csv";
            try (Stream<String> inputLines = Files.lines(Paths.get(file))) {
                //boolean lineOne = true;
                for (String line : (Iterable<String>) inputLines::iterator) {
                    //if (!lineOne) {
                    String[] transData = line.split(",");
                    String transLoc = transData[0];
                    double transPrice = Double.parseDouble(transData[1]);

                    //If we already have this location saved, just add the price.
                    //Otherwise, add the location along with it's price.
                    if (transactions.containsKey(transLoc)) {
                        transactions.put(transLoc,
                                transactions.get(transLoc) + transPrice);
                        //count.put(transLoc, count.get(transLoc) + 1);
                    } else if (!transLoc.equalsIgnoreCase("income")) {
                        transactions.put(transLoc, transPrice);
                        //count.put(transLoc, 1);
                    }

                    //} else {
                    //    lineOne = false;
                    //}
                }
            }

            Set<String> keys = transactions.keySet();
            String[] transNames = new String[keys.size()];
            keys.toArray(transNames);

            BufferedWriter bw;
            FileWriter fw = new FileWriter(
                    file.substring(0, file.length() - 4) + "(SIMPLIFIED).csv");
            bw = new BufferedWriter(fw);
            for (String transName : transNames) {
                bw.write(transName + ","
                        + Math.round((transactions.get(transName) * 100) / 100));
                bw.newLine();
            }

            bw.close();
            fw.close();
        }

    }
}
