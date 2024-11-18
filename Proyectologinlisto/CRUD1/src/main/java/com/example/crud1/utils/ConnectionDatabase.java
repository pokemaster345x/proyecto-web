package com.example.crud1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionDatabase {
/*
    private static final String url = "jdbc:sqlite:C:/Users/ekava/OneDrive/Documentos/GitHub/proyecto-web/Proyectologinlisto/CRUD1/alumnos.db";
    // private static final String username = "postgres";

    private ConnectionDatabase() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

        private static final String url = "jdbc:postgresql://localhost:5432/Inmobiliaria_SA";
        private static final String username = "postgres";
        private static final String password = "postgres";

        private ConnectionDatabase() {}

        public static Connection getConnection() {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

