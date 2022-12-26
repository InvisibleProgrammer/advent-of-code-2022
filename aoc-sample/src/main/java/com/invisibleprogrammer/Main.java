package com.invisibleprogrammer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        var inputFile=  "sample.txt";
//        var inputFile=  "input.txt";

        var lines = Files.readAllLines(Path.of(inputFile));

        for (var line : lines) {
            System.out.println(line);
        }
    }
}
