package repository;

import model.Equation;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquationRepository_impl implements EquationRepository {
    private static String DB_URL = "jdbc:sqlite:Data.db";

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        return DriverManager.getConnection(DB_URL);
    }

    public EquationRepository_impl(){
        createTableIfNotExists();
    }

    public EquationRepository_impl(String dbUrl){
        DB_URL = dbUrl;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String sql = "CREATE TABLE IF NOT EXISTS equations ("
                + "id integer NOT NULL UNIQUE"
                + ", equation text NOT NULL"
                + ", result real NOT NULL"
                + ", PRIMARY KEY (\"id\")"
                + ");";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Equation> getAll() {
        return null;
    }

    @Override
    public Equation get(int id) {
        return null;
    }

    @Override
    public boolean add(Equation equation) {
        return false;
    }

    @Override
    public boolean set(Equation equation) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
