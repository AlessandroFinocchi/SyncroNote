package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.exceptions.DAOException;
import java.sql.SQLException;

public abstract class AbsPublicationController extends AbsLoggedController{

    public abstract void publishNote(PublicationStudentBean publicationStudentBean) throws DAOException, SQLException;

}