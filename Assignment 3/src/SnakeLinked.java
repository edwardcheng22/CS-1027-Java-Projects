/**
 * CS 1027 Assignment 3
 * @author Edward Cheng
 * Student Number: 251024979
 * SnakeLinked.java 
 */

// Constructor
public class SnakeLinked {
	int snakeLength;
	DoubleList<Position>snakeBody;
	
/**
 * Instance variable snakeBody is initialized so it contains a DoubleList of nodes containing Position objects.
 * @param row
 * @param col
 */
	public SnakeLinked(int row, int col) {
		snakeLength = 1;
		snakeBody = new DoubleList<Position>();
		Position posObject = new Position(row, col); // New Object created
		snakeBody.addData(0, posObject); // Stored as first node of the list
	}
// Gets length of the snake	
	public int getLength() {
		return snakeLength;
	}

// Returns the Position object stored in the node of the doubly linked list with the given index
	public Position getPosition(int index) {
		if ((index < 0) || (index >= snakeLength)) {
			return null;
	}
		else {
			return snakeBody.getData(index);
		}
		
	}
	
// Returns true if pos is in the doubly linked list of snakeBody, and it returns false otherwise	
	public boolean snakePosition(Position pos) {
		
		boolean result = false;
		for(int i = 0; i < snakeLength; i++) {
			if(snakeBody.getData(i).equals(pos)) {
				result = true;
			}
		}
		return result;
}
	
// Returns the new position that the head of the snake would occupy if the snake moved in the direction specified by the parameter
		public Position newHeadPosition(String direction){
			
			// Obtain the position of the head
			Position snakeHead = snakeBody.getData(0);
			
			// Obtain the coordinates of the head
			int headCol = snakeHead.getCol();
			int headRow = snakeHead.getRow();
			
			if (direction == "up" ) {
				headRow--;
			}
			else if (direction == "down") {
				headRow++;
			}
			else if (direction == "left") {
				headCol--;
			} 
			else if (direction == "right") {
				headCol++;
			}

			
			// Return the new position of the head
			return new Position(headRow,headCol);
		}
// Moves the snake in the specified direction;
		
		public void moveSnakeLinked(String direction) {
			snakeBody.addData(0, newHeadPosition(direction));
			snakeBody.removeData(snakeLength);
			}

// Shrinks SnakeLength by 1 and deletes last node in the list		
		public void shrink() {
			snakeBody.getNode(0);
			snakeBody.removeData(snakeLength-1);
			snakeLength --;
		}
		
// Increases the length of the snake by 1 and moves the snake�s head in the direction specified
		
		public void grow(String direction) {
			Position newHeadData = newHeadPosition(direction);
			snakeBody.addData(0, newHeadData);
			snakeLength ++; 
		}		
}
