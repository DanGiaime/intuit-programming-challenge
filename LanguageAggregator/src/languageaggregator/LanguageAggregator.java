/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languageaggregator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 *
 * @author dgiai
 */
public class LanguageAggregator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        HashSet<String> language = new HashSet<String>(500);
        String file = "";
        for (int i = 0; i < 100; i++) {
            file = "D:\\College\\Intuit Programming Challenge\\rit-challenge-master\\rit-challenge-master\\transaction-data\\user-" + i + "(SUMMARY).txt";

            //Iterate over lines of file
            try (Stream<String> inputLines = Files.lines(Paths.get(file))) {

                for (String line : (Iterable<String>) inputLines::iterator) {

                    //Get the name of the transaction
                    String[] transData = line.split(",");
                    String transaction = transData[0].toLowerCase();
                    String[] words = transaction.split(" ");

                    //Add all words to language
                    language.addAll(Arrays.asList(words));
                }
            }

        }

        BufferedWriter bw;
        FileWriter fw = new FileWriter(
                file.substring(0, file.length() - 15) + "(LANGUAGE).txt");
        bw = new BufferedWriter(fw);

        //Swap to array
        String[] finalLanguage = new String[language.size()];
        language.toArray(finalLanguage);

        //Write to file
        for (String word : finalLanguage) {
            bw.write(word);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }
}
