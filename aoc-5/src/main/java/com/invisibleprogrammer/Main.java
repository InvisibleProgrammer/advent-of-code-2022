package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static final int MAX_STACKS = 10;

    public static void main(String[] args) throws FileNotFoundException {
        var inputFile=  new File("aoc-5-input.txt");
//        var inputFile = new File("sample.txt");

        Deque<Character>[] stacks = new ArrayDeque[MAX_STACKS];
        for (int i = 0; i < MAX_STACKS; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        Deque<Character>[] secondStacks = new ArrayDeque[MAX_STACKS];
        for (int i = 0; i < MAX_STACKS; i++) {
            secondStacks[i] = new ArrayDeque<>();
        }

        Scanner scanner = new Scanner(inputFile);

        // Phase I: read the current state
        while (scanner.hasNext()) {
            var currentLine = scanner.nextLine();

            if (!currentLine.isEmpty()){
                var position = 1;
                var stackPosition = 0;
                while (position < currentLine.length()) {
                    var current = currentLine.charAt(position);

                    if (Character.isLetter(current)){
                        stacks[stackPosition].addFirst(current);
                        secondStacks[stackPosition].addFirst(current);
                    }

                    stackPosition++;
                    position += 4;
                }
            } else {
                break;
            }
        }

        // Phase II: rearrangement
        while (scanner.hasNext()){
            var currentLine = scanner.nextLine();

            var split = currentLine.split(" ");

            var amount = Integer.valueOf(split[1]);
            var from = Integer.valueOf(split[3]) - 1;
            var to = Integer.valueOf(split[5]) - 1;

//            printStack(stacks[from]);
//            printStack(stacks[to]);

            // Part I:
            for (int i = 0; i < amount; i++){
                var crate= stacks[from].removeLast();
                stacks[to].addLast(crate);
            }

            // Part II:
            var packageToMove = new char[amount];
            for (int i = 0; i < amount; i++){
                packageToMove[i] = secondStacks[from].removeLast();
            }

            for (int i = amount - 1; i >= 0; i--){
                secondStacks[to].addLast(packageToMove[i]);
            }
        }

//        System.out.println("Stack sizes:");
//        for (int i = 0; i < MAX_STACKS; i++) {
//            System.out.println(stacks[i].size());
//            printStack(stacks[i]);
//        }

        System.out.println("Result first part:");
        for (int i = 0; i < MAX_STACKS; i++) {
            if (stacks[i].size() > 0){
                System.out.print(stacks[i].getLast());
            }
        }

        System.out.println();

        System.out.println("Result second part:");
        for (int i = 0; i < MAX_STACKS; i++) {
            if (stacks[i].size() > 0){
                System.out.print(secondStacks[i].getLast());
            }
        }
    }

    private static void printStack(Deque<Character> stack) {
        for (var character :
                stack) {
            System.out.print(character);
        }
        System.out.println();
    }
}