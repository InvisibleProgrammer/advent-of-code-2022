package com.invisibleprogrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    final static int ROCK = 1;
    final static int PAPER = 2;
    final static int SCISSORS = 3;

    public static void main(String[] args) {
        var inputFile=  new File("aoc-2-input.txt");

        var firstScore = 0;
        var secondScore = 0;

        try {
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()){
                String currentRound = scanner.nextLine();

                var opponent = currentRound.charAt(0) - 'A' + 1;
                var you = currentRound.charAt(2) - 'X' + 1;

                firstScore += calculateFirstScore(opponent, you);
                secondScore += calculateSecondScore(opponent, you);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(firstScore);
        System.out.println(secondScore);
    }

    private static int calculateFirstScore(int opponent, int you) {
        var score = you;

        if (opponent == you){
            return score + 3;
        }

        if (
                opponent == ROCK && you == PAPER || opponent == PAPER && you == SCISSORS || opponent == SCISSORS && you == ROCK
        ){
            return score + 6;
        }

        return score;
    }

    private static int calculateSecondScore(int opponent, int you) {
        final int NEED_LOOSE = 1;
        final int NEED_DRAW = 2;
        final int NEED_WIN = 3;

        int score = 0;

        switch (you) {
            case NEED_WIN:
                score += 6;

                switch (opponent) {
                    case ROCK -> score += PAPER;
                    case PAPER -> score += SCISSORS;
                    default -> score += ROCK;
                }

                break;
            case NEED_DRAW:
                score += 3;

                score += opponent;

                break;
            case NEED_LOOSE:
                switch (opponent) {
                    case ROCK -> score += SCISSORS;
                    case PAPER -> score += ROCK;
                    default -> score += PAPER;
                }
                break;
        }

        return score;
    }

}