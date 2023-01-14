package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.logging.Level;

public class SignUpGraphicController extends IGraphicController {
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

    private SignUpController signUpController;

    private static final String STUDENT = "Student";
    private static final String PROFESSOR = "Professor";
    @Override
    public void initialize(){
        super.initialize();

        signUpController = new SignUpController();

        loginLbl.setOnMouseClicked(mouseEvent -> {
            goToPage("Login.fxml");
        });

        roleCombo.getItems().addAll(
                STUDENT,
                PROFESSOR
        );

        roleCombo.setOnAction(event -> {
            if(roleCombo.getValue().equals(STUDENT))
                studentSelected();
            else if(roleCombo.getValue().equals(PROFESSOR))
                professorSelected();
        });

        studentSelected();
        roleCombo.setValue(STUDENT);

    }

    public void userSignup(ActionEvent event){
        try{
            int result = signUpController.signUp(
                    userField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    emailField.getText(),
                    pswField.getText(),
                    roleCombo.getValue());

            if(result == 1){
                goToPage("Home.fxml");
            }

            else{
                logger.log(Level.INFO, "Username already in use");
                errorAlert.setTitle("Sign up error");
                errorAlert.setHeaderText("Username already in use");
                errorAlert.show();
            }
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
