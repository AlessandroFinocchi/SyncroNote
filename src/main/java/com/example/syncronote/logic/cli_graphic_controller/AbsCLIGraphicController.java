package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.utilities.CLIPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class AbsCLIGraphicController implements CLIGraphicControllerInterface {

    Logger logger = Logger.getAnonymousLogger();

    protected int getMenuChoice(int min, int max){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            CLIPrinter.printMessage("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            CLIPrinter.printMessage("Invalid option\n");
        }

        return choice;
    }
}
