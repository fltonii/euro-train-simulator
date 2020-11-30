package com.company;

import java.util.Scanner;

public class Simulation {
    private static int secondsPassed = 0;
    private static Railway railway;

    public static void main(String[] args) {
        System.out.println("Inicializando simulação. Pressione q para finalizar.");
        railway = generateRailway();

        boolean shouldRepeat = true;
        Scanner scanner = new Scanner(System.in);
        while (shouldRepeat) {
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
        scanner.close();
    }

    private static Railway generateRailway() {
        int stationCount = (int) (Math.random() * 20) + 10;
        return new Railway(8);
    }

    private static void tick() {
        secondsPassed++;
    }

    private static void spawnTrains() {
        if (shouldSpawnTrain()) {
            railway.spawnTrains();
        }
    }

    private static boolean shouldSpawnTrain() {
        return secondsPassed == 0 || secondsPassed % 30 == 0;
    }

}
