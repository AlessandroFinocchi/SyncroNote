package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.SignUpController;
import com.example.syncronote.logic.beans.SignupBean;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
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
        String signUpErrorString = "Sign up error";
        try{
            SignupBean signupBean = new SignupBean(
                    userField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    emailField.getText(),
                    pswField.getText(),
                    roleCombo.getValue(),
                    userTypeAttrField.getText());
            int result = signUpController.signUp(signupBean);

            if(result == 1){
                goToPage("Home.fxml");
            }
            else if(result == -1){
                logger.log(Level.INFO, "Username already in use");
                showAlert(signUpErrorString, "Username already in use");
            }
            else {
                logger.log(Level.INFO, "Unknown error");
                showAlert(signUpErrorString, "Unknown error");
            }
        }
        catch (InvalidFormatException | SessionUserException e){
            logger.log(Level.INFO, e.getMessage());
            showAlert(signUpErrorString, e.getMessage());
        }
        catch (DAOException | SQLException e){
            logger.log(Level.INFO, "Error in database");
            showAlert(signUpErrorString, "Error in database");
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
