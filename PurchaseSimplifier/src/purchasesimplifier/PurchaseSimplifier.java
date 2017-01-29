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
    public static void main(String[] args) throws IOException{
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        String file = fc.getSelectedFile().getAbsolutePath();
        HashMap<String, Double> transactions = new HashMap();

        try (Stream<String> inputLines = Files.lines(Paths.get(file))) {
            boolean lineOne = true;
            for (String line : (Iterable<String>) inputLines::iterator) {
                if (!lineOne) {
                    String[] transData = line.split(",");
                    String transLoc = transData[2];
                    double transPrice = Double.parseDouble(transData[3]);

                    //If we already have this location saved, just add the price.
                    //Otherwise, add the location along with it's price.
                    if (transactions.containsKey(transLoc)) {
                        transactions.put(transLoc,
                                transactions.get(transLoc) + transPrice);
                    } else {
                        transactions.put(transLoc, transPrice);
                    }

                } else {
                    lineOne = false;
                }
            }
            
            Set<String> keys = transactions.keySet();
            String[] transNames = new String[keys.size()];
            keys.toArray(transNames);
            
            BufferedWriter bw;
            FileWriter fw  = new FileWriter(
                    file.substring(0,file.length()-4) + "(SUMMARY).txt");
            bw = new BufferedWriter(fw);
            for (String transName : transNames) {
                bw.write(transName + ","
                        + Math.round(transactions.get(transName)));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
    }
    
}
