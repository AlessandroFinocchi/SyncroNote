package com.example.syncronote.logic.cli_graphic_controller;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class AbsCLIGraphicController implements CLIGraphicControllerInterface {

    Logger logger = Logger.getAnonymousLogger();

    protected int getMenuChoice(int min, int max){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }
}
