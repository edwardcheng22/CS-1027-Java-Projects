/**
 * CS 1027 Assignment 4
 * 
 * @author Edward Cheng Student Number: 251024979 OrderedCircularArray.java
 *         implements an ordered list using a circular array
 */

// Implements an ordered list using a circular array
public class OrderedCircularArray<T> implements SortedListADT<T> {

	private CellData[] list;
	private int front;
	private int rear;
	private int count;

	public OrderedCircularArray() {
		front = 1;
		rear = 0;
		count = 0;
		list = (CellData[]) (new CellData[5]);

	}

	/**
	 * Adds a new CellData object storing the given id and value to the ordered list
	 * 
	 * @param id
	 * @param value
	 */
	public void insert(T dataItem, int value) {
		
		CellData<T> newCell = new CellData<T>(dataItem, value); 
		
		if (count == 0) { 
			list[front] = newCell;
			rear = front;
		} 
		
		else if (count == 1) { 
			if (list[front].getValue() < value) {
				list[nextIndex(front)] = newCell;
			} 
			
			else {
				list[nextIndex(front)] = list[front];
				list[front] = newCell;
			}
		} 
		
		else if (count == 2) {
			
			int valFront  = list[front].getValue();
			int valRear = list[rear].getValue();
			
			
			if (value < valFront) {
				list[nextIndex(rear)] = list[rear];
				list[rear] = list[front];
				list[front] = newCell;
			} 
			
			else if (value < valRear) {
				list[nextIndex(rear)] = list[rear];
				list[rear] = newCell;
			} 
			
			else {
				list[nextIndex(rear)] = newCell;
			}
		} 
		
		else { 
		
			if (count == list.length) { 
				int oldIndex = front;
				int newIndex = front;
				
				CellData<T>[] tempList = (CellData<T>[]) (new CellData[list.length * 2]);
				
				for (int i = 0; i < count; i++) {
					tempList[newIndex] = list[oldIndex];
					newIndex = newIndex + 1;
					oldIndex = nextIndex(oldIndex);
				}

				list = tempList;
				rear = front;
				
				if (count != 1) {
				
					for (int i = 1; i < count; i++) {
						rear = nextIndex(rear);
					}
				}
			}
			
			int valFront = list[front].getValue();
			int valRear = list[rear].getValue();
			
			if (value <= valFront) {
				shiftAfterInsert(front, newCell);
			} 
			
			else if (value > valRear) {
				list[nextIndex(rear)] = newCell;
			} 
			
			else if (value == valRear) {
				list[nextIndex(rear)] = list[rear];
				list[rear] = newCell;
			} 
			
			else { 
				int index = front;
				boolean indexFound = false;
				int prevValue;
				int nextValue;
				
				while (index != rear) {
				
					if (list[nextIndex(index)] == null) {
					
						if (list[index].getValue() < value) {
							index = nextIndex(rear);
							indexFound = true;
						} 
						
						else if (list[index].getValue() == value) {
							index = rear;
							indexFound = true;
						}
						
						if (indexFound == false) {
						}
						
						break;
					}
					
					prevValue = list[index].getValue();
					nextValue = list[nextIndex(index)].getValue();
					
					
					if (prevValue == value) {
						indexFound = true;
						break;
					} 
					
					else if (nextValue == value) {
						index = nextIndex(index);
						indexFound = true;
						break;
					}
					
					else if (prevValue < value && value < nextValue) {
						index = nextIndex(index);
						indexFound = true;
						break;
					}
					
					else {
						index = nextIndex(index);
						continue;
					}
				}
				shiftAfterInsert(index, newCell); 
			}
		}

		count++; 
		
		if (count != 1) {
			rear = front; 
		
			for (int i = 1; i < count; i++) { 
				rear = nextIndex(rear);
			}
		} 
		
		else {
			rear = front; 
		}

	}

	/**
	 * Next Index Helper Method
	 * @param i
	 * @return
	 */

	private int nextIndex(int i) {
		
		if (i + 1 == list.length) { 
			return 0;
		} 
		
		else {
			return i + 1;
		}
	}

	
	/**
	 * Previous Index Helper Method
	 * @param i
	 * @return
	 */
	private int previousIndex(int i) {
		if (i == 0) {
			return list.length - 1;
		} 
		
		else {
			return i - 1;
		}

	}

	/**
	 * Helper method for insert
	 * 
	 * @param index
	 * @param cell  
	 */
	
	private void shiftAfterInsert(int index, CellData<T> cell) {
		
		int movedItems = 0;
		
		CellData<T>[] tempList = (CellData<T>[]) (new CellData[list.length]);
		
		for (int i = front; i != index; i = nextIndex(i)) {
			tempList[i] = list[i];
			movedItems++;
		}
		
		tempList[index] = cell; 
		
		for (int i = nextIndex(index); movedItems < count; i = nextIndex(i)) {
			tempList[i] = list[previousIndex(i)];
			movedItems++;
		}
		
		list = tempList;
	}

	/**
	 * Returns the integer value of the CellData object with the specified id
	 * 
	 * @param id
	 */
	public int getValue(T id) throws InvalidDataItemException {
	
		boolean found = false;
		int result = -1;
		
		for (int i = front; i < rear; i = (i+1)%(list.length)) {
			
			if (list[i].getId().equals(id)) {
				found = true;
				result = list[i].getValue(); // Stores the integer value of the CellData object with the specified id
			}
		}
		if (!found) {
			throw new InvalidDataItemException("No Cell Object in List");
		}
		return result;	
	}

	public void remove(T id) throws InvalidDataItemException {

		if (count == 0) {
			throw new InvalidDataItemException("No Cell Object in List");

		}
		
		else if (count == 1) {

			if (list[front].getId() == id) {
				list[front] = null;

			} 
			
			else {
				throw new InvalidDataItemException("No Cell Object in List");
			}

		} 
		
		else {
			
			int position = -1;
			boolean located = false;

			for (int i = 0; i < list.length; i++) {

				if (list[i] != null) {

					if (list[i].getId().equals(id)) {

						list[i] = null;
						position = i;
						located = true;
						break;
					}
				}
			}

			if (located == true && position != -1) {

				list[position] = null;
				while (position != rear) {
					list[position] = list[nextIndex(position)];
					position = nextIndex(position);
				}
				list[rear] = null;

			} else {
				throw new InvalidDataItemException("No Cell Object in List");
			}
		}
		count--;

		if (count == 1) {
			rear = front;

		} else if (count == 0) {
			rear = nextIndex(front);

		} else {
			rear = front;

			for (int i = 1; i < count; i++) {
				rear = nextIndex(rear);
			}
		}
	}

	/**
	 * Removes from the ordered list the CellData object with the specified id
	 * 
	 * @param id
	 * @param newValue
	 */
	public void changeValue(T id, int newValue) throws InvalidDataItemException {
		remove(id);
		insert(id, newValue);

	}

	/**
	 * Removes and returns the id or the CellData object in the ordered list with
	 * smallest associated value
	 */

	public T getSmallest() throws EmptyListException {
		if (count == 0)
			throw new EmptyListException("Empty List");
		T result = (T) list[front].getId();
		list[front] = null;
		count = count - 1;
		front = (front + 1) % list.length;
		return result;
	}

// Checks if list is empty
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

// Returns the number of data items in the ordered list
	public int size() {
		return count;
	}

// Return front
	public int getFront() {
		return front;
	}

// Return rear
	public int getRear() {
		return rear;
	}

}
