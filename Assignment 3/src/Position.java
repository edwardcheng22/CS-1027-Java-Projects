/**
 * CS 1027 Assignment 3
 * @author Edward Cheng
 * Student Number: 251024979
 * Position.java represents the position of a square of the grid
 */

public class Position {
	private int positionRow;
	private int positionColumn;
	
/**
 * Constructor which takes the row and col as parameters
 * @param row
 * @param col
 */
	public Position(int row, int col) {
		positionRow = row;
		positionColumn = col;
	}

// Method that returns the row of the Position	
	public int getRow() {
		return positionRow;
	}
// Same idea as positionRow	
	public int getCol() {
		return positionColumn;
	}
/**
 * Stores the value of newRow in positionRow.
	
 * @param row
 */
	public void setRow(int newrow) {
		positionRow = newrow;
	}
	
	public void setCol(int newcol) {
		positionColumn = newcol;
	}
	
// returns true if this Position object and otherPosition have the same values stored in positionRow and positionColumn.
	public boolean equals(Position otherPosition) {
		if ((positionRow == otherPosition.getRow()) && (positionColumn == otherPosition.getCol())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
