/**
 * CS 1027 Assignment 4
 * @author Edward Cheng
 * Student Number: 251024979
 * CellData.java represents the data items that will be stored in the circular array. 
 */

// Represents the data items that will be stored in the circular array.

public class CellData<T>{

	private T id;
	private int value;
	
/**
 * Constructor 	
 * @param theId
 * @param theValue
 */
	public CellData(T theId, int theValue) {
		id = theId;
		value = theValue;
	}
	
// Return value
	public int getValue() {
		return value;
	}

// Return id
	public T getId() {
		return id;
	}

/**
 * Assigns newValue to instance variable value
 * @param newValue
 */
	public void setValue(int newValue) {
		newValue = value;
	}

/**
 * Assigns the value of newId to instance variable id
 * @param newId
 */
	public void setId(T newId) {
		newId = id;
	}
	
}
