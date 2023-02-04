package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.exceptions.DAOException;
import java.sql.SQLException;

public interface GenericProcedureDAO<P> {

    P execute(Object... params) throws DAOException, SQLException;

}
