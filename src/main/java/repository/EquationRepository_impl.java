package repository;

import model.Equation;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

public class EquationRepository_impl implements EquationRepository {
    private static String DB_URL = "jdbc:sqlite:Data.db";

    /**
     * Registers a DB-driver and connects to it
     * @return Connection Object
     * @throws SQLException if DB wasn't found
     * @see #DB_URL
     */
    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Uses URL of DB file by default = "jdbc:sqlite:Data.db"
     * @see #DB_URL
     */
    public EquationRepository_impl(){
        createTableIfNotExists();
    }

    /**
     * @param dbUrl set as default DB URL
     * @see #DB_URL
     */
    public EquationRepository_impl(String dbUrl){
        DB_URL = dbUrl;
        createTableIfNotExists();
    }

    /**
     * Creates DB file if not exists and table "equations" to keep equations and results
     * @see #DB_URL
     */
    private void createTableIfNotExists(){
        String sql = "CREATE TABLE IF NOT EXISTS equations ("
                + "id integer NOT NULL UNIQUE"
                + ", equation text NOT NULL"
                + ", result real NOT NULL"
                + ", PRIMARY KEY (\"id\" AUTOINCREMENT)"
                + ");";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return list of equations from DB or empty list if DB wasn't found
     * @see #DB_URL
     */
    @Override
    public ArrayList<Equation> getAll() {
        ArrayList<Equation> equations = new ArrayList<>();
        String sql = "SELECT * FROM equations;";
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Equation equation = new Equation();
                equation.setId(resultSet.getInt("id"));
                equation.setEquation(resultSet.getString("equation"));
                equation.setResult(resultSet.getDouble("result"));
                equations.add(equation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return equations;
    }

    /**
     * @param id of equation who need to search
     * @return equation from db with this id. null if equation with this id not found in DB
     * or DB wasn't found
     * @see #DB_URL
     */
    @Override
    public Equation get(int id) {
        String sql = "SELECT 1 FROM equations WHERE id = " + id + ";";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            if (resultSet.next()) {
                Equation equation = new Equation();
                equation.setId(resultSet.getInt("id"));
                equation.setEquation(resultSet.getString("equation"));
                equation.setResult(resultSet.getDouble("result"));
                return equation;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Adds new equation to DB
     * @param equation to add
     * @return true if equation was added or false if DB wasn't found
     * @see #DB_URL
     */
    @Override
    public boolean add(Equation equation) {
        String sql = "INSERT INTO equations (equation, result)"
                + "VALUES ("
                + "'" + equation.getEquation() + "'"
                + ", " + equation.getResult()
                + ");";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sql);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Changes equation from DB with id of equation from @param
     * @param equation to change
     * @return true if equation was changed. false if equation with this id wasn't found
     * or DB wasn't found
     * @see #DB_URL
     */
    @Override
    public boolean set(Equation equation) {
        String sql = "UPDATE equations SET "
                + "equation = '" + equation.getEquation() + "'"
                + ", result = " + equation.getResult()
                + " WHERE id = " + equation.getId() + ";";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sql);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Removes equation with this id from DB
     * @param id of equation who need to removed
     * @return true if equation was removed. false if equation with this id wasn't found
     * or DB wasn't found
     * @see #DB_URL
     */
    @Override
    public boolean remove(int id) {
        String sql = "DELETE FROM equations WHERE id = " + id + ";";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sql);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
