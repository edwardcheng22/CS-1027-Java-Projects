import java.io.*;

/**
 * CS 1027 Assignment 1
 * @author Edward Cheng
 * Student Number: 251024979
 * BoardGame.java represents the board game where the snake moves moves around eating apples
 */
public class BoardGame {
	
	private int board_length;
	private int board_width;
	private Snake theSnake; 
	private String[][] matrix; 
	
/**j
 * BoardGame method takes in a board file, inputted from the user, as a parameter
 * @param boardFile
 */
	
public BoardGame(String boardFile) {
	
	MyFileReader file = new MyFileReader(boardFile);
	
	//Ignores first two integers in file
	file.readInt();
	file.readInt();
	
	int lineCount = 2;
	
	//while loop that reads each line and stores the values in the variables initialized within the constructor
	while (!(file.endOfFile())) {
		if (lineCount == 2) {
			int line = file.readInt();
			board_length = line;
			lineCount++;
		}
		else if (lineCount == 3) {
			int line = file.readInt();
			board_width = line;
			lineCount++;
		}
		else if (lineCount == 4) {
			int line = file.readInt();
			int row = line;
			line = file.readInt();
			int col = line;
			theSnake = new Snake(row, col);
					
			lineCount += 2;
			
			matrix = new String[board_width][board_length];
			
			for (int x = 0; x < board_width; x++) {
				for (int y = 0; y < board_length; y++) {
					matrix[x][y] = "empty";
				}
			}
		}
		else {
			int x = file.readInt();
			int y = file.readInt();
			String object = file.readString();
			
			matrix[x][y] = object;
			
			lineCount += 3;
		}
	}
	
	
}

/**
 * getObject method returns string stored in the matrix
 * @param row
 * @param col
 * @return
 */
public String getObject(int row, int col) {
	
	return matrix[row][col];
	}

/**
 * Stores newObject in instance variable theSnake
 * @param row
 * @param col
 * @param newObject
 */
public void setObject(int row, int col, String newObject) {
	matrix[row][col] = newObject;
}

// Returns length of the snake
public Snake getSnake() {
	return theSnake;
}

public void setSnake(Snake newSnake) {
	theSnake = newSnake;
}

public int getLength() {
	return board_length;
}

public int getWidth() {
	return board_width;
}

/**
 * Returns row and column of the matrix
 * @param row
 * @param col
 * @return
 */
public String getType(int row, int col) {
	
	return matrix[row][col]; 
}

}



