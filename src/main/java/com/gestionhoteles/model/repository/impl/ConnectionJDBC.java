package com.gestionhoteles.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public ConnectionJDBC(){
    }

    public Connection connectDB() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conectado a la base de datos");
            return conn;
        } catch (SQLException var2) {
            SQLException ex = var2;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println();
            }

            throw new SQLException();
        } catch (Exception var3) {
            throw new SQLException();
        }
    }

    public void disconnectDB(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println();
            }
        }

    }
}
