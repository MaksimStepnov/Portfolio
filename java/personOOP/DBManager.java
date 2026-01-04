package oop;

/**
 *
 * @author MaksimPC
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class DBManager
{
    private static final String DB_NAME = "restaurant_db";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql1";

    public static final String CUSTOMERS_TABLE_NAME = "customers";
    public static final String EMPLOYEES_TABLE_NAME = "employees";

    public static void initialize()
    {
        createDatabase();
        createTables();
    }

    public static Connection getServerConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("JDBC Driver not found");
        }
        catch (SQLException e)
        {
            System.err.println("SQL Error while connecting: " + e.getMessage());
        }
        return conn;
    }

    public static Connection getDBConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("JDBC Driver not found.");
        }
        catch (SQLException e)
        {
            System.err.println("SQL Error while connecting: " + e.getMessage());
        }
        return conn;
    }
    public static void createDatabase()
    {
        String sqlQuery = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

        try
        {
            Connection conn = getServerConnection();
            if (conn != null)
            {
                Statement s = conn.createStatement();
                s.executeUpdate(sqlQuery);
                System.out.println("Database " + DB_NAME + " created or already exists.");
            }
            else
            {
                System.err.println("Database connection is null");
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Exception: " + se.getMessage());
        }
    }
    private static void createTables()
    {
        String customersTableQuery =
                "CREATE TABLE IF NOT EXISTS " + CUSTOMERS_TABLE_NAME + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50), "
                + "phone VARCHAR(50), "
                + "email VARCHAR(50), "
                + "street VARCHAR(50), "
                + "city VARCHAR(50), "
                + "country VARCHAR(50), "
                + "membership BIGINT, "
                + "points BIGINT, "
                + "signup_on DATE"
                + ")";
        String employeesTableQuery =
                "CREATE TABLE IF NOT EXISTS " + EMPLOYEES_TABLE_NAME + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50), "
                + "phone VARCHAR(50), "
                + "email VARCHAR(50), "
                + "street VARCHAR(50), "
                + "city VARCHAR(50), "
                + "country VARCHAR(50), "
                + "supervisor VARCHAR(50)"
                + ")";

        try
        {
            Connection conn = getDBConnection();
            if (conn != null)
            {
                Statement s = conn.createStatement();
                s.executeUpdate(customersTableQuery);
                s.executeUpdate(employeesTableQuery);
                System.out.println("Tables created or already exist.");
                conn.close();
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Exception: " + se.getMessage());
        }
    }

    public static void main(String[] args)
    {
        DBManager.initialize();
    }
}

