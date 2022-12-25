package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int limit = 100000;

//        var inputFile=  new File("sample.txt");
        var inputFile = new File("aoc-7-input.txt");

        Directory root = new Directory("/", null);
        Directory current = root;

        List<Directory> allDirectories = new ArrayList<>();
        allDirectories.add(root);

        try {
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()) {
                var currentLine = scanner.nextLine();

                if (currentLine.startsWith("ls ")) {
                    continue;
                }

                var splitLine = currentLine.split(" ");

                if (currentLine.startsWith("$ cd")) {
                    var name = splitLine[2];

                    if (name.equals("..")) {
                        current = current.getRoot();
                    } else {
                        if (!name.equals("/")) {
                            current = current.getSubDirectories().stream()
                                    .filter(d -> d.getName().equals(name))
                                    .findFirst().orElse(root);
                        }
                    }
                } else if (currentLine.startsWith("dir ")) {
                    var newDir = new Directory(splitLine[1], current);
                    current.addSubDirectory(newDir);
                    allDirectories.add(newDir);
                } else if (Character.isDigit(splitLine[0].charAt(0))) {
                    var size = Integer.parseInt(splitLine[0]);

                    current.addFile(size);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Calculates all sizes. We don't need to keep the directory sizes up-to-date, we can just calculate them once.
        var used = root.getSize();

        var sum = allDirectories.stream()
                .map(Directory::getSize)
                .filter(size -> size < limit)
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);

        // Part II:
        var total = 70000000;
        var need = 30000000;

        var needToFreeUp = need - (total - used);

        var result = allDirectories.stream()
                .filter(directory -> directory.getSize() > needToFreeUp)
                .min(Comparator.comparingInt(Directory::getSize));

        System.out.println("Result: " + result.orElse(root).getSize());
    }
}
