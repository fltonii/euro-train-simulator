package com.company;

import java.util.Scanner;

public class Simulation {
    private static int minutesPassed = 0;
    private static Railway railway;

    public static void main(String[] args) {
        System.out.println("Inicializando simulação. Pressione q para finalizar.");
        railway = generateRailway();

        boolean shouldRepeat = true;
        Scanner scanner = new Scanner(System.in);
        while (shouldRepeat) {
            if(minutesPassed == 510) {
                shouldRepeat = false;
                continue;
            }
            spawnTrains();
            System.out.println(railway);
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("q")) {
                railway.reportBoardingLog();
                shouldRepeat = false;
                continue;
            }
            railway.moveTrains();
            tick();
        }
        railway.reportBoardingLog();
        scanner.close();
    }

    private static Railway generateRailway() {
        int stationCount = (int) (Math.random() * 20) + 10;
        return new Railway(stationCount);
    }

    private static void tick() {
        minutesPassed++;
    }

    private static void spawnTrains() {
        if (shouldSpawnTrain()) {
            System.out.println(minutesPassed);
            railway.spawnTrains();
        }
    }

    private static boolean shouldSpawnTrain() {
        return minutesPassed == 0 || minutesPassed % 30 == 0;
    }

}
