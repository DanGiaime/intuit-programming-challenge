/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtovec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;
import javax.swing.JFileChooser;

/**
 *
 * @author dgiai
 */
public class WordToVec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String file = "USER CLASSIFIER LANGUAGE.txt";
        HashMap<String, Integer> vectorizer = new HashMap(28);

        try (Stream<String> inputLines = Files.lines(Paths.get(file))) {
            //Loads language into map
            int idx = 0;
            for (String line : (Iterable<String>) inputLines::iterator) {
                vectorizer.put(line, idx);
                idx++;
            }
        }

        double[][] examples = new double[100][28];
        int curr = 0;
        for (int i = 0; i < 100; i++) {
            String file2 = "user-" + i + "(CLASSIFIED)(SIMPLIFIED).csv";
            System.out.println("Vectorizing user-" + i + "'s transasctions");
            try (Stream<String> inputLines2 = Files.lines(Paths.get(file2))) {
                //Iterate through, create vectors from each classifier
                //Where each column is a classifier, and its value is 
                //the % spent on that classifier for the current person
                double sum = 0;
                for (String line : (Iterable<String>) inputLines2::iterator) {
                    String[] data = line.split(",");
                    String classifier = data[0];
                    double amt = Double.parseDouble(data[1]);
                    sum += amt;
                    examples[curr][vectorizer.get(classifier)] = amt;
                }
                for (int j = 0; j < 28; j++) {
                    examples[curr][j] /= sum;
                }
                curr++;
            }
        }
        //Woo! We got percentage wise vectors! let's start comparing.
        int[] bestMatches = new int[examples.length];
        for (int i = 0; i < examples.length; i++) {
            bestMatches[i] = findClosestPerson(examples, i);
        }
        //Write out best matches to the file
        writeMatchesToFile(file, bestMatches);
    }
    
    //Writes matches to file. Acknowledges perfect matches.
    static public void writeMatchesToFile(String file, int[] matches)
            throws IOException {
        BufferedWriter bw;
        FileWriter fw = new FileWriter(
                file.substring(0, file.length() - 24) + " MATCHES.csv");
        bw = new BufferedWriter(fw);
        //Write matches
        for (int i = 0; i < matches.length; i++) {
            System.out.print("user-" + i + " matches with user-" + matches[i]);
            bw.write("user-" + i + " matches with user-" + matches[i]);
            //if user-x and user-y match with eachother, write perf. match
            if (i == matches[matches[i]]) {
                System.out.print(" -- Perfect match!");
                bw.write(" -- Perfect match!");
            }
            System.out.println();
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    //Returns index of closest person.
    static public int findClosestPerson(double[][] examples, int index) {
        double[] distances = new double[examples.length];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < examples[0].length; j++) {
                //Distances formula! A lot!
                distances[i]
                        += Math.pow(examples[index][j] - examples[i][j], 2);
            }
        }
        //Grab a random, nonzero value to start with for min
        int minIndex = (index == 0) ? (1) : (0);

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < distances[minIndex] && i != index) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
