package lesson2;

import java.sql.*;
import java.util.Optional;
import java.util.function.Consumer;

public class UsersApp {

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        start();
        stop();
    }

    public static void start() {
        try {
            connect();
            //createTable();
            //insertUsers();
            readData();
           //System.out.println(getNickByLoginAndPass("login1", "pass1"));
           getNickByLoginAndPass("login1", "pass1").ifPresent(nick -> System.out.println(nick));
           // dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        statement = connection.createStatement();
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists users (\n" +
                "    id integer primary key autoincrement not null,\n" +
                "    login text not null,\n" +
                "    password text not null,\n" +
                "    nick text not null\n" +
                ");");
    }

    private static void insertUsers() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (login, password, nick) " +
                        "values (?, ?, ?)")) {
            for (int i = 0; i < 3; i++) {
                ps.setString(1, "login" + (i + 1));
                ps.setString(2, "pass" + (i + 1));
                ps.setString(3, "nick" + (i + 1));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void readData() {
        try (ResultSet rs = statement.executeQuery("select * from users")) { //1 - based
            while (rs.next()) {
                System.out.println(rs.getString("login") +
                        " " + rs.getString("password") +
                        " " + rs.getString("nick"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("drop table users");
    }

    public static void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Optional<String> getNickByLoginAndPass(String login, String password) {
        try (ResultSet rs = statement.executeQuery(
                "select * from users " +
                        "where " +
                        " users.login = " + "'" + login + "'" + " AND " +
                        " users.password = " + "'" + password + "'" +
                        " LIMIT 1")) {
            //System.out.println(Optional.ofNullable(rs.getString(3)));
            return Optional.ofNullable(rs.getString(3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }


}
