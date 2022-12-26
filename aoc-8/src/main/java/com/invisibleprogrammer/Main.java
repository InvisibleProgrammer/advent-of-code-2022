package com.invisibleprogrammer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static final Direction[] directions;
    static int steps;

    static {
        directions = new Direction[]{
                new Direction(0, 1),
                new Direction(1, 0),
                new Direction(0, -1),
                new Direction(-1, 0)
        };
    }

    public static void main(String[] args) throws IOException {
//        var inputFile = "sample.txt";
        var inputFile=  "input.txt";

        var input = Files.readAllLines(Path.of(inputFile));

        var size = input.get(0).length();

        var map = new byte[size][size];
        var visibleTrees = new byte[size][size];

        int lineCounter = 0;
        for (var line : input) {
            map[lineCounter] = line.getBytes();
            lineCounter++;
        }


        int previousMax;

        // iterate from the top
        for (int i = 0; i < size; i++) {
            previousMax = -1;

            for (int j = 0; j < size; j++) {

                if (map[j][i] > previousMax) {
                    visibleTrees[j][i] = 1;
                    previousMax = map[j][i];
                }
            }
        }

        // iterate from the bottom
        for (int i = 0; i < size; i++) {
            previousMax = -1;
            for (int j = size - 1; j >= 0; j--) {

                if (map[j][i] > previousMax) {
                    visibleTrees[j][i] = 1;
                    previousMax = map[j][i];
                }
            }
        }


        // iterate from the left
        for (int i = 0; i < size; i++) {
            previousMax = -1;
            for (int j = 0; j < size; j++) {

                if (map[i][j] > previousMax) {
                    visibleTrees[i][j] = 1;
                    previousMax = map[i][j];
                }
            }
        }

        // iterate from the right
        for (int i = 0; i < size; i++) {
            previousMax = -1;
            for (int j = size - 1; j >= 0; j--) {

                if (map[i][j] > previousMax) {
                    visibleTrees[i][j] = 1;
                    previousMax = map[i][j];
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                System.out.print((char) map[i][j]);
            }
            System.out.println();
        }

        var countOfVisibleTrees = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                countOfVisibleTrees += visibleTrees[i][j];

                System.out.print(visibleTrees[i][j]);
            }
            System.out.println();
        }


        // Part II
        int maxScenicScore = 0;


        for (int i = 1; i < size - 1; i++){
            for (int j = 1; j < size - 1; j++){

                var scenicScore = calculateScenicScore(i, j, size, map);

                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }

//        System.out.println(calculateScenicScore(3, 2, size, map));
        System.out.println("Visible trees: " + countOfVisibleTrees);
        System.out.println("Best scenic score: " + maxScenicScore);

        System.out.println("Steps: " + steps); // That is the area to improve
    }

    private static int calculateScenicScore(int i, int j, int size, byte[][] map) {
        var scenicScore = 1;

        for (var direction : directions) {
            int distance = 0;

            var posX = i + direction.horizontal();
            var posY = j + direction.vertical();

            while (posX >= 0 && posX < size && posY >= 0 && posY < size
            ){
                distance++;
                steps++;

                if (map[i][j] <= map[posX][posY]){
                    break;
                }

                posX += direction.horizontal();
                posY += direction.vertical();
            }

            scenicScore *= distance;
        }

        return scenicScore;
    }
}
