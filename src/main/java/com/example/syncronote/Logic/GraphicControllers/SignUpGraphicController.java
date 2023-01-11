package com.example.syncronote.Logic.GraphicControllers;

import com.example.syncronote.Logic.DAO.UserDAO;
import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SignUpGraphicController extends IController{
    @FXML
    private TextField NameField;
    @FXML
    private TextField SurnameField;
    @FXML
    private TextField UserField;
    @FXML
    private TextField EmailField;
    @FXML
    private PasswordField PswField;
    @FXML
    private Label UserTypeAttrLbl;
    @FXML
    private TextField UserTypeAttrField;        // it's the freshman if role = student, university if role = professor
    @FXML
    private ComboBox<String> RoleCombo;
    @FXML
    private Button SignupBtn;
    @FXML
    private Label LoginLbl;

    @Override
    public void initialize(){
        super.initialize();

        LoginLbl.setOnMouseClicked(mouseEvent -> {
            goToPage("Login.fxml");
        });

        RoleCombo.getItems().addAll(
                "Student",
                "Professor"
        );

        RoleCombo.setOnAction(event -> {
            if(RoleCombo.getValue().equals("Student"))
                StudentSelected();
            else if(RoleCombo.getValue().equals("Professor"))
                ProfessorSelected();
        });

        StudentSelected();

    }

    public void UserSignup(ActionEvent event){
        try{
            String username = UserField.getText();
            if(UserDAO.findUsername(username) == null){
                int result = UserDAO.addUser(
                        UserField.getText(),
                        NameField.getText(),
                        SurnameField.getText(),
                        EmailField.getText(),
                        PswField.getText(),
                        RoleCombo.getValue());

                if(result == 1){

                    /*TODO: save user as a Session Singleton to keep trace about the profile
                    *  (it is a method in IController because is shared with LoginGC)*/

                    goToPage("Home.fxml");
                }

            }
            else
                System.out.println("Username already in use");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void StudentSelected(){
        UserTypeAttrLbl.setText("Freshman");
        UserTypeAttrField.setPromptText("Freshman");
    }

    private void ProfessorSelected(){
        UserTypeAttrLbl.setText("University");
        UserTypeAttrField.setPromptText("University");
    }
}
