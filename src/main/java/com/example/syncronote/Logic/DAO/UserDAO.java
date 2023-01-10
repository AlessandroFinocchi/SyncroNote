package com.example.syncronote.Logic.DAO;

import com.example.syncronote.Logic.Enums.UserTypes;
import com.example.syncronote.Logic.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    /* BEGIN CODE SMELL */
    private static final String USER = "root";
    private static final String PASS = "Perarossa01?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/syncronotesdb";
    /* END CODE SMELL */

    public static List<User> GetAllUsers() throws Exception {
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            // Verifica se è stato restituito un insieme vuoto
            if(!rs.first()) {
                throw new Exception("Non esiste alcun utente chiamato ");
            }

            // Riposizionamento del cursore
            rs.first();
            do {
                // lettura delle colonne usando il nome delle colonne
                String nickname = rs.getString("Nickname");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");

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
}
