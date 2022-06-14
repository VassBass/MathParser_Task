package repository;

import model.Equation;

import java.util.ArrayList;

public interface EquationRepository {

    /**
     * @return list of equations
     * @see EquationRepository_impl#getAll() 
     */
    ArrayList<Equation>getAll();

    /**
     * @param id of equation who need to search
     * @return equation with this id or null if equation with this id not found
     * @see EquationRepository_impl#get(int)
     */
    Equation get(int id);

    /**
     * Adds new equation
     * @param equation to add
     * @return id of added equation or -1 if equation wasn't added
     * @see EquationRepository_impl#add(Equation)
     */
    int add(Equation equation);

    /**
     * Changes equation with id of equation from @param
     * @param equation to change
     * @return true if equation was changed or false if not
     * @see EquationRepository_impl#set(Equation) 
     */
    boolean set(Equation equation);

    /**
     * Removes equation with this id
     * @param id of equation who need to removed
     * @return true if equation was removed or false if not
     * @see EquationRepository_impl#remove(int)
     */
    boolean remove(int id);
}
