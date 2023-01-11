package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.logging.Level;

public class SignUpGraphicController extends IController{
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField userField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField pswField;
    @FXML
    private Label userTypeAttrLbl;
    @FXML
    private TextField userTypeAttrField;        // it's the freshman if role = student, university if role = professor
    @FXML
    private ComboBox<String> roleCombo;
    @FXML
    private Label loginLbl;

    @Override
    public void initialize(){
        super.initialize();

        loginLbl.setOnMouseClicked(mouseEvent -> {
            goToPage("Login.fxml");
        });

        roleCombo.getItems().addAll(
                "Student",
                "Professor"
        );

        roleCombo.setOnAction(event -> {
            if(roleCombo.getValue().equals("Student"))
                studentSelected();
            else if(roleCombo.getValue().equals("Professor"))
                professorSelected();
        });

        studentSelected();

    }

    public void userSignup(ActionEvent event){
        try{
            String username = userField.getText();
            if(UserDAO.findUsername(username) == null){
                int result = UserDAO.addUser(
                        userField.getText(),
                        nameField.getText(),
                        surnameField.getText(),
                        emailField.getText(),
                        pswField.getText(),
                        roleCombo.getValue());

                if(result == 1){

                    /*TODO: save user as a Session Singleton to keep trace about the profile
                    *  (it is a method in IController because is shared with LoginGC)*/

                    goToPage("Home.fxml");
                }

            }
            else
                logger.log(Level.INFO, "Username already in use");
        }
        catch(Exception e){
            logger.log(Level.INFO, e.getMessage());
        }
    }

    private void studentSelected(){
        userTypeAttrLbl.setText("Freshman");
        userTypeAttrField.setPromptText("Freshman");
    }

    private void professorSelected(){
        userTypeAttrLbl.setText("University");
        userTypeAttrField.setPromptText("University");
    }
}
