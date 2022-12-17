package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        var inputFile=  new File("sample.txt");
        var inputFile=  new File("aoc-4-input.txt");

        var assignmentPairs = 0;
        var overlappedAssignments = 0;

        try {
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()) {
                var currentLine = scanner.nextLine();

                var splitInput = currentLine.split("[,-]");

                var firstStart = Integer.valueOf(splitInput[0]);
                var firstEnd = Integer.valueOf(splitInput[1]);
                var secondStart = Integer.valueOf(splitInput[2]);
                var secondEnd = Integer.valueOf(splitInput[3]);

                if (contains(firstStart, firstEnd, secondStart, secondEnd) || contains(secondStart, secondEnd, firstStart, firstEnd)){
                    assignmentPairs++;
                }

                if (overLapped(firstStart, firstEnd, secondStart, secondEnd) || overLapped(secondStart, secondEnd, firstStart, firstEnd)){
                    overlappedAssignments++;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(assignmentPairs);
        System.out.println(overlappedAssignments);
    }

    private static boolean overLapped(Integer firstStart, Integer firstEnd, Integer secondStart, Integer secondEnd) {
        return !(firstEnd < secondStart || firstStart > secondEnd);
    }

    private static boolean contains(Integer firstStart, Integer firstEnd, Integer secondStart, Integer secondEnd) {
        return firstStart <= secondStart && firstEnd >= secondEnd;
    }
}