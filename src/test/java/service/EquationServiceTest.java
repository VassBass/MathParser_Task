package service;

import model.Equation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.JDBC;
import repository.EquationRepository_sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EquationServiceTest {
    private static final String DB_URL = "jdbc:sqlite:testData.db";
    private EquationService service = new EquationService_impl(new EquationRepository_sqlite(DB_URL));

    private static Equation equation1(){
        Equation e = new Equation();
        e.setId(1);
        e.setEquation("2*2.10");
        e.setResult(4.20);
        return e;
    }

    private static Equation equation2(){
        Equation e = new Equation();
        e.setId(2);
        e.setEquation("2*2");
        e.setResult(4);
        return e;
    }

    private static Equation equation3(){
        Equation e = new Equation();
        e.setId(3);
        e.setEquation("5*(4-5) + 56");
        e.setResult(51D);
        return e;
    }

    private static final ArrayList<Equation> EQUATIONS = new ArrayList<>();

    @BeforeAll
    public static void init() {
        EQUATIONS.add(equation1());
        EQUATIONS.add(equation2());
        EQUATIONS.add(equation3());
    }

    @BeforeEach
    void setUp() {
        try {
            DriverManager.registerDriver(new JDBC());
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()){

                StringBuilder sql = new StringBuilder("DELETE FROM equations;");
                statement.execute(sql.toString());

                sql.setLength(0);
                sql.append("INSERT INTO equations (id, equation, result) VALUES ");
                for (Equation e : EQUATIONS){
                    sql.append("\n(")
                            .append(e.getId())
                            .append(", '").append(e.getEquation()).append("'")
                            .append(", ").append(e.getResult())
                            .append("),");
                }
                sql.deleteCharAt(sql.length()-1);
                sql.append(";");

                System.out.println(sql);
                statement.execute(sql.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {
        assertIterableEquals(EQUATIONS, service.getAll());
    }

    @Test
    void get() {
        assertEquals(equation1(), service.get(1));
    }

    @Test
    void set() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }
}