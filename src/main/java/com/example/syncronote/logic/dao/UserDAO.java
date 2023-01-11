package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    /* BEGIN CODE SMELL */
    private static final String USER = "root";
    private static final String PASS = "Perarossa01?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/syncronotesdb";
    /* END CODE SMELL */

    private static final String USERNAME = "Nickname";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String PSW = "Password";
    private static final String ROLE = "Role";

    private static Logger logger = Logger.getAnonymousLogger();

    public static List<User> getAllUsers() throws Exception {
        Statement stmt = null;
        Connection conn = null;
        List<User> userList = new ArrayList<>();

        try {
            // CODE SMELL
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Creazione dello statement ed esecuzione della query
            // TYPE_SCROLL_INSENSITIVE: il result set può essere scandito, ma non è sensibile a variazioni nei dati nel db
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // CODE SMELL
            ResultSet rs = stmt.executeQuery("SELECT * FROM User;");

            // Verifica se è stato restituito un insieme vuoto
            if(!rs.first()) {
                throw new Exception("Non esiste alcun utente chiamato ");
            }

            // Riposizionamento del cursore
            rs.first();
            do {
                // lettura delle colonne usando il nome delle colonne
                String nickname = rs.getString(USERNAME);
                String name = rs.getString(NAME);
                String surname = rs.getString(SURNAME);

                userList.add(new User(nickname, name, surname,"", UserTypes.STUDENT));
            } while(rs.next());

            // Chiusura del result set e rilascio delle risorse
            rs.close();
        } finally {
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        }

        return userList;
    }

    public static User findUser(String username, String password) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        User user = null;

        try {
            // CODE SMELL
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Creazione dello statement ed esecuzione della query
            // TYPE_SCROLL_INSENSITIVE: il result set può essere scandito, ma non è sensibile a variazioni nei dati nel db
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // CODE SMELL

            ResultSet rs = stmt.executeQuery("SELECT * FROM User " +
                    "WHERE " + USERNAME + " = '" + username + "' AND " +
                     PSW + " = '" + password + "';");

            // Verifica se è stato restituito un insieme vuoto
            if(!rs.first()) {
                throw new Exception("Non esiste alcun utente chiamato ");
            }

            // Riposizionamento del cursore
            rs.first();

            UserTypes type;

            if(rs.getString(ROLE).equals("Student"))
                type = UserTypes.STUDENT;
            else if (rs.getString(ROLE).equals("Professor"))
                type = UserTypes.PROFESSOR;
            else
                type = UserTypes.ADMIN;

            user = new User(
                    rs.getString(USERNAME),
                    rs.getString(NAME),
                    rs.getString(SURNAME),
                    rs.getString(EMAIL),
                    type);

            // Chiusura del result set e rilascio delle risorse
            rs.close();
        } finally {
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        }

        return user;
    }

    public static User findUsername(String username) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        User user = null;

        try {
            // CODE SMELL
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Creazione dello statement ed esecuzione della query
            // TYPE_SCROLL_INSENSITIVE: il result set può essere scandito, ma non è sensibile a variazioni nei dati nel db
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // CODE SMELL

            ResultSet rs = stmt.executeQuery("SELECT * FROM User " +
                    "WHERE Nickname = '" + username + "';");

            // Verifica se è stato restituito un insieme vuoto
            if(!rs.first()) {
                return null;
            }

            // Riposizionamento del cursore
            rs.first();

            UserTypes type;

            if(rs.getString(ROLE).equals("Student"))
                type = UserTypes.STUDENT;
            else if (rs.getString(ROLE).equals("Professor"))
                type = UserTypes.PROFESSOR;
            else
                type = UserTypes.ADMIN;

            user = new User(
                    rs.getString(USERNAME),
                    rs.getString(NAME),
                    rs.getString(SURNAME),
                    rs.getString(EMAIL),
                    type);

            // Chiusura del result set e rilascio delle risorse
            rs.close();
        } finally {
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        }

        return user;
    }

    public static int addUser(String username, String name, String surname, String email, String psw, String role) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int result = -1;

        try {
            // CODE SMELL
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Creazione dello statement ed esecuzione della query
            // TYPE_SCROLL_INSENSITIVE: il result set può essere scandito, ma non è sensibile a variazioni nei dati nel db
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // CODE SMELL

            String query = "INSERT INTO User (" + USERNAME + ", " + NAME +", " + SURNAME +", " + EMAIL + ", " + PSW + ", " + ROLE + ")"
                    + " VALUES('" + username + "','" + name + "','" + surname + "','" + email + "','" + psw + "','" + role + "')";


            result = stmt.executeUpdate(query);

            if (result > 0) {
                logger.log(Level.INFO, "ROW INSERTED");
            } else {
                logger.log(Level.INFO, "ROW NOT INSERTED");
            }

        } finally {
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        }

        return result;
    }
}
