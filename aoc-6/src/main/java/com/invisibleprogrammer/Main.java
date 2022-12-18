package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var inputFile = new File("aoc-6-input.txt");

        try {
            Scanner scanner = new Scanner(inputFile);

            var currentLine = scanner.nextLine();

            printUniquePatterPosition(4, currentLine);
            printUniquePatterPosition(14, currentLine);

            System.out.println(currentLine);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printUniquePatterPosition(int uniqueCharactersNeeded, String currentLine) {
        for (int i = uniqueCharactersNeeded; i < currentLine.length(); i++) {
            if (allUnique(currentLine.substring(i - uniqueCharactersNeeded, i))) {
                System.out.println(i);
                break;
            }
        }
    }

    private static boolean allUnique(String substring) {
        for (int i = 0; i < substring.length(); i++) {
            for (int j = 0; j < substring.length(); j++) {
                if (i == j) {
                    continue;
                }

                if (substring.charAt(i) == substring.charAt(j)) {
                    return false;
                }
            }
        }

        System.out.println("Found: " + substring);
        return true;
    }
}