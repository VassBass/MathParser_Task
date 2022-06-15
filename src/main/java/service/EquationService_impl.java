package service;

import model.Equation;
import repository.EquationRepository;
import repository.EquationRepository_sqlite;

import java.util.ArrayList;

/**
 * Implementation of {@link EquationService} by using {@link EquationRepository} to storage of equation
 */
public class EquationService_impl implements EquationService {
    private final EquationRepository repository;

    public EquationService_impl(){
        this.repository = new EquationRepository_sqlite();
    }

    public EquationService_impl(EquationRepository repository){
        this.repository = repository;
    }

    /**
     * @return list of equations from repository
     *
     * @see EquationRepository#getAll()
     * @see EquationRepository_sqlite#getAll()
     */
    @Override
    public ArrayList<Equation> getAll() {
        return repository.getAll();
    }

    /**
     * @param id of equation who need to search in repository
     *
     * @return equation with this id from repository
     * null if equation with this id not found
     *
     * @see EquationRepository#get(int)
     * @see EquationRepository_sqlite#get(int)
     */
    @Override
    public Equation get(int id) {
        return repository.get(id);
    }

    /**
     * @param condition of search (<, <=, =, >=, >)
     *
     * @param result number for compare by condition of search with equations results
     *
     * @return list of equations that match the search terms
     *
     * @see EquationRepository#get(String, double)
     * @see EquationRepository_sqlite#get(String, double)
     */
    @Override
    public ArrayList<Equation> get(String condition, double result) {
        return repository.get(condition, result);
    }

    /**
     * Changes equation with id of equation from @param
     *
     * @param equation to change
     *
     * @return true if equation was changed
     * false if not
     *
     * @see EquationRepository#set(Equation)
     * @see EquationRepository_sqlite#set(Equation)
     */
    @Override
    public boolean set(Equation equation) {
        return repository.set(equation);
    }

    /**
     * Adds new equation
     *
     * @param equation to add
     *
     * @return id of added equation
     * -1 if equation wasn't added
     *
     * @see EquationRepository#add(Equation)
     * @see EquationRepository_sqlite#add(Equation)
     */
    @Override
    public int add(Equation equation) {
        return repository.add(equation);
    }

    /**
     * Removes equation with this id
     *
     * @param id of equation who need to removed
     *
     * @return true if equation was removed
     * false if not
     *
     * @see EquationRepository#remove(int)
     * @see EquationRepository_sqlite#remove(int)
     */
    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }
}
