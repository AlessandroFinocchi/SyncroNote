package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.utilities.AbsAlertGenerator;
import com.example.syncronote.logic.utilities.NavigatorSingleton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbsGraphicController extends AbsAlertGenerator {
    @FXML
    private Pane titlePane; // Value injected by FXMLLoader
    @FXML
    private ImageView closeBtn; // Value injected by FXMLLoader
    @FXML
    private ImageView minimizeBtn; // Value injected by FXMLLoader
    protected static final String HOME = "Home.fxml";
    protected static final String LOGIN = "Login.fxml";
    protected static final String SIGN_UP = "SignUp.fxml";


    private double x;
    private double y;

    protected Logger logger = Logger.getAnonymousLogger();

    @Override
    public void initialize(){
        super.initialize();
        assert closeBtn != null : "fx:id=\"CloseBtn\" was not injected: check your FXML file.";
        assert minimizeBtn != null : "fx:id=\"MinimizeBtn\" was not injected: check your FXML file.";
        assert titlePane != null : "fx:id=\"TitlePane\" was not injected: check your FXML file.";


        titlePane.setOnMouseDragged(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().setX(mouseEvent.getScreenX() - x);
            n.getStg().setY(mouseEvent.getScreenY() - y);
        });

        titlePane.setOnMousePressed(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        closeBtn.setOnMouseClicked(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().close();
        });
        minimizeBtn.setOnMouseClicked(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().setIconified(true);
        });
    }

    protected void goToPage(String page) {
        try{
            NavigatorSingleton.getInstance().gotoPage(page);
        }
        catch (IOException e){
            logger.log(Level.INFO, e.getMessage());
        }
    }
}
