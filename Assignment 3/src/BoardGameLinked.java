/**
 * CS 1027 Assignment 3
 * @author Edward Cheng
 * Student Number: 251024979
 * BoardGameLinked.java 
 */

public class BoardGameLinked {

	private int boardLength; 
	private int boardWidth;
	private SnakeLinked theSnake;
	private DoubleList<String>[]board;
	
/**
 *  The parameter is the name of a file containing the dimensions of the game board, the initial position of the snake, and the objects placed on the game board
 * @param boardFile
 */
	public BoardGameLinked(String boardFile) {
		
		MyFileReader file = new MyFileReader(boardFile);
		
		//Ignores first two integers in file
		file.readInt();
		file.readInt();
		
		int lineCount = 2;
		
		//while loop that reads each line and stores the values in the variables initialized within the constructor
		while (!(file.endOfFile())) {
			if (lineCount == 2) {
				int line = file.readInt();
				boardLength = line;
				lineCount++;
			}
			else if (lineCount == 3) {
				int line = file.readInt();
				boardWidth = line;
				lineCount++;
			}
			else if (lineCount == 4) {
				int line = file.readInt();
				int row = line;
				line = file.readInt();
				int col = line;
				theSnake = new SnakeLinked(row, col);
						
				lineCount += 2;
				
				board = (DoubleList<String>[])(new DoubleList[boardWidth]);
				
				for (int x = 0; x < boardWidth; x++) {
					board[x] = new DoubleList<String>();
					for (int y = 0; y < boardLength; y++) {
						board[x].addData(0, "empty");
					}
				}
			}
			else {
				int x = file.readInt();
				int y = file.readInt();
				String object = file.readString();
				
				board[x].getNode(y).setData(object);
				
				lineCount += 3;
			}
		}
		
		
	}

/**
 * Returns the string stored in the node with index = col of the doubly linked list referenced by board[row]
 * @param row
 * @param col
 * @return
 * @throws InvalidPositionException
 */

	public String getObject(int row, int col) throws InvalidPositionException {
		if (row < 0 || col < 0 || row >= boardWidth || col >= boardLength) {
		      throw new InvalidPositionException("Invalid position");
		}
		String returnStr;
		returnStr = board[row].getData(col);
		
		return returnStr;
	}
	
// Stores newObject in the node with index = col of the doubly linked list referenced by board[row]	
	
	public void setObject(int row, int col, String newObject) {
		if (row < 0 || col < 0 || row >= boardWidth || col >= boardLength) {
		      throw new InvalidPositionException("Invalid position");
		}
		board[row].setData(col, newObject);
	}
	
// Returns theSnake
	
	public SnakeLinked getSnakeLinked() {
		return theSnake;
	}
// Stores the value of newSnake in instance variable theSnake

	public void setSnakeLinked(SnakeLinked newSnake) {
		newSnake = theSnake;
	}
	
	public int getLength() {
		return boardLength;
	}
	
	public int getWidth() {
		return boardWidth;
	}
}
