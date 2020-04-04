/**
 * CS 1027 Assignment 1 
 * @author Edward Cheng
 * 251024979
 * Snake.java stores the information about the snake as it moves around the board
 */
public class Snake {

private int snakeLength;
private Position[]snakeBody;

// Snake class initializes the snake length at 1; snakebody array initialized as well
public Snake(int row, int col) {
	snakeLength = 1;
	snakeBody = new Position[5];
	Position posObject = new Position(row, col);
	snakeBody[0] = posObject;
	}

// Gets the length of the snake
public int getLength() {
	return snakeLength; 
	}

/**
 * Retrieves the position of the snake
 * @param index
 * @return
 */
public Position getPosition(int index) {
	if ((index < 0) || (index >= snakeLength)) {
		return null;
	} else {
		return snakeBody[index];
	}
	}
// Shrink snake length by 1
public void shrink() {
	snakeLength -= 1;
	}

/**
 * Returns true if pos is in array snakeBody, and it returns false otherwise. 
 * @param pos
 * @return
 */
public boolean snakePosition(Position pos) {
	for (int i = 0; i < snakeLength; i++) {
		if (snakeBody[i].equals(pos)) {
			return true;
		}
	}
	return false;
	}

/**
 * newHeadPosition takes direction as the parameter, and decreases/increases the row/column according to the direction given
 * @param direction
 * @return
 */
public Position newHeadPosition(String direction){
	int tmpRow = snakeBody[0].getRow();
	int tmpCol = snakeBody[0].getCol();
	
	if (direction == "up" ) {
		tmpRow--;
	}
	else if (direction == "down") {
		tmpRow++;
	}
	else if (direction == "left") {
		tmpCol--;
	} 
	else if (direction == "right") {
		tmpCol++;
	}
	else {
		;
	}
	Position newPos = new Position(tmpRow, tmpCol);
	return newPos;
	}

/**
 * When the snake moves, the array must be updated to contain the new positions of the snake 
 * @param direction
 */
public void moveSnake(String direction) {
	
	for (int i=snakeLength-1; i > 0; i--) {
		Position tmpPos = snakeBody[i-1];
		snakeBody[i] = tmpPos;
	}
	snakeBody[0] = newHeadPosition(direction);
	}

/**
 * Increases the length of the snake by 1 and moves the snake’s head in thedirection specified. T
 * @param direction
 */
public void grow(String direction) {
	snakeLength += 1;
	if (snakeBody.length == snakeLength) {
		increaseArraySize();
	} else {
		;
	}
	for (int i=snakeLength-1; i > 0; i--) {
		Position tmpPos = snakeBody[i-1];
		snakeBody[i] = tmpPos;
	}
	snakeBody[0] = newHeadPosition(direction);
	}


// Doubles size of the array, with the ability to contain more information 
private void increaseArraySize() {
	Position[] newSnakeArray = new Position[snakeBody.length*2];
	
	for (int i = 0; i < snakeLength; i++) {
		newSnakeArray[i] = snakeBody[i];
	}
	snakeBody = newSnakeArray;
	}

}

