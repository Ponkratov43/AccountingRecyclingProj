package sample.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static final String DATABASE_URL =
            "jdbc:h2:file:./AccountingRecycling/db;CASE_INSENSITIVE_IDENTIFIERS=true;DATABASE_TO_UPPER=false";

    private static final String USER = "sa";
    private static final String PASS = "";

    private static final String createTotalTable = "CREATE TABLE total (" +
            "'id' int primary key auto_increment," +
            "'date' date NOT NULL," +
            "'sbl' double," +
            "'aluminium' double," +
            "'copper' double," +
            "'brass' double," +
            "'glass' double," +
            "'paper' double," +
            "'radiators' double," +
            "'accumulators' double," +
            "'sum' double" +
            ");";

    private static final String createSettingTable = "CREATE TABLE setting (\n" +
            "'id' int primary key auto_increment," +
            "'sbl' double," +
            "'aluminium double," +
            "'copper' double," +
            "'brass' double," +
            "'glass' double," +
            "'paper' double," +
            "'radiators' double," +
            "'accumulators' double" +
            ");";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    static {
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTotalTable);
            statement.execute(createSettingTable);
        } catch (SQLException sql) {
            System.err.println("Table exists");
        }
    }

    public static Connection getConnection() {

        return connection;
    }
}
