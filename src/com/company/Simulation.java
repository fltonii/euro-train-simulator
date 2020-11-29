package com.company;

import java.util.Scanner;

public class Simulation {
    private static int secondsPassed = 0;
    private static Railway railway;

    public static void main(String[] args) {
        railway = generateRailway();

        System.out.println(railway);
        boolean shouldRepeat = true;
        Scanner scanner = new Scanner(System.in);
        while (shouldRepeat) {
            scanner.nextLine();
            spawnTrains();
            tick();
        }
    }

    private static Railway generateRailway() {
        int stationCount = (int) (Math.random() * 20) + 10;
        return new Railway(stationCount);
    }

    private static void tick() {
        secondsPassed++;
    }

    private static void spawnTrains() {
        if (shouldSpawnTrain()) {
            railway.spawnTrains();
            System.out.println(secondsPassed + "  Choo Choo");
        }
    }

    private static boolean shouldSpawnTrain() {
        return secondsPassed == 0 || secondsPassed % 30 == 0;
    }

}
