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
        JFileChooser fc = new JFileChooser();
        System.out.println("Choose Language File");
        int returnVal = fc.showSaveDialog(null);
        String file = fc.getSelectedFile().getAbsolutePath();
        HashMap<String, Integer> vectorizer = new HashMap(168);

        try (Stream<String> inputLines = Files.lines(Paths.get(file))) {
            //Loads language into map
            int idx = 0;
            for (String line : (Iterable<String>) inputLines::iterator) {
                vectorizer.put(line, idx);
                idx++;
            }
        }

        JFileChooser fc2 = new JFileChooser();
        System.out.println("choose file to matricize");
        int returnVal2 = fc2.showSaveDialog(null);
        String file2 = fc2.getSelectedFile().getAbsolutePath();
        int[][] examples = new int[60][188];

        try (Stream<String> inputLines2 = Files.lines(Paths.get(file2))) {
            //Loads language into map
            int curr = 0;
            for (String line : (Iterable<String>) inputLines2::iterator) {
                line = line.substring(0, line.indexOf(',')).toLowerCase();
                String[] words = line.split(" ");
                for (String word : words) {
                    System.out.println(vectorizer.get(word));
                    examples[curr][vectorizer.get(word)] = 1;
                }
                curr++;
            }
        }

        BufferedWriter bw;
        FileWriter fw = new FileWriter(
                file.substring(0, file.length() - 4) + "(SUMMARY).mat");
        bw = new BufferedWriter(fw);
        bw.write("X = [");
        bw.newLine();

        for (int i = 0; i < examples.length; i++) {
            for (int j = 0; j < examples[i].length; j++) {
                bw.write(examples[i][j] + " ");
            }
            bw.write(";");
            bw.newLine();
        }
        
        bw.write("]");
        bw.close();
        fw.close();
    }
}
