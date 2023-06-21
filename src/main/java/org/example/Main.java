package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);

        // Creating animal objects
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Prey antelope = new Antelope(x, y, 'A');
            board.placeAnimal(antelope);
        }

        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Prey zebra = new Zebra(x, y, 'Z');
            board.placeAnimal(zebra);
        }

        int x = random.nextInt(10);
        int y = random.nextInt(10);
        Hunter hyena = new Hyena(x, y, 'H');
        board.placeAnimal(hyena);

        x = random.nextInt(10);
        y = random.nextInt(10);
        Hunter lion = new Lion(x, y, 'L');
        board.placeAnimal(lion);

        // Before movement
        board.clearScreen();
        System.out.println("Board before movement:");
        board.printBoard();
        board.BoardSleep();
        board.clearScreen();

        int iteration = 0;
        int preyPopulation = 0;
        int hunterPopulation = 0;
        while (true) {  // Run the simulation until explicitly terminated
            for (Animal animal : board.getAnimals()) {
                animal.move(board);
            }
            board.updateBoard();
            board.printBoard();
            board.BoardSleep();
            board.clearScreen();
            iteration++;

            preyPopulation = 0;
            hunterPopulation = 0;
            for (Animal animal : board.getAnimals()) {
                if (animal instanceof Prey) {
                    preyPopulation++;
                }
                if (animal instanceof Hunter) {
                    hunterPopulation++;
                }
            }

            if (preyPopulation == 0) {
                break;  // Stop the simulation if all prey are dead
            }
        }

        // After movement
        board.printBoard();
        board.saveSimulationResult("simulation_result.csv");
        System.out.printf("Population after movement:\nHunters: %d\nPrey: %d", hunterPopulation, preyPopulation);
        System.out.println();
        System.out.println("Simulation ended after " + iteration + " iterations.");
    }
}
