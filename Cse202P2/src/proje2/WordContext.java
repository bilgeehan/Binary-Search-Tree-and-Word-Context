package proje2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WordContext {

    static HashMap<String, ArrayList<Integer>> WC = new HashMap<>();
    public static Scanner yns = new Scanner(System.in);

    public static void main(String[] args) throws InputMismatchException, IOException {
        System.out.println("Please Enter the Filename (Your file must contains at least 5 WORDS!)");
        String fileName = yns.next() + ".txt";

        System.out.println("Please Enter the Word");
        String searchText = yns.next();

        String fileString = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        String[] words = fileString.split("\\s+");
        System.out.println(words.length);
        if (words.length < 5) {
            System.err.println("Your file must contain at least 5 WORDS!");
            System.out.println("Goodbye!");
            System.exit(0);
        }
        for (int i = 0; i <= words.length - 1; i++) {
            String temp = removePunctitations(words[i]);

            if (!WC.containsKey(temp)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                WC.put(temp, list);
            } else {
                WC.get(temp).add(i);
            }
        }
        ArrayList<Integer> occurance = WC.get(searchText);
        if (occurance == null) {
            System.out.println("0 Occurance");
        } else {
            System.out.println(occurance.size() + " Occurance");
            for (int i = 0; i <= occurance.size() - 1; i++) {
                int index = occurance.get(i);
                if (index >= 2 && index + 2 < words.length) {
                    String output = words[index - 2] + " " + words[index - 1] + " " + words[index] + " " + words[index + 1] + " " + words[index + 2];
                    System.out.println(output);
                } else if (index == 0) {
                    String output = words[index] + " " + words[index + 1] + " " + words[index + 2];
                    System.out.println(output);
                } else if (index == 1) {
                    String output = words[index - 1] + " " + words[index] + " " + words[index + 1] + " " + words[index + 2];
                    System.out.println(output);
                } else if (index == words.length - 2) {
                    String output = words[index - 2] + " " + words[index - 1] + " " + words[index] + " " + words[index + 1];
                    System.out.println(output);
                } else if (index == words.length - 1) {
                    String output = words[index - 2] + " " + words[index - 1] + " " + words[index];
                    System.out.println(output);
                }
            }
        }

    }

    public static String removePunctitations(String str) {
        str = str.replaceAll(",", "");
        str = str.replaceAll(";", "");
        str = str.replaceAll("\\.", "");
        str = str.replaceAll("\\?", "");
        str = str.replaceAll("!", "");
        str = str.replaceAll(":", "");
        return str;
    }

}
