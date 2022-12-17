package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var inputFile=  new File("aoc-1-input.txt");

        var currentElf = 0;
        var badassElf = 0;
        var secondInCommand = 0;
        var theUglyThird = 0;

        try {
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()){
                var currentLine = scanner.nextLine();

                if (currentLine.isEmpty()){
                    if (currentElf > badassElf){
                        theUglyThird = secondInCommand;
                        secondInCommand = badassElf;
                        badassElf = currentElf;
                    } else if (currentElf > secondInCommand){
                        theUglyThird = secondInCommand;
                        secondInCommand = currentElf;
                    } else if (currentElf > theUglyThird){
                        theUglyThird = currentElf;
                    }

                    currentElf = 0;
                } else {

                    currentElf += Integer.parseInt(currentLine);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(badassElf + secondInCommand + theUglyThird);
    }
}