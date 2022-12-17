package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static final int GROUP_SIZE = 3;

    public static void main(String[] args) throws FileNotFoundException {
        var inputFile=  new File("aoc-3-input.txt");
//        var inputFile = new File("sample.txt");

        Scanner scanner = new Scanner(inputFile);

        var sumOfPriorities = 0;
        var sumOfGroupPriorities = 0;

        String[] groupLines = new String[GROUP_SIZE];
        var groupCounter = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(line);

            sumOfPriorities += getCurrentElfPriority(line);

            groupLines[groupCounter] = line;
            groupCounter++;

            if (groupCounter == GROUP_SIZE) {
                sumOfGroupPriorities += getGroupPriority(groupLines);
                groupCounter = 0;
            }

        }

        System.out.println(sumOfPriorities);
        System.out.println(sumOfGroupPriorities);
    }

    private static int getGroupPriority(String[] groupLines) {

        var groupPriority = getItemTypes(groupLines[0]);
        var secondElf = getItemTypes(groupLines[1]);
        var thirdElf = getItemTypes(groupLines[2]);

        groupPriority.retainAll(secondElf);
        groupPriority.retainAll(thirdElf);

        return calculatePriority((byte)groupPriority.toArray()[0]);
    }

    private static int getCurrentElfPriority(String line) {
        var currentElfPriority = 0;

        var firstRuckSack = getItemTypes(line.substring(0, line.length() / 2));
        var secondRuckSack = getItemTypes(line.substring(line.length() / 2));

        firstRuckSack.retainAll(secondRuckSack);

        for (var currentItem :
                firstRuckSack.stream().toList()) {
            var priority = calculatePriority(currentItem);
            currentElfPriority += priority;
        }

        return currentElfPriority;
    }

    private static int calculatePriority(Byte currentItem) {
        return currentItem >= 'a' && currentItem <= 'z' ? currentItem - 'a' + 1 : currentItem - 'A' + 27;
    }

    private static Set<Byte> getItemTypes(String substring) {
        Set<Byte> resultSet = new HashSet<>();

        for (var current :
                substring.getBytes()) {
            resultSet.add(current);
        }

        return resultSet;
    }
}