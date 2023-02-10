package com.example.syncronote;

import com.example.syncronote.logic.cli_graphic_controller.LoginCLIGraphicController;

public class CLIMain {
    public static void main(String[] args) {
        LoginCLIGraphicController applicationController = new LoginCLIGraphicController();
        applicationController.start();
    }
}